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

import java.util.ArrayList;
import java.util.List;

import ru.beykerykt.bullsandcows.base.players.BasePlayer;
import ru.beykerykt.bullsandcows.base.runnables.PlayerRunnable;
import ru.beykerykt.bullsandcows.base.runnables.TimerRunnable;

public class BattleArena implements Runnable {

	private String name;
	private boolean isRunning = false;
	private boolean isPaused = false;

	// Players
	private List<BasePlayer> players = new ArrayList<BasePlayer>();

	// Runnable's
	private TimerRunnable timerR;
	private PlayerRunnable playerR;

	public BattleArena(String name) {
		this.name = name;
	}

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

			// init runables
			timerR = new TimerRunnable(this);
			playerR = new PlayerRunnable(this);

			timerR.start();
			playerR.start();
		}
	}

	public void stop() {
		if (isRunning()) {
			setRunning(false);
			setPaused(false);
			timerR.stop();
			playerR.stop();
		}
	}

	public String getName() {
		return name;
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
		if (!getPlayers().contains(player) && player.getArena() == null) {
			getPlayers().add(player);
			player.setArena(this);
			return true;
		}
		return false;
	}

	public boolean removePlayer(BasePlayer player) {
		if (getPlayers().contains(player) && player.getArena() == this) {
			getPlayers().remove(player);
			player.setArena(null);
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
		return timerR.getTime();
	}

	public int getTimeRaw() {
		return timerR.getTimeRaw();
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
		// TODO: ???
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isPaused ? 1231 : 1237);
		result = prime * result + (isRunning ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		BattleArena other = (BattleArena) obj;
		if (isPaused != other.isPaused)
			return false;
		if (isRunning != other.isRunning)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
