package com.github.Taoist_Yu.MyPlugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class MyPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("插件加载成功！");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("插件成功退出！");
	}
	
	@Override
	public boolean onCommand(CommandSender Sender, Command cmd, String CommandLable, String[] args) {
		if(cmd.getName().equalsIgnoreCase("testCommand")) {
			getLogger().info("指令被触发");
			return true;
		} else if(cmd.getName().equalsIgnoreCase("fireBall")) {
			if(args.length != 1){
				return false;
			} 
			
			Player target = Bukkit.getServer().getPlayer(args[0]);
			
			if(target == null) {
				Sender.sendMessage("无法找到" + args[0]);
				return false;
			}
			
			target.setFireTicks(100);
			
		}
		
		return false;
	}
	
}
