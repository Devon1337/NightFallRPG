package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Cooldown;
import com.devon1337.RPG.Utils.AOEMapping.AreaMap;
import com.devon1337.RPG.Utils.Raycast.Raycast;

import lombok.Getter;
import lombok.Setter;

public abstract class Spell implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5751398408689075937L;

	@Getter
	String Name, Description;

	@Getter
	boolean Enabled = false;
	
	@Getter
	@Setter
	int id, CooldownTime, level;
	
	@Getter @Setter
	double DamageAmount = 0, StunDuration = 0, RangeAmount = 0;
	
	// IE: Warrior, Rogue, Druid, Mage
	@Getter
	NFClasses spellClass;

	@Getter
	Material icon;
	@Getter
	ItemStack item;

	/*
	 * GroupCast - AOE
	 * SkillShot - Single Target
	 * SelfCast  - Only Applies to self
	 */
	@Getter
	@Setter
	SpellType spellType;

	@Getter @Setter
	ISpell spell;

	public static ArrayList<Cooldown> GlobalCooldown = new ArrayList<Cooldown>();

	@Getter
	ArrayList<Passive> availPassives;

	// Creates the spell
	public Spell(String Name, String Description, SpellType spellType, Material icon, int CooldownTime, int level,
			NFClasses spellClass, ArrayList<Passive> availPassives) {
		this.Name = Name;
		this.id = GlobalSpellbook.getSpellSize();
		this.spellType = spellType;
		this.icon = icon;
		this.CooldownTime = CooldownTime;
		this.level = level;
		this.Description = Description;
		this.item = generateItem(this.icon, 1, this);
		this.spellClass = spellClass;
		this.availPassives = availPassives;

		GlobalSpellbook.addSpell(this);
		Logging.OutputToConsole("Registered: " + Name);
	}

	// Adds the player to the global cooldown
	public static void addPlayerCooldown(Player player, Spell spell) {
		GlobalCooldown.add(new Cooldown(player.getUniqueId(), spell, spell.getCooldown()));
	}

	// Updates the spell's cooldown for the player
	public static void runCooldown() {

		for (int i = 0; i < GlobalCooldown.size(); i++) {

			if (GlobalCooldown.get(i).getTime() == 1) {
				GlobalCooldown.remove(GlobalCooldown.get(i));
			} else {
				GlobalCooldown.get(i).setTime(GlobalCooldown.get(i).getTime() - 1);
				updateItem(Bukkit.getPlayer(GlobalCooldown.get(i).getUUID()),
						generateItem(GlobalCooldown.get(i).getSpell().icon, GlobalCooldown.get(i).getTime(),
								GlobalCooldown.get(i).getSpell()),
						GlobalCooldown.get(i).getTime());

				Logging.OutputToPlayer("Spell " + GlobalCooldown.get(i).getSpell().Name + " has "
						+ GlobalCooldown.get(i).getTime() + " seconds remaining.",
						Bukkit.getPlayer(GlobalCooldown.get(i).getUUID()));
			}

		}
	}

	// Updates the item to reflect the cooldown
	public static void updateItem(Player player, ItemStack item, int time) {

		for (int i = 0; i < 3; i++) {
			Logging.OutputToConsole("Item: " + item.getType() + " Name: " + item.getItemMeta().getDisplayName());
			Logging.OutputToConsole("Name: " + player.getInventory().getItem(i).getItemMeta().getDisplayName());
			if (player.getInventory().getItem(i) != null && player.getInventory().getItem(i).getItemMeta()
					.getDisplayName().equals(item.getItemMeta().getDisplayName())) {
				player.getInventory().setItem(i, generateCooldownItem(item.getType(), time, item.getItemMeta()));
			}
		}

	}

	// Generates the item for #updateItem(Player, ItemStack, int)
	public static ItemStack generateCooldownItem(Material Item, int count, ItemMeta meta) {
		ItemStack is = new ItemStack(Item, count);
		is.setItemMeta(meta);
		return is;
	}

	// General #generateItem() method
	public static ItemStack generateItem(Material Item, int count, Spell s) {
		ItemStack item = new ItemStack(Item, count);
		ItemMeta meta = item.getItemMeta();
		NamespacedKey key = new NamespacedKey(NightFallRPG.getPlugin(),"nightfall-rpg-spell");
		meta.setDisplayName(s.Name);
		ArrayList<String> metalore = new ArrayList<String>();
		metalore.add(s.Description);
		metalore.add("Cooldown: " + s.CooldownTime);
		metalore.add("Spell Type: " + s.getSpellType());
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		meta.setLore(metalore);
		meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
		item.setItemMeta(meta);
		return item;
	}

	// Getters/Setters
	public int getCooldown() {
		return CooldownTime;
	}

	public void setCooldown(int CooldownTime) {
		this.CooldownTime = CooldownTime;
	}

	// Self casting spells
	public static void CastSelf(Spell s, Player p) {
		s.getSpell().use(p, null);
		runPassive(s, NFPlayer.getPlayer(p.getUniqueId()), null);
		// s.runAbility(s, p, null);
	}

	
	public void runSpelltest(Player player, ArrayList<Player> targets) {
		this.spell.use(player, targets);
	}

	// Weapon Art/Skill shot Casting
	public static void CastAttack(Spell s, Player p, Player t) {
		ArrayList<Player> tempPlayer = new ArrayList<Player>();
		if (s.getSpellType() == SpellType.WeaponArt) {
			tempPlayer.add(t);
		} else {
			tempPlayer.add(t);
		}

		for (Player target : tempPlayer) {
			runPassive(s, NFPlayer.getPlayer(p.getUniqueId()), NFPlayer.getPlayer(target.getUniqueId()));
		}
		// runAbility(s, p, tempPlayer);
	}

	// AOE casting
	public static void CastGroup(Spell s, Player p) {
		ArrayList<Player> tempPlayer = new AreaMap(p.getWorld(), p.getLocation(), 10).getHostilePlayers(p);

		s.getSpell().use(p, tempPlayer);
		
		for(Player target : tempPlayer) {
		runPassive(s, NFPlayer.getPlayer(p.getUniqueId()), NFPlayer.getPlayer(target.getUniqueId()));
		}
		// runAbility(s, p, tempPlayer);
	}

	@SuppressWarnings("incomplete-switch")
	public static void start(Spell s, Player p) {
		Cooldown cd = getPlayerCooldown(p);
		if (cd == null || cd.getSpell() != s) {
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

	// Get the players cooldown
	public static Cooldown getPlayerCooldown(Player player) {
		for (Cooldown cd : GlobalCooldown) {
			if (cd.getUUID() == player.getUniqueId()) {
				return cd;
			}
		}
		return null;
	}

	// Stage two of ability use
	public static void runPassive(Spell s, NFPlayer player, NFPlayer target) {
		for (Passive pt : player.getPassives()) {
			if (s.getAvailPassives().contains(pt)) {
				pt.run(s ,player, target);
			}
		}
	}

}