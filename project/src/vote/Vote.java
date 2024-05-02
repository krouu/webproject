package vote;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Vote {
	
	JFrame vote;
	JButton vote_btn;
	static String title;
	static String content;
	static String example1;
	static String example2;
	static String example3;
	static String example4;
	static String example5;
	static String[] member_data;
	
	public static void main(String[] args) {
		try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\vote_list.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
            	member_data = line.split("/");
            	
            	title = member_data[0];
				content = member_data[1];
				example1 = member_data[2];
				example2 = member_data[3];
            	
            	if(member_data.length == 5) {
            		example3 = member_data[4];
            	} else if(member_data.length == 6) {
            		example4 = member_data[5];
            	} else if(member_data.length == 7) {
            		example5 = member_data[6];
            	}		
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
        }
	}
	
	
	public Vote() {
		vote = new JFrame("투표");
		
	
	  	// 투표 버튼 객체
	  	vote_btn = new JButton("만들기");
	  	vote_btn.setBounds(300,20,100,40);
	  	vote_btn.setForeground(Color.WHITE);
	  	vote_btn.setBackground(new Color(255,155,0));
	  	vote_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VoteAdd();
				vote.setVisible(false);
			}
		});
	  	
	  	// 확인해야함
	  	for(int i = 0; i < member_data.length; i++) {
	  		// 투표정보 패널
			TitledBorder section1 = new TitledBorder(new LineBorder(Color.LIGHT_GRAY));
			JPanel panel1 = new JPanel();
			panel1.setLayout(null);
			panel1.setBorder(section1);
			panel1.setBounds(10,10,390,125);
			
			vote.add(panel1);
	  	}
	  	
	  		  	
	  	vote.add(vote_btn);
	 	
	  	vote.setLayout(null);
	  	vote.setSize(430,550); // 프레임 크기
        vote.setVisible(true); //프레임 창 보이게
        vote.setLocationRelativeTo(null); //프레임 창 윈도우 가운데
	}


	private void swith(int length) {
		// TODO Auto-generated method stub
		
	}
}
