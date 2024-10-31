import java.util.ArrayList;
import java.util.List;

// Define Vertex class
class Vertex {
    int id;
    int x, y;  // Coordinates for visualization
    List<Vertex> neighbours;

    public Vertex(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.neighbours = new ArrayList<>();
    }

    public void addNeighbour(Vertex vertex) {
        neighbours.add(vertex);
    }

    public List<Vertex> getNeighbours() {
        return neighbours;
    }
}