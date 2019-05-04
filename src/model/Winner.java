package model;

public class Winner {
	private String firstName;
	private String lastName;
	private int income;

	public Winner() {
		firstName = "";
		lastName = "";
		income = 0;
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
		return getFirstName() + " " + getLastName() ;
	}
	
	public int getIncome() {
		return income;
	}
	
	public void setIncome(int income) {
		this.income = income;
	}
}
