package login12_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

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
	
	JFrame Login;
	JTextField id;
	JTextField pw;
    JTextField teamcode;
	
    public Login() {	
			
	  //로그인 창
	  Login = new JFrame("넘버원");
	  
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
	  
	  
	  //아이디 객체
	  id = new JTextField("아이디",20);
	  id.setBounds(115,180,180,30); // 위치
	  
	  //비밀번호 객체
	  pw = new JTextField("비밀번호",20);
	  pw.setBounds(115,220,180,30);
      
	  //팀코드 객체
	  teamcode = new JTextField("팀코드", 20);
	  teamcode.setBounds(115,260,180,30);
	  
	  //로그인 버튼
	  JButton join = new JButton("로그인");
	  join.addActionListener(this);
	  join.setFont(new Font("HY견고딕 보통",Font.BOLD,20));
	  join.setBorder(null);
	  join.setForeground(Color.white);
	  join.setBackground(new Color(255,155,0));
	  join.setBounds(115,320,180,40);
	  
	  //회원가입 버튼
	  JButton jtb = new JButton("회원가입");
	  jtb.setFont(new Font("HY견고딕 보통",Font.BOLD,20));
	  jtb.setForeground(new Color(255,155,0));
	  jtb.setBackground(Color.white);
	  jtb.setBounds(115,370,180,40);	  
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
	  Login.add(id); // 아이디 객체 호출
	  Login.add(pw); // 비밀번호 객체 호출
	  Login.add(teamcode); // 팀코드 객체 호출
	  Login.add(join);
	  Login.add(jtb);
	  
	  Login.setLayout(null); // 레이아웃
	  
	  Login.setSize(430,550); // 프레임 사이즈
	  Login.setVisible(true); // 프레임 보이기
	  Login.setLocationRelativeTo(null); //프레임 창 윈도우 가운데
	  
	  id.addMouseListener(new SignAdapter(id));
	  pw.addMouseListener(new SignAdapter(pw));
	  teamcode.addMouseListener(new SignAdapter(teamcode));
	}
    
		 
    public void actionPerformed(ActionEvent e) {
    	        
        // 각 객체에 공란 및 항목명일 경우 안내문구 띄우기
        if(id.getText().equals("아이디") || id.getText().equals("")) {
        	id.setText("입력해주세요");
            id.setForeground(new Color(255, 69, 0));
        } else if(pw.getText().equals("비밀번호") || pw.getText().equals("")) {
        	pw.setText("입력해주세요");
            pw.setForeground(new Color(255, 69, 0));
        } else if(teamcode.getText().equals("팀코드") || teamcode.getText().equals("")) {
        	teamcode.setText("입력해주세요");
        	teamcode.setForeground(new Color(255, 69, 0));
        } else {
 
	        // memeber_list에 정보가 저장되어 있는지 확인
	        try {
	        	
	        	// member_list 파일 읽어오기
	        	BufferedReader br = new BufferedReader(new FileReader("D:\\test\\member_list.txt"));
				String line = null;
				boolean success = false;
				
				// 한줄씩 확인
				while((line = br.readLine()) != null) {
					String[] member_data = line.split("/");
					String id_chk = member_data[0];
					String pw_chk = member_data[1];
					
					// 로그인 성공
					if(id.getText().equals(id_chk) && pw.getText().equals(pw_chk)) {
						System.out.println("로그인 성공");
						success = true;				
						// 메인 페이지로 이동
						// new 메인페이지();
						// Login.setVisible(false);
						break;
					}
				}
				
				br.close();
				
				// 로그인 실패
				if(!success) {
					id.setText("로그인 실패 (계정정보 확인)");
					id.setForeground(new Color(255, 69, 0));
					pw.setText("");
					teamcode.setText("");
				}
				
			} catch (FileNotFoundException e2) {
				System.out.println("파일을 찾지 못했습니다");
			} catch (Exception e2) {
				e2.printStackTrace();
			} 
        }
        
       
    }
    
  
}

	
