package com.devon1337.RPG.WeaponEffects.Effects.Type;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.WeaponEffects.IWeaponType;
import com.devon1337.RPG.WeaponEffects.Effects.WType;

public class Frozen extends WType implements IWeaponType {

	static String name = "Frozen";
	static float adjustSpeed = 1.0f;
	public Frozen() {
		super(name);
	}
	
	public void use(Player player, ArrayList<Player> targets, float Amount) {
		for(Player p : targets) {
			NFPlayer.getPlayer(p.getUniqueId()).setSpeed(NFPlayer.getPlayer(p.getUniqueId()).getSpeed()-Amount);
			
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	        scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
	            @Override
	            public void run() {
	            	NFPlayer.getPlayer(p.getUniqueId()).setSpeed(NFPlayer.getPlayer(p.getUniqueId()).getSpeed()+Amount);
	            }
	        }, 20*5L);
			
		}
	}
	
	public IWeaponType getIWeaponType() {
		return (IWeaponType) this;
	}
	
}
