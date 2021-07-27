package com.devon1337.RPG.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

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

}