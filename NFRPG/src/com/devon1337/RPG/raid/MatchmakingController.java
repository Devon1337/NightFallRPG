 package com.devon1337.RPG.raid;
 
 import com.devon1337.RPG.Debugging.Logging;
 import java.util.ArrayList;
 import java.util.HashMap;
 import org.bukkit.entity.Player;
 
 
 
 public class MatchmakingController
 {
   static HashMap<Player, Raid> currentQueue = new HashMap<>();
   static ArrayList<Raid> availableRaids = new ArrayList<>();
   
   private static int i = 0;
   
   public static void addToQueue(Player player, Raid raid) {
     currentQueue.put(player, raid);
   }
   
   public static void addRaid(Raid raid) {
     availableRaids.add(raid);
   }
   
   public static Raid getRaid(NFRaid raid) {
     for (int i = 0; i < availableRaids.size(); i++) {
       if (((Raid)availableRaids.get(i)).getEnum() == raid) {
        return availableRaids.get(i);
       }
     } 
     return null;
   }
   
   public static void checkRaids() {
     Logging.OutputToConsole("Running Matchmaking queue.");
     for (i = 0; i < availableRaids.size(); i++) {
       Logging.OutputToConsole("Player Size: " + (((Raid)availableRaids.get(i)).getPlayers()).length + " isEnabled: " + ((Raid)availableRaids.get(i)).isEnabled());
       if ((((Raid)availableRaids.get(i)).getPlayers()).length < 5 && ((Raid)availableRaids.get(i)).isEnabled())
         currentQueue.forEach((key, value) -> {
               Logging.OutputToConsole("Key: " + key + " Value: " + value);
               if (value.getName().equals(((Raid)availableRaids.get(i)).getName()))
                 ((Raid)availableRaids.get(i)).addPlayer(key); 
             }); 
     } 
   }
 }