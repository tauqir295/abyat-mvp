package com.abyat.mvp.model;

import com.abyat.mvp.interfaces.IGame;

import java.util.List;
import java.util.Map;

/**
 * Basketball implementation.
 *
 * @author Mohammad Tauqir
 */
public class Handball implements IGame<HandballPlayerMatchStatistics> {

	@Override
	public int calculateRatingOfPlayer(HandballPlayerMatchStatistics handballPlayerMatchStatistics) {

		int rating = 0;

		if (handballPlayerMatchStatistics.isWinningTeam()) {
			rating += 10;
		}

		EHandballPosition playerPosition = handballPlayerMatchStatistics.getPlayerPosition();

		Map<EHandballAction, Integer> actions = handballPlayerMatchStatistics.getPlayerActions();

		for (EHandballAction action : actions.keySet()) {
			rating += playerPosition.getPlayerRating(action, actions.get(action));
		}

		return rating;
	}

	@Override
	public int calculateEachTeamScore(String teamName, List<HandballPlayerMatchStatistics> playerMatchStatsList) {

		int teamScore = 0;

		for (HandballPlayerMatchStatistics playerMatchStats :
				playerMatchStatsList) {

			if (!playerMatchStats.getPlayerTeamName().equals(teamName)) {
				continue;
			}

			// total handball team score calculated on the goals made
			teamScore += playerMatchStats.getPlayerActions().get(
				EHandballAction.GOAL_MADE);
		}

		return teamScore;
	}

	@Override
	public Class getPlayerMatchStatisticsClass() {
		return HandballPlayerMatchStatistics.class;
	}

}
