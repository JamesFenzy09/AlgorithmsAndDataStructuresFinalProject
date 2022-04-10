package Java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Adapted from TST by Sedgwick and Wayne
 * https://algs4.cs.princeton.edu/52trie/TST.java.html
 */

public class TernarySearchTrie {

    private HashMap<String, String> map;
    private Node<String> root; // root of TST

    private static class Node<String> {
        private char c; // character
        private Node<String> left, mid, right; // left, middle, and right subtries
        private String val; // String associated with string
    }

    /**
     * Does this symbol table contain the given key?
     * 
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(String word) {
        if (word == null) {
            return false;
        }
        return get(word) != null;
    }

    /**
     * Returns the String associated with the given key.
     * 
     * @param key the key
     * @return the String associated with the given key if the key is in the symbol
     *         table and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public String get(String word) {
        if (word == null) {
            return null;
        }
        if (word.length() == 0) {
            return null;
        }
        Node<String> nodeSearch = get(root, word, 0);
        if (nodeSearch == null) {
            return null;
        }
        return nodeSearch.val;
    }

    // return subtrie corresponding to given key
    private Node<String> get(Node<String> nodeSearch, String word, int i) {
        if (nodeSearch == null) {
            return null;
        }
        if (word.length() == 0) {
            return null;
        }
        char ch = word.charAt(i);
        if (ch < nodeSearch.c) {
            return get(nodeSearch.left, word, i);
        } else if (ch > nodeSearch.c) {
            return get(nodeSearch.right, word, i);
        } else if (i < word.length() - 1) {
            return get(nodeSearch.mid, word, i + 1);
        } else {
            return nodeSearch;
        }
    }

    /**
     * Inserts the key-String pair into the symbol table, overwriting the old String
     * with the new String if the key is already in the symbol table. If the String
     * is {@code null}, this effectively deletes the key from the symbol table.
     * 
     * @param key the key
     * @param val the String
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void insert(String word, String val) {
        if (word == null) {
            return;
        }
        root = insert(root, word, val, 0);
    }

    private Node<String> insert(Node<String> node, String word, String val, int i) {
        char ch = word.charAt(i);
        if (node == null) {
            node = new Node<String>();
            node.c = ch;
        }
        if (ch < node.c) {
            node.left = insert(node.left, word, val, i);
        } else if (ch > node.c) {
            node.right = insert(node.right, word, val, i);
        } else if (i < word.length() - 1) {
            node.mid = insert(node.mid, word, val, i + 1);
        } else {
            node.val = val;
        }
        return node;
    }

    /**
     * Returns all of the keys in the set that start with {@code prefix}.
     * 
     * @param prefix the prefix
     * @return all of the keys in the set that start with {@code prefix}, as an
     *         iterable
     * @throws IllegalArgumentException if {@code prefix} is {@code null}
     */
    public Iterable<String> startsWith(String prefix) {
        if (prefix == null) {
            return null;
        }
        Queue<String> list = new LinkedList<String>();
        Node<String> x = get(root, prefix, 0);
        if (x == null) {
            return list;
        }
        if (x.val != null) {
            list.add(prefix);
        }
        collect(x.mid, new StringBuilder(prefix), list);
        return list;
    }

    // all keys in subtrie rooted at x with given prefix
    private void collect(Node<String> nodeSearch, StringBuilder prefix, Queue<String> queue) {
        if (nodeSearch == null)
            return;
        collect(nodeSearch.left, prefix, queue);
        if (nodeSearch.val != null)
            queue.add(prefix.toString() + nodeSearch.c);
        collect(nodeSearch.mid, prefix.append(nodeSearch.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(nodeSearch.right, prefix, queue);
    }

    public TernarySearchTrie(String filename) throws IOException {
        int size = arraySize(filename);
        String[] text2lines = textTo2D(filename, size);
        String[][] fullGrid = fullGrid(text2lines, size);
        String[][] finalGrid = switcharoo(fullGrid, size);
        map = new HashMap<String, String>();
        for (int i = 0; i < size; i++) {
            this.insert(fullGrid[i][2], fullGrid[i][0]);

            String answer = "Stop ID: " + fullGrid[i][0] + "\n" + "Stop Code: "
                    + fullGrid[i][1] +
                    "\nStop Name: " + fullGrid[i][2] + "\nStop Destination: " + fullGrid[i][3] + "\nStop Latitude: "
                    + fullGrid[i][4]
                    + "\nStop Longitude: " + fullGrid[i][5] +
                    "\nZone ID: " + fullGrid[i][6] + "\n";
            map.put(fullGrid[i][0], answer);
        }
    }

    public List<String> getStopInformation(String input) {
        List<String> list = new LinkedList<>();
        this.startsWith(input).forEach((info) -> {
            list.add(map.get(this.get(info)));
        });
        if (list.isEmpty()) {
            list.add("Whoopsie, no such address compadre! Try again!\n");
        }
        return list;
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
            // this.insert(grid[i][2],grid[i][0]); //cannot use here cause static methods
            // cant use instances
        }
        return grid;
    }

}