package sign_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import login12_1.Login;

public class SignUp extends JFrame {
	
		JTextField id;
		JTextField pw1;
		JTextField pw2;
		JTextField name;
		JButton color;
		JTextField color_box;
		JButton sign_btn;
		Color color_check;
		
        public SignUp() {
	
	        // 회원가입 창
	        JFrame Sign = new JFrame("넘버원");

		    //프로그램 명 (NO.1) 
		  	JLabel user = new JLabel("NO.1");  //프로그램 객체
		  	Font font = new Font("HY견고딕 보통",Font.BOLD,40);// 폰트 객체(글자체,효과,사이즈)
		  	user.setFont(font);// 폰트 적용
		  	user.setForeground(new Color(255,155,0)); //폰트 컬러 적용
		  	user.setBounds(160,20,100,100);
		  	  
		  	//프로그램 명 (Team Calendar)
		  	JLabel user2 =new JLabel("Team Calendar");
		  	Font font2 = new Font("HY견고딕 보통",Font.BOLD,20);
		  	user2.setFont(font2);
		  	user2.setBounds(130,75,150,60);

	        id = new JTextField("아이디");
	        id.setBounds(115, 140, 180, 30); // 위치

	        pw1 = new  JTextField("비밀번호");
	        pw1.setBounds(115, 180, 180, 30);

	        pw2 = new JTextField("비밀번호 확인");
	        pw2.setBounds(115, 220, 180, 30);

	        name = new JTextField("이름");
	        name.setBounds(115, 260, 180, 30);
	        		        
	        color = new JButton("색상");
	        color.setBounds(115,300,80,30);
	        // 색상 선택
	        color.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					color_check = JColorChooser.showDialog(color, "색상 선택", Color.CYAN);
					color_box.setBackground(color_check);
					System.out.println(color_check);
				}
			});
	        
	        color_box = new JTextField();
	        color_box.setBounds(215,300,80,30);
	        
	        
	        sign_btn = new JButton("가입하기");
	        sign_btn.setFont(new Font("HY견고딕 보통", Font.BOLD, 20));
	        sign_btn.setBorder(null);
	        sign_btn.setForeground(Color.white);
	        sign_btn.setBackground(new Color(255, 155, 0));
	        sign_btn.setBounds(115, 360, 180, 40);
	        sign_btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(id.getText().equals("아이디") || id.getText().equals("")) {
						id.setText("아이디를 입력해주세요");
	                    id.setForeground(new Color(255, 69, 0));
					} else if(pw1.getText().equals("비밀번호") || pw1.getText().equals("")) {
						pw1.setText("비밀번호를 입력해주세요");
	                    pw1.setForeground(new Color(255, 69, 0));
					} else if(!pw1.getText().equals(pw2.getText())){
						pw2.setText("비밀번호가 일치하지 않습니다");
	                    pw2.setForeground(new Color(255, 69, 0));
					} else if(name.getText().equals("이름") || name.getText().equals("")) {
						name.setText("이름을 입력해주세요");
						name.setForeground(new Color(255, 69, 0));
					} else if(color_box.getText().equals("")) {
						color_box.setText("색상 선택");
						color_box.setForeground(new Color(255, 69, 0));
					} else {
						new Login();
	                    Sign.setVisible(false); // 현재 회원가입 화면 숨기기
					}
				}
			});

	        Sign.add(user);
	        Sign.add(user2);
	        Sign.add(id);
	        Sign.add(pw1);
	        Sign.add(pw2);
	        Sign.add(name);
	        Sign.add(sign_btn);
	        Sign.add(color);
	        Sign.add(color_box);
	        
	        id.addMouseListener(new SignAdapter(id));
	        pw1.addMouseListener(new SignAdapter(pw1));
	        pw2.addMouseListener(new SignAdapter(pw2));
	        name.addMouseListener(new SignAdapter(name));		        
	        
	        
	        Sign.setLayout(null);
	        Sign.setSize(430,550);
	        Sign.setVisible(true); //프레임 창 보이게
	        Sign.setLocationRelativeTo(null); //프레임 창 윈도우 가운데
	        
	        
	        
	    }					
	}
		

