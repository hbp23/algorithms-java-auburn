import java.util.Comparator;

public class distanceComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex v1, Vertex v2) {
        return Integer.compare(v1.d, v2.d);
    }
}
