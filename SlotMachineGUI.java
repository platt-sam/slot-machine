/*
 * Portions of this code originally written by Kevin Sahr on February 27 2019 as "Buttons.java"
 * 
 * Sam Platt
 * Written 1 Mar., 2020
 * Lab 8
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class SlotMachineGUI extends JFrame implements ActionListener {
	
	// Initial reel values for the three reels.
	public static final int INIT0 = 0;
	public static final int INIT1 = 0;
	public static final int INIT2 = 0;
	
	private final int MAX_BET = 4; // The maximum value currentBet can be.
	private final int TRIPLE_ZERO_REWARD = 10; // The base reward for having "three of a kind", where all values are equal to 0.
	private final int THREE_OF_A_KIND_REWARD = 5; // The base reward for having "three of a kind".
	private final int TWO_OF_A_KIND_REWARD = 3; // The base reward for having "two of a kind".
	
	Casino theCasino;
	
	private int currentBet;
	private int win;
	private int totalWinnings;
	
	private int n0;
	private int n1;
	private int n2;
	
	private JLabel casinoLabel;
	
	private JLabel n0Label;
	private JLabel n1Label;
	private JLabel n2Label;
	
	private JLabel betLabel;
	private JLabel winLabel;
	private JLabel totalWinLabel;
	
	private JLabel betData;
	private JLabel winData;
	private JLabel totalWinData;
	
	private JButton betCoinButton;
	private JButton pullHandleButton;
	private JButton resetButton;
	
	public SlotMachineGUI(Casino cas) {
		
		theCasino = cas;
		
		// create the labels to display the values
		
		
	    JPanel textPanel = new JPanel();
	    textPanel.setBackground(Color.blue);
	    
	    casinoLabel = new JLabel();
	    casinoLabel.setOpaque(true);
	    casinoLabel.setBackground(Color.red);
	    casinoLabel.setForeground(Color.white);
	    textPanel.add(casinoLabel);
	    
	    n0Label = new JLabel(); // Will contain the first of three reel values
	    n0Label.setOpaque(true);
	    n0Label.setBackground(Color.yellow);
	    textPanel.add(n0Label);
	    
	    n1Label = new JLabel(); // Will contain the second of three reel values
	    n1Label.setOpaque(true);
	    n1Label.setBackground(Color.yellow);
	    textPanel.add(n1Label);
	    
	    n2Label = new JLabel(); // Will contain the third of three reel values
	    n2Label.setOpaque(true);
	    n2Label.setBackground(Color.yellow);
	    textPanel.add(n2Label);
	    
	    
	    JPanel statsPanel = new JPanel();
	    statsPanel.setBackground(Color.white);
	    
	    betLabel = new JLabel();
	    betLabel.setOpaque(true);
	    betLabel.setBackground(Color.white);
	    betLabel.setForeground(Color.blue);
	    statsPanel.add(betLabel);
	    
	    // Will contain the actual data that goes with betLabel.
	    betData = new JLabel();
	    betData.setOpaque(true);
	    betData.setBackground(Color.white);
	    betData.setForeground(Color.black);
	    statsPanel.add(betData);
	    
	    winLabel = new JLabel();
	    winLabel.setOpaque(true);
	    winLabel.setBackground(Color.white);
	    winLabel.setForeground(Color.blue);
	    statsPanel.add(winLabel);
	    
	    // Will contain the actual data that goes with winLabel.
	    winData = new JLabel();
	    winData.setOpaque(true);
	    winData.setBackground(Color.white);
	    winData.setForeground(Color.black);
	    statsPanel.add(winData);
	    
	    totalWinLabel = new JLabel();
	    totalWinLabel.setOpaque(true);
	    totalWinLabel.setBackground(Color.white);
	    totalWinLabel.setForeground(Color.blue);
	    statsPanel.add(totalWinLabel);
	    
	    // Will contain the actual data that goes with totalWinLabel.
	    totalWinData = new JLabel();
	    totalWinData.setOpaque(true);
	    totalWinData.setBackground(Color.white);
	    totalWinData.setForeground(Color.black);
	    statsPanel.add(totalWinData);
	      
	    // create the buttons
	      
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setBackground(Color.blue);
	    
	    betCoinButton = new JButton("BET COIN");
	    betCoinButton.addActionListener(this);
	    betCoinButton.setBackground(Color.green);
	    betCoinButton.setOpaque(true);
	    buttonPanel.add(betCoinButton);
	    
	    pullHandleButton = new JButton("PULL HANDLE BUTTON");
	    pullHandleButton.addActionListener(this);
	    pullHandleButton.setBackground(Color.green);
	    pullHandleButton.setOpaque(true);
	    buttonPanel.add(pullHandleButton);
	    
	    resetButton = new JButton("RESET");
	    resetButton.addActionListener(this);
	    resetButton.setBackground(Color.green);
	    resetButton.setOpaque(true);
	    buttonPanel.add(resetButton);

	    resetNums();

	    // set-up my container (recall we is-a JFrame) and
	    // add the panels
	    setBackground(Color.black);
	    setLayout(new BorderLayout());
	    add(textPanel, BorderLayout.NORTH);
	    add(statsPanel, BorderLayout.CENTER);
	    add(buttonPanel, BorderLayout.SOUTH);

	    // set some window behavior
	    // (note these are not discussed in the lecture)
	    pack(); // makes the frame fit the contents
	    setDefaultCloseOperation(EXIT_ON_CLOSE); // closing window exits
	    setVisible(true); // make sure we are displayed
	    
	}
	
	/* 
	 * Display the name of the casino and the values of the three reel numbers.
	 */
	private void displayNums() {
		casinoLabel.setText("" + theCasino.casinoName);
		
		n0Label.setText("" + n0);
		n1Label.setText("" + n1);
		n2Label.setText("" + n2);
		
	}
	
	/*
	 * Display the currentBet in a user-friendly way.
	 * Display the win in a user-friendly way.
	 * Display the totalWinnings in a user-friendly way.
	 */
	private void displayStats() {
		betLabel.setText("Bet: ");
		betData.setText("" + currentBet);
		
		winLabel.setText("Win: ");
		winData.setText("" + win);
		
		totalWinLabel.setText("Total Winnings: ");
		totalWinData.setText("" + totalWinnings);
	}
	
	/*
	 * Increment the value of currentBet by 1.
	 * Change whether certain buttons are enabled.
	 * Display all changes.
	 */
	public void betCoin() {
		// Increment currentBet by 1;
		if(currentBet < MAX_BET) {
			currentBet += 1;
		}
		
		if(currentBet < MAX_BET) {
			betCoinButton.setEnabled(true);
		} else {
			betCoinButton.setEnabled(false);
		}
		
		pullHandleButton.setEnabled(true);
		resetButton.setEnabled(true);
		
		displayNums();
		displayStats();
	}
	
	/*
	 * Generate a random number between upperBound and lowerBound for one of the three reels on the slot machine.
	 */
	private int getReelRandom() {
		int upperBound = 5;
		int lowerBound = 0; // since lowerBound is 0, you don't really need it. You can just pass in the upper bound.
		
		Random randNum = new Random();
		
		int rnd = randNum.nextInt(upperBound);	// If the lowerBound != 0 use this instead:
												// int rnd = randNum.nextInt(upperBound - lowerBound) + lowerBound;
		return rnd;
	}
	
	/*
	 * Sets the values of the three reels by generating three pseudo-random numbers.
	 */
	private void spinReels() {
		n0 = getReelRandom();
		n1 = getReelRandom();
		n2 = getReelRandom();
	}
	
	/*
	 * Call spinReels() method. 
	 * Determine how much the player has won. 
	 * Increment totalWinnings by win. 
	 * Reset currentBet to 0. 
	 * Change whether certain buttons are enabled.
	 */
	public void pullHandle() { 
		spinReels();
		
		// Determine how much the player has won:
			
		win = 0; // win starts with a value of 0. This will only be changed if one of the conditions are met.
	
		if((n0 == 0) && (n1 == 0) && (n2 == 0)) { // Triple zeros means TRIPLE_ZERO_REWARD * currentBet.
			win = TRIPLE_ZERO_REWARD * currentBet;
			
			/*
			 * Update your pullHandle method in the Slot Machine class to invoke the updateStats method on its Casino 
			 * whenever that slot machine has non-zero winnings.
			 */
			theCasino.updateStats(win);
			
		} else if((n0 == n1) && (n0 == n2)) { // Three of a kind that is not zero means THREE_OF_A_KIND_REWARD * currentBet;
			win = THREE_OF_A_KIND_REWARD * currentBet;
			
			/*
			 * Update your pullHandle method in the Slot Machine class to invoke the updateStats method on its Casino 
			 * whenever that slot machine has non-zero winnings.
			 */
			theCasino.updateStats(win);
			
		} else if((n0 == n1) || (n0 == n2) || (n1 == n2)) { // Two of a kind means TWO_OF_A_KIND_REWARD * currentBet;
			win = TWO_OF_A_KIND_REWARD * currentBet;
			
			/*
			 * Update your pullHandle method in the Slot Machine class to invoke the updateStats method on its Casino 
			 * whenever that slot machine has non-zero winnings.
			 */
			theCasino.updateStats(win);
			
		}
		
		// Add any new winnings to the totalWinnings variable.
		totalWinnings += win;
		
		/*
		 * I'm not using the method resetNums() here because resetNums() also resets the reel values to zero, 
		 * which I do not want to do.
		 */
		currentBet = 0; // after the user pulls the handle, the current bet resets to zero.
		
		betCoinButton.setEnabled(true); // since the currentBet is zero, betCoinButton should be enabled.
		pullHandleButton.setEnabled(false); // since the currentBet is zero, pullHandleButton should not be enabled.
		
		displayNums();
		displayStats();
		
	}
	
	/*
	 * Resets all three of the reel values to their initial values. 
	 * Resets currentBet and win to 0. 
	 * Change whether certain buttons are enabled. 
	 * Display all changes.
	 */
	public void resetNums() {
		// Change the value of currentBet to 0;
		
		n0 = INIT0;
		n1 = INIT1;
	    n2 = INIT2;
	    
	    currentBet = 0;
	    win = 0;
	    
	    betCoinButton.setEnabled(true);
	    pullHandleButton.setEnabled(false);
	    resetButton.setEnabled(false);
	    
	    displayNums();
	    displayStats();
		
	}
	
	/*
	 * Implement the method defined in the ActionListener interface.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
	    
		if(src == betCoinButton) {
			betCoin();
			
		} else if(src == pullHandleButton) {
			pullHandle();
			
		} else if(src == resetButton) {
	    	resetNums();
	    	
	    }
	}
}
