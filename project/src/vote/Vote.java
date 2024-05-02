package vote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Vote {
	
	JFrame vote;
	JButton vote_btn;
	
	
	public Vote() {
		vote = new JFrame("투표");
		
	
	  	// 투표 버튼 객체
	  	vote_btn = new JButton("만들기");
	  	vote_btn.setBounds(300,20,100,40);
	  	vote_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VoteAdd();
				vote.setVisible(false);
			}
		});
	  	
	  		  	
	  	vote.add(vote_btn);
	 	
	  	vote.setLayout(null);
	  	vote.setSize(430,550); // 프레임 크기
        vote.setVisible(true); //프레임 창 보이게
        vote.setLocationRelativeTo(null); //프레임 창 윈도우 가운데
	}
}
