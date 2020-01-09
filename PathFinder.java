package assignment07;

import java.io.IOException;

public class PathFinder {
	PathFinder(String inputFile, String outputFile){
		solveMaze(inputFile, outputFile);
	}

	public static void solveMaze(String inputFile, String outputFile) {
		try {
			Maze maze = new Maze(inputFile, outputFile);
			maze.solve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
