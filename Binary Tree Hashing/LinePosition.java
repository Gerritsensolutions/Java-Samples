/**
 * LinePosition.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 * 
 * position of a word
 */
public class LinePosition {
	int line;
	int position;

    /**
     * default constructor
     */
	public LinePosition() {
		
	}

    /**
     * two-argument constructor
     * @param line the line the word is located at
     * @param position the position the word is located at
     */
	public LinePosition(int line, int position) {
		this.line = line;
		this.position = position;
	}
	
	/**
	 * gets a string representation for the LinePosition object
	 * @return a string which represents the object
	 */
	public String toString() {
		return line + "-" + position;
	}
}
