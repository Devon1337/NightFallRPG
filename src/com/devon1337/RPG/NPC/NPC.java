package com.devon1337.RPG.NPC;

import com.devon1337.RPG.Utils.Dialog;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;

public abstract class NPC implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7280448406879255912L;
	public String Code;
	public String Name;
	public Faction faction;
	public HashMap<UUID, Dialog> CurDialog = new HashMap<>();
	
	@Getter @Setter
	public ArrayList<Dialog> DIALOG_LIST = new ArrayList<>();

	public NPC(String Code, String Name) {
		this.Code = Code;
		this.Name = Name;
	}

	public NPC(String Code, String Name, Faction faction, HashMap<UUID, Dialog> CurDialog, ArrayList<Dialog> dialog) {
		this.Code = Code;
		this.Name = Name;
		this.faction = faction;
		this.CurDialog = CurDialog;
		this.DIALOG_LIST = dialog;
	}

	public NPC(String Name) {
		this.Name = Name;
	}

	public String getCode() {
		return this.Code;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public NPC getNPC() {
		return this;
	}

	public boolean hasDialog(Player player) {
		return this.CurDialog.containsKey(player.getUniqueId());
	}

	public Faction getFaction() {
		return this.faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	public static void setReputation(Player player, int rep) {
	}

	public Dialog getCurDialog(Player player) {
		if (!this.CurDialog.containsKey(player.getUniqueId()) && this.DIALOG_LIST.size() > 0)
			setCurDialog(player, this.DIALOG_LIST.get(0));
		return this.CurDialog.get(player.getUniqueId());
	}

	public void setCurDialog(Player player, Dialog dlog) {
		if (dlog != null) {
			this.CurDialog.remove(player.getUniqueId());
			this.CurDialog.put(player.getUniqueId(), dlog);
		}
	}

	public void initializePlayerReputation(Player player, int rep) {
	}

	// I/O for NPC
	public void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		stream.writeObject(Code);
		stream.writeObject(Name);
		stream.writeObject(CurDialog);
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