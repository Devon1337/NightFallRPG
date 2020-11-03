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
import com.devon1337.RPG.Classes.Druid;
import com.devon1337.RPG.Classes.Mage;
import com.devon1337.RPG.Classes.Rogue;
import com.devon1337.RPG.Classes.Warrior;
import com.devon1337.RPG.Commands.CheckLevel;
import com.devon1337.RPG.Commands.FLCommand;
import com.devon1337.RPG.Commands.NFGetDialog;
import com.devon1337.RPG.Commands.NFInteractWith;
import com.devon1337.RPG.Commands.NFListNPC;
import com.devon1337.RPG.Commands.NFMessage;
import com.devon1337.RPG.Commands.NFPrint;
import com.devon1337.RPG.Commands.NFQuest;
import com.devon1337.RPG.Commands.NFRaidMaker;
import com.devon1337.RPG.Commands.NFTest;
import com.devon1337.RPG.Commands.NFTravel;
import com.devon1337.RPG.Commands.NFUpdateDialog;
import com.devon1337.RPG.Commands.OpenSpellBook;
import com.devon1337.RPG.Commands.Party;
import com.devon1337.RPG.Commands.PickClass;
import com.devon1337.RPG.Commands.Reply;
import com.devon1337.RPG.Commands.Roll;
import com.devon1337.RPG.Commands.Trade;
import com.devon1337.RPG.Debugging.Logging;
import com.devon1337.RPG.Menus.SelectClass;
import com.devon1337.RPG.NPC.NPC;
import com.devon1337.RPG.NPC.WorldController;
import com.devon1337.RPG.Objects.EasterEggs.CappaTheShopStand;
import com.devon1337.RPG.PassiveAbilities.Lifesteal;
import com.devon1337.RPG.Player.DatabaseAccess;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Player.PlayerUtils;
import com.devon1337.RPG.Quests.NavigationQuest;
import com.devon1337.RPG.Quests.PVPQuest;
import com.devon1337.RPG.Quests.QuestTracker;
import com.devon1337.RPG.Quests.Exceptions.QuestIDInUse;
import com.devon1337.RPG.Utils.DialogueSystem;
import com.devon1337.RPG.Utils.FileManager;
import com.devon1337.RPG.Utils.FriendsList;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.MOTD;
import com.devon1337.RPG.Utils.Raycast.Exceptions.BadProjectile;
import com.devon1337.RPG.Utils.Raycast.Exceptions.ObjectsNotUsed;
//import com.devon1337.RPG.Utils.Raycast.Raycast;
import com.devon1337.RPG.Utils.Raycast.RaycastHitEvent;
import com.devon1337.RPG.raid.MatchmakingController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
//import org.bukkit.persistence.PersistentDataContainer;
//import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class NightFallRPG extends JavaPlugin implements Listener {
	public static PlayerUtils Putils = new PlayerUtils();

	public static QuestTracker qt = new QuestTracker();
	public static DatabaseAccess dba = new DatabaseAccess();
	public BukkitScheduler scheduler = getServer().getScheduler();

	public static Scanner input = new Scanner(System.in);

	public void onEnable() {
		DialogueSystem.init_dialog();

		WorldController.initializeNPC(new NPC("ROGUE_EXIT1", "Ragar the dumb", DialogueSystem.getNPCDialog("ROGUE_EXIT1")), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("ROGUE_ENTRANCE1", "temp_name", DialogueSystem.getNPCDialog("ROGUE_ENTRANCE1")), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("ROGUE_ENTRANCE2", "temp_name", DialogueSystem.getNPCDialog("ROGUE_ENTRANCE2")), WorldController.getFaction("faction_rogues"));
		WorldController.initializeNPC(new NPC("ROGUE_WELCOME1", "temp_name", DialogueSystem.getNPCDialog("ROGUE_WELCOME1")), WorldController.getFaction("faction_rogues"));

		WorldController.initializeNPC(new NPC("ROGUE_CITIZEN4", "temp_name", DialogueSystem.getNPCDialog("ROGUE_CITIZEN4")), WorldController.getFaction("faction_rogues"));

		WorldController.initializeNPC(new NPC("WARRIOR_KING", "Warrior King", DialogueSystem.getNPCDialog("WARRIOR_KING")), WorldController.getFaction("faction_warriors"));

		WorldController.initializeNPC(new NPC("SZ_CLASS_SELECT", "Spooky Mage", DialogueSystem.getNPCDialog("SZ_CLASS_SELECT")), null);
		WorldController.initializeNPC(new NPC("MAGE_CITIZEN2", "Charles", DialogueSystem.getNPCDialog("MAGE_CITIZEN2")), WorldController.getFaction("faction_mage"));

		WorldController.initializeNPC(new NPC("MOOSHROOM_NPC", "Bessie", DialogueSystem.getNPCDialog("MOOSHROOM_NPC")), null);
		
		// Load order
		
		new Assassinate(0);
		new Blood_Shield(1);
		new Charge(2);
		new Confusion(3);
		new Entanglement(4);
		new Fireball(5);
		new MOTW(6);
		new Rejuvenate(7);
		new Shield_Slam(8);
		new Starfire(9);
		new Tranquility(10);
		new Vanish(11);
		new Wrath(12);
		new NightmareSlasher(13);
		new Shield_Bash(14);
		new Empty(15);
		new HeatedJuggernaut(16);
		new Explosive_Strike(17);
		new Plague_Touch(18);
		new Lifesteal();
		
		new Druid();
		new Mage();
		new Warrior();
		new Rogue();
		
		try {
			new NavigationQuest(45, 0, null, 0);
		} catch (QuestIDInUse e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DialogueSystem.init_dialog();
		gameplayScheduler((Plugin) this);

		CappaTheShopStand.init_dialog();

		getServer().getPluginManager().registerEvents(this, (Plugin) this);
		getCommand("class").setExecutor((CommandExecutor) new PickClass());
		getCommand("level").setExecutor((CommandExecutor) new CheckLevel());
		getCommand("spellbook").setExecutor((CommandExecutor) new OpenSpellBook());
		getCommand("roll").setExecutor((CommandExecutor) new Roll());
		getCommand("trade").setExecutor((CommandExecutor) new Trade());
		getCommand("nfparty").setExecutor((CommandExecutor) new Party());
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
		getCommand("nfinteractwith").setExecutor((CommandExecutor) new NFInteractWith());
		
		FileManager.exportNpc();
		FileManager.exportDialog();
		gameplayScheduler(this);
	}

	public void onDisable() {
	}
	
	@EventHandler
	public void onHeldEvent(PlayerItemHeldEvent event) {
		Player player = (Player) event.getPlayer();
		if(event.getNewSlot() < 4) {
		for(Spell s : GlobalSpellbook.getSpells()) {
			
			
			
			if(s.getItem().equals(event.getPlayer().getInventory().getItem(event.getNewSlot())) && s.getSpellType() != SpellType.WeaponArt) {
				Logging.OutputToPlayer("Spell: " + s.getName(), player);
				Spell.start(s, player);
				event.setCancelled(true);
			}
		}
	}
	}
	
	// Inventory Response Handler
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		//Player player = (Player) event.getWhoClicked();
		
		if(event.getView().getTitle() == SelectClass.getTitle()) {
			SelectClass.response(event.getCurrentItem(),(Player) event.getWhoClicked());
			event.setCancelled(true);
		} 
	}

	@EventHandler
	public void onDeathEvent(PlayerDeathEvent event) {
		
		
		
		// Specific Quest
		if (event.getEntity().getKiller() instanceof Player && event.getEntity().getKiller() != null
				&& QuestTracker.hasQuest(event.getEntity(), QuestTracker.grabQuest(3))
				&& QuestTracker.playersQuest(3, event.getEntity()).getStatus() != 2) {
			PVPQuest.completeStep(0, event.getEntity().getKiller(),
					QuestTracker.playersQuest(3, event.getEntity().getKiller()));
			QuestTracker.playersQuest(3, event.getEntity().getKiller())
					.setCurSteps(QuestTracker.playersQuest(3, event.getEntity().getKiller()).getCurSteps() + 1);
		}
	}
	
	@EventHandler 
	public void onRespawnEvent(PlayerRespawnEvent event) {
		
		// Resetting HP to Max
		NFPlayer.getPlayer(event.getPlayer().getUniqueId()).setHp(NFPlayer.getPlayer(event.getPlayer().getUniqueId()).getMaxHp());
	}
	
	@EventHandler
	public void onRegen(EntityRegainHealthEvent event) {
		
		// Adjusts players HP for healing
		if(event.getEntity() instanceof Player) {
			NFPlayer p = NFPlayer.getPlayer(event.getEntity().getUniqueId());
			p.setHp(event.getAmount() + p.getHp());
		}
	}
	
	@EventHandler
	public void onDamageEvent(EntityDamageEvent event) {
		
		Player player = (Player) event.getEntity();
		
		// Adjusts players HP for damage Taken from non-nfrpg means
		if(player != null) {
			if(((player.getHealth() - event.getDamage()) < 1) && (NFPlayer.getPlayer(player.getUniqueId()).getHp() - event.getDamage() > 1)) {
				event.setCancelled(true);
			}
			
			NFPlayer p = NFPlayer.getPlayer(event.getEntity().getUniqueId());
			p.setHp(p.getHp() - event.getDamage());
		}
		
	}

	@EventHandler
	public void onDropEvent(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		ArrayList<String> itemMeta = new ArrayList<>();
		itemMeta.add("dont-hide");

		if (event.getItemDrop().getItemStack().getItemMeta().getLore().equals(itemMeta)) {
			player.sendMessage("You cannot remove that!");
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInventoryMoveItemEvent(InventoryMoveItemEvent event) {
		Player player = (Player) event.getInitiator().getViewers().get(0);
		ArrayList<String> itemMeta = new ArrayList<>();
		itemMeta.add("dont-hide");
		
		if (event.getItem().getItemMeta().getLore().equals(itemMeta)) {
			player.sendMessage("You cannot remove that!");
			event.setCancelled(true);
		}
		
	}

	@EventHandler
	public void onRegionEnter(RegionEnteredEvent e) {
		Player player = e.getPlayer();

		if (player instanceof Player) {
			if (e.getRegionName().equals("dspawn_int")) {
				//player.sendTitle("Lundessal's Ancient Water", "", 10, 40, 10);
				//player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0F, 0.1F);
			} else if (e.getRegionName().equals("rspawn")) {
				player.sendTitle("Stonefall Abyss", "", 10, 40, 10);
				player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0F, 0.1F);
				if (QuestTracker.hasQuest(player, QuestTracker.grabQuest(2))
						&& QuestTracker.playersQuest(2, player).getCurSteps() == 0) {
					NavigationQuest.completeStep(0, player, QuestTracker.playersQuest(2, player));
					QuestTracker.playersQuest(2, player).setCurSteps(1);
				}

			} else if (e.getRegionName().equals("mspawn")) {
				player.sendTitle("Filandrean Jewel Precinct", "", 10, 40, 10);
				player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0F, 0.1F);
			} else if (e.getRegionName().equals("ganaboru")) {
				if (!player.hasPermission("essentials.warps.ganaboru")) {
					Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getServer().getConsoleSender(),
							"lp user " + player.getName() + " permission set essentials.warps.ganaboru true");
					player.sendMessage("A fast travel point has been added to your map!");
				}

				player.sendTitle("Zuru'Ganaboru", "", 10, 40, 10);
				player.playSound((Location) player.getWorld(), Sound.BLOCK_BEACON_ACTIVATE, 1.0F, 0.1F);
			}
		}
	}

	@SuppressWarnings("deprecation")
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
			new NFPlayer(NFClasses.NOCLASS, event.getPlayer().getName(), event.getPlayer().getUniqueId());
			InventoryAssistant.initializeInventory(event.getPlayer());
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
	public void onPlayerUse(PlayerInteractEvent event) throws BadProjectile, ObjectsNotUsed {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();

		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(item.getType() == Material.IRON_SWORD) {
				player.getInventory().setItemInMainHand(NightmareSlasher.getWeapon());
				//new Raycast(player.getUniqueId(), GlobalSpellbook.getSpells().get(14));
			} 
			
			if(item.getType() == Material.DIAMOND_AXE) {
				MOTW.use(player);
			}
			
			
			/*
			if (item.getType() == Material.BLUE_DYE) {

				sim = new Simulate(player, ProjectileType.ARROW);
				if (ClassManager.getTeam(player) == NFClasses.MAGE && !this.confusion.Exists(player)) {
					//this.aam.runFluidCasting(player);
				}

				
				final ArmorStand stand = (ArmorStand) player.getLocation().getWorld().spawn(player.getLocation(),
						ArmorStand.class);
				stand.setVisible(false);
				stand.setGravity(false);
				stand.setArms(true);
				stand.setCollidable(false);
				stand.setInvulnerable(true);
				stand.setItemInHand(new ItemStack(Material.DARK_OAK_LEAVES));

				@SuppressWarnings("deprecation")
				final int animate = Bukkit.getScheduler().scheduleAsyncRepeatingTask((Plugin) this,
						(Runnable) new AnimationController(stand, player), 0L, 1L);

				(new BukkitRunnable() {
					public void run() {
						Bukkit.getScheduler().cancelTask(animate);
						stand.remove();
					}
				}).runTaskLater((Plugin) this, 100L);

				this.confusion.use(player, sim);
			}
			*/
		}
	}

	@EventHandler
	public void onRaycastHit(RaycastHitEvent e) {
		Logging.OutputToConsole("Shoot");
		ArrayList<Player> targets = new ArrayList<Player>();
		targets.add(Bukkit.getPlayer(e.getTarget()));
		Spell.runAbility(e.getSpell(), Bukkit.getPlayer(e.getShooter()), targets);
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
		
		/* Old lifesteal
		if(event.getEntity() instanceof Player) {
		// TODO: Middle stage
		Lifesteal.run((Player) event.getDamager(), (Player) event.getEntity(), event.getDamage(), 1.0f);
		
		}
		*/
		
		
		
		
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			
			PersistentDataContainer container = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();
			
			// Nightmare Slasher
			if(container.has(NightmareSlasher.getKey(), PersistentDataType.INTEGER) && intToBool(container.get(NightmareSlasher.getKey(), PersistentDataType.INTEGER))) {
				NightmareSlasher.Slash(player, (Player) event.getEntity());
			}
			
			for(Spell s : GlobalSpellbook.getSpells()) {
			// Weapon Arts
			if(s.getSpellType() == SpellType.WeaponArt) {
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
				
				for(Spell s : GlobalSpellbook.getSpells()) {
					s.runCooldown();
				}
				
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