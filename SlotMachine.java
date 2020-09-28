/*
 * Sam Platt
 * CS 257
 * Lab 4
 */

import java.util.Random;
import java.util.Scanner;

public class SlotMachine {
	private int currentBet;
	private static int bigJackpot;
	private int reel0;
	private int reel1;
	private int reel2;
	private final int MAX_BET = 4;
	private final int TRIPLE_ZERO_REWARD = 10;
	private final int THREE_OF_A_KIND_REWARD = 5;
	private final int TWO_OF_A_KIND_REWARD = 3;
	
	String s;
	
	private static Scanner scan = new Scanner(System.in); // see: LECTURE 5 slide "Aside: A Useful Class Variable"
	
	/*
	 * Constructor for a slot machine with initial bet of 0 coins and with all 3 reels set to 0.
	 */
	public SlotMachine() {
		currentBet = 0;
		reel0 = 0;
		reel1 = 0;
		reel2 = 0;
	}
	
	/*
	 * Constructor for a slot machine with initial bet of "int bet" coins and with all 3 reels set to 0.
	 */
	public SlotMachine(int bet) {
		currentBet = bet;
		reel0 = 0;
		reel1 = 0;
		reel2 = 0;
	}
	
	/*
	 * Returns a string containing the values on all three reels (int reel0, int reel1, int reel2) and the amount of the current bet (int currentBet).
	 */
	public String toString() {
		String s = "Reel Values: " + reel0 + " " + reel1 + " " + reel2 + "\nCurrent Bet: " + currentBet;
		
		return s;
	}
	
	/*
	 * Returns the amount (in coins) of the biggest jackpot won on any slot machine.
	 */
	public static int getBigJackpot() {
		return bigJackpot;
	}
	
	/*
	 * Generate a random number between upperBound and lowerBound for one of the three reels on the slot machine.
	 */
	private int getReelRandom() {
		int upperBound = 5;
		int lowerBound = 0; // since lower bound is 0, you don't really need it. You can just pass in the upper bound.
		
		Random randNum = new Random();
		
		int rnd = randNum.nextInt(upperBound);
		return rnd;
	}
	
	/*
	 * Sets the values of the three reels by generating three psuedo-random numbers.
	 */
	private void spinReels() {
		reel0 = getReelRandom();
		reel1 = getReelRandom();
		reel2 = getReelRandom();
	}
	
	/*
	 * Set the size of the current bet to 0 coins.
	 */
	public void reset() {
		currentBet = 0;
	}
	
	/*
	 * Increase the size of the current bet by 1 coin.
	 * The maximum allowable bet is 4 coins.
	 */
	public void bet() {
		if(currentBet < MAX_BET) { // Check that the bet isn't already 4 coins.
			currentBet++; // If currentBet is less than 4 coins, increase currentBet by 1.
		}
	}
	
	/*
	 * "
	 * Invoke the spinReels() support method to set the values on the reels.
	 * Output the current state of the machine.
	 * Then determine how much the player has won, if anything.
	 * "
	 */
	public void pullHandle() {
		spinReels();
		
		System.out.println("\n" + toString()); // Output the current state of the machine.
		
		// Determine how much the player has won:
		
		int win = 0; // win starts with a value of 0. This will only be changed if one of the conditions are met.
	
		if((reel0 == 0) && (reel1 == 0) && (reel2 == 0)) { // Triple zeros means TRIPLE_ZERO_REWARD * currentBet.
			win = TRIPLE_ZERO_REWARD * currentBet;
			
		} else if((reel0 == reel1) && (reel0 == reel2)) { // Three of a kind that is not zero means THREE_OF_A_KIND_REWARD * currentBet;
			win = THREE_OF_A_KIND_REWARD * currentBet;
			
		} else if((reel0 == reel1) || (reel0 == reel2) || (reel1 == reel2)) { // Two of a kind means TWO_OF_A_KIND_REWARD * currentBet;
			win = TWO_OF_A_KIND_REWARD * currentBet;
		
		} else {
			win = 0;
		}
	
		if(win == 0) {
			System.out.println("\nSorry, you lost."); // Tell the player they lost (if appropriate).
		
		} else {
			System.out.println("\nYou won " + win + " coins!"); // Tell the player how much they won (if appropriate).
		
			if(win > bigJackpot) { // If this win was larger than the biggest jackpot so far, change the value of bigJackpot to this win.
				bigJackpot = win;
			}
		}
		
		currentBet = 0; // Set the currentBet to 0.
	}
	
	/*
	 * Play this slot machine until the user chooses to walk away.
	 */
	public void play() {
		
		do {
			System.out.println("\n" + toString()); // Output the current state of the slot machine.
			
			System.out.println("\nBiggest jackpot so far: " + getBigJackpot());
			
			System.out.println("\nSelect an action from the options below:\na\tReset\nb\tBet one coin\nc\tPull the handle\nd\tWalk away");
			
			s = scan.nextLine();
			
			if(s.equals("a")) { // Equivalent to "1" on page 2
				reset();
				
			} else if(s.equals("b")) { // Equivalent to "2" on page 2
				bet();
				
			} else if(s.equals("c")) { // Equivalent to "3" on page 2
				if(currentBet > 0) {
					pullHandle();
				
				} else {
					System.out.println("\nYour bet must be greater than 0.");
				}
				
			} else if(!s.equals("d")) { // Any remaining input that is NOT "d"
				System.out.println("\nSorry, that is not a valid option.");
			}
			
		} while(!s.equals("d"));
		
		System.out.println("\n The current bet is " + currentBet + " coins.");
	}
}
