package sign_up;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PasswordAdapter implements MouseListener{
	
	
	JTextField tf;
	
	public PasswordAdapter(JTextField tf) {
		this.tf = tf;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		change();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void change() {
		JPasswordField pw = (JPasswordField) tf;
	}

}
