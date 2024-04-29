package login12_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import sign_up.SignAdapter;
import sign_up.SignUp;




public class Login extends JFrame implements ActionListener {
	JTextField usernameField;
    JPasswordField passwordField;
    JTextField teamcode;
    
	
    public Login() {
			
			
	  //로그인 창
	  JFrame Login = new JFrame("넘버원");
	  
	  
	  //프로그램 명 (NO.1) 
	  JLabel user = new JLabel("NO.1");  //프로그램 객체
	  Font font = new Font("HY견고딕 보통",Font.BOLD,30);// 폰트 객체(글자체,효과,사이즈)
	  user.setFont(font);// 폰트 적용
	  user.setForeground(new Color(255,155,0)); //폰트 컬러 적용
	  user.setBounds(125,20,70,60);
	  
	  //프로그램 명 (Team Calendar)
	  JLabel user2 =new JLabel("Team Calendar");
	  Font font2 = new Font("HY견고딕 보통",Font.BOLD,20);
	  user2.setFont(font2);
	  user2.setBounds(90,60,150,60);
	  
	  
	  //아이디 객체
	  usernameField = new JTextField("아이디 :",20);
	  usernameField.setBounds(75,150,180,30); // 위치
	  
	  
	  //비밀번호 객체
	  passwordField = new JPasswordField("비밀번호 : ",20);
	  passwordField.setBounds(75,190,180,30);
	  
//	      JPasswordField pw2 = new JPasswordField();
//	      pw2.setBounds(100,50,80,30);  임시
      
	  //팀코드 객체
	  teamcode = new JTextField("팀코드 :", 20);
	  teamcode.setBounds(75,230,180,30);
	  
	  //로그인 버튼
	  JButton join = new JButton("로그인");
	  join.addActionListener(this);
	  join.setFont(new Font("HY견고딕 보통",Font.BOLD,20));
	  join.setBorder(null);
	  join.setForeground(Color.white);
	  join.setBackground(new Color(255,155,0));
	  join.setBounds(75,265,180,40);
	  
	  //회원가입 버튼
	  
	  JButton jtb = new JButton("회원가입");
	  jtb.setFont(new Font("HY견고딕 보통",Font.BOLD,20));
	  jtb.setForeground(new Color(255,155,0));
	  jtb.setBackground(Color.white);
	  jtb.setBounds(75,310,180,40);	  
	  jtb.addActionListener(new ActionListener() {
		  @Override
		public void actionPerformed(ActionEvent e) {
			 new SignUp();
			Login.setVisible(false);
			
			
		}
	  });
    
	  
	  
	      
	      
	  //디자인 추가
	  Login.add(user); // 프로젝트팀명 호출
	  Login.add(user2);// 프로젝트팀명 호출
	  Login.add(usernameField); // 아이디 객체 호출
	  Login.add(passwordField); // 비밀번호 객체 호출
	  Login.add(teamcode); // 팀코드 객체 호출
	  Login.add(join);
	  Login.add(jtb);
	  
	  Login.setLayout(null); // 레이아웃
	  
	  Login.setSize(350,400); // 프레임 사이즈
	  Login.setVisible(true); // 프레임 보이기
	  
	  usernameField.addMouseListener(new SignAdapter(usernameField));
	  passwordField.addMouseListener(new SignAdapter(passwordField));
	  teamcode.addMouseListener(new SignAdapter(teamcode));
	  
	  
	  
	}
    
    
	
		 
    public void actionPerformed(ActionEvent e) {
    	String user = usernameField.getText();
    	String user2 = new String(passwordField.getPassword());
        String code = teamcode.getText();

        // 여기에 로그인 처리 로직을 추가하면 됩니다.

        if (user.equals("강순화") && user2.equals("123") && code.equals("NO1")) {
            JOptionPane.showMessageDialog(this, "로그인 성공!");
		            
	    } else if(!user.equals("강순화") && user2.equals("123") && code.equals("NO1")) {
	        JOptionPane.showMessageDialog(this, "아이디가 틀립니다. 확인해주세요");
		          
	    } else if(user.equals("강순화") && !user2.equals("123") && code.equals("NO1")) {
	        JOptionPane.showMessageDialog(this, "비밀번호가 틀립니다. 확인해주세요");
		           
		} else if(user.equals("강순화") && user2.equals("123") && !code.equals("NO1")){
		    JOptionPane.showMessageDialog(this, "팀코드가 틀립니다. 확인해주세요");
		} else{JOptionPane.showMessageDialog(this, "팀코드가 틀립니다. 확인해주세요");
	}
    }
    
  
    }

	
