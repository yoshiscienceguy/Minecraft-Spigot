package main.game;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class SimonSays {

	public Location loc;
	World targetWorld;
	public SimonSays(Location location) {
		this.loc = location;
		this.targetWorld = location.getWorld();
		
	}
	
	public void resetArena() {
		for(int x = 0; x < 40; x++) {
			for(int z = 0; z<40; z++)
			{
				if(x < 20) {
					if(z < 20) {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.GREEN_WOOL);
					}
					else if(z > 20) {
						
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.RED_WOOL);
					}
					else {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.BLACK_WOOL);
					}
				}
				else if(x > 20) {
					if(z < 20) {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.BLUE_WOOL);
					}
					else if(z > 20) {
						
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.YELLOW_WOOL);
					}
					else {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.BLACK_WOOL);
					}
				}
				else {
					targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.BLACK_WOOL);
				}
			}
		}
	}
	public void setLavaBlocks() {
		
		
		Random rand = new Random();
		int randomX = rand.nextInt(2);
		int randomY = rand.nextInt(2);
		
		
		if(randomX == 0) {
			if(randomY == 0) {
				for(int x = 20; x < 40; x++) {
					for(int z = 20; z<40; z++)
					{
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.LAVA);
					}
				}
			}
			else  {
				
				for(int x = 20; x < 40; x++) {
					for(int z = 0; z<20; z++)
					{
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.LAVA);
					}
				}
			}
		}
		else  {
			if(randomY == 0) {
				for(int x = 0; x < 20; x++) {
					for(int z = 20; z<40; z++)
					{
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.LAVA);
					}
				}
			}
			else {
				
				for(int x = 0; x < 20; x++) {
					for(int z = 0; z<20; z++)
					{
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.LAVA);
					}
				}
			}
		}
		
		
	}

}
