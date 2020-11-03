 package com.devon1337.RPG.Quests;
 
 import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;
 import org.bukkit.entity.Player;
 
 public class TestQuest extends Quest {
   public static final String TITLE = "First Quest";
   public static final String DESCRIPTION = "DEV_TEST_1";
   public static final String CODE = "DEV_TEST_1";
   public static final String[] RESPONSES = new String[] { "Test A", "Test B", "Test C", "Test D" };
   public static final int QUESTID = 1;
   
   public TestQuest(int XP_Amount, int Gold_Amount, Player player, int Status, int StepAmount) throws QuestIDInUse {
     super(1, "First Quest", "DEV_TEST_1", "DEV_TEST_1", RESPONSES, XP_Amount, Gold_Amount, player, Status, StepAmount);
   }
   public static final int XP_Amount = 100; public static final int Gold_Amount = 100;
   
   public String getTitle() {
     return "First Quest";
   }
   
   public static String getDescription() {
     return "DEV_TEST_1";
   }
   
   public String getCode() {
     return "DEV_TEST_1";
   }
   
   public static String[] getResponses() {
     return RESPONSES;
   }
   
   public static int getQuestID() {
     return 1;
   }
   
   public static int getXPAmount() {
     return 100;
   }
   
   public static int getGoldAmount() {
     return 100;
   }
 }