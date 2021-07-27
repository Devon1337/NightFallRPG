package com.devon1337.RPG;

import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.SpellLoader;
import com.devon1337.RPG.Classes.Deprived;
import com.devon1337.RPG.Classes.Druid;
import com.devon1337.RPG.Classes.Mage;
import com.devon1337.RPG.Classes.Rogue;
import com.devon1337.RPG.Classes.Warrior;
import com.devon1337.RPG.Commands.CheckLevel;
import com.devon1337.RPG.Commands.FLCommand;
import com.devon1337.RPG.Commands.NFAddFlag;
import com.devon1337.RPG.Commands.NFGetDialog;
import com.devon1337.RPG.Commands.NFListNPC;
import com.devon1337.RPG.Commands.NFMessage;
import com.devon1337.RPG.Commands.NFObjects;
import com.devon1337.RPG.Commands.NFParty;
import com.devon1337.RPG.Commands.NFPrint;
import com.devon1337.RPG.Commands.NFQuest;
import com.devon1337.RPG.Commands.NFRaidMaker;
import com.devon1337.RPG.Commands.NFTeam;
import com.devon1337.RPG.Commands.NFTest;
import com.devon1337.RPG.Commands.NFTravel;
import com.devon1337.RPG.Commands.OpenSpellBook;
import com.devon1337.RPG.Commands.PickClass;
import com.devon1337.RPG.Commands.Reply;
import com.devon1337.RPG.Commands.Roll;
import com.devon1337.RPG.Commands.Trade;
import com.devon1337.RPG.Commands.Utilities.NFRunResponse;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Menus.CreativeObjectMenu;
import com.devon1337.RPG.Menus.FastTravelUI;
import com.devon1337.RPG.Menus.GameMasterMenu;
import com.devon1337.RPG.Menus.QuestMenu;
import com.devon1337.RPG.Menus.SelectClass;
import com.devon1337.RPG.Menus.SpellBook;
import com.devon1337.RPG.Menus.LevelUpMenus.WarriorLevelUpMenu;
import com.devon1337.RPG.NPC.AllFactions;
import com.devon1337.RPG.NPC.Faction;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Objects.NFObject;
import com.devon1337.RPG.Objects.QuestBookObject;
import com.devon1337.RPG.Objects.EasterEggs.CappaTheShopStand;
import com.devon1337.RPG.Objects.SelectClass.DruidClassBook;
import com.devon1337.RPG.Objects.SelectClass.MageClassBook;
import com.devon1337.RPG.Objects.SelectClass.RogueClassBook;
import com.devon1337.RPG.Objects.SelectClass.WarriorClassBook;
import com.devon1337.RPG.PassiveAbilities.CCPower;
import com.devon1337.RPG.PassiveAbilities.DisguiseMob;
import com.devon1337.RPG.PassiveAbilities.IronSkin;
import com.devon1337.RPG.PassiveAbilities.Lifesteal;
import com.devon1337.RPG.PassiveAbilities.LowerCD;
import com.devon1337.RPG.PassiveAbilities.MageBuff;
import com.devon1337.RPG.PassiveAbilities.PartyHeal;
import com.devon1337.RPG.PassiveAbilities.RogueBuff;
import com.devon1337.RPG.PassiveAbilities.SpellPower;
import com.devon1337.RPG.Player.AccountFlags;
import com.devon1337.RPG.Player.DatabaseAccess;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Player.PlayerUtils;
import com.devon1337.RPG.Quests.EventFlags;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.QuestTags;
import com.devon1337.RPG.Quests.Step;
import com.devon1337.RPG.Quests.StepStatus;
import com.devon1337.RPG.Utils.EcoSystem;
import com.devon1337.RPG.Utils.FileManager;
import com.devon1337.RPG.Utils.FriendsList;
import com.devon1337.RPG.Utils.MOTD;
import com.devon1337.RPG.Utils.Region;
import com.devon1337.RPG.Utils.Dialog.DialogueSystem;
import com.devon1337.RPG.Utils.EngineInterfaces.Chat;
import com.devon1337.RPG.Utils.EngineInterfaces.Update;
import com.devon1337.RPG.Utils.Regions.Debug.ShrineTest;
import com.devon1337.RPG.Utils.Regions.Druid.TunnelEntrance;
import com.devon1337.RPG.Utils.Regions.SelectClass.AncientBookRoom;
import com.devon1337.RPG.Utils.Regions.SelectClass.Pitfall;
import com.devon1337.RPG.Utils.Regions.Warrior.WSpawn;
import com.devon1337.RPG.Utils.Raycast.RaycastHitEvent;
import com.devon1337.RPG.raid.MatchmakingController;
import com.garbagemule.MobArena.MobArena;

