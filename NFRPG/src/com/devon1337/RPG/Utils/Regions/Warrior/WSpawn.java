package com.devon1337.RPG.Utils.Regions.Warrior;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.IRegion;
import com.devon1337.RPG.Utils.Region;

public class WSpawn extends Region implements IRegion {

	// Collision Name
	static final String COL_NAME = "wspawn";

	public WSpawn() {
		super(COL_NAME);
		super.setReg(this);
	}

	@Override
	public void onEnter(NFPlayer player) {
		
		/*
		// sets up blackwood with the correct dialog, probably gonna repurpose ngl
		Quest q = player.getQuestFromTags(QuestTags.WARRIOR_START);
		NPC blackwood = WorldController.npcExist(NPCCodes.WARRIOR_BLACKWOOD);
		if(q != null) {
			blackwood.setActiveDialog(player.getPlayer(), blackwood.getAllLines().get(0));
		}
		
		*/
	}

	@Override
	public void onLeave(NFPlayer player) {
		// TODO Auto-generated method stub
		
	}

}
