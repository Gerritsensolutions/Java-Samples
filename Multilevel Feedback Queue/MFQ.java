/**
 * MFQ.java
 * @author Tyler Gerritsen
 * @version 4-7-2017
 * 
 * simulates a multi-level feedback queue
 */

import java.io.*;
import java.util.Scanner;

public class MFQ {
	private SuperOutput out;			// input/output objects
	private Scanner in;					//
	
	private String input;				// string variables for scanning mfq.txt
	private String delims;				//
	private String tokens[];			//
	
	private double totalJobNumber;		// this and the following are all statistical variables
	private double totalJobTime;		// used for the final result of the MFQ displayed by
	private double totalIdleTime;		// the outStats method
										//
	private double averageResponse;		//
	private double averageTurnaround;	//
	private double averageWait;			//
	private double averageThroughput;	//
	
	/*
	 * the following are all intended for use during the MFQ simulation 
	 */
	
	ObjectQueueInterface inputQueue = new ObjectQueue();	// inputQueue which holds all jobs
															
	CPU cpu = new CPU();									// CPU and Clock objects for job processing 
	Clock clock = new Clock();
	
	ObjectQueueInterface firstQueue = new ObjectQueue();	// four Queue objects for the MFQ
	ObjectQueueInterface secondQueue = new ObjectQueue();
	ObjectQueueInterface thirdQueue = new ObjectQueue();
	ObjectQueueInterface fourthQueue = new ObjectQueue();
	
	/**
	 * default constructor
	 */
	public MFQ() {
		
	}
	
	/**
	 * one-argument constructor
	 * @param s SuperOutput object for output purposes
	 * @throws IOException for superoutput object
	 */
	public MFQ(SuperOutput s) throws IOException {
		out = s;
		in = new Scanner(new File("mfq.txt"));
		
		totalJobNumber = 0;
		totalJobTime = 0;
		totalIdleTime = 0;
		
		averageResponse = 0;
		averageTurnaround = 0;
		averageWait = 0;
		averageThroughput = 0;
	}
	
	/**
	 * gets all jobs for the MFQ and loads them into the inputQueue object
	 */
	public void getJobs() {
		int arrivalTime;
		int pid;
		int cpuTime;
		
		while(in.hasNextLine()) {
			input = in.nextLine();
			delims = "[ ]+";
			tokens = input.split(delims);
			
			arrivalTime = Integer.parseInt(tokens[0]);
			pid = Integer.parseInt(tokens[1]);
			cpuTime = Integer.parseInt(tokens[2]);
			
			Job job = new Job(out, arrivalTime, pid, cpuTime);
			inputQueue.insert(job);
		}
	}
	
	/**
	 * outputs a header for further statistical outputs
	 */
	public void outputHeader() {
		String format = "%-10s \t %-10s \t %-10s \t %-10s \t %-10s \t %-10s %n";
		
		out.output(format, " ", " ",  " ", "CPU",  "Total", "Lowest");
		out.output(format, " ", "System",  " ", "Time",  "Time In", "Level");
		out.output(format, "Event", "Time",  "PID", "Needed",  "System", "Queue");
		out.output(format, "-----", "------", "---", "------", "-------", "------");
	}
	
	/**
	 * runs the MFQ simulation using several different classes and helper methods
	 */
	public void runSimulation() {
		while(clock.getClock() <= 155) {
			/* ********************************************************
			 * perform operations if cpu is already in use
			 **********************************************************/
			if(cpu.isBusy()) {											
				cpu.setCpuQuantumClock(cpu.getCpuQuantumClock()-1);
				cpu.getJob().setCpuTimeRemaining(cpu.getJob().getCpuTimeRemaining() - 1);
				
				if(cpu.getJob().getCpuTimeRemaining() == 0) {
					jobDone();
					notBusy();
				}
				else {
					if(cpu.getCpuQuantumClock() == 0) {			
						preempt();
					}
				}
				
			}
			
			/*
			 * tick the clock and submit jobs
			 */
			clock.tick();
			submitJob();					
			
			/* ********************************************************
			 * perform operations if cpu is not busy
			 **********************************************************/
			if(!cpu.isBusy()) {
				notBusy();
			}
		}
		calculateAverages();
		return;
	}	
	
	/* 
	 * The following are helper methods for the runSimulation method
	 */
	
	/**
	 * submits job into the MFQ if the arrival time is appropriate
	 */
	private void submitJob() {
		if(!inputQueue.isEmpty()) {									// nested "ifs" to check job arrival times 
			Job front = (Job)inputQueue.query();					// and preempt if needed
			if(front.getArrivalTime() == clock.getClock()) {		//
				front.arrival();									//
				firstQueue.insert(inputQueue.remove());				//
				if(cpu.isBusy()) {
					if(cpu.getJob().getCurrentQueue() != 1) {
						preempt();
					}
					else {
						averageResponse++;
					}
				}													
			}														//
		}															//
	}
	