import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.milkbowl.vault.economy.Economy;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import net.raidstone.wgevents.events.RegionLeftEvent;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

@SuppressWarnings("deprecation")
public class NightFallRPG extends JavaPlugin implements Listener {
	
	public static PlayerUtils Putils = new PlayerUtils();

	public static DatabaseAccess dba = new DatabaseAccess();
	public BukkitScheduler scheduler = getServer().getScheduler();

	@Getter
	public static final ArrayList<Chat> CHAT_EVENTS = new ArrayList<Chat>();
	
	@Getter
	public static final ArrayList<Player> PLAYER_EVENTS = new ArrayList<Player>();
	
	@Getter
	public static final ArrayList<Region> REGION_EVENTS = new ArrayList<Region>();
	
	@Getter
	public static final ArrayList<Update> UPDATE_EVENTS = new ArrayList<Update>();
	
	@Getter
	public static Economy econ;
	
	@Getter
	private static MobArena ma;

	public static Scanner input = new Scanner(System.in);

	public void onEnable() {

		if(getServer().getPluginManager().getPlugin("MobArena") != null) {
			ma = (MobArena) getServer().getPluginManager().getPlugin("MobArena");
		} else {
			Logging.OutputToConsole("[!] WARNING MOB ARENA NOT DETECTED!");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		// DO NOT ADJUST LOAD ORDER FOR SAKE OF STABILITY
		// Loading Factions
		WorldController.initializeFaction(new Faction(AllFactions.Warrior, "Warrior"));
		WorldController.initializeFaction(new Faction(AllFactions.Rogue, "Rogue"));
		WorldController.initializeFaction(new Faction(AllFactions.Druid, "Druid"));
		WorldController.initializeFaction(new Faction(AllFactions.Mage, "Mage"));
		
		// Loading NPCs
//		new Blackwood();
//		new DefaultWarriorCitizen1();
//		new DefaultDruidCitizen1();
//		new MinoShearman();
//		new BryceShurman();
//		new HoldenPerry();
//		new ExampleShrineGod();
//		new DeprivedInfo();
//		new TaladanLightbreeze();
	
		// Loading Collision
		new Pitfall();
		new AncientBookRoom();
		new ShrineTest();
		new WSpawn();
		new TunnelEntrance();	
		
		
		SpellLoader.run();
		// Loading Default Spells
		/*
		new Assassinate();
		new Blood_Shield();
		new Charge();
		new Confusion();
		new Entanglement();
		new Fireball();
		new MOTW();
		new Rejuvenate();
		new Shield_Slam();
		new Starfire();
		new Tranquility();
		new Vanish();
		new Wrath();
		new NightmareSlasher();
		new Shield_Bash();
		new Empty();
		new HeatedJuggernaut();
		new Explosive_Strike();
		new Plague_Touch();
		*/

		// Loading Spell Passives
		new Lifesteal();
		new SpellPower();
		new CCPower();
		new LowerCD();
		new PartyHeal();
		new RogueBuff();
		new IronSkin();
		new MageBuff();
		new DisguiseMob();
		
		// Menu Loading
		new CreativeObjectMenu();
		new FastTravelUI();
		new GameMasterMenu();
		new WarriorLevelUpMenu();
		new SelectClass();
		new SpellBook();
		new QuestMenu();

		// Loading Group Classes
		new Druid();
		new Mage();
		new Warrior();
		new Rogue();
		new Deprived();

		// Initializing Weapon Prefixes
//		Minor m = new Minor();
//		Prefixes p = m;
//		p.setIPrefix(m.getIPrefix());

		// Initializing Weapon Types
//		Frozen f = new Frozen();
//		WType w = f;
//		w.setIWeapon(f.getIWeapon());

		gameplayScheduler((Plugin) this);

		// Loads game objects
		new DruidClassBook();
		new RogueClassBook();
		new WarriorClassBook();
		new MageClassBook();
		new QuestBookObject();

		// Create Custom Weapons
//		ArrayList<WType> types = new ArrayList<WType>();
//		ArrayList<Prefixes> prefixes = new ArrayList<Prefixes>();
//		types.add(w);
//		prefixes.add(p);
//		Weapon wp = new Weapon(types, prefixes, Material.DIAMOND_SWORD);

		// Add custom weapon to the creative object menu
//		new BlankObject(wp.getItem(), wp.getName());

		// (Deprecated) Gotta remove
		CappaTheShopStand.init_dialog();

		// Quest Initialization
//		new TestQuest();
//		new WarriorStart();
//		new NameYourself();
//		new RogueStart();
//		new DruidStart();
//		new MageStart();
//		new UnchartedTerritories();

		// Registering Commands
		getServer().getPluginManager().registerEvents(this, (Plugin) this);
		getCommand("class").setExecutor((CommandExecutor) new PickClass());
		getCommand("level").setExecutor((CommandExecutor) new CheckLevel());
		getCommand("spellbook").setExecutor((CommandExecutor) new OpenSpellBook());
		getCommand("roll").setExecutor((CommandExecutor) new Roll());
		getCommand("trade").setExecutor((CommandExecutor) new Trade());
		getCommand("nfparty").setExecutor((CommandExecutor) new NFParty());
		getCommand("nfquest").setExecutor((CommandExecutor) new NFQuest());
		getCommand("fl").setExecutor((CommandExecutor) new FLCommand());
		getCommand("nftest").setExecutor((CommandExecutor) new NFTest());
		getCommand("nfprint").setExecutor((CommandExecutor) new NFPrint());
		getCommand("nflistnpc").setExecutor((CommandExecutor) new NFListNPC());
		getCommand("nfgetdialog").setExecutor((CommandExecutor) new NFGetDialog());
		getCommand("nfmsg").setExecutor((CommandExecutor) new NFMessage());
		getCommand("nfr").setExecutor((CommandExecutor) new Reply());
		getCommand("nftravel").setExecutor((CommandExecutor) new NFTravel());
		getCommand("nfraid").setExecutor((CommandExecutor) new NFRaidMaker());
		getCommand("nfobjects").setExecutor((CommandExecutor) new NFObjects());
		getCommand("nfteam").setExecutor((CommandExecutor) new NFTeam());
		getCommand("toggleflag").setExecutor((CommandExecutor) new NFAddFlag());
		getCommand("runresponse").setExecutor((CommandExecutor) new NFRunResponse());
		
		// Exports NPC Data, NPC Dialog, and imports all fast travels
		FileManager.exportNpc();
		FileManager.exportDialog();
		try {
			FileManager.importFastTravel();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Checks all Registered quests
		for (Quest q : Quest.getAllQuests()) {
			Logging.OutputToConsole("Quest Registered: " + q.getName());

		}

		// Attempts to import existing player data
		for (Player player : Bukkit.getOnlinePlayers()) {
			FileManager.importPlayer(player.getUniqueId());
		}

//		// Attempts to register the plugin as an economy based plugin
		if (getPlugin().getServer().getPluginManager().isPluginEnabled("Vault")) {
			getPlugin().getServer().getServicesManager().register(Economy.class, new EcoSystem(this), getPlugin(),
					ServicePriority.Normal);
		}

	}

	// I dont really know what to put here since its just if the plugin disables and
	// it should never disable
	public void onDisable() {
	}
	
//
//	
//		Spigot Events
//	
//	
	
	// Activates whenever the player presses 0-9 or scrolls through their hotbar.
	@EventHandler
	public void onHeldEvent(PlayerItemHeldEvent event) {
		Player player = (Player) event.getPlayer();
		NamespacedKey key = new NamespacedKey(getPlugin(), "nightfallrpg-questbook");
		// if the player slot is less than 4 and the player contains the account flag
		// developer
		
		// Spell casting
//		if (event.getNewSlot() < 4
//				&& !NFPlayer.getPlayer(player.getUniqueId()).getFlags().contains(AccountFlags.Developer)) {
//
//			// TODO: Close the loop off plzzzz :(((
//			for (Spell s : GlobalSpellbook.getSpells()) {
//				if (s.getItem().equals(event.getPlayer().getInventory().getItem(event.getNewSlot()))
//						&& s.getSpellType() != SpellType.WeaponArt) {
//					Logging.OutputToPlayer("Spell: " + s.getName(), player);
//					Spell.start(s, player);
//					event.setCancelled(true);
//				}
//			}
//		}

		// If the item in the slot contains the persistent data for
		// nightfallrpg-questbook
		if (event.getPlayer().getInventory().getItem(event.getNewSlot()).getItemMeta().getPersistentDataContainer()
				.get(key, PersistentDataType.INTEGER) != null) {
			NFObject.getObject(4).hoverEvent(NFPlayer.getPlayer(player.getUniqueId()));
			event.setCancelled(true);
		}
	}

	// Activates whenever the player clicks on a slot in the inventory, Theirs or
	// ChestUI
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p1 = (Player) event.getWhoClicked();
		NFPlayer player = null;
		if (p1 != null) {
			player = NFPlayer.getPlayer(p1.getUniqueId());
		}

		// If the item contains persistent data and the player does not contain the flag
		// developer
		if (!event.getCurrentItem().getItemMeta().getPersistentDataContainer().isEmpty()
				&& !player.getFlags().contains(AccountFlags.Developer)) {
			p1.sendMessage("You cannot remove that!");
			event.setCancelled(true);
		}

		// If the player is not null and the player is in a menu TODO: Broken, often causes false positives
//		if (player != null && player.getMenu() != null) {
//			player.getMenu().runResponse(player, event.getRawSlot(), event);
//			event.setCancelled(true);
//		}

	}

	// Activates whenever the player closes their Inventory screen or any ChestUI
	public void onInventoryLeave(InventoryCloseEvent event) {
		NFPlayer player = NFPlayer.getPlayer(event.getPlayer().getUniqueId());

		if (!player.getMenu().getInventory().equals(event.getInventory())) {
			player.setMenu(null);
		}
	}

	// Activates whenever the player dies
	@EventHandler
	public void onDeathEvent(PlayerDeathEvent event) {
		Player player = event.getEntity();

		player.getInventory().setItem(0, new ItemStack(Material.AIR));
		player.getInventory().setItem(1, new ItemStack(Material.AIR));
		player.getInventory().setItem(2, new ItemStack(Material.AIR));
		player.getInventory().setItem(3, new ItemStack(Material.AIR));
	}

	// Activates whenever the player respawns
	@EventHandler
	public void onRespawnEvent(PlayerRespawnEvent event) {

		NFPlayer player = NFPlayer.getPlayer(event.getPlayer().getUniqueId());

		// Resetting HP to Max
		//player.setHp(NFPlayer.getPlayer(event.getPlayer().getUniqueId()).getMaxHp());

		// Respawning Spells and Questbook
		player.respawn();
		NFQuest.generateNewQuestBook(player.getPlayer());

	}

	// Activates whenever the player gains HP
	@EventHandler
	public void onRegen(EntityRegainHealthEvent event) {

		// Adjusts players HP for healing
//		if (event.getEntity() instanceof Player) {
//			NFPlayer p = NFPlayer.getPlayer(event.getEntity().getUniqueId());
//			p.setHp(event.getAmount() + p.getHp());
//		}
	}

	// Activates whenever the player gets damaged by an entity(ARROW)
	@EventHandler
	public void onDamageEventByEntity(EntityDamageByEntityEvent event) {
		@SuppressWarnings("unused")
		Arrow arr = null;
		if (event.getDamager() instanceof Arrow) {
			arr = (Arrow) event.getDamager();
		}

//		// Adjusts players HP for damage Taken from non-nfrpg means
//		if (event.getEntity() instanceof Player && (Player) event.getEntity() != null && !arr.hasMetadata("raycast")) {
//			Player player = (Player) event.getEntity();
//
//			NFPlayer p = NFPlayer.getPlayer(event.getEntity().getUniqueId());
//			p.setHp(p.getHp() - event.getDamage());
//
//			if (((player.getHealth() - event.getDamage()) < 1)
//					&& (NFPlayer.getPlayer(player.getUniqueId()).getHp() - event.getDamage() > 1)) {
//				event.setCancelled(true);
//			}
//		}

		// Weapon art spell casting
//		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
//			Player player = (Player) event.getDamager();
//
//			for (Spell s : GlobalSpellbook.getSpells()) {
//				// Weapon Arts
//				if (s.getSpellType() == SpellType.WeaponArt && s.getItem() == player.getItemOnCursor()) {
//					Spell.CastAttack(s, player, (Player) event.getEntity());
//				}
//
//			}
//
//		}

	}

	// Activates whenever the player gets damaged
	@EventHandler
	public void onDamageEvent(EntityDamageEvent event) {

		// Adjusts players HP for damage Taken from non-nfrpg means
		if (event.getEntity() instanceof Player && (Player) event.getEntity() != null) {
			Player player = (Player) event.getEntity();

			NFPlayer p = NFPlayer.getPlayer(event.getEntity().getUniqueId());
			p.setHp(p.getHp() - event.getDamage());

			if (((player.getHealth() - event.getDamage()) < 1)
					&& (NFPlayer.getPlayer(player.getUniqueId()).getHp() - event.getDamage() > 1)) {
				event.setCancelled(true);
			}
		}

	}
	
	// Activates whenever an entity dies
	@EventHandler
	public void onEntityDeathEvent(EntityDeathEvent event) {
		
		if(event.getEntity().getKiller() instanceof Player) {
			NFPlayer player = NFPlayer.getPlayer(event.getEntity().getKiller().getUniqueId());
			Quest NY = player.getQuestFromTags(QuestTags.NAME_YOURSELF);
			
			// Updates combat based quests
			for(Quest q : player.getQuests()) {
				if(q.getFlags().contains(EventFlags.KillEvent)) {
					for(Step s : q.getSteps()) {
						if(event.getEntity().getType().equals(s.getReq().getMob()) && s.getStatus().equals(StepStatus.Active)) {
							s.getReq().setAmount(s.getReq().getAmount()+1);;
						}
					}
				}
			}
			
			if(NY != null) {
				
			}
			
		}
		
	}
	
	public static void removeEngineUpdate(NFPlayer player) {
		UPDATE_EVENTS.remove(player);
	}
	
	// Activates whenever the player quits the server by disconnection(No internet,
	// Login Issues, etc)
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		removeEngineUpdate(NFPlayer.getPlayer(event.getPlayer().getUniqueId()));
		event.setQuitMessage("");
		FriendsList.goOffline(event.getPlayer());

		// GCs the player after leaving
		if (FileManager.hasPlayedBefore(event.getPlayer().getUniqueId())) {
			FileManager.exportPlayer(NFPlayer.getPlayer(event.getPlayer().getUniqueId()));
		}
		NFPlayer.getPlayers().remove(NFPlayer.getPlayer(event.getPlayer().getUniqueId()));

	}

