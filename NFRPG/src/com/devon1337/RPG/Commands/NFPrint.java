 package com.devon1337.RPG.Commands;
 
 import org.bukkit.ChatColor;
 import org.bukkit.Sound;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;

import com.devon1337.RPG.Utils.Dialog.DialogueSystem;
 
 
 public class NFPrint
   implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
    Player player = (Player)sender;
     
     if (sender instanceof Player && args.length == 1) {
       if (DialogueSystem.hasDialog(player)) {
         DialogueSystem.removeDialog(player);
         String message = args[0];
         message = message.replace('_', ' ');
         player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1.0F, 1.3F);
        player.sendMessage("");
         player.sendMessage(ChatColor.LIGHT_PURPLE + message);
        return true;
       } 
       player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "You had waited too long...");
       return true;
     } 
 
     
     return false;
   }
 }
