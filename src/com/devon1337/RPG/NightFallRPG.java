package com.devon1337.RPG;

import com.devon1337.RPG.Player.*;
import com.devon1337.RPG.Utils.Raycast.ProjectileType;
import com.devon1337.RPG.Utils.Raycast.Simulate;
import com.devon1337.RPG.Utils.Raycast.Exceptions.BadProjectile;
import com.devon1337.RPG.Utils.Raycast.Exceptions.InvalidTarget;
import com.devon1337.RPG.Utils.Raycast.Exceptions.ObjectsNotUsed;
import com.devon1337.RPG.ActiveAbilities.ActiveAbilityManager;
import com.devon1337.RPG.ActiveAbilities.Assassinate;
import com.devon1337.RPG.ActiveAbilities.Blood_Shield;
import com.devon1337.RPG.ActiveAbilities.Charge;
import com.devon1337.RPG.ActiveAbilities.Confusion;
import com.devon1337.RPG.ActiveAbilities.Fireball;
import com.devon1337.RPG.ActiveAbilities.Vanish;
import com.devon1337.RPG.Classes.ClassManager;
import com.devon1337.RPG.Classes.Rogue;
import com.devon1337.RPG.Commands.CheckLevel;
import com.devon1337.RPG.Commands.OpenSpellBook;
import com.devon1337.RPG.Commands.Party;
import com.devon1337.RPG.Commands.PickClass;
import com.devon1337.RPG.Commands.Roll;
import com.devon1337.RPG.Commands.SimulateSpell;
import com.devon1337.RPG.Commands.Trade;
import com.devon1337.RPG.Menus.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

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
	public ActiveAbilityManager aam = new ActiveAbilityManager();
	public static DatabaseAccess dba = new DatabaseAccess();
	public static Simulate sim;
	
	public BukkitScheduler scheduler = getServer().getScheduler();

	public static Scanner input = new Scanner(System.in);

	public void onEnable() {
		aam.init_abilities();

		gameplayScheduler(this);

		getServer().getPluginManager().registerEvents(this, this);
		getCommand("class").setExecutor(new PickClass());
		getCommand("simulate").setExecutor(new SimulateSpell());
		getCommand("level").setExecutor(new CheckLevel());
		getCommand("spellbook").setExecutor(new OpenSpellBook());
		getCommand("roll").setExecutor(new Roll());
		getCommand("trade").setExecutor(new Trade());
		getCommand("nfparty").setExecutor(new Party());
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

		if (event.getView().getTitle().equals(selClass.getTitle())) {

			selClass.inputResponse(event.getSlot(), player);

			if (event.getClick().equals(ClickType.NUMBER_KEY)) {
				event.setCancelled(true);
			}

			event.setCancelled(true);
		}

	}

	@SuppressWarnings("deprecation")
	public static OfflinePlayer getOfflinePlayer(String username) {
		return Bukkit.getOfflinePlayer(username);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		// Player player = event.getPlayer();
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

		if (event.getAction() == Action.RIGHT_CLICK_AIR) {

			if (item.getType() == Material.ORANGE_DYE) {
				charge.use(player);
			}
			
			if (item.getType() == Material.WHITE_DYE) {
				@SuppressWarnings("unused")
				Simulate raycast = new Simulate(player, ProjectileType.FIREBALL);
			}

			if (item.getType() == Material.RED_DYE) {
				if (ClassManager.getTeam(player) == NFClasses.MAGE
						&& !fireball.Exists(player)) {
					aam.runFluidCasting(player);
				}
				
				fireball.use(player);
			}
			
			if (item.getType() == Material.PINK_DYE) {
				if (ClassManager.getTeam(player) == NFClasses.MAGE
						&& !fireball.Exists(player)) {
					aam.runFluidCasting(player);
				}
				
				bs.use(player);
			}
			
			if (item.getType() == Material.BLUE_DYE) {

				sim = new Simulate(player, ProjectileType.ARROW);
				if (ClassManager.getTeam(player) == NFClasses.MAGE
						&& !confusion.Exists(player)) {
					aam.runFluidCasting(player);
				}
				confusion.use(player, sim);
			}
		}
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
							player.spawnParticle(Particle.EXPLOSION_NORMAL, sim.getTarget().getLocation(), 1, 0.0, 0.0, 0.5);
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
			player.sendMessage("Target: " + target.getName());
			player.sendMessage("HitWithItem: " + player.getInventory().getItemInMainHand().getType());
			if (player.getInventory().getItemInMainHand().hasItemMeta()) {
				if (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_HOE)) {
					assassinate.use(player, target);
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
				rogue.roguePassiveReset();
				rogue.roguePassive();

			}

		}, 0L, 20L);
	}

	public void gameplayLoop() {
		vanish.updateCooldowns();
		charge.updateCooldowns();
		assassinate.updateCooldowns();
		confusion.updateCooldowns();
		fireball.updateCooldowns();
		bs.updateCooldowns();
	}

	public static Plugin getPlugin() {
		return NightFallRPG.getPlugin(NightFallRPG.class);
	}

	public static World getWorld() {
		return Bukkit.getWorlds().get(0);
	}

}