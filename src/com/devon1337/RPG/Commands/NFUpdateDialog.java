 package com.devon1337.RPG.Commands;
 
 import com.devon1337.RPG.Debugging.Logging;
 import com.devon1337.RPG.NPC.NPC;
 import com.devon1337.RPG.NPC.WorldController;
 import com.devon1337.RPG.NightFallRPG;
 import com.devon1337.RPG.Utils.Dialog;
 import com.devon1337.RPG.Utils.DialogFlags;
 import com.devon1337.RPG.Utils.DialogueSystem;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 import org.bukkit.scheduler.BukkitScheduler;
 
 
 
 
 
 public class NFUpdateDialog
   implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
     if (args.length >= 4) {
       Logging.OutputToConsole("Ran");
       final NPC npc = WorldController.npcExist(args[0]);
       String message = args[1].replace("_", " ");
       final Player player = Bukkit.getPlayerExact(args[2]);
       Dialog nextDialog = DialogueSystem.getDialog(Integer.parseInt(args[3]));
 
       
       player.sendMessage(ChatColor.LIGHT_PURPLE + message);
       
       if (npc.getCurDialog(player).getFlags().contains(DialogFlags.CONTINUE)) {
         BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
         npc.setCurDialog(player, nextDialog);
         Logging.OutputToConsole("Continued: " + nextDialog.getCode());
        scheduler.scheduleSyncDelayedTask(NightFallRPG.getPlugin(), new Runnable()
             {
               public void run() {
                DialogueSystem.removeDialog(player);
                 DialogueSystem.DisplayDialog(player, npc.getCurDialog(player), npc);
                Logging.OutputToConsole("Continue Ran");
               }
             }, 40L);
         
         return true;
       } 
       
       npc.setCurDialog(player, nextDialog);
       return true;
     } 
 
 
     
    return false;
   }
 }