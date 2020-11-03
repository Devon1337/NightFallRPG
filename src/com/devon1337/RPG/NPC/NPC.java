 package com.devon1337.RPG.NPC;
 
 import com.devon1337.RPG.Debugging.Logging;
 import com.devon1337.RPG.Utils.Dialog;

import java.io.IOException;
import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.UUID;
 import org.bukkit.entity.Player;
 
 
 public class NPC implements java.io.Serializable {
	 
   /**
	 * 
	 */
	private static final long serialVersionUID = -7280448406879255912L;
public String Code;
   public String Name;
   public Faction faction;
   public HashMap<UUID, Integer> Reputation = new HashMap<>();
   public HashMap<UUID, Dialog> CurDialog = new HashMap<>();
   public ArrayList<Dialog> DIALOG_LIST = new ArrayList<>();
   
   public NPC(String Code, String Name, ArrayList<Dialog> dialog) {
     this.Code = Code;
     this.Name = Name;
     
     for (int i = 0; i < dialog.size(); i++) {
       Logging.OutputToConsole("Dialog Code: " + ((Dialog)dialog.get(i)).getCode());
     }
     
     this.DIALOG_LIST = dialog;
   }
   
   public NPC(String Code, String Name, Faction faction, HashMap<UUID, Integer> Reputation, HashMap<UUID, Dialog> CurDialog,ArrayList<Dialog> dialog) {
	   this.Code = Code;
	   this.Name = Name;
	   this.faction = faction;
	   this.Reputation = Reputation;
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
   
   public boolean hasReputation(Player player) {
     return this.Reputation.containsKey(player.getUniqueId());
   }
   
   public boolean hasDialog(Player player) {
     return this.CurDialog.containsKey(player.getUniqueId());
   }
   
   public int getReputation(Player player) {
     if (hasReputation(player)) {
      return ((Integer)this.Reputation.get(player.getUniqueId())).intValue();
     }
     if (this.faction != null) {
       // TODO: Add reputation shit from new groupclass obj
       return ((Integer)this.Reputation.get(player.getUniqueId())).intValue();
     } 
     this.Reputation.put(player.getUniqueId(), Integer.valueOf(0));
     return 0;
   }
   
   public Faction getFaction() {
     return this.faction;
   }
   
   public void setFaction(Faction faction) {
     this.faction = faction;
   }
 
   
   public static void setReputation(Player player, int rep) {}
 
   
   public Dialog getCurDialog(Player player) {
     if (!this.CurDialog.containsKey(player.getUniqueId()) && this.DIALOG_LIST.size() > 0) setCurDialog(player, this.DIALOG_LIST.get(0)); 
     return this.CurDialog.get(player.getUniqueId());
   }
   
   public void setCurDialog(Player player, Dialog dlog) {
     Logging.OutputToConsole(String.valueOf(player.getName()) + " current dialog = " + dlog.getID());
     if (dlog != null) {
       this.CurDialog.remove(player.getUniqueId());
      this.CurDialog.put(player.getUniqueId(), dlog);
     } 
   }
   
   public void initializePlayerReputation(Player player, int rep) {}

	// I/O for NPC
	public void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		stream.writeObject(Code);
		stream.writeObject(Name);
		if(this.faction != null) {
		stream.writeObject(faction.Code);
		}
		stream.writeObject(Reputation);
		stream.writeObject(CurDialog);
		//stream.writeObject(DIALOG_LIST);
	}

	@SuppressWarnings("unchecked")
	public void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {

		String code = (String) stream.readObject(), name = (String) stream.readObject();
		Faction tempF = WorldController.getFaction((String) stream.readObject());
		HashMap<UUID, Integer> rep = (HashMap<UUID, Integer>) stream.readObject();
		HashMap<UUID, Dialog> curd = (HashMap<UUID, Dialog>) stream.readObject();
		ArrayList<Dialog> DL = (ArrayList<Dialog>) stream.readObject();

		new NPC(code, name, tempF, rep, curd, DL);

	}
   
 }