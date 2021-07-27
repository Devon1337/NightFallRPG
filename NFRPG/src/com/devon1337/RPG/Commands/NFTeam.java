package com.devon1337.RPG.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.Classes.GroupClass;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.QuestTags;
import com.devon1337.RPG.Quests.Step;
import com.devon1337.RPG.Quests.StepStatus;

public class NFTeam implements CommandExecutor {
	
	
	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		Player player = (Player) sender;
		NFPlayer p1 = NFPlayer.getPlayer(player.getUniqueId());
		Step s = null;
		
		// Completes the first quest
		for (Quest q : p1.getQuests()) {
			if (q.getTag().equals(QuestTags.START_ZONE_SELECT_CLASS)) {
				s = q.getSteps().get(1);
				s.setStatus(StepStatus.Completed);
			}
		}

		if (sender instanceof Player && args.length == 2) {
			Quest q = null;
			switch (args[0]) {
			case "druid":
				
				// Sets the previous step's title and description
//				s.setTitle(ChatColor.GREEN + "Stories about the world tree");
//				s.setDescription("You aligned with the druids");
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp druid " + p1.getPlayer().getName());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p1.getPlayer().getName() + " parent add druid");
				
				p1.setPClass(GroupClass.getClass(0));
				
				p1.addPassive(p1.getPClass().getPassive());
				
				//player.teleport(FastTravel.getWayPoint(args[2]).getLocation());
				return true;
				
			case "mage":
				
				// Sets the previous step's title and description
//				s.setTitle(ChatColor.DARK_BLUE +"Blueprints to the home crystal");
//				s.setDescription("You aligned with the mages");
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp mage " + p1.getPlayer().getName());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p1.getPlayer().getName() + " parent add mage");
				
				p1.setPClass(GroupClass.getClass(1));
				
				p1.addPassive(p1.getPClass().getPassive());
				
				//player.teleport(FastTravel.getWayPoint(args[2]).getLocation());
				return true;
				
			case "warrior":
				
				// Sets the previous step's title and description
//				s.setTitle(ChatColor.GOLD + "Warrior Shield Schematics");
//				s.setDescription("You aligned with the warriors");
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp warrior " + p1.getPlayer().getName());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p1.getPlayer().getName() + " parent add warrior");
				
				// Adds the warrior starting quest
//				q = Quest.getQuest(QuestTags.WARRIOR_START);
//				q.getSteps().get(0).setStatus(StepStatus.Active);
//				p1.addQuest(q);
				
				p1.setPClass(GroupClass.getClass(2));
				
				p1.addPassive(p1.getPClass().getPassive());
				
				//player.teleport(FastTravel.getWayPoint(args[2]).getLocation());
				return true;
				
			case "rogue":
				
				// Sets the previous step's title and description
//				s.setTitle(ChatColor.GRAY +"Plans to attack");
//				s.setDescription("You aligned with the rogues");
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp rogue " + p1.getPlayer().getName());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p1.getPlayer().getName() + " parent add rogue");
				
				p1.setPClass(GroupClass.getClass(3));
				
				p1.addPassive(p1.getPClass().getPassive());
				
				//player.teleport(FastTravel.getWayPoint(args[2]).getLocation());
				return true;
				
			}
		}

		return false;
	}

}
