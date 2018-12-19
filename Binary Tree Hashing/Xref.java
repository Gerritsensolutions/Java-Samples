/**
 * Xref.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 * 
 * references for the words to be ignored
 */

import java.io.FileNotFoundException;

public class Xref {
	SuperOutput out;
	
	Hash hash;
	Listing listing;
	Query query;
	
    /**
     * default constructor
     */
	public Xref() {
		
	}
	
	/**
     * single-argument constructor
     * @param out SuperOutput object for easy output
     */
	public Xref(SuperOutput out) {
		this.out = out;
	}

	/**
	 * outputs a nice header at the start of the program
	 */
	public void outputHeader() {
		out.output("TEXT FILE CROSS-REFERENCE LISTING%n");
		out.output("---- ---- --------------- -------%n");
		out.output("%n");
	}
	
	/**
	 * creates a hash table from omit.txt
	 * @param out SuperOutput object for easy output
	 * @throws FileNotFoundException if the file is not found
	 */
	public void buildHashTable(SuperOutput out) throws FileNotFoundException {
		hash = new Hash(out);
	}
	
	/**
	 * performs all tree-related operations
	 * @throws FileNotFoundException if the file is not found
	 */
	public void buildBinaryTree() throws FileNotFoundException {
		listing = new Listing(out, hash);
		listing.readLines();
		listing.generateListing();
	}
	
	/**
	 * runs queries on the binary search tree, displaying the found words
	 */
	public void runQueries() {
		query = new Query(out, listing.getTree());
		query.search();
	}
}