	// Activates whenever the player leaves the server by own intentions(Leave game,
	// Alt+F4)
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeave(PlayerKickEvent event) {
		removeEngineUpdate(NFPlayer.getPlayer(event.getPlayer().getUniqueId()));
		event.setLeaveMessage("");
		FriendsList.goOffline(event.getPlayer());

		// GCs the player after leaving
		FileManager.exportPlayer(NFPlayer.getPlayer(event.getPlayer().getUniqueId()));
		NFPlayer.getPlayers().remove(NFPlayer.getPlayer(event.getPlayer().getUniqueId()));
	}
	
	// Activates whenever the player chats
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		ArrayList<Player> recips = new ArrayList<>();

		for (Player player : event.getRecipients()) {
			recips.add(player);
		}
		for (int i = 0; i < recips.size(); i++) {
			if (DialogueSystem.hasDialog(recips.get(i))) {
				event.getRecipients().remove(recips.get(i));
			}
		}

		event.setCancelled(DialogueSystem.hasDialog(event.getPlayer()));
	}

	// Activates whenever the projectile hits an entity
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		Player player = null;
		if(event.getEntity().getShooter() instanceof Player) {
		player = (Player) event.getEntity().getShooter();
		}
		
		if (event.getEntity() instanceof org.bukkit.entity.Arrow) {
			Logging.OutputToConsole("Is raycast: " + event.getEntity().hasMetadata("raycast"));
			if (event.getHitEntity() instanceof Player && event.getEntity().hasMetadata("raycast")) {
				new RaycastHitEvent(player.getUniqueId(), event.getHitEntity().getUniqueId(),
						GlobalSpellbook.getSpells().get(event.getEntity().getMetadata("spell").get(0).asInt()),
						(Arrow) event.getEntity());
			}
		}

	}

	// Activates whenever the player joins the server
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {

		// New Login Sys
		if (!FileManager.hasPlayedBefore(event.getPlayer().getUniqueId())
				|| event.getPlayer().getName().equalsIgnoreCase("devon1337")) {
			@SuppressWarnings("unused")
			NFPlayer player = new NFPlayer(event.getPlayer().getName(), event.getPlayer().getUniqueId());
//			player.addQuest(Quest.getQuest(QuestTags.START_ZONE_SELECT_CLASS));
		} else if (FileManager.hasPlayedBefore(event.getPlayer().getUniqueId())) {
			FileManager.importPlayer(event.getPlayer().getUniqueId());
		}

		// Current Login Sys
		Logging.OutputToPlayer("Name: " + event.getPlayer().getName(), event.getPlayer());
		MOTD.printMOTD(event.getPlayer());
		Logging.OutputToPlayer("You are level: " + NFPlayer.getPlayer(event.getPlayer().getUniqueId()).getLevel(),
				event.getPlayer());
		event.setJoinMessage("");
		FriendsList.goOnline(event.getPlayer());
	}

	// Activates whenever the player tries to drop an item
	@EventHandler
	public void onDropEvent(PlayerDropItemEvent event) {
		Player player = event.getPlayer();

		if (!event.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer().isEmpty()
				&& !NFPlayer.getPlayer(player.getUniqueId()).getFlags().contains(AccountFlags.Developer)) {
			player.sendMessage("You cannot remove that!");
			event.setCancelled(true);
		}
	}

	// Activates whenever the player tries to move an item
	@EventHandler
	public void onInventoryMoveItemEvent(InventoryMoveItemEvent event) {
		// Player player = (Player) event.getInitiator().getViewers().get(0);

	}

	// Activates whenever the player right clicks on a player
	@SuppressWarnings("unused")
	@EventHandler
	public void onInteractEvent(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			Player p = e.getPlayer(), t = (Player) e.getRightClicked();
//			if (p.isSneaking()) {
//				PickPocket p2 = new PickPocket(NFPlayer.getPlayer(p.getUniqueId()),
//						NFPlayer.getPlayer(t.getUniqueId()));
//			}
		}
	}

