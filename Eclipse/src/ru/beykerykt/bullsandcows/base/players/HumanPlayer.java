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

import java.util.Scanner;

import ru.beykerykt.bullsandcows.base.gui.IUserInterface;

public class HumanPlayer extends BasePlayer {

	private Scanner input;

	public HumanPlayer(String name, String code, IUserInterface gui) {
		super(name, code, gui);
	}

	@Override
	public void onStartGame() {
		input = new Scanner(System.in);
	}

	@Override
	public void onEndGame() {
		input.close();
	}

	@Override
	public String getGuessCode() {
		getUserInterface().showText("Guess a 4-digit number with no duplicate digits: ");
		int guess = input.nextInt();
		String line = String.valueOf(guess);
		return line;
	}

	@Override
	public BasePlayer getChooseOpponent() {
		if (getArena().getPlayers().size() == 2) {
			for (BasePlayer player : getArena().getPlayers()) {
				if (!player.getPlayerName().equals(getPlayerName())) {
					return player;
				}
			}
		}

		getUserInterface().showText("Choose your opponent");
		for (int i = 0; i < getArena().getPlayers().size(); i++) {
			getUserInterface().showText((i + 1) + ". " + getArena().getPlayers().get(i).getPlayerName());
		}
		int index = input.nextInt() - 1;
		if (index < 0 || index > getArena().getPlayers().size()) {
			getUserInterface().showText("Wrong index!");
			return getChooseOpponent();
		}
		BasePlayer op = getArena().getPlayers().get(index);
		if (op.getPlayerName().equals(getPlayerName())) {
			getUserInterface().showText("You stupid idiot!");
			getUserInterface().showText("Choose other player!");
			return getChooseOpponent();
		}
		return op;
	}
}