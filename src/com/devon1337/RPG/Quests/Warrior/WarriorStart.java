package com.devon1337.RPG.Quests.Warrior;

import java.util.ArrayList;

import com.devon1337.RPG.Quests.EventFlags;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.QuestStatus;
import com.devon1337.RPG.Quests.Step;

import lombok.Getter;
import lombok.Setter;

public class WarriorStart extends Quest {

	
	@Getter
	static final String TITLE = "Wake Up";
	@Getter
	static final String DESCRIPTION = "You passed out while working and missed your group to travel to Ganaboru.";
	@Getter @Setter
	static int XP_Amount = 100, Gold_Amount = 100;
	
	static ArrayList<Step> steps = new ArrayList<Step>();
	static ArrayList<EventFlags> flags = new ArrayList<EventFlags>();
	static QuestStatus status = QuestStatus.Incomplete;

	public WarriorStart() {
		super(TITLE, DESCRIPTION, XP_Amount, Gold_Amount, steps, flags, status);
	}
	
	public static void addStep(Step s) {
		steps.add(s);
	}

}
