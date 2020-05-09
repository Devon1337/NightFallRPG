package com.devon1337.RPG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.devon1337.RPG.ActiveAbilities.Charge;
import com.devon1337.RPG.ActiveAbilities.Vanish;

public class SimulateSpell implements CommandExecutor {

	public Vanish vanish = new Vanish();
	public Charge charge = new Charge();
	
	@Override
	public boolean onCommand(CommandSender sender,  Command cmd,  String arg2, String[] args) {
		Player player = (Player) sender;
		
		if(sender instanceof Player && args.length > 0) {
			inputResponse(args[0], player);
			return true;
		}
		
		// TODO Auto-generated method stub
		return false;
	}
	
	public void inputResponse(String message, Player player) {
		switch(message) {
		case "vanish": 
			vanish.use(player);
			break;
		case "charge":
			charge.use(player);
			break;
		}
	}
	

}