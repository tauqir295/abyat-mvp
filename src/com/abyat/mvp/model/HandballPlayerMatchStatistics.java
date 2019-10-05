package com.abyat.mvp.model;


import com.abyat.mvp.AbyatMain;
import com.abyat.mvp.interfaces.IPlayerMatchStats;
import com.abyat.mvp.interfaces.IGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Match statistics implementation for the Handball.
 *
 * @author Mohammad Tauqir
 */
public class HandballPlayerMatchStatistics extends AbstractPlayerMatchStatistics implements IPlayerMatchStats<EHandballPosition, EHandballAction> {
	private EHandballPosition player_position;
	private Map<EHandballAction, Integer> player_actions;

	public HandballPlayerMatchStatistics(String input, Boolean teamWon) {
		String[] statsArray = input.split(";");

		if (statsArray.length != 7) {
			throw new IllegalArgumentException(
				"Invalid format: " + input);
		}

		setWinningTeam(teamWon);
		setNameOfPlayer(statsArray[0]);
		setPlayerNickName(statsArray[1]);
		setPlayerNumber(Integer.parseInt(statsArray[2]));
		setPlayerTeamName(statsArray[3]);

		player_position = EHandballPosition.valueOf(statsArray[4]);

		player_actions = new HashMap<>();

		player_actions.put(
			EHandballAction.GOAL_MADE, Integer.parseInt(statsArray[5]));
		player_actions.put(
			EHandballAction.GOAL_RECEIVED, Integer.parseInt(statsArray[6]));
	}

	@Override
	public EHandballPosition getPlayerPosition() {
		return player_position;
	}

	@Override
	public Map<EHandballAction, Integer> getPlayerActions() {
		return player_actions;
	}

	public int getPlayerRating() throws Exception {
		Class<IGame> gameClass = AbyatMain.GAMES.get("HANDBALL");

		IGame iGame = gameClass.newInstance();

		return iGame.calculateRatingOfPlayer(this);
	}


}
