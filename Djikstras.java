import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Djikstras<T> {

	public Set<T> constructPath(T startVertex, Vertex<T> endVertex) {
        Stack<T> stack = new Stack<>();
        
        while(!endVertex.getName().equals(startVertex)) {
        	
            stack.push(endVertex.getName());
            endVertex = endVertex.getParent();
            
            if (endVertex == null) {
                return Collections.emptySet();
            }
        }
        stack.push(startVertex);

        Set<T> path = new LinkedHashSet<>();
        
        while(!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return path;
    }

    public Set<T> findShortestPath(Graph<T> graph, T start, T end) {

        Vertex<T> startVertex = graph.getVertex(start);
        Vertex<T> endVertex = graph.getVertex(end);
        Queue<Vertex<T>> queue = new PriorityQueue<>();
        
    	if (graph == null || start == null || end == null) {
    		return Collections.emptySet();
        }
        if (start.equals(end)) {
            return Collections.emptySet();
        }
        if (startVertex == null || endVertex == null) { 
        	return Collections.emptySet(); 
        }
        
        queue.add(startVertex);
        ((ComparableVertex<T>)startVertex).setDistance(0D);

        while(!queue.isEmpty()) {
            ComparableVertex<T> prev = (ComparableVertex<T>)queue.remove();

            prev.getEdges().forEach(edge -> {
            	
                ComparableVertex<T> curr = (ComparableVertex<T>)edge.getDestination();
                if (!curr.isVisited()) {
                    double minDistance = Math.min(curr.getDistance(),
                            ((Edge<T>)edge).getWeight() + prev.getDistance());
                    if (minDistance <= curr.getDistance()) {
                        curr.setDistance(minDistance);
                        curr.setParent(prev);
                    }
                    queue.add(curr);
                }
            });

            prev.setVisited(true);
        }
        return constructPath(start, endVertex);
    }
}