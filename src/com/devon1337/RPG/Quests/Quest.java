package com.devon1337.RPG.Quests;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Commands.NFQuest;

public class Quest {

	/* Status Codes
	 * 0 - Available
	 * 1 - Active
	 * 2 - Completed
	 * 3 - Failed
	 */
	
	public final int MAX_RESPONSES = 4;
	public Player player;
	public int Status, QuestID, XP_Amount, Gold_Amount, CurSteps = 0, StepAmount = 5;
	public String Title, Description, Code;
	public String[] Responses = new String[MAX_RESPONSES];
	
	public Quest(int QuestID, String Title, String Description, String Code, String[] Responses, int XP_Amount, int Gold_Amount, Player player) {
		this.QuestID = QuestID;
		this.Title = Title;
		this.Description = Description;
		this.Code = Code;
		this.Responses = Responses;
		this.XP_Amount = XP_Amount;
		this.Gold_Amount = Gold_Amount;
		this.player = player;
	}
	
	public Quest(Quest quest, Player player, int Status, int StepAmount) {
		this.QuestID = quest.QuestID;
		Title = quest.Title;
		Description = quest.Description;
		Code = quest.Code;
		this.Responses = quest.Responses;
		this.XP_Amount = quest.XP_Amount;
		this.Gold_Amount = quest.Gold_Amount;
		this.player = player;
		this.Status = Status;
		this.StepAmount = StepAmount;
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
