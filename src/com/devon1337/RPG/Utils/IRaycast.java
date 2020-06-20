package com.devon1337.RPG.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import com.devon1337.RPG.Utils.Raycast.ProjectileType;

import net.minecraft.server.v1_15_R1.PacketPlayOutEntityDestroy;

public class IRaycast {

	public Player target, shooter;
	public double damage = 0;
	public int knockback_amount = 0;
	public boolean gravity = false, silent = true;
	public Vector velocity;
	public Location loc;
	public Arrow arrow;
	public Fireball fb;
	public SmallFireball sfb;

	// Internal use
	public IRaycast(Player sh, ProjectileType proj) {

	}

	public IRaycast(Player sh, ProjectileType proj, double d) {

	}

	public IRaycast(Player sh, ProjectileType proj, double d, int kb) {

	}

	public IRaycast(Player sh, ProjectileType proj, double d, int kb, boolean g) {

	}

	public IRaycast(Player sh, ProjectileType proj, double d, int kb, boolean g, boolean s) {

	}

	public IRaycast(Player sh, ProjectileType proj, double d, int kb, boolean g, boolean s, Vector v) {

	}

	// External use
	public IRaycast(Player sh, ProjectileType proj, Plugin plugin) {

	}

	public IRaycast(Player sh, ProjectileType proj, Plugin plugin, double d) {

	}

	public IRaycast(Player sh, ProjectileType proj, Plugin plugin, double d, int kb) {

	}

	public IRaycast(Player sh, ProjectileType proj, Plugin plugin, double d, int kb, boolean g) {

	}

	public IRaycast(Player sh, ProjectileType proj, Plugin plugin, double d, int kb, boolean g, boolean s) {

	}

	public IRaycast(Player sh, ProjectileType proj, Plugin plugin, double d, int kb, boolean g, boolean s, Vector v) {

	}

	// Projectile Launching
	@SuppressWarnings("unused")
	private void useArrow(Player player, Plugin plugin) {
		arrow = player.launchProjectile(Arrow.class, player.getLocation().getDirection());
		arrow.setVelocity(arrow.getVelocity().multiply(4));
		arrow.setDamage(0);
		arrow.setKnockbackStrength(0);
		arrow.setGravity(false);
		arrow.setSilent(true);
		arrow.setInvulnerable(true);
		arrow.setMetadata("raycast", new FixedMetadataValue(plugin, true));
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
		    PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(arrow.getEntityId());
		    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		}
	}
	
	// Force clip projectile objects
	public void forceClip() {
		
	}
	
	
	// Getters and Setters
	public Player getTarget() {
		return shooter;

	}

	public void setTarget(Player target) {
		this.target = target;
	}

	public Player getShooter() {
		return shooter;
	}

	public void setShooter(Player shooter) {
		this.shooter = shooter;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public int getKnockback() {
		return knockback_amount;
	}

	public void setKnockback(int knockback_amount) {
		this.knockback_amount = knockback_amount;
	}

	public boolean hasGravity() {
		return gravity;

	}

	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}

	public boolean isSilent() {
		return silent;
	}

	public void setSilent(boolean silent) {
		this.silent = silent;
	}

}