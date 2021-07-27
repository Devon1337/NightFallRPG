package com.devon1337.RPG.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Dialog.Dialog;
import com.devon1337.RPG.Utils.Dialog.DialogueSystem;

public class FileManager {

	// nfn: NightFall NPC
	// nfr: NightFall Raid
	// nft: NightFall Fast Travel
	// nfd: NightFall Dialog
	public final static String[] extensions = {"nfn", "nfr", "nft", "nfp", "nfd"};
	public static void writeToFile(FileType ft) {
			for(NPC n : WorldController.AllNPC) {
				File file = new File(NightFallRPG.getPlugin().getDataFolder() + "/npc/" + n.getCode() + "." + extensions[0]);
				if(!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					Logging.OutputToConsole("Creating File: " + n.name + ".");
					n.writeObject(oos);
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		
			
	}
	
	private static void fileExists(File file) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void exportPlayer(NFPlayer player) {
		File file = new File(NightFallRPG.getPlugin().getDataFolder() + "/players/" + player.getUUID().toString() + "." + extensions[3]);
		fileExists(file);
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			Logging.OutputToConsole("Creating File: " + player.getUUID() + ".");
			player.writeObject(oos);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void exportNpc() {
		for(NPC n : WorldController.AllNPC) {
			File file = new File(NightFallRPG.getPlugin().getDataFolder() + "/npc/" + n.getCode() + "." + extensions[0]);
			fileExists(file);
			try {
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				Logging.OutputToConsole("Creating File: " + n.name + ".");
				n.writeObject(oos);
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void exportDialog() {
		for(Dialog d : DialogueSystem.getDialogs()) {
			File file = new File(NightFallRPG.getPlugin().getDataFolder() + "/dialog/" + d.getCode() + "." + extensions[4]);
			fileExists(file);
			
			try {
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				Logging.OutputToConsole("Creating File: " + d.getCode() + ".");
				d.writeObject(oos);
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void importPlayer(UUID player) {
		File file = new File(NightFallRPG.getPlugin().getDataFolder() + "/players/" + player.toString() + "." + extensions[3]);
		if(file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				NFPlayer.readObject(ois);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void exportPoint(Point p) {
		File file = new File(NightFallRPG.getPlugin().getDataFolder() + "/warps/" + p.getType() + "_" + p.getName() + ".nft");
		
		fileExists(file);
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			p.writeObject(oos);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void importFastTravel() throws IOException {
		for(File file : new File(NightFallRPG.getPlugin().getDataFolder() + "/warps/").listFiles()) {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Point.readObject(ois);
			
		}
	}
	
	public static boolean hasPlayedBefore(UUID player) {
		File file = new File(NightFallRPG.getPlugin().getDataFolder() + "/players/" + player.toString() + "." + extensions[3]);
		if(file.exists()) {
			return true;
		}
		
		return false;
	}
	
}
