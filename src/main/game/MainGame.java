package main.game;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.EventListener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;

public class MainGame extends JavaPlugin{

	private String steps;
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		
	}
	
	public MainGame() {
		steps = "false";
		
	}
	public String isSteped() {
        return steps;
    }
	public void setStarted(final String started) {
        this.steps = started;
    }

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args )
	{
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
		else if()
		return false;
	}

}
