package com.devon1337.RPG.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;

import com.devon1337.RPG.NFClasses;
import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Classes.GroupClass;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Menus.LevelUpMenu;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.PassiveAbilities.PassiveType;

public class NFPlayer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4641258715434396281L;
	NFClasses pClass_enum;
	GroupClass pClass;
	String BuildID, Name;
	UUID id;
	int Level, xp, xpReq;
	boolean online = false, isDead = false;
	float xpGainMod = 1, damageResistance = 1.0f, playerSpeed;
	double currentHP, maxHp = 20;
	Spell[] curSpells = new Spell[3];
	ArrayList<Passive> curPassive = new ArrayList<Passive>();
	
	
	// Data Retention
	private static ArrayList<NFPlayer> globalPlayers = new ArrayList<NFPlayer>();
	
	private static final int xpBaseValue = 65, maxLevelClamp = 5;
	private static final float xpBaseModifier = 2.75f;
	
	
	public NFPlayer(NFClasses pClass_enum, String Name, UUID id) {
		Logging.OutputToConsole("Player created!");
		this.pClass_enum = pClass_enum;
		this.Name = Name;
		this.id = id;
		this.Level = 1;
		this.xp = 1;
		this.xpReq = xpBaseValue;
		this.online = true;
		this.isDead = false;
		this.playerSpeed = Bukkit.getPlayer(id).getWalkSpeed();
		this.currentHP = Bukkit.getPlayer(id).getHealth();
		
		if(Bukkit.getPlayer(id).getName().equals("Devon1337")) {
			this.currentHP = 100;
			this.maxHp = 100;
		}
		
		
		curPassive.add(GlobalSpellbook.getPassives().get(0));
		Bukkit.getPlayer(id).setHealth(20*(this.currentHP/this.maxHp));
		globalPlayers.add(this);
	}
	
	
	public NFPlayer(String Name, UUID id, Spell[] curSpells, ArrayList<Passive> curPassive, NFClasses pClass_enum,
			double maxHp, double currentHP, float damageResistance, float xpGainMod, int Level, int xp) {

		this.Name = Name;
		this.id = id;
		this.curSpells = curSpells;
		this.curPassive = curPassive;
		this.pClass_enum = pClass_enum;
		this.maxHp = maxHp;
		this.currentHP = currentHP;
		this.damageResistance = damageResistance;
		this.xpGainMod = xpGainMod;
		this.Level = Level;
		this.xp = xp;
		this.playerSpeed = Bukkit.getPlayer(id).getWalkSpeed();
		this.currentHP = Bukkit.getPlayer(id).getHealth();
		Bukkit.getPlayer(id).setHealth(20*(this.currentHP/this.maxHp));
		globalPlayers.add(this);
	}
	
	// Getters/Setters
	public Spell[] getSpells() {
		return this.curSpells;
	}
	
	public Spell getSpell(int slot) {
		return this.curSpells[slot];
	}
	
	public void setSpells(int slot, Spell spell) {
		curSpells[slot] = spell;
	}
	
	public ArrayList<Passive> getPassives() {
		return this.curPassive;
	}
	
	
	public Passive getPassive(PassiveType pType) {
		for(Passive p : curPassive) {
			if(p.getType() == pType) {
				return p;
			}
		}
		return null;
	}
	
	public void addPassive(Passive p) {
		curPassive.add(p);
	}
	
	public NFClasses getPlayerClass() {
		return this.pClass_enum;
	}
	
	public void setPlayerClass(NFClasses pClass_enum) {
		this.pClass_enum = pClass_enum;
	}
	
	@SuppressWarnings("incomplete-switch")
	public void addClass(GroupClass pClass) {
		this.pClass = pClass;
		this.pClass_enum = pClass.getClassEnum();
		switch(pClass.getClassEnum()) {
		case MAGE:
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp mage " + this.Name);
			break;
		case DRUID:
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp druid " + this.Name);
			break;
		case WARRIOR:
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp warrior " + this.Name);
			break;
		case ROGUE:
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp rogue " + this.Name);
			break;
		}
	}
	
	public boolean isOnline() {
		return this.online;
	}
	
	public void setOnline(boolean online) {
		this.online = online;
	}
	
	public static ArrayList<NFPlayer> getPlayers() {
		return globalPlayers;
	}
	
	public static NFPlayer getPlayer(UUID id) {
		for(NFPlayer p : globalPlayers) {
			if(p.getUUID() == id) {
				return p;
			} 
		}
		return null;
	}
	
	public int getLevel() {
		return this.Level;
	}
	
	public void setLevel(int Level) {
		this.Level = Level;
		new LevelUpMenu(Bukkit.getPlayer(id));
	}
	
	public void addXp(int amount) {	
			xp += amount * xpGainMod;
			Bukkit.getPlayerExact(Name).sendMessage("You have gained " +  amount + " xp!");
			Bukkit.getPlayerExact(Name).sendMessage("XP: " + xp + "/" + xpReq);
			if(xp > xpReq && this.getLevel() <= maxLevelClamp) {
				Bukkit.getPlayerExact(Name).sendMessage("You have leveled up!");
				setLevel(this.getLevel()+1);
				xpReq = getRequiredXp();
			}
	}
	
	public float getSpeed() {
		return this.playerSpeed;
	}
	
	public void setSpeed(float playerSpeed) {
		Bukkit.getPlayer(this.id).setWalkSpeed(playerSpeed);
		this.playerSpeed = playerSpeed;
	}
	
	public int getRequiredXp() {
		return (int) ((int) xpBaseValue + ((xpBaseValue * xpBaseModifier)*(Level+1)));
	}
	
	public UUID getUUID() {
		return this.id;
	}
	
	public float getDamageResistance() {
		return this.damageResistance;
	}
	
	public void setDamageResistance(float damageResistance) {
		this.damageResistance = damageResistance;
	}
	
	public double getHp() {
		return this.currentHP;
	}
	
	public double getRatio() {
		return this.currentHP/this.maxHp;
	}
	
	
	public void setHp(double currentHP) {
		if(currentHP > maxHp) {
			this.currentHP = maxHp;
		} else {
			if(currentHP > 0) {
				this.currentHP = currentHP;
			} else {
				this.currentHP = 0;
			}
		}
		
		// Update player UI Health
		Logging.OutputToPlayer("HP UPDATE: " + this.currentHP + "/" + this.maxHp + " ADJUSTED: " + (20*(getRatio())), Bukkit.getPlayer(id));
		Bukkit.getPlayer(id).setHealth(20*(getRatio()));
	}
	
	public double getMaxHp() {
		return this.maxHp;
	}
	
	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}
	
	// I/O for NFPlayer
	public void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		stream.writeChars(Name);
		stream.writeChars(id.toString());
		stream.writeObject(curSpells);
		stream.writeObject(curPassive);
		stream.writeObject(pClass);
		stream.writeDouble(maxHp);
		stream.writeDouble(currentHP);
		stream.writeFloat(damageResistance);
		stream.writeFloat(xpGainMod);
		stream.writeInt(Level);
		stream.writeInt(xp);
	}
	
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		String name = stream.readLine();
		Logging.OutputToConsole("Importing: " + name);
		UUID id =  UUID.fromString(stream.readLine());
		Spell[] spells = (Spell[]) stream.readObject();
		ArrayList<Passive> passives = (ArrayList<Passive>) stream.readObject();
		NFClasses curClass = (NFClasses) stream.readObject();
		double mxHP = stream.readDouble();
		double crHP = stream.readDouble();
		float damageResist = stream.readFloat();
		float xpGainMod = stream.readFloat();
		int level = stream.readInt();
		int xp = stream.readInt();


		new NFPlayer(name, id, spells, passives, curClass, mxHP, crHP, damageResist, xpGainMod, level, xp);

	}
	
	
}
