import java.io.FileNotFoundException;

/**
 * Driver.java
 * @author Tyler Gerritsen
 * @version 4/28/2017
 * 
 * driver class for the payroll
 */
public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		SuperOutput out = new SuperOutput("csis.txt");
		
		Payroll database = new Payroll(out);
		database.outputHeader();
		database.loadEmployees();
		database.outputContents();
		database.outputHeadcount();
		database.outputGenderDiversity();
		database.outputLoyalEmployees();
		database.giveRaises();
		database.sort();
		database.hire();
		database.fire();
		
		out.close();
	}

}
