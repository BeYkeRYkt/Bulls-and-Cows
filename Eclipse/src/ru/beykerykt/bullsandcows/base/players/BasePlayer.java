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
package ru.beykerykt.bullsandcows.base.players;

import ru.beykerykt.bullsandcows.base.gui.IUserInterface;

public abstract class BasePlayer {

	private String code;
	private String name;
	private IUserInterface gui;
	private boolean guessed = false;
	private int guesses = 0;

	public BasePlayer(String name, String code, IUserInterface gui) {
		this.name = name;
		this.code = code;
		this.gui = gui;
	}

	public String getSecretCode() {
		return code;
	}

	public String getPlayerName() {
		return name;
	}

	public IUserInterface getUserInterface() {
		return gui;
	}

	public boolean isGuessed() {
		return guessed;
	}

	public void setGuessed(boolean guessed) {
		this.guessed = guessed;
	}

	public int getGuesses() {
		return guesses;
	}

	public void setGuesses(int guesses) {
		this.guesses = guesses;
	}

	public void guessCodeTo(BasePlayer player, String guess) {
		guesses++;
		String hint = getHint(guess);
		if (hint.equals("WON")) {
			setGuessed(true);
			getUserInterface().showText("You won!");
		}
		getUserInterface().showText(hint);
	}

	public String getHint(String guess) {
		int bulls = 0;
		int cows = 0;

		for (int i = 0; i < getSecretCode().length(); i++) {
			if (guess.charAt(i) == getSecretCode().charAt(i)) {
				bulls++;
			} else if (getSecretCode().contains(guess.charAt(i) + "")) {
				cows++;
			}
		}
		// System.out.println(cows + " Cows and " + bulls + " Bulls.");
		if (bulls == getSecretCode().length()) {
			return "WON";
		}
		return bulls + ":" + cows;
	}
	
	public boolean next(BasePlayer opponent){
		nextGuess(opponent);
		return true;
	}
	
	//Твой ход!
	protected abstract void nextGuess(BasePlayer player);
}