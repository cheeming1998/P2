/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import entity.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author waikang
 */
public class CustomerDA {
       private String host = "jdbc:derby://localhost:1527/FioreFlower";
    private String user = "abcde";
    private String password = "abcde";
    private String tableName = "Customer";
    private Connection conn;
    private PreparedStatement stmt;
  public static CustomerDA custDA = new CustomerDA();
    public CustomerDA(){
        createConnection();
    }
    public Customer getRecord(String ID){
        String queryStr = "SELECT * FROM " + tableName + " WHERE CUSTOMERID = ?";
        Customer customerRecord = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customerRecord = new Customer(rs.getString("CUSTOMERID"),rs.getString("USERNAME"),rs.getString("PASSWORD"),rs.getString("CONTACTNO"), rs.getString("EMAIL"),rs.getDate("DATEJOINED"));
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return customerRecord;
    }
     public static void main(String args[]) {
         Customer cust=custDA.getRecord("1");
         System.out.println(cust.getContactno());
     }
    
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
