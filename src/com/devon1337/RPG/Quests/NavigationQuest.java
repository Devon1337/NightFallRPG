package com.devon1337.RPG.Quests;

import org.bukkit.entity.Player;

import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;

public class NavigationQuest extends Quest {

	

	public final static String TITLE = "The Dark Pit", DESCRIPTION = "Find and Locate the stonefall abyss!", CODE = "DEV_TEST_2";
	public final static String[] STEPS = {"Find the entrance to Stonefall Abyss", "Talk to ROGUE_WELCOME_1"};
	public final static int QUESTID = 2, XP_Amount = 100, Gold_Amount = 100;
	
	public NavigationQuest(int XP_Amount, int Gold_Amount, Player player, int Status, int StepAmount) throws QuestIDInUse {
		super(QUESTID, TITLE, DESCRIPTION, CODE, STEPS, XP_Amount, Gold_Amount, player, Status, StepAmount);
		// TODO Auto-generated constructor stub
	}
	
	public String getTitle() {
		return TITLE;
	}
	
	public static String getDescription() {
		return DESCRIPTION;
	}
	
	public String getCode() {
		return CODE;
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
	
	public Quest getQuest() {	
		return this;
	}
	
}
