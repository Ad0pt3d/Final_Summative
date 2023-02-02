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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JFrame {

   DefaultTableModel model = new DefaultTableModel();
   private JTable employeeTable;
   private JScrollPane scrollPane;
   private JButton addButton, updateWageButton, removeButton;
   private JLabel nameLabel, wageLabel, hoursLabel;
   private JTextField nameField, wageField, hoursField;


   public Main() {
       employeeTable = new JTable(model);

       model.addColumn("Employee Name");
       model.addColumn("Wage/Hr");
       model.addColumn("Hours Worked");
       model.addColumn("Total Pay");
       model.addColumn("Date");

       model.addRow(new Object[]{"Rafi", "15", "0", "0", "N/A"});
       model.addRow(new Object[]{"Peeker", "15", "0", "0", "N/A"});
       scrollPane = new JScrollPane(employeeTable);
       scrollPane.setPreferredSize(new Dimension(300, 150));

       nameLabel = new JLabel("Name:");
       nameField = new JTextField("Rafi", 10);

       wageLabel = new JLabel("Wage/Hr:");
       wageField = new JTextField("15", 10);

       hoursLabel = new JLabel("Hours Worked:");
       hoursField = new JTextField("5", 10);

       addButton = new JButton("Add");
       addButton.addActionListener(new AddButtonListener());

       removeButton = new JButton("Remove");
       removeButton.addActionListener(new RemoveButtonListener());

       updateWageButton = new JButton("Update");
       updateWageButton.addActionListener(new UpdateButtonListener());

       JPanel inputPanel = new JPanel();
       inputPanel.add(nameLabel);
       inputPanel.add(nameField);
       inputPanel.add(wageLabel);
       inputPanel.add(wageField);
       inputPanel.add(hoursLabel);
       inputPanel.add(hoursField);
       inputPanel.add(addButton);
       inputPanel.add(removeButton);
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
           Date date = new Date();
           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           String strDate = sdf.format(date);

           String name = nameField.getText();
           String wageHr = String.valueOf(wageField.getText());
           String hoursWorked = String.valueOf(hoursField.getText());

           boolean exists = checkIfExist();

           if (!exists) {
               model.addRow(new Object[]{name, wageHr, hoursWorked, TotalPay(Double.parseDouble(hoursWorked), Double.parseDouble(wageHr)), strDate});
           }

       }
   }

    private class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = sdf.format(date);

            String name = nameField.getText();
            String wageHr = String.valueOf(wageField.getText());
            String hoursWorked = String.valueOf(hoursField.getText());

            for (int row = 0; row < employeeTable.getRowCount(); row++) {
                if (name.equals(employeeTable.getValueAt(row, 0))) {
                    employeeTable.setValueAt(wageHr, row, 1);
                    employeeTable.setValueAt(hoursWorked, row, 2);
                    employeeTable.setValueAt(TotalPay(Double.parseDouble(hoursWorked), Double.parseDouble(wageHr)), row, 3);
                    employeeTable.setValueAt(strDate, row, 4);
                }
            }
        }
    }

    private class RemoveButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           String name = nameField.getText();

           for (int row = 0; row < employeeTable.getRowCount(); row++) {
               if (name.equals(employeeTable.getValueAt(row, 0))) {
                   model.removeRow(row);
               }
           }
       }
    }

   public boolean checkIfExist() {
       String name = nameField.getText();

       boolean exists = false;

       for (int row = 0; row < employeeTable.getRowCount(); row++) {
           if (name.equals(employeeTable.getValueAt(row, 0))) {
               JOptionPane.showMessageDialog(null, "Employee Already Exists");
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

   public double TotalPay(double hoursWorked, double wageHr) {
       double totalPay = (hoursWorked * wageHr);

       return totalPay;
   }


   public static void main(String[] args) {
        new Main();
   }

   public static void SaveToJSON() {

   }
}


