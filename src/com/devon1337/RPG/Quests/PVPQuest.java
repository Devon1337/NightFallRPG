 package com.devon1337.RPG.Quests;
 
 import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;
 import org.bukkit.entity.Player;
 
 public class PVPQuest
   extends Quest
 {
   public final int STEP_AMOUNT = 5; public static final String TITLE = "Clearing the Spider's Nest";
   public static final String DESCRIPTION = "Slay 5 players in PVP";
   public static final String CODE = "WARRIOR_QUEST_1";
   public static final String[] RESPONSES = new String[] { "Test A", "Test B", "Test C", "Test D" };
   public static final int QUESTID = 3;
   
   public PVPQuest(int XP_Amount, int Gold_Amount, Player player, int Status, int StepAmount) throws QuestIDInUse {
    super(3, "Clearing the Spider's Nest", "Slay 5 players in PVP", "WARRIOR_QUEST_1", RESPONSES, XP_Amount, Gold_Amount, player, Status, StepAmount);
   }
   public static final int XP_Amount = 100; public static final int Gold_Amount = 100;
   
   public String getTitle() {
     return "Clearing the Spider's Nest";
   }
   
   public static String getDescription() {
     return "Slay 5 players in PVP";
   }
   
   public String getCode() {
     return "WARRIOR_QUEST_1";
   }
   
   public static String[] getResponses() {
     return RESPONSES;
   }
   
   public static int getQuestID() {
     return 3;
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
         player.sendMessage("You have killed 1/5 Players!");
         quest.setCurSteps(1);
         break;
       case 1:
         player.sendMessage("You have killed 2/5 Players!");
         quest.setStatus(2);
         break;
       case 2:
         player.sendMessage("You have killed 3/5 Players!");
         quest.setCurSteps(3);
         break;
       case 3:
         player.sendMessage("You have killed 4/5 Players!");
         quest.setCurSteps(4);
         break;
       case 4:
         player.sendMessage("You have killed 5/5 Players!");
        quest.setCurSteps(5);
         break;
     } 
   }
 }