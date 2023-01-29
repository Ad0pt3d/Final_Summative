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
import java.util.ArrayList;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDateTime;

public class Main extends JFrame {

   public Main() {
        JButton button1 = new JButton("click");
        button1.setBounds(100, 100, 100, 30);

        add(button1);

        setTitle("Employee Wage Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        pack();
        setVisible(true);
   }

   public static void main(String[] args) {
        new Main();
   }
}

class Employee {
    String name;
    int id;

}