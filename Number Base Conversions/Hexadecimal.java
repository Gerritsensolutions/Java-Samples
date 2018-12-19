/**
 * Hexadecimal.java
 * @author Tyler Gerritsen
 * @version 2-13-2017
 * 
 * this class takes a hexadecimal value and converts it to either a decimal integer
 * or a binary string
 */
import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.Math.*;

public class Hexadecimal {
	private PrintWriter pw;
	private Scanner in = new Scanner(System.in);
	private String hex;
	private long dec;
	private StringBuilder bin = new StringBuilder(32);
	
	/**
	 * default constructor
	 */
	public Hexadecimal() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Single-argument constructor which accepts a PrintWriter object to be used
	 * for text file output
	 * @param pw PrintWriter instance to be used
	 */
	public Hexadecimal(PrintWriter pw) {
		this.pw = pw;
	}
	
	/**
	 * converts hexadecimal to decimal
	 */
	public void hexToDec() {
		inputHex();
		toDec();
		outDec();
	}
	
	/**
	 * inputs the hexadecimal number
	 */
	private void inputHex() {
		System.out.println("Enter a hexadecimal number.");
		hex = new String(in.nextLine());
		System.out.println();
		
		pw.println("Enter a hexadecimal number.");
		pw.println(hex);
		pw.println();
		return;
	}
	
	/**
	 * converts the hex variable to dec (BUG!)
	 */
	private void toDec() {
		long subtotal = 0;
		for(int i = 7; i >= 0; i--) {
			switch (hex.charAt(i)) {
			case '1': 
				subtotal += 1 * pow(16, (8 - (i + 1)));
				break;
			case '2': 
				subtotal += 2 * pow(16, (8 - (i + 1)));
				break;
			case '3': 
				subtotal += 3 * pow(16, (8 - (i + 1)));
				break;
			case '4': 
				subtotal += 4 * pow(16, (8 - (i + 1)));
				break;
			case '5': 
				subtotal += 5 * pow(16, (8 - (i + 1)));
				break;
			case '6': 
				subtotal += 6 * pow(16, (8 - (i + 1)));
				break;
			case '7': 
				subtotal += 7 * pow(16, (8 - (i + 1)));
				break;
			case '8': 
				subtotal += 8 * pow(16, (8 - (i + 1)));
				break;
			case '9': 
				subtotal += 9 * pow(16, (8 - (i + 1)));
				break;
			case 'A': 
				subtotal += 10 * pow(16, (8 - (i + 1)));
				break;
			case 'B': 
				subtotal += 11 * pow(16, (8 - (i + 1)));
				break;
			case 'C': 
				subtotal += 12 * pow(16, (8 - (i + 1)));
				break;
			case 'D': 
				subtotal += 13 * pow(16, (8 - (i + 1)));
				break;
			case 'E': 
				subtotal += 14 * pow(16, (8 - (i + 1)));
				break;
			case 'F': 
				subtotal += 15 * pow(16, (8 - (i + 1)));
				break;				
			}
		}
		dec = subtotal;
	}
	
	/**
	 * display decimal result
	 */
	private void outDec() {
		System.out.println("Result: " + dec);
		System.out.println();
		
		pw.println("Result: " + dec);
		pw.println();
	}
	
	/**
	 * converts hexadecimal to binary
	 */
	public void hexToBin()  {
		inputHex();
		toBin();
		outBin();
	}
	
	/**
	 * performs conversion operations (hex-to-bin)
	 */
	private void toBin()  {
		for (int i = 0; i < 8; i++) {
			switch (hex.charAt(i)) {
			case '0': 
				bin.append("0000 ");
				break;
			case '1': 
				bin.append("0001 ");
				break;
			case '2': 
				bin.append("0010 ");
				break;
			case '3': 
				bin.append("0011 ");
				break;
			case '4': 
				bin.append("0100 ");
				break;
			case '5': 
				bin.append("0101 ");
				break;
			case '6': 
				bin.append("0110 ");
				break;
			case '7': 
				bin.append("0111 ");
				break;
			case '8': 
				bin.append("1000 ");
				break;
			case '9': 
				bin.append("1001 ");
				break;
			case 'A': 
				bin.append("1010 ");
				break;
			case 'B': 
				bin.append("1011 ");
				break;
			case 'C': 
				bin.append("1100 ");
				break;
			case 'D': 
				bin.append("1101 ");
				break;
			case 'E': 
				bin.append("1110 ");
				break;
			case 'F': 
				bin.append("1111 ");
				break;																																													
			}
		}
	}
	
	/**
	 * displays the binary value
	 */
	private void outBin() {
		System.out.println("Result: " + bin);
		System.out.println();
		
		pw.println("Result: " + bin);
		pw.println();
		
		bin = new StringBuilder(32);
	}
}
