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
		BasketballPlayerMatchStatistics basketballPlayerMatchStatistics) {

		int rating = 0;

		if (basketballPlayerMatchStatistics.isWinningTeam()) {
			rating += 10;
		}

		EBasketballPosition position = basketballPlayerMatchStatistics.getPlayerPosition();

		Map<EBasketballAction, Integer> basketballAction =
				basketballPlayerMatchStatistics.getPlayerActions();

		for (EBasketballAction eBasketballAction : basketballAction.keySet()) {
			rating += position.getPlayerRating(eBasketballAction, basketballAction.get(eBasketballAction));
		}

		return rating;
	}

	@Override
	public int calculateEachTeamScore(
		String teamName,
		List<BasketballPlayerMatchStatistics> playerMatchStatsList) {

		int score = 0;

		for (BasketballPlayerMatchStatistics basketballPlayerMatchStatistics :
				playerMatchStatsList) {

			// calculate score only for team
			if (!basketballPlayerMatchStatistics.getPlayerTeamName().equals(teamName)) {
				continue;
			}

			// total basketball team score calculated on scored points
			score += basketballPlayerMatchStatistics.getPlayerActions().get(
				EBasketballAction.SCORE);
		}

		return score;
	}

	@Override
	public Class getPlayerMatchStatisticsClass() {
		return BasketballPlayerMatchStatistics.class;
	}

}
