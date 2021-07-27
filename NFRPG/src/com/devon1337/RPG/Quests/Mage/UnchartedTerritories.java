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

public class UnchartedTerritories extends Quest {
	
	@Getter
	static final String TITLE = "Uncharted Territories";
	@Getter
	static final String DESCRIPTION = "-NO DESCRIPTION-";
	@Getter @Setter
	static int XP_Amount = 100, Gold_Amount = 100;
	
	static ArrayList<EventFlags> flags = new ArrayList<EventFlags>();
	static QuestStatus status = QuestStatus.Available;
	static QuestTags tag = QuestTags.UNCHARTED_TERRITORIES;

	public UnchartedTerritories() {
		super(TITLE, DESCRIPTION, XP_Amount, Gold_Amount, tag, flags, status);
		super.setSteps(initSteps());
		super.setFaction(WorldController.getFaction(AllFactions.Mage));
	}
	
	public ArrayList<Step> initSteps() {
		ArrayList<Step> steps = new ArrayList<Step>();
		//steps.add(new Step("Find and speak with Tandanis Startrick in the Library.", "", ));
		//steps.add(new Step("Talk to Landravedon Leafcloud instead in the Green Room.", "", false, StepStatus.Active, EventFlags.Custom, this));
		return steps;
	}

}
