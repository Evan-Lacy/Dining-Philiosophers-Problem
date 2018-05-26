// Use java threads to simulate the Dining Philosophers Problem; Credits given to Prof. Riley
// Evan Lacy./
package edu.bsu.emlacy;

class PhilosophersTable /*implements Runnable*/{
	
	Savant savant0; //creates objects of the Savant class 
	Savant savant1; //to be used for individual threads
	Savant savant2;
	Savant savant3;
	Savant savant4;
	
	Sticks chopsticks[] = new Sticks[5]; //create arrays to store the objects 
	Savant theorist[] = new Savant[5]; //for Savant and Sticks
	
	public PhilosophersTable() { //constructor of the class
		for(int k = 0; k<5; k++) {
			Sticks sticks = new Sticks(); //initializing instances of
			chopsticks[k] = sticks; 	  //5 chopsticks for the table
		}
		savant0 = new Savant(0, chopsticks[1], chopsticks[0]);		//KINDA NEED THOSE ID NUMBERS TO NOT ALL BE THE SAME.
		savant1 = new Savant(1, chopsticks[2], chopsticks[1]);		//OTHERWISE, A CERTAIN ID NUMBER *COUGH* ZERO  *COUGH*
		savant2 = new Savant(2, chopsticks[3], chopsticks[2]);		//GETS ALL THE FOOD
		savant3 = new Savant(3, chopsticks[4], chopsticks[3]);
		savant4 = new Savant(4, chopsticks[0], chopsticks[4]);
		
		theorist[0] = savant0;		//need to populate the array with the savants who now know
		theorist[1] = savant1;		//which chopsticks they can use and what their numbers are
		theorist[2] = savant2;
		theorist[3] = savant3;
		theorist[4] = savant4;
	}
	
	public static void main(String args[]){
		System.out.println("Starting the Dining Philosophers Simulation\n");
		miscsubs.InitializeChecking();
		// Your code here...
		
		PhilosophersTable seating = new PhilosophersTable();
		seating.mealEnjoyment();
		
		// End of your code
		miscsubs.LogResults();

	}
	
	public void mealEnjoyment() {
		for (int i=0; i< miscsubs.NUMBER_PHILOSOPHERS; i++) {
			theorist[i].start();
		}
		for (int t = 0; t<miscsubs.NUMBER_PHILOSOPHERS; t++) {
			try {
				theorist[t].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//original attempt, did not accomplish the goal
/*while (miscsubs.TotalEats != miscsubs.MAX_EATS) {			//so, this blocked out section technically works, as each Philosopher gets 100 food
for (int f = 0; f< miscsubs.NUMBER_PHILOSOPHERS; f++) { //but it unfortunately doesn't use threading or synchronization 
	//for(int j=0; j<)									//This means that every eats 100 pieces of food exactly.
	miscsubs.InitializeChecking();						//This is accomplished by iterating through the list of Philosophers
	miscsubs.RandomDelay();								//and each one eating one at a time until the total food eaten matches max food units				
	miscsubs.StartEating(f);
	miscsubs.RandomDelay();
	miscsubs.DoneEating(f);
	miscsubs.RandomDelay();	
}
}*/