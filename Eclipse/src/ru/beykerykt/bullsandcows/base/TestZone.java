package ru.beykerykt.bullsandcows.base;

import ru.beykerykt.bullsandcows.base.gui.PrintStreamInterface;
import ru.beykerykt.bullsandcows.base.players.BasePlayer;
import ru.beykerykt.bullsandcows.base.players.HumanPlayer;
import ru.beykerykt.bullsandcows.base.players.bot.BotPlayer;

public class TestZone {
	public static void main(String[] args) {
		BasePlayer player = new HumanPlayer("DevelopedOne", GameUtils.generateRandomCode(), new PrintStreamInterface(System.out));
		BasePlayer bot = new BotPlayer("Bob", GameUtils.generateRandomCode());

		BattleArea arena = new BattleArea();
		arena.start(player, bot);

		//Scanner input = new Scanner(System.in);
		//player.getUserInterface().showText("Guess a 4-digit number with no duplicate digits: ");
		//player.getUserInterface().showText(bot.getSecretCode());
		
		//BobFinder finder = new BobFinder();
		//finder.reset();
		
		//while (arena.isRunning()) {
		//	int guess = input.nextInt();
		//	String line = String.valueOf(guess);
		//	player.getUserInterface().showText(bot.getHint(line));
		//}
		//input.close();
	}

}
