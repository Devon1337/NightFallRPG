package com.devon1337.RPG.Quests;

public class TestQuest {

	public final static String TITLE = "First Quest", DESCRIPTION = "DEV_TEST_1", CODE = "DEV_TEST_1";
	public final static String[] RESPONSES = {"Test A", "Test B", "Test C", "Test D"};
	public final static int QUESTID = 1, XP_Amount = 100, Gold_Amount = 100;
	
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
	
	
}
