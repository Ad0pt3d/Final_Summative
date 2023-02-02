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
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main extends JFrame {

   DefaultTableModel model = new DefaultTableModel();
   private JTable employeeTable;
   private JScrollPane scrollPane;
   private JButton addButton, updateWageButton;
   private JLabel nameLabel, wageLabel, hoursLabel, totalLabel;
   private JTextField nameField, wageField;


   public Main() {
       employeeTable = new JTable(model);

       model.addColumn("Employee Name");
       model.addColumn("Wage/Hr");
       model.addColumn("Hours Worked");
       model.addColumn("Total Pay");

       model.addRow(new Object[]{"Rafi", "15", "0", "0"});
       model.addRow(new Object[]{"Peeker", "15", "0", "0"});
       scrollPane = new JScrollPane(employeeTable);
       scrollPane.setPreferredSize(new Dimension(300, 150));

       nameLabel = new JLabel("Name:");
       nameField = new JTextField(10);

       wageLabel = new JLabel("Wage/Hr:");
       wageField = new JTextField(10);

       addButton = new JButton("Add");
       addButton.addActionListener(new AddButtonListener());

       updateWageButton = new JButton("Update Wage");
       updateWageButton.addActionListener(new UpdateWageButtonListener());

       JPanel inputPanel = new JPanel();
       inputPanel.add(nameLabel);
       inputPanel.add(nameField);
       inputPanel.add(wageLabel);
       inputPanel.add(wageField);
       inputPanel.add(addButton);
       inputPanel.add(updateWageButton);


       add(scrollPane, BorderLayout.CENTER);
       add(inputPanel, BorderLayout.SOUTH);

       setTitle("Employee Wage Counter");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       pack();
       setVisible(true);
   }

   private class AddButtonListener implements  ActionListener {
       public void actionPerformed(ActionEvent e) {
           String name = nameField.getText();
           String wageHr = wageField.getText();

           boolean exists = checkIfExist();

           if (!exists) {
               model.addRow(new Object[]{name, wageHr, "0", "0"});
           }

       }
   }

    private class UpdateWageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String wageHr = wageField.getText();

            for (int row = 0; row < employeeTable.getRowCount(); row++) {
                if (name.equals(employeeTable.getValueAt(row, 0))) {
                    employeeTable.setValueAt(wageHr, row, 1);
                }
            }
        }
    }

   public boolean checkIfExist() {
       String name = nameField.getText();

       boolean exists = false;

       for (int row = 0; row < employeeTable.getRowCount(); row++) {
           if (name.equals(employeeTable.getValueAt(row, 0))) {
               System.out.println("Employee Already Exists");
               exists = true;
           }
       }
       if (exists) {
           return true;
       }
       else {
           return false;
       }
   }


   public static void main(String[] args) {
        new Main();
   }

   public static void SaveToJSON() {

   }
}


