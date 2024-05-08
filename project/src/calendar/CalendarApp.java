package calendar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import login.Login;
import vote.Vote;


// 달력 앱을 나타내는 클래스
public class CalendarApp extends JFrame {
    private JLabel selectday;  // 자유로운 NO.1의 레이블
    private JButton addButton; // 일정 추가 버튼
    private JDatePicker schedule; // 날짜 선택기
    private JPanel calendarPanel; // 달력 패널
    private JLabel selectedDateLabel; // 선택된 날짜 표시 레이블
    private JButton viewEventsButton; // 일정 보기 버튼
    private static final String SAVE_FOLDER_PATH = "C:/CalendarEvents/";
    private JButton profile;
    private UserProfile userProfile; // 사용자 프로필 객체
    
   
     // 로그인 정보를 가져오는 멤버변수
    String id = Login.getId_chk();
    String pw = Login.getPw_chk();
    String color = Login.getColor_chk();

    public CalendarApp() {
        setTitle("NO1 Team"); // 프레임 제목 설정
        setSize(800, 600); // 프레임 사이즈
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료버튼
        setLayout(null); // 레이아웃 설정
        
        // 로그인 정보 컬러
        String[] color_info = color.split(",");
        Integer r_value = Integer.valueOf(color_info[0].substring(2));
        Integer g_value = Integer.valueOf(color_info[1].substring(2));
        Integer b_value = Integer.valueOf(color_info[2].substring(2));
       
        // 프로필 버튼 설정
        profile = new JButton("내 프로필");
        profile.setBounds(700, 10, 80, 30);
        profile.setForeground(Color.white);
        profile.setBackground(new Color(r_value, g_value, b_value));
        profile.setBorder(new RoundBorder(10));
        profile.setBorderPainted(false);
        profile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Profile profile = new Profile(id);
            }
        });
  
        // 자유로운 NO.1 레이블 설정들
        Font font1 = new Font("HY 견고딕", Font.BOLD, 20);
        selectday = new JLabel("자   유   로   운  N   O   .   1");
        selectday.setFont(font1);
        selectday.setBounds(250, 10, 400, 30);
        
        // 스케줄 객체 선언 및 사이즈 조절
        schedule = new JDatePicker();
        schedule.setBounds(210, 30, 340, 30);
        
       // 일정 추가 버튼
        addButton = new JButton("일정 추가");
        addButton.setBounds(580, 10, 100, 30);
        addButton.setBackground(Color.cyan);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {// 일정추가버튼을 클릭시 이행할 이벤트
                String selectedDate = schedule.getSelectedDate();
                if (selectedDate != null) {
                    String event = JOptionPane.showInputDialog(CalendarApp.this, "일정 추가하기");
                    if (event != null && !event.isEmpty()) {
                        saveEvent(selectedDate, event, id, profile.getBackground());
                    }
                } else {
                    JOptionPane.showMessageDialog(CalendarApp.this, "날짜를 먼저 선택해주세요.");
                }
            }
        });
        
        
        
        // 캘린더 패널 설정들
        calendarPanel = new JPanel(); // 패널 객체 생성
        calendarPanel.setLayout(new GridLayout(0, 7)); // 그리드 레이아웃 0, 7열
        calendarPanel.setBounds(40, 100, 700, 420); // 사이즈
        add(calendarPanel); // 패널 추가
        
        // 스케줄 객체의 달력 패널과 선택된 날짜 레이블 선정
        schedule.calendarPanel = calendarPanel;
        schedule.selectedDateLabel = selectedDateLabel;
        
        // 메뉴바 설정
        JMenuBar mb = new JMenuBar();
        JMenu home = new JMenu("홈");
        JMenu to = new JMenu("투표");
 
        // 폰트 설정
        Font font = new Font("HY견고딕", Font.BOLD, 20);
        
        // 메뉴 폰트 설정
        home.setFont(font);
        to.setFont(font);
    
        // 메뉴바 여백 설정
        Insets margin = new Insets(10, 70, 20, 30);
        to.setMargin(margin);
  
        // 메뉴바에 메뉴 추가
        mb.add(home);
        mb.add(to);
        
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 홈 메뉴 클릭 시 실행할 코드 작성
            }
        });

        to.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { // 투표버튼 클릭 시 이벤트
                new Vote();
            }
        });

        //프레임에 컴포넌트들 추가
        add(selectday);
        add(schedule);
        add(addButton);
        add(profile);
        
      
        setJMenuBar(mb); // 프레임에 메뉴바 설정
        setVisible(true); // 프레임 보이기
        setLocationRelativeTo(null); // 프레임을 화면 중앙에 배치
        schedule.updateCalendar(); // 달력을 업데이트
    }
    
    // 일정추가시 저장하는 기능
    private void saveEvent(String selectedDate, String event, String userId, Color color) {
       // 파일 이름을 설정하는 기능
        String fileName = SAVE_FOLDER_PATH + File.separator + selectedDate + ".txt"; 
        try {
            File folder = new File(SAVE_FOLDER_PATH);
            folder.mkdirs();
            
            // 파일에 일정 정보 저장
            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.write(selectedDate + ": " + event + "\n");
                writer.write("User ID: " + userId + "\n");
                writer.write("Color: " + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "\n");
                JOptionPane.showMessageDialog(CalendarApp.this, "일정이 저장되었습니다.");
                System.out.println("일정 파일이 저장된 경로: " + fileName);

                // 선택한 날짜에 색상 변경 기능
                int day = Integer.parseInt(selectedDate.split("-")[2]);
                Component[] components = calendarPanel.getComponents();
                JButton selectedDayButton = null;
                for (Component component : components) {
                    if (component instanceof JButton && ((JButton) component).getText().equals(Integer.toString(day))) {
                        selectedDayButton = (JButton) component;
                        break;
                    }
                }
                if (selectedDayButton != null && selectedDayButton.getBackground().equals(Color.WHITE)) {
                    selectedDayButton.setBackground(color);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(CalendarApp.this, "파일을 열거나 쓰는 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(CalendarApp.this, "폴더를 생성하는 도중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
    
    // 일정 보기 다이얼로그를 표시하는 메서드,
    private void showEventsDialog(String events) {
        JOptionPane.showMessageDialog(this, events, "일정 보기", JOptionPane.PLAIN_MESSAGE);
    }
    
    // 이벤트 정보를 추출하는 메서드
    private String extractEvents(String events) {
        int colonIndex = events.indexOf(":");
        if (colonIndex != -1) {
            return events.substring(colonIndex + 1).trim();
        } else {
            return events.trim();
        }
    }
  
    // 저장한 일정을 불러오는 메서드
    private void loadEvent(String selectedDate) {
        String fileName = SAVE_FOLDER_PATH + File.separator + selectedDate + ".txt";
        StringBuilder eventBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                eventBuilder.append(line).append("\n");
            }

            String events = eventBuilder.toString();
            String extractedEvents = extractEvents(events);
            showEventsDialog(extractedEvents);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(CalendarApp.this, "저장된 이벤트가 없습니다.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(CalendarApp.this, "파일을 읽는 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
    
    // 날짜 선택하는 기능 안에 있는 클래스
    class JDatePicker extends JPanel implements ActionListener {
       // 날짜 선택기능 컴포넌트들 선언
        private JComboBox<String> monthComboBox;
        private JComboBox<String> yearComboBox;
        private JComboBox<String> dayComboBox;
        private JPanel calendarPanel;
        private JLabel selectedDateLabel;

       
        public JDatePicker() {
           // 일 콤보박스 설정
            String[] days = new String[31];
            for (int i = 0; i < 31; i++) {
                days[i] = String.valueOf(i + 1);
            }

            dayComboBox = new JComboBox<>(days);
            dayComboBox.setBounds(300, 30, 100, 30);
            
            // 월 콤보박스 설정
            String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
            monthComboBox = new JComboBox<>(months);
            Calendar cal2 = Calendar.getInstance();
            int currentmonth = cal2.get(Calendar.MONTH);
            monthComboBox.setSelectedIndex(currentmonth);
            
            // 년 콤보박스 설정
            String[] years = new String[10];
            int Year = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = 0; i < 10; i++) {
                years[i] = String.valueOf(Year + i);
            }

            //콤보박스 객체 생성 및 위치 설정
            yearComboBox = new JComboBox<>(years); 
            yearComboBox.setBounds(210, 30, 120, 30);

            //콤보박스 추가
            add(yearComboBox);
            add(new JLabel("년"));
            add(monthComboBox);
            add(new JLabel("월"));
            add(dayComboBox);
            add(new JLabel("일"));

            //콤보박스에
            yearComboBox.addActionListener(this); // 년 콤보박스를 현재 클래스에서 이벤트 처리 
            monthComboBox.addActionListener(this);  // 월 콤보박스를 현재 클래스에서 이벤트 처리 
            dayComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedDate = getSelectedDate();
                    if (selectedDate != null) {
                        int day = Integer.parseInt(selectedDate.split("-")[2]);
                        Component[] components = calendarPanel.getComponents();
                        JButton selectedDayButton = null;
                        for (Component component : components) {
                            if (component instanceof JButton && ((JButton) component).getText().equals(Integer.toString(day))) {
                                selectedDayButton = (JButton) component;
                                break;
                            }
                        }
                        if (selectedDayButton != null) {
                            selectedDayButton.setBackground(profile.getBackground());
                        }
                    }
                }
            });
        }

        
        //현재 선택된 연도, 월, 일을 가져와 반환하는 메서드
        public String getSelectedDate() { 
            String month = (String) monthComboBox.getSelectedItem();
            String year = (String) yearComboBox.getSelectedItem();
            String day = (String) dayComboBox.getSelectedItem();
            if (month != null && year != null && day != null) {
                return year + "-" + month + "-" + day;
            } else {
                return null;
            }
        }

        //버튼의 배경색과 선을 설정.
        private void setButtonColor(JButton button, Color color) {
            button.setBackground(color);
            button.setOpaque(true);
            button.setBorderPainted(false);
        }

       //calendar Event 텍스트파일에 있는 색상정보를 불러와 표시해주는 기능
        private Color loadSavedColorForDate(String date) {
            String fileName = SAVE_FOLDER_PATH + File.separator + date + ".txt";
            File file = new File(fileName);
            if (!file.exists()) {
                return null;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Color:")) {
                        String[] colorValues = line.substring(line.indexOf(":") + 1).trim().split(",");
                        int r = Integer.parseInt(colorValues[0].trim());
                        int g = Integer.parseInt(colorValues[1].trim());
                        int b = Integer.parseInt(colorValues[2].trim());
                        return new Color(r, g, b);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        
        // 선택된 연도의 월/요일을 업데이트하여 캘린더를 새로 그리는 기능.
        public void updateCalendar() {
            calendarPanel.removeAll();
            int selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
            int selectedMonth = monthComboBox.getSelectedIndex();
            Calendar cal = Calendar.getInstance();
            cal.set(selectedYear, selectedMonth, 1);
            int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            String[] dayOfWeekLabels = {"일", "월", "화", "수", "목", "금", "토"};
            for (int i = 0; i < dayOfWeekLabels.length; i++) {
                JLabel dayLabel = new JLabel(dayOfWeekLabels[i]);
                dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
                dayLabel.setBounds(40 + i * 100, 100, 100, 30);
                if (dayOfWeekLabels[i].equals("토")) {
                    dayLabel.setForeground(Color.BLUE);
                } else if (dayOfWeekLabels[i].equals("일")) {
                    dayLabel.setForeground(Color.RED);
                }
                calendarPanel.add(dayLabel);
            }
            int numDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 1; i < startDayOfWeek; i++) {
                JLabel space = new JLabel();
                space.setBorder(BorderFactory.createLineBorder(Color.black));
                space.setBackground(Color.GRAY);
                calendarPanel.add(space);
            }
            for (int i = 1; i <= numDays; i++) {
                JButton dayButton = new JButton(Integer.toString(i));
                dayButton.setBorder(BorderFactory.createLineBorder(Color.black));
                dayButton.setBackground(Color.white);
                calendarPanel.add(dayButton);
                dayButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedDate = yearComboBox.getSelectedItem() + "-" +
                                (monthComboBox.getSelectedIndex() + 1) + "-" +
                                dayButton.getText();
                        loadEvent(selectedDate);
                        dayButton.setBackground(profile.getBackground());
                    }
                });
                Color savedColor = loadSavedColorForDate(selectedYear + "-" + (selectedMonth + 1) + "-" + i);
                if (savedColor != null) {
                    setButtonColor(dayButton, savedColor);
                }
                if ((startDayOfWeek + i - 1) % 7 == 0) {
                    dayButton.setForeground(Color.BLUE);
                } else if ((startDayOfWeek + i - 1) % 7 == 1) {
                    dayButton.setForeground(Color.red);
                }
            }
            int value = 7 - ((numDays + startDayOfWeek - 1) % 7);
            for (int i = 0; i < value; i++) {
                JLabel space = new JLabel();
                space.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                space.setBackground(Color.GRAY);
                calendarPanel.add(space);
            }
            calendarPanel.revalidate();
            calendarPanel.repaint();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == yearComboBox || e.getSource() == monthComboBox || e.getSource() == dayComboBox) {
                updateCalendar();
            }
        }
    }
}