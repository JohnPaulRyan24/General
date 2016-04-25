package PageRank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PageRank {
	static int NUM_OF_NODES;
	public static void main(String[] args) throws FileNotFoundException{
		int ITERATIONS = 10000;
		
		//JOHN JUST DO IT 
		File f = new File("adjmat.txt");
		//expected input - txt file with 
		//first line = number of nodes, then 
		//rest of lines the rows of the adjacency matrix (elements separated by spaces
		Scanner scan = new Scanner(f);
		NUM_OF_NODES = Integer.parseInt(scan.nextLine());
		Node[] ALL_NODES = new Node[NUM_OF_NODES];
		Node n;
		for(int i=0; i<NUM_OF_NODES; i++){
			n = new Node();
			n.currentPR = 1.0/NUM_OF_NODES;
			ALL_NODES[i] = n;
			
		}
		for(int i=0; i<NUM_OF_NODES; i++){		
			for(int j=0; j<NUM_OF_NODES; j++){
				if(scan.next().equals("1")) ALL_NODES[i].neighbors.add(ALL_NODES[j]);
					
			}
		}
		for(int k=0; k<ITERATIONS; k++){
			if(k%1000==0) printScores(ALL_NODES);
			updateScores(ALL_NODES);
		}
		
		
		scan.close();
	}
	public static void updateScores(Node[] nodes){
		for(Node n : nodes){
			n.lastPR = n.currentPR;
			n.currentPR = 0;
		}
		double dole;
		for(Node node : nodes){
			if(node.neighbors.size()==0){
				node.currentPR+=node.lastPR;
				continue;
			}
			dole = node.lastPR/node.neighbors.size();
			
			for(Node neighbor : node.neighbors){
				neighbor.currentPR+=dole;
			}	
		}	
	}
	
	public static void printScores(Node[] nodes){
		System.out.println("#####################");
		for (int i=0; i<NUM_OF_NODES; i++){
			System.out.println("Node "+i+": "+nodes[i].currentPR);
		}System.out.println("#####################");
	}
	
	
}
class Node{
	double lastPR;
	double currentPR;
	ArrayList<Node> neighbors = new ArrayList<Node>();
	
}
