package com.github.Taoist_Yu.MyPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class MyPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("������سɹ���");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("����ɹ��˳���");
	}//
	
}
