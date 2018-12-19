/**
 * Driver.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 * 
 * Driver.java is the driver for the binary hash tree.
 */

import java.io.FileNotFoundException;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		SuperOutput out = new SuperOutput("out.txt");
		Xref reference = new Xref(out);
		
		reference.outputHeader();
		reference.buildHashTable(out);
		reference.buildBinaryTree();
		reference.runQueries();
		
		out.close();
	}
}
