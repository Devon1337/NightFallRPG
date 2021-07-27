package com.devon1337.RPG.Quests.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.IQuest;

import lombok.Getter;

public class QuestFailedEvent extends Event {

	@Getter
	IQuest quest;
	
	@Getter
	NFPlayer player;
	
	private static final HandlerList HANDLERS = new HandlerList();
	
	public QuestFailedEvent(IQuest quest, NFPlayer player) {
		this.quest = quest;
		this.player = player;
	}
	
	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	
}
