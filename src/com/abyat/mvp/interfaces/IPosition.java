package com.abyat.mvp.interfaces;

/**
 * Player's position, to be implemented for the concrete sport.
 *
 * @author Mohammad Tauqir
 */
public interface IPosition<A extends IAction> {

	/**
	 * Calculates player's rating based on player's position, action and
	 * action times.
	 *
	 * @param action Player's action
	 * @param times Times repeated
	 *
	 * @return Action rating
	 */
	public int getRating(A action, int times);
}
