package main.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
	@EventHandler
    public void onPlayerMove (PlayerMoveEvent event) {
		

		Player player = event.getPlayer();
		Location loc = player.getLocation();
		loc.setY(loc.getY()-1);
		
		String blockName = loc.getBlock().getType().toString();

		if(blockName.equals("GRASS")) {
			player.sendMessage("Stepped on Grass");
			
		}
		

	}
}
