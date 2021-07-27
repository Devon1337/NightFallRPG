package com.devon1337.RPG.Quests.Warrior;

import java.util.ArrayList;

import com.devon1337.RPG.NPC.AllFactions;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Quests.EventFlags;
import com.devon1337.RPG.Quests.IQuest;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.QuestStatus;
import com.devon1337.RPG.Quests.QuestTags;
import com.devon1337.RPG.Quests.Step;
import com.devon1337.RPG.Quests.StepStatus;
import com.devon1337.RPG.Utils.Dialog.DialogCodes;
import com.devon1337.RPG.Utils.Dialog.DialogueSystem;
import com.devon1337.RPG.Utils.Dialog.Requirement;

import lombok.Getter;
import lombok.Setter;

public class WarriorStart extends Quest implements IQuest {

	
	@Getter
	static final String TITLE = "Wake Up";
	@Getter
	static final String DESCRIPTION = "You passed out while working and missed your group to travel to Ganaboru.";
	@Getter @Setter
	static int XP_Amount = 100, Gold_Amount = 100;
	
	static ArrayList<EventFlags> flags = new ArrayList<EventFlags>();
	static QuestStatus status = QuestStatus.Available;
	static QuestTags tag = QuestTags.WARRIOR_START;

	public WarriorStart() {
		super(TITLE, DESCRIPTION, XP_Amount, Gold_Amount, tag, flags, status);
		super.setSteps(initSteps());
		super.setFaction(WorldController.getFaction(AllFactions.Warrior));
		super.setQ(this);
	}
	
	public ArrayList<Step> initSteps() {
		ArrayList<Step> steps = new ArrayList<Step>();
		steps.add(new Step("Wake up!", "You passsed out while working", new Requirement(DialogueSystem.getDialogLine(DialogCodes.WARRIOR_QUEST_1_1), true), EventFlags.Dialog, StepStatus.Active, this));
		//steps.add(new Step("Speak with Bastian Blackwood.", "You may still be able to travel to Ganaboru", true, StepStatus.Inactive, EventFlags.LocationEvent, this));
		//steps.add(new Step("A long haul", "Travel to ganaboru after learning where it is located at!", true, StepStatus.Inactive, EventFlags.LocationEvent, this));
		return steps;
	}

	@Override
	public Quest newQuest() {
		return new WarriorStart();
	}

	@Override
	public void onFailed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompleted() {
		// TODO Auto-generated method stub
		
	}

}
