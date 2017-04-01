package ru.beykerykt.bullsandcows.base.runnables;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import ru.beykerykt.bullsandcows.base.BattleArena;
import ru.beykerykt.bullsandcows.base.utils.GameUtils;

public abstract class BattleArenaRunnable implements IRunnable {

	private String name;
	private BattleArena arena;
	private ScheduledFuture<?> sf = null;

	public BattleArenaRunnable(String name, BattleArena arena) {
		this.name = name;
		this.arena = arena;
	}

	@Override
	public void start() {
		sf = GameUtils.getExecutorService().scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);
	}

	@Override
	public void stop() {
		sf.cancel(false);
	}

	@Override
	public String getName() {
		return name;
	}

	public BattleArena getArena() {
		return arena;
	}

}
