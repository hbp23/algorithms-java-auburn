import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class DijkstrasAlgorithm {
    // dijkstras algorithm method
    // Allow a user to provide a graph and a source vertex s as a text file
    // Display the shortest path for s to every other vertex in the graph
    // Output a file text describing the shortest path for s to every other vertex in the graph

    // List of all vertices in graph
    public List<Vertex> Vertices = new ArrayList<>();

    public void dijkstrasAlgorithm(Vertex s) {
        s.d = 0; // sets source distance to 0
        Set<Vertex> S = new HashSet<>(); // still have no idea what this is for
        Queue<Vertex> Q = new PriorityQueue<>(new distanceComparator()); // this is a queue that will pop out the
        // vertex with the smallest distance (it is using a comparator for that)
        Q.addAll(Vertices); // this adds all vertices to the queue
        while (!Q.isEmpty()) {
            Vertex u = Q.poll(); // pops out the vertex with the smallest distance
            S.add(u); // still no clue what this is for
            for (Edge e : u.adjacentVertices) { // looks through all edges for the Vertex 'u'
                Vertex v = Vertex.findVertex(e.name, Vertices);
                // in edge class, since it takes a string instead of vertex, I needed to get the vertex
                // that vertex 'u' has an edge for so this finds it by comparing the names. I did this because this
                // way we can read the .txt file line by line only once without messing anything up, since we can create
                // a new Vertex with the first letter, and then add edges for the ones in the same line. Edges are
                // stored in Vertex class
                if (Relax(u, e, v)) { // does the relax method as pp in class
                    Q.remove(v); // needed to remove and add to reorganize queue since the priority queue only
                    Q.add(v); // reorganizes when you do call poll() or add() and since we do poll() and then alters
                    // the distances afterward, the queue never reorganizes. this was the easiest solution.
                    // priority queue uses heaps btw
                }
            }
        }
    }

    public boolean Relax(Vertex u, Edge e, Vertex v) {
        if (v.d > u.d + e.weight) {
            v.d = u.d + e.weight;
            v.predecessor = u;
            return true; // regular relax method in pp in class, just need boolean to see if queue needs to be
        } // reorganized
        return false;
    }

    public void readFile(String filePath) throws FileNotFoundException {
        Scanner lineScan = new Scanner(new File(filePath));
        String sourceString = lineScan.nextLine();
        while (lineScan.hasNextLine()) {
            String line = lineScan.nextLine();
            Scanner wordScan = new Scanner(line);
            Vertex current = new Vertex(wordScan.next());
            Vertices.add(current);
            while (wordScan.hasNext()) {
                String word = wordScan.next();
                String[] edge = word.split(",");
                current.adjacentVertices.add(new Edge(edge[0], Integer.parseInt(edge[1])));
            }
            wordScan.close();
        }
        lineScan.close();
        dijkstrasAlgorithm(Vertex.findVertex(sourceString, Vertices));
        // UPDATE FILE PATH
        File output = new File("/Users/harshpatel/Desktop/AUBURN_UNIVERSITY/CPSC_3280_Fall_2024/m4/m4programmingassignment/src/outputShortestPaths.txt");
        PrintWriter writer = new PrintWriter(output);
        for (Vertex v : Vertices) {
            if (sourceString.equals(v.name)) {
                continue;
            }
            String line = Vertex.printShortestPath(v);
            writer.println(line);
            writer.flush();
            System.out.println(line);
        }
        writer.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Please enter file name: ");
        DijkstrasAlgorithm d = new DijkstrasAlgorithm();
        Scanner scan  = new Scanner(System.in);
        d.readFile(scan.nextLine());
        scan.close();
    }
}

