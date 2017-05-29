package com.github.Taoist_Yu.MyPlugin;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.scheduler.BukkitRunnable;

public final class MyPlayerListener implements Listener {
	
	private MyPlugin plugin;
	private Set<Player> wattingSet = new HashSet<Player>();
	private Set<Player> alreadySet = new HashSet<Player>();
	
	public MyPlayerListener(MyPlugin plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void Proc(PlayerRespawnEvent event){
		Player target = event.getPlayer();
			
		int key = 0;
		for(Player value: wattingSet){
			if(target == value)
				key = 1;
		}
		for(Player value: alreadySet){
			if(target == value)
				key = 2;
		}
        
		if(key == 2){
			target.sendRawMessage("已复活");
			alreadySet.remove(target);
			return;
		}else if(key == 1){
			target.sendRawMessage("复活冷却中");
			new BukkitRunnable(){
				@Override
				public void run(){
					target.setHealth(0);
				}
			}.runTaskLaterAsynchronously(plugin, 10);
		}else if(key == 0){
			target.sendRawMessage("复活冷却中");
			new BukkitRunnable(){
				@Override
				public void run(){
					target.setHealth(0);
				}
			}.runTaskLaterAsynchronously(plugin, 10);
			wattingSet.add(target);
			new BukkitRunnable(){
				@Override
				public void run(){
					wattingSet.remove(target);
					alreadySet.add(target);
				}
			}.runTaskLaterAsynchronously(plugin, 100);
		}
		
	}
	
}
