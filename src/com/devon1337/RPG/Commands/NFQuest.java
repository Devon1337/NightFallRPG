 package com.devon1337.RPG.Commands;
 
 import com.devon1337.RPG.Debugging.Logging;
 import com.devon1337.RPG.Quests.Quest;
 import com.devon1337.RPG.Quests.QuestTracker;
 import java.util.ArrayList;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.Material;
 import org.bukkit.command.Command;
 import org.bukkit.command.CommandExecutor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.entity.Player;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.BookMeta;
 import org.bukkit.inventory.meta.ItemMeta;
 
 public class NFQuest
   implements CommandExecutor
 {
   QuestTracker qt = new QuestTracker();
   public static ArrayList<Player> qbUpdate = new ArrayList<>();
   
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = null;
		int QuestID = 0, Stage = 0;
		if (sender instanceof Player) {
			player = (Player) sender;

			if (args.length > 0) {
				QuestID = Integer.parseInt(args[0]);
			}

			switch (args.length) {
			case 0:
				createQuestBook(player);
				return true;
			case 1:
				qt.addQuest(player, QuestTracker.grabQuest(QuestID));
				return true;
			case 2:
				Stage = Integer.parseInt(args[1]);
				if (QuestTracker.hasQuest(player, QuestTracker.grabQuest(QuestID))) {
					QuestTracker.nextUpdateStep(QuestID, Stage, player);
				}
				return true;
			}

		} else if (!(sender instanceof Player) && args.length >= 2) {
			player = Bukkit.getPlayerExact(args[0]);
			QuestID = Integer.parseInt(args[1]);

			if (args.length == 3 && QuestTracker.hasQuest(player, QuestTracker.grabQuest(QuestID))) {
				Stage = Integer.parseInt(args[2]);
				QuestTracker.nextUpdateStep(QuestID, Stage, player);
			} else if (args.length < 3) {
				qt.addQuest(player, QuestTracker.grabQuest(QuestID));
			} else if (!QuestTracker.hasQuest(player, QuestTracker.grabQuest(QuestID))) {
				Logging.OutputToConsole("Player does not have required quest!");
			}
		}
		return false;
	}
 
   
   public static void generateNewQuestBook(Player player) {
    if (qbUpdate.contains(player)) {
       player.getInventory().setItem(2, createQuestBook(player));
      qbUpdate.remove(player);
     } 
   }
   
   public static void addPlayer(Player player) {
     if (!qbUpdate.contains(player)) {
       qbUpdate.add(player);
     }
   }
 
   
   public static ItemStack createQuestBook(Player player) {
     QuestTracker.displayAllPlayerQuests(player);
     ArrayList<String> pages = new ArrayList<>();
     int i;
     for (i = 0; i < QuestTracker.getQuests(player).size(); i++) {
       String message = "\n\n";
 
       
      for (int j = 0; j < ((Quest)QuestTracker.getQuests(player).get(i)).getStepAmount(); j++) {
         if (((Quest)QuestTracker.getQuests(player).get(i)).CurSteps > i || ((Quest)QuestTracker.getQuests(player).get(i)).getStatus() == 1) {
           Logging.OutputToConsole("Quest: " + ((Quest)QuestTracker.getQuests(player).get(i)).getTitle() + " Step 1: " + ((Quest)QuestTracker.getQuests(player).get(i)).getStep(0));
           
           message = String.valueOf(message) + ChatColor.GRAY + "- " + ((Quest)QuestTracker.getQuests(player).get(i)).getStep(j) + "\n";
         } else if (((Quest)QuestTracker.getQuests(player).get(i)).CurSteps < i) {
           Logging.OutputToConsole("Quest: " + ((Quest)QuestTracker.getQuests(player).get(i)).getTitle() + " Step 1: " + ((Quest)QuestTracker.getQuests(player).get(i)).getStep(0));
           message = String.valueOf(message) + ChatColor.LIGHT_PURPLE + "- " + ((Quest)QuestTracker.getQuests(player).get(i)).getStep(j) + "\n";
         } 
       } 
       
      Logging.OutputToConsole("Current Message: " + message);
       
       if (((Quest)QuestTracker.getQuests(player).get(i)).Status == 1) {
         pages.add(ChatColor.GOLD + ((Quest)QuestTracker.getQuests(player).get(i)).getTitle() + "\n\n" + 
             ChatColor.DARK_PURPLE + ((Quest)QuestTracker.getQuests(player).get(i)).Description + message);
       }
     } 
 
 
 
     
     for (i = 0; i < QuestTracker.getQuests(player).size(); i++) {
       if (((Quest)QuestTracker.getQuests(player).get(i)).Status == 2) {
         pages.add(ChatColor.GRAY + ((Quest)QuestTracker.getQuests(player).get(i)).getTitle() + "\n\n" + ChatColor.GRAY + 
             ((Quest)QuestTracker.getQuests(player).get(i)).Description);
       }
     } 
 
 
 
 
     
     ArrayList<String> tx = new ArrayList<>();
     tx.add("dont-hide");
     
    ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
     BookMeta bookMeta = (BookMeta)writtenBook.getItemMeta();
     bookMeta.setTitle("Quests");
     bookMeta.setAuthor("NightFallRPG");
     bookMeta.setLore(tx);
     bookMeta.setPages(pages);
     writtenBook.setItemMeta((ItemMeta)bookMeta);
     
     return writtenBook;
   }
 }