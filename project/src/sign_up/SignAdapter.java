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
		String data = tf.getText();
		
		if(data.equals("아이디") || data.equals("비밀번호") || data.equals("비밀번호 확인") || data.equals("이름") || data.equals("팀코드") || 
		data.equals("입력해주세요") || data.equals("비밀번호가 일치하지 않습니다") || data.equals("선택해주세요")) tf.setText("");
		
	}
}
