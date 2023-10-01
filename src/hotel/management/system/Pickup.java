
package hotel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import net.proteanit.sql.*;

public class Pickup extends JFrame implements ActionListener{
    
    JTable table;
    JButton submit, back;
    Choice typeofcar;
    JCheckBox available;
    
    Pickup(){        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel("Pickup Service");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(400, 30, 200, 30);
        add(text);
        
        JLabel lblbed = new JLabel("Type of Car");
        lblbed.setBounds(50, 100, 100, 20);
        add(lblbed);
        
        typeofcar = new Choice();
        typeofcar.setBounds(150, 100, 200, 25);
        add(typeofcar);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.stmt.executeQuery("SELECT * FROM driver");
            while(rs.next()){
                typeofcar.add(rs.getString("brand"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //labels
        JLabel l1 = new JLabel("Name");
        l1.setBounds(30, 160, 100, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Age");
        l2.setBounds(200, 160, 100, 20);
        add(l2);
        
        JLabel l3 = new JLabel("Gender");
        l3.setBounds(330, 160, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Company");
        l4.setBounds(460, 160, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("Brand");
        l5.setBounds(630, 160, 100, 20);
        add(l5);
        
        JLabel l6 = new JLabel("Availability");
        l6.setBounds(740, 160, 100, 20);
        add(l6);
        
        JLabel l7 = new JLabel("Location");
        l7.setBounds(890, 160, 100, 20);
        add(l7);
        
        
        //table
        table = new JTable();
        table.setBounds(0, 200, 1000, 300);
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.stmt.executeQuery("SELECT * FROM driver");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        //buttons
        submit = new JButton("Submit");
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.setBounds(300, 520, 120, 30);
        submit.addActionListener(this);
        add(submit);
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(500, 520, 120, 30);
        back.addActionListener(this);
        add(back);
        
        //        setBounds(300, 200, 1050, 600);
        setBounds(300, 100, 1000, 600);
        setVisible(true);
    }
    
     public void actionPerformed(ActionEvent ae){
         if(ae.getSource() == submit){
             
             try{
                 String sql = "SELECT * FROM driver WHERE brand = '"+typeofcar.getSelectedItem()+"' ";
                 
                 Conn conn = new Conn();
                 ResultSet rs;
                 rs = conn.stmt.executeQuery(sql);
                 table.setModel(DbUtils.resultSetToTableModel(rs));
                 
                 
             }catch(Exception e){
                 e.printStackTrace();
             }
                          
         } else{
         setVisible(false);
         new Reception();
         }
     }
    
    //main method
    public static void main(String args[]){
        new Pickup();
    }
}
