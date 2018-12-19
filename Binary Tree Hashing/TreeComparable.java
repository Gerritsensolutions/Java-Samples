/**
 * TreeComparable.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 * 
 * used for node comparisons in binary trees
 */
public interface TreeComparable {
	public int compareTo(Object o);
	public void operate(Object o);
	public void visit();
}
