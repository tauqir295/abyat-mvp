package com.abyat.mvp.util;

import com.abyat.mvp.AbyatMain;
import com.abyat.mvp.interfaces.IPlayerMatchStats;
import com.abyat.mvp.interfaces.IGame;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Utility class.
 *
 * @author Mohammad Tauqir
 */
public class AbyatUtility {

	/**
	 * Get MVP nickname based on the player's statistics.
	 *
	 * @param playerMatchStatsList Player's statistics .
	 * @return MVP nickname
	 * @throws Exception :
	 */
	public static String getMVPNickName(List<IPlayerMatchStats> playerMatchStatsList) throws Exception {

		Map<String, Integer> playerRatingsMap = new HashMap<>();

		// calculate ratings for each player(assuming that nicknames are unique)
		for (IPlayerMatchStats playerMatchStats : playerMatchStatsList) {
			int rating = playerRatingsMap.getOrDefault(playerMatchStats.getPlayerNickName(), 0);

			rating += playerMatchStats.getPlayerRating();

			playerRatingsMap.put(playerMatchStats.getPlayerNickName(), rating);
		}

		// get MVP entry this the highest rating
		Map.Entry<String, Integer> mvpEntry = playerRatingsMap.entrySet().stream()
			.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
			.limit(1).findFirst().get();

		return mvpEntry.getKey();
	}

	/**
	 *  match statistics from the via input stream.
	 *
	 * @param inputStream Input stream to read statistics from.
	 * @return players statistics
	 * @throws Exception for unsupported game, incorrect format, or i/o error.
	 */
	public static List<IPlayerMatchStats> readMatchStats(InputStream inputStream) throws Exception {

		List<IPlayerMatchStats> IPlayerMatchStatsList = new ArrayList<>();

		BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));

		String gameName = bf.readLine();

		if (gameName.equals("") || ! AbyatMain.GAMES.containsKey(gameName)) {
			throw new IllegalArgumentException(
					"Invalid game name" + gameName);
		}

		// instantiate selected sport class
		Class<IGame> gameClass = AbyatMain.GAMES.get(gameName);

		IGame IGame = gameClass.newInstance();

		Class<IPlayerMatchStats> playerMatchStatsClass = IGame.getPlayerMatchStatisticsClass();

		String inputString;

		List<String> nicknames = new ArrayList<>();

		// read players stats line by line, checking nicks uniqueness
		while ((inputString = bf.readLine()) != null) {
			IPlayerMatchStats IPlayerMatchStats =
					playerMatchStatsClass.getConstructor(
							String.class, Boolean.class).newInstance(
							inputString, false);

			if (nicknames.contains(IPlayerMatchStats.getPlayerNickName())) {
				throw new IllegalStateException(
						"Player's nickname should be unique");
			}

			nicknames.add(IPlayerMatchStats.getPlayerNickName());

			IPlayerMatchStatsList.add(IPlayerMatchStats);
		}

		Map<String, Integer> teamPointsMap = new HashMap<>();

		// calculate teams scores to know which team won the match
		for (IPlayerMatchStats IPlayerMatchStats : IPlayerMatchStatsList) {
			if (teamPointsMap.containsKey(IPlayerMatchStats.getPlayerTeamName())) {
				continue;
			}

			teamPointsMap.put(IPlayerMatchStats.getPlayerTeamName(),IGame.calculateEachTeamScore(IPlayerMatchStats.getPlayerTeamName(), IPlayerMatchStatsList));
		}

		// get the winner team and assign corresponding player's property
		Map.Entry<String, Integer> teamWonEntry = teamPointsMap.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.limit(1).findFirst().get();

		for (IPlayerMatchStats IPlayerMatchStats : IPlayerMatchStatsList) {
			if (teamWonEntry.getValue().equals(IPlayerMatchStats.getPlayerTeamName())) {
				IPlayerMatchStats.setWinningTeam(true);
			}
		}

		return IPlayerMatchStatsList;
	}

}
