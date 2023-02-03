/*
 *Program Name: Employee Wage Counter
 *Programmer Name: Rafi Miah
 *Program Date: February 2, 2023
 *
 *Program Description:
 *This program will add employees to a list displayed by GUI
 *with the employee's name, wage per hour, and how many hours worked.
 *The program will then calculate the total pay of the employee on the current date the employee was added.
 *The user will also be able to remove the user or update the wage and hours worked if needed.
 *
 *Inputs:
 *The user will be able to input employee name, wage per hour and hours worked using the GUI
 *and click buttons to add employees, remove employees and update the employees.
 *
 *Output:
 *The output of the program will be the GUI displayed
 *a table with 5 columns and a list accompanying those columns will be displayed
 *text labeling fields that can be written in and buttons to click
 */


// imports
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// Main class inherits class JFrame
public class Main extends JFrame {

   // Variables for GUI
   DefaultTableModel model = new DefaultTableModel();
   private JTable employeeTable;
   private JScrollPane scrollPane;
   private JButton addButton, updateWageButton, removeButton;
   private JLabel nameLabel, wageLabel, hoursLabel;
   private JTextField nameField, wageField, hoursField;


   // Main method
   public Main() {
       // Creating the table for employees
       employeeTable = new JTable(model);

       // Adding columns to table
       model.addColumn("Employee Name");
       model.addColumn("Wage/Hr");
       model.addColumn("Hours Worked");
       model.addColumn("Total Pay");
       model.addColumn("Date");

       // Adding starter employees
       model.addRow(new Object[]{"Rafi", "15", "8", "120", "N/A"});
       model.addRow(new Object[]{"Tahir", "25", "7", "75", "N/A"});

       // A window containing the table
       scrollPane = new JScrollPane(employeeTable);
       scrollPane.setPreferredSize(new Dimension(300, 150));

       // Text and text field for employee name
       nameLabel = new JLabel("Name:");
       nameField = new JTextField("Rafi", 10);

       // Text and text field for wage per hour
       wageLabel = new JLabel("Wage/Hr:");
       wageField = new JTextField("15", 10);

       //Text and text field for hours worked
       hoursLabel = new JLabel("Hours Worked:");
       hoursField = new JTextField("8", 10);

       // A button to add employees
       addButton = new JButton("Add");
       addButton.addActionListener(new AddButtonListener()); // When button is pressed call function AddButtonListener

       // A button to remove employees
       removeButton = new JButton("Remove");
       removeButton.addActionListener(new RemoveButtonListener()); // When button is pressed call function RemoveButtonListener

       // A button to update employees
       updateWageButton = new JButton("Update");
       updateWageButton.addActionListener(new UpdateButtonListener()); // When button is pressed call function UpdateButtonListener

       // A panel to add all the labels, fields and buttons to the panel
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


       add(scrollPane, BorderLayout.CENTER); // Add the scrollPane window to the screen with center layout
       add(inputPanel, BorderLayout.SOUTH);// Add the panel window to the screen with south layout

       setTitle("Employee Wage Counter"); // Sets title of program
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when the x is clicked on the program close it
       pack(); // Sets all contents to its size or bigger
       setVisible(true); // Makes everything visible
   }

   // Button Listener Class
   private class AddButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           // Variable to manage current date
           Date date = new Date();
           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           String strDate = sdf.format(date);

           // Variables to grab what is in the text field
           String name = nameField.getText();
           String wageHr = String.valueOf(wageField.getText());
           String hoursWorked = String.valueOf(hoursField.getText());

           // Adds a row to the table with all the variables
           model.addRow(new Object[]{name, wageHr, hoursWorked, TotalPay(Double.parseDouble(hoursWorked), Double.parseDouble(wageHr)), strDate});
       }
   }

    // Update Listener class
    private class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Variable to manage current date
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = sdf.format(date);

            // Variables to grab what is in the text field
            String name = nameField.getText();
            String wageHr = String.valueOf(wageField.getText());
            String hoursWorked = String.valueOf(hoursField.getText());

            // Loops through the table and if the name field matches in the table update the values
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

    // Remove Listener Class
    private class RemoveButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           // Variables to grab what is in the text field
           String name = nameField.getText();

           // Loops through the table and if field matches remove row
           for (int row = 0; row < employeeTable.getRowCount(); row++) {
               if (name.equals(employeeTable.getValueAt(row, 0))) {
                   model.removeRow(row);
               }
           }
       }
    }

   // Total Pay Class
   public double TotalPay(double hoursWorked, double wageHr) {
       // Variable to calculate the total pay using hours worked and wage per hour
       double totalPay = (hoursWorked * wageHr);

       // returns the amount of total pay
       return totalPay;
   }


   // Calls main
   public static void main(String[] args) {
        new Main();
   }

}


