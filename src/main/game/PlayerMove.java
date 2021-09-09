package main.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class PlayerMove implements Listener {
	public boolean canStep;
	@EventHandler
    public void onPlayerMove (PlayerMoveEvent event) {
	
		
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		World targetWorld = loc.getWorld();
		loc.setY(loc.getY()-1);
		
		//String blockName = loc.getBlock().getType().toString();

		if(canStep) {
			targetWorld.getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).setType(Material.GRASS_BLOCK);
		}
		

	}
}
