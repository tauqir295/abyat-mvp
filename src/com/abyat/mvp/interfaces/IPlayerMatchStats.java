package com.abyat.mvp.interfaces;

import java.util.Map;

/**
 * Basic API for a sport match statistics for the concrete player.
 *
 * @author Mohammad Tauqir
 */
public interface IPlayerMatchStats<P extends IPosition, A extends IAction> {

	/**
	 * Team won the match.
	 *
	 * @return True if team won the match.
	 */
	public boolean isWinningTeam();

	public void setWinningTeam(boolean teamWon);

	/**
	 * Player's name.
	 *
	 * @return Player's name.
	 */
	public String getNameOfPlayer();

	/**
	 * Player's nickname.
	 *
	 * @return Player's nickname.
	 */
	public String getPlayerNickName();

	/**
	 * Player's number.
	 *
	 * @return Player's number.
	 */
	public int getPlayerNumber();

	/**
	 * Player's team name.
	 *
	 * @return Player's team name.
	 */
	public String getPlayerTeamName();

	/**
	 * Player's position.
	 *
	 * @return Player's position.
	 */
	public P getPlayerPosition();

	/**
	 * Player's actions map.
	 *
	 * @return Player's actions map.
	 */
	public Map<A, Integer> getPlayerActions();

	/**
	 * Calculated player's rating.
	 *
	 * @return Calculated player's rating
	 */
	public int getRating() throws Exception;

}
