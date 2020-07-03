package com.devon1337.RPG;

import com.devon1337.RPG.Player.*;
import com.devon1337.RPG.Quests.NavigationQuest;
import com.devon1337.RPG.Quests.PVPQuest;
import com.devon1337.RPG.Quests.QuestTracker;
import com.devon1337.RPG.Quests.TestQuest;
import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;
import com.devon1337.RPG.Utils.AnimationController;
import com.devon1337.RPG.Utils.DialogueSystem;
import com.devon1337.RPG.Utils.FriendsList;
import com.devon1337.RPG.Utils.Raycast.ProjectileType;
import com.devon1337.RPG.Utils.Raycast.Simulate;
import com.devon1337.RPG.Utils.Raycast.Exceptions.BadProjectile;
import com.devon1337.RPG.Utils.Raycast.Exceptions.InvalidTarget;
import com.devon1337.RPG.Utils.Raycast.Exceptions.ObjectsNotUsed;

import net.raidstone.wgevents.events.RegionEnteredEvent;

import com.devon1337.RPG.ActiveAbilities.ActiveAbilityManager;
import com.devon1337.RPG.ActiveAbilities.Assassinate;
import com.devon1337.RPG.ActiveAbilities.Blood_Shield;
import com.devon1337.RPG.ActiveAbilities.Charge;
import com.devon1337.RPG.ActiveAbilities.Confusion;
import com.devon1337.RPG.ActiveAbilities.Entanglement;
import com.devon1337.RPG.ActiveAbilities.Fireball;
import com.devon1337.RPG.ActiveAbilities.Rejuvenate;
import com.devon1337.RPG.ActiveAbilities.Shield_Slam;
import com.devon1337.RPG.ActiveAbilities.Vanish;
import com.devon1337.RPG.ActiveAbilities.Wrath;
import com.devon1337.RPG.Classes.ClassManager;
import com.devon1337.RPG.Classes.Rogue;
import com.devon1337.RPG.Commands.CheckLevel;
import com.devon1337.RPG.Commands.FLCommand;
import com.devon1337.RPG.Commands.NFPrint;
import com.devon1337.RPG.Commands.NFQuest;
import com.devon1337.RPG.Commands.NFTest;
import com.devon1337.RPG.Commands.OpenSpellBook;
import com.devon1337.RPG.Commands.Party;
import com.devon1337.RPG.Commands.PickClass;
import com.devon1337.RPG.Commands.Roll;
import com.devon1337.RPG.Commands.RunDialog;
import com.devon1337.RPG.Commands.SimulateSpell;
import com.devon1337.RPG.Commands.Trade;
import com.devon1337.RPG.Menus.*;
import com.devon1337.RPG.NPC.Faction;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.WorldController;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

@SuppressWarnings("deprecation")
public class NightFallRPG extends JavaPlugin implements Listener {

	public static PlayerUtils Putils = new PlayerUtils();
	public static SelectClass selClass = new SelectClass();
	
	public static Rogue rogue = new Rogue();

	public Vanish vanish = new Vanish();
	public Charge charge = new Charge();
	public Assassinate assassinate = new Assassinate();
	public Confusion confusion = new Confusion();
	public Fireball fireball = new Fireball();
	public Blood_Shield bs = new Blood_Shield();
	public Shield_Slam ss = new Shield_Slam();
	public Rejuvenate reju = new Rejuvenate();
	public Entanglement et = new Entanglement();
	public Wrath wr = new Wrath();
	public ActiveAbilityManager aam = new ActiveAbilityManager();
	public static QuestTracker qt = new QuestTracker();
	public static DatabaseAccess dba = new DatabaseAccess();
	public static Simulate sim;

	public BukkitScheduler scheduler = getServer().getScheduler();

	public static Scanner input = new Scanner(System.in);

