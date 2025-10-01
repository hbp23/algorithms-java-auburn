import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Vertex {
    final String name;
    int d;
    Vertex predecessor;
    List<Edge> adjacentVertices;

    public Vertex(String name) {
        this.name = name;
        d = Integer.MAX_VALUE;
        predecessor = null;
        adjacentVertices = new ArrayList<>();
    }

    public String toString() {
        return name;
    }

    public static Vertex findVertex(String v, List<Vertex> vertices) {
        Vertex x = null;
        for (Vertex i : vertices) {
            if (v.equals(i.name)) { return i;} // does what described above
        }
        if (x == null) {throw new IllegalArgumentException("No source found or edge vertex not found");}
        return x;
    }

    public static String printShortestPath(Vertex v) {
        String result = v.name + ": ";
        for (Vertex i : getShortestPath(v)) {
            result += (i + " ");
        }
        return result;
    }

    public static List<Vertex> getShortestPath(Vertex v) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex p = v; p.predecessor != null; p = p.predecessor) {
            path.add(p);
        }
        Collections.reverse(path); // Reverse the path to start from the source
        return path;
    }

    /*public String toString() {
        String result = "{" + this.name + ", " + this.d + ", ";
        if (predecessor != null) { result += this.predecessor.name + "}";}
        else { result += null + "}";}
        return result;
    }*/
}
