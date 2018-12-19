/**
 * Listing.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 *
 * listing.java is responsible for outputting the initial
 * text file contents with line numbers, building a binary tree
 * for the cross-reference listing words, 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Listing {
	SuperOutput out;
	Scanner in;
	
	Hash hash;
	ObjectBinaryTreeInterface tree = new ObjectBinaryTree();
	
	int lineNumber;
	int wordPosition;

	/**
	 * default constructor
	 */
	public Listing() {
		
	}
	
	/**
	 * two-argument constructor
	 * @param out SuperOutput object for easy output
	 * @param h hash table to be used when building the tree
	 * @throws FileNotFoundException if the file is not found
	 */
	public Listing(SuperOutput out, Hash h) throws FileNotFoundException {
		this.out = out;
		in = new Scanner(new File("getty.txt"));
		hash = h;
		
		lineNumber = 1;
		
		buildTree();
	}

	/**
	 * reads each line of text from the file, displaying line numbers as we go
	 * @throws FileNotFoundException if the file is not found
	 */
	public void readLines() throws FileNotFoundException {
		out.output("The contents of the file which will be read are shown below, with line numbers: %n%n");
		
		while(in.hasNextLine()) {
			out.output("%2d ", lineNumber);
			out.output(in.nextLine() + "%n");
			lineNumber++;
		}
		out.output("%n");
		
		in = new Scanner(new File("getty.txt"));
		lineNumber = 1;
	}
	
	/**
	 * outputs a cross-reference listing of all useful words in the text file
	 * by performing an inorder traversal of the binary tree. output is alphabetized
	 * and word positions are ordered in ascending fashion
	 */
	public void generateListing() {
		out.output("Word listing for each useful word, showing the words themselves, the number%n"
				+ "of times they appear in the text, and their positions in the text:%n%n");
		out.output("%-15s %-15s %-15s %n", "Word", "Occurences", "Position(s)...");
		out.output("----            ----------      --------------%n");
		tree.inTrav(tree.getRoot());
		out.output("%n");
	}
	
	/*
	 * helper methods
	 ******************************************************************/
	
	/**
	 * buildTree scans all words from getty.txt and inserts them into a binary tree as
	 * Word objects. any useless words from the hash table are not inserted. buildTree
	 * keeps track of the current line number and position of each word.
	 * @throws FileNotFoundException
	 */
	private void buildTree() throws FileNotFoundException {
		String input;
		String delims;
		String[] tokens;
		
		while(in.hasNextLine()) { 
			input = in.nextLine();
			delims = "[ ]+";
			tokens = input.split(delims);
			
			for(int i = 0; i < tokens.length; i++) {
				if(!hash.contains(tokens[i])) {
					tree.insertBSTDup(new Word(out, tokens[i], lineNumber, i + 1));
				}
			}
			lineNumber++;
		}
		in = new Scanner(new File("getty.txt"));
		lineNumber = 1;
	}
	
	/*
	 * Getter for the binary tree
	 *********************************************************************/
	
	/**
	 * this getter is important for the query system. with this method, the binary tree
	 * built from buildTree can be passed over to the query object.
	 * @return a BinaryTreeInterface object which is the complete tree.
	 */
	public ObjectBinaryTreeInterface getTree() {
		return tree;
	}
}
