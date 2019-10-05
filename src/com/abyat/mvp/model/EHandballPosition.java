package com.abyat.mvp.model;


import com.abyat.mvp.interfaces.IPosition;

/**
 * Available handball player positions.
 *
 * @author Mohammad Tauqir
 */
public enum EHandballPosition implements IPosition<EHandballAction> {

	G("Goalkeeper") {
		@Override
		public int getPlayerRating(EHandballAction action, int times) {
			if (action == EHandballAction.GOAL_MADE) {
				return 5 * times;
			}
			return -1 * times;
		}
	},
	F("Field player") {
		@Override
		public int getPlayerRating(EHandballAction action, int times) {
			if (action == EHandballAction.GOAL_MADE) {
				return times;
			}
			return -1 * times;
		}
	};

	EHandballPosition(String role) {
	}
}
