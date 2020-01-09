package assignment07;

import java.util.ArrayList;
import java.util.LinkedList;

public class Node {
	private char data;
	public int row;
	public int col;
	LinkedList<Node> neighbors; 
	ArrayList<String> neighborTypes;
	public boolean visited = false;
	Node cameFrom = null;
	
	public char getData() {
		return data;
	}
	
	public void setData(char val) {
		data = val;
	}
	
	Node(char value){
		data = value;
		neighbors = new LinkedList<Node>();
		neighborTypes = new ArrayList<String>();
	}
	
	/**
	 * adds a new neighbor node to the list of neighbors
	 **/
	public boolean addNeighbor(Node node) {
		if(neighbors.contains(node)) {
			return false;
		}
		return neighbors.add(node);
	}
}
