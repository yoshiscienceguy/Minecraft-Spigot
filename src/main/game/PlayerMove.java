package main.game;

import java.util.Random;

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
	
		Material[] wools = {Material.BLACK_WOOL,
							Material.BLUE_WOOL,
							Material.BROWN_WOOL,
							Material.CYAN_WOOL
							};
		int rnd = new Random().nextInt(wools.length);
		
		Material rWool = wools[rnd];
		
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		World targetWorld = loc.getWorld();
		loc.setY(loc.getY()-1);
		
		//String blockName = loc.getBlock().getType().toString();

		if(canStep) {
			targetWorld.getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()).setType(rWool);
		}
		

	}
}
