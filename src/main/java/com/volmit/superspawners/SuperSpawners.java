package com.volmit.superspawners;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperSpawners extends JavaPlugin
{
	public static SuperSpawners instance;

	@Override
	public void onEnable()
	{
		instance = this;
	}

	@Override
	public void onDisable()
	{

	}

	public static void register(Listener l)
	{
		Bukkit.getServer().getPluginManager().registerEvents(l, this);
	}

	public static void unregister(Listener l)
	{
		HandlerList.unregisterAll(l);
	}
}
