
package hotel.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import net.proteanit.sql.*;

public class Department extends JFrame implements ActionListener{
    
    JTable table;
    JButton back;
    
    Department(){        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        //labels
        JLabel l1 = new JLabel("Department");
        l1.setBounds(180, 10, 100, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Budget");
        l2.setBounds(420, 10, 100, 20);
        add(l2);
        
        
        //table
        table = new JTable();
        table.setBounds(0, 50, 700, 350);
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.stmt.executeQuery("SELECT * FROM department");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(280, 400, 120, 30);
        back.addActionListener(this);
        add(back);
        
        //        setBounds(300, 200, 1050, 600);
        setBounds(400, 200, 700, 480);
        setVisible(true);
    }
    
     public void actionPerformed(ActionEvent ae){
         setVisible(false);
         new Reception();
     }
    
    //main method
    public static void main(String args[]){
        new Department();
    }
}
