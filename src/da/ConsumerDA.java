/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import entity.Consumer;
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
public class ConsumerDA {
      private String host = "jdbc:derby://localhost:1527/FioreFlower";
    private String user = "abcde";
    private String password = "abcde";
    private String tableName = "CONSUMER";
    private Connection conn;
    private PreparedStatement stmt;
    public ConsumerDA(){
        createConnection();
    }
    public Consumer getRecord(String ID){
         String queryStr = "SELECT * FROM " + tableName + " WHERE CUSTOMERID = ?";
         Consumer consumerRecord = null;
         CustomerDA customerDA = new CustomerDA();
         try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                consumerRecord = new Consumer(rs.getString("CUSTOMERID"),customerDA.getRecord(rs.getString("CUSTOMERID")),rs.getString("FULLNAME"),rs.getDate("DATEOFBIRTH"));
            }
            }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
         
          return consumerRecord;
        
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
