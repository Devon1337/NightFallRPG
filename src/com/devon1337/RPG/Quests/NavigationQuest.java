 package com.devon1337.RPG.Quests;
 
 import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;
 import org.bukkit.entity.Player;
 
 public class NavigationQuest
   extends Quest
 {
   public static final String TITLE = "The Dark Pit";
   public static final String DESCRIPTION = "Find and Locate the stonefall abyss!";
   public static final String CODE = "DEV_TEST_2";
   public static final String[] STEPS = new String[] { "Find the entrance to Stonefall Abyss", "Talk to ROGUE_WELCOME_1" };
   public static final int QUESTID = 2;
   
   public NavigationQuest(int XP_Amount, int Gold_Amount, Player player, int Status) throws QuestIDInUse {
     super(2, "The Dark Pit", "Find and Locate the stonefall abyss!", "DEV_TEST_2", STEPS, XP_Amount, Gold_Amount, player, Status, STEPS.length);
   }
   public static final int XP_Amount = 100; public static final int Gold_Amount = 100;
   
   public String getTitle() {
     return "The Dark Pit";
   }
   
   public static String getDescription() {
     return "Find and Locate the stonefall abyss!";
   }
   
   public String getCode() {
     return "DEV_TEST_2";
   }
   
   public static int getQuestID() {
     return 2;
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
         player.sendMessage("Talk to ROGUE_WELCOME1");
         quest.setCurSteps(1);
         break;
       case 1:
         player.sendMessage("You have completed the quest");
         quest.setStatus(2);
         break;
     } 
   }
   
   public Quest getQuest() {
     return this;
   }
 }