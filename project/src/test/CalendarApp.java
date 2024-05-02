package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class CalendarApp extends JFrame {
    private JLabel selectday;
    private JButton addButton;
    private JDatePicker schedule;
    private JPanel calendarPanel;
   
    public CalendarApp() {
        setTitle("NO1 Team");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
//        setLayout(new BorderLayout());
        
        Font font1 = new Font("HY 견고딕",Font.BOLD,20);
        selectday = new JLabel("자   유   로   운  N   O   .   1");
        selectday.setFont(font1);
        selectday.setBounds(250,10,400,30);
      
      

        schedule = new JDatePicker();
        schedule.setBounds(210,30,340,30); 
      

        

        addButton = new JButton("일정 추가");
        addButton.setBounds(650,10, 100, 30);
        addButton.setBackground(Color.cyan);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDate = schedule.getSelectedDate();
                if (selectedDate != null) {
                    String event = JOptionPane.showInputDialog(CalendarApp.this, "메모.");
                    // 여기서는 간단하게 입력된 일정을 콘솔에 출력합니다.
                    System.out.println("날짜: " + selectedDate + ", 일정: " + event);
                } else {
                    JOptionPane.showMessageDialog(CalendarApp.this, "날짜를 먼저 선택해주세요.");
                }
            }
        });
        

        // 캘린더 패널 생성
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0, 7)); // 7열 그리드
        calendarPanel.setBounds(40,100,700,420);

        
        
        // 현재 날짜 가져오기
        Calendar cal = Calendar.getInstance(); // 오늘 날짜
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        
        // 해당 월의 첫 번째 날의 요일 가져오기
        cal.set(year, month, 1);
        int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        
     // 요일 배열
        String[] dayOfWeekLabels = {"일", "월", "화", "수", "목", "금", "토"};
        
        for (int i = 0; i < dayOfWeekLabels.length; i++) {
            JLabel dayLabel = new JLabel(dayOfWeekLabels[i]);
            dayLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬
          
            
            dayLabel.setBounds(40 + i * 100, 100, 100, 30); // 위치와 크기 설정
            
            if(dayOfWeekLabels[i].equals("토")) {
                dayLabel.setForeground(Color.BLUE); // 토요일은 파란색
            } else if(dayOfWeekLabels[i].equals("일")) {
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
            
            
        // 휴일 색깔 지정     
        if((startDayOfWeek + i -1)% 7 ==0) {
           dayButton.setForeground(Color.BLUE);// 토요일일때 파란색으로
        }else if ((startDayOfWeek + i -1) % 7 == 1) {
           dayButton.setForeground(Color.red); // 일요일일 때 빨간색으로
 
        }
    
        }
        
     // 31일 이후의 빈 셀 추가
        int value= 7 - ((numDays+ startDayOfWeek - 1) %7);  // 남은 빈 셀 개수 계산
        for (int i = 0; i < value; i++) {
            JLabel space = new JLabel();
            space.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 테두리 설정
            space.setBackground(Color.GRAY);
            calendarPanel.add(space);
        }


        
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
        


            
        
        
        add(calendarPanel);
        add(selectday);
        add(schedule);
        add(addButton);
        setJMenuBar(mb);
        setVisible(true);
       
    }

    private class DayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String dayButton = button.getText();
            // 요일 버튼 클릭 시 처리할 내용 추가
            JOptionPane.showMessageDialog(null, "You clicked " + dayButton);
        }
    }
       
    // 년// 월// 일 버튼 메서드
    class JDatePicker extends JPanel {
        private JComboBox<String> dayComboBox;
        private JComboBox<String> monthComboBox;
        private JComboBox<String> yearComboBox;

        public JDatePicker() {
            String[] days = new String[31];
            for (int i = 0; i < 31; i++) {
                days[i] = String.valueOf(i + 1);
                
 
            }
            dayComboBox = new JComboBox<>(days);
            dayComboBox.setBounds(300, 30, 100, 30);

            String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
            monthComboBox = new JComboBox<>(months);
            monthComboBox.setBounds(350, 30, 100, 30);

            String[] years = new String[10];
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = 0; i < 10; i++) {
                years[i] = String.valueOf(currentYear + i);
            }

            yearComboBox = new JComboBox<>(years);
            yearComboBox.setBounds(210, 30, 120, 30);
            
            add(yearComboBox);
            add(new JLabel("년"));
            add(monthComboBox);
            add(new JLabel("월"));
            add(dayComboBox);
            add(new JLabel("일"));


        }

        public String getSelectedDate() {
            String day = (String) dayComboBox.getSelectedItem();
            String month = (String) monthComboBox.getSelectedItem();
            String year = (String) yearComboBox.getSelectedItem();
            if (day != null && month != null && year != null) {
                return year + "-" + month + "-" + day;
            } else {
                return null;
            }
        }
    }
    
}