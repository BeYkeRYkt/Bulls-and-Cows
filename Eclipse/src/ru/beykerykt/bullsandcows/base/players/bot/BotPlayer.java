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
package ru.beykerykt.bullsandcows.base.players.bot;

import java.util.Random;

import ru.beykerykt.bullsandcows.base.gui.NullInterface;
import ru.beykerykt.bullsandcows.base.players.BasePlayer;

public class BotPlayer extends BasePlayer {

	public BotPlayer(String name, String code) {
		super(name, code, new NullInterface());
	}

	@Override
	protected String getGuessCode() {
		getArena().broadcastMessage("I'm stupid banana. I'm pass.");
		return "PASS";
	}

	@Override
	public BasePlayer getChooseOpponent() {
		Random r = new Random();
		int index = r.nextInt(getArena().getPlayers().size());
		BasePlayer op = getArena().getPlayers().get(index);
		if (op.getPlayerName().equals(getPlayerName())) {
			getUserInterface().showText("You stupid idiot!");
			getUserInterface().showText("Choose other player!");
			return getChooseOpponent();
		}
		return op;
	}

}