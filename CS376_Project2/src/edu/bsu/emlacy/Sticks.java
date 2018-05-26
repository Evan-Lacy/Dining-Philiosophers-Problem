package edu.bsu.emlacy;

public class Sticks {
	public boolean stickLock;
	
	public Sticks() {	
	}
	
	public synchronized void retrieve() throws InterruptedException{
		while(stickLock) {
			wait();
		}
		stickLock = true;
	}
	public synchronized void relinquish() {
		this.stickLock = false;
		notifyAll();
	}
}
