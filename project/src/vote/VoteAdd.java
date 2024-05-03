package vote;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import sign_up.SignAdapter;

public class VoteAdd extends JFrame implements ActionListener{
	
	JFrame vote_add;
	JTextField title;
	JTextField content;
	JTextField example1;
	JTextField example2;
	JTextField example3;
	JTextField example4;
	JButton save_btn;
	JButton add_btn;
	JLabel memo;
	int count = 3;
	
	
	public VoteAdd() {
		vote_add = new JFrame("투표 추가");
		
		// 투표정보 패널
		TitledBorder section1 = new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "투표정보");
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBorder(section1);
		panel1.setBounds(10,10,390,125);
		
		// 투표 제목 객체
		title = new JTextField("투표 제목");
		title.setBounds(20,30,370,40);
		title.setBorder(new LineBorder(Color.DARK_GRAY));
		title.setForeground(Color.LIGHT_GRAY);
		title.addMouseListener(new SignAdapter(title));
		
		// 투표에 관한 설명 객체
		content = new JTextField("투표에 관한 설명 입력(옵션)");
		content.setBounds(20,80,370,40);
		content.setBorder(new LineBorder(Color.DARK_GRAY));
		content.setForeground(Color.LIGHT_GRAY);
		content.addMouseListener(new SignAdapter(content));
		
		// 항목추가 버튼 객체
		add_btn = new JButton("투표 항목 추가");
		add_btn.setBounds(20,150,150,40);
		add_btn.setForeground(new Color(255,155,0));
		add_btn.setBackground(Color.WHITE);
		add_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(count == 3) {
					example3 = new JTextField("항목3",20);
					example3.setBounds(20,300,370,40);
					example3.setBorder(new LineBorder(Color.DARK_GRAY));
					example3.setForeground(Color.LIGHT_GRAY);
					example3.addMouseListener(new SignAdapter(example3));
					
					vote_add.add(example3);
					count += 1;
				} else if(count == 4) {
					example4 = new JTextField("항목4",20);
					example4.setBounds(20,350,370,40);
					example4.setBorder(new LineBorder(Color.DARK_GRAY));
					example4.setForeground(Color.LIGHT_GRAY);
					example4.addMouseListener(new SignAdapter(example4));
					
					vote_add.add(example4);
					add_btn.setEnabled(false);
					memo.setVisible(true);
					
				} 
			}
		});
		
		// 투표항목 최대 4개까지 안내 객체
		memo = new JLabel("최대 4개까지 가능합니다");
		memo.setForeground(new Color(255,170,0)); //폰트 컬러 적용
		memo.setBounds(180,150,200,40);
		memo.setVisible(false);
		
		// 투표항목1 객체
		example1 = new JTextField("항목1",20);
		example1.setBounds(20,200,370,40);
		example1.setBorder(new LineBorder(Color.DARK_GRAY));
		example1.setForeground(Color.LIGHT_GRAY);
		example1.addMouseListener(new SignAdapter(example1));
		
		// 투표항목2 객체
		example2 = new JTextField("항목2",20);
		example2.setBounds(20,250,370,40);
		example2.setBorder(new LineBorder(Color.DARK_GRAY));
		example2.setForeground(Color.LIGHT_GRAY);
		example2.addMouseListener(new SignAdapter(example2));
		
		// 저장버튼 객체
		save_btn = new JButton("저장");
		save_btn.setBounds(130,450,150,40);
		save_btn.setForeground(Color.WHITE);
		save_btn.setBackground(new Color(255,155,0));
		save_btn.addActionListener(this);
		
		
		vote_add.add(title);
		vote_add.add(content);
		vote_add.add(panel1);
		vote_add.add(example1);
		vote_add.add(example2);
		vote_add.add(add_btn);
		vote_add.add(memo);
		vote_add.add(save_btn);
		
		vote_add.setLayout(null);
		vote_add.setSize(430,550); // 프레임 크기
		vote_add.setVisible(true); //프레임 창 보이게
		vote_add.setLocationRelativeTo(null); //프레임 창 윈도우 가운데
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(title.getText().equals("투표 제목") || title.getText().equals("")) {
			title.setText("입력해주세요");
			title.setForeground(new Color(255, 69, 0));
		} else if(example1.getText().equals("항목1") || example1.getText().equals("")) {
			example1.setText("입력해주세요");
			example1.setForeground(new Color(255, 69, 0));
		} else if(example2.getText().equals("항목2") || example2.getText().equals("")) {
			example2.setText("입력해주세요");
			example2.setForeground(new Color(255, 69, 0));
		} else {
			try {
				// vote_list 파일에 회원가입 정보 저장
				String filePath = "D:\\vote_list.txt";
				
				// 파일이 없을 경우 파일 생성
				File file = new File(filePath);
				if(!file.exists()) {
					file.createNewFile();
				}
				
				// 파일에 투표정보 저장
				BufferedWriter br = new BufferedWriter(new FileWriter(file,true));
				String vote_data = null;
				
				if(example3 != null && example4 == null ) {
					vote_data = title.getText()+"/"+content.getText()+"/"+example1.getText()+"/"+example2.getText()+"/"+example3.getText();	
				} else if(example3 != null && example4 != null) {
					vote_data = title.getText()+"/"+content.getText()+"/"+example1.getText()+"/"+example2.getText()+"/"+example3.getText()+"/"+example4.getText();	
				} else if(example3 == null && example4 == null) {
					vote_data = title.getText()+"/"+content.getText()+"/"+example1.getText()+"/"+example2.getText();
				}
				
				br.write(vote_data);
				br.newLine();
				
				br.flush(); 
				br.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			new Vote(); // 투표 창으로 이동
			vote_add.setVisible(false); // 현재 회원가입 화면 숨기기
		}
	}
}
