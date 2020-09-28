/*
 * Sam Platt
 * Written 5 March 2020
 * CS 257
 */

import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class CasinoDriver {
	
	public static void main(String args[]) {
		
		// Check to ensure that there is at least one command line argument, or exits if there is not)
		if(args.length > 0) {
		
			// Uses EventQueue.invokeLater() method to create a new Casino for each name passed in on the command line as shown.
			EventQueue.invokeLater(() -> {
			
				// for each name pass in create new Casino.
				for(int i = 0; i < args.length; i++) {
					new Casino(args[i]);
					
				}
			});
		}
		
	}
	
}
