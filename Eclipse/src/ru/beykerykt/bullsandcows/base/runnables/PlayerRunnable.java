package ru.beykerykt.bullsandcows.base.runnables;

import java.util.ListIterator;

import ru.beykerykt.bullsandcows.base.BattleArena;
import ru.beykerykt.bullsandcows.base.players.BasePlayer;

public class PlayerRunnable extends BattleArenaRunnable {

	public PlayerRunnable(BattleArena arena) {
		super("players", arena);
	}

	@Override
	public void run() {
		if (!getArena().isPaused()) {
			ListIterator<BasePlayer> li = getArena().getPlayers().listIterator();
			while (li.hasNext()) {
				BasePlayer player = li.next();
				if (li.hasNext()) {
					player.next(li.next());
					li.previous();
				} else {
					player.next(getArena().getPlayers().get(0));
				}
			}
		}
	}
}