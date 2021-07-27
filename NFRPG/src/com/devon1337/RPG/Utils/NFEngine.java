package com.devon1337.RPG.Utils;

public interface NFEngine {

	void onUpdate();
	
	void onDeath();
	
	void onInventoryClose();
	
	void onRespawn();
	
	void onHealthRegain();
	
	void onDropEvent();
	
	void onRegionEnter();
	
	void onRegionLeave();
	
	void onQuit();
	
	void onDisconnect();
	
	void onJoin();
	
	void onPlayerChat();
	
	void onStart();
	
}
