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
		public int getRating(EHandballAction action, int times) {
			switch (action) {
				case GOAL_MADE:
					return 5 * times;
				default:
					return -1 * times;
			}
		}
	},
	F("Field player") {
		@Override
		public int getRating(EHandballAction action, int times) {
			switch (action) {
				case GOAL_MADE:
					return  times;
				default:
					return -1 * times;
			}
		}
	};

	private String _brief;

	EHandballPosition(String brief) {
		_brief = brief;
	}
}
