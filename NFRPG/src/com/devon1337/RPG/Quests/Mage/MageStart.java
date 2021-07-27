package com.devon1337.RPG.Quests.Mage;

import java.util.ArrayList;

import com.devon1337.RPG.NPC.AllFactions;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Quests.EventFlags;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.QuestStatus;
import com.devon1337.RPG.Quests.QuestTags;
import com.devon1337.RPG.Quests.Step;

import lombok.Getter;
import lombok.Setter;

public class MageStart extends Quest {
	
	@Getter
	static final String TITLE = "The Arrival";
	@Getter
	static final String DESCRIPTION = "-NO DESCRIPTION-";
	@Getter @Setter
	static int XP_Amount = 100, Gold_Amount = 100;
	
	static ArrayList<EventFlags> flags = new ArrayList<EventFlags>();
	static QuestStatus status = QuestStatus.Available;
	static QuestTags tag = QuestTags.MAGE_START;

	public MageStart() {
		super(TITLE, DESCRIPTION, XP_Amount, Gold_Amount, tag, flags, status);
		super.setSteps(initSteps());
		super.setFaction(WorldController.getFaction(AllFactions.Mage));
	}
	
	public ArrayList<Step> initSteps() {
		ArrayList<Step> steps = new ArrayList<Step>();
		return steps;
	}

}