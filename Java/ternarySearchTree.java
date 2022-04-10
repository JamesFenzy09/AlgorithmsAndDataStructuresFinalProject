package Java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ternarySearchTree {

    public static void main(String[] args) throws IOException {
        // Trie trie = new Trie();
        Scanner scan = new Scanner(System.in);
        String stopsFile = "./TextFiles/stops.txt";
        int stopsFileSize = arraySize(stopsFile);
        System.out.println(textTo2D(stopsFile, stopsFileSize)[1124]);
        System.out.println(fullGrid(textTo2D(stopsFile, stopsFileSize), stopsFileSize)[1][1]);
        System.out
                .println(switcharoo(fullGrid(textTo2D(stopsFile, stopsFileSize), stopsFileSize),
                        stopsFileSize)[8744][2]);

        String[][] fullFinalGrid = switcharoo(fullGrid(textTo2D(stopsFile, stopsFileSize), stopsFileSize),
                stopsFileSize);
        // addToTree(trie, fullFinalGrid, stopsFileSize);
        String answer = "";
        TernarySearchTrie tst = new TernarySearchTrie(stopsFile);
        System.out.print(
                "Please enter the bus stop's full name or by the first few characters in the name: ");
        String userInput = scan.nextLine();
        userInput = userInput.toUpperCase();
        tst.getStopInformation(userInput).forEach((info) -> {
            System.out.println(info);
        });
        // boolean anotherSearch = true;
        // while (anotherSearch) {
        // System.out.println("Please enter part of, or the full bus stop name you're
        // looking for");
        // answer = scan.nextLine();
        // if (trie.search(answer)) {
        // System.out.println("Hooray, there is a bus stop that mathces your exact
        // request!");
        // }
        // // System.out.println(trie.startsWith(answer, stopsFileSize)[1]);
        // else if (trie.startsWith(answer)) {
        // System.out.println(
        // "Unfortunately there is no bus stops that match your exact search however
        // here are the stops that match what you entered");
        // } else {
        // System.out.println("Nothing matches what you entered loser");
        // }
        // boolean valid = false;
        // System.out.println("number of runs: " + trie.startsWithCounter(answer));
        // while (!valid) {
        // System.out.println("Do you want to make another stop search?('yes' or
        // 'no')");
        // answer = scan.nextLine();
        // answer = answer.toLowerCase();
        // if (answer.equals("no")) {
        // anotherSearch = false;
        // valid = true;
        // System.out.println("Not a problem! Have a nice day");
        // System.exit(1);
        // } else if (answer.equals("yes")) {
        // anotherSearch = true;
        // valid = true;
        // } else if (!answer.equals("no") || !answer.equals("yes")) {
        // System.out.println("Sorry that was not a valid entry, try again!");
        // valid = false;
        // }
        // }
        // }
    }

    public static String finalMain(String userInput, String stopsFile) throws IOException {
        TernarySearchTrie tst = new TernarySearchTrie(stopsFile);
        String finalAnswer = "";
        String poop = "";
        List<String> poo = new LinkedList<>();
        poo = tst.getStopInformation(userInput);
        for (int i = 0; i < poo.size(); i++) {
            finalAnswer = finalAnswer + poo.get(i) + "\n";
        }
        return finalAnswer;
    }

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

    static String[][] switcharoo(String[][] grid, int size) {
        String[] justColumnThree = new String[size];
        for (int i = 0; i < size; i++) {
            justColumnThree[i] = grid[i][2];
            if (justColumnThree[i].contains("FLAGSTOP WB ")) {
                justColumnThree[i] = grid[i][2].replace("FLAGSTOP WB ", "");
                justColumnThree[i] = justColumnThree[i] + " FLAGSTOP WB";
            } else if (justColumnThree[i].contains("FLAGSTOP SB ")) {
                justColumnThree[i] = grid[i][2].replace("FLAGSTOP SB ", "");
                justColumnThree[i] = justColumnThree[i] + " FLAGSTOP SB";
            } else if (justColumnThree[i].contains("FLAGSTOP NB ")) {
                justColumnThree[i] = grid[i][2].replace("FLAGSTOP NB ", "");
                justColumnThree[i] = justColumnThree[i] + " FLAGSTOP NB";
            } else if (justColumnThree[i].contains("FLAGSTOP EB ")) {
                justColumnThree[i] = grid[i][2].replace("FLAGSTOP EB ", "");
                justColumnThree[i] = justColumnThree[i] + " FLAGSTOP EB";
            } else if (justColumnThree[i].contains("WB ")) {
                justColumnThree[i] = grid[i][2].replace("WB ", "");
                justColumnThree[i] = justColumnThree[i] + " WB";
            } else if (justColumnThree[i].contains("SB ")) {
                justColumnThree[i] = grid[i][2].replace("SB ", "");
                justColumnThree[i] = justColumnThree[i] + " SB";
            } else if (justColumnThree[i].contains("NB ")) {
                justColumnThree[i] = grid[i][2].replace("NB ", "");
                justColumnThree[i] = justColumnThree[i] + " NB";
            } else if (justColumnThree[i].contains("EB ")) {
                justColumnThree[i] = grid[i][2].replace("EB ", "");
                justColumnThree[i] = justColumnThree[i] + " EB";
            }
            grid[i][2] = justColumnThree[i];
        }
        return grid;
    }

    // static String[][] addToTree(Trie trie, String[][] grid, int size) {
    // for (int i = 0; i < size; i++) {
    // trie.insert(grid[i][2]);
    // }
    // return null;
    // }
}