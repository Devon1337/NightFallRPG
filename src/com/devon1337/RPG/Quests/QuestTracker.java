package com.devon1337.RPG.Quests;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Commands.NFQuest;
import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;

public class QuestTracker {

	public static HashMap<Player, ArrayList<Quest>> questList = new HashMap<Player, ArrayList<Quest>>();
	public static ArrayList<Quest> GLOBAL_QUEST_LIST = new ArrayList<Quest>();
	

	public void addQuest(Player player, Quest quest) {
		if (QuestListContains(GLOBAL_QUEST_LIST, quest.getCode())) { // Problematic line
			// Need to make the quest object be able to be presentable with and without the user.

			// Create the quest object
			// Assign to the player
			// pulls from GLOBAL_QUEST_LIST
			
			//questList.put(player, fixAL(getQuests(player), quest));
			
			
			if (getQuests(player) == null) {
				questList.put(player, fixAL(new ArrayList<Quest>(), quest));
			} else {
				questList.put(player, fixAL(getQuests(player), quest));
			}

			System.out.println("Player: " + player.getName() + " has accepted questID: " + quest.QuestID);
			player.sendMessage(ChatColor.LIGHT_PURPLE + "Quest acquired: " + ChatColor.GOLD + ChatColor.BOLD + quest.Title);
			player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			NFQuest.addPlayer(player);
		} else {
			try {
				initQuest(quest);
			} catch (QuestIDInUse e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static boolean QuestListContains(ArrayList<Quest> GQLCode, String CompareCode) {
		for(int i = 0; i < GQLCode.size(); i++) {
			if(GQLCode.get(i).getCode().equals(CompareCode)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static Quest grabQuest(int QuestID) {
		for (int i = 0; i < GLOBAL_QUEST_LIST.size(); i++) {
			if (GLOBAL_QUEST_LIST.get(i).QuestID == QuestID) {
				return GLOBAL_QUEST_LIST.get(i);
			}
		}
		return null;
	}
	
	public static boolean hasQuestID(int ID, Player player) {
		for(int i = 0; i < questList.get(player).size(); i++) {
			if(questList.get(player).get(i).QuestID == ID) {
				return true;
			}
		}
		return false;
	}
	
	public static Quest playersQuest(int ID, Player player) {
		for(int i = 0; i < questList.get(player).size(); i++) {
			if(questList.get(player).get(i).QuestID == ID) {
				return questList.get(player).get(i);
			}
		}
		return null;
	}

	public static boolean hasQuest(Player player, Quest quest) {
		if (questList.containsKey(player) && hasQuestID(quest.QuestID, player)) {
			return true;
		}
		return false;
	}

	public ArrayList<Quest> fixAL(ArrayList<Quest> arr, Quest val) {
		arr.add(val);
		return arr;
	}

	public static ArrayList<Quest> getQuests(Player player) {
		return questList.get(player);
	}

	public void initQuest(Quest quest) throws QuestIDInUse {

		if (GLOBAL_QUEST_LIST.contains(quest)) {
			throw new QuestIDInUse(
					"NFRPG Exception: The QuestID: " + quest.QuestID + " is already in use! please reassign the ID.");
		} else {
			System.out.println(quest.Title + " has been initialized!");
			GLOBAL_QUEST_LIST.add(quest);
		}
	}
	
	

}
