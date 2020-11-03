 package com.devon1337.RPG.Commands;
 
 import com.devon1337.RPG.NPC.NPC;
 import com.devon1337.RPG.Utils.Dialog;
 import com.devon1337.RPG.Utils.DialogFlags;
 import com.devon1337.RPG.Utils.DialogueSystem;
 import java.util.HashMap;
 import java.util.UUID;
 import org.bukkit.Bukkit;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 
 
 
 public class NFMessage
   implements CommandExecutor
 {
   public static HashMap<UUID, UUID> currentReply = new HashMap<>();
 
   
   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
     Player player = (Player)sender;
     Player target = Bukkit.getPlayerExact(args[0]);
    String msg = "";
     for (int i = 1; i < args.length; i++) {
       msg = String.valueOf(msg) + " " + args[i];
     }
     if (sender instanceof Player && args.length >= 2 && target != null) {
       Dialog dlog = new Dialog(msg, player.getName());
       dlog.addFlag(DialogFlags.MESSAGE);
       dlog.addFlag(DialogFlags.FORCE_LEFT);
       DialogueSystem.DisplayDialog(player, dlog, new NPC(player.getName()));
       dlog.removeFlag(DialogFlags.FORCE_LEFT);
      DialogueSystem.DisplayDialog(target, dlog, new NPC(player.getName()));
       currentReply.put(player.getUniqueId(), target.getUniqueId());
       currentReply.put(target.getUniqueId(), player.getUniqueId());
       return true;
     } 
 
 
     
    return false;
   }
   
   public static Player getCurrentReply(Player player) {
     if (currentReply.containsKey(player.getUniqueId())) {
       return Bukkit.getPlayer(currentReply.get(player.getUniqueId()));
     }
    return null;
   }
 }
