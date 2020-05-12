package com.devon1337.RPG.Utils.AOEMapping;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class AreaMap {

	//public static ArrayList<Player> players;
	
	public AreaMap(World world, Location point, int damageAmount, String message) {
		for(@SuppressWarnings("unused") Player player : world.getPlayers()) {

		}
	}
	
	public AreaMap(World world, Location point, int damageAmount, String message, Player sender) {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			//Bukkit.getPlayer("Devon1337").sendMessage("Location difference: " + player.getName() + ": " + getDistance(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), point.getBlockX(),point.getBlockY(), point.getBlockZ()));
			if(player != sender && getDistance(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), point.getBlockX(),point.getBlockY(), point.getBlockZ()) < 10) {
				sender.sendMessage("You have hit " + player.getName() + "!");
				if((sender.getHealth() + damageAmount) <= 20) {
					sender.setHealth(sender.getHealth()+damageAmount);
				} else {
					double absorpHeart = (20-sender.getHealth()) + damageAmount;
					//sender.sendMessage("Damage Amount: " + damageAmount + " absorpHeart: " + absorpHeart + " Expected: " + (damageAmount-absorpHeart));
					sender.setHealth(sender.getHealth() + (absorpHeart-damageAmount));
					sender.setAbsorptionAmount(absorpHeart);
				}
				
				//sender.setAbsorptionAmount(arg0);
				player.setHealth(player.getHealth()-damageAmount);
				player.sendMessage(message);
			}
		}
	}
	
	public int getDistance(int x1, int y1, int z1, int x2, int y2, int z2) {
		return getDifference(x1,x2) + getDifference(y1,y2) + getDifference(z1,z2);
	}
	
	public int getDifference(int val1, int val2) {
		//Bukkit.getPlayer("Devon1337").sendMessage("Difference " + (Math.abs(val1)-Math.abs(val2)));
		return Math.abs(Math.abs(val1)-Math.abs(val2));
	}
	
	
	
}
