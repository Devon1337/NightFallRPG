package com.devon1337.RPG.Utils.Raycast;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitScheduler;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Debugging.Logging;

import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy;


public class Raycast {

	Location startPosition;
	UUID Shooter;
	Arrow arrow;
	Spell spell;
	
	public Raycast(Player player) {
		this.Shooter = player.getUniqueId();
		castRay();
	}
	
	public Raycast(UUID Shooter) {
		this.Shooter = Shooter;
		castRay();
	}
	
	public Raycast(UUID Shooter, Spell spell) {
		this.Shooter = Shooter;
		this.spell = spell;
		castRay();
	}
	
	@SuppressWarnings("rawtypes")
	public void castRay() {
		Logging.OutputToConsole("Casting ray");
		Player player = Bukkit.getPlayer(Shooter);
		
		this.arrow = (Arrow) player.launchProjectile(Arrow.class, player.getLocation().getDirection());
		this.arrow.setVelocity(this.arrow.getVelocity().multiply(4));
		this.arrow.setDamage(0.0D);
		this.arrow.setKnockbackStrength(0);
		this.arrow.setGravity(true);
		this.arrow.setSilent(true);
		this.arrow.setInvulnerable(true);
		
		this.arrow.setMetadata("raycast", (MetadataValue) new FixedMetadataValue(NightFallRPG.getPlugin(), this));
		if(spell != null) {
			this.arrow.setMetadata("spell", (MetadataValue) new FixedMetadataValue(NightFallRPG.getPlugin(), spell.getId()));
		}

		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(new int[] { this.arrow.getEntityId() });
			(((CraftPlayer) p).getHandle()).playerConnection.sendPacket((Packet) packet);
		}
		clippingTimer();
	}
	
	private void removeArrow() {
		if (this.arrow != null) {
			this.arrow.remove();
		}
	}
	
	private void clippingTimer() {
		BukkitScheduler scheduler = NightFallRPG.getPlugin().getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable() {
			public void run() {
				removeArrow();
			}
		}, 300L);
	}
	
	public Location getLocation() {
		return this.startPosition;
	}
	
	public Spell getSpell() {
		return this.spell;
	}
	
	public UUID getShooter() {
		return this.Shooter;
	}
}
