package com.devon1337.RPG.Quests;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.NPC.AllFactions;
import com.devon1337.RPG.NPC.Faction;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Events.QuestCompletedEvent;
import com.devon1337.RPG.Quests.Events.QuestFailedEvent;
import com.devon1337.RPG.Quests.Events.QuestStartEvent;
import com.devon1337.RPG.Utils.Dialog.RequirementType;

import lombok.Getter;
import lombok.Setter;

public abstract class Quest implements Listener {
	
	@Getter @Setter
	String name, description;
	
	@Getter
	NFPlayer player;
	
	@Getter
	int QuestID, xpAmount, goldAmount, Quantity, RequiredQuantity;
	
	@Getter @Setter
	Material mat;
	
	@Getter @Setter
	EntityType ent;
	
	@Getter @Setter
	int CurrentStep;
	
	@Getter @Setter
	ArrayList<Step> steps;
	
	@Getter @Setter
	ArrayList<EventFlags> flags = new ArrayList<EventFlags>();
	
	@Getter
	QuestStatus status;
	
	@Getter @Setter
	QuestTags tag;
	
	@Getter
	Faction faction;
	
	@Getter
	IQuest q;
	
	@Getter
	static ArrayList<Quest> allQuests = new ArrayList<Quest>();
	
	public Quest(String name, String description, int xpAmount, int goldAmount, QuestTags tag, ArrayList<EventFlags> flags, QuestStatus status) {
		this.name = name;
		this.description = description;
		this.xpAmount = xpAmount;
		this.goldAmount = goldAmount;
		this.flags = flags;
		this.status = status;
		this.QuestID = allQuests.size();
		this.tag = tag;
		CurrentStep = 0;
		Logging.OutputToConsole("Quest Created: " + this.name);
		Bukkit.getServer().getPluginManager().registerEvents((Listener) this, NightFallRPG.getPlugin());
		if(!allQuests.contains(this)) {
			allQuests.add(this);
		}
		
	}
	
	public void setFaction(Faction f) {
		allQuests.remove(this);
		this.faction = f;
		allQuests.add(this);
	}
	
	public void setQ(IQuest q) {
		allQuests.remove(this);
		this.q = q;
		allQuests.add(this);
	}
	
	// Gets all quests from a particular faction
	public static ArrayList<Quest> getAllQuestsFromFaction(AllFactions faction) {
		ArrayList<Quest> q2 = new ArrayList<Quest>();
		for(Quest q : allQuests) {
			Logging.OutputToConsole("Checking Quest: " + q.getName());
			if(q.getFaction() != null) {
			 if(q.getFaction().getFaction().equals(faction)) q2.add(q);
			} else if(faction == null && q.getFaction() == null) {
				q2.add(q);
			}
		}
		return q2;
	}
	
	// Checks all steps for their requirement completion
	public void checkRequirement(NFPlayer player) {
		// Simple check to make sure its not updating already completed/not acquired quests
		if (this.status != QuestStatus.Completed || this.status != QuestStatus.Unavailable
				|| this.status != QuestStatus.Failed) {
			for (Step s : this.steps) {
				s.checkStatus(player);
			}
		}
	}
	
	public void setStatus(QuestStatus status) {
		this.status = status;
	}
	
	public static Quest getQuest(QuestTags tag) {
		for(Quest q : allQuests) {
			if(q.getTag() == tag) {
				return q;
			}
		}
		return null;
	}
	
	public Quest instantiateQuest(NFPlayer player) {
		Quest q2 = q.newQuest();
		if(q2 != null) {
			applyDialog(player);
			return q2;
		} else {
			return null;
		}
		
	}
	
	public void setPlayer(NFPlayer player) {
		allQuests.remove(this);
		this.player = player;
	}
	
	@EventHandler
	public void onFailed(QuestFailedEvent event) {
		if (event.getQuest().equals(this.q)) {
			this.setStatus(QuestStatus.Failed);
			q.onFailed();
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCompleted(QuestCompletedEvent event) {
		if (event.getQuest().equals(this.q) && this.player.equals(event.getPlayer())) {
			Player player = event.getPlayer().getPlayer();
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
			player.sendTitle("Quest Completed", this.getName());
			this.setStatus(QuestStatus.Completed);
			event.getPlayer().addXp(this.getXpAmount());
			q.onCompleted();
		}
	}
	
	// New Quest Event
	@EventHandler
	public void onNewQuest(QuestStartEvent event) {
		
		// Instantiates the quest for the player
		NFPlayer player = event.getPlayer();
		Quest q = event.getQuest();
		
		if(player != null && q != null) {
			player.addQuest(q);
		}
	}
	
	// Adds priority dialog
	public void applyDialog(NFPlayer player) {
		for(Step s : steps) {
			Logging.OutputToConsole("NPC: " + s.req.getNpc() + "Dialog: " + s.req.getDialog());
			if(s.req.getType() == RequirementType.Dialog) {
				s.req.getNpc().addPriorityLine(s.req.getDialog(), player);
			}
		}
	}
}
