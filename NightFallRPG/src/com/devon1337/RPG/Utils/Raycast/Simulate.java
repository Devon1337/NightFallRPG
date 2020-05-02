package com.devon1337.RPG.Utils.Raycast;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Utils.Raycast.Exceptions.BadProjectile;
import com.devon1337.RPG.Utils.Raycast.Exceptions.IllegalPluginAccess;
import com.devon1337.RPG.Utils.Raycast.Exceptions.InvalidTarget;
import com.devon1337.RPG.Utils.Raycast.Exceptions.ObjectsNotUsed;

import net.minecraft.server.v1_15_R1.PacketPlayOutEntityDestroy;

public class Simulate {

	public Player target;
	public Player shooter;
	public double damage = 0;
	public int knockback_amount = 0;
	public boolean gravity = true, silent = true;
	public Vector velocity;
	public Arrow arrow;
	public Fireball fireball;
	public SmallFireball smallfireball;
	public static Location loc;

	/*
	 * Summary: Used for external use, Arrow Default
	 */
	public Simulate(Player player) {
		shooter = player;
		useArrow(player, NightFallRPG.getPlugin());
	}

	/*
	 * Summary: Used for internal use, Arrow Default
	 */
	public Simulate(Player player, Plugin plugin) throws IllegalPluginAccess {
		shooter = player;
		useArrow(player, plugin);
	}

	/*
	 * Summary: Used for external use
	 */
	public Simulate(Player player, ProjectileType projectile) throws BadProjectile, ObjectsNotUsed {
		shooter = player;
		Plugin plugin = NightFallRPG.getPlugin();
		switch (projectile) {
		case ARROW:
			useArrow(player, plugin);
			clippingTimer(plugin, ProjectileType.ARROW);
			break;
		case FIREBALL:
			useFireball(player, plugin);
			clippingTimer(plugin, ProjectileType.FIREBALL);
			break;
		case SMALL_FIREBALL:
			useSmallFireball(player, plugin);
			clippingTimer(plugin, ProjectileType.SMALL_FIREBALL);
			break;
		default:
			throw new BadProjectile("Exception: BadProjectile -- Refer to com.devon1337.RPG.Utils.Raycast");
		}
	}

	/*
	 * Summary: Used for internal use
	 */
	public Simulate(Player player, Plugin plugin, ProjectileType projectile) throws BadProjectile, ObjectsNotUsed {
		shooter = player;
		switch (projectile) {
		case ARROW:
			useArrow(player, plugin);
			clippingTimer(plugin, ProjectileType.ARROW);
			break;
		case FIREBALL:
			useFireball(player, plugin);
			clippingTimer(plugin, ProjectileType.FIREBALL);
			break;
		case SMALL_FIREBALL:
			useSmallFireball(player, plugin);
			clippingTimer(plugin, ProjectileType.SMALL_FIREBALL);
			break;
		default:
			throw new BadProjectile("Exception: BadProjectile -- Refer to com.devon1337.RPG.Utils.Raycast");
		}
	}

	/*
	 * Summary: Used for external use
	 */
	public Simulate(Player player, ProjectileType projectile, double DMG) throws BadProjectile, ObjectsNotUsed {
		shooter = player;
		damage = DMG;
		Plugin plugin = NightFallRPG.getPlugin();
		switch (projectile) {
		case ARROW:
			useArrow(player, plugin);
			clippingTimer(plugin, ProjectileType.ARROW);
			break;
		case FIREBALL:
			useFireball(player, plugin);
			clippingTimer(plugin, ProjectileType.FIREBALL);
			break;
		default:
			throw new BadProjectile("Exception: BadProjectile -- Refer to com.devon1337.RPG.Utils.Raycast");
		}
	}

	/*
	 * Summary: Used for external use
	 */
	public Simulate(Player player, ProjectileType projectile, double DMG, int ka) throws BadProjectile, ObjectsNotUsed {
		shooter = player;
		damage = DMG;
		knockback_amount = ka;
		Plugin plugin = NightFallRPG.getPlugin();
		switch (projectile) {
		case ARROW:
			useArrow(player, plugin);
			clippingTimer(plugin, ProjectileType.ARROW);
			break;
		case FIREBALL:
			useFireball(player, plugin);
			clippingTimer(plugin, ProjectileType.FIREBALL);
			break;
		default:
			throw new BadProjectile("Exception: BadProjectile -- Refer to com.devon1337.RPG.Utils.Raycast");
		}
	}

	/*
	 * Summary: Used for external use
	 */
	public Simulate(Player player, ProjectileType projectile, double DMG, int ka, boolean grav)
			throws BadProjectile, ObjectsNotUsed {
		shooter = player;
		damage = DMG;
		knockback_amount = ka;
		gravity = grav;
		Plugin plugin = NightFallRPG.getPlugin();
		switch (projectile) {
		case ARROW:
			useArrow(player, plugin);
			clippingTimer(plugin, ProjectileType.ARROW);
			break;
		case FIREBALL:
			useFireball(player, plugin);
			clippingTimer(plugin, ProjectileType.FIREBALL);
			break;
		default:
			throw new BadProjectile("Exception: BadProjectile -- Refer to com.devon1337.RPG.Utils.Raycast");
		}
	}

