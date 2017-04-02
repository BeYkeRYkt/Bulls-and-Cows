package ru.beykerykt.bullsandcows.base.runnables;

import ru.beykerykt.bullsandcows.base.BattleArena;
import ru.beykerykt.bullsandcows.base.players.BasePlayer;

public class PlayerRunnable extends BattleArenaRunnable {

	public PlayerRunnable(BattleArena arena) {
		super("players", arena);
	}

	@Override
	public void run() {
		if (!getArena().isPaused()) {
			for (BasePlayer player : getArena().getPlayers()) {
				player.onTurn();
			}
		}
	}
}