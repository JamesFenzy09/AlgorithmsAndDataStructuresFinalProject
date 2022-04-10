package Java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DijkstraAlgorithm {
    public static File STOPS;
    public static File STOP_TIMES;
    public static File TRANSFERS;

    public DijkstraAlgorithm(String stopsPath, String stopTimesPath, String transfersPath) {
        STOPS = new File(stopsPath);
        STOP_TIMES = new File(stopTimesPath);
        TRANSFERS = new File(transfersPath);
    }

    public static StopConnections routes;
    public static Trips trips;

    public static void setupGraph(File stops, File stop_times, File transfers) throws IOException {
        routes = new StopConnections(stops, transfers);
        trips = new Trips(stop_times);

        for (int t = 1; t < trips.validData.size(); t++) {
            TripDetails trip1 = trips.validData.get(t - 1);
            TripDetails trip2 = trips.validData.get(t);
            int cost = 1;
            if (trip1.tripID == trip2.tripID) {
                routes.makeConnection(trip1.stopID, trip2.stopID, cost);
            }
        }
    }

    public void setupGraphFiles() throws IOException {
        setupGraph(STOPS, STOP_TIMES, TRANSFERS);
    }

    public static void printShortestPathInfo(int start, int destination) {
        ArrayList<Integer> shortestPath = routes.getShortestPath(start, destination);
        double shortestCost = routes.getShortestPathCost();

        if (shortestCost == Double.POSITIVE_INFINITY) {
            System.out.println("No route from from " + start + " to " + destination);
        } else if (shortestCost == Double.NEGATIVE_INFINITY) {
            System.out.println("both are same");
        } else if (shortestCost == -1.0) {
            System.out.println("Invalid input");
        } else {
            System.out.println("Cost from " + start + " to " + destination + " is: " + shortestCost);

            System.out.println("\nThe quickest route you can from Stop ID " + start + " to Stop ID " + destination
                    + " is to travel ion the following order! \n");
            for (int i = 0; i < shortestPath.size(); i++) {
                System.out.print(shortestPath.get(i));
                if (i != shortestPath.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    public static void part1GUI() throws IOException {
        String stops_times_path = "./TextFiles/stop_times.txt";
        File stop_times = new File(stops_times_path);

        String stops_path = "./TextFiles/stops.txt";
        File stops = new File(stops_path);

        String transfers_path = "./TextFiles/transfers.txt";
        File transfers = new File(transfers_path);

        setupGraph(stops, stop_times, transfers);

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the stop ID one");
        String one = scan.nextLine();
        System.out.println("Please enter the stop ID two");
        String two = scan.nextLine();

        int fromStopID = Integer.parseInt(one);
        int toStopID = Integer.parseInt(two);

        if (routes.isValidStopId(fromStopID) && routes.isValidStopId(toStopID)) {
            System.out.println("Inputs start stop - " + one + " dest stop - " + two);
            printShortestPathInfo(fromStopID, toStopID);
            double shortestCost = routes.getShortestPathCost();
            ArrayList<Integer> shortestPath = routes.getShortestPath(fromStopID, toStopID);
            routes.getEnrouteStops(shortestPath);

            System.out.println("\nThe cost associated with moving from " + fromStopID + " to " + toStopID
                    + " is " + shortestCost);
        } else {
            System.out.println("Sorry bro, no such stop combination! Please try again!");
        }

    }

    public static void main(String[] args) throws IOException {
        part1GUI();
    }
}