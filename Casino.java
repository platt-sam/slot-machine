/*
 * Portions of this code originally written by Kevin Sahr on February 27 2019 as "Buttons.java"
 * 
 * Sam Platt
 * Written 5 March, 2020
 * CS 257
 */

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Casino extends JFrame implements ActionListener {
	
	// CLASS VARIABLES
	
	// INSTANCE VARIABLES
	
	public String casinoName;
	public int totalWinnings; // The sum of all totalWinnings values of SlotMachineGUI objects created from this Casino.
	public int highestJackpot; // The highest jackpot (winning) on a SlotMachineGUI object created from this Casino.
	public ArrayList<SlotMachineGUI> machines = new ArrayList<SlotMachineGUI>();
	
	private JLabel casinoNameLabel;
	private JLabel totalCasinoWinningsLabel;
	private JLabel jackpotLabel;
	
	private JButton createButton;
	private JButton resetButton;
	
	
	//CONSTRUCTORS
	
	public Casino(String name) {
		
		casinoName = name;
		
		totalWinnings = 0; // The sum of all totalWinnings values of SlotMachineGUI objects created from this Casino.
		
		highestJackpot = 0; // The highest jackpot (winning) on a SlotMachineGUI object created from this Casino.
		
		
		JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.red);
		
		casinoNameLabel = new JLabel();
		casinoNameLabel.setOpaque(true);
		casinoNameLabel.setBackground(Color.green);
		textPanel.add(casinoNameLabel);
		
		
		JPanel statsPanel = new JPanel();
		statsPanel.setBackground(Color.white);
		
		totalCasinoWinningsLabel = new JLabel();
		totalCasinoWinningsLabel.setOpaque(true);
		totalCasinoWinningsLabel.setBackground(Color.white);
		totalCasinoWinningsLabel.setForeground(Color.black);
		statsPanel.add(totalCasinoWinningsLabel);
		
		jackpotLabel = new JLabel();
		jackpotLabel.setOpaque(true);
		jackpotLabel.setBackground(Color.white);
		jackpotLabel.setForeground(Color.black);
		statsPanel.add(jackpotLabel);
		
		
		displayValues();
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.blue);
		
		createButton = new JButton("CREATE SLOT MACHINE");
		createButton.addActionListener(this);
		createButton.setOpaque(true);
		createButton.setBackground(Color.green);
		buttonPanel.add(createButton);
		
		resetButton = new JButton("RESET");
		resetButton.addActionListener(this);
		resetButton.setOpaque(true);
		resetButton.setBackground(Color.green);
		buttonPanel.add(resetButton);
		
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
	
	// METHODS
	
	private void displayValues() {
		casinoNameLabel.setText("" + getCasinoName());
		
		totalCasinoWinningsLabel.setText("Total Winnings: " + getTotalWinnings());
		
		jackpotLabel.setText("Highest Jackpot: " + getHighestJackpot());
		
	}
	
	/*
	 * The method takes as an argument an amount won on a single slot machine handle pull, 
	 * and should update and display the Casino’s total winnings and biggest jackpot instance variables accordingly. 
	 * This method should also enable the Casino’s reset button (which may have been disabled by a prior reset).
	 */
	protected void updateStats(int amountWon) {
		totalWinnings += amountWon;
		
		if(highestJackpot < amountWon) {
			highestJackpot = amountWon;
			
		}
		
		displayValues();
		
		resetButton.setEnabled(true);
	}
	
	/*
	 * Resets all existing slot machines.
	 */
	private void resetAll() {
		for(int i = 0; i < machines.size(); i++) {
			machines.get(i).resetNums();
			
		}
		
		/*
		 * Alter the Casino reset button code so that pressing the reset button also sets 
		 * the total winnings and biggest jackpot instance variables to zero 
		 * (in addition to the actions specified in Part A).
		 */
		totalWinnings = 0;
		highestJackpot = 0;
		
		displayValues();
		
	}
	
	/*
	 * Gets the name of the Casino object.
	 */
	private String getCasinoName() {
		return casinoName;
		
	}
	
	/*
	 * Gets the total coins won by all Slot Machines in the Casino.
	 */
	private int getTotalWinnings() {
		return totalWinnings;
	}
	
	private int getHighestJackpot() {
		return highestJackpot;
	}
	
	/*
	 * Creates a new SlotMachineGUI object and adds it to the machines ArrayList.
	 */
	private void createNewSlotMachine() {
		EventQueue.invokeLater(() -> {
			
			SlotMachineGUI m = new SlotMachineGUI(this);
			
			// System.out.println(this.casinoName);
			
			/* 
			 * do whatever else you need to do with the new slot machine here; 
			 * at a minimum you should add the new slot machine to the Casino's ArrayList instance variable
			 */
			machines.add(m);
			
		});
	}
	
	
	
	/*
	 * Implement the method defined in the ActionListener interface.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		
		if(src == createButton) {
			createNewSlotMachine();
			
		} else if(src == resetButton) {
	    	resetAll();
	    	
	    }
	}
	
}
