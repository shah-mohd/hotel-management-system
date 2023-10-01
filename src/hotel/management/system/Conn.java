
package hotel.management.system;

import java.sql.*;
//import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
    
        Connection con;
        Statement stmt;
        
    Conn(){
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "demo", "12345");
            stmt = con.createStatement();
//            System.out.println("all good");
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static void main(String args[]){
//        new Conn();
    }
}
