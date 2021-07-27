package com.devon1337.RPG.Utils.Regions.Druid;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.FastTravel;
import com.devon1337.RPG.Utils.IRegion;
import com.devon1337.RPG.Utils.Region;

public class TunnelEntrance extends Region implements IRegion {

	// Collision Name
	static final String COL_NAME = "worldtree_tunnel_entrance";
	static final String WARP_NAME = "worldtree_secret_warp";

	public TunnelEntrance() {
		super(COL_NAME);
		super.setReg(this);
	}

	@Override
	public void onEnter(NFPlayer player) {
		player.setRegion(this);

		player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 1));

		Bukkit.getScheduler().scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
			@Override
			public void run() {
				Location loc = FastTravel.getWayPoint(WARP_NAME).getLocation();
				loc.setPitch(player.getPlayer().getLocation().getPitch());
				loc.setYaw(player.getPlayer().getLocation().getYaw());
				player.getPlayer().teleport(loc);
			}
		}, 10);
	}

	@Override
	public void onLeave(NFPlayer player) {
		player.setRegion(null);
	}

}
