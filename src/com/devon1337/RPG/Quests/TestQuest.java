package com.devon1337.RPG.Quests;

import org.bukkit.entity.Player;

import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;

public class TestQuest extends Quest {

	public TestQuest(int QuestID, String Title, String Description, String Code, String[] Responses, int XP_Amount,
			int Gold_Amount, Player player, int Status, int StepAmount) throws QuestIDInUse {
		super(QuestID, Title, Description, Code, Responses, XP_Amount, Gold_Amount, player, Status, StepAmount);
		// TODO Auto-generated constructor stub
	}

	public final static String TITLE = "First Quest", DESCRIPTION = "DEV_TEST_1", CODE = "DEV_TEST_1";
	public final static String[] RESPONSES = {"Test A", "Test B", "Test C", "Test D"};
	public final static int QUESTID = 1, XP_Amount = 100, Gold_Amount = 100;
	
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
	
	
}
