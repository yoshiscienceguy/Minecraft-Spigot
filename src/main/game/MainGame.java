package main.game;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;

public class MainGame extends JavaPlugin{
	private static MainGame instance;
	public PlayerMove pm;
	@Override
	public void onEnable() {
		instance = this;
		pm = new PlayerMove();
		getServer().getPluginManager().registerEvents(pm, this);
		
	}
	public static MainGame getInstance() {
		return instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args )
	{
		if(label.equalsIgnoreCase("steps")) {
			pm.canStep = !pm.canStep;
		}
		if(label.equalsIgnoreCase("hello")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage("Hello world!");
				return true;
				
			}
			
		}
		else if(label.equalsIgnoreCase("sword")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
				item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
				player.getInventory().addItem(item);
				

				return true;
				
			}
			
		}
		else if(label.equalsIgnoreCase("make")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				Location loc = player.getLocation();
				World targetWorld = loc.getWorld();
				
				int width = Integer.parseInt(args[0]);
				int length = Integer.parseInt(args[1]);
				int height = Integer.parseInt(args[2]);
				
				int middleWidth = width / 2;
				int middleLength = length /2 ;
				int middleHeight = height /2 ;
				
				for(int x = 0; x < width; x++) {
					for(int y = 0; y< length; y++) {
						for(int z = 0; z<height; z++)
						{
							if(y == 0) {
								
								targetWorld.getBlockAt(loc.getBlockX()+x - middleWidth,loc.getBlockY()+y - middleLength,loc.getBlockZ()+z - middleHeight).setType(Material.GRASS_BLOCK);
							}
							else {
								targetWorld.getBlockAt(loc.getBlockX()+x - middleWidth,loc.getBlockY()+y - middleLength,loc.getBlockZ()+z - middleHeight).setType(Material.AIR);
							}
							
						}
					}
					
					
				}
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
				World targetWorld = loc.getWorld();
				
				
				
				player.sendTitle(ChatColor.AQUA +"Simon Says...","Get Ready!", 10, 70, 20); 
		        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent("Get Ready!"));
		        
		        SimonSays game = new SimonSays(loc);
				game.resetArena();
				// Defiantly gonna use lambda, or this would look reaaalllly bad
				
				CountdownTimer timer = new CountdownTimer(getInstance(),
				        10,
				        () -> Bukkit.broadcastMessage(ChatColor.YELLOW + "Timer is commencing. Get ready!"),
				        () -> {
				            Bukkit.broadcastMessage(ChatColor.YELLOW + "Timer is up!");
				            
							game.setLavaBlocks();
				        },
				        (t) -> {Bukkit.broadcastMessage(ChatColor.YELLOW + "Time left: " + (t.getSecondsLeft()) + "/" + (t.getTotalSeconds()) + " seconds");
						        }

				);

				// Start scheduling, don't use the "run" method unless you want to skip a second
				timer.scheduleTimer();
				
				
					
				}
				return true;
				
			}
		return false;
	}

}
