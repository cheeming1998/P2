/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import entity.Staff;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author waikang
 */
public class StaffDA {
      private String host = "jdbc:derby://localhost:1527/FioreFlower";
    private String user = "abcde";
    private String password = "abcde";
    private String tableName = "Staff";
    private Connection conn;
    private PreparedStatement stmt;
    static StaffDA staff = new StaffDA();
    public StaffDA(){
         createConnection();
    }
    
    public List<Staff> getAllRecord(){
        String queryStr = "SELECT * FROM " + tableName;
         List<Staff> staffList = new ArrayList<Staff>();
           Staff staffRecord = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
              
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return staffList;
    }
     public static void main(String[] args) {
         List<Staff> staffList = staff.getAllRecord();
        // TODO code application logic here
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
