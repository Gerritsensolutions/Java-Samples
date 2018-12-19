import java.io.IOException;

/**
 * Driver.java
 * @author Tyler Gerritsen
 * @version 4-7-2017
 * 
 * Driver class for the MFQ.
 */
public class Driver {
	public static void main(String[] args) throws IOException {
		SuperOutput out = new SuperOutput("out.txt");
		MFQ mfq = new MFQ(out);
		mfq.getJobs();
		mfq.outputHeader();
		mfq.runSimulation();
		mfq.outStats();
		out.close();
	}

}
