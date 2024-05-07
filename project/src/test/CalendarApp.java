package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CalendarApp extends JFrame {
    private JLabel selectday;
    private JButton addButton;
    private JDatePicker schedule;
    private JPanel calendarPanel;
    private JLabel selectedDateLabel;
    private JButton viewEventsButton;
    private static final String SAVE_FOLDER_PATH = "C:/CalendarEvents/";
    private JButton profile;
    private UserProfile userProfile; // 사용자 프로필 객체

    
//    
//    Login login_id = new Login();
    
    
   
    public CalendarApp() {
    	
//    	Login login = new Login();
//    	System.out.println(login.getLogin());
        setTitle("NO1 Team");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        
        

        
      //내 프로필 버튼 생성
        profile = new JButton("내 프로필");
        profile.setBounds(700, 10, 80, 30);
        profile.setForeground(Color.white);
        profile.setBackground(new Color(255,155,0));
        profile.setBorder(new RoundBorder(10));;
        profile.setBorderPainted(false);
//        profile.setBorderPainted();
        profile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // showProfile(); // 프로필 정보를 표시하는 메서드 호출
            	Profile profile = new Profile();
            }
        });


        
        Font font1 = new Font("HY 견고딕",Font.BOLD,20);
        selectday = new JLabel("자   유   로   운  N   O   .   1");
        selectday.setFont(font1);
        selectday.setBounds(250,10,400,30);
      
      

        schedule = new JDatePicker();
        schedule.setBounds(210,30,340,30); 
      

        

        addButton = new JButton("일정 추가");
        addButton.setBounds(580,10, 100, 30);
        addButton.setBackground(Color.cyan);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDate = schedule.getSelectedDate();
                if (selectedDate != null) {
                    String event = JOptionPane.showInputDialog(CalendarApp.this, "일정 추가하기");
                    if (event != null && !event.isEmpty()) {
                        saveEvent(selectedDate, event);
                    }
                } else {
                    JOptionPane.showMessageDialog(CalendarApp.this, "날짜를 먼저 선택해주세요.");
                }
            }
        });
        viewEventsButton = new JButton("일정 보기");
        viewEventsButton.setBounds(620, 50, 100, 30);
        viewEventsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        

        // 캘린더 패널 생성
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7)); // 7열 그리드
        calendarPanel.setBounds(40,100,700,420);
        add(calendarPanel); // 캘린더 패널 추가

       
        schedule.calendarPanel = calendarPanel;
        schedule.selectedDateLabel = selectedDateLabel;
// 메뉴바 버튼
        
        JMenuBar mb = new JMenuBar();// 메뉴바 객체 선언
        JMenu home = new JMenu("홈");
        JMenu to = new JMenu("투표");
        
        Font font = new Font("HY견고딕",Font.BOLD,20);//폰트 선언, 글꼴,스타일,크기
        home.setFont(font);
        to.setFont(font);
        
        
     // 메뉴 항목의 간격 조정
        Insets margin = new Insets(10, 70, 20, 30); // 위, 왼쪽, 아래, 오른쪽
