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
        

        selectday = new JLabel("자   유   로   운  N   O   .   1");
        selectday.setBounds(250,1,400,30);
      
      

        schedule = new JDatePicker();
        schedule.setBounds(210,30,340,30); 
      

        

        addButton = new JButton("일정 추가");
        addButton.setBounds(650,10, 100, 30);
        addButton.setBackground(Color.cyan);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDate = schedule.getSelectedDate();
                if (selectedDate != null) {
                    String event = JOptionPane.showInputDialog(CalendarApp.this, "일정을 입력하세요.");
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
        calendarPanel.setBounds(80,100,700,450);

        
        // 현재 날짜 가져오기
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        
        // 해당 월의 첫 번째 날의 요일 가져오기
        cal.set(year, month, 1);
        int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        
     // 요일 배열
        String[] dayOfWeekLabels = {"일", "월", "화", "수", "목", "금", "토"};
        
     // 요일 표시 추가
        for (String dayOfWeekLabel : dayOfWeekLabels) {
            JLabel dayLabel = new JLabel(dayOfWeekLabel);
            calendarPanel.add(dayLabel);
            
            
            
        

      
        }
        
        // 해당 월의 마지막 날짜 가져오기
        int numDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        // 빈 셀 추가
        for (int i = 1; i < startDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }
        

        // 해당 월의 날짜 추가
        for (int i = 1; i <= numDays; i++) {
            JLabel dayLabel = new JLabel(Integer.toString(i));
            calendarPanel.add(dayLabel);
        
    
        }
    
//        if ( dayOfWeek == Calendar.SUNDAY) {
//            dayLabel.setForeground(Color.RED); // 토요일과 일요일은 빨간색으로 설정  
//        } else if(dayOfWeek2==Calendar.SATURDAY) {
//           dayLabel.setForeground(Color.orange);
//        }
//        


            
        
        
        add(calendarPanel);
        add(selectday);
        add(schedule);
        add(addButton);
        setVisible(true);
    }

    private class DayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String dayOfWeek = button.getText();
            // 요일 버튼 클릭 시 처리할 내용 추가
            JOptionPane.showMessageDialog(null, "You clicked " + dayOfWeek);
        }
    }
       
 
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
            yearComboBox.setBounds(210, 30, 100, 30);
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