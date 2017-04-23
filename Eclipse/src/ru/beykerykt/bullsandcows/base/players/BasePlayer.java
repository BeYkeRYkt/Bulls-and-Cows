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
package ru.beykerykt.bullsandcows.base.players;

import java.util.ArrayList;
import java.util.List;

import ru.beykerykt.bullsandcows.base.BattleArena;
import ru.beykerykt.bullsandcows.base.gui.IUserInterface;
import ru.beykerykt.bullsandcows.base.utils.GameUtils;

public abstract class BasePlayer {

	private String code;
	private String name;
	private IUserInterface gui;
	private BattleArena arena;
	private List<OpponentInfo> guessedPlayers;

	public BasePlayer(String name, String code, IUserInterface gui) {
		this.name = name;
		this.code = code;
		this.gui = gui;
		this.guessedPlayers = new ArrayList<OpponentInfo>();
	}

	//////////////////////////////////////////////////////////////////////
	//
	// General
	//
	/////////////////////////////////////////////////////////////////////
	public String getSecretCode() {
		return code;
	}

	public String getPlayerName() {
		return name;
	}

	public BattleArena getArena() {
		return arena;
	}

	public void setArena(BattleArena arena) {
		this.arena = arena;
	}

	public IUserInterface getUserInterface() {
		return gui;
	}

	//////////////////////////////////////////////////////////////////////
	//
	// Multiplayer
	//
	/////////////////////////////////////////////////////////////////////
	public List<OpponentInfo> getOpponentInfos() {
		return guessedPlayers;
	}

	public OpponentInfo getOpponentInfo(String name) {
		for (OpponentInfo entry : getOpponentInfos()) {
			if (entry.getName().equals(name)) {
				return entry;
			}
		}
		return null;
	}

	//////////////////////////////////////////////////////////////////////
	//
	// Multiplayer
	//
	/////////////////////////////////////////////////////////////////////
	public void guessCodeTo(BasePlayer player, String guess) {
		OpponentInfo entry = getOpponentInfo(player.getPlayerName());
		if (entry == null) {
			return;
		}
		entry.tickGuess();
		String hint = player.getHint(guess);
		entry.setLastCode(guess);
		entry.setLastResponse(hint);
		onReceivingResponse(entry.getLastResponse());

		if (entry.getLastResponse().equals("WON")) {
			entry.setGuessed(true);
			getUserInterface().showText("You won!");
		}
		
		getUserInterface().showText(entry.getLastResponse());
	}

	public String getHint(String guessFromAnotherPlayer) {
		int bulls = 0;
		int cows = 0;

		if (guessFromAnotherPlayer.length() < GameUtils.CODE_LENGTH) {
			return bulls + ":" + cows;
		}

		for (int i = 0; i < getSecretCode().length(); i++) {
			if (guessFromAnotherPlayer.charAt(i) == getSecretCode().charAt(i)) {
				bulls++;
			} else if (getSecretCode().contains(guessFromAnotherPlayer.charAt(i) + "")) {
				cows++;
			}
		}
		// System.out.println(cows + " Cows and " + bulls + " Bulls.");
		if (bulls == getSecretCode().length()) {
			return "WON";
		}
		return bulls + ":" + cows;
	}

	//////////////////////////////////////////////////////////////////////
	//
	// GameEvents
	//
	/////////////////////////////////////////////////////////////////////
	public void onStartGame() {
	}

	public void onEndGame() {
	}

	public void onPlayerJoin(BasePlayer player) {
		if (!player.getPlayerName().equals(getPlayerName()) && player.getArena().equals(getArena())) {
			getOpponentInfos().add(new OpponentInfo(player.getPlayerName()));
		}
	}

	public void onPlayerLeave(BasePlayer player) {
		if (player.getPlayerName().equals(getPlayerName()) && player.getArena().equals(getArena())) {
			getOpponentInfos().remove(getOpponentInfo(player.getPlayerName()));
		}
	}

	public void onTick() {
		BasePlayer opponent = getChooseOpponent();
		String gcode = getGuessCode();
		guessCodeTo(opponent, gcode);
	}

	public void onReceivingResponse(String response) {
	}

	public abstract BasePlayer getChooseOpponent();

	protected abstract String getGuessCode();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		BasePlayer other = (BasePlayer) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}