	/*
	 * Summary: Used for external use
	 */
	public Simulate(Player player, ProjectileType projectile, double DMG, int ka, boolean grav, boolean sil)
			throws BadProjectile, ObjectsNotUsed {
		shooter = player;
		damage = DMG;
		knockback_amount = ka;
		gravity = grav;
		silent = sil;
		Plugin plugin = NightFallRPG.getPlugin();
		switch (projectile) {
		case ARROW:
			useArrow(player, plugin);
			clippingTimer(plugin, ProjectileType.ARROW);
			break;
		case FIREBALL:
			useFireball(player, plugin);
			clippingTimer(plugin, ProjectileType.FIREBALL);
			break;
		default:
			throw new BadProjectile("Exception: BadProjectile -- Refer to com.devon1337.RPG.Utils.Raycast");
		}
	}

	/*
	 * Summary: Used for external use
	 */
	public Simulate(Player player, ProjectileType projectile, double DMG, int ka, boolean grav, boolean sil, Vector vel) throws BadProjectile, ObjectsNotUsed {
		shooter = player;
		damage = DMG;
		knockback_amount = ka;
		gravity = grav;
		silent = sil;
		velocity = vel;
		Plugin plugin = NightFallRPG.getPlugin();
		if(velocity != null) {
		switch (projectile) {
		case ARROW:
			useArrow(player, plugin);
			clippingTimer(plugin, ProjectileType.ARROW);
			break;
		case FIREBALL:
			useFireball(player, plugin);
			clippingTimer(plugin, ProjectileType.FIREBALL);
			break;
		default:
			throw new BadProjectile("Exception: BadProjectile -- Refer to com.devon1337.RPG.Utils.Raycast");
		}
		} else {
			throw new ObjectsNotUsed("Exeception: ObjectsNotUsed -- Refer to velocity is current null");
		}
	}

	private void useFireball(Player player, Plugin plugin) {
		fireball = (Fireball) NightFallRPG.getWorld().spawnEntity(player.getLocation(), EntityType.FIREBALL);

		fireball.setMetadata("raycast", new FixedMetadataValue(plugin, true));
	}

	private void useSmallFireball(Player player, Plugin plugin) {
		int x = player.getLocation().getBlockX();
		int y = player.getLocation().getBlockY() + 1;
		int z = player.getLocation().getBlockZ();
		float pitch = player.getLocation().getPitch();
		float yaw = player.getLocation().getYaw();
		loc = new Location(player.getLocation().getWorld(), x,y,z, yaw, pitch);
		smallfireball = (SmallFireball) NightFallRPG.getWorld().spawnEntity(loc,
				EntityType.SMALL_FIREBALL);

		smallfireball.setMetadata("raycast", new FixedMetadataValue(plugin, true));
	}

	private void useArrow(Player player, Plugin plugin) {

		arrow = player.launchProjectile(Arrow.class, player.getLocation().getDirection());
		arrow.setVelocity(arrow.getVelocity().multiply(4));
		arrow.setDamage(0);
		arrow.setKnockbackStrength(0);
		arrow.setGravity(false);
		arrow.setSilent(true);
		arrow.setMetadata("raycast", new FixedMetadataValue(plugin, true));
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
		    PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(arrow.getEntityId());
		    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		}

		//arrowClippingRunnable(plugin, bullet);
	}

	private void removeFireball() throws ObjectsNotUsed {
		if (fireball != null) {
			fireball.remove();
		} else {
			throw new ObjectsNotUsed(
					"Exception: ObjectsNotUsed -- You attempted to read for an object that does not yet exist!");
		}
	}

	private void removeArrow() throws ObjectsNotUsed {
		if (arrow != null) {
			arrow.remove();
		} else {
			throw new ObjectsNotUsed(
					"Exception: ObjectsNotUsed -- You attempted to read for an object that does not yet exist!");
		}
	}

	private void removeSmallFireball() throws ObjectsNotUsed {
		if (smallfireball != null) {
			smallfireball.remove();
		} else {
			throw new ObjectsNotUsed(
					"Exception: ObjectsNotUsed -- You attempted to read for an object that does not yet exist!");
		}
	}

	private void clippingTimer(Plugin plugin, ProjectileType projectile) throws BadProjectile, ObjectsNotUsed {
		BukkitScheduler scheduler = plugin.getServer().getScheduler();

		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				// System.out.println("Running!");
				switch (projectile) {
				case ARROW:
					try {
						removeArrow();
					} catch (ObjectsNotUsed e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case FIREBALL:
					try {
						removeFireball();
					} catch (ObjectsNotUsed e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case SMALL_FIREBALL:
					try {
						removeSmallFireball();
					} catch (ObjectsNotUsed e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				default:
					try {
						throw new BadProjectile("Exception: BadProjectile -- Refer to com.devon1337.RPG.Utils.Raycast");
					} catch (BadProjectile e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}, 20 * 15);
	}
	
	public Location getLocation() {
		return loc;
	}

	public void setTarget(Player player) {
		target = player;
	}

	public Player getTarget() throws InvalidTarget {
		if (target == null) {
			throw new InvalidTarget("Exception: InvalidTarget -- Target may be null, please ask a developer!");
		} else {
			return target;
		}
	}

	public void setShooter(Player player) {
		shooter = player;
	}

	public Player getShooter() throws InvalidTarget {
		if (shooter == null) {
			throw new InvalidTarget("Exception: InvalidTarget -- Target may be null, please ask a developer!");
		} else {
			return shooter;
		}
	}
}