package com.devon1337.RPG.Utils.Regions.Debug;

import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.NPCCodes;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.IRegion;
import com.devon1337.RPG.Utils.Region;
import com.devon1337.RPG.Utils.Dialog.DialogueSystem;
import com.devon1337.RPG.Utils.Dialog.Requirement;

public class ShrineTest extends Region implements IRegion {

	// Collision Name
	static final String COL_NAME = "shrine_test";
	Requirement req = new Requirement(13000, 18000, true);

	public ShrineTest() {
		super(COL_NAME);
		super.setReg(this);
	}

	@Override
	public void onEnter(NFPlayer player) {
		player.setRegion(this);
		
		// Starts the dialog with the example shrine god
		if(req.checkRequirement(player)) {
			NPC npc = WorldController.npcExist(NPCCodes.TEST_SHRINE_GOD);
			DialogueSystem.DisplayDialog(NFPlayer.getPlayer(player.getUUID()), npc.getActiveDialog(player.getPlayer()));
		}
	}

	@Override
	public void onLeave(NFPlayer player) {
		player.setRegion(null);
	}

}
