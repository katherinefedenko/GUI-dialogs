package model;

import java.time.*;

public class Tournament {
	private String name;
	private LocalDate date;
	// private String date;
	private String sport;
	private int prizeAmount;
	private Winner winner;
	private String firstName;
	private String lastName;
	private double income;

	public Tournament() {
		name = "";
		sport = "";
		prizeAmount = 0;
		date = LocalDate.of(1914, 7, 28);
		firstName = "";
		lastName = "";
		income = 1;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	/*
	 * public String getDate() { return date; }
	 * 
	 * public void setDate(String date) { this.date = date; }
	 */
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = 0.6 * this.prizeAmount;
	}
}
