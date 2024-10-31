import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFrame;

public class HyperRingGraph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;

        // Get the number of nodes with input validation
        while (true) {
            System.out.print("Enter the number of nodes: ");
            try {
                n = scanner.nextInt();
                if (n <= 0) {
                    System.out.println("Number of nodes must be positive. Please try again.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();  // Clear invalid input
            }
        }

        Graph hyperRingGraph = new Graph(n);
        System.out.println("Graph connectivity:");
        hyperRingGraph.displayAdjacencyMatrix();

        int choice = 0;

        // Choose traversal method with input validation
        while (true) {
            System.out.println("\nChoose traversal method:");
            System.out.println("1. Depth-First Search (DFS)");
            System.out.println("2. Breadth-First Search (BFS)");
            try {
                choice = scanner.nextInt();
                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice. Enter 1 for DFS or 2 for BFS.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.next();  // Clear invalid input
            }
        }

        int startNode = -1;

        // Get the starting node with range validation
        while (true) {
            System.out.print("Enter the starting node for traversal: ");
            try {
                startNode = scanner.nextInt();
                if (startNode < 0 || startNode >= n) {
                    System.out.println("Starting node must be between 0 and " + (n - 1) + ". Please try again.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer between 0 and " + (n - 1) + ".");
                scanner.next();  // Clear invalid input
            }
        }

        // Perform traversal based on choice
        System.out.println("Traversal result:");
        if (choice == 1) {
            hyperRingGraph.dfsTraversal(startNode);
        } else {
            hyperRingGraph.bfsTraversal(startNode);
        }

        // Visualize the graph with validation for 'y' or 'n'
        System.out.print("\nIf you want to visualize the graph, enter 'y' (or 'n' to skip): ");
        char visualize = 'n';
        while (true) {
            String input = scanner.next().toLowerCase();
            if (input.length() == 1 && (input.charAt(0) == 'y' || input.charAt(0) == 'n')) {
                visualize = input.charAt(0);
                break;
            } else {
                System.out.println("Invalid input. Please enter 'y' to visualize or 'n' to skip.");
            }
        }

        if (visualize == 'y') {
            JFrame frame = new JFrame("HyperRing Graph Visualization");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GraphPanel(hyperRingGraph));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            System.out.println("Exiting program.");
        }

        scanner.close();
    }
}
