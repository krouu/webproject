package test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

//프로필 버튼 테두리
public class RoundBorder extends AbstractBorder {
    private int radius;
    
    RoundBorder(int radius) {
        this.radius = radius;
    }

    //테두리를 그리는 메소드
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(c.getForeground());
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2d.dispose();
    }

    //데두리의 안쪽 여백을 반환하는 메소드
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
    }

    //테두리의 안쪽 여백을 반환하는 메소드
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = radius + 1;
        
        return insets;
    }
    
}