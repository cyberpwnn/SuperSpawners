package com.volmit.superspawners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperSpawners extends JavaPlugin implements Listener
{
	public static SuperSpawners instance;
	public static int basetick = 372;

	@Override
	public void onEnable()
	{
		instance = this;
		register(this);
	}

	@Override
	public void onDisable()
	{

	}

	@EventHandler
	public void on(PlayerInteractEvent e)
	{
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			if(e.getItem() == null)
			{
				return;
			}

			if(e.getClickedBlock().getType().equals(Material.MOB_SPAWNER) && e.getItem().getType().equals(Material.SUGAR))
			{
				ControlledSpawner cc = new ControlledSpawner(e.getClickedBlock().getLocation());
				cc.setMultiplier(cc.getMultiplier() + (((0.18 + (Math.random() / 10.0)) / cc.getMultiplier())));
				e.getPlayer().sendMessage("Spawner Multipler: " + cc.getMultiplier() + "... " + cc.getTicks());
			}
		}
	}

	public double getMultiplier(int ticks)
	{
		return 1.0 / (double) ((double) ticks / basetick);
	}

	public int getTicks(double multiplier)
	{
		return (int) ((double) basetick / multiplier);
	}

	public static void register(Listener l)
	{
		Bukkit.getServer().getPluginManager().registerEvents(l, instance);
	}

	public static void unregister(Listener l)
	{
		HandlerList.unregisterAll(l);
	}
}
