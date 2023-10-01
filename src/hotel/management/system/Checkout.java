

package hotel.management.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Checkout extends JFrame implements ActionListener{
  
    Choice ccustomer;
    JLabel lblroomnumber,lblcheckintime,lblcheckouttime;
    JButton checkout,back;
    
    Checkout(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        JLabel text = new JLabel("Checkout");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(100, 20, 100, 30);
        text.setForeground(Color.blue);
        add(text);
        
        JLabel lblid = new JLabel("Customer id");
        lblid.setBounds(30, 80, 100, 30);
        add(lblid);
        
        ccustomer = new Choice();
        ccustomer.setBounds(150, 80, 150, 25);
        add(ccustomer);
        
        
        
        //image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel tick = new JLabel(i3);
        tick.setBounds(310, 80, 20, 20);
        add(tick);
        
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 30);
        add(lblroom);
        
        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(150, 130, 100, 30);
        add(lblroomnumber);
        
        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setBounds(30, 180, 100, 30);
        add(lblcheckin);
        
        lblcheckintime = new JLabel();
        lblcheckintime.setBounds(150, 180, 100, 30);
        add(lblcheckintime);
        
        JLabel lblcheckout = new JLabel("Checkout Time");
        lblcheckout.setBounds(30, 230, 100, 30);
        add(lblcheckout);
        
        Date date = new Date();
        lblcheckouttime = new JLabel(""+ date);
        lblcheckouttime.setBounds(150, 230, 100, 30);
        add(lblcheckouttime);
        
        //buttons
        checkout = new JButton("Checkout");
        checkout.setBounds(30, 280, 120, 30);
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.addActionListener(this);
        add(checkout);
        
        back = new JButton("Back");
        back.setBounds(170, 280, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        
        try{            
            Conn c = new Conn();
            ResultSet rs = c.stmt.executeQuery("SELECT * FROM customer");
            while(rs.next()){
                ccustomer.add(rs.getString("number1"));
                lblroomnumber.setText(rs.getString("room"));
                lblcheckintime.setText(rs.getString("checkintime"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //image
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6);
        image.setBounds(350, 50, 400, 250);
        add(image);
        
        
        
        
        
        //        setBounds(300, 200, 1050, 600);
        setBounds(300, 100, 800, 400);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == checkout){
            
            String sql = "DELETE FROM customer WHERE number1 = '"+ccustomer.getSelectedItem()+"' ";
            String sql2 = "UPDATE room SET availability = 'Available' WHERE roomnumber = '"+lblroomnumber.getText()+"' ";
            
            try{
                Conn c = new Conn();
                c.stmt.executeUpdate(sql);
                c.stmt.executeUpdate(sql2);
                
                JOptionPane.showMessageDialog(null, "Checkout Done");
                setVisible(false);
                new Reception();
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else{
            setVisible(false);
            new Reception();
        }
        
    }
    
    
    
    public static void main(String args[]){
        new Checkout();
    }
    
}
