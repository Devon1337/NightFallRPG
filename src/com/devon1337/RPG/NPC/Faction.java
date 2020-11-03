 package com.devon1337.RPG.NPC;
 
 import com.devon1337.RPG.Debugging.Logging;
 import java.util.ArrayList;
 import org.bukkit.entity.Player;
 
 public class Faction
 {
   public String Code;
   public String Name;
   public ArrayList<NPC> NPC_LIST = new ArrayList<>();
 
   
   public Faction(String Code, String Name) {
     this.Code = Code;
     this.Name = Name;
   }
   
   public void linkFaction(NPC npc) {
     npc.setFaction(this);
     Logging.OutputToConsole("Linking: " + npc.getCode() + " -> " + this.Code);
   }
   
   public void addNPC(NPC npc) {
     this.NPC_LIST.add(npc);
   }
   
   public ArrayList<NPC> getNPC() {
     ArrayList<NPC> temp = new ArrayList<>();
     
    for (int i = 0; i < this.NPC_LIST.size(); i++) {
       temp.add(this.NPC_LIST.get(i));
     }
     
    return temp;
   }
   
   public static void setReputation(Player player, int rep) {}
 }