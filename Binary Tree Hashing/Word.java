/**
 * Word.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 *
 * Represents a word in a text document
 */
public class Word implements TreeComparable {
	SuperOutput out;
	
	String word;
	int count;
	ObjectListInterface list;

    /**
     * default constructor
     */
	public Word() {
		count = 0;
		list = new ObjectList();
	}

    /**
     * two-argument constructor
     * @param out SuperOutput object for easy output
     * @param word the string to set this word object to
     */
	public Word(SuperOutput out, String word) {
		this.word = word;
	}
	
	/**
	 * two-argument constructor
	 * @param out SuperOutput Object for easy output
	 * @param word String which will be the word
	 * @param line the line in which this word appears
	 * @param position the position within the line for this
	 * word
	 */
	public Word(SuperOutput out, String word, int line, int position) {
		this.out = out; 
		this.word = new String(word);
		count = 1;
		list = new ObjectList();
		list.addLast(new LinePosition(line, position));
		
		this.word = trimWord();
	}
	
	/**
	 * prints information about the word
	 */
	public void print() {
		out.output("%-15s %-15s ", word, count);
		
		ObjectListNode node = list.getFirstNode();
		while(node != null) {
			out.output(node.getInfo().toString() + "/t");
		}
	}
	
	//public LinePosition getLinePosition() {
		
	//}
	
	/**
	 * overridden commpareTo method from the TreeComparable interface. compares
	 * two word objects together.
	 * @param o Object to be compared with this one
	 * @return integer representing the comparison results.
	 */
	@Override
	public int compareTo(Object o) {
		Word w = (Word)o;
		return word.compareTo(w.getWord());
	}
	
	/**
	 * overridden operate method from the TreeComparable interface.
	 * used when a duplicate node is inserted into the binary tree
	 * @param o Object to operate with
	 */
	@Override
	public void operate(Object o) {
		count++;
		Word w = (Word)o;
		list.addLast(w.getLinePosition());
	}

	/**
	 * overridden visit method from the TreeComparable interface.
	 * enumerates the word object and prints its contents
	 */
	@Override
	public void visit() {
		out.output("%-15s %-15d ", word, count);
		
		ObjectListNode node = new ObjectListNode(list.getFirstNode());
		node = (ObjectListNode)node.getInfo();
		for(int i = 0; i < count; i++) {
			LinePosition position = (LinePosition)node.getInfo();
			out.output(position.toString() + " ");
			node = node.getNext();
		}
		out.output("%n");
	}
	
	/*
	 * Helper methods
	 ****************************************/
	
	/**
	 * trimWord is used to get rid of pesky punctuation, capitalizations, and white space
	 * @return a string containing the word, minus 
	 */
	private String trimWord() {
		String s = word.toLowerCase();		
		StringBuilder string = new StringBuilder("");
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) >= 97 || s.charAt(i) == 45) {
				string.append(s.charAt(i));
			}
		}
		return string.toString();
	}
	
	/*
	 * Getters and setters
	 *****************************************************************/
	
	/**
	 * getter for the word member
	 * @return a string which is the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * getter for the first LinePosition member
	 * @return LinePosition object which is the first element in the ObjectList
	 */
	public LinePosition getLinePosition() {
		return (LinePosition)list.getFirst();
	}

}
