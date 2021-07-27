package com.devon1337.RPG.Utils.Regions.SelectClass;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.IRegion;
import com.devon1337.RPG.Utils.Region;

public class AncientBookRoom extends Region implements IRegion {

	// Collision Name
	static final String COL_NAME = "select_class_book_room";

	public AncientBookRoom() {
		super(COL_NAME);
		super.setReg(this);
	}

	@Override
	public void onEnter(NFPlayer player) {
		player.setRegion(this);
	}

	@Override
	public void onLeave(NFPlayer player) {
		player.setRegion(null);
	}

}