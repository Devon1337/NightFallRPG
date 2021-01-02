package com.devon1337.RPG.Menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.devon1337.RPG.Player.NFPlayer;
import com.devon1337.RPG.Quests.Quest;
import com.devon1337.RPG.Quests.QuestStatus;
import com.devon1337.RPG.Utils.IMenu;
import com.devon1337.RPG.Utils.InventoryAssistant;
import com.devon1337.RPG.Utils.Menu;

public class QuestMenu  extends Menu implements InventoryHolder, IMenu{

	private Inventory QM;
	public static String Title = "Quest Menu";
	ArrayList<Quest> myQuests = new ArrayList<Quest>();
	int[] scrollAmount = {0,0,0,0};
	
	public QuestMenu() {
		super(Title);
		super.setMenu(this);
	}
	
	public void init_items(NFPlayer player) {
		this.QM = Bukkit.createInventory(this, InventoryAssistant.getInventorySize(6), CreativeObjectMenu.Title);
		
		myQuests = player.getCurrentQuests();
		Material glass = null;
		
		String[] Names = {"Warrior","Rogue","Druid","Mage", "Side Quests"};
		
		for (int j = 0; j < 5; j++) {
			this.QM.setItem(2*j, createBookItem(1, Names[j]));
			for (int i = 0; i < 4; i++) {
				
				
				
				// Applies Glass
				if (j % 2 == 0) {
					glass = Material.GRAY_STAINED_GLASS_PANE;
				} else {
					glass = Material.BLACK_STAINED_GLASS_PANE;
				}

				this.QM.setItem((9 * (j+1)) + 1 + (2 * i), createGuiItem(glass, 1, " ", " "));

			}
		}

		// Assigning Quest Slots
		for(Quest q : myQuests) {
			Material mat;
			int[] index = {9,11,13,15,17};
			
			
			
			
			// Applies Material
			if(q.getStatus().equals(QuestStatus.Completed)) {
				mat = Material.FILLED_MAP;
			} else {
				mat = Material.MAP;
			}
			
			// Creates Items for UI
			if(q.getFaction() != null) {
			switch(q.getFaction().getFaction()) {
			case Warrior:
				this.QM.setItem(index[0], createQuestItem(mat, q));
				index[0] += 9;
				break;
			case Druid:
				this.QM.setItem(index[1], createQuestItem(mat, q));
				index[1] += 9;
				break;
			case Mage:
				this.QM.setItem(index[2], createQuestItem(mat, q));
				index[2] += 9;
				break;
			case Rogue:
				this.QM.setItem(index[3], createQuestItem(mat, q));
				index[3] += 9;
				break;
			default:
				
				break;
			}
			} else {
				this.QM.setItem(index[4], createQuestItem(mat, q));
				index[4] += 9;
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

	public boolean Response(NFPlayer player, int index) {
		return false;
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
