package com.devon1337.RPG.NPC;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;

public interface INPC {

	
	void StartQuest(Quest quest, NFPlayer player);
	void FailQuest();
	void CompleteQuest();
	
	// void StartQuest
	// void FailQuest
	// void CompleteQuest
	
}
