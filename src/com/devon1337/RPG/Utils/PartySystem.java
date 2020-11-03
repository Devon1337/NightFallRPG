 package com.devon1337.RPG.Utils;
 
 import com.devon1337.RPG.NightFallRPG;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Random;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.entity.Player;
 import org.bukkit.scheduler.BukkitScheduler;
 
 
 
 
 public class PartySystem
 {
   public static HashMap<Player, String> partyList = new HashMap<>();
   public static HashMap<Player, String> inviteQueue = new HashMap<>();
   public static final String letters = "abcdefghijklmnopqrstuvwxyz1234567890";
   public static final int ID_LENGTH = 8;
   public static Random rand = new Random();
   public static TextHandler texthandler = new TextHandler();
   
   public static void createParty(Player player) {
     String id = generateID();
     partyList.put(player, id);
     player.sendMessage("You have started a party! key - " + id);
   }
   
   public static void removeParty(Player player) {
     partyList.remove(player);
     player.sendMessage("You have left the party!");
   }
 
 
   
   public static String generateID() {
     String id = "";
     boolean done = false;
     
     while (!done) {
       for (int i = 0; i < 8; i++) {
         id = String.valueOf(id) + "abcdefghijklmnopqrstuvwxyz1234567890".charAt(rand.nextInt("abcdefghijklmnopqrstuvwxyz1234567890".length()));
       }
      if (!partyList.containsValue(id)) {
         done = true;
       }
     } 
     
     return id;
   }
   
   public static void sendInvite(final Player player, final Player target) {
     if (!inParty(target)) {
       BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
       inviteQueue.put(target, getId(player));
      target.sendMessage(String.valueOf(player.getName()) + " has invited you to a party! click to accept/deny!");
       
       texthandler.sendNewClickEvent(target, "/nfparty join", ChatColor.GREEN + "[Join]");
       texthandler.sendNewClickEvent(target, "/nfparty decline", ChatColor.DARK_RED + "[Decline]");
       
       target.sendMessage(" ");
       
       player.sendMessage("You have invited " + target.getName());
       
       scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable()
           {
             public void run() {
              if (PartySystem.inviteQueue.containsKey(player)) {
                 PartySystem.inviteQueue.remove(target);
                 target.sendMessage("Invite has expired!");
                 player.sendMessage("Invite has expired!");
               } 
             }
          },  600L);
     } else {
       player.sendMessage(ChatColor.DARK_RED + target.getName() + " is already in a party!");
     } 
   }
   
   public static void declineInvite(Player player) {
     if (inviteQueue.containsKey(player)) {
       
      ArrayList<Player> cur_party = getParty(inviteQueue.get(player));
       for (int i = 0; i < cur_party.size(); i++) {
         ((Player)cur_party.get(i)).sendMessage(ChatColor.DARK_RED + player.getName() + " has declined the party invite!");
       }
      player.sendMessage("You have declined the invite!");
       inviteQueue.remove(player);
     } 
   }
   
   public static void acceptInvite(Player player) {
    if (inviteQueue.containsKey(player)) {
      partyList.put(player, inviteQueue.get(player));
      inviteQueue.remove(player);
       
       player.sendMessage("You have joined the party!");
       ArrayList<Player> cur_party = getParty(partyList.get(player));
       for (int i = 0; i < cur_party.size(); i++) {
        ((Player)cur_party.get(i)).sendMessage(String.valueOf(player.getName()) + " has joined the party!");
       }
     } 
   }
   
   public static boolean inParty(Player player) {
     return partyList.containsKey(player);
   }
 
   
   public static void listParty(String id, Player player) {
     ArrayList<Player> cur_party = getParty(id);
     for (int i = 0; i < cur_party.size(); i++) {
       if (i == 0) {
         player.sendMessage("--- Players ---");
       }
       player.sendMessage((new StringBuilder(String.valueOf(((Player)cur_party.get(i)).getName()))).toString());
     } 
   }
 
   
   public static ArrayList<Player> getParty(String id) {
     ArrayList<Player> players = new ArrayList<>();
     
     for (Map.Entry<Player, String> entry : partyList.entrySet()) {
       
       if (((String)entry.getValue()).equals(id)) {
         players.add(entry.getKey());
       }
     } 
 
     
     return players;
   }
   
   public static String getId(Player player) {
     if (partyList.containsKey(player)) {
      return partyList.get(player);
     }
     return "";
   }
 }