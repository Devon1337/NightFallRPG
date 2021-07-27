package com.devon1337.RPG.Quests;

public enum QuestTags {
	START_ZONE_SELECT_CLASS, WARRIOR_START, NAME_YOURSELF, DRUID_START, ROGUE_START, MAGE_START, UNCHARTED_TERRITORIES;
	
	
	public static QuestTags getQuest(String Tag) {
		for(Quest q : Quest.getAllQuests()) {
			if(q.getTag().toString().equals(Tag)) {
				return q.getTag();
			}
		}
		return null;
	}
}
