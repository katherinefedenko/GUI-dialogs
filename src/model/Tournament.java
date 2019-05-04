package model;

import java.time.*;

public class Tournament {
	private String name;
	//private LocalDate date;
	private String date;
	private String sport;
	private int prizeAmount;
	private Winner winner;

	/*public Tournament() {
		name = "k";
		//date = LocalDate.of(2000, 1, 1);
		sport = "t";
		prizeAmount = 0;
	}*/
	public Tournament(String name, String sport, int prizeAmount, String date) {
		this.name = name;
		this.sport = sport;
		this.prizeAmount = prizeAmount;
		this.date = date;
	}

	/*
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	*/
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	
	public int getPrizeAmount() {
		return prizeAmount;
	}

	public void setPrizeAmount(int prizeAmount) {
		this.prizeAmount = prizeAmount;
	}
	
	public Winner getWinner() {
		return winner;
	}
	
	public void setWinner(Winner winner) {
		this.winner = winner;
	}
}
