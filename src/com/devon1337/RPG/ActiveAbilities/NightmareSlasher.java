package com.devon1337.RPG.ActiveAbilities;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.inventivetalent.glow.GlowAPI;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.PassiveAbilities.Passive;

public class NightmareSlasher extends Spell implements ISpell{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5446123036318609875L;
	// Predefined Variables
	static final String Name = ChatColor.GRAY + "Nightmare " + ChatColor.DARK_GRAY + "Slasher", Description = "Enter someone's dreams to seperate their body.";
	static final NFClasses classReq = NFClasses.ROGUE;
	static final ArrayList<Passive> availPassives = new ArrayList<Passive>();
	static final Material spellIcon = Material.IRON_SWORD;
	static final NamespacedKey key = new NamespacedKey(NightFallRPG.getPlugin(), "nm_slasher");
	static final SpellType spellType = SpellType.WeaponArt;
	
	public NightmareSlasher() {
		super(Name, Description, spellType, spellIcon, 10, 1, classReq, availPassives);
		super.setSpell(this);
	}
	
	public static ItemStack getWeapon() {
		return createGuiItem(Material.DIAMOND_SWORD, 1, "Dream Drain");
	}
	
	public static void Slash(Player player, Player target) {
		target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*20,1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*20, 1));
		GlowAPI.setGlowing(player, GlowAPI.Color.WHITE, target);
	}
	
	public static ItemStack createGuiItem(Material material, int amount, String name, String... lore) {
	     
		
		ItemStack item = new ItemStack(material, amount);
	     
	     ItemMeta meta = item.getItemMeta();
	     
	     
	    meta.setDisplayName(name);
		ArrayList<String> metalore = new ArrayList<>(); byte b; int i;
	     String[] arrayOfString;
	     for (i = (arrayOfString = lore).length, b = 0; b < i; ) { String lorecomments = arrayOfString[b];
	      metalore.add(lorecomments);
	       b++; }
	     meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
	     
	     meta.setLore(metalore);
	     meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
	     item.setItemMeta(meta);
	     return item;
	   }
	
	
	public static NamespacedKey getKey() {
		return key;
	}

	@Override
	public double use(Player player, ArrayList<Player> targets) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ISpell getISpell() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void addPassive(Passive p) {
		availPassives.add(p);
	}
}