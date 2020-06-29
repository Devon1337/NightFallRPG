package com.devon1337.RPG.Quests;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Commands.NFQuest;
import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;

public abstract class Quest {

	/* Status Codes
	 * 0 - Available
	 * 1 - Active
	 * 2 - Completed
	 * 3 - Failed
	 */
	
	public Player player;
	public int Status, QuestID, XP_Amount, Gold_Amount, CurSteps = 0, StepAmount = 5;
	public String Title, Description, Code;
	public String[] Steps;
	
	public Quest(int QuestID, String Title, String Description, String Code, String[] Steps, int XP_Amount, int Gold_Amount, Player player, int Status, int StepAmount) throws QuestIDInUse {
		this.QuestID = QuestID;
		this.Title = Title;
		this.Description = Description;
		this.Code = Code;
		this.Steps = Steps;
		this.XP_Amount = XP_Amount;
		this.Gold_Amount = Gold_Amount;
		this.player = player;
		QuestTracker.initQuest(this);
	}
	
	public Quest(Quest quest, Player player, int Status, int StepAmount) {
		this.QuestID = quest.QuestID;
		Title = quest.Title;
		Description = quest.Description;
		Code = quest.Code;
		this.Steps = quest.Steps;
		this.XP_Amount = quest.XP_Amount;
		this.Gold_Amount = quest.Gold_Amount;
		this.player = player;
		this.Status = Status;
		this.StepAmount = StepAmount;
	}
	
	public String getStep(int index) {
			if(index < Steps.length) {
				return Steps[index];
			}
			
			return "";
	}
	
	public String getCode() {
		return Code;
	}
	
	public String getTitle() {
		return Title;
	}
	
	public int getStepAmount() {
		return StepAmount;
	}
	
	public int getCurSteps() {
		return CurSteps;
	}
	
	public void setCurSteps(int CurSteps) {
		this.CurSteps = CurSteps;
	}
	
	public Player getPlayer() {
		if(player != null) {
			return player;
		}
		return null;
	}
	
	public void setPlayer(Player player) {
		
	}
	
	public void setStatus(int Status) {
		
		if(Status == 2) {			
			player.sendMessage(ChatColor.LIGHT_PURPLE + "Quest Complete: " + ChatColor.GOLD + ChatColor.BOLD + Title);
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
			NFQuest.addPlayer(player);
		}
		
		this.Status = Status;
	}
	
	public int getStatus() {
		return Status;
	}

}
