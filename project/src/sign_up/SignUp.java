package sign_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import login12_1.Login;
import login12_1.loginFormMain;	
		import javax.swing.*;
		import java.awt.*;
		import java.awt.event.ActionEvent;
		import java.awt.event.ActionListener;

	public class SignUp extends JFrame {

		
            public SignUp() {
		
		        // 회원가입 창
		        JFrame Sign = new JFrame("넘버원");

		        // 프로그램 명 (NO.1)
		        JLabel user = new JLabel("NO.1");  // 프로그램 객체
		        Font font = new Font("HY견고딕 보통", Font.BOLD, 30);// 폰트 객체(글자체,효과,사이즈)
		        user.setFont(font);// 폰트 적용
		        user.setForeground(new Color(255, 155, 0)); // 폰트 컬러 적용
		        user.setBounds(125, 20, 70, 60);

		        JTextField id = new JTextField("아이디");
		        id.setBounds(75, 100, 180, 30); // 위치

		        JTextField pw1 = new  JTextField("비밀번호");
		        pw1.setBounds(75, 140, 180, 30);

		        JTextField pw2 = new JTextField("비밀번호 확인");
		        pw2.setBounds(75, 180, 180, 30);

		        JTextField name = new JTextField("이름");
		        name.setBounds(75, 220, 180, 30);

		        JTextField birth = new JTextField("생년월일");
		        birth.setBounds(75, 260, 180, 30);

		        JButton sign_btn = new JButton("가입하기");
		        sign_btn.setFont(new Font("HY견고딕 보통", Font.BOLD, 20));
		        sign_btn.setBorder(null);
		        sign_btn.setForeground(Color.white);
		        sign_btn.setBackground(new Color(255, 155, 0));
		        sign_btn.setBounds(75, 300, 180, 30);

		        Sign.add(user);
		        Sign.add(id);
		        Sign.add(pw1);
		        Sign.add(pw2);
		        Sign.add(name);
		        Sign.add(birth);
		        Sign.add(sign_btn);
		        
		        
		        Sign.setLayout(null);
		        Sign.setSize(350, 400);
		        Sign.setVisible(true);
		        Sign.setLocationRelativeTo(null); //프레임 창 윈도우 가운데
		        
		        
		        // 비밀번호 체크
		        sign_btn.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                if (pw1.getText().equals(pw2.getText())) {
		                    // 회원가입 성공 시 로그인 화면으로 전환
		                    new Login();
		                    Sign.setVisible(false); // 현재 회원가입 화면 숨기기
		                } else {
		                    pw2.setText("비밀번호가 일치하지 않습니다");
		                    pw2.setForeground(new Color(255, 69, 0));
		                }
		            }
		        });
		    }
	}
		

