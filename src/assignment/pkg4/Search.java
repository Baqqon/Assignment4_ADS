import java.util.*;

public abstract class Search<Vertex> {
    protected final Vertex source;
    protected final Set<Vertex> marked;
    protected final Map<Vertex, Vertex> edgeTo;

    public Search(Vertex source) {
        this.source = source;
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(Vertex v) {
        return marked.contains(v);
    }

    public Iterable<Vertex> pathTo(Vertex v) {
        if (!hasPathTo(v)) return null;
        LinkedList<Vertex> path = new LinkedList<>();
        for (Vertex x = v; x != source; x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(source);
        return path;
    }
}