	/**
	 * loads a new job into the cpu. adjusts quantum clock, busy flag.
	 */
	private void notBusy() {
		int level = 1;
		if(firstQueue.isEmpty()) {
			level++;
			if(secondQueue.isEmpty()) {
				level++;
				if(thirdQueue.isEmpty()) {
					level++;
					if(fourthQueue.isEmpty()) {
						level++;
						totalIdleTime++; // increments idle time for proper results
					}
				}
			}
		}
		
		switch(level) {
		case 1: cpu.setJob((Job)firstQueue.remove());
				cpu.getJob().setCurrentQueue(level);
				cpu.setCpuQuantumClock((int)Math.pow(2, level));
				cpu.setBusyFlag(true);
				averageWait += clock.getClock() - cpu.getJob().getArrivalTime();
				averageResponse +=  clock.getClock() - cpu.getJob().getArrivalTime(); // adding to average response time
				break;
		case 2: cpu.setJob((Job)secondQueue.remove());
				cpu.getJob().setCurrentQueue(level);
				cpu.setCpuQuantumClock((int)Math.pow(2, level));
				cpu.setBusyFlag(true);
				averageWait += cpu.getJob().getLastQueueEntryTime() - cpu.getJob().getArrivalTime();
				break;
		case 3: cpu.setJob((Job)thirdQueue.remove());
				cpu.getJob().setCurrentQueue(level);
				cpu.setCpuQuantumClock((int)Math.pow(2, level));
				cpu.setBusyFlag(true);
				averageWait += cpu.getJob().getLastQueueEntryTime() - cpu.getJob().getArrivalTime();
				break;
		case 4: cpu.setJob((Job)fourthQueue.remove());
				cpu.getJob().setCurrentQueue(level);
				cpu.setCpuQuantumClock((int)Math.pow(2, level));
				cpu.setBusyFlag(true);
				averageWait += cpu.getJob().getLastQueueEntryTime() - cpu.getJob().getArrivalTime();
				break;
		}
	}
	
	/**
	 * cleans up when a job is finished (still need code to add totals)
	 */
	private void jobDone() {
		totalJobNumber++;														// recording some totals			
		//if(cpu.getJob().getCpuTimeRequired() == 1) {
		//	totalJobTime++;
		//}
		totalJobTime += (clock.getClock() + 1) - cpu.getJob().getArrivalTime();
		averageTurnaround += totalJobTime;
		
		cpu.getJob().departure(clock.getClock() + 1, (clock.getClock() + 1) - cpu.getJob().getArrivalTime()); // output and prep for next job
		cpu.setBusyFlag(false);
	}
	
	/**
	 * checks to see if there are other jobs waiting to be done. 
	 * used for preemption
	 */
	private boolean hasMoreJobs() {
		boolean jobs = false;
		if(!firstQueue.isEmpty()) {
			jobs = true;
		}
		if(!secondQueue.isEmpty()) {
			jobs = true;
		}
		if(!thirdQueue.isEmpty()) {
			jobs = true;
		}
		if(!fourthQueue.isEmpty()) {
			jobs = true;
		}
		return jobs;
	}
	
	/**
	 * cpu preemption. moves jobs to their next queue.
	 */
	private void preempt() {
		switch(cpu.getJob().getCurrentQueue()) {
		case 1:
			cpu.getJob().setCurrentQueue(2);
			cpu.getJob().setLastQueueEntryTime(clock.getClock());
			secondQueue.insert(cpu.getJob());
			cpu.setBusyFlag(false);
			notBusy();
			break;
		case 2:
			cpu.getJob().setCurrentQueue(3);
			cpu.getJob().setLastQueueEntryTime(clock.getClock());
			thirdQueue.insert(cpu.getJob());
			cpu.setBusyFlag(false);
			notBusy();
			break;
		case 3:
			cpu.getJob().setCurrentQueue(4);
			cpu.getJob().setLastQueueEntryTime(clock.getClock());
			fourthQueue.insert(cpu.getJob());
			cpu.setBusyFlag(false);
			notBusy();
			break;
		case 4:
			cpu.getJob().setLastQueueEntryTime(clock.getClock());
			fourthQueue.insert(cpu.getJob());
			cpu.setBusyFlag(false);
			notBusy();
			break;
		}
	}
	
	/**
	 * performs some division on our average variables for final results. The dividends have
	 * already been determined during the MFQ simulation
	 */ 
	private void calculateAverages() {
		averageResponse /= totalJobNumber;
		averageTurnaround /= totalJobNumber;
		averageWait /= totalJobNumber;
		averageThroughput = (totalJobNumber / totalJobTime);
	}
	
	/*
	 * End helper methods
	 */
	
	/**
	 * outputs final statistical analyses after the MFQ simulation has completed
	 */
	public void outStats() {
		String format = "\t%s %.2f %n";
		
		out.output("%nRESULTS: %n");		
		out.output(format, "Total number of jobs:", totalJobNumber);
		out.output(format, "Total time of all jobs:", totalJobTime);
		out.output(format, "Total CPU idle time:", totalIdleTime);
		out.output("%n");
		out.output(format, "Average response time:", averageResponse);
		out.output(format, "Average turnaround:", averageTurnaround);
		out.output(format, "Average wait time:", averageWait);
		out.output(format, "Average throughput:", averageThroughput);
	}
}
