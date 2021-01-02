package com.devon1337.RPG.Utils;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.Setter;

public class Point implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6161192230314637377L;
	@Getter @Setter
	private int x, y, z;

	@Getter @Setter
	private float pitch, yaw;
	@Getter
	private Material block = Material.VINE;
	@Getter
	private String name;
	@Getter
	private NFTType type;
	@Getter
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
		
		FileManager.exportPoint(this);
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Location getLocation() {
		return new Location(this.world, this.x, this.y, this.z, this.yaw, this.pitch);
	}
	
	// I/O for NFPlayer
	public void writeObject(java.io.ObjectOutputStream stream) throws IOException {
			stream.writeInt(x);
			stream.writeInt(y);
			stream.writeInt(z);
			stream.writeFloat(pitch);
			stream.writeFloat(yaw);
			stream.writeObject(name);
			stream.writeObject(type);
			stream.writeObject(world.getName());
	}
	
	public static void readObject(java.io.ObjectInputStream stream) throws IOException {
		try {
			int x = stream.readInt();
			int y = stream.readInt();
			int z = stream.readInt();
			float pitch = stream.readFloat();
			float yaw = stream.readFloat();
			String name = (String) stream.readObject();
			NFTType type = (NFTType) stream.readObject();
			World w = Bukkit.getWorld((String) stream.readObject());
			
			FastTravel.addWayPoint(new Point(x,y,z,pitch,yaw,name, type, w));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}