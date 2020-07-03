package com.devon1337.RPG.Utils;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.NPC.WorldController;

public class DialogueSystem {

	public static TextHandler texthandler = new TextHandler();
	public static ArrayList<Dialog> GLOBAL_DIALOG_LIST = new ArrayList<Dialog>();
	public static ArrayList<UUID> inDialog = new ArrayList<UUID>();
	public static int rightEdgeDistance = 0;

	public static void init_dialog() {

		// Output: "I just cant find the city" Responses: "Who knows", "I know where it is"
		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_QUEST_1_1", "I just cant find the city", generateResponseArray("Who knows", "/nfdialog 8 input Who_knows", "", "", "I know where it is", "/nfquest 2", "faction_rogues", "400"), 0, "ROGUE_EXIT_1"));
		// Output: "Did you see anybody outside?" Responses: "Yes", "No..."
		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_QUEST_1_2", "Did you see anybody outside?", generateResponseArray("Yes", "/nfquest confirm {player} 2","","", "No...", "/nfprint No...", "", ""), 1, "ROGUE_ENTRANCE_1"));
		// Output: "Wake up!" Response: "What do you want?"
		GLOBAL_DIALOG_LIST.add(new Dialog("SZ_CLASS_SELECT_1_1", "Wake up!", generateResponseArray("What do you want?", "/nfdialog 3 input What_do_you_want?", "", ""), 2, "SZ_CLASS_SELECT"));
		// Output: "You need to remember who you were..." Response: "I think I was ...", "I dont remember what I was"
		GLOBAL_DIALOG_LIST.add(new Dialog("SZ_CLASS_SELECT_1_2", "You need to remember who you were...", generateResponseArray("I think I was ...", "/class", "", "", "I dont remember what I was", "/nfprint I_dont_remember_what_I_was", "", ""), 3, "SZ_CLASS_SELECT"));
		// Output: "So are you going to take the jump or are you going to find another city to live in?" Response: "Watch me..."
		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_CITY_1_1", "So are you going to take the jump or are you going to find another city to live in?", generateResponseArray("Watch me...", "/nfprint Watch me...", "", ""), 4, "ROGUE_ENTRANCE1"));
		// Output: "Come see my magical crates!" Response: "Will do!"
		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_CRATES_1_1", "Come see my magical crates!", generateResponseArray("Will do!", "/nfprint Will_do!", "", ""), 5, "ROGUE_CRATES1"));
		// Output: "Welcome" Response: "Travel", "Simulate Random Dialog", "Display Reputations"
		GLOBAL_DIALOG_LIST.add(new Dialog("GM_AREA_1_1", "Welcome", generateResponseArray("Travel", "/nfdialog 7 input Travel", "", "", "Display Reputations", "/nftest", "", ""), 6, "ROGUE_CRATES1"));
		// Output: "View Travel Points Below:" Response: "Zuru Ganaboru", "Warrior", "Stonefall Abyss", "Mage"
		GLOBAL_DIALOG_LIST.add(new Dialog("GM_AREA_1_2", "View Travel Points Below:", generateResponseArray("Zuru Ganaboru", "/warp Ganaboru", "", "", "Warrior", "/warp Warrior", "", "", "Stonefall Abyss", "","", "", "Mage", "", "", ""), 7, "ROGUE_CRATES1"));
		// Output: "You're the least bit useful!" Response: "..."
		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_QUEST_1_3", "You're the least bit useful!", generateResponseArray("...", "/nfprint ...", "", ""), 8, "ROGUE_EXIT_1"));
		
	}

	public static String generateSpaces() {
		String temp = "";
		for (int i = 0; i < rightEdgeDistance; i++) {
			temp = " " + temp;
		}
		return temp;

	}

	public static void setEdgeDistance(int distance) {
		rightEdgeDistance = distance;
		GLOBAL_DIALOG_LIST.clear();
		init_dialog();
	}

	public static String[] generateResponseArray(String... Responses) {
		String[] tx = new String[Responses.length];
		for (int i = 0; i < Responses.length; i++) {
			tx[i] = Responses[i];
		}
		return tx;
	}
	
