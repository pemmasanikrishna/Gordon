package com.gordon.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.gordon.model.GordonMenu;

@Service
public class GordonService {

	private GordonMenu gordonMenu;

	public int calculateMaxSatisfaction(InputStream fileStream) {
		readFromFile(fileStream);
		return calcMaxSatisfaction();
	}

	// Read Input file and form GordonMenu object
	private void readFromFile(InputStream fileStream) {

		String[] firstLine;
		gordonMenu = new GordonMenu();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream))) {
			firstLine = reader.readLine().split(" ");

			gordonMenu.setTotalTimeAvailable(Integer.valueOf(firstLine[0]));
			gordonMenu.setTotalItems(Integer.valueOf(firstLine[1]));

			int[] amountsOfSatisfaction = new int[gordonMenu.getTotalItems()];
			int[] timesTaken = new int[gordonMenu.getTotalItems()];

			int i = 0;

			for (String line; (line = reader.readLine()) != null;) {

				firstLine = line.split(" ");
				amountsOfSatisfaction[i] = Integer.valueOf(firstLine[0]);
				timesTaken[i] = Integer.valueOf(firstLine[1]);
				i++;

			}

			gordonMenu.setTimesTaken(timesTaken);
			gordonMenu.setAmountOfSatisfactions(amountsOfSatisfaction);

		} catch (Exception exception) {

		}
	}

	// A utility function that returns maximum of two integers
	private static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// Returns the maximum statisfaction value in a given time from the menu
	private int calcMaxSatisfaction() {
		int i, j;
		int satisfactionArray[][] = new int[gordonMenu.getTotalItems() + 1][gordonMenu.getTotalTimeAvailable() + 1];

		// Build table satisfactionArray[][] using dp
		for (i = 0; i <= gordonMenu.getTotalItems(); i++) {
			for (j = 0; j <= gordonMenu.getTotalTimeAvailable(); j++) {
				if (i == 0 || j == 0)
					satisfactionArray[i][j] = 0;
				else if (gordonMenu.getTimesTaken()[i - 1] <= j)
					satisfactionArray[i][j] = max(
							gordonMenu.getAmountOfSatisfactions()[i - 1]
									+ satisfactionArray[i - 1][j - gordonMenu.getTimesTaken()[i - 1]],
							satisfactionArray[i - 1][j]);
				else
					satisfactionArray[i][j] = satisfactionArray[i - 1][j];
			}
		}

		return satisfactionArray[gordonMenu.getTotalItems()][gordonMenu.getTotalTimeAvailable()];
	}
}
