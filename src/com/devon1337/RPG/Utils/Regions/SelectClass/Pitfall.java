package com.devon1337.RPG.Utils.Regions.SelectClass;

import org.bukkit.Location;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.FastTravel;
import com.devon1337.RPG.Utils.IRegion;
import com.devon1337.RPG.Utils.Region;

public class Pitfall extends Region implements IRegion {

	// Collision Name
	static final String COL_NAME = "selectclass_pitfall";
	static final String WARP_NAME = "SelectClass_Room_Warp";

	public Pitfall() {
		super(COL_NAME);
		super.setReg(this);
	}

	@Override
	public void onEnter(NFPlayer player) {
		player.setRegion(this);
		Location loc = FastTravel.getWayPoint(WARP_NAME).getLocation();
		loc.setPitch(player.getPlayer().getLocation().getPitch());
		loc.setYaw(player.getPlayer().getLocation().getYaw());
		player.getPlayer().teleport(loc);
	}

	@Override
	public void onLeave(NFPlayer player) {
		player.setRegion(null);
	}
	
}
