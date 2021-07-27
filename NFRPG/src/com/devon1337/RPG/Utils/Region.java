package com.devon1337.RPG.Utils;

import java.util.ArrayList;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;

import lombok.Getter;
import lombok.Setter;

public abstract class Region {

	@Getter
	String CollisionName;
	
	@Getter @Setter
	IRegion reg;
	
	
	static ArrayList<Region> allRegions = new ArrayList<>();
	
	public Region(String CollisionName) {
		this.CollisionName = CollisionName;
		allRegions.add(this);
	}
	
	public static Region getRegion(String CollisionName) {
		for(Region r : allRegions) {
			if(r.getCollisionName().equals(CollisionName)) {
				return r;
			}
		}
		return null;
	}
	
	public void enterRegion(NFPlayer player) {
		reg.onEnter(player);
		
		for(Quest q : player.getQuests()) {
			q.checkRequirement(player);
		}
	}
	
	public void leaveRegion(NFPlayer player) {
		reg.onLeave(player);
	}
	
	
}
