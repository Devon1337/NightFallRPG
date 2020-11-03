package com.devon1337.RPG.WeaponEffects.Effects.Type;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.WeaponEffects.Effects.WType;

public class Frozen extends WType {

	static String name = "Frozen";
	static float adjustSpeed = 1.0f;
	public Frozen(int id) {
		super(name, id);
	}
	
	public static void use(Player player, ArrayList<Player> targets) {
		for(Player p : targets) {
			NFPlayer.getPlayer(p.getUniqueId()).setSpeed(NFPlayer.getPlayer(p.getUniqueId()).getSpeed()-adjustSpeed);
			
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	        scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
	            @Override
	            public void run() {
	            	NFPlayer.getPlayer(p.getUniqueId()).setSpeed(NFPlayer.getPlayer(p.getUniqueId()).getSpeed()+adjustSpeed);
	            }
	        }, 20*5L);
			
		}
	}
	
}
