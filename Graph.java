import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph<T> {
    public Map<T, Vertex<T>> adjacencyMap;

    public Graph() {
        this.adjacencyMap = new HashMap<>();
    }

    public void addEdge(T start, T end, double weight) {
    	
        Vertex<T> startVertex = addComparableVertex(start);
        Vertex<T> endVertex = addComparableVertex(end);

        Edge<T> startToDest = new Edge<>(endVertex, weight);
        Edge<T> destTostart = new Edge<>(startVertex, weight);

        if (!startVertex.containsEdge(end)) {
            startVertex.addEdge(startToDest);
        }
        if (!endVertex.containsEdge(start)) {
            endVertex.addEdge(destTostart);
        }
    }

    public boolean removeEdge(T start, T end) {

        boolean removeStart = this.adjacencyMap.get(start).removeEdge(end);
        boolean removeEnd = this.adjacencyMap.get(end).removeEdge(start);
        return removeStart || removeEnd;
    }

    public Vertex<T> addVertex(T name) {
        if (adjacencyMap.containsKey(name)) {
            return adjacencyMap.get(name);
        }
        Vertex<T> vertex = new Vertex<>(name);
        this.adjacencyMap.put(vertex.getName(), vertex);
        
        return vertex;
    }

    public Vertex<T> addComparableVertex(T name) {
        if (adjacencyMap.containsKey(name)) {
        	
            return adjacencyMap.get(name);
        }
        Vertex<T> vertex = new ComparableVertex<>(name);
        this.adjacencyMap.put(vertex.getName(), vertex);
        
        return vertex;
    }

    public Vertex<T> getVertex(T name) {
        return this.adjacencyMap.get(name);
    }

    public Set<T> getVertices() {
        return this.adjacencyMap.keySet();
    }

    public Set<Edge<T>> getEdges(T name) {
        Vertex<T> vertex = this.adjacencyMap.getOrDefault(name, null);
        
        if (null != vertex) {
            return this.adjacencyMap.get(name).getEdges();
        }
        return Collections.emptySet();
    }
    
}