package com.devon1337.RPG.Quests.Rogue;

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

public class RogueStart extends Quest {

	
	@Getter
	static final String TITLE = "Wake Up";
	@Getter
	static final String DESCRIPTION = "You had passed out at the pub after a night of drinking to reward yourself!";
	@Getter @Setter
	static int XP_Amount = 100, Gold_Amount = 100;
	
	static ArrayList<EventFlags> flags = new ArrayList<EventFlags>();
	static QuestStatus status = QuestStatus.Available;
	static QuestTags tag = QuestTags.ROGUE_START;

	public RogueStart() {
		super(TITLE, DESCRIPTION, XP_Amount, Gold_Amount, tag, flags, status);
		super.setSteps(initSteps());
		super.setFaction(WorldController.getFaction(AllFactions.Rogue));
	}
	
	public ArrayList<Step> initSteps() {
		ArrayList<Step> steps = new ArrayList<Step>();
		return steps;
	}

}
