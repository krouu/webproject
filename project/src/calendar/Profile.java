package calendar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import login12_1.Login;

public class Profile extends JFrame{
   private JButton profile; //프로필 버튼 객체
    private UserProfile userProfile; // 사용자 프로필 객체
    private JButton logout; // 로그아웃 버튼 객체
    static String id; // 아이디 변수
    

    // 프로필 창 내용
    public Profile(String id) {
       
       // 프레임 설정
       this.id = id;
        setTitle("프로필");// 프로필 창의 Titie
        setSize(400, 200); // 프로필 창 사이즈
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //프로필 창만 닫게
        setLayout(null); // 레이아웃을 null로 설정하여 절대 위치에 컴포넌트를 배치
        

        // 프로필 정보 표시 영역
        JTextArea profileTextArea = new JTextArea();
        profileTextArea.setEditable(false); // 편집 불가능하도록 설정
        add(profileTextArea);

        
        // 로그아웃 버튼 생성
        logout = new JButton("로그아웃");
        logout.setBounds(150, 120, 100, 30); // 위치와 크기 설정
        logout.setBackground(new Color(255, 155, 0));
        logout.setForeground(Color.white);
        logout.setBorderPainted(false); // 버튼상자 선 없애기
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 로그아웃 버튼 클릭 시 프로그램 종료
            }
        });
        add(logout); // 프레임에 로그아웃 버튼 추가

        // 프로필 정보 로드
        loadProfile(id);

        
        // 화면 표시
        setLocationRelativeTo(null); // 화면 가운데 위치
        setVisible(true);
    }

    
    private void loadProfile(String chk_id) {
        System.out.println(id);
        
        try {
            // 회원 정보를 저장한 파일 경로
            String filePath = "C:\\Users\\admin\\Desktop\\member_list.txt";

            // 파일에서 회원 정보 읽어오기
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder profileInfo = new StringBuilder();
            while ((line = br.readLine()) != null) {
                // 각 회원 정보를 파싱하여 프로필 정보에 추가
                String[] userInfo = line.split("/");
                String id = userInfo[0];
                
                // 로그인 정보 확인
                if(chk_id.equals(id)) {
                   String name = userInfo[2];
                   String color = userInfo[3];

                    // 프로필 정보에 추가
                    profileInfo.append("아이디: ").append(id).append("\n\n");
                    profileInfo.append("이름: ").append(name).append("\n\n");
                    profileInfo.append("선택된 색상: ").append(color).append("\n\n");
                    break; // 해당 사용자 정보를 찾았으므로 더 이상 반복할 필요가 없음
                    
                    
                    
                }
            }
            br.close();
  

            // 프로필 정보 표시
            JTextArea profileTextArea = new JTextArea(profileInfo.toString());
            profileTextArea.setEditable(false); // 편집 불가능하도록 설정
            profileTextArea.setBounds(10, 10, 365, 100); // 위치와 크기 설정
            
            
            // 글꼴 및 스타일 설정
            Font font = new Font("SansSerif", Font.BOLD, 14); 
            profileTextArea.setFont(font); // JTextArea에 글꼴 설정

            // 선택적으로 텍스트 색상과 배경색 설정 가능
            profileTextArea.setForeground(Color.BLACK);
            profileTextArea.setBackground(Color.WHITE);
            
            

            add(profileTextArea);
            
    

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "프로필 정보를 불러오는 데 문제가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }

    }
    

    //프로필 버튼 클릭 시 프로필 창 나오게 출력
   private void profile() {
      profile.doClick();

   }
    
 
    
}