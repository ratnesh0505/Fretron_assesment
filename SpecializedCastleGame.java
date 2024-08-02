// The task was to find all possible paths for a "specialized castle" to return to its starting position on a grid, following certain movement rules. The initial thought was to implement a pathfinding algorithm that could explore all possibilities based on the castle's movement constraints (only moving forward, turning left after a kill, etc.). This problem has similarities to graph traversal problems, particularly Depth-First Search (DFS), where you explore all possible paths and backtrack when necessary. The challenge was to ensure all unique paths were found and stored without duplication. Backtracking was essential here as it allowed us to explore different paths by restoring the grid to its previous state after each recursive call.


import java.util.*;
public class SpecializedCastleGame {

    static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; 
    static Set<String> uniquePaths = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of soldiers: ");
        int numSoldiers = scanner.nextInt();
        int[][] board = new int[10][10];

        for (int i = 0; i < numSoldiers; i++) {
            System.out.print("Enter coordinates for soldier " + (i + 1) + ": ");
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            board[x][y] = 1; /
        }

        System.out.print("Enter the coordinates for your “special” castle: ");
        int startX = scanner.nextInt() - 1;
        int startY = scanner.nextInt() - 1;

        List<String> currentPath = new ArrayList<>();
        findPaths(board, startX, startY, 0, currentPath);

        System.out.println("Thanks. There are " + uniquePaths.size() + " unique paths for your ‘special_castle’");

        int pathNumber = 1;
        for (String path : uniquePaths) {
            System.out.println("Path " + pathNumber++ + ":\n" + path);
        }
    }

    static void findPaths(int[][] board, int x, int y, int direction, List<String> currentPath) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;

        if (board[x][y] == 1) { 
            currentPath.add("Kill (" + (x + 1) + "," + (y + 1) + "). Turn Left");
            board[x][y] = 0; 

            direction = (direction + 1) % 4; 

            findPaths(board, x + DIRECTIONS[direction][0], y + DIRECTIONS[direction][1], direction, currentPath);

            board[x][y] = 1; 
            currentPath.remove(currentPath.size() - 1);
        } else if (x == 0 && y == 1) { // Arrived back home
            currentPath.add("Arrive (" + (x + 1) + "," + (y + 1) + ")");
            uniquePaths.add(String.join("\n", currentPath));
            currentPath.remove(currentPath.size() - 1);
        } else {
            findPaths(board, x + DIRECTIONS[direction][0], y + DIRECTIONS[direction][1], direction, currentPath);
        }
    }
}
