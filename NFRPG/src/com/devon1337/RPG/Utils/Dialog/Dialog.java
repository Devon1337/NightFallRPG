package com.devon1337.RPG.Utils.Dialog;

import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.Quests.Quest;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

public class Dialog implements java.io.Serializable {

	private static final long serialVersionUID = -2423348137035693927L;
	
	@Getter
	DialogCodes code;
	
	@Getter @Setter
	Requirement req;
	
	@Getter
	String message;
	
	@Getter 
	NPC npc;
	
	@Getter
	Response[] response;
	
	@Getter
	ArrayList<DialogFlags> flags = new ArrayList<>();
	
	@Getter @Setter
	Quest quest; // Related to quest line
	
	@Getter @Setter
	Dialog nextLine;
	
	public Dialog(String message, NPC npc) {
		this.message = message;
		this.npc = npc;
	}
	
	public Dialog(String message, NPC npc, Response[] response, DialogCodes code) {
		this.message = message;
		this.npc = npc;
		this.response = response;
		this.code = code;
	}
	
	public Dialog(String message, NPC npc, Response[] response, DialogCodes code, Requirement req) {
		this.message = message;
		this.npc = npc;
		this.response = response;
		this.code = code;
		this.req = req;
	}
	
	public void removeFlag(DialogFlags flag) {
		this.flags.remove(flag);
	}

	public void addFlag(DialogFlags flag) {
		this.flags.add(flag);
	}

	public void writeObject(java.io.ObjectOutputStream stream) {
		try {
			stream.writeObject(flags);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}