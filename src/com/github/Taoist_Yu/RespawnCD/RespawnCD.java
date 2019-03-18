package com.github.Taoist_Yu.RespawnCD;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//重生冷却
public final class RespawnCD extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("插件加载成功！");
		getServer().getPluginManager().registerEvents(new PlayerListener(this),this);
	}

	@Override
	public void onDisable() {
		getLogger().info("插件成功退出！");
	}

}
