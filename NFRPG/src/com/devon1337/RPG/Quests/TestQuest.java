package com.devon1337.RPG.Quests;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import com.devon1337.RPG.Utils.Region;
import com.devon1337.RPG.Utils.Dialog.Requirement;

import lombok.Getter;
import lombok.Setter;

public class TestQuest extends Quest implements IQuest{

	
	@Getter
	static final String TITLE = "Push upon the tower";
	@Getter
	static final String DESCRIPTION = "DEV_TEST_1";
	@Getter @Setter
	static int XP_Amount = 100, Gold_Amount = 100;
	
	static ArrayList<EventFlags> flags = new ArrayList<EventFlags>();
	static QuestStatus status = QuestStatus.Available;
	static QuestTags tag = QuestTags.START_ZONE_SELECT_CLASS;

	public TestQuest() {
		super(TITLE, DESCRIPTION, XP_Amount, Gold_Amount, tag, flags, status);
		super.setSteps(initSteps());
		super.setQ((IQuest) this);
	}
	
	public ArrayList<Step> initSteps() {
		ArrayList<Step> steps = new ArrayList<Step>();
		steps.add(new Step("Run!", "You were losting trying to do something and got surrounded!", new Requirement(Region.getRegion("selectclass_pitfall"), true), EventFlags.EnterRegionEvent, StepStatus.Active, this));
		steps.add(new Step(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "read the books", ChatColor.DARK_RED + "" + ChatColor.MAGIC + "follow your destiny", new Requirement(true), EventFlags.Custom, StepStatus.Active, this));
		
		return steps;
	}

	@Override
	public Quest newQuest() {
		// TODO Auto-generated method stub
		return new TestQuest();
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