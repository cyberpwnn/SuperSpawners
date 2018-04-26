package com.volmit.superspawners;

import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;

public class ControlledSpawner
{
	private Location location;

	public ControlledSpawner(Location location)
	{
		this.location = location;
	}

	public double getMultiplier()
	{
		CreatureSpawner s = (CreatureSpawner) location.getBlock().getState();

		if(s.getMaxSpawnDelay() == s.getMinSpawnDelay() - 1)
		{
			return 1;
		}

		return (double) SuperSpawners.instance.getMultiplier(s.getMaxSpawnDelay());
	}

	public void setMultiplier(double multiplier)
	{
		int t = SuperSpawners.instance.getTicks(multiplier);

		if(t <= 1)
		{
			t = 2;
		}

		CreatureSpawner s = (CreatureSpawner) location.getBlock().getState();
		s.setMinSpawnDelay(1);
		s.setMaxSpawnDelay(t);
		s.setMinSpawnDelay(s.getMaxSpawnDelay() - 1);
		s.setSpawnCount((int) ((multiplier / 20.5) + 3));
		s.setRequiredPlayerRange(128);
		s.setMaxNearbyEntities(128);
		s.update();
	}

	public int getTicks()
	{
		CreatureSpawner s = (CreatureSpawner) location.getBlock().getState();
		return s.getMaxSpawnDelay();
	}
}
