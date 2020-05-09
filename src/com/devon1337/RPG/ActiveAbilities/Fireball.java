package com.devon1337.RPG.ActiveAbilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Player.PlayerStats;
import com.devon1337.RPG.Utils.Raycast.ProjectileType;
import com.devon1337.RPG.Utils.Raycast.Simulate;
import com.devon1337.RPG.Utils.Raycast.Exceptions.BadProjectile;
import com.devon1337.RPG.Utils.Raycast.Exceptions.InvalidTarget;
import com.devon1337.RPG.Utils.Raycast.Exceptions.ObjectsNotUsed;

public class Fireball {
	public final int COOLDOWN_AMOUNT = 10;
	public final int DURATION_AMOUNT = 4;
	public final int CLASS_TYPE = 3; // -- Mage
	public final Material ITEM = Material.RED_DYE;
	public final Material CD_ITEM = Material.LIGHT_BLUE_DYE;
	public BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

	public static HashMap<Player, Integer> pCooldowns = new HashMap<Player, Integer>();
	
	public PlayerStats stats = new PlayerStats();
	
	public void gameplayScheduler(Plugin plugin, Location local, World world) {
		//int i = 0;
		
		
		
		Location loc = local;
		
		new BukkitRunnable() {
			int repeatAmount = 50;
			
			@Override
			public void run() {
				if(repeatAmount <= 0) {
					this.cancel();
					return;
				}
				repeatAmount--;
				world.spawnParticle(Particle.DRIP_LAVA, loc, 4);
			}
		}.runTaskTimer(plugin, 0, 5);
		
	}
	
	public void use(Player player) {
		if(!pCooldowns.containsKey(player)) {
		stats.storeMoveSpeed(player, player.getWalkSpeed());
		pCooldowns.put(player, COOLDOWN_AMOUNT);
		
		try {
			Simulate sim = new Simulate(player, ProjectileType.SMALL_FIREBALL);
			
			if(sim.getTarget() != null) {
				sim.getTarget().setHealth(sim.getTarget().getHealth()-3);
			}
			
			gameplayScheduler(NightFallRPG.getPlugin(), sim.getLocation(), player.getWorld());
			
		} catch (BadProjectile | ObjectsNotUsed | InvalidTarget e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		} else {
			player.sendMessage(ChatColor.DARK_RED + "Fireball has " + pCooldowns.get(player) + " seconds left!");
		}
	}
	
	public void updateCooldowns() {
		for(Map.Entry<Player, Integer> entry : pCooldowns.entrySet()) {
			
			System.out.println("Fireball: " + entry.getKey() + ": " + entry.getValue() + " seconds -> " + (entry.getValue()-1) + " seconds.");
			//pCooldowns.remove(entry.getKey());
			pCooldowns.replace(entry.getKey(), entry.getValue()-1);
			//pCooldowns.put(entry.getKey(), entry.getValue()-1);
			if(entry.getValue()-1 < 0) {
				pCooldowns.remove(entry.getKey());
			}
			
		    /*
			if(entry.getValue() == COOLDOWN_AMOUNT-DURATION_AMOUNT) {
				entry.getKey().setWalkSpeed(stats.getMoveSpeed(entry.getKey()));
			}
			*/
			
		}
	}
	
	public boolean Exists(Player player) {
		return pCooldowns.containsKey(player);
	}
	
	public int getCooldown(Player player) {
		if(pCooldowns.containsKey(player)) {
		return pCooldowns.get(player);
		}
		
		return 0;
	}
	
	public void setCooldown(Player player, int cooldown_amount) {
		if(pCooldowns.containsKey(player)) {
			pCooldowns.remove(player);
		}
		pCooldowns.put(player, cooldown_amount);
	}
}
