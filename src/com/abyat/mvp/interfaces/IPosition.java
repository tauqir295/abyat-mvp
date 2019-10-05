package com.abyat.mvp.interfaces;

/**
 * Player position, to be implemented in the game.
 *
 * @author Mohammad Tauqir
 */
public interface IPosition<A extends IAction> {
    int getPlayerRating(A action, int times);
}
