
public class Driver {
	/*
	 * Prints out a message to the user that jackpot data is being fetched.
	 * Prints out the data on the current biggest jackpot using getBigJackpot() method.
	 */
	public static void bigJackpotToString() {
		System.out.println("\nFetching jackpot data...");
		
		System.out.println("\nBiggest jackpot so far: " + SlotMachine.getBigJackpot());
	}
	
	public static void main(String[] args) {
		
		bigJackpotToString(); // Output the current amount of the biggest jackpot won on any slot machine.
		
		// Create two slot machines.
		System.out.println("\nCreating two slot machines...");
		
		SlotMachine slotmachine0 = new SlotMachine();
		SlotMachine slotmachine1 = new SlotMachine();
		
		// Output the current state of both slot machines...
		System.out.println("\nGetting statistics on both slot machines...");
		
		System.out.println("\n" + slotmachine0.toString());
		System.out.println("\n" + slotmachine1.toString());
		
		bigJackpotToString(); // ...and the current amount of the biggest jackpot.
		
		slotmachine0.play(); // Play the first slot machine until the user walks away.
		
		System.out.println("\nGetting statistics on both slot machines...");
		
		System.out.println("\n" + slotmachine0.toString()); // Output the current state of both slot machines...
		System.out.println("\n" + slotmachine1.toString());
		
		bigJackpotToString(); // ...and the current amount of the biggest jackpot.
		
		slotmachine1.play(); // Play the second slot machine until the user walks away.
		
		System.out.println("\nGetting statistics on both slot machines...");
		
		System.out.println("\n" + slotmachine0.toString()); // Output the current state of both slot machines...
		System.out.println("\n" + slotmachine1.toString());
		
		bigJackpotToString(); // ...and the current amount of the biggest jackpot.
	}
}
