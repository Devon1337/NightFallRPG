package com.devon1337.RPG.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Classes.GroupClass;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Objects.NFObject;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Utils.Menu;
import com.devon1337.RPG.Utils.Region;

import lombok.Getter;
import lombok.Setter;

public class NFPlayer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4641258715434396281L;
	@Getter
	@Setter
	GroupClass pClass;
	String BuildID, Name;
	UUID id;
	Menu inv;
	int Level, xp, xpReq;
	boolean online = false, isDead = false;
	float xpGainMod = 1, damageResistance = 1.0f, playerSpeed;
	double currentHP, maxHp = 20, money = 0.0;
	
	@Getter @Setter
	Region region;
	
	@Getter
	Spell[] curSpells = new Spell[3];
	@Getter
	ArrayList<Passive> curPassive = new ArrayList<Passive>();
	@Getter
	ArrayList<Quest> currentQuests = new ArrayList<Quest>();
	
	@Getter
	ArrayList<AccountFlags> flags = new ArrayList<AccountFlags>();

	// Data Retention
	private static ArrayList<NFPlayer> globalPlayers = new ArrayList<NFPlayer>();

	private static final int xpBaseValue = 65, maxLevelClamp = 5;
	private static final float xpBaseModifier = 2.75f;

	public NFPlayer(String Name, UUID id) {
		Logging.OutputToConsole("Player created!");
		this.Name = Name;
		this.id = id;
		this.Level = 1;
		this.xp = 1;
		pClass = GroupClass.getClass(4);
		this.xpReq = xpBaseValue;
		this.online = true;
		this.isDead = false;
		this.playerSpeed = Bukkit.getPlayer(id).getWalkSpeed();
		this.currentHP = Bukkit.getPlayer(id).getHealth();
		
		for(int i = 0; i < 3; i++) {
			this.setSpell(i, GlobalSpellbook.getSpell(15));
		}
		
		this.getPlayer().getInventory().setItem(3, NFObject.getObject(4).getItem());

		if (Bukkit.getPlayer(id).getName().equals("Devon1337")) {
			this.currentHP = 100;
			this.maxHp = 100;
		}

		curPassive.add(GlobalSpellbook.getPassives().get(0));
		Bukkit.getPlayer(id).setHealth(20 * (this.currentHP / this.maxHp));
		globalPlayers.add(this);

		addQuest(Quest.getAllQuests().get(0));

	}

	public NFPlayer(String Name, UUID id, Spell[] curSpells, ArrayList<Passive> curPassive, double maxHp,
			double currentHP, float damageResistance, float xpGainMod, int Level, int xp) {

		this.Name = Name;
		this.id = id;
		this.curSpells = curSpells;
		this.curPassive = curPassive;
		this.maxHp = maxHp;
		this.currentHP = currentHP;
		this.damageResistance = damageResistance;
		this.xpGainMod = xpGainMod;
		this.Level = Level;
		this.xp = xp;
		this.playerSpeed = Bukkit.getPlayer(id).getWalkSpeed();
		this.currentHP = Bukkit.getPlayer(id).getHealth();
		
		
		globalPlayers.add(this);
	}

	public ArrayList<Passive> getPassives() {
		return this.curPassive;
	}

	public Menu getMenu() {
		return this.inv;
	}

	public void setMenu(Menu inv) {
		this.inv = inv;
	}

	public void addPassive(Passive p) {
		curPassive.add(p);
	}

	public boolean isOnline() {
		return this.online;
	}
	
	public void respawn() {
		for(int i = 0; i < 3; i++) {
			getPlayer().getInventory().setItem(i, curSpells[i].getItem());
		}
		this.getPlayer().getInventory().setItem(3, NFObject.getObject(4).getItem());
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public static ArrayList<NFPlayer> getPlayers() {
		return globalPlayers;
	}

	public static NFPlayer getPlayer(UUID id) {
		for (NFPlayer p : globalPlayers) {
			if (p.getUUID() == id) {
				return p;
			}
		}
		return null;
	}

	public void setSpell(int index, Spell s) {
		if(index < curSpells.length) {
			curSpells[index] = s;
			getPlayer().getInventory().setItem(index, s.getItem());
		}
	}
	
	public int getLevel() {
		return this.Level;
	}

	public void setLevel(int Level) {
		this.Level = Level;
		Menu.getMenu(3).openNFInventory(this);
	}

	public void addXp(int amount) {
		xp += amount * xpGainMod;
		Bukkit.getPlayerExact(Name).sendMessage("You have gained " + amount + " xp!");
		Bukkit.getPlayerExact(Name).sendMessage("XP: " + xp + "/" + xpReq);
		if (xp > xpReq && this.getLevel() <= maxLevelClamp) {
			Bukkit.getPlayerExact(Name).sendMessage("You have leveled up!");
			setLevel(this.getLevel() + 1);
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
		return (int) ((int) xpBaseValue + ((xpBaseValue * xpBaseModifier) * (Level + 1)));
	}

	public UUID getUUID() {
		return this.id;
	}

	public Player getPlayerFromUUID() {
		return Bukkit.getPlayer(this.id);
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
		return this.currentHP / this.maxHp;
	}

	public void healPlayer(double currentHP) {
		if(this.getHp()+currentHP<this.maxHp) {
			this.setHp(this.getHp()+currentHP);
		} else {
			this.setHp(this.maxHp);
		}
	}
	
	public void applyDamage(double currentHP) {
		if ((currentHP / this.damageResistance) > maxHp) {
			this.currentHP = maxHp;
		} else {
			if ((currentHP / this.damageResistance) > 0) {
				this.currentHP = (currentHP / this.damageResistance);
			} else {
				this.currentHP = 0;
			}
		}

		// Update player UI Health
		Logging.OutputToPlayer("HP UPDATE: " + this.currentHP + "/" + this.maxHp + " ADJUSTED: " + (20 * (getRatio())),
				Bukkit.getPlayer(id));
		Bukkit.getPlayer(id).setHealth(20 * (getRatio()));
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(this.id);
	}

	public void setHp(double currentHP) {
		if (currentHP > maxHp) {
			this.currentHP = maxHp;
		} else {
			if (currentHP > 0) {
				this.currentHP = currentHP;
			} else {
				this.currentHP = 0;
			}
		}

		// Update player UI Health
		Logging.OutputToPlayer("HP UPDATE: " + this.currentHP + "/" + this.maxHp + " ADJUSTED: " + (20 * (getRatio())),
				Bukkit.getPlayer(id));
		Bukkit.getPlayer(id).setHealth(20 * (getRatio()));
	}

	public double getMaxHp() {
		return this.maxHp;
	}

	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}

	public void addQuest(Quest quest) {
		Bukkit.getPlayer(getUUID()).sendTitle(ChatColor.GOLD + quest.getName(),
				ChatColor.LIGHT_PURPLE + quest.getSteps().get(0).getHint(), 1, 20, 1);
		this.currentQuests.add(quest);
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
		UUID id = UUID.fromString(stream.readLine());
		Spell[] spells = (Spell[]) stream.readObject();
		ArrayList<Passive> passives = (ArrayList<Passive>) stream.readObject();
		double mxHP = stream.readDouble();
		double crHP = stream.readDouble();
		float damageResist = stream.readFloat();
		float xpGainMod = stream.readFloat();
		int level = stream.readInt();
		int xp = stream.readInt();

		new NFPlayer(name, id, spells, passives, mxHP, crHP, damageResist, xpGainMod, level, xp);

	}

}
