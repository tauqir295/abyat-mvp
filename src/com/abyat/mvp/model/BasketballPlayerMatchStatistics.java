package com.abyat.mvp.model;

import com.abyat.mvp.AbyatMain;
import com.abyat.mvp.interfaces.IPlayerMatchStats;
import com.abyat.mvp.interfaces.IGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Match statistics implementation for the Basketball.
 *
 * @author Mohammad Tauqir
 */
public class BasketballPlayerMatchStatistics extends AbstractPlayerMatchStatistics
	implements IPlayerMatchStats<EBasketballPosition, EBasketballAction> {

	private EBasketballPosition player_position;
	private Map<EBasketballAction, Integer> player_actions;

	public BasketballPlayerMatchStatistics(String input, Boolean teamWon) {
		String[] statsArray = input.split(";");

		if (statsArray.length != 8) {
			throw new IllegalArgumentException(
				"Invalid format: " + input);
		}

		setWinningTeam(teamWon);
		setNameOfPlayer(statsArray[0]);
		setPlayerNickName(statsArray[1]);
		setPlayerNumber(Integer.parseInt(statsArray[2]));
		setPlayerTeamName(statsArray[3]);

		player_position = EBasketballPosition.valueOf(statsArray[4]);

		player_actions = new HashMap<>();

		player_actions.put(EBasketballAction.SCORE, Integer.parseInt(statsArray[5]));
		player_actions.put(EBasketballAction.REBOUND, Integer.parseInt(statsArray[6]));
		player_actions.put(EBasketballAction.ASSIST, Integer.parseInt(statsArray[7]));
	}

	@Override
	public EBasketballPosition getPlayerPosition() {
		return player_position;
	}

	@Override
	public Map<EBasketballAction, Integer> getPlayerActions() {
		return player_actions;
	}

	public int getRating() throws Exception {
		Class<IGame> gameClass = AbyatMain.GAMES.get("BASKETBALL");

		IGame iGame = gameClass.newInstance();

		return iGame.calculateRatingOfPlayer(this);
	}


}
