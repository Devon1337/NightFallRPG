 package com.devon1337.RPG.Commands;
 
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;

import com.devon1337.RPG.Player.AccountFlags;
import com.devon1337.RPG.Player.NFPlayer;
 
 
 public class NFTest
   implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
     Player player = (Player)sender;
     
     if (sender instanceof Player) {
    	 NFPlayer p2 = NFPlayer.getPlayer(player.getUniqueId());
    	 if(p2.getFlags().contains(AccountFlags.Developer)) {
    		 p2.getFlags().remove(AccountFlags.Developer);
    		 player.sendMessage("Hewwo mwister devewuper =UwU=");
    	 } else {
    		 p2.getFlags().add(AccountFlags.Developer);
    		 player.sendMessage("Goobye mwister =UwU=");
    	 }
    	 
    	 return true;
     } 
     
     return false;
   }
 }