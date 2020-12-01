public class Edge<T> {
    private Vertex<T> destination;
    private double weight;

    public Edge(Vertex<T> destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public void setDestination(Vertex<T> destination) {
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}