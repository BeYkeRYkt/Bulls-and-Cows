package ru.beykerykt.bullsandcows.base.runnables;

import java.util.Iterator;

import ru.beykerykt.bullsandcows.base.BattleArena;
import ru.beykerykt.bullsandcows.base.players.BasePlayer;

public class PlayerRunnable extends BattleArenaRunnable {

	public PlayerRunnable(BattleArena arena) {
		super("players", arena);
	}

	@Override
	public void run() {
		if (!getArena().isPaused()) {
			Iterator<BasePlayer> it = getArena().getPlayers().iterator();
			BasePlayer player = it.next();
			while (it.hasNext()) {
				player.next(it.next());
			}
			player.next(getArena().getPlayers().get(0));
		}
	}
}