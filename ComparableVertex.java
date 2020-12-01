import java.util.Set;

public class ComparableVertex<T> extends Vertex<T> implements Comparable<ComparableVertex> {

    private double distance = Double.POSITIVE_INFINITY;

    public ComparableVertex(T name) {
        super(name);
    }

    public ComparableVertex(T name, Set<Edge<T>> edges) {
        super(name, edges);
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int compareTo(ComparableVertex other) {
        return Double.compare(this.distance, other.getDistance());
    }
    
}