package com.devon1337.RPG.Quests;

import org.bukkit.Bukkit;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Events.QuestCompletedEvent;
import com.devon1337.RPG.Utils.Dialog.Requirement;

import lombok.Getter;
import lombok.Setter;

public class Step {

	@Getter @Setter
	String title, description;
	
	@Getter
	StepStatus status;
	
	@Getter @Setter
	EventFlags flag;
	
	@Getter
	Requirement req;
	
	@Getter
	Quest quest;
	
	// Basic Step
	public Step(String title, String description, Requirement req, EventFlags flag, StepStatus status, Quest quest) {
		this.title = title;
		this.description = description;
		this.req = req;
		this.status = status;
		this.quest = quest;
		this.flag = flag;
		quest.getFlags().add(flag);
		
	}
	
	// Completes task if requirement is true
	public void checkStatus(NFPlayer player) {
		if(req.checkRequirement(player)) {
			this.status = StepStatus.Completed;
			checkQuestCompletion();
		}
	}
	
	// Sets the status
	public void setStatus(StepStatus status) {
		this.status = status;
		checkQuestCompletion();
	}
	
	private void checkQuestCompletion() {
		
		int requiredCompleted = 0, requiredAmount = 0;
		for(Step s : this.quest.getSteps()) {	
			if(s.req.isRequired() && s.getStatus() == StepStatus.Completed) {
				requiredCompleted++;
				requiredAmount++;
			} else if(s.req.isRequired()) {
				requiredAmount++;
			}
		}
		
		if(requiredCompleted == requiredAmount) {
			Bukkit.getPluginManager().callEvent(new QuestCompletedEvent(quest.getQ(), quest.getPlayer()));
		}
	}
	
}
