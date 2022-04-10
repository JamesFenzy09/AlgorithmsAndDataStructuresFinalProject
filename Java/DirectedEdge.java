package Java;

import java.util.Arrays;

//Code adapted from Algorithms fourth edition, Robert Sedgewick and Kevin Wayne.
//https://algs4.cs.princeton.edu/44sp/DirectedEdge.java.html

public class DirectedEdge {
    private final double weight; // weight of this edge
    private final int v; // vertex this edge points from
    private final int w; // vertex this edge points to;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v; // vertex from, aka source vertex
        this.w = w; // vertex to, aka destination vertex
        this.weight = weight;

        // error checking ensure the vertex names are within the given range
        if (v < 0)
            throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (w < 0)
            throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (Double.isNaN(weight))
            throw new IllegalArgumentException("Weight is NaN");
    }

    public int from() { // vertex this edge points from
        return v;

    }

    public int to() { // vertex this edge points to
        return w;
    }

    public double weight() {
        return weight;
    }
}