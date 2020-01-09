package assignment07;

import java.util.LinkedList;

public class Graph {
	private int _size;
	Node[][] verticies;
	LinkedList<Node> Q;
	Node start;
	Node goal;
	boolean hasStart = false;
	boolean hasGoal = false;
	int _height;
	int _width;
	Graph(int height, int width){
		_size = 0;
		_height = height;
		_width = width;
		verticies = new Node [height][width];
	}
	/**
	 * Gets the size of the graph.
	 * @return the number of nodes is the graph
	 **/
	public int size() {
		return _size;
	}
	
	/**
	 * Gets the width of the graph.
	 * @return int -the width the graph
	 **/
	public int getWidth() {
		return _width;
	}
	
	/**
	 * Gets the height of the graph.
	 * @return int -the height the graph
	 **/
	public int getHeight() {
		return _height;
	}
	
	/**
	 * Adds valid neighbors to node's neighbor collection for each Node in the graph.
	 * 
	 **/
	public void findNeighbors() {
		for(int row = 0; row < _height; row++) {
			for(int col = 0; col < _width; col++) {
				Node currentNode = getVerticy(row, col);
				if(currentNode == null) continue;
				if(col -1 > 0 && getVerticy(row, col -1) != null) {
					currentNode.addNeighbor(getVerticy(row, col - 1));
					currentNode.neighborTypes.add("Left");
				}
				//right
				if(col +1 < _width && getVerticy(row, col +1) != null) {
					currentNode.addNeighbor(getVerticy(row, col +1));
					currentNode.neighborTypes.add("Right");
				}
				//up
				if(row -1 > 0 && getVerticy(row -1, col) != null) {
					currentNode.addNeighbor(getVerticy(row -1, col));
					currentNode.neighborTypes.add("Up");
				}
				//down
				if(row +1 < _height && getVerticy(row + 1, col) != null) {
					currentNode.addNeighbor(getVerticy(row + 1, col));
					currentNode.neighborTypes.add("Down");
				}
			}
		}
	}
	
	/**
	 * Sets the value of a node in the graph, increments size, and sets the row and col indicies of node
	 * @throws error if more than 1 Start node in the input graph
	 * @throws error if more than 1 Goal node in the input graph
	 **/
	public void addVerticy(int row, int col, char val) {
		
		if(val=='X') {
			verticies[row][col] =  null;
			_size +=1;
		}else if(val=='S') {
			Node newNode = new Node(val);
			verticies[row][col] =  newNode;
			_size +=1;
			if(start != null) throw new Error("Invalid maze, only 1 start allowed");
			start = newNode;
			newNode.row = row;
			newNode.col = col;
			hasStart = true;	
		}else if(val=='G') {
			Node newNode = new Node(val);
			verticies[row][col] =  newNode;
			if(goal != null) throw new Error("Invalid maze, only 1 goal allowed");
			goal = newNode;
			_size +=1;
			newNode.row = row;
			newNode.col = col;
			hasGoal = true;
		}else{
			Node newNode = new Node(val);
			verticies[row][col] =  newNode;
			_size +=1;
			newNode.row = row;
			newNode.col = col;
		}	
		
	}
	
	/**
	 * Gets a verticy/ Node based on it's row and column
	 * @return target verticy/node
	 **/
	public Node getVerticy(int row, int col) {
		return verticies[row][col];
	}
	
	/**
	 * Gets collection of all verticies/ Nodes
	 * @return all verticies in graph
	 **/
	public Node[][] getVerticies() {
		return verticies;
	}
	
	/**
	 * Performs Breadth First Search on graph setting visited and came from properties 
	 * on all relevant nodes
	 * @return the goal node if shortest path found else returns null;
	 **/
	public Node breadthFirstSearch() {
		Q = new LinkedList<Node>();
		start.visited = true;
		Q.add(start);
		while(Q.size() > 0 ) {
			Node current = Q.remove();
			
			if(current.equals(goal)) { 
				System.out.println("Found Goal");
				return current;
			}
			for(Node neighbor: current.neighbors) {
				if(!neighbor.visited) {
					neighbor.visited = true;
					neighbor.cameFrom = current;
					Q.add(neighbor);
					//neighbor.data = '.';
				}
			}
		}
		//check output
//		for(int row = 0; row < _height; row++) {
//			for(int col = 0; col < _width; col++) {
//				
//				if(verticies[row][col] == null) {
//					System.out.print("X");
//				}else {
//					System.out.print(verticies[row][col].getData());
//				}					
//			}
//			System.out.println();
//		}
		return null;
	}
	
	/**
	 * Changes the value of nodes in the shortest path based on came from property of the node
	 * @param - the goal node to use as a start position
	 **/
	public void addPath(Node current) {
		if(current.cameFrom == null) return;
		current.setData('.');
		if(current == goal) current.setData('G');
		addPath(current.cameFrom);
	}
}
