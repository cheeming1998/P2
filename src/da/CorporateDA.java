/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import entity.Consumer;
import entity.Corporate;
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
public class CorporateDA {
     private String host = "jdbc:derby://localhost:1527/FioreFlower";
    private String user = "abcde";
    private String password = "abcde";
    private String tableName = "CORPORATE";
    private Connection conn;
    private PreparedStatement stmt;
    public CorporateDA(){
        createConnection();
    }
     public Corporate getRecord(String ID){
         String queryStr = "SELECT * FROM " + tableName + " WHERE CUSTOMERID = ?";
         Corporate corporateRecord =null;
          CustomerDA customerDA = new CustomerDA();
          try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                corporateRecord = new Corporate(rs.getString("CUSTOMERID"),customerDA.getRecord(rs.getString("CUSTOMERID")),rs.getString("COMPANYNAME"),rs.getString("COMPANYADDRESS"),rs.getDouble("MONTHLYLIMIT"));
            }
            }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
         
          return corporateRecord;
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
