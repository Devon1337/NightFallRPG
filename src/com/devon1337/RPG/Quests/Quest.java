package com.devon1337.RPG.Quests;

import com.devon1337.RPG.Commands.NFQuest;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public abstract class Quest {
	public UUID player;
	public int Status;
	public int QuestID;
	public int XP_Amount;
	public int Gold_Amount;
	public int CurSteps = 0;
	public int StepAmount = 5;
	public String Title;
	public String Description;

	public Quest(int QuestID, String Title, String Description, String Code, String[] Steps, int XP_Amount,
			int Gold_Amount, Player player, int Status, int StepAmount) throws QuestIDInUse {
		this.QuestID = QuestID;
		this.Title = Title;
		this.Description = Description;
		this.Code = Code;
		this.Steps = Steps;
		this.XP_Amount = XP_Amount;
		this.Gold_Amount = Gold_Amount;
		if (player != null) {
			this.player = player.getUniqueId();
		}
		this.Status = Status;
		this.StepAmount = StepAmount;
		QuestTracker.initQuest(this);
	}

	public String Code;
	public String[] Steps;

	public Quest(Quest quest, Player player, int Status, int StepAmount) {
		this.QuestID = quest.QuestID;
		this.Title = quest.Title;
		this.Description = quest.Description;
		this.Code = quest.Code;
		this.Steps = quest.Steps;
		this.XP_Amount = quest.XP_Amount;
		this.Gold_Amount = quest.Gold_Amount;
		if (player != null) {
			this.player = player.getUniqueId();
		}
		this.Status = Status;
		this.StepAmount = StepAmount;
	}

	public String getStep(int index) {
		if (index < this.Steps.length) {
			return this.Steps[index];
		}

		return "";
	}

	public String getCode() {
		return this.Code;
	}

	public String getTitle() {
		return this.Title;
	}

	public int getStepAmount() {
		return this.StepAmount;
	}

	public int getCurSteps() {
		return this.CurSteps;
	}

	public void setCurSteps(int CurSteps) {
		this.CurSteps = CurSteps;
	}

	public Player getPlayer() {
		if (this.player != null) {
			return Bukkit.getPlayer(this.player);
		}
		return null;
	}

	public void setPlayer(Player player) {
		this.player = player.getUniqueId();
	}

	public void setStatus(int Status) {
		if (Status == 2) {
			getPlayer().sendMessage(
					ChatColor.LIGHT_PURPLE + "Quest Complete: " + ChatColor.GOLD + ChatColor.BOLD + this.Title);
			getPlayer().playSound(getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
			if (NFPlayer.getPlayer(getPlayer().getUniqueId()) != null) {
				NFPlayer.getPlayer(getPlayer().getUniqueId()).addXp(XP_Amount);
			}
			NFQuest.addPlayer(getPlayer());
		}

		this.Status = Status;
	}

	public int getStatus() {
		return this.Status;
	}
}
