package com.devon1337.RPG.Utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import com.devon1337.RPG.Debugging.Logging;

import email.com.gmail.cosmoconsole.bukkit.camera.ServerCinematics;



public class Cinematic {

	public String Code, Name;
	public ArrayList<String> Paths = new ArrayList<String>();
	public static ArrayList<String> GLOBAL_ANIMATION_PATHS = new ArrayList<String>();
	public static ServerCinematics cine = (ServerCinematics) Bukkit.getServer().getPluginManager().getPlugin("ServerCinematics");
	
	public static void loadPaths() {
		
		GLOBAL_ANIMATION_PATHS = (ArrayList<String>) cine.getAvailablePaths();
		
		for(int i = 0; i < GLOBAL_ANIMATION_PATHS.size(); i++) {
			Logging.OutputToConsole("Paths: " + GLOBAL_ANIMATION_PATHS.get(i));
		}
		
	}
	
	
	public Cinematic(String Code, String Name, String... Paths) {
		this.Code = Code;
		this.Name = Name;
	}
	
	public String getPath(String path) {
		for(int i = 0; i < GLOBAL_ANIMATION_PATHS.size(); i++) {
			if(GLOBAL_ANIMATION_PATHS.get(i).equals(path)) return GLOBAL_ANIMATION_PATHS.get(i); 
		}
		
		return "";
	}
}
