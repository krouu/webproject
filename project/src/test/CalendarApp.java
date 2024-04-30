package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class CalendarApp extends JFrame {
    private JLabel selectedDateLabel;
    private JButton addButton;
    private JDatePicker datePicker;

    public CalendarApp() {
        setTitle("NO1 Team");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        selectedDateLabel = new JLabel("날짜를 선택하세요.");
        add(selectedDateLabel, BorderLayout.NORTH);
      

        datePicker = new JDatePicker();
        add(datePicker, BorderLayout.CENTER);

        addButton = new JButton("일정 추가");
        addButton.setSize(30,40);
        addButton.setBounds(500,10,30,40);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDate = datePicker.getSelectedDate();
                if (selectedDate != null) {
                    String event = JOptionPane.showInputDialog(CalendarApp.this, "일정을 입력하세요.");
                    // 여기서는 간단하게 입력된 일정을 콘솔에 출력합니다.
                    System.out.println("날짜: " + selectedDate + ", 일정: " + event);
                } else {
                    JOptionPane.showMessageDialog(CalendarApp.this, "날짜를 먼저 선택해주세요.");
                }
            }
        });
       
 
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalendarApp();
            }
        });
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

        String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        monthComboBox = new JComboBox<>(months);

        String[] years = new String[10];
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 10; i++) {
            years[i] = String.valueOf(currentYear + i);
        }
        yearComboBox = new JComboBox<>(years);
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
