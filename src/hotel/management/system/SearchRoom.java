
package hotel.management.system;

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

public class SearchRoom extends JFrame implements ActionListener{
    
    JTable table;
    JButton submit, back;
    JComboBox bedtype;
    JCheckBox available;
    
    SearchRoom(){        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel("Search for Room");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(400, 30, 200, 30);
        add(text);
        
        JLabel lblbed = new JLabel("Bed Type");
        lblbed.setBounds(50, 100, 100, 20);
        add(lblbed);
        
        bedtype = new JComboBox(new String[]{"Single Bed", "Double Bed"});
        bedtype.setBounds(150, 100, 150, 25);
        bedtype.setBackground(Color.white);
        add(bedtype);
        
        available = new JCheckBox("Only Display Available");
        available.setBounds(650, 100, 150, 25);
        available.setBackground(Color.white);
        add(available);
        
        //labels
        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(50, 160, 100, 20);
        add(l1);
        
        JLabel l2 = new JLabel("Availibility");
        l2.setBounds(270, 160, 100, 20);
        add(l2);
        
        JLabel l3 = new JLabel("Cleaning Status");
        l3.setBounds(450, 160, 100, 20);
        add(l3);
        
        JLabel l4 = new JLabel("Price");
        l4.setBounds(670, 160, 100, 20);
        add(l4);
        
        JLabel l5 = new JLabel("Bed Type");
        l5.setBounds(870, 160, 100, 20);
        add(l5);
        
        
        //table
        table = new JTable();
        table.setBounds(0, 200, 1000, 300);
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.stmt.executeQuery("SELECT * FROM room");
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
                 String sql = "SELECT * FROM room WHERE bed_type = '"+bedtype.getSelectedItem()+"' ";
                 String sql2 = "SELECT * FROM room WHERE availability = 'Available' AND bed_type = '"+bedtype.getSelectedItem()+"' ";
                 
                 Conn conn = new Conn();
                 ResultSet rs;
                 if(available.isSelected()){
                    rs = conn.stmt.executeQuery(sql2);
                 }else{
                    rs = conn.stmt.executeQuery(sql); 
                 }
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
        new SearchRoom();
    }
}
