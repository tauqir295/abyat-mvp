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
	public int calculateRatingOfPlayer(
		HandballPlayerMatchStatistics playerMatchStats) {

		int rating = 0;

		if (playerMatchStats.isWinningTeam()) {
			rating += 10;
		}

		EHandballPosition position = playerMatchStats.getPlayerPosition();

		Map<EHandballAction, Integer> actions =
			playerMatchStats.getPlayerActions();

		for (EHandballAction action : actions.keySet()) {
			rating += position.getRating(action, actions.get(action));
		}

		return rating;
	}

	@Override
	public int calculateTeamScore(
		String teamName,
		List<HandballPlayerMatchStatistics> playerMatchStatsList) {

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
	public Class getPlayerMatchStatsClass() {
		return HandballPlayerMatchStatistics.class;
	}

}