	public void onEnable() {
		
		// Factions | Note: Reputation Base Values are from the perspective of the faction and not the perspective of the player;
		WorldController.initializeFaction(new Faction("faction_mages", "mages", 1000, -1000, -100, -400, 200));
		WorldController.initializeFaction(new Faction("faction_warriors", "warriors", -100, 300, 1000, 0, -1000));
		WorldController.initializeFaction(new Faction("faction_druids", "druids", -1000, 1000, 100, 500, -800));
		WorldController.initializeFaction(new Faction("faction_rogues", "rogues", 0, 200, -100, 500, -400));
		
		
		// NPCS
		// Rogues
		WorldController.initializeNPC(new NPC("Rogue_Exit_1", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Entrance_1", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Entrance_2", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Welcome_1", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Crates_1", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_1", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_2", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_3", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_4", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_5", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_6", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_7", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_8", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_9", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_10", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_11", "temp_name"), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("Rogue_Citizen_12", "temp_name"), WorldController.getFaction("faction_rogues"));
		
		// Warriors
		WorldController.initializeNPC(new NPC("Warrior_Guard_1", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Guard_2", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Guard_3", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Guard_4", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Guard_5", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Guard_6", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Guard_1", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Guard_7", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Guard_8", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Guard_9", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Guard_10", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Citizen_1", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Citizen_2", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Citizen_3", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Citizen_4", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Citizen_5", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_Citizen_6", "temp_name"), WorldController.getFaction("faction_warriors"));
		WorldController.initializeNPC(new NPC("Warrior_King", "temp_name"), WorldController.getFaction("faction_warriors"));
		
		// Quests
		try {
			new NavigationQuest(0,0, null, 1);
			new PVPQuest(0,0, null, 1, 0);
			new TestQuest(0,0, null, 1, 0);
		} catch (QuestIDInUse e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Cinematics
		//Cinematic.loadPaths();
		//new Cinematic("WARRIOR_SPAWN", "Warrior Spawn", "warriorEntrance1", "warriorEntrance2", "warriorEntrance3");
		
		aam.init_abilities();
		DialogueSystem.init_dialog();
		gameplayScheduler(this);
		
		
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("class").setExecutor(new PickClass());
		getCommand("simulate").setExecutor(new SimulateSpell());
		getCommand("level").setExecutor(new CheckLevel());
		getCommand("spellbook").setExecutor(new OpenSpellBook());
		getCommand("roll").setExecutor(new Roll());
		getCommand("trade").setExecutor(new Trade());
		getCommand("nfparty").setExecutor(new Party());
		getCommand("nfquest").setExecutor(new NFQuest());
		getCommand("nfdialog").setExecutor(new RunDialog());
		getCommand("fl").setExecutor(new FLCommand());
		getCommand("nftest").setExecutor(new NFTest());
		getCommand("nfprint").setExecutor(new NFPrint());
	}

	public void onDisable() {

	}

	@SuppressWarnings("unused")
	private void addClassPath(final URL url) throws IOException {
		final URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		final Class<URLClassLoader> sysclass = URLClassLoader.class;
		try {
			final Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });
			method.setAccessible(true);
			method.invoke(sysloader, new Object[] { url });
		} catch (final Throwable t) {
			t.printStackTrace();
			throw new IOException("Error adding " + url + " to system classloader");
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ArrayList<String> itemMeta = new ArrayList<String>();
		itemMeta.add("dont-hide");
		
		if(event.getCurrentItem().getItemMeta().getLore().equals(itemMeta)) {
			player.sendMessage("You cannot remove that!");
			event.setCancelled(true);
		}
		
		if (event.getView().getTitle().equals(selClass.getTitle(0))) {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1.3f);
			selClass.inputResponse(event.getSlot(), player);

			if (event.getClick().equals(ClickType.NUMBER_KEY)) {
				event.setCancelled(true);
			}

			event.setCancelled(true);
		}

	}
	
	@EventHandler
	public void onDeathEvent(PlayerDeathEvent event) {
		if(event.getEntity().getKiller() instanceof Player && event.getEntity().getKiller() != null && QuestTracker.hasQuest(event.getEntity(), QuestTracker.grabQuest(3)) && QuestTracker.playersQuest(3, event.getEntity()).getStatus() != 2) {
			PVPQuest.completeStep(0, event.getEntity().getKiller(), QuestTracker.playersQuest(3, event.getEntity().getKiller()));
			QuestTracker.playersQuest(3, event.getEntity().getKiller()).setCurSteps(QuestTracker.playersQuest(3, event.getEntity().getKiller()).getCurSteps() + 1);
		}
	}
	
	@EventHandler
	public void onDropEvent(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		ArrayList<String> itemMeta = new ArrayList<String>();
		itemMeta.add("dont-hide");
		
		if(event.getItemDrop().getItemStack().getItemMeta().getLore().equals(itemMeta)) {
			player.sendMessage("You cannot remove that!");
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInventoryMoveItemEvent(InventoryMoveItemEvent event) {
		Player player = (Player) event.getInitiator().getViewers().get(0);
		ArrayList<String> itemMeta = new ArrayList<String>();
		itemMeta.add("dont-hide");
		if(event.getItem().getItemMeta().getLore().equals(itemMeta)) {
			player.sendMessage("You cannot remove that!");
			event.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onRegionEnter(RegionEnteredEvent e) {
		Player player = e.getPlayer();

		if (player instanceof Player) {
			if (e.getRegionName().equals("dspawn_int")) {
				player.sendTitle("Lundessal's Ancient Water", "", 10 * 1, 20 * 2, 10 * 1);
				player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 0.1f);
			} else if(e.getRegionName().equals("rspawn")) {
				player.sendTitle("Stonefall Abyss", "", 10 * 1, 20 * 2, 10 * 1);
				player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 0.1f);
				if(QuestTracker.hasQuest(player, QuestTracker.grabQuest(2)) && QuestTracker.playersQuest(2, player).getCurSteps() == 0) {
					NavigationQuest.completeStep(0, player, QuestTracker.playersQuest(2, player));
					QuestTracker.playersQuest(2, player).setCurSteps(1);
				}
				
			} else if(e.getRegionName().equals("mspawn")) {
				player.sendTitle("Filandrean Jewel Precinct", "", 10 * 1, 20 * 2, 10 * 1);
				player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 0.1f);
			} else if(e.getRegionName().equals("ganaboru")) {
				if(!player.hasPermission("essentials.warps.ganaboru")) {
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set essentials.warps.ganaboru true");
					player.sendMessage("A fast travel point has been added to your map!");
				}
				// int 1 - start fade timer
				// int 2 - total time
				// int 3 - end fade timer
				player.sendTitle("Zuru'Ganaboru", "", 10 * 1, 20 * 2, 10 * 1);
				player.playSound((Location) player.getWorld(), Sound.BLOCK_BEACON_ACTIVATE, 1, 0.1f);
			}
		}

	}

	public static OfflinePlayer getOfflinePlayer(String username) {
		return Bukkit.getOfflinePlayer(username);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage("");
		FriendsList.goOffline(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeave(PlayerKickEvent event) {
		event.setLeaveMessage("");
		FriendsList.goOffline(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		// Player player = event.getPlayer();
		event.setJoinMessage("");
		FriendsList.goOnline(event.getPlayer());
		
		if (!PlayerLevel.exists(event.getPlayer())) {
			PlayerLevel.setLevel(event.getPlayer(), 1);
			PlayerLevel.setXP(event.getPlayer(), 0);
			PlayerLevel.setXPRate(event.getPlayer(), 1);
		}
		event.getPlayer().sendMessage(
				"Welcome " + event.getPlayer().getDisplayName() + " level: " + PlayerLevel.getLevel(event.getPlayer()));
		// dba.testConn();

	}

	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) throws BadProjectile, ObjectsNotUsed {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();

		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if (item.getType() == Material.PHANTOM_MEMBRANE) {
				vanish.use(player);
			}

			if (item.getType() == Material.ORANGE_DYE) {
				charge.use(player);
			}

			if (item.getType() == Material.BROWN_DYE) {
				et.use(player, new Simulate(player, ProjectileType.ARROW));
			}

			if (item.getType() == Material.LIME_DYE) {
				wr.use(player, new Simulate(player, ProjectileType.ARROW, 6));
			}

			if (item.getType() == Material.WHITE_DYE) {
				new Simulate(player, ProjectileType.FIREBALL);
			}

			if (item.getType() == Material.YELLOW_DYE) {

				ss.use(player);

			}

			if (item.getType() == Material.RED_DYE) {
				if (ClassManager.getTeam(player) == NFClasses.MAGE && !fireball.Exists(player)) {
					aam.runFluidCasting(player);
				}

				fireball.use(player);
			}

			if (item.getType() == Material.PINK_DYE) {
				if (ClassManager.getTeam(player) == NFClasses.MAGE && !fireball.Exists(player)) {
					aam.runFluidCasting(player);
				}

				bs.use(player);
			}

			if (item.getType() == Material.BLUE_DYE) {

				sim = new Simulate(player, ProjectileType.ARROW);
				if (ClassManager.getTeam(player) == NFClasses.MAGE && !confusion.Exists(player)) {
					aam.runFluidCasting(player);
				}

				ArmorStand stand = player.getLocation().getWorld().spawn(player.getLocation(), ArmorStand.class);
				stand.setVisible(false);
				stand.setGravity(false);
				stand.setArms(true);
				stand.setCollidable(false);
				stand.setInvulnerable(true);
				stand.setItemInHand(new ItemStack(Material.DARK_OAK_LEAVES));

				int animate = Bukkit.getScheduler().scheduleAsyncRepeatingTask(this,
						new AnimationController(stand, player), 0, 1);

				new BukkitRunnable() {
					@Override
					public void run() {
						Bukkit.getScheduler().cancelTask(animate);
						stand.remove();
					}
				}.runTaskLater(this, 100);

				confusion.use(player, sim);
			}
		}
	}

	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
		ArrayList<Player> recips = new ArrayList<Player>();
		
		for(Player player : event.getRecipients()) {
			recips.add(player);
		}
		for(int i = 0; i < recips.size(); i++) {
            if(DialogueSystem.hasDialog(recips.get(i))) {
            		event.getRecipients().remove(recips.get(i));
            }
		}
		
		event.setCancelled(DialogueSystem.hasDialog(event.getPlayer()));
    }
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		Player player = (Player) event.getEntity().getShooter();

		if (event.getEntity() instanceof Arrow) {

			boolean raycast = event.getEntity().getMetadata("raycast").get(0).asBoolean();

			if (raycast) {
				if (event.getHitEntity() instanceof Player) {
					sim.setTarget((Player) event.getHitEntity());

					event.getEntity().remove();
					if (sim != null) {
						confusion.ability(player, sim);
						try {
							player.spawnParticle(Particle.EXPLOSION_NORMAL, sim.getTarget().getLocation(), 1, 0.0, 0.0,
									0.5);
						} catch (InvalidTarget e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		}
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player target = (Player) event.getEntity();
			Player player = (Player) event.getDamager();
			if (player.getInventory().getItemInMainHand().hasItemMeta()) {
				if (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_HOE)) {
					assassinate.use(player, target);
					event.setCancelled(true);
				}

				if (player.getInventory().getItemInMainHand().getType().equals(Material.PURPLE_DYE)) {
					reju.use(player, target);
					event.setCancelled(true);
				}
			}

		}
	}

	// Scheduled Tasks
	public void gameplayScheduler(Plugin plugin) {
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				// System.out.println("-- GLOBAL GAME CYCLE --");
				gameplayLoop();
				//rogue.roguePassiveReset();
				//rogue.roguePassive();

			}

		}, 0L, 20L);
	}

	public void doGlobalLoop() {
		//Logging.OutputToConsole(" --- Running Global Loop --- ");
		@SuppressWarnings("unchecked")
		List<Player> allPlayers = (List<Player>) Bukkit.getOnlinePlayers();
		for(int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
			NFQuest.generateNewQuestBook(allPlayers.get(i));
		}
	}
	
	public void gameplayLoop() {
		doGlobalLoop();
		vanish.updateCooldowns();
		charge.updateCooldowns();
		assassinate.updateCooldowns();
		confusion.updateCooldowns();
		fireball.updateCooldowns();
		bs.updateCooldowns();
		ss.updateCooldowns();
		reju.updateCooldowns();
	}

	public static Plugin getPlugin() {
		return NightFallRPG.getPlugin(NightFallRPG.class);
	}

	public static World getWorld() {
		return Bukkit.getWorlds().get(0);
	}

}