
package hotel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class UpdateRoom extends JFrame implements ActionListener{
    
    Choice ccustomer;
    JTextField tfroom, tfavailable, tfstatus, tfpaid, tfpending;
    JButton check, update, back;
    
    UpdateRoom(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel("Update Room Status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 25));
        text.setBounds(30, 20, 250, 30);
        text.setForeground(Color.blue);
        add(text);
        
        
        JLabel lblid = new JLabel("Customer ID");
        lblid.setBounds(30, 80, 100, 20);
        add(lblid);
        
        ccustomer = new Choice();
        ccustomer.setBounds(200, 80, 150, 25);
        add(ccustomer);
        
        try{            
            Conn c = new Conn();
            ResultSet rs = c.stmt.executeQuery("SELECT * FROM customer");
            while(rs.next()){
                ccustomer.add(rs.getString("number1"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 20);
        add(lblroom);
        
        tfroom = new JTextField();
        tfroom.setBounds(200, 130, 150, 25);
        add(tfroom);
        
        JLabel lblavailable = new JLabel("Availability");
        lblavailable.setBounds(30, 180, 100, 20);
        add(lblavailable);
        
        tfavailable = new JTextField();
        tfavailable.setBounds(200, 180, 150, 25);
        add(tfavailable);
        
        JLabel lblcheckin = new JLabel("Cleaning Status");
        lblcheckin.setBounds(30, 230, 100, 20);
        add(lblcheckin);
        
        tfstatus = new JTextField();
        tfstatus.setBounds(200, 230, 150, 25);
        add(tfstatus);
        
        //buttons
        check = new JButton("Check");
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.setBounds(30, 300, 100, 30);
        check.addActionListener(this);
        add(check);
        
        update = new JButton("Update");
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.setBounds(150, 300, 100, 30);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(270, 300, 100, 30);
        back.addActionListener(this);
        add(back);
        
        //image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);
        
        
        
        //        setBounds(300, 200, 1050, 600);
        setBounds(300, 100, 980, 450);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
         if(ae.getSource() == check){
             String id = ccustomer.getSelectedItem();
             String sql = "SELECT * FROM customer WHERE number1 = '"+id+"' ";
             try{
                 Conn c = new Conn();
                 ResultSet rs = c.stmt.executeQuery(sql);
                 while(rs.next()){
                     tfroom.setText(rs.getString("room"));
                 }
                 
                 ResultSet rs2 = c.stmt.executeQuery("SELECT * FROM room WHERE roomnumber = '"+tfroom.getText()+"' ");
                 while(rs2.next()){
                     tfavailable.setText(rs2.getString("availability"));
                     tfstatus.setText(rs2.getString("cleaning_status"));
                 }
                 
             }catch(Exception e){
                 e.printStackTrace();
             }
             
         } else if(ae.getSource() == update){
             String number = ccustomer.getSelectedItem();
             String room = tfroom.getText();
             String available = tfavailable.getText();
             String status = tfstatus.getText();
             
             try{
                 Conn c = new Conn();
                 c.stmt.executeUpdate("UPDATE room SET availability='"+available+"',cleaning_status='"+status+"' WHERE roomnumber = '"+room+"' ");
                 
                 JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                 setVisible(false);
                 new Reception();
                 
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
        new UpdateRoom();
    }
}
