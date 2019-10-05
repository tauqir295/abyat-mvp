package com.abyat.mvp.interfaces;

import java.util.Map;

/**
 * Interface for a game statistics of the  player.
 *
 * @author Mohammad Tauqir
 */
 public interface IPlayerMatchStats<P extends IPosition, A extends IAction> {

	 boolean isWinningTeam();

	 void setWinningTeam(boolean teamWon);

	 String getNameOfPlayer();

	 String getPlayerNickName();

	 int getPlayerNumber();

	 String getPlayerTeamName();

	 P getPlayerPosition();

	 Map<A, Integer> getPlayerActions();

	 int getPlayerRating() throws Exception;

}
