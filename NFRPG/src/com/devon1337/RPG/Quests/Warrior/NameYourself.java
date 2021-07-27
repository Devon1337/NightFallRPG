package com.devon1337.RPG.Quests.Warrior;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;

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

public class NameYourself extends Quest implements IQuest {

	
	@Getter
	static final String TITLE = "Make a Name for yourself";
	@Getter
	static final String DESCRIPTION = "You are trying to regain trust with Blackwood.";
	@Getter @Setter
	static int XP_Amount = 500, Gold_Amount = 100;
	
	static ArrayList<EventFlags> flags = new ArrayList<EventFlags>();
	static QuestStatus status = QuestStatus.Available;
	static QuestTags tag = QuestTags.NAME_YOURSELF;

	public NameYourself() {
		super(TITLE, DESCRIPTION, XP_Amount, Gold_Amount, tag, flags, status);
		super.setSteps(initSteps());
		super.setFaction(WorldController.getFaction(AllFactions.Warrior));
		super.setQ(this);
	}
	
	public ArrayList<Step> initSteps() {
		ArrayList<Step> steps = new ArrayList<Step>();
		steps.add(new Step("Chat with Holden", "You can find him near the outer gates!", new Requirement(DialogueSystem.getDialogLine(DialogCodes.WARRIOR_QUEST_2_1), false), EventFlags.Dialog, StepStatus.Active, this));
		//steps.add(new Step("Chat with Holt", "You can find him near the outer gates!", new Requirement(DialogueSystem.getDialogLine(DialogCodes.WARRIOR_QUEST_2_3), false), EventFlags.Dialog, StepStatus.Active, this));
		steps.add(new Step("Big bad spiders", "Kill 10 Spiders", new Requirement(EntityType.SPIDER, 0, 10, true), EventFlags.KillEvent, StepStatus.Inactive, this));
		steps.add(new Step("My pet spider", "Kill 10 Cave Spiders", new Requirement(EntityType.CAVE_SPIDER, 0, 10, true), EventFlags.KillEvent, StepStatus.Inactive, this));
		//steps.add(new Step("Cash in", "Turn in 5 Spider eyes", true, StepStatus.Inactive, EventFlags.Custom, this));
		return steps;
	}

	@Override
	public Quest newQuest() {
		return new NameYourself();
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
