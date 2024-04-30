package test;

import javax.swing.SwingUtilities;

public class CalendarMain {
   
   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
          public void run() {
             new CalendarApp();
          }
       });
      
   }
}