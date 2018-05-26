package edu.bsu.emlacy;

public class Savant extends Thread {
	public int Num; //an identification number for each Thinker
	public Sticks leftStick; //an instance of the chopstick object specifically for the left
	public Sticks rightStick; //instance of the chopstick object for the right hand

	//general constructor for a Philosopher
	public Savant (int Num, Sticks leftStick, Sticks rightStick) {
		this.Num = Num;
		this.leftStick = leftStick;
		this.rightStick = rightStick;
	}
	
	public void nourish() {							//since these are philosophers, let's get fancy with terminology, for fun.
		if (!leftStick.stickLock & !rightStick.stickLock) {
			
			try {   								//honestly I have no idea why I needed the try catch here. 
				rightStick.retrieve();				//maybe so that way if the operation wouldn't work, it wouldn't crash?
			} catch (InterruptedException e) {		//all I know is Java said put it in... I'm gonna look it up
				e.printStackTrace();				//Yeup. It is because there is a possibility that function may not work. 
			}										//So it catches an exception to prevent failure.
			
			try {
				leftStick.retrieve();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			miscsubs.StartEating(this.Num);
			miscsubs.RandomDelay();
			rightStick.relinquish();
			leftStick.relinquish();
			miscsubs.DoneEating(this.Num);
			//miscsubs.RandomDelay();
		}
	}
	
	public void ponder() {
		miscsubs.RandomDelay();
	}
	
	public void run() {
		while (miscsubs.TotalEats<miscsubs.MAX_EATS) {//originally, it was gonna be != as the operand symbol but that would make it run forever
			ponder();
			nourish();
		}	
	}
	
}

