 package com.devon1337.RPG.Commands;
 
 import org.bukkit.ChatColor;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 
 
 
 
 
 public class UnlockGate
   implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
     Player player = (Player)sender;
     
     if (args.length <= 0)
     {
       
       player.sendMessage(ChatColor.DARK_RED + "Incorrect Usage: /trade {UserName}");
     }

    
     return false;
  }
}