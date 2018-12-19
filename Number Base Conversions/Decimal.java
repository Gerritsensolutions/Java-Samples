/**
 * Decimal.java
 * @author Tyler Gerritsen
 * @version 2-13-2017
 */
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Decimal {
	private final int MAX = 39;
	private PrintWriter pw;
	private Scanner in = new Scanner(System.in);
	private int decimal;
	private char[] binary = new char[MAX];
	private char[] hexadecimal = new char[8];

	/**
	 * default constructor
	 */
	public Decimal() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Single-argument constructor which accepts a PrintWriter object to be used
	 * for text file output
	 * @param pw PrintWriter instance to be used
	 */
	public Decimal(PrintWriter pw) {
		this.pw = pw;
	}

	/**
	 * converts decimal to binary
	 */
	public void decToBin() {
		inputDec();
		toBin();
		outBin();
	}
	
	/**
	 * sets decimal class member variable to an input read with Scanner
	 */
	private void inputDec() {
		System.out.println("Enter a decimal number.");
		decimal = in.nextInt();
		System.out.println();
		
		pw.println("Enter a decimal number.");
		pw.println(decimal);
		pw.println();
		return;
	}
	
	/**
	 * converts decimal to binary, formatting along the way.
	 */
	private void toBin() {
		/** these are a few variables to both aid readability and keep track of loop operations */
		int upper = MAX - 1; 
		int count = 0;
		int nibbleSize = 4;
		int nextNibble = 0;
		int remainder;
		
		/** this loop does a few things: performs arithmetic involved with converting decimal to binary,
		 *  puts those resulting values in the proper place in the binary array as they are calculated,
		 *  and adds spaces to separate nibbles.*/
		for (int i = 0; i < upper; i++) {
			if(decimal > 0) {
				nextNibble++;
				remainder = decimal % 2;
				decimal /= 2;
				binary[upper - count] = (char)(remainder + 48);
				count++;
			}
			else if (count <= upper) {
				nextNibble++;
				binary[upper - count] = '0';
				count++;
			}
			if (nextNibble == nibbleSize && count < upper) {
				  binary[upper - count] = ' ';
				  nextNibble = 0;
				  count++;
			}
		}
	}
	
	/**
	 * displays the binary value
	 */
	private void outBin() {
		/** used new String(binary) here; calling println on a char array results in the address being returned.*/
		System.out.println("Result: " + new String(binary));
		System.out.println();
		
		pw.println("Result: " + new String(binary));
		pw.println();
		return;
	}
	
	/**
	 * converts decimal to hexadecimal
	 */
	public void decToHex() {
		inputDec();
		toHex();
		outHex();
	}
	
	/**
	 * Converts decimal to hexadecimal, stores value in hexadecimal class member variable
	 */
	private void toHex() {
		for (int i = 7; i >= 0; i--) {
			int remainder = decimal % 16;
			decimal /= 16;
		
			switch (remainder) {
			case 10: hexadecimal[i] = 'A'; break;
			case 11: hexadecimal[i] = 'B'; break;
			case 12: hexadecimal[i] = 'C'; break;
			case 13: hexadecimal[i] = 'D'; break;
			case 14: hexadecimal[i] = 'E'; break;
			case 15: hexadecimal[i] = 'F'; break;
			default: hexadecimal[i] = (char)(remainder + 48); break;
			}
		}
	}
	
	/**
	 * displays hexadecimal conversion
	 */
	private void outHex() {
		System.out.print("Result: 0x");
		for (int i = 0; i < 8; i++) {
			System.out.print(hexadecimal[i]);
		}
		System.out.println();
		System.out.println();
		
		pw.print("Result: 0x");
		for (int i = 0; i < 8; i++) {
			pw.print(hexadecimal[i]);
		}
		pw.println();
		pw.println();
	}
	
}