//        home.setMargin(margin);
        to.setMargin(margin);
     
        
        mb.add(home);
        mb.add(to);
        

        mb.add(home);
        mb.add(to);
        


            
        
        
        
        add(selectday);
        add(schedule);
        add(addButton);
        add(profile);
        setJMenuBar(mb);
        setVisible(true);
        setLocationRelativeTo(null);
        schedule.updateCalendar(); // 달력 업데이트
       
    }
    
    private void saveEvent(String selectedDate, String event) {
        String fileName = SAVE_FOLDER_PATH + File.separator + selectedDate + ".txt";
        try {
            // Create the folder if it doesn't exist
            File folder = new File(SAVE_FOLDER_PATH);
            folder.mkdirs(); // Create directory and parent directories if they don't exist

            // Use try-with-resources to automatically close the FileWriter
            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.write(selectedDate + ": " + event + "\n");
                JOptionPane.showMessageDialog(CalendarApp.this, "일정이 저장되었습니다.");
                
                // Print file path to console
                System.out.println("일정 파일이 저장된 경로: " + fileName);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(CalendarApp.this, "파일을 열거나 쓰는 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(CalendarApp.this, "폴더를 생성하는 도중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
    


    private void showEventsDialog(String events) {
        JOptionPane.showMessageDialog(this, events, "일정 보기", JOptionPane.PLAIN_MESSAGE);
    }
    private String extractEvents(String events) {
        // 파일 이름이 포함된 이벤트 문자열에서 파일 이름을 제거하고 내용만을 반환
        int colonIndex = events.indexOf(":");
        if (colonIndex != -1) {
            return events.substring(colonIndex + 1).trim(); // ":" 이후의 문자열을 가져와서 앞뒤 공백 제거
        } else {
            return events.trim(); // 파일 이름이 포함되지 않은 경우 그대로 반환 (공백 제거)
        }
    }
    private void loadEvent(String selectedDate) {
        String fileName = SAVE_FOLDER_PATH + File.separator + selectedDate + ".txt";
        StringBuilder eventBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                eventBuilder.append(line).append("\n");
            }
            
            String events = eventBuilder.toString();
            String extractedEvents = extractEvents(events); // 파일 이름을 제거하여 이벤트 내용만 추출
            showEventsDialog(extractedEvents); // 파일을 성공적으로 읽어온 경우에만 이벤트를 표시하는 다이얼로그 호출
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(CalendarApp.this, "저장된 이벤트가 없습니다.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(CalendarApp.this, "파일을 읽는 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }


 
       
    // 년// 월// 일 버튼 메서드
    class JDatePicker extends JPanel implements ActionListener {
    
        
        private JComboBox<String> monthComboBox;
        private JComboBox<String> yearComboBox;
        private JComboBox<String> dayComboBox;
        private JPanel calendarPanel;
        private JLabel selectedDateLabel;
       
    

        public JDatePicker() {
            String[] days = new String[31];
            for (int i = 0; i < 31; i++) {
                days[i] = String.valueOf(i + 1);
                
 
            }
            
            dayComboBox = new JComboBox<>(days);
            dayComboBox.setBounds(300, 30, 100, 30);
  

            String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
            monthComboBox = new JComboBox<>(months);
            Calendar cal2 = Calendar.getInstance();
            int currentmonth = cal2.get(Calendar.MONTH);// JDatePicker 의 현재 월 호출
            monthComboBox.setSelectedIndex(currentmonth);
            
            
           
            
            
            
           
            monthComboBox.setBounds(350, 30, 100, 30);
         
            
//            monthComboBox.addActionListener(new ActionListener());

            String[] years = new String[10];
            int Year = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = 0; i < 10; i++) {
                years[i] = String.valueOf(Year + i);
            }

            yearComboBox = new JComboBox<>(years);
            yearComboBox.setBounds(210, 30, 120, 30);
//            yearComboBox.addActionListener(new ActionListener());
            
            
            add(yearComboBox);
            add(new JLabel("년"));
            add(monthComboBox);
            add(new JLabel("월"));
            add(dayComboBox);
            add(new JLabel("일"));



            // ActionListeners
            dayComboBox.addActionListener(this);
            yearComboBox.addActionListener(this);
            monthComboBox.addActionListener(this);
   
        
        }
        
        

        public String getSelectedDate() {

            String month = (String) monthComboBox.getSelectedItem();
            String year = (String) yearComboBox.getSelectedItem();
            String day = (String)   dayComboBox.getSelectedItem();
            int selectedMonth = monthComboBox.getSelectedIndex() + 1;
            int selectedYear = Integer.parseInt(year);
            
            if (month != null && year != null && day != null) {
                return year + "-" + month + "-" +day;
            } else {
                return null;
            }
        }



             public void updateCalendar() {
              
                  calendarPanel.removeAll();

                  // Calendar 객체 생성 및 설정
                  int selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
                  int selectedMonth = monthComboBox.getSelectedIndex();

                  Calendar cal = Calendar.getInstance();
                  cal.set(selectedYear, selectedMonth, 1);

                  // 해당 월의 첫 번째 날의 요일 가져오기
                  int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

                  // 요일 배열
                  String[] dayOfWeekLabels = {"일", "월", "화", "수", "목", "금", "토"};

                  // 요일 레이블 추가
                  for (int i = 0; i < dayOfWeekLabels.length; i++) {
                      JLabel dayLabel = new JLabel(dayOfWeekLabels[i]);
                      dayLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬
                      dayLabel.setBounds(40 + i * 100, 100, 100, 30); // 위치와 크기 설정

                      if (dayOfWeekLabels[i].equals("토")) {
                          dayLabel.setForeground(Color.BLUE); // 토요일은 파란색
                      } else if (dayOfWeekLabels[i].equals("일")) {
                          dayLabel.setForeground(Color.RED); // 일요일은 빨간색
                      }

                      calendarPanel.add(dayLabel);
                  }

                  // 해당 월의 마지막 날짜 가져오기
                  int numDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

                  // 빈 셀 추가 시작 날짜
                  for (int i = 1; i < startDayOfWeek; i++) {
                      JLabel space = new JLabel();
                      space.setBorder(BorderFactory.createLineBorder(Color.black));
                      space.setBackground(Color.GRAY);
                      calendarPanel.add(space);
                  }

                  // 해당 월의 날짜 추가
                  for (int i = 1; i <= numDays; i++) {
                       JButton dayButton = new JButton(Integer.toString(i)); // 요일 버튼 지정
                       dayButton.setBorder(BorderFactory.createLineBorder(Color.black)); // 테투리 선
                       dayButton.setBackground(Color.white);
                       calendarPanel.add(dayButton); // 캘린더 패널에 요일 호출

                       // Add ActionListener to day button
                       dayButton.addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               String selectedDate = yearComboBox.getSelectedItem() + "-" + 
                                                      (monthComboBox.getSelectedIndex() + 1) + "-" + 
                                                      dayButton.getText();
                               loadEvent(selectedDate);
                           }
                       });

                     
                      

                      // 휴일 색깔 지정
                      if ((startDayOfWeek + i - 1) % 7 == 0) {
                          dayButton.setForeground(Color.BLUE);// 토요일일때 파란색으로
                      } else if ((startDayOfWeek + i - 1) % 7 == 1) {
                          dayButton.setForeground(Color.red); // 일요일일 때 빨간색으로
                      }
                  }

                  // 31일 이후의 빈 셀 추가
                  int value = 7 - ((numDays + startDayOfWeek - 1) % 7);  // 남은 빈 셀 개수 계산
                  for (int i = 0; i < value; i++) {
                      JLabel space = new JLabel();
                      space.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 테두리 설정
                      space.setBackground(Color.GRAY);
                      calendarPanel.add(space);
                  }

                  // JPanel 다시 그리기
                  calendarPanel.revalidate();
                  calendarPanel.repaint();
              }

              @Override
              public void actionPerformed(ActionEvent e) {
                 if (e.getSource() == yearComboBox || e.getSource() == monthComboBox || e.getSource() ==dayComboBox) {
                      updateCalendar();
                  }
              }
          }
}
