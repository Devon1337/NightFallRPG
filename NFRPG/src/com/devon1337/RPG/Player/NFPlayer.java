package com.devon1337.RPG.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.devon1337.RPG.NightFallRPG;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.Classes.GroupClass;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Objects.NFObject;
import com.devon1337.RPG.PassiveAbilities.Passive;
import com.devon1337.RPG.PassiveAbilities.UseType;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.QuestStatus;
import com.devon1337.RPG.Quests.QuestTags;
import com.devon1337.RPG.Utils.Menu;
import com.devon1337.RPG.Utils.Region;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;

import lombok.Getter;
import lombok.Setter;

public class NFPlayer implements java.io.Serializable, Update {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4641258715434396281L;
	@Getter @Setter
	GroupClass pClass;
	
	String BuildID, Name;
	UUID id;
	Menu inv;
	
	@Getter
	int level, xp, xpReq;
	boolean online = false, isDead = false;
	@Getter @Setter
	float xpGainMod = 1, playerSpeed = 0.2f, speedModifier = 0;
	float damageResistance = 1.0f;
	double currentHP, maxHp = 20, money = 0.0;
	
	@Getter @Setter
	Region region; // Player's current Region

	@Getter
	ArrayList<Spell> spells = new ArrayList<Spell>(); // Player's current spells
	
	@Getter
	ArrayList<Passive> passives = new ArrayList<Passive>(); // Player's current passives
	
	@Getter
	ArrayList<Quest> quests = new ArrayList<Quest>(); // Player's current quests
	
	@Getter
	ArrayList<NFPlayer> friends = new ArrayList<NFPlayer>(); // Player's current friends

	@Getter
	ArrayList<AccountFlags> flags = new ArrayList<AccountFlags>(); // Player's current tags

	// Data Retention
	private static ArrayList<NFPlayer> globalPlayers = new ArrayList<NFPlayer>();

	private static final int XP_BASE_VALUE = 145, MAX_LEVEL_CLAMP = 5;
	private static final float XP_BASE_MODIFIER = 2.75f;

	public NFPlayer(String Name, UUID id) {
		Logging.OutputToConsole("Player created!");
		this.Name = Name;
		this.id = id;
		this.level = 1;
		this.xp = 1;
		pClass = GroupClass.getClass(4);
		this.xpReq = XP_BASE_VALUE;
		this.online = true;
		this.isDead = false;
		this.currentHP = Bukkit.getPlayer(id).getHealth();
		
//		for(int i = 0; i < 3; i++) {
//			this.addSpell(i, GlobalSpellbook.getSpell(15));
//		}
		
		this.getPlayer().getInventory().setItem(8, NFObject.getObject(4).getItem());

		if (Bukkit.getPlayer(id).getName().equals("Devon1337")) {
			this.currentHP = 100;
			this.maxHp = 100;
		}
		
		Bukkit.getPlayer(id).setHealth(20 * (this.currentHP / this.maxHp));
		globalPlayers.add(this);
		
		NightFallRPG.getUPDATE_EVENTS().add(this);

	}

	public NFPlayer(String Name, UUID id, ArrayList<Spell> spells, ArrayList<Passive> passives, double maxHp,
			double currentHP, float damageResistance, float xpGainMod, int level, int xp) {

		this.Name = Name;
		this.id = id;
		this.spells = spells;
		this.passives = passives;
		this.maxHp = maxHp;
		this.currentHP = currentHP;
		this.damageResistance = damageResistance;
		this.xpGainMod = xpGainMod;
		this.level = level;
		this.xp = xp;
		this.currentHP = Bukkit.getPlayer(id).getHealth();
		
		
		globalPlayers.add(this);
		
		NightFallRPG.getUPDATE_EVENTS().add(this);
	}

	public Menu getMenu() {
		return this.inv;
	}

	public void setMenu(Menu inv) {
		this.inv = inv;
	}

	public void addPassive(Passive p) {
		this.passives.add(p);
	}
	
	public Quest hasQuestWithTag(QuestTags tag) {
		for(Quest q : this.getQuests()) {
			if(q.getTag().equals(tag)) {
				return q;
			}
		}
		return null;
	}

	public boolean isOnline() {
		return this.online;
	}
	
