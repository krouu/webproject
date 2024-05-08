package sign_up;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

// 항목 클릭 시 공란처리
public class SignAdapter implements MouseListener{
	
	
	JTextField tf;
	
	public SignAdapter(JTextField tf) {
		this.tf = tf;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		change();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		change();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
				
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
				
	}
	
	public void change() {
		
		tf.setText("");
		tf.setForeground(Color.BLACK);
		
	}
}
