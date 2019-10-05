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
 * MVP utility methods.
 *
 * @author Mohammad Tauqir
 */
public class AbyatUtility {

	/**
	 * Get MVP nickname based on the players statistics.
	 *
	 * @param IPlayerMatchStatsList Players statistics list.
	 * @return MVP nickname
	 * @throws Exception
	 */
	public static String getMVPNickName(
		List<IPlayerMatchStats> IPlayerMatchStatsList) throws Exception {

		Map<String, Integer> playerRatings = new HashMap<>();

		// calculate ratings for each player(assuming that nicknames are unique)
		for (IPlayerMatchStats IPlayerMatchStats : IPlayerMatchStatsList) {
			int rating = playerRatings.getOrDefault(IPlayerMatchStats.getPlayerNickName(), 0);

			rating += IPlayerMatchStats.getRating();

			playerRatings.put(IPlayerMatchStats.getPlayerNickName(), rating);
		}

		// get MVP entry this the highest rating
		Map.Entry<String, Integer> mvpEntry = playerRatings.entrySet().stream()
			.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
			.limit(1).findFirst().get();

		return mvpEntry.getKey();
	}

	/**
	 * Reads match statistics from the specified input stream.
	 *
	 * @param inputStream Input stream to read statistics from.
	 * @return List of players statistics
	 * @throws Exception in case of unsupported sport name, incorrect format,
	 * or i/o error.
	 */
	public static List<IPlayerMatchStats> readMatchStats(InputStream inputStream)
			throws Exception {

		List<IPlayerMatchStats> IPlayerMatchStatsList = new ArrayList<>();

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

		String sportName = bufferedReader.readLine();

		if (sportName.equals("") || ! AbyatMain.GAMES.containsKey(sportName)) {
			throw new IllegalArgumentException(
					"Invalid sport name" + sportName);
		}

		// instantiate selected sport class
		Class<IGame> gameClass = AbyatMain.GAMES.get(sportName);

		IGame IGame = gameClass.newInstance();

		Class<IPlayerMatchStats> playerMatchStatsClass =
				IGame.getPlayerMatchStatsClass();

		String inputString = null;

		List<String> nicknames = new ArrayList<>();

		// read players stats line by line, checking nicks uniqueness
		while ((inputString = bufferedReader.readLine()) != null) {
			IPlayerMatchStats IPlayerMatchStats =
					playerMatchStatsClass.getConstructor(
							String.class, Boolean.class).newInstance(
							inputString, false);

			if (nicknames.contains(IPlayerMatchStats.getPlayerNickName())) {
				throw new IllegalStateException(
						"Players nicks should be unique");
			}

			nicknames.add(IPlayerMatchStats.getPlayerNickName());

			IPlayerMatchStatsList.add(IPlayerMatchStats);
		}

		Map<String, Integer> teamPoints = new HashMap<>();

		// calculate teams scores to know which team won the match
		for (IPlayerMatchStats IPlayerMatchStats : IPlayerMatchStatsList) {
			if (teamPoints.containsKey(IPlayerMatchStats.getPlayerTeamName())) {
				continue;
			}

			teamPoints.put(
					IPlayerMatchStats.getPlayerTeamName(),
					IGame.calculateTeamScore(
							IPlayerMatchStats.getPlayerTeamName(), IPlayerMatchStatsList));
		}

		// get the winner team and assign corresponding player's property
		Map.Entry<String, Integer> teamWon = teamPoints.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.limit(1).findFirst().get();

		for (IPlayerMatchStats IPlayerMatchStats : IPlayerMatchStatsList) {
			if (teamWon.getValue().equals(IPlayerMatchStats.getPlayerTeamName())) {
				IPlayerMatchStats.setWinningTeam(true);
			}
		}

		return IPlayerMatchStatsList;
	}

}