	public static ArrayList<String> createFormat(String message) {
		/*
		 * 						Name |
		 *            This is a test |
		 *         message that I am |
		 *          		 typing. |
		 *          
		 *  Devon1337
		 *  [Response]
		 *  [Response]
		 * 
		 */
		
		final int RIGHT_WALL_DEFAULT = 70;
		ArrayList<String> messages = new ArrayList<String>();
		int charCount = 0;
		String temp_msg = "";
		int adjusted = 0;
		for(int i = 0; i < message.length(); i++) {
			if(charCount == 25) {
				Logging.OutputToConsole(temp_msg);
				adjusted = RIGHT_WALL_DEFAULT-charCount;
				setEdgeDistance(adjusted);
				messages.add(generateSpaces() + temp_msg);
				temp_msg = "";
				charCount = 0;
			}
			
			charCount++;
			temp_msg = temp_msg + message.charAt(i);
			
			if(i+1==message.length()) {
				adjusted = RIGHT_WALL_DEFAULT-charCount;
				setEdgeDistance(adjusted);
				messages.add(generateSpaces() + temp_msg);
			}
			
		}
		if(messages.size() == 0) {
			
			adjusted = RIGHT_WALL_DEFAULT-charCount;
			setEdgeDistance(adjusted);
			messages.add(generateSpaces() + temp_msg);
			return messages;
		}
		
		return messages;
		
	}

	public static void DisplayDialog(Player player, Dialog dialog) {
		//PlayerStats pstats = new PlayerStats();
		
		if(!hasDialog(player)) {
		player.sendMessage("");
		player.sendMessage("");
		player.sendMessage("");
		player.sendMessage("");
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1);
		inDialog.add(player.getUniqueId());
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		player.sendMessage(ChatColor.DARK_GRAY + "                   You and Chat are frozen!");
		player.sendMessage(ChatColor.DARK_GRAY + "" + createFormat(dialog.NPCName).get(0));
		for(int i = 0; i < createFormat(dialog.Message).size(); i++) {
			player.sendMessage(ChatColor.LIGHT_PURPLE + "" + createFormat(dialog.Message).get(i));
		}
		
		
		
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_GRAY + "" + player.getName());
		//pstats.storeMoveSpeed(player, player.getWalkSpeed());
		//player.setWalkSpeed(0);
			
			for(int i = 0; i < dialog.Responses.length-1; i+=4) {
				if(dialog.Responses[i].contains("{player}")) {
					dialog.Responses[i].replace("{player}", player.getName());
				}
				
				if(dialog.Responses[i+1].contains("{player}")) {
					dialog.Responses[i+1].replace("{player}", player.getName());
				}
				
				if(!dialog.Responses[i+2].equalsIgnoreCase("") && !dialog.Responses[i+3].equalsIgnoreCase("") && Integer.parseInt(dialog.Responses[i+3]) < WorldController.getFaction(dialog.Responses[i+2]).getReputation(player)) {
				texthandler.sendNewClickEvent(player, dialog.Responses[i+1], "[" + dialog.Responses[i] + "]");
				} else if(dialog.Responses[i+2].equalsIgnoreCase("") && dialog.Responses[i+3].equalsIgnoreCase("")) {
					texthandler.sendNewClickEvent(player, dialog.Responses[i+1], "[" + dialog.Responses[i] + "]");
				}
			}


		scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (inDialog.contains(player.getUniqueId())) {
					inDialog.remove(player.getUniqueId());
					//player.setWalkSpeed(pstats.getMoveSpeed(player));
				}
			}
		}, 20 * 5L);
		}

	}

	public static boolean hasDialog(Player player) {
		return inDialog.contains(player.getUniqueId());
	}
	
	public static void removeDialog(Player player) {
		inDialog.remove(player.getUniqueId());
	}
	
	public static Dialog getDialog(int index) {
		if (index <= GLOBAL_DIALOG_LIST.size()) {
			return GLOBAL_DIALOG_LIST.get(index);
		} else {
			return null;
		}
	}
}
