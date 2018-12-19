/**
 * Payroll.java
 * @author Tyler Gerritsen
 * @version 4/28/2017
 * 
 * payroll system for all the employees
 */

import java.io.*;
import java.util.Scanner;

public class Payroll {
	private	SuperOutput out;								// input/output data members
	private Scanner payfileIn;								//
	private Scanner hirefileIn;								//
	private Scanner firefileIn;								//
	
	private String input;									// string variables for scanning payfile.txt
	private String delims;									//
	private String tokens[];								//
	
	private ObjectListInterface list = new ObjectList();	// list of objects
	
	private double standardHourlyRaise;						// standard raise values for hourly and weekly
	private double standardWeeklyRaise;						// employees
	
	
	/**
	 * default constructor
	 */
	public Payroll() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * one-argument constructor for SuperOutput
	 * @param out SuperOutput object for easy output
	 * @throws FileNotFoundException if Scanner objects cannot find specified files
	 */
	public Payroll(SuperOutput out) throws FileNotFoundException {
		this.out = out;
		payfileIn = new Scanner(new File("payfile.txt"));
		hirefileIn = new Scanner(new File("hirefile.txt"));
		firefileIn = new Scanner(new File("firefile.txt"));
		
		standardHourlyRaise = 0.75;
		standardWeeklyRaise = 50;
		
		out.output("%50s%n", "COMPANY PAYROLL");
		for(int i = 0; i <= 86; i++) {
			out.output("=");
		}
		out.output("%n%n");
	}
	
	/**
	 * outputs a fancy header for our database
	 */
	public void outputHeader() {
		String format = "%-10s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s %n";
		out.output(format, "First Name", "Last Name", "Gender", "Tenure", "Rate", "Salary");
		out.output(format, "----------", "---------", "------", "------", "----", "------");
	}
	
	/**
	 * loads all employee information into a linked list.
	 */
	public void loadEmployees() {
		while(payfileIn.hasNextLine()) {
			input = payfileIn.nextLine();
			delims = "[ ]+";
			tokens = input.split(delims);
			
			list.addLast(new Employee(out, 
									  tokens[0], 
									  tokens[1], 
									  tokens[2].charAt(0), 
									  Integer.parseInt(tokens[3]),
									  tokens[4].charAt(0), 
									  Double.parseDouble(tokens[5])));		
		}
	}

	/**
	 * outputs the information of all employees in the list
	 */
	public void outputContents() {
		ObjectListNode p = list.getFirstNode();
		Employee e;
		
		while(p != null) {
			e = (Employee)p.getInfo();
			e.print();
			p = p.getNext();
		}
		out.output("%n");
	}
	
	/**
	 * prints the total number of people employed by the company
	 */
	public void outputHeadcount() {
		ObjectListNode p = list.getFirstNode();
		int headcount = 0;
		
		while(p != null) {
			headcount++;
			p = p.getNext();
		}
		
		out.output("Total number of employees: %d%n", headcount);
		out.output("%n");
	}
	
	/**
	 * prints the full names of all the women on the payroll
	 */
	public void outputGenderDiversity() {
		out.output("Women employed: %n");
		String format = "\t\t%-10s \t %-10s %n";
		out.output(format, "First Name", "Last Name");
		out.output(format, "----------", "---------");
		ObjectListNode p = list.getFirstNode();
		Employee e;
		
		while(p != null) {
			e = (Employee)p.getInfo();
			if(e.isFemale()) {
				out.output("\t\t");
				e.printFullName();
			}
			p = p.getNext();
		}
		out.output("%n");
	}
	
	/**
	 * prints the names and salaries of all loyal employees
	 */
	public void outputLoyalEmployees() {
		out.output("Loyal employees: %n");
		String format = "\t\t%-10s \t %-10s \t %-10s %n";
		out.output(format, "First Name", "Last Name", "Salary");
		out.output(format, "----------", "---------", "------");
		
		ObjectListNode p = list.getFirstNode();
		Employee e;
		
		while(p != null) {
			e = (Employee)p.getInfo();
			if(e.isLoyal()) {
				out.output("\t\t");
				e.printNameAndSalary();
			}
			p = p.getNext();
		}
		out.output("%n");
	}
	
	/**
	 * gives raises to eligible employees in the linked list
	 */
	public void giveRaises() {
		out.output("New salaries: %n");
		String format = "\t\t%-10s \t %-10s \t %-10s %n";
		out.output(format, "First Name", "Last Name", "Salary");
		out.output(format, "----------", "---------", "------");
		
		ObjectListNode p = list.getFirstNode();
		Employee e;	
		while(p != null) {
			e = (Employee)p.getInfo();
			if(e.deservesRaise()) {
				list.remove(e);
				e.raise(e.rateIsWeekly() ? standardWeeklyRaise : standardHourlyRaise);
				out.output("\t\t");
				e.printNameAndSalary();
				list.insert(e);
			}
			p = p.getNext();
		}
		out.output("%n");
	}
	
	/**
	 * sorts the employees in the list alphabetically according to last name and first name
	 * in that order
	 */
	public void sort() {
		out.output("Employees sorted alphabetically: %n");
		String format = "\t\t%-10s \t %-10s %n";
		out.output(format, "First Name", "Last Name");
		out.output(format, "----------", "---------");
		
		ObjectList newlist = new ObjectList();
		while(list.getFirstNode() != null) {
			newlist.insert(list.removeFirst());
		}
		list = newlist;
		
		ObjectListNode p = list.getFirstNode();
		Employee e;
		while(p != null) {
			e = (Employee)p.getInfo();
			out.output("\t\t");
			e.printFullName();
			p = p.getNext();
		}
		out.output("%n");
	}
	
	/**
	 * hires all employees in hirefile.txt and displays the updated employee roster
	 */
	public void hire() {
		while(hirefileIn.hasNextLine()) {
			input = hirefileIn.nextLine();
			delims = "[ ]+";
			tokens = input.split(delims);
			
			list.insert(new Employee(out, 
									 tokens[0], 
									 tokens[1], 
									 tokens[2].charAt(0), 
									 Integer.parseInt(tokens[3]),
									 tokens[4].charAt(0), 
									 Double.parseDouble(tokens[5])));		
		}
		out.output("Hiring complete. Updated employee roster: %n");
		outputHeader();
		outputContents();
	}
	
	/**
	 * fires all employees in firefile.txt and displays the updated employee roster
	 */
	public void fire() {
		while(firefileIn.hasNextLine()) {
			input = firefileIn.nextLine();
			delims = "[ ]+";
			tokens = input.split(delims);
			
			tokens[1] = tokens[1].concat(tokens[0]);
			ObjectListNode p = list.getFirstNode();
			Employee e;
			while(p != null) {
				e = (Employee)p.getInfo();
				p = p.getNext();
				if(tokens[1].equals(e.nameConcat())) {
					list.remove(e);
					p = null;
				}
			}
		}
		out.output("Firing complete. Updated employee roster: %n");
		outputHeader();
		outputContents();
	}
}
