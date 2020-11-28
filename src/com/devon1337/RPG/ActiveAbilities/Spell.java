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
	ISpell spellClasses;

	public static ArrayList<Cooldown> GlobalCooldown = new ArrayList<Cooldown>();
	PassiveType[] availPassives;

	public Spell(String Name, String Description, SpellType spellType, Material spellIcon, int CooldownTime,
			int level, NFClasses spellClass, PassiveType[] availPassives) {
		this.Name = Name;
		this.id = GlobalSpellbook.getSpellSize();
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
								GlobalCooldown.get(i).getSpell()), GlobalCooldown.get(i).getTime());
				
				Logging.OutputToPlayer("Spell " + GlobalCooldown.get(i).getSpell().Name + " has " + GlobalCooldown.get(i).getTime() + " seconds remaining.", Bukkit.getPlayer(GlobalCooldown.get(i).getUUID()));
			}

		}
	}

	public static void updateItem(Player player, ItemStack item, int time) {
		
		for (int i = 0; i < 3; i++) {
			Logging.OutputToConsole("Item: " + item.getType() + " Name: " + item.getItemMeta().getDisplayName());
			Logging.OutputToConsole("Name: " + player.getInventory().getItem(i).getItemMeta().getDisplayName());
			if (player.getInventory().getItem(i) != null && player.getInventory().getItem(i).getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
				player.getInventory().setItem(i, generateCooldownItem(item.getType(), time, item.getItemMeta()));
			}
		}

	}

	public static ItemStack generateCooldownItem(Material Item, int count, ItemMeta meta) {
		ItemStack is = new ItemStack(Item, count);
		is.setItemMeta(meta);
		return is;
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

	public void setISpell(ISpell spell) {
		this.spellClasses = spell;
	}
	
	public ISpell getSpellMethod() {
		return this.spellClasses;
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

	public static void CastSelf(Spell s, Player p) {
		s.getSpellMethod().use(p, null);
		//s.runAbility(s, p, null);
	}
	
	public void runSpelltest(Player player, ArrayList<Player> targets) {
		this.spellClasses.use(player, targets);
	}

	public static void CastAttack(Spell s, Player p, Player t) {
		ArrayList<Player> tempPlayer = new ArrayList<Player>();
		if (s.getSpellType() == SpellType.WeaponArt) {
			tempPlayer.add(t);
		} else {
			tempPlayer.add(t);
		}
		
		s.getSpellMethod().use(p, tempPlayer);
		
		//runAbility(s, p, tempPlayer);
	}

	public static void CastGroup(Spell s, Player p) {
		ArrayList<Player> tempPlayer = new AreaMap(p.getWorld(), p.getLocation(), 10).getHostilePlayers(p);
		
		s.getSpellMethod().use(p, tempPlayer);
		
		//runAbility(s, p, tempPlayer);
	}

	@SuppressWarnings("incomplete-switch")
	public static void start(Spell s, Player p) {
		Cooldown cd = getPlayerCooldown(p);
		if(cd == null || cd.getSpell() != s) {
		switch (s.getSpellType()) {
		case SkillShot:
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

	
	/*
	// Stage one of ability use
	@SuppressWarnings("deprecation")
	public static void runAbility(Spell s, Player player, ArrayList<Player> targets) {
		
		Double damageDone = 0.0;
		Logging.OutputToConsole("Spell: " + s.Name + " Player: " + player.getName());
		GlobalCooldown.add(new Cooldown(player.getUniqueId(), s, s.getCooldown()));
		switch (s.getId()) {
		case 0:
			
			//Assassinate.use(player, targets);
			break;
		case 1:
			damageDone = Blood_Shield.use(player, targets);
			break;
		case 2:
			Charge.use(player);
			break;
		case 3:
			Confusion.use(player, targets.get(0));
			break;
		case 4:
			Entanglement.use(player, targets.get(0));
			break;
		case 5:
			//new NFParticle(player);
			damageDone = Fireball.use(player, targets);
			break;
		case 6:
			HeatedJuggernaut.use(player, targets);
			break;
		case 10:
			MOTW.use(player);
			break;
		case 8:
			player.setItemInHand(NightmareSlasher.getWeapon());
			break;
		case 9:

			break;
		case 7:
			Rejuvenate.use(player, targets);
			break;
		case 11:
			Shield_Bash.use(player, targets.get(0));
			break;
		case 12:
			Shield_Slam.use(player, targets);
			break;
		case 13:
			Starfire.use(player, targets);
			break;
		case 14:
			Tranquility.use(player, targets.get(0));
			break;
		case 15:
			Vanish.use(player);
			break;
		case 16:
			damageDone = Wrath.use(player, targets.get(0));
			break;
		}
		runPassive(s, NFPlayer.getPlayer(player.getUniqueId()), damageDone);
		
		
			
		

	}
*/
	
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