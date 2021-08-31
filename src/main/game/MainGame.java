package main.game;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class Main extends JavaPlugin{
	
	@Override
    public void onPlayerMove(PlayerMoveEvent event) {
		super onPlayerMove();
		if (event.isCancelled()) {
            return;
            
        }
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		loc.setY(loc.getY()-1);
		if(loc.getBlock().getType().equals(Material.GRASS_BLOCK)){
		//CODE HERE
			player.sendMessage("stepping on grass");
		}
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
		return false;
	}

}
