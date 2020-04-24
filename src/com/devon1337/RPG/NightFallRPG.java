package com.devon1337.RPG;

import com.devon1337.RPG.Player.*;
import com.devon1337.RPG.Utils.Raycast.ProjectileType;
import com.devon1337.RPG.Utils.Raycast.Simulate;
import com.devon1337.RPG.Utils.Raycast.Exceptions.BadProjectile;
import com.devon1337.RPG.Utils.Raycast.Exceptions.ObjectsNotUsed;
import com.devon1337.RPG.ActiveAbilities.Assassinate;
import com.devon1337.RPG.ActiveAbilities.Charge;
import com.devon1337.RPG.ActiveAbilities.Vanish;
import com.devon1337.RPG.Classes.Rogue;
import com.devon1337.RPG.Commands.CheckLevel;
import com.devon1337.RPG.Commands.PickClass;
import com.devon1337.RPG.Commands.SimulateSpell;
import com.devon1337.RPG.Menus.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
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
	public static DatabaseAccess dba = new DatabaseAccess();
	
	
	
	public BukkitScheduler scheduler = getServer().getScheduler();

	public static Scanner input = new Scanner(System.in);

	public void onEnable() {
		gameplayScheduler(this);
		
		dba.initClassLoader();
		
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("class").setExecutor(new PickClass());
		getCommand("simulate").setExecutor(new SimulateSpell());
		getCommand("level").setExecutor(new CheckLevel());
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
		Player player = event.getPlayer();
		
		try {
			if(!dba.exists(player.getName())) {
				try {
					dba.createProfile(player);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.sendMessage("Profile created!");
			} else {
				player.sendMessage("You are already in the database!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) throws BadProjectile, ObjectsNotUsed {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();

		if (event.getAction() == Action.RIGHT_CLICK_AIR) {

			if (item.getType() == Material.WHITE_DYE) {
				@SuppressWarnings("unused")
				Simulate raycast = new Simulate(player, ProjectileType.FIREBALL);
			}
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		@SuppressWarnings("unused")
		Player player = (Player) event.getEntity().getShooter();
		
		if(event.getEntity() instanceof Arrow) {
			
			boolean raycast = event.getEntity().getMetadata("raycast").get(0).asBoolean();
			if(raycast) {
				event.getEntity().remove();
				
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
				//System.out.println("-- GLOBAL GAME CYCLE --");
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
	}

	public static Plugin getPlugin() {
		return NightFallRPG.getPlugin(NightFallRPG.class);
	}
	
	public static World getWorld() {
		return Bukkit.getWorlds().get(0);
	}
	
}