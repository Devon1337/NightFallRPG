package com.devon1337.RPG.Objects.EasterEggs;

import com.devon1337.RPG.Utils.Dialog;
import com.devon1337.RPG.Utils.DialogFlags;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import org.bukkit.entity.Player;

public class CappaTheShopStand {
	private static Dialog[] dialog_list;
	private static HashMap<UUID, Integer> pissedMeter = new HashMap<>();

	public static void init_dialog() {
		ArrayList<Dialog> DIALOG_LIST = new ArrayList<>();

		dialog_list = new Dialog[DIALOG_LIST.size()];
		for (int i = 0; i < DIALOG_LIST.size(); i++) {
			dialog_list[i] = DIALOG_LIST.get(i);
			dialog_list[i].addFlag(DialogFlags.MESSAGE);
		}
		DIALOG_LIST.removeAll(DIALOG_LIST);
	}

	public static Dialog talkTo(Player player) {
		Random rand = new Random();
		if (pissedMeter.containsKey(player.getUniqueId())) {
			switch (((Integer) pissedMeter.get(player.getUniqueId())).intValue()) {
			case 1:
				pissedMeter.replace(player.getUniqueId(),
						Integer.valueOf(((Integer) pissedMeter.get(player.getUniqueId())).intValue() + 1));
				return dialog_list[5];
			case 2:
				pissedMeter.replace(player.getUniqueId(),
						Integer.valueOf(((Integer) pissedMeter.get(player.getUniqueId())).intValue() + 1));
				return dialog_list[6];
			case 3:
				return dialog_list[rand.nextInt(4)];
			}
			return null;
		}

		pissedMeter.put(player.getUniqueId(), Integer.valueOf(1));
		return dialog_list[4];
	}
}