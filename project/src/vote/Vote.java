package vote;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Vote{
	
	JFrame vote;
	JPanel panel;
	JButton vote_btn;
	
	public Vote() {	

		vote = new JFrame("투표");

		// 투표 버튼 객체
	  	vote_btn = new JButton("만들기");
	  	vote_btn.setBounds(270,20,130,40);
	  	vote_btn.setForeground(Color.WHITE);
	  	vote_btn.setBackground(new Color(255,155,0));
	  	vote_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VoteAdd();
				vote.setVisible(false);
			}
		});
	  	
	  	 // 컨텐츠 패널 생성
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.WHITE);

		// vote_list 파일에서 투표정보 불러오기
		try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\vote_list.txt"));
            String line;
            
            // 객체 y 초기값
            int title_y = 80;
            int content_y = 70;
            int btn1_y = 120;
            int btn2_y = 150;
            int btn3_y = 180;
            int btn4_y = 210;
            int save_y = 100;
            

            while ((line = reader.readLine()) != null) {
            	
            	JRadioButton example3_btn = null;
                JRadioButton example4_btn = null;
            	
            	String[] vote_data = line.split("/");
            	
            	String title = vote_data[0];
            	String content = vote_data[1];
            	String example1 = vote_data[2];
            	String example2 = vote_data[3];
            	
            	// 항목1 객체
            	JRadioButton example1_btn = new JRadioButton(example1);
            	example1_btn.setBounds(30,btn1_y,200,28);
            	
            	
            	// 항목2 객체
            	JRadioButton example2_btn = new JRadioButton(example2);
            	example2_btn.setBounds(30,btn2_y,200,28);

            	if(vote_data.length == 5) {
            		// 항목3 객체
            		String example3 = vote_data[4];
            		
            		example3_btn = new JRadioButton(example3);
            		example3_btn.setBounds(30,btn3_y,200,28);
            		vote.add(example3_btn);
       
            	
            	} else if(vote_data.length == 6) {
            		// 항목3 객체
            		String example3 = vote_data[4];
            		
            		example3_btn = new JRadioButton(example3);
            		example3_btn.setBounds(30,btn3_y,200,28);
            		vote.add(example3_btn);
            		            		
            		// 항목4 객체
            		String example4 = vote_data[5];

            		example4_btn = new JRadioButton(example4);
            		example4_btn.setBounds(30,btn4_y,200,28);
            		vote.add(example4_btn);
            	}
            	            	
            	ButtonGroup group = new ButtonGroup();
            	group.add(example1_btn);
            	group.add(example2_btn);
            	group.add(example3_btn);
            	group.add(example4_btn);
            	            	
            	// 투표정보 패널
        		TitledBorder section = new TitledBorder(new LineBorder(Color.LIGHT_GRAY), title);
        		JPanel vote_panel = new JPanel();
        		vote_panel.setLayout(null);
        		vote_panel.setBorder(section);
        		vote_panel.setBounds(20,title_y,380,170);
        		            	
            	
            	// 투표 제목 객체
            	JLabel content_label = new JLabel(content);
            	content_label.setBounds(30, content_y, 355, 80);
            	content_label.setForeground(Color.LIGHT_GRAY);
            	
            	// 투표 저장 객체
            	JButton save_btn = new JButton("저장");
            	save_btn.setBounds(270,save_y,110,40);
            	save_btn.setForeground(new Color(255,155,0));
            	save_btn.setBackground(Color.WHITE);
            	save_btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						save_btn.setEnabled(false);
					}
				});
            	
            	// 객체 y 증가값
            	btn1_y += 180;
            	btn2_y += 180;
            	btn3_y += 180;
            	btn4_y += 180;
            	title_y += 180;
            	content_y += 180;
            	save_y += 180;
            	
            	vote.add(example1_btn);
            	vote.add(example2_btn);
            	vote.add(content_label);
            	vote.add(save_btn);
            	vote.add(vote_panel);
            	            	
            }
            reader.close();
            
        } catch (IOException e) {
            System.err.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
        }

	  	vote.add(vote_btn);
	  	
	  	
	  	vote.setLayout(null);
	  	vote.setSize(430,550); // 프레임 크기
        vote.setVisible(true); //프레임 창 보이게
        vote.setLocationRelativeTo(null); //프레임 창 윈도우 가운데
	}
	
	public static void main(String[] args) {
		Vote vote = new Vote();
	}
}
