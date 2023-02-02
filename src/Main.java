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

       model.addRow(new Object[]{"Rafi", "15", "0", "0"});
       model.addRow(new Object[]{"Peeker", "15", "0", "0"});
       scrollPane = new JScrollPane(employeeTable);
       scrollPane.setPreferredSize(new Dimension(300, 150));

       nameLabel = new JLabel("Name:");
       nameField = new JTextField(10);

       wageLabel = new JLabel("Wage/Hr:");
       wageField = new JTextField(10);

       hoursLabel = new JLabel("Hours Worked:");
       hoursField = new JTextField(10);

       addButton = new JButton("Add");
       addButton.addActionListener(new AddButtonListener());

       removeButton = new JButton("Remove");
       removeButton.addActionListener(new RemoveButtonListener());

       updateWageButton = new JButton("Update Wage");
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
           String name = nameField.getText();
           double wageHr = Double.parseDouble(wageField.getText());
           double hoursWorked = Double.parseDouble(hoursField.getText());

           boolean exists = checkIfExist();

           if (!exists) {
               model.addRow(new Object[]{name, wageHr, hoursWorked, TotalPay(hoursWorked, wageHr)});
           }

       }
   }

    private class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            Double wageHr = Double.parseDouble(wageField.getText());
            Double hoursWorked = Double.parseDouble(hoursField.getText());

            for (int row = 0; row < employeeTable.getRowCount(); row++) {
                if (name.equals(employeeTable.getValueAt(row, 0))) {
                    employeeTable.setValueAt(wageHr, row, 1);
                    employeeTable.setValueAt(hoursWorked, row, 2);
                    employeeTable.setValueAt(TotalPay(hoursWorked, wageHr), row, 3);
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


