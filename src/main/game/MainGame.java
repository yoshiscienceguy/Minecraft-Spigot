package main.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class MainGame extends JavaPlugin{
	private static MainGame instance;
	public PlayerMove pm;
	public SimonSays game;
	@Override
	public void onEnable() {
		instance = this;
		pm=new PlayerMove();
		getServer().getPluginManager().registerEvents(pm, this);
	}
	public static MainGame getInstance() {
		return instance;
	}
	//Test Test Test Test TestTest 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("hello")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage("Hello world!");
				return true;
			}
		}
		else if (label.equalsIgnoreCase("bow")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack item = new ItemStack(Material.BOW,1);
				item.addUnsafeEnchantment(Enchantment.KNOCKBACK,255);
				item.addUnsafeEnchantment(Enchantment.DURABILITY, 255);
				item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK,255);
				player.getInventory().addItem(item);
				return true;
			}
		}
		else if (label.equalsIgnoreCase("op_sword")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack item = new ItemStack(Material.NETHERITE_SWORD,1);
				item.addUnsafeEnchantment(Enchantment.DURABILITY,255);
				item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,255);
				player.getInventory().addItem(item);
				return true;
			}
		}
		else if (label.equalsIgnoreCase("god_apple")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE,128);
				item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,255);
				item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT,255);
				item.addUnsafeEnchantment(Enchantment.DIG_SPEED,255);
				player.getInventory().addItem(item);
				return true;
			}
		}
		else if (label.equalsIgnoreCase("pickaxe")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE,1);
				item.addUnsafeEnchantment(Enchantment.DIG_SPEED,255);
				item.addUnsafeEnchantment(Enchantment.DURABILITY,255);
				item.addUnsafeEnchantment(Enchantment.MENDING,255);
				player.getInventory().addItem(item);
				return true;
			}
		}
		else if (label.equalsIgnoreCase("canwalk")) {
			pm.canwalk=!pm.canwalk;
		}
		else if (label.equalsIgnoreCase("clear_world")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Location loc = player.getLocation();
				World targetWorld = loc.getWorld();
				
				int width = Integer.parseInt(args[0]);
				int height = Integer.parseInt(args[1]);
				int length = Integer.parseInt(args[2]);
				for (int x=0; x<width;x++) {
					for(int y=0; y<height;y++) {
						for(int z=0;z<length;z++) {
							targetWorld.getBlockAt(loc.getBlockX()+x-width/2,loc.getBlockY()+y,loc.getBlockZ()+z-length/2).setType(Material.AIR);
						}
					}
				}
				for (int x=0; x<width;x++) {
					for (int z=0; z<length;z++) {
							targetWorld.getBlockAt(loc.getBlockX()+x-width/2,loc.getBlockY()-1,loc.getBlockZ()+z-length/2).setType(Material.GRASS_BLOCK);
					}
				}
				return true;
			}
		}
		else if(label.equalsIgnoreCase("arenastop")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if (!player.isOp()) {
					return false;
				}
				game.stopgame();
				return true;
				
			}
		}
		else if(label.equalsIgnoreCase("arena")) {
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if (!player.isOp()) {
					return false;
				}
				
				Location loc = player.getLocation();
				pm.arenaCenter=loc;
				
				
				player.sendTitle(ChatColor.AQUA +"Simon Says...","Get Ready!", 10, 70, 20); 
		        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent("Get Ready!"));
		        
		        game = new SimonSays(loc,getInstance());
				game.startgame();
				
				
				
					
				}
				return true;
				
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return false;
	}
}
