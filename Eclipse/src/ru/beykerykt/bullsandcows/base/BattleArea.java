/**
* The MIT License (MIT)
*
* Copyright (c) 2017 D3v3l0p3d_0n3
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
*/
package ru.beykerykt.bullsandcows.base;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import ru.beykerykt.bullsandcows.base.players.BasePlayer;

public class BattleArea implements Runnable {

	private boolean isRunning = false;
	private boolean isPaused = false;

	// Players
	private List<BasePlayer> players = new ArrayList<BasePlayer>();

	// Executor
	private ScheduledFuture<?> timer = null;
	private int time = -1;

	//////////////////////////////////////////////////////////////////////
	//
	// Base arena logic
	//
	/////////////////////////////////////////////////////////////////////
	public void start() {
		if (!isRunning()) {
			if (getPlayers().isEmpty()) {
				return;
			}

			setRunning(true); // run!
			setPaused(false); // set false pause

			// Start timer
			timer = GameUtils.getExecutorService().scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);
		}
	}

	public void stop() {
		if (isRunning()) {
			setRunning(false);
			setPaused(false);
			// GameUtils.getExecutorService().shutdownNow();
		}
	}

	//////////////////////////////////////////////////////////////////////
	//
	// Running / Pause
	//
	/////////////////////////////////////////////////////////////////////
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	//////////////////////////////////////////////////////////////////////
	//
	// Players
	//
	/////////////////////////////////////////////////////////////////////
	public List<BasePlayer> getPlayers() {
		return players;
	}

	public boolean addPlayer(BasePlayer player) {
		if (!getPlayers().contains(player)) {
			getPlayers().add(player);
			return true;
		}
		return false;
	}

	public boolean removePlayer(BasePlayer player) {
		if (getPlayers().contains(player)) {
			getPlayers().remove(player);
			return true;
		}
		return false;
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

	//////////////////////////////////////////////////////////////////////
	//
	// Broadcast messages
	//
	/////////////////////////////////////////////////////////////////////
	public void broadcastMessage(String message) {
		for (BasePlayer player : getPlayers()) {
			player.getUserInterface().showText(message);
		}
	}

	//////////////////////////////////////////////////////////////////////
	//
	// Runnable
	//
	/////////////////////////////////////////////////////////////////////
	@Override
	public void run() {
		if (!isPaused()) {
			time++;
			broadcastMessage(getTime());
		}
	}
}
