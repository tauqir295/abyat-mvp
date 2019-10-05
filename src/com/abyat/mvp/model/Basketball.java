package com.abyat.mvp.model;

import com.abyat.mvp.interfaces.IGame;

import java.util.List;
import java.util.Map;

/**
 * Basketball implementation.
 *
 * @author Mohammad Tauqir
 */
public class Basketball implements IGame<BasketballPlayerMatchStatistics> {

	@Override
	public int calculateRatingOfPlayer(
		BasketballPlayerMatchStatistics playerMatchStats) {

		int rating = 0;

		if (playerMatchStats.isWinningTeam()) {
			rating += 10;
		}

		EBasketballPosition position = playerMatchStats.getPlayerPosition();

		Map<EBasketballAction, Integer> actions =
			playerMatchStats.getPlayerActions();


		for (EBasketballAction action : actions.keySet()) {
			rating += position.getRating(action, actions.get(action));
		}

		return rating;
	}

	@Override
	public int calculateTeamScore(
		String teamName,
		List<BasketballPlayerMatchStatistics> playerMatchStatsList) {

		int score = 0;

		for (BasketballPlayerMatchStatistics playerMatchStats :
				playerMatchStatsList) {

			// calculate score only for team
			if (!playerMatchStats.getPlayerTeamName().equals(teamName)) {
				continue;
			}

			// total basketball team score calculated on scored points
			score += playerMatchStats.getPlayerActions().get(
				EBasketballAction.SCORE);
		}

		return score;
	}

	@Override
	public Class getPlayerMatchStatsClass() {
		return BasketballPlayerMatchStatistics.class;
	}

}
