/**
 * TQTree.java
 * A Java class that supports a Binary Tree that plays the game of twenty questions
 * 
 * @author Christine Alvarado
 * @version 1.0
 * @date May 11, 2014
 */
package hw7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Scanner;

import hw7.TQTree.TQNode;

public class TQTree {
    private TQNode root;

    /**
     * Inner class that supports a node for a twenty questions tree. You should not
     * need to change this class.
     */
    class TQNode {
        /* You SHOULD NOT add any instance variables to this class */
        TQNode yesChild; // The node's right child
        TQNode noChild; // The node's left child
        String data; // A question (for non-leaf nodes)
                     // or an item (for leaf nodes)

        int idx; // index used for printing

        /**
         * Make a new TWNode
         * 
         * @param data
         *            The question or answer to store in the node.
         */
        public TQNode(String data) {
            this.data = data;
        }

        /**
         * Setter for the noChild
         * 
         * @param noChild
         *            The new left (no) child
         */
        public void setNoChild(TQNode noChild) {
            this.noChild = noChild;
        }

        /**
         * Setter for the yesChild
         * 
         * @param yesChild
         *            The new right (yes) child
         */
        public void setYesChild(TQNode yesChild) {
            this.yesChild = yesChild;
        }

        /**
         * Getter for the yesChild
         * 
         * @return The node's yes (right) child
         */
        public TQNode getYesChild() {
            return this.yesChild;
        }

        /**
         * Getter for the noChild
         * 
         * @return The node's no (left) child
         */
        public TQNode getNoChild() {
            return this.noChild;
        }

        /**
         * Getter for the data
         * 
         * @return The data stored in this node
         */
        public String getData() {
            return this.data;
        }

        /**
         * Setter for the index
         * 
         * @param idx
         *            index of this for printing
         */
        public void setIndex(int idx) {
            this.idx = idx;
        }

        /**
         * get the index
         * 
         * @return idx index of this for printing
         */
        public int getIndex() {
            return this.idx;
        }
    } // End TQNode

