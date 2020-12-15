package com.devon1337.RPG.Quests;

public enum QuestStatus {
	
	/*
	 * Completed: Player has acquired and finished the task.
	 * Incomplete: Player has acquired the task but has not finished the task.
	 * Failed: Player has acquired the task and failed it.
	 * Available: Player has not acquired the task
	 * Unavailable: Task is unavailable to all players
	 */
	Completed, Incomplete, Failed, Available, Unavailable;
}
