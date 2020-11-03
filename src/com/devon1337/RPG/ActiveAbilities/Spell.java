package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.PassiveAbilities.PassiveType;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Cooldown;
import com.devon1337.RPG.Utils.AOEMapping.AreaMap;
import com.devon1337.RPG.Utils.Raycast.Raycast;

public abstract class Spell implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5751398408689075937L;
	String Name, Description;
	int id, CooldownTime, level;
	NFClasses spellClass;
	Material spellIcon;
	ItemStack spellItem;
	SpellType spellType;

	public static ArrayList<Cooldown> GlobalCooldown = new ArrayList<Cooldown>();
	PassiveType[] availPassives;

	public Spell(String Name, String Description, int id, SpellType spellType, Material spellIcon, int CooldownTime,
			int level, NFClasses spellClass, PassiveType[] availPassives) {
		this.Name = Name;
		this.id = id;
		this.spellType = spellType;
		this.spellIcon = spellIcon;
		this.CooldownTime = CooldownTime;
		this.level = level;
		this.Description = Description;
		this.spellItem = generateItem(this.spellIcon, 1, this);
		this.spellClass = spellClass;
		this.availPassives = availPassives;
		GlobalSpellbook.addSpell(this);
		Logging.OutputToConsole("Registered: " + Name);
	}

	public static void addPlayerCooldown(Player player, Spell spell) {
		GlobalCooldown.add(new Cooldown(player.getUniqueId(), spell, spell.getCooldown()));
	}

	public static void runCooldown() {

		for (int i = 0; i < GlobalCooldown.size(); i++) {

			if (GlobalCooldown.get(i).getTime() == 1) {
				GlobalCooldown.remove(GlobalCooldown.get(i));
			} else {
				GlobalCooldown.get(i).setTime(GlobalCooldown.get(i).getTime() - 1);
				updateItem(Bukkit.getPlayer(GlobalCooldown.get(i).getUUID()),
						generateItem(GlobalCooldown.get(i).getSpell().spellIcon, GlobalCooldown.get(i).getTime(),
								GlobalCooldown.get(i).getSpell()));
				
				Logging.OutputToPlayer("Spell " + GlobalCooldown.get(i).getSpell() + " has " + GlobalCooldown.get(i).getTime() + " seconds remaining.", Bukkit.getPlayer(GlobalCooldown.get(i).getUUID()));
			}

		}
	}

	public static void updateItem(Player player, ItemStack item) {
		Logging.OutputToConsole("updating Items");
		for (int i = 0; i < 3; i++) {
			if (player.getInventory().getItem(i) != null
					&& player.getInventory().getItem(i).getItemMeta().getDisplayName() == item.getItemMeta()
							.getDisplayName()
					&& player.getInventory().getItem(i).getItemMeta().getLore() == item.getItemMeta().getLore()) {
				player.getInventory().setItem(i, item);
			}
		}

	}

	public static ItemStack generateItem(Material Item, int count, Spell s) {
		ItemStack item = new ItemStack(Item, count);
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(s.Name);
		ArrayList<String> metalore = new ArrayList<String>();
		metalore.add(s.Description);
		metalore.add("Cooldown: " + s.CooldownTime);
		metalore.add("Spell Type: " + s.getSpellType());
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}

	public NFClasses getClassReq() {
		return this.spellClass;
	}

	public PassiveType[] getPassives() {
		return this.availPassives;
	}

	public SpellType getSpellType() {
		return this.spellType;
	}

	public void setSpellType(SpellType spellType) {
		this.spellType = spellType;
	}

	public ItemStack getItem() {
		return this.spellItem;
	}

	// Getters/Setters
	public int getCooldown() {
		return CooldownTime;
	}

	public void setCooldown(int CooldownTime) {
		this.CooldownTime = CooldownTime;
	}

	public int getId() {
		return this.id;
	}

	public String getDescription() {
		return this.Description;
	}

	public int getLevel() {
		return this.level;
	}

	public Material getIcon() {
		return this.spellIcon;
	}

	public String getName() {
		return this.Name;
	}

	@SuppressWarnings("static-access")
	public static void CastSelf(Spell s, Player p) {
		s.runAbility(s, p, null);
	}

	public static void CastAttack(Spell s, Player p, Player t) {
		ArrayList<Player> tempPlayer = new ArrayList<Player>();
		if (s.getSpellType() == SpellType.WeaponArt) {
			tempPlayer.add(t);
		} else {
			tempPlayer.add(t);
		}

		runAbility(s, p, tempPlayer);
	}

	public static void CastGroup(Spell s, Player p) {
		ArrayList<Player> tempPlayer = new AreaMap(p.getWorld(), p.getLocation(), 10).getHostilePlayers(p);
		runAbility(s, p, tempPlayer);
	}

	@SuppressWarnings("incomplete-switch")
	public static void start(Spell s, Player p) {
		Cooldown cd = getPlayerCooldown(p);
		if(cd == null) {
		switch (s.getSpellType()) {
		case SkillShot:
			Logging.OutputToConsole("Starting Cast");
			new Raycast(p.getUniqueId(), s);
			break;
		case GroupCast:
			CastGroup(s, p);
			break;
		case QuickCast:
			CastSelf(s, p);
			break;
		}
		} 

	}
	
	public static Cooldown getPlayerCooldown(Player player) {
		for(Cooldown cd : GlobalCooldown) {
			if(cd.getUUID() == player.getUniqueId()) {
				return cd;
			}
		}
		return null;
	}

	// Stage one of ability use
	@SuppressWarnings("deprecation")
	public static void runAbility(Spell s, Player player, ArrayList<Player> targets) {
		
		Double damageDone = 0.0;
		
		GlobalCooldown.add(new Cooldown(player.getUniqueId(), s, s.getCooldown()));
		switch (s.getName()) {
		case "Assassinate":
			Assassinate.use(player, targets.get(0));
			break;
		case "Blood Shield":
			damageDone = Blood_Shield.use(player, targets);
			break;
		case "Charge":
			Charge.use(player);
			break;
		case "Confusion":
			Confusion.use(player, targets.get(0));
			break;
		case "Entanglement":
			Entanglement.use(player, targets.get(0));
			break;
		case "Fireball":
			damageDone = Fireball.use(player, targets.get(0));
			break;
		case "Heated Juggernaut":
			HeatedJuggernaut.use(player, targets);
			break;
		case "Mark of the Wild":
			MOTW.use(player);
			break;
		case "Nightmare Slasher":
			player.setItemInHand(NightmareSlasher.getWeapon());
			break;
		case "Dream Drain":

			break;
		case "Rejuvenate":
			Rejuvenate.use(player, targets);
			break;
		case "Shield Bash":
			Shield_Bash.use(player, targets.get(0));
			break;
		case "Shield Slam":
			Shield_Slam.use(player, targets);
			break;
		case "Starfire":
			Starfire.use(player, targets);
			break;
		case "Tranquility":
			Tranquility.use(player, targets.get(0));
			break;
		case "Vanish":
			Vanish.use(player);
			break;
		case "Wrath":
			damageDone = Wrath.use(player, targets.get(0));
			break;
		}
		runPassive(s, NFPlayer.getPlayer(player.getUniqueId()), damageDone);
		
		
			
		

	}

	
	// Stage two of ability use
	public static void runPassive(Spell s, NFPlayer player, double DamageAmount) {
		for (PassiveType pt : s.getPassives()) {
			Passive nfPassive = player.getPassive(pt);
			if (nfPassive != null) {
				nfPassive.run(player, DamageAmount);
			}

		}
	}

}