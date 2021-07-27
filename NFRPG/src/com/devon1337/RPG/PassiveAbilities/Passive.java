package com.devon1337.RPG.PassiveAbilities;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Player.NFPlayer;

import lombok.Getter;
import lombok.Setter;

public abstract class Passive implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2281975162444200530L;
	String name, description;
	int level;
	PassiveType pType;
	@Getter
	UseType type;
	ItemStack item;
	
	// Toggles on
	@Getter @Setter
	boolean Enabled = false;
	
	long Duration;
	
	@Getter
	NFClasses team;
	
	@Getter @Setter
	IPassive passive;
	
	public BukkitScheduler scheduler = Bukkit.getScheduler();
	
	public static Material DefaultIcon = Material.IRON_SWORD;
	
	public Passive(String name, int level, PassiveType pType, Material mat, NFClasses team, long Duration, UseType type) {
		this.name = name;
		this.level = level;
		this.pType = pType;
		this.team = team;
		this.type = type;
		this.Duration = Duration;
		item = generateItem(mat, 1, this);
		GlobalSpellbook.addPassive(this);
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public ItemStack getItem() {
		return this.item;
	}
	
	public PassiveType getPType() {
		return this.pType;
	}
	
	public void run(Spell s, NFPlayer player, NFPlayer target) {
		this.passive.run(s, player, target);
	}
	
	// For whatever fucking reason we cant edit Enabled in this fucking method
	public void start(NFPlayer player) {

		// Runs passive
		this.passive.start(player);
		// Duration -1 is a toggle
		if (Duration > 0) {
			this.scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
				public void run() {
					passive.stop(player);
					Enabled = false;
				}
			}, Duration);

		}

	}
	
	public void stop(NFPlayer player) {
		if(Duration == -1) {
			passive.stop(player);
			Enabled = false;
		}
	}

	public static ItemStack generateItem(Material Item, int count, Passive p) {
		ItemStack item = new ItemStack(Item, count);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(p.getName());
		ArrayList<String> metalore = new ArrayList<String>();
		metalore.add(p.getDescription());
		meta.addItemFlags(new ItemFlag[] {ItemFlag.HIDE_ATTRIBUTES});
		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}
	
	@SuppressWarnings("static-access")
	public Material getIcon() {
		return this.DefaultIcon;
	}
	
	
}
