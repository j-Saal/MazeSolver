/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        ArrayList<MazeCell> solution = new ArrayList<>();
        Stack<MazeCell> solutions = new Stack<MazeCell>();
        MazeCell currentcell = maze.getEndCell();
        while (currentcell != maze.getStartCell()) {
            solutions.push(currentcell);
            currentcell = currentcell.getParent();
        }
        solutions.push(maze.getStartCell());
        while (!solutions.isEmpty()) {
            solution.add(solutions.pop());
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> path = new Stack<>();
        path.push(maze.getStartCell());
        maze.getStartCell().setExplored(true);
        while (!path.isEmpty()) {
            MazeCell current = path.pop();
            if (current == maze.getEndCell()) {
                return getSolution();
            }
            int currentRow = current.getRow();
            int currentCol = current.getCol();

            if (maze.isValidCell(currentRow - 1, currentCol)) {
                maze.getCell(currentRow - 1, currentCol).setExplored(true);
                maze.getCell(currentRow - 1, currentCol).setParent(current);
                path.push(maze.getCell(currentRow - 1, currentCol));
            }

            if (maze.isValidCell(currentRow, currentCol - 1)) {
                maze.getCell(currentRow, currentCol - 1).setExplored(true);
                maze.getCell(currentRow, currentCol - 1).setParent(current);
                path.push(maze.getCell(currentRow, currentCol - 1));
            }

            if (maze.isValidCell(currentRow + 1, currentCol)) {
                maze.getCell(currentRow + 1, currentCol).setExplored(true);
                maze.getCell(currentRow + 1, currentCol).setParent(current);
                path.push(maze.getCell(currentRow + 1, currentCol));
            }

            if (maze.isValidCell(currentRow, currentCol + 1)) {
                maze.getCell(currentRow, currentCol + 1).setExplored(true);
                maze.getCell(currentRow, currentCol + 1).setParent(current);
                path.push(maze.getCell(currentRow, currentCol + 1));
            }
        }
        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> path = new LinkedList<>();
        path.offer(maze.getStartCell());
        maze.getStartCell().setExplored(true);
        while (!path.isEmpty()) {
            MazeCell current = path.poll();
            if (current == maze.getEndCell()) {
                return getSolution();
            }
            int currentRow = current.getRow();
            int currentCol = current.getCol();

            if (maze.isValidCell(currentRow + 1, currentCol)) {
                maze.getCell(currentRow + 1, currentCol).setExplored(true);
                maze.getCell(currentRow + 1, currentCol).setParent(current);
                path.offer(maze.getCell(currentRow + 1, currentCol));
            }

            if (maze.isValidCell(currentRow, currentCol + 1)) {
                maze.getCell(currentRow, currentCol + 1).setExplored(true);
                maze.getCell(currentRow, currentCol + 1).setParent(current);
                path.offer(maze.getCell(currentRow, currentCol + 1));
            }

            if (maze.isValidCell(currentRow - 1, currentCol)) {
                maze.getCell(currentRow - 1, currentCol).setExplored(true);
                maze.getCell(currentRow - 1, currentCol).setParent(current);
                path.offer(maze.getCell(currentRow - 1, currentCol));
            }

            if (maze.isValidCell(currentRow, currentCol - 1)) {
                maze.getCell(currentRow, currentCol - 1).setExplored(true);
                maze.getCell(currentRow, currentCol - 1).setParent(current);
                path.offer(maze.getCell(currentRow, currentCol - 1));
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
