package sign_up;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

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
		if(tf.getText().equals("아이디") || tf.getText().equals("비밀번호") || tf.getText().equals("비밀번호 확인") || tf.getText().equals("이름")) {
			tf.setText("");
		}
	}
}
