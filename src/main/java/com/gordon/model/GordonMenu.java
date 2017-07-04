package com.gordon.model;

public class GordonMenu {

	private int totalTimeAvailable;
	private int totalItems;

	private int[] amountOfSatisfactions;
	private int[] timesTaken;

	public int[] getAmountOfSatisfactions() {
		return amountOfSatisfactions;
	}

	public void setAmountOfSatisfactions(int[] amountOfSatisfactions) {
		this.amountOfSatisfactions = amountOfSatisfactions;
	}

	public int[] getTimesTaken() {
		return timesTaken;
	}

	public void setTimesTaken(int[] timesTaken) {
		this.timesTaken = timesTaken;
	}

	public int getTotalTimeAvailable() {
		return totalTimeAvailable;
	}

	public void setTotalTimeAvailable(int totalTimeAvailable) {
		this.totalTimeAvailable = totalTimeAvailable;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

}
