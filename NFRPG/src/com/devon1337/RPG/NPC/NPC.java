package com.devon1337.RPG.NPC;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Utils.Dialog.Dialog;
import com.devon1337.RPG.Utils.Dialog.DialogCodes;
import com.devon1337.RPG.Utils.Dialog.DialogFlags;
import com.devon1337.RPG.Utils.Dialog.DialogueSystem;

public abstract class NPC implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7280448406879255912L;
	
	@Getter
	public NPCCodes code;
	
	@Getter @Setter
	public String name;
	
	@Getter @Setter
	public Faction faction;
	
	public INPC npc; // Gets the interface for the NPC
	
	@Getter @Setter
	public Dialog defaultDialog; // Default dialog line
	
	public HashMap<UUID, Dialog> activeDialog = new HashMap<>(); // Players currently talking to an npc
	
	@Getter @Setter
	public ArrayList<Dialog> genericDialog = new ArrayList<Dialog>(); // All generic dialog lines for said npc 
	
	@Getter
	public HashMap<UUID, Dialog[]> requiredDialog = new HashMap<>(); // Lists all dialog the player will need, done in post
	
	@Getter
	public ArrayList<Dialog> allLines = new ArrayList<>();

	public NPC(NPCCodes code, String name) {
		this.code = code;
		this.name = name;
	}

	public NPC(NPCCodes code, String name, Faction faction, HashMap<UUID, Dialog> activeDialog, ArrayList<Dialog> allLines) {
		this.code = code;
		this.name = name;
		this.faction = faction;
		this.activeDialog = activeDialog;
		this.allLines = allLines;
	}

	public NPC(String name) {
		this.code = NPCCodes.GENERIC;
		this.name = name;
	}

	public NPC getNPC() {
		return this;
	}
	
	// Seperates Generic Lines from dialog pool
	private void decoupleGenericLines(ArrayList<Dialog> lines) {
		
		for(Dialog d : lines) {
			if(d.getFlags().contains(DialogFlags.GENERIC)) {
				this.genericDialog.add(d);
			} else {
				this.allLines.add(d);
			}
		}	
	}
	
	// Replaces current dialog set with new dialog set
	public void setLines(ArrayList<Dialog> lines) {
		decoupleGenericLines(lines);
		DialogueSystem.GLOBAL_DIALOG_LIST.addAll(lines);
	}
	
	// Adds new dialog lines to NPC
	public void addLines(ArrayList<Dialog> lines) {
		decoupleGenericLines(lines);
		DialogueSystem.GLOBAL_DIALOG_LIST.addAll(lines);
	}
	
	// Adds a new dialog line to NPC
	public void addLine(Dialog line) {
		ArrayList<Dialog> temp = new ArrayList<Dialog>();
		temp.add(line);
		decoupleGenericLines(temp);
		DialogueSystem.GLOBAL_DIALOG_LIST.add(line);
	}
	
	// Removes the old priority list in favor of a new priority list
	public void setPriorityLines(Dialog[] lines, NFPlayer player) {
		this.requiredDialog.remove(player.getUUID());
		this.requiredDialog.put(player.getPlayer().getUniqueId(), lines);
		
	}
	
	// Adds to the existing priority pool
	public void addPriorityLine(Dialog line, NFPlayer player) {
		
		Dialog[] temp = new Dialog[1];
		
		if(this.requiredDialog.get(player.getUUID()) != null) {
			temp = this.requiredDialog.get(player.getUUID());
		} else {
			temp[0] = line;
		}
		
		this.requiredDialog.put(player.getUUID(), temp);
	}
	
	// Checks if the NPC has interacted with the player before
	public boolean hasDialog(Player player) {
		return this.activeDialog.containsKey(player.getUniqueId());
	}
	
	public Dialog getDialogFromCode(DialogCodes code) {
		for(Dialog d : allLines) {
			if(d.getCode().equals(code)) return d; 
		}
		return null;
	}

	// Sets up the dialog itself to rotate through different lines
	public Dialog getActiveDialog(Player player) {
		Random rand = new Random();
		Dialog dialog = null;
		// Run the methods in this exact order Q_Q or else dialog softlocking will happen
		
		// Checks to give the player the default NPC dialog
		if (!DialogueSystem.hasDialog(player) && this.allLines.size() > 0)
			dialog = this.defaultDialog;
		
		// Checks to give the player a random generic dialog line
		if(!DialogueSystem.hasDialog(player) && this.genericDialog.size() > 0) {
			ArrayList<Dialog> temp = new ArrayList<Dialog>();
		
			// Checks all generic dialog
			for(Dialog d : this.genericDialog) {
				if(d.getReq() != null && d.getReq().checkRequirement(NFPlayer.getPlayer(player.getUniqueId()))) {
					temp.add(d);
				} else if(d.getReq() == null) {
					temp.add(d);
				}
			}
			
			dialog = temp.get(rand.nextInt(temp.size()));
		}
		
		// Checks to give the player the required NPC dialog
		if (!DialogueSystem.hasDialog(player) && this.requiredDialog.containsKey(player.getUniqueId()))
			dialog = this.requiredDialog.get(player.getUniqueId())[0];
		
		// Applies the current prioritized dialog
		if(dialog != null) 
			setActiveDialog(player, dialog);
		 
		
		return this.activeDialog.get(player.getUniqueId());
	}

	// Forces the players active dialog
	public void setActiveDialog(Player player, Dialog dlog) {
		if (dlog != null) {
			this.activeDialog.remove(player.getUniqueId());
			this.activeDialog.put(player.getUniqueId(), dlog);
		}
	}

	public void initializePlayerReputation(Player player, int rep) {
	}
	
	// TODO: Add logic
	public static void setReputation(Player player, int rep) {
	}

	// I/O for NPC
	public void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		stream.writeObject(code.toString());
		stream.writeObject(name);
		//stream.writeObject();
		// stream.writeObject(DIALOG_LIST);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {

		String name = (String) stream.readObject();
		//Faction tempF = WorldController.getFaction((String) stream.readObject());
		HashMap<UUID, Dialog> curd = (HashMap<UUID, Dialog>) stream.readObject();
		ArrayList<Dialog> DL = (ArrayList<Dialog>) stream.readObject();

		//new NPC(code, name, tempF, rep, curd, DL);

	}

}