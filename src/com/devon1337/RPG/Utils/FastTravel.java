package com.devon1337.RPG.Utils;

import java.io.File;
import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FastTravel {
	private File dateFile = null;

	private static ArrayList<Point> wayPoints = new ArrayList<>();

	public static void addWayPoint(Point p1) {
		wayPoints.add(p1);
	}

	public static boolean wayPointExists(Point p1) {
		return wayPoints.contains(p1);
	}

	public static void removeWayPoint(Point p1) {
		wayPoints.remove(p1);
	}

	public static Point getWayPoint(int index) {
		return wayPoints.get(index);
	}

	public static void warpToPoint(Player player, Point p1) {
		Location loc = new Location(p1.getWorld(), p1.getX(), p1.getY() + 0.3D, p1.getZ(), p1.getYaw(), p1.getPitch());

		player.teleport(loc);
	}

	public static ArrayList<Point> grabList() {
		return wayPoints;
	}

	public static void listWaypoints(Player player) {
		for (int i = 0; i < wayPoints.size(); i++) {
			player.sendMessage(String.valueOf(((Point) wayPoints.get(i)).getName()) + ": ("
					+ ((Point) wayPoints.get(i)).getX() + " " + ((Point) wayPoints.get(i)).getY() + " "
					+ ((Point) wayPoints.get(i)).getZ() + ") Highway: " + ((Point) wayPoints.get(i)).getType());
		}
	}

	public File getFile() {
		return this.dateFile;
	}
}