 package com.devon1337.RPG.Commands;
 
 import com.devon1337.RPG.Utils.PartySystem;
 import org.bukkit.Bukkit;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 
 
 public class Party
   implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
     Player player = (Player)sender;
     
     if (args.length == 1 && args[0].equals("create") && !PartySystem.inParty(player)) {
       PartySystem.createParty(player);
       return true;
    }  if (args.length == 1 && args[0].equals("leave") && PartySystem.inParty(player)) {
       PartySystem.removeParty(player);
       return true;
     }  if (args.length == 2 && args[0].equals("invite") && PartySystem.inParty(player))
     { if (Bukkit.getPlayerExact(args[1]) != null) {
         PartySystem.sendInvite(player, Bukkit.getPlayerExact(args[1]));
         return true;
       }  }
     else { if (args.length == 1 && args[0].equals("join") && !PartySystem.inParty(player)) {
         PartySystem.acceptInvite(player);
         return true;
       }  if (args.length == 1 && args[0].equals("decline") && !PartySystem.inParty(player)) {
         PartySystem.declineInvite(player);
         return true;
       }  if (args.length == 1 && args[0].equals("list") && PartySystem.inParty(player)) {
        PartySystem.listParty(PartySystem.getId(player), player);
         return true;
       }  }
 
     
     return false;
   }
 }