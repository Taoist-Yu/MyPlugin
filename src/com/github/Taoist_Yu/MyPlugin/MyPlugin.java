package com.github.Taoist_Yu.MyPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("插件加载成功！");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("插件成功退出！");
	}//
	
}
