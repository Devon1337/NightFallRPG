package com.devon1337.RPG.Menus;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.NPC.AllFactions;
import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.QuestStatus;
import com.devon1337.RPG.Quests.Step;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

public class QuestMenu extends Menu implements InventoryHolder, IMenu {

	private Inventory QM;
	public static String Title = "Quest Menu";
	ArrayList<Quest> myQuests = new ArrayList<Quest>();

	ArrayList<Quest> warriorScroll, druidScroll, mageScroll, rogueScroll, secretScroll;
	HashMap<Integer, Quest> inventorySlot = new HashMap<Integer, Quest>();
	
	ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
	BookMeta bookMeta = (BookMeta) book.getItemMeta();

	int[] scrollAmount = { 0, 0, 0, 0, 0};

	public QuestMenu() {
		super(Title, false, null);
		super.setMenu(this);
	}

	public void init_items(NFPlayer player) {
		this.QM = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(6), Title);

		myQuests = player.getQuests();
		Material glass = null;

		String[] Names = { "Warrior", "Rogue", "Druid", "Mage", "Side Quests" };

		for (int j = 0; j < 5; j++) {
			this.QM.setItem(2 * j, createBookItem(1, Names[j]));
			for (int i = 0; i < 4; i++) {

				// Applies Glass
				if (j % 2 == 0) {
					glass = Material.GRAY_STAINED_GLASS_PANE;
				} else {
					glass = Material.BLACK_STAINED_GLASS_PANE;
				}

				this.QM.setItem((9 * (j + 1)) + 1 + (2 * i), createGuiItem(glass, 1, " ", " "));

			}
		}
		warriorScroll = Quest.getAllQuestsFromFaction(AllFactions.Warrior);
		druidScroll = Quest.getAllQuestsFromFaction(AllFactions.Druid);
		rogueScroll = Quest.getAllQuestsFromFaction(AllFactions.Rogue);
		mageScroll = Quest.getAllQuestsFromFaction(AllFactions.Mage);
		secretScroll = Quest.getAllQuestsFromFaction(null);
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				switch (i) {
				case 0:
					if (j < warriorScroll.size()) {
						inventorySlot.put(9 * (1 + j) + (i * 2), warriorScroll.get(j));
						this.QM.setItem(9 * (1 + j) + (i * 2),
								createQuestItem(getItem(player, warriorScroll.get(j)), warriorScroll.get(j)));
					}
					break;
				case 1:
					if (j < rogueScroll.size()) {
						inventorySlot.put(9 * (1 + j) + (i * 2), rogueScroll.get(j));
						this.QM.setItem(9 * (1 + j) + (i * 2),
								createQuestItem(getItem(player, rogueScroll.get(j)), rogueScroll.get(j)));
					}
					break;
				case 2:
					if (j < druidScroll.size()) {
						inventorySlot.put(9 * (1 + j) + (i * 2), druidScroll.get(j));
						this.QM.setItem(9 * (1 + j) + (i * 2),
								createQuestItem(getItem(player, druidScroll.get(j)), druidScroll.get(j)));
					}
					break;
				case 3:
					if (j < mageScroll.size()) {
						inventorySlot.put(9 * (1 + j) + (i * 2), mageScroll.get(j));
						this.QM.setItem(9 * (1 + j) + (i * 2),
								createQuestItem(getItem(player, mageScroll.get(j)), mageScroll.get(j)));
					}
					break;
				case 4:
					if (j < secretScroll.size()) {
						inventorySlot.put(9 * (1 + j) + (i * 2), secretScroll.get(j));
						this.QM.setItem(9 * (1 + j) + (i * 2),
								createQuestItem(getItem(player, secretScroll.get(j)), secretScroll.get(j)));
					}
					break;
				}
			}
		}

	}
	
	private ItemStack createQuestItem(Material material, Quest quest) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(quest.getName());
		ArrayList<String> metalore = new ArrayList<>();
		
		metalore.add("Quest Description: ");
		metalore.add(quest.getDescription());

		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });

		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}
	
	private ItemStack createBookItem(int amount, String name) {
		ItemStack item = new ItemStack(Material.BOOK, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("QuestBook");
		ArrayList<String> metalore = new ArrayList<>();
		
		metalore.add(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "CLASS: " + ChatColor.GOLD + "" + ChatColor.BOLD + name);
		metalore.add("You have completed 0/6 quests for " + name + ".");

		
		
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });

		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}
	
	private ItemStack createGuiItem(Material material, int amount, String name, String Description) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> metalore = new ArrayList<>();
		
		metalore.add(Description);

		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });

		meta.setLore(metalore);
		item.setItemMeta(meta);
		return item;
	}

	public Material getItem(NFPlayer player, Quest q) {
		Material mat;
		
		if (player.getQuestFromTags(q.getTag()) != null) {
			QuestStatus status = player.getQuestFromTags(q.getTag()).getStatus();
			switch (status) {
			case Completed:
				mat = Material.BOOK;
				break;
			case Incomplete:
				mat = Material.FILLED_MAP;
				break;
			case Failed:
				mat = Material.BARRIER;
				break;
			case Available:
				mat = Material.MAP;
			default:
				mat = Material.MAP;
				break;
			}
		} else {
			mat = Material.MAP;
		}
		
		return mat;
	}
	
	public boolean Response(NFPlayer player, int index) {
		//Logging.OutputToPlayer("Player has quest: " + player.getCurrentQuests().contains(this.inventorySlot.get(index)), player.getPlayer());
		if(this.inventorySlot.containsKey(index) && player.getQuestFromTags(this.inventorySlot.get(index).getTag()) != null) {
			QuestSubMenu qsm = new QuestSubMenu();
			qsm.setQ(player.getQuestFromTags(this.inventorySlot.get(index).getTag()));
			Menu menu = qsm;
			player.getPlayer().closeInventory();
			menu.openNFInventory(player);
			return true;
		}
		
		return false;
	}
	
	public void generatePage(Quest q) {
			ArrayList<String> pages = new ArrayList<String>();
			String temp = "";
			
			for(Step s : q.getSteps()) {
				temp += "/n - " + s.getTitle();
			}
			pages.add("     " + q.getName() + "/n" + temp);
			bookMeta.setPages(pages);
			book.setItemMeta(bookMeta);
			
	}

	public Inventory open(Player player) {
		init_items(NFPlayer.getPlayer(player.getUniqueId()));
		return this.QM;
	}
	
	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return this.QM;
	}
	
	public IMenu getIMenu() {
		return (IMenu) this;
	}

	@Override
	public int getPage() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
