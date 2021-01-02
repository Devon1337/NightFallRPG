package com.devon1337.RPG.Objects.SelectClass;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.devon1337.RPG.Objects.NFObject;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class RogueClassBook extends NFObject {

	static String Name = "Rogue Class Book";

	static String Description = "Become a Rogue!";
	
	static ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
	static BookMeta bookMeta = (BookMeta) book.getItemMeta();
	List<BaseComponent[]> pages = bookMeta.spigot().getPages();
	
	public RogueClassBook() {
		super(InitBook(), Name, Description);
	}
	
	// Creates the book
	public static ItemStack InitBook() {
		bookMeta.setTitle("Rogue_Class_Book");
		bookMeta.setAuthor("Author");
		BaseComponent[] page = new ComponentBuilder("Join Rogue")
				.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp Rogue_Start"))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Edge boy").create()))
				.create();
		
		bookMeta.spigot().addPage(page);
		book.setItemMeta(bookMeta);
		return book;
	}
}
