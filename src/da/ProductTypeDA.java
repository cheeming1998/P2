/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import entity.Customer;
import entity.Producttype;
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
public class ProductTypeDA {
      private String host = "jdbc:derby://localhost:1527/FioreFlower";
    private String user = "abcde";
    private String password = "abcde";
    private String tableName = "PRODUCTTYPE";
    private Connection conn;
    private PreparedStatement stmt;
    public ProductTypeDA(){
        createConnection();
        
    }
    public Producttype getRecord(String ID){
         String queryStr = "SELECT * FROM " + tableName + " WHERE PRODUCTTYPEID = ?";
        Producttype productTypeRecord = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
              while(rs.next()) {
              productTypeRecord = new Producttype(rs.getString("PRODUCTTYPEID"),rs.getString("PRODUCTTYPENAME"));
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return productTypeRecord;
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
