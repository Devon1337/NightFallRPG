package com.devon1337.RPG.Utils;

import java.io.File;
import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FastTravel {
	
	private File dateFile = null;
	private static ArrayList<Point> wayPoints = new ArrayList<>();

	// Adds new Waypoint to Global
	public static void addWayPoint(Point p1) {
		wayPoints.add(p1);
	}

	// Checks if Waypoint exists
	public static boolean wayPointExists(Point p1) {
		return wayPoints.contains(p1);
	}

	// Removes Waypoint from Global
	public static void removeWayPoint(Point p1) {
		wayPoints.remove(p1);
	}

	// Gets Waypoint from Code (rogue_start)
	public static Point getWayPoint(String code) {
		for(Point p : wayPoints) {
			if(p.getName().equals(code)) {
				return p;
			}
		}
		return null;
	}
	
	// Gets Waypoint from index
	public static Point getPerciseWayPoint(int index) {
		return wayPoints.get(index);
	}

	// Warps a player to a set Point
	public static void warpToPoint(Player player, Point p1) {
		Location loc = new Location(p1.getWorld(), p1.getX(), p1.getY() + 0.3D, p1.getZ(), p1.getYaw(), p1.getPitch());

		player.teleport(loc);
	}

	// Gets the list of all Waypoints
	public static ArrayList<Point> grabList() {
		return wayPoints;
	}

	// Displays all the way points.
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