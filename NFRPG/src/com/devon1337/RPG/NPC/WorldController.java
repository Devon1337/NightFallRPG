 package com.devon1337.RPG.NPC;
 
 import com.devon1337.RPG.Debugging.Logging;
 import java.util.ArrayList;
 import org.bukkit.entity.Player;
 
 
 
 public class WorldController
 {
   public static ArrayList<Faction> Factions = new ArrayList<>();
   public static ArrayList<NPC> StandAloneNPC = new ArrayList<>();
   public static ArrayList<NPC> AllNPC = new ArrayList<>();
   
   public static void initializeFaction(Faction faction) {
     Logging.OutputToConsole("Initializing Faction: " + faction.getFaction() + "!");
     Factions.add(faction);
   }
   
   public static void initializeNPC(NPC npc, Faction faction) {
     if (faction != null && getFaction(faction.getFaction()) != null) {
       faction.addNPC(npc);
      Faction tempFac = faction;
       tempFac.linkFaction(npc);
       AllNPC.add(npc);
     } else if (faction == null) {
       StandAloneNPC.add(npc);
       AllNPC.add(npc);
     } 
   }
   
   public static Faction getFaction(AllFactions fact) {
     for (int i = 0; i < Factions.size(); i++) {
       if (((Faction)Factions.get(i)).getFaction().equals(fact)) {
         return Factions.get(i);
       }
     } 
     
     return null;
   }
 
   
   public static void DisplayAllNPC(Player player) {
     int i;
     for (i = 0; i < Factions.size(); i++) {
       Logging.OutputToPlayer("Faction: " + ((Faction)Factions.get(i)).Name, player);
       for (int j = 0; j < ((Faction)Factions.get(i)).getNPC().size(); j++) {
         Logging.OutputToPlayer(((NPC)((Faction)Factions.get(i)).getNPC().get(j)).code.toString(), player);
       }
     } 
     
     Logging.OutputToPlayer("Factionless NPC:", player);
     
     if (StandAloneNPC.size() > 0)
       for (i = 0; i < StandAloneNPC.size(); i++) {
         Logging.OutputToPlayer(((NPC)StandAloneNPC.get(i)).code.toString(), player);
       } 
   }
   
   public static NPC npcExist(NPCCodes code) {
     int i;
     for (i = 0; i < Factions.size(); i++) {
       for (int j = 0; j < ((Faction)Factions.get(i)).getNPC().size(); j++) {
         if (((NPC)((Faction)Factions.get(i)).getNPC().get(j)).code.equals(code)) {
           return ((Faction)Factions.get(i)).getNPC().get(j);
         }
       } 
     } 
     
     for (i = 0; i < StandAloneNPC.size(); i++) {
       if (((NPC)StandAloneNPC.get(i)).code.equals(code)) {
         return StandAloneNPC.get(i);
       }
     } 
     return null;
   }
   
 }
