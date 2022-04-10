package Java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class tripSearch {

	static int arraySize(String filename) throws IOException {
		int answer = 0;

		try {
			String charset = "UTF-8";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), charset));
			br.mark(1);
			if (br.read() != 0xFEFF)
				br.reset();
			String line;
			while ((line = br.readLine()) != null) {
				answer++;
			}
		} catch (FileNotFoundException e) {

		}
		return answer;
	}

	static String[] textTo2D(String filename, int size) throws IOException {
		String[] answer = new String[size];
		try {
			String charset = "UTF-8";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), charset));
			br.mark(1);
			if (br.read() != 0xFEFF) {
				br.reset();
			}
			String line;
			for (int i = 0; i < size; i++) {
				answer[i] = br.readLine();
			}
		} catch (FileNotFoundException e) {

		}
		return answer;
	}

	static String[][] fullGrid(String[] string, int size) {
		int columns = 0;
		String[] arr = string[1].split(",");
		columns = arr.length + 1;
		String[][] grid = new String[size][columns];
		for (int i = 0; i < size; i++) {
			string[i] = string[i].replaceAll(", ", ",");
			arr = string[i].split(",");
			for (int j = 0; j < arr.length; j++) {
				grid[i][j] = arr[j];
			}
		}
		return grid;
	}

	static boolean validArrivalTime(String[][] grid, int size, String input) {
		for (int i = 0; i < size; i++) {
			int j = 1;
			String str = grid[i][j];
			if (str != null && str.equals(input)) {
				return true;
			}
		}
		return false;
	}

	static String getStopID(String[] string, String[] textStops, String[][] gridStopTimes, String[][] gridStops,
			int stopTimesSize, int stopsSize, String input) {
		String answer = "";
		String finalAnswer = "";
		ArrayList<String> quickSort = new ArrayList<String>();
		ArrayList<String> stopID = new ArrayList<String>();
		int duplicate = 1;
		for (int i = 0; i < stopTimesSize; i++) {
			int j = 1;
			String str = gridStopTimes[i][j];
			String stopIDs = gridStopTimes[i][j];
			if (str != null && str.equals(input)) {
				str = gridStopTimes[i][0]; // changes
				stopIDs = gridStopTimes[i][3]; // changes
				quickSort.add(str);
				stopID.add(stopIDs);
			}
		}
		String[] test;
		String[] sendToQuickSort = quickSort.toArray(new String[0]);
		String[] sendToStopID = stopID.toArray(new String[0]);
		int[] realSend = new int[sendToQuickSort.length];
		int[] realSendIDs = new int[sendToStopID.length];
		for (int a = 0; a < sendToQuickSort.length; a++) {
			realSend[a] = Integer.parseInt(sendToQuickSort[a]);
			realSendIDs[a] = Integer.parseInt(sendToStopID[a]);
		}
		realSend = quickSort(realSend);
		realSendIDs = quickSort(realSendIDs);
		for (int a = 0; a < realSend.length; a++) {
			sendToQuickSort[a] = Integer.toString(realSend[a]);
			sendToStopID[a] = Integer.toString(realSendIDs[a]);
		}
		for (int b = 0; b < realSend.length; b++) {
			test = stopData(textStops, gridStops, sendToStopID[b], stopsSize); // changes below
			answer = "Match number " + duplicate + " to arrival time " + input + " is:\nTrip ID: " + sendToQuickSort[b]
					+ "\nStop ID: " + test[0] + "\n" + "Stop Code: " + test[1] +
					"\nStop Name: " + test[2] + "\nStop Destination: " + test[3] + "\nStop Latitude: " + test[4]
					+ "\nStop Longitude: " + test[5] +
					"\nZone ID: " + test[6];
			finalAnswer = finalAnswer + "\n" + answer + "\n";
			duplicate++;
		}

		return finalAnswer;
	}

	static String[] stopData(String[] textStops, String[][] gridStops, String stopID, int stopsSize) {
		String[] arr = textStops[1].split(",");
		int columns = arr.length + 1;
		String[] stopInfo = new String[columns];

		for (int i = 0; i < stopsSize; i++) {
			int j = 0;
			String ID = gridStops[i][j];
			if (ID != null && ID.equals(stopID)) {
				for (int k = 0; k < columns - 1; k++) {
					stopInfo[k] = gridStops[i][k];
				}

			}
		}
		return stopInfo;
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		// stop times
		String stopTimes = "./TextFiles/stop_times.txt";
		int sizeStopTimes = arraySize(stopTimes);
		// System.out.println("Size: "+sizeStopTimes);03
		String[] textStopTimes = textTo2D(stopTimes, sizeStopTimes);
		// System.out.println("First Line: "+ textStopTimes[1]);
		String[][] gridStopTimes = fullGrid(textStopTimes, sizeStopTimes);
		// System.out.println("At point [1][1]: " + gridStopTimes[1][0]);

		// stops
		String stops = "./TextFiles/stops.txt";
		int sizeStops = arraySize(stops);
		// System.out.println("Size: "+sizeStops);
		String[] textStops = textTo2D(stops, sizeStops);
		// System.out.println("First Line: "+ textStops[3]);
		String[][] gridStops = fullGrid(textStops, sizeStops);
		// System.out.println("At point [1][1]: " + gridStops[1][1]);

		String answer, anotherOne;
		boolean valid = false;
		while (!valid) {
			answer = "";
			System.out.println("Enter your arrival time in 24hr format now (hh:mm:ss): ");
			answer = scan.nextLine();
			answer = answer.replaceAll(" ", "");
			String[] validChecker = answer.split(":");
			int[] changetoInt = new int[validChecker.length];
			boolean extraMessage = false;
			try {
				for (int i = 0; i < validChecker.length; i++) {
					changetoInt[i] = Integer.parseInt(validChecker[i]);
				}
				if (changetoInt[0] > 24 || changetoInt[1] > 59 || changetoInt[2] > 59) {
					valid = false;
				} else {
					valid = validArrivalTime(gridStopTimes, sizeStopTimes, answer);
					extraMessage = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("error");
			}
			if (valid == true) {
				System.out.println("Yay! Arrival time is valid!\n ");
				String stopInformation = getStopID(textStops, textStopTimes, gridStopTimes, gridStops, sizeStopTimes,
						sizeStops, answer);
				System.out.println(stopInformation);
				anotherOne = "";
				while ((!anotherOne.equals("yes") || !anotherOne.equals("no")) && valid) {
					System.out.println("\nDo you wish to check another arrival time?(yes or no)");
					anotherOne = scan.nextLine();
					anotherOne.toLowerCase();
					anotherOne = anotherOne.replaceAll(" ", "");
					if (anotherOne.equals("yes")) {
						valid = false;
					} else if (anotherOne.equals("no")) {
						// while (!anotherOne.equals("yes") || !anotherOne.equals("no")) {
						// System.out.println("Sorry, what you entered is not a valid response, please
						// use 'yes' or 'no'");
						// System.out.println("\nDo you wish to check another arrival time?(yes or
						// no)");
						// anotherOne = scan.nextLine();
						// }
						System.out.println("No Problem! Have a nice Day! ");
						System.exit(1);
					} else {
						System.out.println("Sorry, what you entered is not a valid response, please use 'yes' or 'no'");
					}
				}
			} else {
				if (extraMessage) {
					System.out.println(
							"Sorry, the time you entered exceeds the valid time format (24:59:59), try again!");
				} else {
					System.out.println("Sorry, there are no arrival times that suit what you entered, try again!");

				}
			}
		}

	}

	public static void quickSort(int[] a, int low, int high) {
		// pick the pivot
		int mid = low + (high - low) / 2;
		double pivot = a[mid];

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (a[i] < pivot) {
				i++;
			}

			while (a[j] > pivot) {
				j--;
			}

			if (i <= j) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		}

		// recursively sort two sub parts
		if (low < j)
			quickSort(a, low, j);

		if (high > i)
			quickSort(a, i, high);
	}

	static int[] quickSort(int[] a) {
		int min = 0;
		int max = (a.length) - 1;
		quickSort(a, min, max);
		return a;

	}// end quicksort

	public static String finalMain(String stopsPath, String stopTimesPath, String userInput) throws IOException {
		Scanner scan = new Scanner(System.in);
		// stop times
		String stopTimes = stopTimesPath;
		int sizeStopTimes = arraySize(stopTimes);
		String[] textStopTimes = textTo2D(stopTimes, sizeStopTimes);
		String[][] gridStopTimes = fullGrid(textStopTimes, sizeStopTimes);

		// stops
		String stops = stopsPath;
		int sizeStops = arraySize(stops);
		String[] textStops = textTo2D(stops, sizeStops);
		String[][] gridStops = fullGrid(textStops, sizeStops);

		String answer, anotherOne;
		boolean valid = false;
		while (!valid) {
			/*
			 * answer ="";
			 * System.out.println("Enter your arrival time in 24hr format now (hh:mm:ss): "
			 * );
			 * answer = scan.nextLine();
			 */
			userInput = userInput.replaceAll(" ", "");
			String[] validChecker = userInput.split(":");
			int[] changetoInt = new int[validChecker.length];
			boolean extraMessage = false;
			for (int i = 0; i < validChecker.length; i++) {
				changetoInt[i] = Integer.parseInt(validChecker[i]);
			}
			if (changetoInt[0] > 24 || changetoInt[1] > 59 || changetoInt[2] > 59) {
				valid = false;
			} else {
				valid = validArrivalTime(gridStopTimes, sizeStopTimes, userInput);
				extraMessage = true;
			}
			if (valid == true) {
				System.out.println("Yay! Arrival time is valid!\n ");
				String stopInformation = getStopID(textStops, textStopTimes, gridStopTimes, gridStops, sizeStopTimes,
						sizeStops, userInput);
				return stopInformation;
				/*
				 * System.out.println("\nDo you wish to check another arrival time?(yes or no)"
				 * );
				 * anotherOne = scan.nextLine();
				 * anotherOne.toLowerCase();
				 * anotherOne = anotherOne.replaceAll(" ", "");
				 * if(anotherOne.equals("yes")) {
				 * valid=false;
				 * }
				 * else {
				 * System.out.println("No Problem! Have a nice Day! ");
				 * System.exit(1);
				 * }
				 */
			} else {
				if (extraMessage) {
					return "Sorry, the time you entered exceeds the valid time format (24:59:59), try again!";
				} else {
					return "Sorry, there are no arrival times that suit what you entered, try again!";

				}
			}
		}
		return null;
	}
}
