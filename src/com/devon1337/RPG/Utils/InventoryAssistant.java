 package com.devon1337.RPG.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.Commands.NFQuest;
import com.devon1337.RPG.Player.NFPlayer;
 
 public class InventoryAssistant {
   public static int getInventorySize(int RowCount) {
     return 9 * RowCount;
   }
   public static boolean hasFreeSlot(Player player) {
     PlayerInventory playerInventory = player.getInventory();
     
     for (int i = 0; i < (playerInventory.getStorageContents()).length; i++) {
       if (playerInventory.getStorageContents()[i] == null) {
         return true;
       }
     } 
     return false;
   }
 
   
   public static int getFirstFreeSlot(Player player) {
     PlayerInventory playerInventory = player.getInventory();
     
     for (int i = 0; i < (playerInventory.getStorageContents()).length; i++) {
       if (playerInventory.getStorageContents()[i] == null) {
         return i;
       }
     } 
     return -1;
   }
   
   // Assigning Slots
   // We can assume slots 0-3 will be empty.
   public static void initializeInventory(Player player) {
	     PlayerInventory playerInventory = player.getInventory();
	     
	     // Spell GUI Init
	     for(int i = 0; i < 3; i++) {
	     playerInventory.setItem(i, GlobalSpellbook.getSpells().get(15).getItem());
	     NFPlayer.getPlayer(player.getUniqueId()).setSpells(i, GlobalSpellbook.getSpells().get(15)); 
	     }
	     
	     // Quest Book Init
	     NFQuest.createQuestBook(player);
   }

   
   
 }