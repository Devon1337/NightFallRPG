package com.devon1337.RPG.Utils.Dialog;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.devon1337.RPG.NPC.Faction;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.Step;
import com.devon1337.RPG.Quests.StepStatus;
import com.devon1337.RPG.Utils.Region;

import lombok.Getter;
import lombok.Setter;

public class Requirement {

	@Getter
	RequirementType type;
	
	@Getter
	EntityType mob;
	
	@Getter
	Faction faction;
	
	@Getter
	Quest quest;
	
	@Getter
	Step step;
	
	@Getter
	NPC npc;
	
	@Getter
	Dialog dialog;
	
	@Getter
	ItemStack item;
	
	@Getter
	Region region;
	
	@Getter
	int level, gold;
	
	@Getter
	long startTime, endTime;
	
	@Getter
	StepStatus sStatus;
	
	@Getter @Setter
	int amount, amountRequired;
	
	@Getter @Setter
	boolean required;
	
	// Faction Requirement
	public Requirement(Faction faction, int amount, boolean required) {
		type = RequirementType.Reputation;
		this.required = required;
	}
	
	// Quest Requirement
	public Requirement(Quest quest, boolean required) {
		this.quest = quest;
		type = RequirementType.Quest;
		this.required = required;
	}
	
	// Quest Step Requirement
	public Requirement(Step step, boolean required) {
		this.quest = step.getQuest();
		this.step = step;
		type = RequirementType.Step;
		this.required = required;
	}
	
	// Dialog Requirement
	public Requirement(Dialog dialog, boolean required) {
		type = RequirementType.Dialog;
		this.dialog = dialog;
		this.npc = dialog.npc;
		this.required = required;
	}
	
	// Item Requirement
	public Requirement(ItemStack item, boolean required) {
		type = RequirementType.Item;
		this.item = item;
		this.required = required;
	}	
	
	// Mob Requirement
	public Requirement(EntityType mob, int amount, int amountRequired, boolean required) {
		type = RequirementType.Mob;
		this.mob = mob;
		this.amount = amount;
		this.amountRequired = amountRequired;
		this.required = required;
	}
	
	// Gold & Level Requirement
	public Requirement(int index, RequirementType type, boolean required) {
		this.type = type;
		if(type==RequirementType.Gold) {
			this.gold = index;
		} else {
			this.level = index;
		}
		this.required = required;
	}
	
	// Time Requirement
	public Requirement(long startTime, long endTime, boolean required) { 
		type = RequirementType.Time;
		this.startTime = startTime;
		this.endTime = endTime;
		this.required = required;
	}
	
	// Location Requirement
	public Requirement(Region region, boolean required) {
		type = RequirementType.Location;
		this.region = region;
		this.required = required;
	}
	
	public Requirement(boolean required) {
		type = RequirementType.Custom;
		this.required = required;
	}
	
	public boolean checkRequirement(NFPlayer player) {
		switch(this.type) {
		case Gold:
			break;
		case Level:
			break;
		case Reputation:
			break;
		case Item:
			break;
		case Quest:
			return CheckQuest(player);
		case Step:
			return CheckSteps(player);
		case Time:
			return CheckTime(player);
		case Dialog:
			return CheckDialog(player);
		case NPC:
			break;
		case Location:
			return CheckLocation(player);
		case Mob:
			break; 
		case Custom:
			break;
		}
		
		return false;
	}
	
	// Checks if time requirement matches!
	public boolean CheckTime(NFPlayer player) {
		long currentTime = player.getPlayer().getWorld().getTime();
		if(currentTime > startTime && currentTime < endTime) {
			return true;
		}
		
		return false;
	}
	
	// Checks if the player has done a certain quest to a point
	public boolean CheckQuest(NFPlayer player) {
		for(Quest q : player.getQuests()) {
			if(q.getTag().equals(quest.getTag()) && q.getStatus().equals(quest.getStatus())) {
				return true;
			}
		}
		return false;
	}
	
	// Checks if the player has done a certain step to a point
	public boolean CheckSteps(NFPlayer player) {
		for (Quest q : player.getQuests()) {
			for (Step s : q.getSteps()) {
				if (s.equals(this.step)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean CheckLocation(NFPlayer player) {
		return player.getRegion().equals(this.region);
	}
	
	public boolean CheckDialog(NFPlayer player) {
		return DialogueSystem.getLine(player.getPlayer()).code.equals(dialog.code);
	}
}
