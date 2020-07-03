package com.devon1337.RPG.Quests;

import org.bukkit.entity.Player;

import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;

public class PVPQuest extends Quest {

	public final int STEP_AMOUNT = 5;
	public final static String TITLE = "Clearing the Spider's Nest", DESCRIPTION = "Slay 5 players in PVP",
			CODE = "WARRIOR_QUEST_1";
	public final static String[] RESPONSES = { "Test A", "Test B", "Test C", "Test D" };
	public final static int QUESTID = 3, XP_Amount = 100, Gold_Amount = 100;

	public PVPQuest(int XP_Amount, int Gold_Amount, Player player, int Status, int StepAmount) throws QuestIDInUse {
		super(QUESTID, TITLE, DESCRIPTION, CODE, RESPONSES, XP_Amount, Gold_Amount, player, Status, StepAmount);
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
		switch (stepNumber) {
		case 0:
			player.sendMessage("You have killed 1/5 Players!");
			quest.setCurSteps(1);
			break;
		case 1:
			player.sendMessage("You have killed 2/5 Players!");
			quest.setStatus(2);
			break;
		case 2:
			player.sendMessage("You have killed 3/5 Players!");
			quest.setCurSteps(3);
			break;
		case 3:
			player.sendMessage("You have killed 4/5 Players!");
			quest.setCurSteps(4);
			break;
		case 4:
			player.sendMessage("You have killed 5/5 Players!");
			quest.setCurSteps(5);
			break;
		}
	}
}
