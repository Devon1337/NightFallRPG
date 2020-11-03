 package com.devon1337.RPG.Quests;
 
 import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;
 import com.devon1337.RPG.Utils.InventoryAssistant;
 import java.util.ArrayList;
 import org.bukkit.Material;
 import org.bukkit.entity.Player;
 import org.bukkit.inventory.ItemFlag;
 import org.bukkit.inventory.ItemStack;
 import org.bukkit.inventory.meta.ItemMeta;
 
 
 public class BookRogueQuest
   extends Quest
 {
   public static final String TITLE = "Babis first leap";
   public static final String DESCRIPTION = "Deliver the book to ROGUE_CITIZEN_4";
   public static final String CODE = "ROGUE_QUEST_1";
   public static final String[] STEPS = new String[] { "Leap into the abyss", "Find and talk to ROGUE_CITIZEN_4." };
   public static final int QUESTID = 4;
   
   public BookRogueQuest(int XP_Amount, int Gold_Amount, Player player, int Status) throws QuestIDInUse {
     super(4, "Babis first leap", "Deliver the book to ROGUE_CITIZEN_4", "ROGUE_QUEST_1", STEPS, XP_Amount, Gold_Amount, player, Status, STEPS.length);
   }
   public static final int XP_Amount = 100; public static final int Gold_Amount = 100;
   
   public String getTitle() {
     return "Babis first leap";
   }
   
   public static String getDescription() {
     return "Deliver the book to ROGUE_CITIZEN_4";
   }
   
   public String getCode() {
     return "ROGUE_QUEST_1";
   }
   
   public static int getQuestID() {
     return 4;
   }
   
   public static int getXPAmount() {
     return 100;
   }
   
   public static int getGoldAmount() {
     return 100;
   }
   
   public static void completeStep(int stepNumber, Player player, Quest quest) {
     switch (stepNumber) {
       case 0:
         if (InventoryAssistant.hasFreeSlot(player)) {
           player.getInventory().setItem(InventoryAssistant.getFirstFreeSlot(player), createGuiItem(Material.BOOK, 1, "Letter to ROGUE_CITIZEN4", new String[] { "" }));
         }
         player.sendMessage(STEPS[0]);
         quest.setCurSteps(1);
         break;
       case 1:
        player.sendMessage("You have completed the quest");
         quest.setStatus(2);
         break;
     } 
   }
   
   public static ItemStack createGuiItem(Material material, int amount, String name, String... lore) {
     ItemStack item = new ItemStack(material, amount);
     
     ItemMeta meta = item.getItemMeta();
     
     meta.setDisplayName(name);
     ArrayList<String> metalore = new ArrayList<>(); byte b; int i;
     String[] arrayOfString;
     for (i = (arrayOfString = lore).length, b = 0; b < i; ) { String lorecomments = arrayOfString[b];
       metalore.add(lorecomments);
       b++; }
     
     meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
     
     meta.setLore(metalore);
     item.setItemMeta(meta);
    return item;
   }
   
   public Quest getQuest() {
     return this;
   }
 }