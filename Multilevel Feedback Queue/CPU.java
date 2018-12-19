/**
 * CPU.java
 * @author Tyler Gerritsen
 * @version 4-7-2017
 *
 * simulates a CPU for the MFQ
 */
public class CPU {
	private Job job;
	private int cpuQuantumClock;
	private boolean busyFlag;
	
	/**
	 * default constructor
	 */
	public CPU() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * gets the current job in the cpu
	 * @return job object assigned to the cpu
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * sets the current job in the cpu
	 * @param job Job object to be set
	 */
	public void setJob(Job job) {
		this.job = job;
	}

	/**
	 * getter for the current quantum clock
	 * @return the remaining time left in a given quantum
	 */
	public int getCpuQuantumClock() {
		return cpuQuantumClock;
	}

	/**
	 * setter for the quantum
	 * @param cpuQuantumClock the value the quantum will be set to 
	 */
	public void setCpuQuantumClock(int cpuQuantumClock) {
		this.cpuQuantumClock = cpuQuantumClock;
	}

	/**
	 * checks if the cpu is busy
	 * @return true if the cpu is busy
	 */
	public boolean isBusy() {
		return busyFlag;
	}

	/**
	 * sets the busy flag in the cpu
	 * @param busyFlag boolean value of whether or not the cpu is busy
	 */
	public void setBusyFlag(boolean busyFlag) {
		this.busyFlag = busyFlag;
	}



	
}
