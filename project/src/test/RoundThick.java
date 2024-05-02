package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.border.AbstractBorder;


public class RoundThick {
   
   class ProfileButton extends JButton {
       public ProfileButton(String text) {
           super(text);
           setContentAreaFilled(false); // Make button transparent
           setBorder(new ThickBorder(Color.LIGHT_GRAY, 5)); // Set thick border
       }
   }

   

   class ThickBorder extends AbstractBorder {
       private Color color;
       private int thickness;

       public ThickBorder(Color color, int thickness) {
           this.color = color;
           this.thickness = thickness;
       }
       

       @Override
       public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
           Graphics2D g2d = (Graphics2D) g.create();
           g2d.setColor(color);
           g2d.setStroke(new BasicStroke(thickness));
           g2d.drawRoundRect(x, y, width - 1, height - 1, 20, 20); // Draw rounded rectangle border
           g2d.dispose();
       }

       @Override
       public Insets getBorderInsets(Component c) {
           return new Insets(thickness, thickness, thickness, thickness);
       }

       @Override
       public Insets getBorderInsets(Component c, Insets insets) {
           insets.left = insets.top = insets.right = insets.bottom = thickness;
           return insets;
       }
   }
}