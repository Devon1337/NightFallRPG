package com.devon1337.RPG.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FriendsList {

	public static HashMap<UUID, ArrayList<UUID>> fList = new HashMap<UUID,ArrayList<UUID>>();
	
	private final static String FRIEND_ONLINE_MESSAGE = ChatColor.DARK_PURPLE + " has came online!", FRIEND_OFFLINE_MESSAGE = ChatColor.DARK_GRAY + " has went offline!";
	
	// TODO: Might error out because player dont exist but thats to be fixed
	public static void addFriend(Player player, Player target) {
		if(fList.containsKey(player.getUniqueId())) {
			player.sendMessage("You have added " + target.getName());
			
			fList.put(player.getUniqueId(), generateAppendArrayList(getFriends(player), target));
		} else {
			player.sendMessage("You have added " + target.getName());
			fList.put(player.getUniqueId(), generateNewArrayList(target.getUniqueId()));
		}
	}
	
	public static void removeFriend(Player player, Player target) {
		if(isFriend(player, target)) {
			if(fList.get(player.getUniqueId()).size() == 1) {
				fList.remove(player.getUniqueId());
			} else {
				fList.put(player.getUniqueId(), removeArrayList(getFriends(player), target));
			}
		}
	}
	
	public static ArrayList<UUID> removeArrayList(ArrayList<UUID> arg1, Player player) {
		ArrayList<UUID> players = arg1;
		players.remove(player.getUniqueId());
		return players;
	}
	
	public static ArrayList<UUID> generateNewArrayList(UUID player) {
		ArrayList<UUID> players = new ArrayList<UUID>();
		players.add(player);		
		return players;
	}
	
	public static ArrayList<UUID> generateAppendArrayList(ArrayList<UUID> arg1, Player player) {
		ArrayList<UUID> players = arg1;
		players.add(player.getUniqueId());		
		return players;
	}
	
	public static boolean isFriend(Player player, Player target) {
		if(fList.containsKey(player.getUniqueId()) && fList.get(player.getUniqueId()).contains(target.getUniqueId())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static ArrayList<UUID> getFriends(Player player) {
		if(fList.containsKey(player.getUniqueId())) {
			return fList.get(player.getUniqueId());
		} else {
			return null;
		}
	}
	
	public static void goOnline(Player player) {		
		for(int i = 0; i < fList.size(); i++) {
			UUID target = (UUID) fList.keySet().toArray()[i];
			if(fList.get(target).contains(player.getUniqueId())) {
				Bukkit.getPlayer(target).sendMessage(ChatColor.GRAY + player.getName() + FRIEND_ONLINE_MESSAGE);
			}
		}
	}
	
	public static void goOffline(Player player) {
		for(int i = 0; i < fList.size(); i++) {
			UUID target = (UUID) fList.keySet().toArray()[i];
			if(fList.get(target).contains(player.getUniqueId())) {
				Bukkit.getPlayer(target).sendMessage(ChatColor.GRAY + player.getName() + FRIEND_OFFLINE_MESSAGE);
				
			}
		}
	}
	
}
