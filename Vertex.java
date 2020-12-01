import java.util.HashSet;
import java.util.Set;

public class Vertex<T> {

    private T name;
    private Vertex<T> parent;
    private boolean visited;
    private Set<Edge<T>> edges;

    public Vertex(T name) {
        this(name, new HashSet<>());
    }

    public Vertex(T name, Set<Edge<T>> edges) {
        this.name = name;
        this.edges = edges;
    }

    public Edge addEdge(Edge<T> edge) {
        this.edges.add(edge);
        return edge;
    }

    public T getName() {
        return name;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public Vertex<T> getParent() {
        return parent;
    }

    public void setParent(Vertex<T> parent) {
        this.parent = parent;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    public boolean containsEdge(T destination) {
        return this.getEdges().stream().anyMatch(edge->edge.getDestination().getName().equals(destination));
    }
    
    public boolean removeEdge(T name) {
        return this.edges.removeIf(a->a.getDestination().name.equals(name));
    }
}