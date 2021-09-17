package main.game;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class SimonSays implements Runnable{

	private Integer assignedTaskId;
	
	public Location loc;
	World targetWorld;
	MainGame instance;
	public boolean ready;
	
	public SimonSays(Location location,MainGame inst) {

		this.loc = location;
		this.targetWorld = location.getWorld();
		this.instance = inst;
		this.ready = false;
	}
	public void startGame() {
		resetArena();
		ready = true;
		this.assignedTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, this, 0L, 20L);
	}
	
	public void resetArena() {
		for(int x = 0; x < 40; x++) {
			for(int z = 0; z<40; z++)
			{
				if(x < 20) {
					if(z < 20) {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.GREEN_CONCRETE);
					}
					else if(z > 20) {
						
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.RED_CONCRETE);
					}
					else {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.BLACK_CONCRETE);
					}
				}
				else if(x > 20) {
					if(z < 20) {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.BLUE_CONCRETE);
					}
					else if(z > 20) {
						
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.YELLOW_CONCRETE);
					}
					else {
						targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.BLACK_CONCRETE);
					}
				}
				else {
					targetWorld.getBlockAt(loc.getBlockX()+x - 20,loc.getBlockY(),loc.getBlockZ()+z - 20).setType(Material.BLACK_CONCRETE);
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
	public void CountdownLava(int time) {
		CountdownTimer timer = new CountdownTimer(instance,
		        time,
		        () -> Bukkit.getOnlinePlayers().forEach((p)->p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Survive!"))),
		        () -> {
		            Bukkit.broadcastMessage(ChatColor.YELLOW + "Timer is up!");
		            
					setLavaBlocks();
					CountdownNormal(5);
		        },
		        (t) -> {Bukkit.broadcastMessage(ChatColor.YELLOW + "Time left: " + (t.getSecondsLeft()) + "/" + (t.getTotalSeconds()) + " seconds");
				        }

		);

		// Start scheduling, don't use the "run" method unless you want to skip a second
		timer.scheduleTimer();
		
	}
	public void CountdownNormal(int time) {
		CountdownTimer timer = new CountdownTimer(instance,
		        time,
		        () -> Bukkit.broadcastMessage(ChatColor.YELLOW + "Timer is commencing. Get ready!"),
		        () -> {
		            Bukkit.broadcastMessage(ChatColor.YELLOW + "Timer is up!");
		            
					resetArena();
					ready = true;
		        },
		        (t) -> {Bukkit.broadcastMessage(ChatColor.YELLOW + "Time left: " + (t.getSecondsLeft()) + "/" + (t.getTotalSeconds()) + " seconds");
				        }

		);

		// Start scheduling, don't use the "run" method unless you want to skip a second
		timer.scheduleTimer();
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(ready) {
			ready = false;
			CountdownLava(10);

		}
	}
	
	public void stopGame() {
		ready = false;
		if (assignedTaskId != null) {
			Bukkit.getScheduler().cancelTask(assignedTaskId);
        	
        }
		return;
	}
}
