/**
 * Menu.java
 * @author Tyler Gerritsen
 * @version 2-13-2017
 * Simply displays a menu of options for the user to choose from
 */
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
	private PrintWriter pw;
	private Scanner in = new Scanner(System.in);
	
	/**
	 * default constructor
	 */
	public Menu() {
	
	}
	
	/**
	 * Single-argument constructor which accepts a PrintWriter object to be used
	 * for text file output
	 * @param pw PrintWriter instance to be used
	 */
	public Menu(PrintWriter pw) {
		this.pw = pw;
	}
	
	/** 
	 * displays a main menu of options and prompts the user for input
	 */
	public void display() {
		System.out.println("NUMBER BASE CONVERSION PROGRAM");
		System.out.println("------ ---- ---------- -------");
		System.out.println("Choose an option:");
		System.out.println();
		System.out.println("1. Convert decimal to binary.");
		System.out.println("2. Convert decimal to hexadecimal.");
		System.out.println("3. Convert binary to decimal.");
		System.out.println("4. Convert binary to hexadecimal.");
		System.out.println("5. Convert hexadecimal to decimal.");
		System.out.println("6. Convert hexadecimal to binary.");
		System.out.println();
		
		pw.println("NUMBER BASE CONVERSION PROGRAM");
		pw.println("------ ---- ---------- -------");
		pw.println("Choose an option:");
		pw.println();
		pw.println("1. Convert decimal to binary.");
		pw.println("2. Convert decimal to hexadecimal.");
		pw.println("3. Convert binary to decimal.");
		pw.println("4. Convert binary to hexadecimal.");
		pw.println("5. Convert hexadecimal to decimal.");
		pw.println("6. Convert hexadecimal to binary.");
		pw.println();
		return;
	}
	
	/**
	 * returns user selection input and echos user input to text file.
	 * @return integer input
	 */
	public int getSelection() {
		int selection;
		selection = in.nextInt();
		pw.println(selection);
		pw.println();
		return selection;
	}
}
