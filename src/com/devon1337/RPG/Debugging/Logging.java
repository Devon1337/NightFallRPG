 package com.devon1337.RPG.Debugging;
 
 import org.bukkit.entity.Player;
 
 public class Logging
 {
   public static void OutputToPlayer(String Message, Player player) {
    player.sendMessage("[NFRPG-Verbose] " + Message);
   }
   
   public static void OutputToConsole(String Message) {
    System.out.println("[NFRPG-Verbose] " + Message);
   }
 }