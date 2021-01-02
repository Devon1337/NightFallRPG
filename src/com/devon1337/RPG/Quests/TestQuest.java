package com.devon1337.RPG.Quests;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class TestQuest extends Quest {

	
	@Getter
	static final String TITLE = "First Quest";
	@Getter
	static final String DESCRIPTION = "DEV_TEST_1";
	@Getter @Setter
	static int XP_Amount = 100, Gold_Amount = 100;
	
	static ArrayList<Step> steps = new ArrayList<Step>();
	static ArrayList<EventFlags> flags = new ArrayList<EventFlags>();
	static QuestStatus status = QuestStatus.Incomplete;

	public TestQuest() {
		super(TITLE, DESCRIPTION, XP_Amount, Gold_Amount, steps, flags, status);
	}
	
	public static void addStep(Step s) {
		steps.add(s);
	}

}