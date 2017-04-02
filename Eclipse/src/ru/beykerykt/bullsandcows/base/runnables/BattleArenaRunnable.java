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
