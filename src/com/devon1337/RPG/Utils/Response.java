 package com.devon1337.RPG.Utils;
 
 import com.devon1337.RPG.NPC.Faction;
 
 public class Response implements java.io.Serializable {
	 
   /**
	 * 
	 */
	private static final long serialVersionUID = 1111953368683528207L;
String cmd;
   String message;
   int requiredAmount;
   Faction sidedFaction;
   Dialog DLog;
   
   public Response(String cmd, String message, int requiredAmount, Faction sidedFaction, Dialog DLog) {
     this.cmd = cmd; this.message = message; this.requiredAmount = requiredAmount; this.sidedFaction = sidedFaction; this.DLog = DLog;
   }
   
   public String getMessage() {
    return this.message;
   }
   public void setMessage(String message) {
    this.message = message;
   }
   
   public String getCMD() {
     return this.cmd;
   }
   
   public int getRA() {
     return this.requiredAmount;
   }
   
   public Faction getFaction() {
     return this.sidedFaction;
   }
   
   public Dialog getNextDialog() {
     return this.DLog;
   }
 }