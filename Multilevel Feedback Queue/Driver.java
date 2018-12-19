import java.io.IOException;

/**
 * Driver.java
 * @author Tyler Gerritsen
 * @version 4-7-2017
 * 
 * Driver class for the MFQ. This is essentially your own driver, professor,
 * with the exception that I switched out the PrintWriter object you used
 * with the SuperOutput object of your own design, for convenience purposes.
 */
public class Driver {
	public static void main(String[] args) throws IOException {
		SuperOutput out = new SuperOutput("csis.txt");
		MFQ mfq = new MFQ(out);
		mfq.getJobs();
		mfq.outputHeader();
		mfq.runSimulation();
		mfq.outStats();
		out.close();
	}

}
