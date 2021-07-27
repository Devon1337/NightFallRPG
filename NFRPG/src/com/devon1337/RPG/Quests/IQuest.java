package com.devon1337.RPG.Quests;

public interface IQuest {
	public Quest newQuest();
	public void onFailed();
	public void onCompleted();
}
