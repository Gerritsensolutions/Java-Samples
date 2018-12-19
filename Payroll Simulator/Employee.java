/**
 * Employee.java
 * @author Tyler Gerritsen
 * @version 4/28/2017
 * 
 * class representing an employee
 */
public class Employee implements Comparable<Employee> {
	private SuperOutput out;
	private String firstName,
				   lastName;
	private char gender, 
				 rate;
	private int tenure;
	private double salary;
	
	/**
	 * default constructor
	 */
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructor with arguments for all data members, including a SuperOutput object
	 * @param out SuperOutput object
	 * @param firstName first name of the employee
	 * @param lastName last name of the employee
	 * @param gender gender of the employee
	 * @param tenure the number of years this employee has been with the company
	 * @param rate rate of payment for the employee
	 * @param salary payment amount for the employee
	 */
	public Employee(SuperOutput out, String firstName, String lastName, char gender, int tenure, char rate, double salary) {
		this.out = out;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.tenure = tenure;
		this.rate = rate;
		this.salary = salary;
	}
	
	/**
	 * prints employee fields in tabular format
	 */
	public void print() {
		String format = "%-10s \t %-10s \t %-10s \t %-10s \t %-10s \t $%-10.2f %n";
		out.output(format, firstName, lastName, gender, tenure, rate, salary);
	}
	
	/**
	 * prints basic information of the employee; name and salary
	 */
	public void printNameAndSalary() {
		String format = "%-10s \t %-10s \t %-10.2f %n";
		out.output(format, firstName, lastName, salary);
	}
	
	/**
	 * outputs only the first and last name of the employee
	 */
	public void printFullName() {
		String format = "%-10s \t %-10s %n";
		out.output(format, firstName, lastName);
	}
	
	/**
	 * checks if this employee is female
	 * @return true if the employee is female
	 */
	public boolean isFemale() {	
		return gender == 'F' ? true : false;
	}
	
	/**
	 * comparison function to indicate if this employee's payrate is hourly or weekly
	 * @return true if the employee is weekly
	 */
	public boolean rateIsWeekly() {
		return rate == 'W' ? true : false;
	}
	
	/**
	 * checks if the employee's salary is low enough to qualify for a raise
	 * @return true if the employee deserves a raise
	 */
	public boolean deservesRaise() { // include an hourly and weekly standard raise in the payroll class
		switch(rate) {
		case 'H':
			return salary < 10 ? true : false;
		case 'W':
			return salary < 350 ? true : false;
		default:
			new Exception("Raise Runtime Error: invalid rate character").printStackTrace();
			System.exit(1);
			return false;
		}
	}
	
	/**
	 * gives the employee a raise
	 * @param a raise amount to be given
	 */
	public void raise(double a) {
		salary += a;
	}
	
	/**
	 * checks if this employee has been with this company long enough and is paid well enough to be considered
	 * loyal to the company
	 * @return true if the employee can be considered loyal to the company
	 */
	public boolean isLoyal() {
		int weeksWorked = 52;
		int desirableTenure = 5;
		double desirableWages = 35000;
		double yearlyWages;
		
		if(tenure >= desirableTenure) {
			yearlyWages = weeksWorked * salary;
			return yearlyWages > desirableWages ? true : false;
		}
		return false;
	}

	/**
	 * overridden compareTo method for comparisons between two employee objects
	 * @param e employee object to compare this object with
	 * @return a negative integer, zero, or a positive integer as this object is less than, 
	 * equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Employee e) {
		if(this.nameConcat().equals(e.nameConcat())) {
			return 0;
		}
		return this.nameConcat().compareTo(e.nameConcat()) > 0 ? 1 : -1;
	}
	
	/**
	 * puts the last name and first name together. used for comparisons
	 * @return a string containing the last name with the first name concatenated at the end
	 */
	public String nameConcat() {
		String str = lastName;
		str = str.concat(firstName);
		return lastName.concat(firstName);
	}
}
