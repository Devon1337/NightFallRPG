package com.devon1337.RPG.Utils.Dialog;

import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.EventFlags;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.Step;
import com.devon1337.RPG.Utils.TextHandler;
import com.devon1337.RPG.NightFallRPG;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class DialogueSystem {

	public static TextHandler texthandler = new TextHandler();
	public static ArrayList<Dialog> GLOBAL_DIALOG_LIST = new ArrayList<>();
	public static HashMap<UUID, Dialog> inDialog = new HashMap<UUID, Dialog>();
	public static int rightEdgeDistance = 0;

	@Deprecated
	public static ArrayList<Dialog> getNPCDialog(String code) {
		ArrayList<Dialog> temp = new ArrayList<>();

		for (int i = 0; i < GLOBAL_DIALOG_LIST.size(); i++) {
			if (((Dialog) GLOBAL_DIALOG_LIST.get(i)).getNpc().getName().equals(code)) {
				Logging.OutputToConsole("NPC: " + code + " Dialog: " + ((Dialog) GLOBAL_DIALOG_LIST.get(i)).code.toString());
				temp.add(GLOBAL_DIALOG_LIST.get(i));
			}
		}

		return temp;
	}

	// Preferred way of retrieving lines
	public static Dialog getDialogLine(DialogCodes code) {
		for(Dialog d : GLOBAL_DIALOG_LIST) {
			if(code.equals(d.code)) {
				return d;
			}
		}
		return null;
	}
	
	public static String generateSpaces() {
		String temp = "";
		for (int i = 0; i < rightEdgeDistance; i++) {
			temp = " " + temp;
		}
		return temp;
	}

	public static Response[] generateResponseArray(Response... r) {
		return r;
	}

	public static ArrayList<String> alignText(String message) {
		
		int RIGHT_WALL_DEFAULT = 70;
		ArrayList<String> messages = new ArrayList<>();
		int charCount = 0;
		String temp_msg = "";
		int adjusted = 0;

		for (int i = 0; i < message.length(); i++) {

			if (charCount >= 25 && message.charAt(i) == ' ') {
				adjusted = RIGHT_WALL_DEFAULT - charCount;
				rightEdgeDistance = adjusted;
				messages.add(String.valueOf(generateSpaces()) + temp_msg);
				temp_msg = "";
				charCount = 0;
			}

			charCount++;
			temp_msg = String.valueOf(temp_msg) + message.charAt(i);

			if (i + 1 == message.length()) {
				adjusted = RIGHT_WALL_DEFAULT - charCount;
				rightEdgeDistance = adjusted;
				messages.add(String.valueOf(generateSpaces()) + temp_msg);
			}
		}

		if (messages.size() == 0) {

			adjusted = RIGHT_WALL_DEFAULT - charCount;
			rightEdgeDistance = adjusted;
			messages.add(String.valueOf(generateSpaces()) + temp_msg);
			return messages;
		}

		return messages;
	}
	
	// Formats Strings
	public static String FormatString(String Message, NFPlayer player) {
		/* Format Codes
		 * %player% - Replace with player's name
		 * %gold% - Replace with player's gold count
		 * %class% - Replace with player's class
		 */
		String temp = Message;
		
		
		// Removes the players name
		if(Message.contains("%player%")) {
			return temp.replace("%player%", player.getPlayer().getName());
		}
		
		
		return temp;
	}

	// Runs the flags
	@SuppressWarnings("incomplete-switch")
	public static void RunFlags(NFPlayer player, Dialog dialog) {
		ArrayList<String> msg;
		String temp = null;
		boolean NoInput = false, NextLine = false;

		for (DialogFlags flag : dialog.getFlags()) {
			switch (flag) {
			case FORCE_LEFT:
				temp = dialog.getMessage();
				break;
			case NOINPUT:
				NoInput = true;
				break;
			case NEXT_LINE:
				NextLine = true;
				NoInput = true;
				break;
			}
		}
		
		// Runs Quest Logic 
		for(Quest q : player.getQuests()) {
			if(q.getFlags().contains(EventFlags.Dialog)) {
				for(Step s : q.getSteps()) {
					if(s.getFlag().equals(EventFlags.Dialog)) {
						s.checkStatus(player);
					}
				}
			}
		}

		// Prints message depending on force-left or default-right
		if (temp != null) {
			player.getPlayer().sendMessage(temp);
		} else {
			msg = alignText(FormatString(dialog.getMessage(), player));

			player.getPlayer().sendMessage(" ");

			// Prints Message
			for (String s : msg) {
				player.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + s);
			}

		}

		// Runs Response
		if (!NoInput) {
			printResponse(player, dialog);
		}

		// Displays next auto dialog
		if (NextLine) {
			Logging.OutputToConsole("Printing automatic message");
			newDialog(player, dialog.getNextLine());
		}
		
		removeFromDialog(player.getPlayer());

	}

	// Prints the Dialog
	public static void DisplayDialog(NFPlayer player, Dialog dialog) {
		Player pl = player.getPlayer();
		
		if(!hasDialog(pl)) {
			
			player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1.0F, 1.0F); // Plays notification sound
			
			// Displays name
			pl.sendMessage(ChatColor.DARK_PURPLE + alignText(dialog.npc.getName()).get(0));
			
			inDialog.put(player.getPlayer().getUniqueId(), dialog); // Marks the player for dialog
			
			// Displays message
			RunFlags(player, dialog);
			
		}
	}

	// Prints the response options given to the player
	public static void printResponse(NFPlayer player, Dialog dialog) {
		String cmd = null, msg = null;
		int i = 0;
		if (dialog.getResponse() != null) {
			for (Response r : dialog.getResponse()) {

				// Formats the Strings
				msg = FormatString(r.message, player);
				cmd = "/runresponse " + player.getPlayer().getName() + " " + dialog.code.toString() + " " + i;

				texthandler.sendNewClickEvent(player.getPlayer(), cmd, "[" + msg + "]");

				cmd = null;
				msg = null;
				i++;
			}
		}
	}

	// Prints next auto dialog
	public static void newDialog(final NFPlayer player, Dialog dialog) {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		
		scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
			public void run() {
				dialog.getNpc().setActiveDialog(player.getPlayer(), dialog);
				DialogueSystem.DisplayDialog(player, dialog);
			}
		}, 100L);
	}

	public static void removeFromDialog(final Player player) {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

		scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
			public void run() {
				if (DialogueSystem.inDialog.containsKey(player.getUniqueId())) {
					DialogueSystem.inDialog.remove(player.getUniqueId());
				}
			}
		},

				100L);
	}

	public static void forceRemoveDialog(Player player) {
		inDialog.remove(player.getUniqueId());
	}

	public static boolean hasDialog(Player player) {
		return inDialog.containsKey(player.getUniqueId());
	}
	
	public static Dialog getLine(Player player) {
		if(inDialog.containsKey(player.getUniqueId())) {
			return inDialog.get(player.getUniqueId());
		}
		return null;
	}

	public static void removeDialog(Player player) {
		inDialog.remove(player.getUniqueId());
	}

	public static Dialog getDialog(int index) {
		if (index <= GLOBAL_DIALOG_LIST.size()) {
			return GLOBAL_DIALOG_LIST.get(index);
		}
		return null;
	}

	public static ArrayList<Dialog> getDialogs() {
		return GLOBAL_DIALOG_LIST;
	}
}