package com.abyat.mvp.interfaces;

import java.util.List;

/**
 * Basic game interface, to be implemented for the each games.
 *
 * @author Mohammad Tauqir
 */
public interface IGame<M extends IPlayerMatchStats> {

	int calculateRatingOfPlayer(M playerMatchStats);

	int calculateEachTeamScore(String teamName, List<M> playerMatchStatsList);

	Class getPlayerMatchStatisticsClass();

}
