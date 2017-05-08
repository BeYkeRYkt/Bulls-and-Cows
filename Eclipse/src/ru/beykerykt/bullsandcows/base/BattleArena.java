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
package ru.beykerykt.bullsandcows.base;

import java.util.ArrayList;
import java.util.List;

import ru.beykerykt.bullsandcows.base.players.BasePlayer;
import ru.beykerykt.bullsandcows.base.runnables.IRunnable;
import ru.beykerykt.bullsandcows.base.runnables.TimerRunnable;
import ru.beykerykt.bullsandcows.base.utils.GameUtils;

public class BattleArena implements Runnable {

	private String name;
	private boolean isRunning = false;
	private boolean isPaused = false;

	// Players
	private List<BasePlayer> players;

	// Runnable's
	private List<IRunnable> runnables;
	private TimerRunnable timerR;

	public BattleArena(String name) {
		this.name = name;
		this.players = new ArrayList<BasePlayer>();
		this.runnables = new ArrayList<IRunnable>();
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
			timerR.start();

			for (BasePlayer player : getPlayers()) {
				player.onStartGame();
			}

			for (IRunnable r : getRunnables()) {
				if (!r.isRunning()) {
					r.start();
				}
			}
		}
	}

	public void stop() {
		if (isRunning()) {
			setRunning(false);
			setPaused(false);

			for (BasePlayer player : getPlayers()) {
				player.onEndGame();
			}

			for (IRunnable r : getRunnables()) {
				if (r.isRunning()) {
					r.stop();
				}
			}

			timerR.stop();
			GameUtils.shutdownExecutor(true);
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
			player.setArena(this);
			getPlayers().add(player);
			player.getUserInterface().onPlayerJoin();
			for (BasePlayer p : getPlayers()) {
				p.onPlayerJoin(player);
				player.onPlayerJoin(p);
			}

			if (isRunning()) {
				player.onStartGame();
			}
			return true;
		}
		return false;
	}

	public boolean removePlayer(BasePlayer player) {
		if (getPlayers().contains(player) && player.getArena() != this) {
			if (isRunning()) {
				player.onEndGame();
			}
			for (BasePlayer p : getPlayers()) {
				player.onPlayerLeave(p);
				p.onPlayerLeave(player);
			}
			player.getUserInterface().onPlayerLeave();
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
	public List<IRunnable> getRunnables() {
		return runnables;
	}

	public void addRunnable(IRunnable runnable, boolean startAfter) {
		runnables.add(runnable);

		if (startAfter && isRunning()) {
			runnable.start();
		}
	}

	public IRunnable getRunnable(String name) {
		for (IRunnable run : getRunnables()) {
			if (run.getName().equals(name)) {
				return run;
			}
		}
		return null;
	}

	public void removeRunnable(IRunnable runnable) {
		if (getRunnables().contains(runnable)) {
			runnable.stop();
			getRunnables().remove(runnable);
		}
	}

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
