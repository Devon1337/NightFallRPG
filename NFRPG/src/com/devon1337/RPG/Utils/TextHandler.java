package com.devon1337.RPG.Utils;


import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;



public class TextHandler {
	
	@SuppressWarnings("rawtypes")
	public void sendNewClickEvent(Player player, String cmd, String messages) {
		IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a("{\"text\":\"\",\"extra\":[{\"text\":\""
				+ messages + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + cmd + "\"}}]}");
		PacketPlayOutChat packet = new PacketPlayOutChat(comp, null, null);
		CraftPlayer p2 = (CraftPlayer) player;
		(p2.getHandle()).playerConnection.sendPacket((Packet) packet);
	}
}