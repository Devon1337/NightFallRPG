package com.devon1337.RPG.Utils;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class AnimationController extends BukkitRunnable {

	double xv,yv,zv;
	Player p;
	ArmorStand s;
	
	public AnimationController(ArmorStand stand, Player player) {
		s = stand;
		p = player;
		xv = p.getVelocity().multiply(10).getX();
		yv = p.getVelocity().multiply(10).getY() + 0.784;
		xv = p.getVelocity().multiply(10).getZ();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		EulerAngle oldRot = s.getRightArmPose();
		EulerAngle newRot = oldRot.add(0.1f, 0, 0);
		p.sendMessage("Current Vel: " + s.getVelocity());
		p.sendMessage("P Orig Vel: " + xv + "," + yv + "," + zv);
		
		if (xv > yv && xv > zv) {
		s.setVelocity(new Vector(xv+5.6f, yv, zv));
		
		} else if (xv < yv && yv > zv) {
			s.setVelocity(new Vector(xv, yv+5.6f, zv));
			
		} else if (xv < zv && yv < zv) {
			s.setVelocity(new Vector(xv, yv, zv+5.6f));
			
		}
		
		
		p.sendMessage("New Vel: " + s.getVelocity());
		s.setRightArmPose(newRot);
	}

	
	
}
