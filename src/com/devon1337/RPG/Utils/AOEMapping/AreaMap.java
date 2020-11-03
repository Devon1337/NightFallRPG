package com.devon1337.RPG.Utils.AOEMapping;

import com.devon1337.RPG.Utils.PartySystem;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class AreaMap {
	
	private ArrayList<Player> localPlayers = new ArrayList<Player>();

	public AreaMap(World world, Location point, int MaxDist) {
		for (Player player : world.getPlayers()) {
			if(getDistance(player.getLocation().getBlockX(), player.getLocation().getBlockY(), 
					player.getLocation().getBlockZ(), point.getBlockX(), point.getBlockY(), point.getBlockZ()) < MaxDist) {
				localPlayers.add(player);		
			}
		}		
	}
	
	public AreaMap(World world, Location point, int MaxDist, int MinDist) {
		for (Player player : world.getPlayers()) {
			if(getDistance(player.getLocation().getBlockX(), player.getLocation().getBlockY(), 
					player.getLocation().getBlockZ(), point.getBlockX(), point.getBlockY(), point.getBlockZ()) < MaxDist && getDistance(player.getLocation().getBlockX(), player.getLocation().getBlockY(), 
							player.getLocation().getBlockZ(), point.getBlockX(), point.getBlockY(), point.getBlockZ()) > MaxDist) {
				localPlayers.add(player);		
			}
		}		
	}
	
	public ArrayList<Player> getPlayers() {
		return this.localPlayers;
	}
	
	public ArrayList<Player> getPartyPlayers(Player sender) {
		ArrayList<Player> temp = new ArrayList<Player>();
		for(Player p : localPlayers) {
		if(PartySystem.getParty(PartySystem.getId(sender)) == p && p != sender) {
			temp.add(p);
		}
		}
		
		return temp;
	}
	
	public ArrayList<Player> getHostilePlayers(Player sender) {
		ArrayList<Player> temp = new ArrayList<Player>();
		for(Player p : localPlayers) {
		if(PartySystem.getParty(PartySystem.getId(sender)) != p && p != sender) {
			temp.add(p);
		}
		}
		
		return temp;
	}

	public int getDistance(int x1, int y1, int z1, int x2, int y2, int z2) {
		return getDifference(x1, x2) + getDifference(y1, y2) + getDifference(z1, z2);
	}

	public int getDifference(int val1, int val2) {
		return Math.abs(Math.abs(val1) - Math.abs(val2));
	}
}