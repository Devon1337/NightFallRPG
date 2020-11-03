 package com.devon1337.RPG.Quests;
 
 import com.devon1337.RPG.Commands.NFQuest;
 import com.devon1337.RPG.Debugging.Logging;
 import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;
 import com.devon1337.RPG.Utils.PartySystem;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.UUID;
 import org.bukkit.ChatColor;
 import org.bukkit.Sound;
 import org.bukkit.entity.Player;
 
 
 
 public class QuestTracker
 {
   public static HashMap<UUID, ArrayList<Quest>> questList = new HashMap<>();
  public static ArrayList<Quest> GLOBAL_QUEST_LIST = new ArrayList<>();
 
   
   public void addQuest(Player player, Quest quest) {
     if (QuestListContains(GLOBAL_QUEST_LIST, quest.getCode())) {
       
       if (quest.QuestID == 4) {
         BookRogueQuest.completeStep(0, player, quest);
       }
       
       if (PartySystem.inParty(player)) {
        for (int i = 0; i < PartySystem.getParty(PartySystem.getId(player)).size(); i++) {
           Player target = PartySystem.getParty(PartySystem.getId(player)).get(i);
           if (player != target) {
             target.sendMessage(ChatColor.DARK_GRAY + player.getName() + " has accepted " + quest.getTitle() + "!");
           }
         } 
       }
       
       if (getQuests(player) == null) {
         questList.put(player.getUniqueId(), fixAL(new ArrayList<>(), quest));
       } else {
         questList.put(player.getUniqueId(), fixAL(getQuests(player), quest));
       } 
       
       playersQuest(quest.QuestID, player).setPlayer(player);
       playersQuest(quest.QuestID, player).setCurSteps(0);
       System.out.println("Player: " + player.getName() + " has accepted questID: " + quest.QuestID);
       player.sendMessage(ChatColor.LIGHT_PURPLE + "Quest acquired: " + ChatColor.GOLD + ChatColor.BOLD + quest.Title);
       player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
       NFQuest.addPlayer(player);
     } else {
       try {
         initQuest(quest);
       } catch (QuestIDInUse e) {
         
         e.printStackTrace();
       } 
     } 
   }
 
 
 
   
   public static void generateQuestMetaData() {}
 
 
 
   
   public static boolean QuestListContains(ArrayList<Quest> GQLCode, String CompareCode) {
     for (int i = 0; i < GQLCode.size(); i++) {
       if (((Quest)GQLCode.get(i)).getCode().equals(CompareCode)) {
        return true;
       }
     } 
     
     return false;
   }
   
   public static Quest grabQuest(int QuestID) {
     for (int i = 0; i < GLOBAL_QUEST_LIST.size(); i++) {
       if (((Quest)GLOBAL_QUEST_LIST.get(i)).QuestID == QuestID) {
        return GLOBAL_QUEST_LIST.get(i);
       }
     } 
     return null;
   }
   
   @SuppressWarnings("rawtypes")
public static boolean hasQuestID(int ID, Player player) {
     for (int i = 0; i < ((ArrayList)questList.get(player.getUniqueId())).size(); i++) {
       if (((Quest)((ArrayList)questList.get(player.getUniqueId())).get(i)).QuestID == ID) {
         return true;
       }
     } 
     return false;
   }
   
   @SuppressWarnings("rawtypes")
public static Quest playersQuest(int ID, Player player) {
     for (int i = 0; i < ((ArrayList)questList.get(player.getUniqueId())).size(); i++) {
       if (((Quest)((ArrayList)questList.get(player.getUniqueId())).get(i)).QuestID == ID) {
         return ((ArrayList<Quest>)questList.get(player.getUniqueId())).get(i);
       }
       Logging.OutputToConsole("Player Quest: " + questList.get(player.getUniqueId()));
     } 
     
     return null;
   }
   
   public static boolean hasQuest(Player player, Quest quest) {
     if (questList.containsKey(player.getUniqueId()) && hasQuestID(quest.QuestID, player)) {
       return true;
     }
     return false;
   }
   
   public ArrayList<Quest> fixAL(ArrayList<Quest> arr, Quest val) {
     arr.add(val);
     return arr;
   }
 
   
   public static ArrayList<Quest> getQuests(Player player) {
     return questList.get(player.getUniqueId());
   }
   
   @SuppressWarnings("rawtypes")
public static void displayAllPlayerQuests(Player player) {
     for (int i = 0; i < ((ArrayList)questList.get(player.getUniqueId())).size(); i++) {
       Logging.OutputToConsole("Quest: " + ((Quest)((ArrayList<Quest>)questList.get(player.getUniqueId())).get(i)).getTitle() + " Quest Step 1: " + ((Quest)((ArrayList<Quest>)questList.get(player.getUniqueId())).get(i)).getStep(0));
     }
   }
 
   
   public static void initQuest(Quest quest) throws QuestIDInUse {
     if (GLOBAL_QUEST_LIST.contains(quest)) {
       throw new QuestIDInUse(
           "NFRPG Exception: The QuestID: " + quest.QuestID + " is already in use! please reassign the ID.");
     }
     Logging.OutputToConsole("Quest Stats: Name: " + quest.Title + " ID: " + quest.Code + " Step 1: " + quest.getStep(0));
     GLOBAL_QUEST_LIST.add(quest);
   }
 
   
   public static void nextUpdateStep(int QuestID, int Step, Player player) {
     if (hasQuest(player, playersQuest(QuestID, player)))
       switch (QuestID) {
 
       case 2:
    	   NavigationQuest.completeStep(Step, player, playersQuest(QuestID, player));
    	   break;
    	   
         
         case 5:
           MooshroomQuest.completeStep(Step - 1, player, playersQuest(QuestID, player));
           break;
       }  
   }
 }