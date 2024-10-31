import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Graph {
    private List<Vertex> vertices;
    private int[][] adjacencyMatrix;

    public Graph(int n) {
        vertices = new ArrayList<>();
        adjacencyMatrix = new int[n][n]; // Initialize adjacency matrix for n nodes

        // Set positions for nodes in a circle for visualization
        int radius = 150;
        int centerX = 200;
        int centerY = 200;

        for (int i = 0; i < n; i++) {
            int x = (int) (centerX + radius * Math.cos(2 * Math.PI * i / n));
            int y = (int) (centerY + radius * Math.sin(2 * Math.PI * i / n));
            vertices.add(new Vertex(i, x, y));
        }
        createHyperRingGraph(n);
    }

    private void createHyperRingGraph(int n) {
        for (int i = 0; i < n; i++) {
            for (int power = 1; power < n; power *= 2) {
                int neighbourIndex = (i + power) % n;
                if (adjacencyMatrix[i][neighbourIndex] == 0) {  // Avoid adding duplicate edges
                    // Add neighbor connections in both directions
                    vertices.get(i).addNeighbour(vertices.get(neighbourIndex));
                    vertices.get(neighbourIndex).addNeighbour(vertices.get(i));
                    
                    // Update adjacency matrix to reflect the undirected edge
                    adjacencyMatrix[i][neighbourIndex] = 1;
                    adjacencyMatrix[neighbourIndex][i] = 1;
                }
            }
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void displayAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
    
        // Print column headers with node numbers and a divider below
        System.out.print("    ");  // Initial spacing for row labels
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.out.printf("%3d", i);
        }
        System.out.println();
    
        System.out.print("   +");  // Corner symbol for the top left of the matrix
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.out.print("---");  // Divider line
        }
        System.out.println();
    
        // Print matrix rows with row headers and separators
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.out.printf("%2d |", i);  // Row header with separator
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                System.out.printf("%3d", adjacencyMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public void dfsTraversal(int start) {
        System.out.print("DFS Traversal starting from node " + start + ": ");
        boolean[] visited = new boolean[vertices.size()];
        dfsHelper(vertices.get(start), visited);
        System.out.println();  // Ensure newline after traversal output
    }
    
    private void dfsHelper(Vertex vertex, boolean[] visited) {
        System.out.print(vertex.id + " ");
        visited[vertex.id] = true;
        for (Vertex neighbour : vertex.getNeighbours()) {
            if (!visited[neighbour.id]) {
                dfsHelper(neighbour, visited);
            }
        }
    }
    
    public void bfsTraversal(int start) {
        System.out.print("BFS Traversal starting from node " + start + ": ");
        boolean[] visited = new boolean[vertices.size()];
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(vertices.get(start));
        visited[start] = true;
    
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            System.out.print(current.id + " ");
            for (Vertex neighbour : current.getNeighbours()) {
                if (!visited[neighbour.id]) {
                    visited[neighbour.id] = true;
                    queue.add(neighbour);
                }
            }
        }
        System.out.println();  // Ensure newline after traversal output
    }
}
