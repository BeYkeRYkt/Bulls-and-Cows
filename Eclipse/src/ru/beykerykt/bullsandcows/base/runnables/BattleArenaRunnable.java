/**
* MIT License
* 
* Copyright (c) 2017 Vladimir Mikhaylov
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
**/
package ru.beykerykt.bullsandcows.base.runnables;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import ru.beykerykt.bullsandcows.base.BattleArena;
import ru.beykerykt.bullsandcows.base.utils.GameUtils;

public abstract class BattleArenaRunnable implements IRunnable {

	private String name;
	private BattleArena arena;
	private ScheduledFuture<?> sf = null;

	private long period;
	private TimeUnit timeUnit;

	public BattleArenaRunnable(String name, BattleArena arena, long period, TimeUnit timeUnit) {
		this.name = name;
		this.arena = arena;
		this.period = period;
		this.timeUnit = timeUnit;
	}

	@Override
	public void start() {
		sf = GameUtils.getExecutorService().scheduleAtFixedRate(this, 0, period, timeUnit);
	}

	@Override
	public void stop() {
		sf.cancel(false);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isRunning() {
		if (sf != null && (!sf.isCancelled() || !sf.isDone())) {
			return true;
		}
		return false;
	}

	public BattleArena getArena() {
		return arena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arena == null) ? 0 : arena.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (period ^ (period >>> 32));
		result = prime * result + ((timeUnit == null) ? 0 : timeUnit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BattleArenaRunnable other = (BattleArenaRunnable) obj;
		if (arena == null) {
			if (other.arena != null)
				return false;
		} else if (!arena.equals(other.arena))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (period != other.period)
			return false;
		if (timeUnit != other.timeUnit)
			return false;
		return true;
	}
}