	public void respawn() {
		for(int i = 0; i < 3; i++) {
			//getPlayer().getInventory().setItem(i, curSpells[i].getItem());
		}
		this.getPlayer().getInventory().setItem(8, NFObject.getObject(4).getItem());
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

	public void addSpell(int index, Spell s) {
		// TODO add spell logic
	}

	public void setLevel(int Level) {
		this.level = Level;
		pClass.getLevelUpMenu().openNFInventory(this);
	}

	public void addXp(int amount) {
		xp += amount * xpGainMod;
		Bukkit.getPlayerExact(Name).sendMessage("You have gained " + amount + " xp!");
		Bukkit.getPlayerExact(Name).sendMessage("XP: " + xp + "/" + xpReq);
		
		if (xp > xpReq && this.getLevel() <= MAX_LEVEL_CLAMP) {
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
		return (int) ((int) XP_BASE_VALUE + ((XP_BASE_VALUE * XP_BASE_MODIFIER) * (level + 1)));
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
//		if(this.getHp()+currentHP<this.maxHp) {
//			this.setHp(this.getHp()+currentHP);
//		} else {
//			this.setHp(this.maxHp);
//		}
	}
	
	public void applyDamage(double currentHP) {
//		if ((currentHP / this.damageResistance) > maxHp) {
//			this.currentHP = maxHp;
//		} else {
//			if ((currentHP / this.damageResistance) > 0) {
//				this.currentHP = (currentHP / this.damageResistance);
//			} else {
//				this.currentHP = 0;
//			}
//		}
//
//		// Update player UI Health
//		Logging.OutputToPlayer("HP UPDATE: " + this.currentHP + "/" + this.maxHp + " ADJUSTED: " + (20 * (getRatio())),
//				Bukkit.getPlayer(id));
//		Bukkit.getPlayer(id).setHealth(20 * (getRatio()));
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(this.id);
	}

	public void setHp(double currentHP) {
//		if (currentHP > maxHp) {
//			this.currentHP = maxHp;
//		} else {
//			if (currentHP > 0) {
//				this.currentHP = currentHP;
//			} else {
//				this.currentHP = 0;
//			}
//		}
//
//		// Update player UI Health
//		Logging.OutputToPlayer("HP UPDATE: " + this.currentHP + "/" + this.maxHp + " ADJUSTED: " + (20 * (getRatio())),
//				Bukkit.getPlayer(id));
//		Bukkit.getPlayer(id).setHealth(20 * (getRatio()));
	}

	public double getMaxHp() {
		return this.maxHp;
	}

	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}

	// Adds a new quest to the player!
	public void addQuest(Quest quest) {
		Bukkit.getPlayer(getUUID()).sendTitle(ChatColor.GOLD + quest.getName(),
				ChatColor.LIGHT_PURPLE + quest.getSteps().get(0).getTitle(), 1, 20, 1);
		Quest q2 = quest.instantiateQuest(this);
		this.quests.add(q2);
		q2.setPlayer(this);
		q2.setStatus(QuestStatus.Incomplete);
	}
	
	// Get Quest from QuestTags
	public Quest getQuestFromTags(QuestTags tag) {
		for(Quest q : this.getQuests()) {
			if(q.getTag().equals(tag)) {
				return q;
			}
		}
		
		return null;
	}

	// I/O for NFPlayer
	public void writeObject(java.io.ObjectOutputStream stream) throws IOException {
		stream.writeChars(Name);
		stream.writeChars(id.toString());
		stream.writeObject(spells);
		stream.writeObject(passives);
		stream.writeObject(pClass);
		stream.writeDouble(maxHp);
		stream.writeDouble(currentHP);
		stream.writeFloat(damageResistance);
		stream.writeFloat(xpGainMod);
		stream.writeInt(level);
		stream.writeInt(xp);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		String name = stream.readLine();
		Logging.OutputToConsole("Importing: " + name);
		UUID id = UUID.fromString(stream.readLine());
		ArrayList<Spell> spells = (ArrayList<Spell>) stream.readObject();
		ArrayList<Passive> passives = (ArrayList<Passive>) stream.readObject();
		double mxHP = stream.readDouble();
		double crHP = stream.readDouble();
		float damageResist = stream.readFloat();
		float xpGainMod = stream.readFloat();
		int level = stream.readInt();
		int xp = stream.readInt();

		new NFPlayer(name, id, spells, passives, mxHP, crHP, damageResist, xpGainMod, level, xp);

	}

	
	public Passive getPassiveFromName(String name) {
		for(Passive p : this.getPassives()) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
		// Updates move speed
		this.getPlayer().setWalkSpeed(this.getPlayerSpeed() + this.getSpeedModifier());
		//Logging.OutputToConsole(this.Name + ": Move Speed - > " + this.getPlayer().getWalkSpeed());
		
		for(Passive p : this.getPassives()) {
			//Logging.OutputToConsole("Checking Passive: " + p.getName());
			if(p.getType() == UseType.SelfCast && !p.isEnabled()) {
				//Logging.OutputToConsole("Running"
						//+ p.getName() + ": isEnabled? " + p.isEnabled());
				p.start(this);
				p.setEnabled(true);
			}
		}
		
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}



}
