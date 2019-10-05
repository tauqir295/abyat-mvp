package com.abyat.mvp.interfaces;

/**
 * Player position, to be implemented for the concrete game.
 *
 * @author Mohammad Tauqir
 */
public interface IPosition<A extends IAction> {

	/**
	 * Calculates player rating based on player position, action and action times.
	 *
	 * @param action Player action
	 * @param times Times repeated
	 *
	 * @return rating
	 */
	public int getPlayerRating(A action, int times);
}
