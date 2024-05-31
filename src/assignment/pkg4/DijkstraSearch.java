import java.util.*;

public class DijkstraSearch<V> {

    private final Map<Vertex<V>, Double> distances = new HashMap<>();
    private final Map<Vertex<V>, Vertex<V>> previous = new HashMap<>();
    private final PriorityQueue<Vertex<V>> priorityQueue = new PriorityQueue<>(Comparator.comparing(distances::get));

    public void dijkstra(WeightedGraph<V> graph, Vertex<V> source) {
        distances.put(source, 0.0);
        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            Vertex<V> current = priorityQueue.poll();

            for (Edge<V> edge : graph.getAdjVertices(current)) {
                Vertex<V> neighbor = edge.destination();
                double newDist = distances.get(current) + edge.weight();
                if (newDist < distances.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    priorityQueue.add(neighbor);
                }
            }
        }
    }

    // Check if path exists to destination
    public boolean hasPath(Vertex<V> destination) {
        return previous.get(destination) != null;
    }

    public double getDistanceTo(Vertex<V> destination) {
        return distances.getOrDefault(destination, Double.POSITIVE_INFINITY);
    }

    public List<Vertex<V>> getShortestPath(Vertex<V> destination) {
        if (!hasPath(destination)) {
            return null;
        }
        List<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> at = destination; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
