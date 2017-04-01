package ru.beykerykt.bullsandcows.base.runnables;

import java.text.DecimalFormat;

import ru.beykerykt.bullsandcows.base.BattleArena;

public class TimerRunnable extends BattleArenaRunnable {

	private int time = -1;

	public TimerRunnable(BattleArena arena) {
		super("timer", arena);
	}

	@Override
	public void run() {
		if (!getArena().isPaused()) {
			time++;
		}
	}

	//////////////////////////////////////////////////////////////////////
	//
	// Timer
	//
	/////////////////////////////////////////////////////////////////////
	public String getTime() {
		DecimalFormat decimalFormat = new DecimalFormat("00");
		return decimalFormat.format(time / 60) + ":" + decimalFormat.format(time % 60);
	}

	public int getTimeRaw() {
		return time;
	}
}