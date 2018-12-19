/**
 * SuperOutput.java
 * @author Tyler Gerritsen
 * @version Unknown
 * 
 * A simple class for outputting a string to both a file and the 
 * terminal window 
 */ 

import java.io.*; 
public class SuperOutput {
	PrintWriter pw;  
	
	/**      
	 * Constructor for SuperOutput objects      
	 * @param fileName  the name of the file to print to      
	 */ 
	public SuperOutput(String fileName) {
		try {
			pw = new PrintWriter(new FileWriter(fileName));
			}
		catch (IOException e) {
			System.out.printf("File %s could not be opened"
					+ " for output.\n", fileName);
			pw = null;
			}
		}
	
	/**      
 	* Forwards a string of format specifiers and var args       
 	* to two printf statements      
 	* @param string   the string to print
 	* @param args string arguments
 	*/
	public void output(String string, Object... args) {
		if (pw == null)
			return;
		System.out.printf(string, args);
		pw.printf(string, args);
		}
	
	/**      
	 * Forwards a string of format specifiers and var args      
	 * to just the file. This is used for echoing items       
	 * the user may have input.       
	 * @param string   the string to print
	 * @param args string arguments      
	 */
	public void outputFile(String string, Object... args) {
		if (pw == null)
			return; 
		pw.printf(string, args);
		}
	
	/**
	 * Closes the SuperOutput object 
     */ 
	public void close () {
		if (pw != null) {
			pw.close();
			}
		}
} 