    /**
     * Builds a new TQTree by reading from a file
     * 
     * @param filename
     *            The file containing the tree
     * @throws FileNotFoundException
     *             if the file cannot be found or read.
     */
    public TQTree(String filename) {
        File f = new File(filename);
        LineNumberReader reader;
        try {
            reader = new LineNumberReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file " + filename);
            System.err.println("Building default Question Tree.");
            buildDefaultTree();
            return;
        }

        buildTreeFromFile(reader);
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("An error occured while closing file " + filename);
        }

    }

    // Build the tree from the file that reader is reading from.
    private void buildTreeFromFile(LineNumberReader reader) {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            errorBuildTree("File contains no tree.");
            return;
        }

        if (line == null) {
            errorBuildTree("File contains no tree.");
            return;
        }
        String[] lineSplit = line.split(":", 2);
        if (lineSplit.length < 2) {
            errorBuildTree("Incorrect file format: line 1.");
            return;
        }
        String qOrA = lineSplit[0];
        String data = lineSplit[1];

        if (!qOrA.equals("Q")) {
            errorBuildTree("Incorrect file format: line 1.");
            return;
        }
        root = new TQNode(data);
        try {
            root.setNoChild(buildSubtree(reader));
            root.setYesChild(buildSubtree(reader));
        } catch (ParseException e) {
            errorBuildTree(e.getMessage() + ": line " + +e.getErrorOffset());
        }
    }

    // Print an error message and then build the default tree
    private void errorBuildTree(String message) {
        System.err.println(message);
        System.err.println("Buidling default Question Tree");
        buildDefaultTree();
    }

    /**
     * Recursive method to build a TQTree by reading from a file.
     * 
     * @param reader
     *            A LineNumberReader that reads from the file
     * @return The TQNode at the root of the created tree
     * @throws ParseException
     *             If the file format is incorrect
     */
    private TQNode buildSubtree(LineNumberReader reader) throws ParseException {

        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException("Error reading tree from file: " + e.getMessage(), reader.getLineNumber());
        }

        if (line == null) {
            // We should never be calling this method if we don't have any more input
            throw new ParseException("End of file reached unexpectedly", reader.getLineNumber());
        }

        String[] lineSplit = line.split(":", 2);
        String qOrA = lineSplit[0];
        String data = lineSplit[1];

        TQNode subRoot = new TQNode(data);
        if (qOrA.equals("Q")) {
            subRoot.setNoChild(buildSubtree(reader));
            subRoot.setYesChild(buildSubtree(reader));
        }
        return subRoot;
    }

    /** Builds a starter TQ tree with 1 question and 2 answers */
    public TQTree() {
        buildDefaultTree();
    }

    private void buildDefaultTree() {
        root = new TQNode("Is it bigger than a breadbox?");
        root.setNoChild(new TQNode("spam"));
        root.setYesChild(new TQNode("a computer scientist"));

    }

    /**
     * Play one round of the game of Twenty Questions using this game tree Augments
     * the tree if the computer does not guess the right answer
     */
    public void play(Scanner input) {
    		// TODO: Implement this method
    		Boolean end = false;
        String response;
    		System.out.println(root.data);
        int idx = root.idx;
        TQNode currentNode = root;           
        while(true){
        		// To get input from user use nextLine() method only.       
            response = input.nextLine();
            //When user inputs yes
        		if(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) 
        		{
        			TQNode yesChild = currentNode.getYesChild();
        			TQNode noChild = currentNode.getNoChild();
        			//End the game
        			if(end == true) break;
        			//If currentNode is a leaf node
        			//Computer won!
        			if(yesChild == null && noChild == null) {
        				System.out.println("I won!");
        				break;
        			}
        			//If yesChild is not null but a leaf node
        			//Take a guess
        			if(yesChild != null && 
        					yesChild.getYesChild() == null) {
        				System.out.println("Is it " + yesChild.data + "?");        			
        			}
        			//If yesChild is not null nor a leaf node
        			//Ask question
        			if(yesChild != null && 
        					yesChild.getYesChild() != null) {
        				System.out.println(yesChild.data);
        			}
        			currentNode = yesChild;
        		}
        		
        		//When user inputs anything other than yes
        		if(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("yes")) 
        		{
        		
        			String newObj;
        			String oldObj;
        			TQNode yesChild = currentNode.getYesChild();
        			TQNode noChild = currentNode.getNoChild();
        		
        			//If noChild is not null but a leaf node
        			//Take a guess
        			if(noChild != null && 
        					noChild.getYesChild() == null && end != true) {
        				System.out.println("Is it " + noChild.data + "?");  
        				currentNode = noChild;	
        			}
        			//If noChild is not null nor a leaf node
        			//Ask question
        			if(noChild != null && 
        					yesChild.getYesChild() != null) {
        				System.out.println(noChild.data);
        				currentNode = noChild;
        			}
        			//If currentNode is a leaf node
        			//Ask what was it
        			if(yesChild == null && noChild == null) {
        				System.out.println("Ok, what was it?");   
        				//Record the new object and 
        				//ask for a question to ask next time
        				response = input.nextLine();
        				newObj = response;
        				oldObj = currentNode.data;
        				currentNode.noChild = new TQNode(currentNode.data);
        				currentNode.yesChild = new TQNode(newObj); 
        				System.out.println("Give me a question that would "
        						+ "distinguish " + newObj + " from " + oldObj);
        				//Record the new question and
        				//ask how to place the new object
        				response = input.nextLine();
        				currentNode.data = response;
        				System.out.println("And would the answer to the "
        						+ "question for "+ newObj +" be yes or no?");
        				end = true;
        			}
        			//Swap yes and no child
        			else if(end == true) {
        				currentNode.setNoChild(yesChild);
        				currentNode.setYesChild(noChild);
        				break;
        			}
        		}
        }//end while loop
    }

    // PRIVATE HELPER METHODS
    // You will likely want to add a few more private helper methods here.
    // IMPORTANT NOTE: You will want to pass Scanner object to your helper methods
    // if you wish to get input in those methods
    // Make sure you only have calls to 'nextLine()' in your helper methods. Not
    // 'next()'

    public void print() {
        PrintWriter writer = new PrintWriter(System.out);
        printTree(writer, root);
        writer.flush();
    }

    /**
     * method for breadth-first traversal of the tree.
     * 
     * @param The
     *            print writer to write to (usually stdout)
     * @param The
     *            current root from which to write
     */
    private void printTree(PrintWriter writer, TQNode root) {
        // TODO: Implement this method
    	    LinkedList<TQNode> tree = new LinkedList<TQNode>();
        tree.add(root);
        TQNode currNode = root;
        int i = 0;
        int k = 0;
        while(currNode != null) {
        		if(currNode.getNoChild() != null && currNode.getYesChild() != null) {
        			tree.add(currNode.getNoChild());
        			tree.add(currNode.getYesChild());
        		}
        		if(i == tree.size()-1) break;
        		else {
        			i++;
        			currNode = tree.get(i);
        		}
        }
        
        for(int j = 0; j < tree.size(); j++) {
        		currNode = tree.get(j);
        		
        		if(currNode.getNoChild() != null && currNode.getYesChild() != null) {
        			int noChildIdx = 2*k + 1;
            	    int yesChildIdx = 2*k + 2;
            	    k++;
        			writer.println(j + ": '" + currNode.data + "' no:("
        					+ noChildIdx + ") yes:(" + yesChildIdx + ")");
        		}
        		if(currNode.getNoChild() == null && currNode.getYesChild() == null) {
        			writer.println(j + ": '" + currNode.data + 
        					"' no:(null) yes:(null)");
        		}
        }
    }

    /**
     * Save this Twenty Questions tree to the file with the given name
     * 
     * @param filename
     *            The name of the file to save to
     * @throws FileNotFoundException
     *             If the file cannot be used to save the tree
     */
    public void save(String filename) throws FileNotFoundException {
        File f = new File(filename);
        PrintWriter writer = new PrintWriter(f);
        saveTree(writer, root);
        writer.close();
    }

    /**
     * Recursive helper method to do the preorder traversal of the tree.
     * 
     * @param The
     *            print writer to write to
     * @param The
     *            current root from which to write
     */
    private void saveTree(PrintWriter writer, TQNode currentRoot) {
        if (currentRoot == null) {
            return;
        }
        String toWrite = "";
        if (currentRoot.getNoChild() == null && currentRoot.getYesChild() == null) {
            toWrite = "A:" + currentRoot.getData();
        } else {
            toWrite = "Q:" + currentRoot.getData();
        }
        writer.println(toWrite);
        saveTree(writer, currentRoot.getNoChild());
        saveTree(writer, currentRoot.getYesChild());
    }

}
