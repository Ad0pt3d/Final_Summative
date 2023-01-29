/*
 *
 *
 *
 *
 *
 *
 *
 *
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JFrame {

    private JTable employeeTable;
    private JScrollPane scrollPane;
    private JButton clockInButton, clockOutButton;
    private JLabel nameLabel, wageLabel, clockInLabel1, clockInLabel2, clockOutLabel1, clockOutLabel2, hoursLabel, totalLabel;
    private JTextField nameField, wageField;


    public Main() {
       String[] columnNames = {"Name", "Wage/Hr", "Clock In", "Clock Out", "Hours Worked", "Total Pay"};
       String[][] data = {
               {"Rafi", "15", "00:00", "00:00", "0", "90"},
               {"Peek", "10", "00:00", "00:00", "0", "60"},
               {"Taror", "20", "00:00", "00:00", "0", "60"},
       };
       employeeTable = new JTable(data, columnNames);

       scrollPane = new JScrollPane(employeeTable);
       scrollPane.setPreferredSize(new Dimension(300, 150));

       nameLabel = new JLabel("Name:");
       nameField = new JTextField(10);

       wageLabel = new JLabel("Wage/Hr:");
       wageField = new JTextField(10);

       clockInLabel1 = new JLabel("Clock In:");
       clockInLabel2 = new JLabel();

       clockOutLabel1 = new JLabel("Clock Out:");
       clockOutLabel2 = new JLabel();

       clockInButton = new JButton("Clock In");
       clockInButton.addActionListener(new ClockInButtonListener());

       clockOutButton = new JButton("Clock Out");
       clockOutButton.addActionListener(new ClockOutButtonListener());

       JPanel inputPanel = new JPanel();
       inputPanel.add(nameLabel);
       inputPanel.add(nameField);
       inputPanel.add(wageLabel);
       inputPanel.add(wageField);
       inputPanel.add(clockInLabel1);
       inputPanel.add(clockInButton);
       inputPanel.add(clockOutLabel1);
       inputPanel.add(clockOutButton);


       add(scrollPane, BorderLayout.CENTER);
       add(inputPanel, BorderLayout.SOUTH);

       setTitle("Employee Wage Counter");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       pack();
       setVisible(true);
    }

   private class ClockInButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            String time = sdf.format(date);

            String name = nameField.getText();
            String clockIn = time;

            for (int row = 0; row < employeeTable.getRowCount(); row++) {
                if (name.equals(employeeTable.getValueAt(row, 0))) {
                    employeeTable.setValueAt(clockIn, row, 1);
                }
            }
        }
   }

   private class ClockOutButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
           Date date = new Date();
           String time = sdf.format(date);

           String name = nameField.getText();
           String clockOut = time;

           for (int row = 0; row < employeeTable.getRowCount(); row++) {
               if (name.equals(employeeTable.getValueAt(row, 0))) {
                   employeeTable.setValueAt(clockOut, row, 2);
               }
           }
       }
   }

   public static void main(String[] args) {
        new Main();
   }
}

class Employee {
    String name;
    float wage;
    float total;

}