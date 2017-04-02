package ru.beykerykt.bullsandcows.base;

import ru.beykerykt.bullsandcows.base.gui.PrintStreamInterface;
import ru.beykerykt.bullsandcows.base.players.BasePlayer;
import ru.beykerykt.bullsandcows.base.players.HumanPlayer;
import ru.beykerykt.bullsandcows.base.players.bot.BotPlayer;
import ru.beykerykt.bullsandcows.base.utils.GameUtils;

public class TestZone {
	public static void main(String[] args) {
		try {
			BasePlayer player = new HumanPlayer("DevelopedOne", GameUtils.generateRandomCode(), new PrintStreamInterface(System.out));
			BasePlayer bot = new BotPlayer("Bob", GameUtils.generateRandomCode());

			BattleArena arena = new BattleArena("Test");
			arena.addPlayer(player);
			arena.addPlayer(bot);
			arena.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
