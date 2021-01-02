package com.devon1337.RPG.Utils;

import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.NPC.NPC;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

public class Dialog implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2423348137035693927L;
	int RightEdgeDistance_Text = 0;
	int RightEdgeDistance_Name = 0;
	String Code;
	String Message;
	@Getter @Setter
	NPC Npc;
	Response[] Responses = new Response[4];
	Dialog proceedDLog;
	ArrayList<DialogFlags> flags = new ArrayList<>();

	public Dialog(String Message, NPC Npc) {
		this.Message = Message;
		this.Npc = Npc;
	}

	public Dialog(String Code, String Message, Response[] Responses, NPC Npc) {
		Logging.OutputToConsole("Dialog: " + Code + " has been initialized!");
		this.Code = Code;
		this.Message = Message;
		this.Responses = Responses;
		this.Npc = Npc;
	}

	public Dialog getNextDialog() {
		return this.proceedDLog;
	}

	public int getREDText() {
		return this.RightEdgeDistance_Text;
	}

	public void setREDText(int RightEdgeDistance_text) {
		this.RightEdgeDistance_Text = RightEdgeDistance_text;
	}

	public int getREDName() {
		return this.RightEdgeDistance_Name;
	}

	public void setREDName(int RightEdgeDistance_name) {
		this.RightEdgeDistance_Name = RightEdgeDistance_name;
	}

	public String getCode() {
		return this.Code;
	}

	public String getMessage() {
		return this.Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}

	public void removeFlag(DialogFlags flag) {
		this.flags.remove(flag);
	}

	public void addFlag(DialogFlags flag) {
		this.flags.add(flag);
	}

	public ArrayList<DialogFlags> getFlags() {
		return this.flags;
	}
	
	public void writeObject(java.io.ObjectOutputStream stream) {
		try {
			stream.writeInt(RightEdgeDistance_Text);
			stream.writeInt(RightEdgeDistance_Name);
			stream.writeChars(Code);
			stream.writeChars(Message);
			stream.writeObject(Npc);
			stream.writeObject(Responses);
			stream.writeObject(flags);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}