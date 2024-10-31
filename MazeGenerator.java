import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeGenerator {
    private int rows, cols;
    private int[][] maze;

    // Directions for moving in the grid (right, down, left, up)
    private final int[][] directions = {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public MazeGenerator(int rows, int cols) {
        // Ensure odd dimensions to avoid isolated rows/columns
        this.rows = rows % 2 == 1 ? rows : rows + 1;
        this.cols = cols % 2 == 1 ? cols : cols + 1;
        maze = new int[this.rows][this.cols];
        generateMaze(1, 1);  // Start from an odd cell

        // Ensure there's an exit at the bottom-right corner
        maze[rows - 2][cols - 1] = 1;  // Make a path into the corner
        maze[rows - 1][cols - 2] = 1; 
        maze[rows - 1][cols - 1] = 1; // Exit point
    }

    // DFS-based recursive maze generation
    private void generateMaze(int row, int col) {
        maze[row][col] = 1;  // Mark the current cell as a path (visited)

        // Randomize directions to ensure random maze generation
        List<int[]> shuffledDirections = new ArrayList<>();
        for (int[] direction : directions) {
            shuffledDirections.add(direction);
        }
        Collections.shuffle(shuffledDirections);

        // Try each direction in random order
        for (int[] direction : shuffledDirections) {
            int newRow = row + direction[0] * 2;
            int newCol = col + direction[1] * 2;

            if (isValid(newRow, newCol)) {
                // Carve a path between the cells
                maze[row + direction[0]][col + direction[1]] = 1;
                generateMaze(newRow, newCol);
            }
        }
    }

    // Check if the new cell is within bounds and not visited
    private boolean isValid(int row, int col) {
        return row > 0 && row < rows - 1 && col > 0 && col < cols - 1 && maze[row][col] == 0;
    }

    // Print the generated maze with start and end points marked
    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 1 && j == 1) {
                    System.out.print("S ");  // Mark the start point
                } else if (i == rows - 1 && j == cols - 1) {
                    System.out.print("E ");  // Mark the end point
                } else {
                    // Use '█' for walls and '.' for paths
                    System.out.print(maze[i][j] == 1 ? ". " : "█ ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rows = 75;
        int cols = 75;

        MazeGenerator maze = new MazeGenerator(rows, cols);
        maze.printMaze();
    }
}
