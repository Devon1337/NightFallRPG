 package com.devon1337.RPG.Commands;
 
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 
 
 public class NFTest
   implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
     //Player player = (Player)sender;
     
     if (sender instanceof Player) {
       return true;
     } 
     
     return false;
   }
 }