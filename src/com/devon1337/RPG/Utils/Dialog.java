package com.devon1337.RPG.Utils;

import com.devon1337.RPG.Debugging.Logging;

public class Dialog {

	public int ID, RightEdgeDistance_Text = 0, RightEdgeDistance_Name = 0;
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
	
	public void setREDText(int RightEdgeDistance_text) {
		this.RightEdgeDistance_Text = RightEdgeDistance_text;
	}
	
	public void setREDName(int RightEdgeDistance_name) {
		this.RightEdgeDistance_Name = RightEdgeDistance_name;
	}
	
}
