package com.abyat.mvp.model;

import com.abyat.mvp.interfaces.IPosition;

/**
 * Available basketball player positions.
 *
 * @author Mohammad Tauqir
 */
public enum EBasketballPosition implements IPosition<EBasketballAction> {

	G("GUARD") {
		@Override
		public int getPlayerRating(EBasketballAction action, int times) {
			switch (action) {
				case SCORE:
					return 2 * times;
				case ASSIST:
					return 1 * times;
				default:
					return 3 * times;
			}
		}
	},
	F("FORWARD") {
		@Override
		public int getPlayerRating(EBasketballAction action, int times) {
			switch (action) {
				default:
					return 2 * times;
			}
		}
	},
	C("CENTER") {
		@Override
		public int getPlayerRating(EBasketballAction action, int times) {
			switch (action) {
				case SCORE:
					return 2 * times;
				case ASSIST:
					return 3 * times;
				default:
					return times;
			}
		}
	};

	EBasketballPosition(String role) {
	}
}
