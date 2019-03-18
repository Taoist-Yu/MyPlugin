package com.github.Taoist_Yu.RespawnCD;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public final class PlayerListener implements Listener {

	//记录玩家剩余多少秒可以复活
	class PlayerAndTime
	{
		Player player;
		int time;
		PlayerAndTime(Player player,int time)
		{
			this.player = player;
			this.time = time;
		}
	}
	
	private RespawnCD plugin;
	private Set<PlayerAndTime> wattingSet = new HashSet<PlayerAndTime>();
	private Set<PlayerAndTime> alreadySet = new HashSet<PlayerAndTime>();
	
	public PlayerListener(RespawnCD plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void Proc(PlayerRespawnEvent event){
		Player target = event.getPlayer();
		int waitting_time = 0;
			
		int key = 0;
		for(PlayerAndTime value: wattingSet){
			if(target == value.player){
				key = 1;
				waitting_time = value.time;
			}
		}
		for(PlayerAndTime value: alreadySet){
			if(target == value.player){
				key = 2;
				alreadySet.remove(value);
			}

		}
        
		if(key == 2){
			target.sendRawMessage("已复活");
			return;
		}else if(key == 1){
			target.sendRawMessage("复活冷却中,剩余时间"+waitting_time+"s");
			new BukkitRunnable(){
				@Override
				public void run(){
					target.setHealth(0);
				}
			}.runTaskLaterAsynchronously(plugin, 10);
		}else if(key == 0){
			target.sendRawMessage("复活冷却中,剩余时间10s");
			//复活冷却中，令玩家死亡
			new BukkitRunnable(){
				@Override
				public void run(){
					target.setHealth(0);
				}
			}.runTaskLaterAsynchronously(plugin, 10);
			//将玩家添加到待复活队列中
			PlayerAndTime watting_player = new PlayerAndTime(target,10);
			wattingSet.add(watting_player);
			new BukkitRunnable(){
				@Override
				public void run(){
					watting_player.time--;
					if(watting_player.time == 0)
					{
						wattingSet.remove(watting_player);
						alreadySet.add(watting_player);
					}
				}
			}.runTaskTimerAsynchronously(plugin, 0, 20);
		}
		
	}
	
}
