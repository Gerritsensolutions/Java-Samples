/**
 * Project Title: Stacks Lab
 * 
 * Project Description: Performs infix to postfix using a stack of objects
 * 
 * Version or Date: 3-5-2017
 * 
 * How to Start the Project: Run this driver
 * 
 * Author: Tyler Gerritsen
 * 
 * Palomar ID: 010247897
 * 
 * User Instructions: enter a number from the menu selections to convert 
 * different number systems.
 */
import java.util.Scanner;
import java.io.*;

public class Driver {
	public static void main(String[] args) throws IOException {
		SuperOutput out = new SuperOutput("csis.txt");
		ObjectStack stack = new ObjectStack();
		Scanner in = new Scanner(new File("infix.txt"));
		
		String line;
		String delims = " ";
		String tokens[];
		
		while(in.hasNext()) {
			line = in.nextLine();
			out.output("Infix expression: " + line + "%n");
			tokens = line.split(delims);
			
			InfixToPostfix infix = new InfixToPostfix(out, stack, tokens);
			infix.toPostfix();
			EvalPostfix eval = new EvalPostfix(out, stack, infix.getPostfix());
			eval.evaluate();
		}
		
		in.close();
		out.close();
	}

}