//
//	
//		World Guard Events	
//	
//	
	
	// Activates whenever the player enters a region
	@EventHandler
	public void onRegionEnter(RegionEnteredEvent e) {
		Player player = e.getPlayer();
		Logging.OutputToConsole("Region Name: " + e.getRegionName());
		if (player instanceof Player
				&& !NFPlayer.getPlayer(player.getUniqueId()).getFlags().contains(AccountFlags.Developer)
				&& Region.getRegion(e.getRegionName()) != null) {
			Region.getRegion(e.getRegionName()).enterRegion(NFPlayer.getPlayer(player.getUniqueId()));
		}
	}

	// Activates whenever the player leaves a region
	public void onRegionLeft(RegionLeftEvent e) {
		Player player = e.getPlayer();

		if (player instanceof Player
				&& !NFPlayer.getPlayer(player.getUniqueId()).getFlags().contains(AccountFlags.Developer)
				&& Region.getRegion(e.getRegionName()) != null) {
			Region.getRegion(e.getRegionName()).leaveRegion(NFPlayer.getPlayer(player.getUniqueId()));
		}
	}

//	
//	
//		NF RPG Events	
//	
//	
	
	// Activates whenever the raycast hits an entity
	@EventHandler
	public void onRaycastHit(RaycastHitEvent e) {
		ArrayList<Player> targets = new ArrayList<Player>();
		targets.add(Bukkit.getPlayer(e.getTarget()));
		e.getSpell().getSpell().use(Bukkit.getPlayer(e.getShooter()), targets);
	}

	// Int to Bool quick maths, pretty useless
	public static boolean intToBool(int i) {
		if (i == 1) {
			return true;
		}
		return false;
	}

	// Gameplay Scheduler, Does all the things for cooldowns
	public void gameplayScheduler(Plugin plugin) {
		this.scheduler.scheduleSyncRepeatingTask((Plugin) this, new Runnable() {

			public void run() {
				
				for(Update u : UPDATE_EVENTS) {
					u.onUpdate();
				}
				
				
				// NightFallRPG.this.gameplayLoop();

				// x * (y/z)
				// x - Current HP
				// z - Max HP
				// y - NFPlayer max HP

				/*
				 * for(NFPlayer p : NFPlayer.getPlayers()) {
				 * if(Bukkit.getPlayer(p.getUUID()).getHealth() * (p.getMaxHp()/20.0D) ==
				 * p.getHp()) { p.setHp(Bukkit.getPlayer(p.getUUID()).getHealth()*
				 * (p.getMaxHp()/20.0D)); } }
				 */
//				Spell.runCooldown();
				/*
				 * for(Spell s : GlobalSpellbook.getSpells()) { s.runCooldown(); }
				 */

			}
		}, 0L, 20L);
	}

	// Starts the loop
	public void doGlobalLoop() {
		MatchmakingController.checkRaids();

		@SuppressWarnings("unchecked")
		List<Player> allPlayers = (List<Player>) Bukkit.getOnlinePlayers();
		for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
			NFQuest.generateNewQuestBook(allPlayers.get(i));
		}
	}

	// useless, starts up the gameplay loop
	public void gameplayLoop() {
		doGlobalLoop();
	}

	// Returns the offline player from the username
	public static OfflinePlayer getOfflinePlayer(String username) {
		return Bukkit.getOfflinePlayer(username);
	}

	// returns the current plugin
	public static Plugin getPlugin() {
		return (Plugin) getPlugin(NightFallRPG.class);
	}

	// returns the current world
	public static World getWorld() {
		return Bukkit.getWorlds().get(0);
	}
}