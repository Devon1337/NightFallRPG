package com.devon1337.RPG.Quests;

import org.bukkit.entity.Player;

public class NavigationQuest {

	public final static String TITLE = "The Dark Pit", DESCRIPTION = "Find and Locate the stonefall abyss!", CODE = "DEV_TEST_2";
	public final static String[] RESPONSES = {"Test A", "Test B", "Test C", "Test D"};
	public final static int QUESTID = 2, XP_Amount = 100, Gold_Amount = 100;
	
	public static String getTitle() {
		return TITLE;
	}
	
	public static String getDescription() {
		return DESCRIPTION;
	}
	
	public static String getCode() {
		return CODE;
	}
	
	public static String[] getResponses() {
		return RESPONSES;
	}
	
	public static int getQuestID() {
		return QUESTID;
	}
	
	public static int getXPAmount() {
		return XP_Amount;
	}
	
	public static int getGoldAmount() {
		return Gold_Amount;
	}
	
	public static void completeStep(int stepNumber, Player player, Quest quest) {
		switch(stepNumber) {
		case 0:
			player.sendMessage("Talk to ROGUE_WELCOME1");
			quest.setCurSteps(1);;
			break;
		case 1:
			player.sendMessage("You have completed the quest");
			quest.setStatus(2);
			break;
		}
	}
	
}
