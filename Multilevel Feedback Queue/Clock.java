/**
 * Clock.java
 * @author Tyler Gerritsen
 * @version 4-7-2017
 * 
 * simulates a CPU clock
 */
public class Clock {
	private int clock;
	
	/**
	 * default constructor
	 */
	public Clock() {
		clock = 0;
	}
	
	/**
	 * ticks the clock
	 */
	public void tick() {
		clock++;
	}
	
	/**
	 * getter for the clock
	 * @return value of the clock
	 */
	public int getClock() {
		return clock;
	}
}
