/**
 * Job.java
 * @author Tyler Gerritsen
 * @version 4-7-2017
 * 
 * simulates a job for the cpu
 */
public class Job {
	SuperOutput out;
	
	private int pid, 
				arrivalTime, 
				cpuTimeRequired, 
				cpuTimeRemaining, 
				currentQueue,
				lastQueueEntryTime;

	/**
	 * default constructor
	 */
	public Job() {
		
	}
	
	/**
	 * single-parameter constructor for output
	 * @param out SuperOutput object for easy output
	 */

	/**
	 * three-parameter constructor
	 * @param out SuperOutput object for easy output
	 * @param arrivalTime the time in which this job should arrive at the MFQ
	 * @param pid the process ID number for the job
	 * @param cpuTime the required time needed to complete this process
	 */
	public Job(SuperOutput out, int arrivalTime, int pid, int cpuTime) {
		this.arrivalTime = arrivalTime;
		this.pid = pid;
		this.cpuTimeRequired = cpuTime;
		this.cpuTimeRemaining = cpuTime;
		this.out = out;
	}
	
	/**
	 * prints arrival time information
	 */
	public void arrival() {
		String format = "%-10s \t %-10s \t %-10s \t %-10s %n";
		out.output(format, "Arrival", arrivalTime,  pid, cpuTimeRequired);
	}
	
	/**
	 * prints departure information
	 * @param i total time job was in the system
	 */
	public void departure(int time, int i) {
		String format = "%-10s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s %n";
		out.output(format, "Departure", time, pid, " ", i, currentQueue);
	}
	
	/**
	 * getter for the cpu time remaining
	 * @return remaining required time the process needs in the cpu to finish
	 */
	public int getCpuTimeRemaining() {
		return cpuTimeRemaining;
	}

	/**
	 * setter for the cpu time remaining
	 * @param cpuTimeRemaining value for the time remaining to be set to
	 */
	public void setCpuTimeRemaining(int cpuTimeRemaining) {
		this.cpuTimeRemaining = cpuTimeRemaining;
	}

	/**
	 * getter for the current queue
	 * @return current queue assigned to the job
	 */
	public int getCurrentQueue() {
		return currentQueue;
	}

	/**
	 * setter for the current queue
	 * @param currentQueue the queue this job will be set to
	 */
	public void setCurrentQueue(int currentQueue) {
		this.currentQueue = currentQueue;
	}

	/**
	 * gets the arrival time of this job
	 * @return the jobs arrival time
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * getter for the cpu time required
	 * @return the cpu time required
	 */
	public int getCpuTimeRequired() {
		return cpuTimeRequired;
	}

	/**
	 * getter for the jobs most recent queue entry time
	 * @return the value of the clock at the time of this jobs last queue entry
	 */
	public int getLastQueueEntryTime() {
		return lastQueueEntryTime;
	}

	/**
	 * getter for the jobs most recent queue entry time
	 * @param lastQueueEntryTime the value of the clock when this job is inserted into any queue
	 */
	public void setLastQueueEntryTime(int lastQueueEntryTime) {
		this.lastQueueEntryTime = lastQueueEntryTime;
	}
}
