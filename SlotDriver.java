/*
 * Sam Platt
 * Written 1 Mar., 2020
 * Lab 8
 */

import java.awt.*;
import javax.swing.*;

public class SlotDriver {

   // main method to test our GUI
   public static void main (String[] args) {

      // let the event queue handle creation of our window
      // (not discussed in lecture)
      EventQueue.invokeLater(() -> {
         // create three new objects
         new SlotMachineGUI();
         new SlotMachineGUI();
         new SlotMachineGUI();
      });
   }
} 