package com.devon1337.RPG.Utils;

import com.devon1337.RPG.Debugging.Logging;

public class Dialog {

	public int ID;
	public String Code, Message, NPCName;
	public String[] Responses = new String[4];
	
	public Dialog(String Code, String Message, String[] Responses, int ID, String NPCName) {
		Logging.OutputToConsole("Dialog: " + Code + " has been initialized!");
		this.Code = Code;
		this.Message = Message;
		this.Responses = Responses;
		this.ID = ID;
		this.NPCName = NPCName;
	}
	
	
}
