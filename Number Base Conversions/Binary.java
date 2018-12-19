/**
 * Binary.java
 * @author Tyler Gerritsen
 * @version 2-13-2017
 * Accepts a binary value and converts it to decimal and hexadecimal
 */
import java.io.PrintWriter;
import java.util.Scanner;

public class Binary {
	private PrintWriter pw;
	private Scanner in = new Scanner(System.in);
	private int doubleWord = 32;
	//private int nibble = 4;
	private String bin;
	private long dec;
	private StringBuilder hex = new StringBuilder("00000000"); 
	
	/**
	 * default constructor
	 */
	public Binary() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Single-argument constructor which accepts a PrintWriter object to be used
	 * for text file output
	 * @param pw PrintWriter instance to be used
	 */
	public Binary(PrintWriter pw) {
		this.pw = pw;
	}
	
	/**
	 * converts binary to decimal
	 */
	public void binToDec() {
		inputBin();
		toDec();
		outDec();
	}
	
	/**
	 * sets the binary value
	 */
	private void inputBin() {
		System.out.println("Enter a binary number.");
		bin = in.nextLine();
		System.out.println();
		
		pw.println("Enter a binary number.");
		pw.println(bin);
		pw.println();
		return;
	}
	
	/**
	 * converts to decimal 
	 */
	private void toDec() {
		long subtotal = 0;
		int base = 2;
		int flag;
		for (int i = 0; i < doubleWord; i++) {
			flag = (int)(bin.charAt(i) - 48);
			subtotal = subtotal * base + flag;
		}
		dec = subtotal;
	}
	
	/**
	 * outputs decimal conversion of the original hexadecimal value
	 */
	private void outDec() {
		System.out.println("Result: " + dec);
		System.out.println();
		
		pw.println("Result: " + dec);
		pw.println();
	}
	
	/**
	 * converts binary to hexadecimal
	 */
	public void binToHex() {
		inputBin();
		toHex();
		outHex();
	}
	
	/**
	 * converts a binary String to a hexadecimal StringBuilder
	 */
	private void toHex() {
		int hexIndex = 0;
		int nibble = 4;
		for (int i = 0; i < doubleWord; i += nibble) {
			if (bin.substring(i, i + nibble).equals("0000")) {
				hex.setCharAt(hexIndex, '0');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("0001")) {
				hex.setCharAt(hexIndex, '1');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("0010")) {
				hex.setCharAt(hexIndex, '2');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("0011")) {
				hex.setCharAt(hexIndex, '3');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("0100")) {
				hex.setCharAt(hexIndex, '4');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("0101")) {
				hex.setCharAt(hexIndex, '5');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("0110")) {
				hex.setCharAt(hexIndex, '6');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("0111")) {
				hex.setCharAt(hexIndex, '7');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("1000")) {
				hex.setCharAt(hexIndex, '8');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("1001")) {
				hex.setCharAt(hexIndex, '9');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("1010")) {
				hex.setCharAt(hexIndex, 'A');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("1011")) {
				hex.setCharAt(hexIndex, 'B');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("1100")) {
				hex.setCharAt(hexIndex, 'C');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("1101")) {
				hex.setCharAt(hexIndex, 'D');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("1110")) {
				hex.setCharAt(hexIndex, 'E');
				hexIndex++;
			}
			if (bin.substring(i, i + nibble).equals("1111")) {
				hex.setCharAt(hexIndex, 'F');
				hexIndex++;
			}
		}
	}
	
	/**
	 * displays the hexadecimal value
	 */
	private void outHex() {
		System.out.println("Result: " + hex);
		System.out.println();
		
		pw.println("Result: " + hex);
		pw.println();
	}
}

