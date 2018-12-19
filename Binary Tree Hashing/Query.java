/**
 * Query.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 * 
 * Queries the text document for particular words
 */

import java.util.Scanner;

public class Query {
	SuperOutput out;
	Scanner input;
	
	ObjectBinaryTreeInterface tree;
	
    /**
     * default constructor
     */
	public Query() {
		
	}

	/**
	 * two-parameter constructor
	 * @param out SuperOutput object for easy output
	 * @param tree tree interface for this class's binary tree
	 */
	public Query(SuperOutput out, ObjectBinaryTreeInterface tree) {
		this.out = out;
		input = new Scanner(System.in);
		
		this.tree = tree;
	}
	
	/**
	 * this search function uses a sentinel-controlled loop to allow inputs from the user.
	 * it displays the results of a search for a specific word in the cross-reference listing.
	 */
	public void search() {
		String str = " ";
		ObjectTreeNodeInterface node;
		
		out.output("Now you can ask for any word in the lising, and information about that word will be displayed%n"
				+ "To search, enter a word and press the return key. To exit, enter quit %n%n");
		
		while(!str.equals("quit")) {
			str = input.nextLine();
			out.outputFile(str + "%n%n");
			node = tree.searchBST(new Word(out, str));
			
			if(str.equals("quit")) {
				out.output("Exiting...");
				return;
			}
			if(node == null) {
				out.output("The word could not be found!%n%n");
			}
			else {
				out.output("The word was found successfully!%n%n");
				Word w = (Word)node.getInfo();
				out.output("%-15s %-15s %-15s %n", "Word", "Occurences", "Position(s)...");
				out.output("----            ----------      --------------%n");
				w.visit();
				out.output("%n");
			}
		}
	}
}
