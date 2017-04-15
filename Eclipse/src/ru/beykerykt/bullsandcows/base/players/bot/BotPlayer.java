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
import ru.beykerykt.bullsandcows.base.players.bot.finder.BobFinder;
import ru.beykerykt.bullsandcows.base.players.bot.finder.CodeFinder;

public class BotPlayer extends BasePlayer {

	private CodeFinder finder;

	public BotPlayer(String name, String code) {
		super(name, code, new NullInterface());
		setCodeFinder(new BobFinder());
	}

	@Override
	public void onStartGame() {
		getCodeFinder().reset();
	}

	@Override
	protected String getGuessCode() {
		String code = getCodeFinder().getGuessCode();
		getArena().broadcastMessage("I'm stupid banana. I think: " + code);
		return code;
	}

	@Override
	public BasePlayer getChooseOpponent() {
		Random r = new Random();
		int index = r.nextInt(getArena().getPlayers().size());
		BasePlayer op = getArena().getPlayers().get(index);
		if (op.getPlayerName().equals(getPlayerName())) {
			getUserInterface().showText("You stupid idiot!");
			getUserInterface().showText("Choose another player!");
			return getChooseOpponent();
		}
		return op;
	}

	@Override
	public void onReceivingResponse(String response) {
		getCodeFinder().onReceivingResponse(response);
	}

	public CodeFinder getCodeFinder() {
		return finder;
	}

	public void setCodeFinder(CodeFinder finder) {
		this.finder = finder;
	}
}