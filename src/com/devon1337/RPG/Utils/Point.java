package com.devon1337.RPG.Utils;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Point implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6161192230314637377L;
	private int x;
	private int y;
	private int z;
	private float pitch;
	private Material block = Material.VINE;
	private float yaw;
	private String name;
	private NFTType type;
	private World world;

	@Deprecated
	public Point(int x, int y, int z, float pitch, float yaw, String name, NFTType type, World world) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
		this.name = name;
		this.type = type;
		setWorld(world);
	}

	public Point(Player player, String name, NFTType type) {
		Location loc = player.getLocation();
		this.x = loc.getBlockX();
		this.y = loc.getBlockY();
		this.z = loc.getBlockZ();
		this.pitch = loc.getPitch();
		this.yaw = loc.getYaw();
		this.name = name;
		this.type = type;
		setWorld(player.getWorld());
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public float getYaw() {
		return this.yaw;
	}

	public float getPitch() {
		return this.pitch;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NFTType getType() {
		return this.type;
	}

	public World getWorld() {
		return this.world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Material getBlock() {
		return this.block;
	}

	public void setBlock(Material block) {
		this.block = block;
	}
	
	// I/O for NFPlayer
	public void writeObject(java.io.ObjectOutputStream stream) throws IOException {
			stream.writeInt(x);
			stream.writeInt(y);
			stream.writeInt(z);
			stream.writeFloat(pitch);
			stream.writeFloat(yaw);
			stream.writeChars(name);
			stream.writeChars(type.getName());	
	}
	
}