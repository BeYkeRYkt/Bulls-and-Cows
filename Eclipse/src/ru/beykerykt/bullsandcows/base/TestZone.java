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

import ru.beykerykt.bullsandcows.base.gui.PrintStreamInterface;
import ru.beykerykt.bullsandcows.base.players.BasePlayer;
import ru.beykerykt.bullsandcows.base.players.HumanPlayer;
import ru.beykerykt.bullsandcows.base.players.bot.BotPlayer;
import ru.beykerykt.bullsandcows.base.runnables.PlayerRunnable;
import ru.beykerykt.bullsandcows.base.utils.GameUtils;

public class TestZone {
	public static void main(String[] args) {
		try {
			BasePlayer player = new HumanPlayer("DevelopedOne", GameUtils.generateRandomCode(), new PrintStreamInterface(System.out));
			BasePlayer bot = new BotPlayer("Bob", GameUtils.generateRandomCode());

			BattleArena arena = new BattleArena("Test");
			arena.getRunnables().add(new PlayerRunnable(arena));
			arena.addPlayer(player);
			arena.addPlayer(bot);
			arena.start();

			player.getUserInterface().showText(bot.getSecretCode());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
