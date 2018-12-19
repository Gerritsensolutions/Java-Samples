/**
 * Hash.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 * 
 * Hashing class
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hash {
	Scanner omitIn;							// input-output data members
	SuperOutput out;						//
	
	final private int TABLESIZE;			// constant term for the table size. change this value to change
											// the size of the table
	
	private ObjectListInterface[] table;	// data members for all hash table information
	int collisions;							//
	int maxSize;							//
	double averageSize;						//

    /**
     * default constructor
     */
	public Hash() {
		TABLESIZE = 37;
	}
	
	/**
	 * one-argument constructor
	 * @param out SuperOutput object for easy output
	 * @throws FileNotFoundException if the file cannot be found
	 */
	public Hash(SuperOutput out) throws FileNotFoundException {
		// Initializations for all data members
		TABLESIZE = 37;
		omitIn = new Scanner(new File("omit.txt"));
		this.out = out;
		collisions = 0;
		
		table = new ObjectListInterface[TABLESIZE];
		for(int i = 0; i < table.length; i++) {
			table[i] = new ObjectList();
		}
		
		// creates the hash table and generates appropriate output
		build();
		getAverageSize();
		getMaxSize();
		outputDescription();
		outputCollisions();
		outputAverageSize();
		outputMaxSize();
		displayTable();
	}
	
	/**
	 * checks if the hash table contains a specific word
	 * @param s string to be searched for
	 * @return true if the table contains a word
	 */
	public boolean contains(String s) {
		s = trim(s);
		int hash = getHash(s);
		
		if(table[hash].getFirstNode() != null) {
			ObjectListNode node = new ObjectListNode();
			node = table[hash].getFirstNode();
			while(node != null) {
				if(node.getInfo().equals(s)) {
					return true;
				}
				node = node.getNext();
			}
		}
		return false;
	}
	
	/*
	 * below are helper methods
	 ***************************************************/

	/**
	 * accepts a string argument and generates its hash code
	 * @param s string to be scanned
	 * @return hash code of the string argument
	 */
	private int getHash(String s) {
		long hash = 7;
		
		for(int i = 0; i < s.length(); i++) {
			hash += (hash * 31) + (long)s.charAt(i);
		}
		hash *= ((long)s.charAt(0) % 37);

		return (int)(hash % TABLESIZE);
	}
	
	/**
	 * builds the hash table by reading from omit.txt and calling getHash
	 */
	private void build() {
		String word;
		int hashCode;
		
		while(omitIn.hasNextLine()) {
			word = omitIn.nextLine();
			hashCode = getHash(word);
			checkCollision(hashCode);
			table[hashCode].addLast(word);
		}
	}
	
	/**
	 * trim is used to get rid of pesky punctuation, capitalizations, and white space
	 * @return a string containing the word, minus those useless characters
	 */
	private String trim(String str) {
		String s = str.toLowerCase();		
		StringBuilder string = new StringBuilder("");
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) >= 97 || s.charAt(i) == 45) {
				string.append(s.charAt(i));
			}
		}
		return string.toString();
	}
	
	/**
	 * checks for a collision during the build process and takes appropriate
	 * actions
	 * @param c hash code for the key
	 * @return true if there was a collision
	 */
	private boolean checkCollision(int c) {
		if(!table[c].isEmpty()) {
			collisions++;
			return true;
		}
		return false;
	}
	
	/**
	 * computes the average size of each chain in the table
	 */
	private void getAverageSize() {
		int sum = 0;
		
		for(int i = 0; i < TABLESIZE; i++) {
			if(table[i].getFirstNode() != null) {
				sum += table[i].size();
			}
		}
		averageSize = (double)sum / (double)TABLESIZE;
	}
	
	/**
	 * gets the size of the biggest chain in the table
	 */
	private void getMaxSize() {
		int temp = 0;
		
		for(int i = 0; i < TABLESIZE; i++) {
			temp = temp < table[i].size() ? table[i].size() : temp;
		}
		
		maxSize = temp;
	}
	
	/**
	 * outputs a description of the hash function
	 */
	private void outputDescription() {
		out.output("A hash table has been created from omit.txt%n%n");
		out.output("The hash function used to create this table generates a hash code from a key by%n"
				+ "computing the sum of the results of adding each character to a hashing value. The%n"
				+ "hashing value is obtained by multiplying a given hashing variable (7 in this case)%n"
				+ "by 31. That computed sum is then multiplied by the integer representation of the%n"
				+ "first character in the string by modulo 37 to reduce collisions, and the function then returns%n"
				+ "the modulo of that value by the total size of the hash table itself.%n%n");
	}

	
	/**
	 * outputs the total number of collisions
	 */
	private void outputCollisions() {
		out.output("Total number of collisions: %d%n", collisions);
	}
	
	/**
	 * outputs the average size of the chains
	 */
	private void outputAverageSize() {
		out.output("Average chain size: %.2f%n", averageSize);
	}
	
	/**
	 * outputs the size of the longest chain in the table
	 */
	private void outputMaxSize() {
		out.output("Maximum chain size: %d%n%n", maxSize);
	}
	
	/**
	 * outputs a visual display of the table
	 */
	private void displayTable() {
		out.output("A visual representation of the hash table is shown below: %n%n");
		
		for(int i = 0; i < TABLESIZE; i++) {
			if(table[i].getFirstNode() == null) {
				out.output(i + " [   ]%n");
			}
			else {
				ObjectListNode node = table[i].getFirstNode();
				out.output(i + " [ " + node.getInfo() + " ] ");
				node = node.getNext();
				while(node != null) {
						out.output("- [ " + node.getInfo() + " ]");
						node = node.getNext();
				}
				out.output("%n");
			}
		}
		out.output("%n");
	}
}
