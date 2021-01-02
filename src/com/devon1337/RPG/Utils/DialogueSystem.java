package com.devon1337.RPG.Utils;

import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NightFallRPG;
import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class DialogueSystem {

	public static TextHandler texthandler = new TextHandler();
	public static ArrayList<Dialog> GLOBAL_DIALOG_LIST = new ArrayList<>();
	public static ArrayList<UUID> inDialog = new ArrayList<>();
	public static int rightEdgeDistance = 0;

	public static void init_dialog() {
		/*
		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_QUEST_1_2", "You're the least bit useful!",
				generateResponseArray(new Response[] { new Response("/nfprint ...", "...", 0, null, null) }), 0,
				"ROGUE_EXIT1"));

		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_QUEST_1_1", "I just cant find the city",
				generateResponseArray(new Response[] {
						new Response("/nfud ROGUE_EXIT1 Who_knows %player 0", "Who knows", 0, null, getDialog(0)),
						new Response("/nfdialog 8 input I_know_where_it_is.", "I know where it is", 0, null, null) }),
				1, "ROGUE_EXIT1"));
		((Dialog) GLOBAL_DIALOG_LIST.get(1)).addFlag(DialogFlags.START);
		((Dialog) GLOBAL_DIALOG_LIST.get(1)).addFlag(DialogFlags.CONTINUE);

		GLOBAL_DIALOG_LIST.add(new Dialog("SZ_CLASS_SELECT_1_1", "Wake up!", null, 2, "SZ_CLASS_SELECT"));
		((Dialog) GLOBAL_DIALOG_LIST.get(2)).addFlag(DialogFlags.START);
		((Dialog) GLOBAL_DIALOG_LIST.get(2)).addFlag(DialogFlags.CONTINUE);

		GLOBAL_DIALOG_LIST.add(new Dialog("SZ_CLASS_SELECT_1_2", "You need to remember who you were...",
				generateResponseArray(new Response[] { new Response("/class", "I think I was...", 0, null, null),
						new Response(" ", "I dont remember what I was", 0, null, null) }),
				3, "SZ_CLASS_SELECT"));
		((Dialog) GLOBAL_DIALOG_LIST.get(2)).addFlag(DialogFlags.NOINPUT);
		((Dialog) GLOBAL_DIALOG_LIST.get(2)).proceedDLog = GLOBAL_DIALOG_LIST.get(3);

		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_CITY_1_1",
				"So are you going to take the jump or are you going to find another city to live in?",
				generateResponseArray(
						new Response[] { new Response("/nfprint Watch_me", "Watch me...", 0, null, null) }),
				4, "ROGUE_ENTRANCE1"));
		((Dialog) GLOBAL_DIALOG_LIST.get(4)).addFlag(DialogFlags.START);

		GLOBAL_DIALOG_LIST.add(new Dialog("ROGUE_QUEST_2_1", "I...I just can't jump, It's suicide!",
				generateResponseArray(new Response[] { new Response("/nfquest 4", "Why'd you come", 0, null, null),
						new Response("/nfprint Go_find_a_new_town!", "Go find a new town!", 300,
								WorldController.getFaction("faction_rogues"), null),
						new Response("/nfprint Follow_me", "Follow me", 0, null, null) }),
				5, "ROGUE_ENTRANCE2"));
		((Dialog) GLOBAL_DIALOG_LIST.get(5)).addFlag(DialogFlags.START);

		GLOBAL_DIALOG_LIST.add(new Dialog("MAGE_CITY_1_1",
				"The corruption is spreading too fast. I've seen what it does to people.  I think its better to end myself before the corruption can get me.",
				generateResponseArray(new Response[] { new Response("/nfprint ...", "...", 0, null, null),
						new Response("/nfprint You_monster...", "Good we can't have any weak links", 0, null, null),
						new Response("/nfprint Don't_worry_we'll_be_able_to_contain_it_before_it_gets_out_of_control",
								"Don't worry we'll be able to contain it before it gets out of control.", 0, null,
								null) }),
				6, "MAGE_CITIZEN2"));
		((Dialog) GLOBAL_DIALOG_LIST.get(6)).addFlag(DialogFlags.START);
		((Dialog) GLOBAL_DIALOG_LIST.get(6)).addFlag(DialogFlags.FORCE_RED);

		GLOBAL_DIALOG_LIST.add(new Dialog("BESSIE_QUEST_1_1",
				"Hey!  I was trying to find my magic shrinking stew so that I could shrink down and...", null, 6,
				"MOOSHROOM_NPC"));

		GLOBAL_DIALOG_LIST.add(new Dialog("BESSIE_QUEST_1_1",
				" hide under a small mushroom from a creeper and got stuck down here.  If you could find it for me, you'd be a dear.",
				generateResponseArray(
						new Response[] { new Response("/nfquest 5", "How are you talking?", 7, null, null),
								new Response("/nfprint I_don't_have_time_to_help_you.",
										"I don't have time to help you.", 0, null, null),
								new Response("/nfprint How_do_cow_talk?", "How do Cow talks", 0, null, null) }),
				7, "MOOSHROOM_NPC"));
		((Dialog) GLOBAL_DIALOG_LIST.get(7)).proceedDLog = GLOBAL_DIALOG_LIST.get(8);
		((Dialog) GLOBAL_DIALOG_LIST.get(7)).addFlag(DialogFlags.START);
		((Dialog) GLOBAL_DIALOG_LIST.get(7)).addFlag(DialogFlags.NOINPUT);
		 */
	}

	public static ArrayList<Dialog> getNPCDialog(String code) {
		ArrayList<Dialog> temp = new ArrayList<>();

		for (int i = 0; i < GLOBAL_DIALOG_LIST.size(); i++) {
			if (((Dialog) GLOBAL_DIALOG_LIST.get(i)).getNpc().getName().equals(code)
					&& ((Dialog) GLOBAL_DIALOG_LIST.get(i)).getFlags().contains(DialogFlags.START)) {
				Logging.OutputToConsole("NPC: " + code + " Dialog: " + ((Dialog) GLOBAL_DIALOG_LIST.get(i)).Code);
				temp.add(GLOBAL_DIALOG_LIST.get(i));
			}
		}

		return temp;
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

	public static ArrayList<String> createFormat(String message) {
		@SuppressWarnings("unused")
		int RIGHT_WALL_DEFAULT = 70;
		ArrayList<String> messages = new ArrayList<>();
		int charCount = 0;
		String temp_msg = "";
		int adjusted = 0;
		for (int i = 0; i < message.length(); i++) {

			if (charCount >= 25 && message.charAt(i) == ' ') {
				Logging.OutputToConsole(temp_msg);
				adjusted = 70 - charCount;
				rightEdgeDistance = adjusted;
				messages.add(String.valueOf(generateSpaces()) + temp_msg);
				temp_msg = "";
				charCount = 0;
			}

			charCount++;
			temp_msg = String.valueOf(temp_msg) + message.charAt(i);

			if (i + 1 == message.length()) {
				adjusted = 70 - charCount;
				rightEdgeDistance = adjusted;
				messages.add(String.valueOf(generateSpaces()) + temp_msg);
			}
		}

		if (messages.size() == 0) {

			adjusted = 70 - charCount;
			rightEdgeDistance = adjusted;
			messages.add(String.valueOf(generateSpaces()) + temp_msg);
			return messages;
		}

		return messages;
	}

	public static void DisplayDialog(Player player, Dialog dialog, NPC npc) {
		if (!hasDialog(player)) {
			player.sendMessage("");
			player.sendMessage("");
			player.sendMessage("");
			if (dialog.getFlags().contains(DialogFlags.START)) {
				player.sendMessage("You and Chat are frozen!");
			}

			if (dialog.getFlags().contains(DialogFlags.FORCE_LEFT)) {
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1.0F, 1.0F);
				inDialog.add(player.getUniqueId());

				player.sendMessage(ChatColor.DARK_GRAY + npc.getName());
				player.sendMessage(ChatColor.LIGHT_PURPLE + dialog.Message);
			} else {
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1.0F, 1.0F);
				inDialog.add(player.getUniqueId());

				player.sendMessage(ChatColor.DARK_GRAY + (String) createFormat(npc.getName()).get(0));

				for (int i = 0; i < createFormat(dialog.Message).size(); i++) {
					player.sendMessage(ChatColor.LIGHT_PURPLE + (String) createFormat(dialog.Message).get(i));
				}
			}

			if (dialog.getFlags().contains(DialogFlags.MESSAGE) || dialog.getFlags().contains(DialogFlags.NOINPUT)) {
				forceRemoveDialog(player);
			}

			if (!dialog.getFlags().contains(DialogFlags.MESSAGE) || !dialog.getFlags().contains(DialogFlags.NOINPUT)) {
				printResponse(player, dialog);
				removeFromDialog(player);
			}

			if (dialog.getFlags().contains(DialogFlags.NOINPUT)) {
				Logging.OutputToConsole("Ran");
				newDialog(player, npc);
			}
		}
	}

	public static void printResponse(Player player, Dialog dialog) {
		String tempCMD = null, msg = null;

		if (dialog.Responses != null) {
			player.sendMessage("");
			player.sendMessage(ChatColor.DARK_GRAY + player.getName());
			for (int i = 0; i < dialog.Responses.length; i++) {

				if (dialog.Responses[i].getMessage().contains("{player}")) {
					dialog.Responses[i].getMessage().replace("{player}", player.getName());
				}

				if (dialog.Responses[i].getCMD().contains("%player")) {
					tempCMD = dialog.Responses[i].getCMD().replace("%player", player.getName());
				}

				if (tempCMD == null) {
					tempCMD = dialog.Responses[i].getCMD();
				}

				if (dialog.getFlags().contains(DialogFlags.FORCE_RED)) {
					msg = ChatColor.DARK_RED + dialog.Responses[i].getMessage();
				}

				if (msg == null) {
					msg = dialog.Responses[i].getMessage();
				}

				texthandler.sendNewClickEvent(player, tempCMD, "[" + dialog.Responses[i].getMessage() + "]");

				tempCMD = null;
				msg = null;
			}
		}
	}

	public static void newDialog(final Player player, final NPC npc) {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

		scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
			public void run() {
				Logging.OutputToConsole((npc.getCurDialog(player)).Message);
				DialogueSystem.DisplayDialog(player, npc.getCurDialog(player).getNextDialog(), npc);
			}
		}, 100L);
	}

	public static void removeFromDialog(final Player player) {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

		scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
			public void run() {
				if (DialogueSystem.inDialog.contains(player.getUniqueId())) {
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
		return inDialog.contains(player.getUniqueId());
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