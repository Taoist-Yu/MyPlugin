package com.github.Taoist_Yu.MyPlugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

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
		}
		
		return false;
	}
	
}
