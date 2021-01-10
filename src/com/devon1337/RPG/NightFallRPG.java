package com.devon1337.RPG;

import com.devon1337.RPG.ActiveAbilities.Assassinate;
import com.devon1337.RPG.ActiveAbilities.Blood_Shield;
import com.devon1337.RPG.ActiveAbilities.Charge;
import com.devon1337.RPG.ActiveAbilities.Confusion;
import com.devon1337.RPG.ActiveAbilities.Empty;
import com.devon1337.RPG.ActiveAbilities.Entanglement;
import com.devon1337.RPG.ActiveAbilities.Explosive_Strike;
import com.devon1337.RPG.ActiveAbilities.Fireball;
import com.devon1337.RPG.ActiveAbilities.GlobalSpellbook;
import com.devon1337.RPG.ActiveAbilities.HeatedJuggernaut;
import com.devon1337.RPG.ActiveAbilities.MOTW;
import com.devon1337.RPG.ActiveAbilities.NightmareSlasher;
import com.devon1337.RPG.ActiveAbilities.Plague_Touch;
import com.devon1337.RPG.ActiveAbilities.Rejuvenate;
import com.devon1337.RPG.ActiveAbilities.Shield_Bash;
import com.devon1337.RPG.ActiveAbilities.Shield_Slam;
import com.devon1337.RPG.ActiveAbilities.Spell;
import com.devon1337.RPG.ActiveAbilities.SpellType;
import com.devon1337.RPG.ActiveAbilities.Starfire;
import com.devon1337.RPG.ActiveAbilities.Tranquility;
import com.devon1337.RPG.ActiveAbilities.Vanish;
import com.devon1337.RPG.ActiveAbilities.Wrath;
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
import com.devon1337.RPG.Commands.NFPrint;
import com.devon1337.RPG.Commands.NFQuest;
import com.devon1337.RPG.Commands.NFRaidMaker;
import com.devon1337.RPG.Commands.NFTeam;
import com.devon1337.RPG.Commands.NFTest;
import com.devon1337.RPG.Commands.NFTravel;
import com.devon1337.RPG.Commands.NFUpdateDialog;
import com.devon1337.RPG.Commands.OpenSpellBook;
import com.devon1337.RPG.Commands.NFParty;
import com.devon1337.RPG.Commands.PickClass;
import com.devon1337.RPG.Commands.Reply;
import com.devon1337.RPG.Commands.Roll;
import com.devon1337.RPG.Commands.Trade;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Menus.CreativeObjectMenu;
import com.devon1337.RPG.Menus.FastTravelUI;
import com.devon1337.RPG.Menus.GameMasterMenu;
import com.devon1337.RPG.Menus.LevelUpMenu;
import com.devon1337.RPG.Menus.QuestMenu;
import com.devon1337.RPG.Menus.SelectClass;
import com.devon1337.RPG.Menus.SpellBook;
import com.devon1337.RPG.Objects.BlankObject;
import com.devon1337.RPG.Objects.NFObject;
import com.devon1337.RPG.Objects.QuestBookObject;
import com.devon1337.RPG.Objects.EasterEggs.CappaTheShopStand;
import com.devon1337.RPG.Objects.SelectClass.DruidClassBook;
import com.devon1337.RPG.Objects.SelectClass.MageClassBook;
import com.devon1337.RPG.Objects.SelectClass.RogueClassBook;
import com.devon1337.RPG.Objects.SelectClass.WarriorClassBook;
import com.devon1337.RPG.PassiveAbilities.CCPower;
import com.devon1337.RPG.PassiveAbilities.Lifesteal;
import com.devon1337.RPG.PassiveAbilities.LowerCD;
import com.devon1337.RPG.PassiveAbilities.PartyHeal;
import com.devon1337.RPG.PassiveAbilities.SpellPower;
import com.devon1337.RPG.Player.AccountFlags;
import com.devon1337.RPG.Player.DatabaseAccess;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Player.PlayerUtils;
import com.devon1337.RPG.Quests.EventFlags;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.Step;
import com.devon1337.RPG.Quests.StepStatus;
import com.devon1337.RPG.Quests.TestQuest;
import com.devon1337.RPG.Quests.Warrior.WarriorStart;
import com.devon1337.RPG.Utils.DialogueSystem;
import com.devon1337.RPG.Utils.EcoSystem;
import com.devon1337.RPG.Utils.FileManager;
import com.devon1337.RPG.Utils.FriendsList;
import com.devon1337.RPG.Utils.MOTD;
import com.devon1337.RPG.Utils.PickPocket;
import com.devon1337.RPG.Utils.Region;
import com.devon1337.RPG.Utils.Regions.SelectClass.Pitfall;
import com.devon1337.RPG.WeaponEffects.Weapon;
import com.devon1337.RPG.WeaponEffects.Effects.Prefixes;
import com.devon1337.RPG.WeaponEffects.Effects.WType;
import com.devon1337.RPG.WeaponEffects.Effects.Prefix.Minor;
import com.devon1337.RPG.WeaponEffects.Effects.Type.Frozen;
import com.devon1337.RPG.Utils.Raycast.RaycastHitEvent;
import com.devon1337.RPG.raid.MatchmakingController;

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
	public static Economy econ;

	public static Scanner input = new Scanner(System.in);

	@SuppressWarnings("unused")
	public void onEnable() {
		DialogueSystem.init_dialog();

		// Loading Factions
		
		// Loading NPCs
		
		// Loading Collision
		new Pitfall();
		
		// Loading Default Spells
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
		
		// Loading Spell Passives
		new Lifesteal();
		new SpellPower();
		new CCPower();
		new LowerCD();
		new PartyHeal();
		
		// Loading Group Classes
		new Druid();
		new Mage();
		new Warrior();
		new Rogue();
		new Deprived();
		
		// Initializing Weapon Prefixes
		Minor m = new Minor();
		Prefixes p = m;
		p.setIPrefix(m.getIPrefix());
		
		// Initializing Weapon Types
		Frozen f = new Frozen();
		WType w = f;
		w.setIWeapon(f.getIWeapon());
		
		DialogueSystem.init_dialog();
		gameplayScheduler((Plugin) this);
		
		// Loads custom objects
		new DruidClassBook();
		new RogueClassBook();
		new WarriorClassBook();
		new MageClassBook();
		new QuestBookObject();
		ArrayList<WType> types = new ArrayList<WType>();
		ArrayList<Prefixes> prefixes = new ArrayList<Prefixes>();
		
		types.add(w);
		prefixes.add(p);
		
		Weapon wp = new Weapon(types, prefixes, Material.DIAMOND_SWORD);

		new BlankObject(wp.getItem(), wp.getName());
		
		CappaTheShopStand.init_dialog();
		
		// Menu Loading
		new CreativeObjectMenu();
		new FastTravelUI();
		new GameMasterMenu();
		new LevelUpMenu();
		new SelectClass();
		new SpellBook();
		new QuestMenu();
		
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
		getCommand("nfud").setExecutor((CommandExecutor) new NFUpdateDialog());
		getCommand("nfgetdialog").setExecutor((CommandExecutor) new NFGetDialog());
		getCommand("nfmsg").setExecutor((CommandExecutor) new NFMessage());
		getCommand("nfr").setExecutor((CommandExecutor) new Reply());
		getCommand("nftravel").setExecutor((CommandExecutor) new NFTravel());
		getCommand("nfraid").setExecutor((CommandExecutor) new NFRaidMaker());
		getCommand("nfobjects").setExecutor((CommandExecutor) new NFObjects());
		getCommand("nfteam").setExecutor((CommandExecutor) new NFTeam());
		getCommand("toggleflag").setExecutor((CommandExecutor) new NFAddFlag());
		
		FileManager.exportNpc();
		FileManager.exportDialog();
		try {
			FileManager.importFastTravel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TestQuest.addStep(new Step("Go to point A", "You were losting trying to do something", true, StepStatus.Inactive, EventFlags.LocationEvent));
		TestQuest test_quest = new TestQuest();
		
		WarriorStart.addStep(new Step("Wake up.", "You passsed out while working.", true, StepStatus.Inactive, EventFlags.LocationEvent));
		WarriorStart.addStep(new Step("Speak with Bastian Blackwood.", "You may still be able to travel to Ganaboru", true, StepStatus.Inactive, EventFlags.LocationEvent));
		WarriorStart ws_quest = new WarriorStart();
		
		for(Quest q : Quest.getAllQuests()) {
		Logging.OutputToConsole("Quest Registered: " + q.getName());
	
		}
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			FileManager.importPlayer(player.getUniqueId());
		}
		
		if (getPlugin().getServer().getPluginManager().isPluginEnabled("Vault")) {
            getPlugin().getServer().getServicesManager().register(Economy.class, new EcoSystem(this), getPlugin(), ServicePriority.Normal);
        }
		
	}
	
	public void onDisable() {
	}
	
	@EventHandler
	public void onHeldEvent(PlayerItemHeldEvent event) {
		Player player = (Player) event.getPlayer();
		if (event.getNewSlot() < 4
				&& !NFPlayer.getPlayer(player.getUniqueId()).getFlags().contains(AccountFlags.Developer)) {

			// TODO: Close the loop off plzzzz :(((
			for (Spell s : GlobalSpellbook.getSpells()) {
				if (s.getItem().equals(event.getPlayer().getInventory().getItem(event.getNewSlot()))
						&& s.getSpellType() != SpellType.WeaponArt) {
					Logging.OutputToPlayer("Spell: " + s.getName(), player);
					Spell.start(s, player);
					event.setCancelled(true);
				}
			}
		}
		NamespacedKey key = new NamespacedKey(getPlugin(), "nightfallrpg-questbook");
		if(event.getPlayer().getInventory().getItem(event.getNewSlot()).getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.INTEGER) != null) {
			NFObject.getObject(4).hoverEvent(NFPlayer.getPlayer(player.getUniqueId()));
			event.setCancelled(true);
		}
	}
	
	// Inventory Response Handler
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p1 = (Player) event.getWhoClicked();
		NFPlayer player = null;
		if(p1 != null) {
		player = NFPlayer.getPlayer(p1.getUniqueId());
		}
		
		if (!event.getCurrentItem().getItemMeta().getPersistentDataContainer().isEmpty() && !player.getFlags().contains(AccountFlags.Developer)) {
			p1.sendMessage("You cannot remove that!");
			event.setCancelled(true);
		}
		
		
		
		if(player != null && player.getMenu() != null) {
			player.getMenu().runResponse(player, event.getRawSlot(), event);
			event.setCancelled(true);
		}
		
	}

	public void onInventoryLeave(InventoryCloseEvent event) {
		NFPlayer player = NFPlayer.getPlayer(event.getPlayer().getUniqueId());
		
		if(!player.getMenu().getInventory().equals(event.getInventory())) {
			player.setMenu(null);
		}
	}
	
	/* Player on Death Event
	* - Used for making sure the spells dont drop as items
	* - Used for quest tracking
	*/
	@EventHandler
	public void onDeathEvent(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		player.getInventory().setItem(0, new ItemStack(Material.AIR));
		player.getInventory().setItem(1, new ItemStack(Material.AIR));
		player.getInventory().setItem(2, new ItemStack(Material.AIR));
		player.getInventory().setItem(3, new ItemStack(Material.AIR));
	}
	
	/* Player Respawn Event
	 * - Used for HP Logic
	 */
	@EventHandler 
	public void onRespawnEvent(PlayerRespawnEvent event) {
		
		NFPlayer player = NFPlayer.getPlayer(event.getPlayer().getUniqueId());
		
		// Resetting HP to Max
		player.setHp(NFPlayer.getPlayer(event.getPlayer().getUniqueId()).getMaxHp());
		
		// Respawning Spells and Questbook
		player.respawn();
		NFQuest.generateNewQuestBook(player.getPlayer());
		
	}
	
	
	/* Player on Regeneration Health Event 
	 * - Used to detect and adjust players health due to base game reasons.
	 * 
	 */
	@EventHandler
	public void onRegen(EntityRegainHealthEvent event) {
		
		// Adjusts players HP for healing
		if(event.getEntity() instanceof Player) {
			NFPlayer p = NFPlayer.getPlayer(event.getEntity().getUniqueId());
			p.setHp(event.getAmount() + p.getHp());
		}
	}
	
	@EventHandler
	public void onDamageEvent(EntityDamageByEntityEvent event) {
		Arrow arr = null;
		if(event.getDamager() instanceof Arrow) {
			arr = (Arrow) event.getDamager();
		}
		
		// Adjusts players HP for damage Taken from non-nfrpg means
		if(event.getEntity() instanceof Player && (Player) event.getEntity() != null && !arr.hasMetadata("raycast")) {
			Player player = (Player) event.getEntity();
			
			
			NFPlayer p = NFPlayer.getPlayer(event.getEntity().getUniqueId());
			p.setHp(p.getHp() - event.getDamage());
			
			if(((player.getHealth() - event.getDamage()) < 1) && (NFPlayer.getPlayer(player.getUniqueId()).getHp() - event.getDamage() > 1)) {
				event.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void onDamageEvent(EntityDamageEvent event) {
		
		// Adjusts players HP for damage Taken from non-nfrpg means
		if(event.getEntity() instanceof Player && (Player) event.getEntity() != null) {
			Player player = (Player) event.getEntity();
			
			NFPlayer p = NFPlayer.getPlayer(event.getEntity().getUniqueId());
			p.setHp(p.getHp() - event.getDamage());
			
			if(((player.getHealth() - event.getDamage()) < 1) && (NFPlayer.getPlayer(player.getUniqueId()).getHp() - event.getDamage() > 1)) {
				event.setCancelled(true);
			}
		}
		
	}

	@EventHandler
	public void onDropEvent(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		//NamespacedKey key = new NamespacedKey(NightFallRPG.getPlugin(),"nightfall-rpg-spell");
		
		if (!event.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer().isEmpty() && !NFPlayer.getPlayer(player.getUniqueId()).getFlags().contains(AccountFlags.Developer)) {
			player.sendMessage("You cannot remove that!");
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInventoryMoveItemEvent(InventoryMoveItemEvent event) {
		//Player player = (Player) event.getInitiator().getViewers().get(0);
		
	}
	
	// Player Interact Entity Event
	@SuppressWarnings("unused")
	@EventHandler
	public void onInteractEvent(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			Player p = e.getPlayer(), t = (Player) e.getRightClicked();
			if (p.isSneaking()) {
				PickPocket p2 = new PickPocket(NFPlayer.getPlayer(p.getUniqueId()),
						NFPlayer.getPlayer(t.getUniqueId()));
			}
		}
	}
	

	@EventHandler
	public void onRegionEnter(RegionEnteredEvent e) {
		Player player = e.getPlayer();
		Logging.OutputToConsole("Region Name: " + e.getRegionName());
		if (player instanceof Player && !NFPlayer.getPlayer(player.getUniqueId()).getFlags().contains(AccountFlags.Developer) && Region.getRegion(e.getRegionName()) != null) {
			Region.getRegion(e.getRegionName()).enterRegion(NFPlayer.getPlayer(player.getUniqueId()));
		}
	}
	
	public void onRegionLeft(RegionLeftEvent e) {
		Player player = e.getPlayer();

		if (player instanceof Player && !NFPlayer.getPlayer(player.getUniqueId()).getFlags().contains(AccountFlags.Developer) && Region.getRegion(e.getRegionName()) != null) {
			Region.getRegion(e.getRegionName()).leaveRegion(NFPlayer.getPlayer(player.getUniqueId()));
		}
	}

	public static OfflinePlayer getOfflinePlayer(String username) {
		return Bukkit.getOfflinePlayer(username);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage("");
		FriendsList.goOffline(event.getPlayer());
		
		// GCs the player after leaving
		if(FileManager.hasPlayedBefore(event.getPlayer().getUniqueId())) {
		FileManager.exportPlayer(NFPlayer.getPlayer(event.getPlayer().getUniqueId()));
		}
		NFPlayer.getPlayers().remove(NFPlayer.getPlayer(event.getPlayer().getUniqueId()));
		
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeave(PlayerKickEvent event) {
		event.setLeaveMessage("");
		FriendsList.goOffline(event.getPlayer());
		
		// GCs the player after leaving
		FileManager.exportPlayer(NFPlayer.getPlayer(event.getPlayer().getUniqueId()));
		NFPlayer.getPlayers().remove(NFPlayer.getPlayer(event.getPlayer().getUniqueId()));
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		// New Login Sys
		if(!FileManager.hasPlayedBefore(event.getPlayer().getUniqueId()) || event.getPlayer().getName().equalsIgnoreCase("devon1337")) {
			new NFPlayer(event.getPlayer().getName(), event.getPlayer().getUniqueId());
		} else if(FileManager.hasPlayedBefore(event.getPlayer().getUniqueId())) {
			FileManager.importPlayer(event.getPlayer().getUniqueId());
		}
		
		
		// Current Login Sys
		Logging.OutputToPlayer("Name: " + event.getPlayer().getName(), event.getPlayer());
		MOTD.printMOTD(event.getPlayer());
		Logging.OutputToPlayer("You are level: " + NFPlayer.getPlayer(event.getPlayer().getUniqueId()).getLevel(), event.getPlayer());
		event.setJoinMessage("");
		FriendsList.goOnline(event.getPlayer());
	}

	@EventHandler
	public void onRaycastHit(RaycastHitEvent e) {
		ArrayList<Player> targets = new ArrayList<Player>();
		targets.add(Bukkit.getPlayer(e.getTarget()));
		e.getSpell().getSpell().use(Bukkit.getPlayer(e.getShooter()), targets);
	}
	
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

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		Player player = (Player) event.getEntity().getShooter();

		if (event.getEntity() instanceof org.bukkit.entity.Arrow) {
			Logging.OutputToConsole("Is raycast: " + event.getEntity().hasMetadata("raycast"));
			if (event.getHitEntity() instanceof Player && event.getEntity().hasMetadata("raycast")) {
				new RaycastHitEvent(player.getUniqueId(), event.getHitEntity().getUniqueId(), GlobalSpellbook.getSpells().get(event.getEntity().getMetadata("spell").get(0).asInt()), (Arrow) event.getEntity());
			}
		}
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {

		/*
		 * Old lifesteal if(event.getEntity() instanceof Player) { // TODO: Middle stage
		 * Lifesteal.run((Player) event.getDamager(), (Player) event.getEntity(),
		 * event.getDamage(), 1.0f);
		 * 
		 * }
		 */

		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();

			// PersistentDataContainer container =
			// player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();

			// Nightmare Slasher
			/*
			 * if(container.has(NightmareSlasher.getKey(), PersistentDataType.INTEGER) &&
			 * intToBool(container.get(NightmareSlasher.getKey(),
			 * PersistentDataType.INTEGER))) { NightmareSlasher.Slash(player, (Player)
			 * event.getEntity()); }
			 */

			for (Spell s : GlobalSpellbook.getSpells()) {
				// Weapon Arts
				if (s.getSpellType() == SpellType.WeaponArt && s.getItem() == player.getItemOnCursor()) {
					Spell.CastAttack(s, player, (Player) event.getEntity());
				}

			}

		}

	}
	
	public static boolean intToBool(int i) {
		if(i == 1) {
			return true;
		}
		return false;
	}

	public void gameplayScheduler(Plugin plugin) {
		this.scheduler.scheduleSyncRepeatingTask((Plugin) this, new Runnable() {

			@SuppressWarnings("static-access")
			public void run() {
				//NightFallRPG.this.gameplayLoop();
				
				// x * (y/z)
				// x - Current HP
				// z - Max HP
				// y - NFPlayer max HP
				
				/*
				for(NFPlayer p : NFPlayer.getPlayers()) {
					if(Bukkit.getPlayer(p.getUUID()).getHealth() * (p.getMaxHp()/20.0D) == p.getHp()) {
					p.setHp(Bukkit.getPlayer(p.getUUID()).getHealth()* (p.getMaxHp()/20.0D));
					}
				}
				*/
				Spell.runCooldown();
				/*
				for(Spell s : GlobalSpellbook.getSpells()) {
					s.runCooldown();
				}
				*/
				
			}
		}, 0L, 20L);
	}

	public void doGlobalLoop() {
		MatchmakingController.checkRaids();

		@SuppressWarnings("unchecked")
		List<Player> allPlayers = (List<Player>) Bukkit.getOnlinePlayers();
		for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
			NFQuest.generateNewQuestBook(allPlayers.get(i));
		}
	}

	public void gameplayLoop() {
		doGlobalLoop();
	}

	public static Plugin getPlugin() {
		return (Plugin) getPlugin(NightFallRPG.class);
	}

	public static World getWorld() {
		return Bukkit.getWorlds().get(0);
	}
}