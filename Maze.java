package assignment07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Maze {
	Graph mazeGraph;
	private int width;
	private int height;
	private String outputFileLocation; 
	
	Maze(String inputFile, String outputFile) throws IOException{
		readFile(inputFile);
		mazeGraph.findNeighbors();
		outputFileLocation = outputFile;
	}
	/**
	 * Container method that calls Depth First Search, and if a path is found, calls method to draw
	 * the shortest path to the output string. Finally this method calls a method to write the ouputfile 
	 * **/
	public void solve() {
		Node goalNode = mazeGraph.breadthFirstSearch();
		if (goalNode != null) {
			mazeGraph.addPath(goalNode);
		}
		String outputString = createOutputString();
		printFile(outputString);
	}
	
	/**
	 * Reads a file from the input string location and creates a graph object of the input Maze
	 * @param inputFilePath string - where to upload the file from
	 * @throws IOException if there is a problem reading the file. 
	 * @throws Invalid Maze exception if the input maze does not have a valid start and goal
	 * @throws Number Format Exception if the input file does not properly format the Graph width and height as Integers. 
	 * **/
	private void readFile(String inputFilePath) throws IOException {
		File file = new File(inputFilePath);
		BufferedReader buffer;
		try {
			buffer = new BufferedReader(new FileReader(file));
			String[] dimensions = buffer.readLine().split(" ");
			try{
				height = Integer.parseInt(dimensions[0]);
				width = Integer.parseInt(dimensions[1]);
			}catch (NumberFormatException e) {
				buffer.close();
				throw new NumberFormatException("First Line must be represent Maze height and width");
			}
			mazeGraph = new Graph(height, width);
			
			int h =  0;
			while(h <= height) {
				String line = buffer.readLine();
				if (line == null) break;
				System.out.println(line);
				for(int w = 0; w < line.length(); w++) {
					mazeGraph.addVerticy(h,w, line.charAt(w));
					line.charAt(w);	
				}
				h += 1;	
			}
			if(!mazeGraph.hasStart || !mazeGraph.hasGoal) {
				buffer.close();
				throw new Error("Invalid Maze: needs a valid start and end");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * builds the output string of the result maze.
	 * @return a string representation of the result maze.
	 **/
	private String createOutputString() {
		String output = "";
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if( mazeGraph.getVerticy(row,col) == null) {
					output += 'X';
				}else {
					output += mazeGraph.getVerticy(row,col).getData();
				}					
			}
			output += "\r\n";
		}
		return output;
	}
	
	/**
	 * prints file to the output location specified in the constructor. 
	 *  - this method uses the outputString created in createOutputString
	 **/
	private void printFile(String outputString) {
		try(PrintWriter output = new PrintWriter(new FileWriter(outputFileLocation))) {
		     output.println(height + " " + width);
		     output.print(outputString);
		} catch (IOException e) {
			System.out.println("Something went wrong printing the file");
			e.printStackTrace();
		}
	}
	
}
