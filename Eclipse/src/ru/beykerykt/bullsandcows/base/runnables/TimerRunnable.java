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

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import ru.beykerykt.bullsandcows.base.BattleArena;

public class TimerRunnable extends BattleArenaRunnable {

	private int time = -1;

	public TimerRunnable(BattleArena arena) {
		super("timer", arena, 1, TimeUnit.SECONDS);
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