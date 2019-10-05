package com.abyat.mvp.model;


/**
 * Common player match statistics functionality.
 *
 * @author Mohammad Tauqir
 */
public abstract class AbstractPlayerMatchStatistics {

	private boolean winningTeam;
	private String nameOfPlayer;
	private String playerNickName;
	private int playerNumber;
	private String playerTeamName;

	public boolean isWinningTeam() {
		return winningTeam;
	}

	public void setWinningTeam(boolean winningTeam) {
		this.winningTeam = winningTeam;
	}

	public String getNameOfPlayer() {
		return nameOfPlayer;
	}

	public void setNameOfPlayer(String nameOfPlayer) {
		this.nameOfPlayer = nameOfPlayer;
	}

	public String getPlayerNickName() {
		return playerNickName;
	}

	public void setPlayerNickName(String playerNickName) {
		this.playerNickName = playerNickName;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getPlayerTeamName() {
		return playerTeamName;
	}

	public void setPlayerTeamName(String playerTeamName) {
		this.playerTeamName = playerTeamName;
	}
}
