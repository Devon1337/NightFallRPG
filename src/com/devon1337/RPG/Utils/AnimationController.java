 package com.devon1337.RPG.Utils;
 
 import org.bukkit.entity.ArmorStand;
 import org.bukkit.entity.Player;
 import org.bukkit.scheduler.BukkitRunnable;
 import org.bukkit.util.EulerAngle;
 import org.bukkit.util.Vector;
 
	public class AnimationController extends BukkitRunnable {
		double xv;
		double yv;

		public AnimationController(ArmorStand stand, Player player) {
			this.s = stand;
			this.p = player;
			this.xv = this.p.getVelocity().multiply(10).getX();
			this.yv = this.p.getVelocity().multiply(10).getY() + 0.784D;
			this.xv = this.p.getVelocity().multiply(10).getZ();
		}

		double zv;
		Player p;
		ArmorStand s;

		public void run() {
			EulerAngle oldRot = this.s.getRightArmPose();
			EulerAngle newRot = oldRot.add(0.10000000149011612D, 0.0D, 0.0D);
			this.p.sendMessage("Current Vel: " + this.s.getVelocity());
			this.p.sendMessage("P Orig Vel: " + this.xv + "," + this.yv + "," + this.zv);

			if (this.xv > this.yv && this.xv > this.zv) {
				this.s.setVelocity(new Vector(this.xv + 5.599999904632568D, this.yv, this.zv));
			} else if (this.xv < this.yv && this.yv > this.zv) {
				this.s.setVelocity(new Vector(this.xv, this.yv + 5.599999904632568D, this.zv));
			} else if (this.xv < this.zv && this.yv < this.zv) {
				this.s.setVelocity(new Vector(this.xv, this.yv, this.zv + 5.599999904632568D));
			}

			this.p.sendMessage("New Vel: " + this.s.getVelocity());
			this.s.setRightArmPose(newRot);
		}
	}