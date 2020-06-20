package com.devon1337.RPG.Utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.devon1337.RPG.NightFallRPG;

public class DialogueSystem {

	public static TextHandler texthandler = new TextHandler();
	public static ArrayList<Dialog> GLOBAL_DIALOG_LIST = new ArrayList<Dialog>();
	public static ArrayList<Player> inDialog = new ArrayList<Player>();
	
	public static void init_dialog() {

		// Output: "I just cant find the city" Responses: "Who knows", "I know where it is"
		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_QUEST_1_1", "I just cant find the city", generateResponseArray("Who knows", "", "I know where it is", "/nfquest 2"), 0, "ROGUE_EXIT_1"));
		// Output: "Did you see anybody outside?" Responses: "Yes", "No..."
		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_QUEST_1_2", "Did you see anybody outside?", generateResponseArray("Yes", "/nfquest confirm {player} 2","No...", ""), 1, "ROGUE_ENTRANCE_1"));
		// Output: "Hey You..." Response: "..."
		// Output: "Wake up!" Response: "What do you want?"
		GLOBAL_DIALOG_LIST.add(new Dialog("SZ_CLASS_SELECT_1_1", "Wake up!", generateResponseArray("What do you want?", "/nfdialog 3"), 2, "SZ_CLASS_SELECT"));
		// Output: "You need to remember who you were..." Response: "I think I was ...", "I dont remember what I was"
		GLOBAL_DIALOG_LIST.add(new Dialog("SZ_CLASS_SELECT_1_2", "You need to remember who you were...", generateResponseArray("I think I was ...", "/class", "I dont remember what I was", ""), 3, "SZ_CLASS_SELECT"));
		// Output: "So are you going to take the jump or are you going to find another city to live in?"  Response: "Watch me..."
		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_CITY_1_1", "So are you going to take the jump or are you going to find another city to live in?", generateResponseArray("Watch me...", ""), 4, "ROGUE_ENTRANCE1"));
		// Output: "Come see my magical crates!" Response: "Will do!"
		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_CRATES_1_1", "Come see my magical crates!", generateResponseArray("Will do!", ""), 5, "ROGUE_CRATES1"));
	}
	
	public static String[] generateResponseArray(String... Responses) {
		String[] tx = new String[Responses.length];
		for(int i = 0; i < Responses.length; i++) {
			tx[i] = Responses[i];
		}	
		return tx;
	}
	
	public static void DisplayDialog(Player player, Dialog dialog) {
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1);
		inDialog.add(player);
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		player.sendMessage(dialog.Message);
		player.sendMessage("");
		if(dialog.ID == 1) {
			if(dialog.Responses.length >= 2) {
			texthandler.sendNewClickEvent(player, dialog.Responses[1], "[" + dialog.Responses[0] + "]");
			}
			if(dialog.Responses.length >= 4) {
			texthandler.sendNewClickEvent(player, dialog.Responses[3].replace("{player}", player.getName()), "[" + dialog.Responses[2] + "]");
			}
		} else {
			if(dialog.Responses.length >= 2) {
			texthandler.sendNewClickEvent(player, dialog.Responses[1], "[" + dialog.Responses[0] + "]");
			}
			if(dialog.Responses.length >= 4) {
			texthandler.sendNewClickEvent(player, dialog.Responses[3], "[" + dialog.Responses[2] + "]");
			}
		}
		
		scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if(inDialog.contains(player)) {
					inDialog.remove(player);
				}
			}
		}, 20*15L);
		
	}
	
	public static boolean hasDialog(Player player) {
		return inDialog.contains(player);
	}
	
	public static Dialog getDialog(int index) {
		if(index <= GLOBAL_DIALOG_LIST.size()) {
			return GLOBAL_DIALOG_LIST.get(index);
		} else {
			return null;
		}
	}
}
