package com.devon1337.RPG.Utils;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutChat;
import net.minecraft.server.v1_15_R1.IChatBaseComponent.ChatSerializer;

public class TextHandler {

	public void sendNewClickEvent(Player player, String cmd, String messages) {
		
			
			IChatBaseComponent comp = ChatSerializer
					.a("{\"text\":\"" + "\",\"extra\":[{\"text\":\"" + messages + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + cmd + "\"}}]}");
			PacketPlayOutChat packet = new PacketPlayOutChat(comp);
			CraftPlayer p2 = (CraftPlayer) player;
			p2.getHandle().playerConnection.sendPacket(packet);
		
		
		
	}
	
}
