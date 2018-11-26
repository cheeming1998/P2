/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import entity.Custorder;
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
public class CustOrderDA {
       private String host = "jdbc:derby://localhost:1527/FioreFlower";
    private String user = "abcde";
    private String password = "abcde";
    private String tableName = "CUSTORDER";
    private Connection conn;
    private PreparedStatement stmt;
    CustomerDA custDA = new CustomerDA();
    static CustOrderDA custoDA = new CustOrderDA();
    public CustOrderDA(){
        createConnection();
    }
     public List<Custorder> getAllRecord(){
         String queryStr = "SELECT * FROM " + tableName;
          List<Custorder> custOrderList = new ArrayList<>();
          
          Custorder custOrder = null;
          try{
              stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
              custOrder = new Custorder(rs.getString("CUSTORDERID"), rs.getDate("ORDERDATE"),rs.getDate("DATEDELIVERY"),custDA.getRecord(rs.getString("CUSTOMERID")),rs.getString("STATUS"),rs.getDouble("TOTAL"),rs.getString("DELIVERYADDRESS"),rs.getDouble("DISTANCE"),rs.getDate("PICKUPTIME"));
              custOrderList.add(custOrder);
            }
          }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
          return custOrderList;
     }
     public Custorder getRecord(String ID){
          String queryStr = "SELECT * FROM " + tableName + " WHERE CUSTORDERID = ?";
        Custorder custorderRecord = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
              custorderRecord = new Custorder(rs.getString("CUSTORDERID"), rs.getDate("ORDERDATE"),rs.getDate("DATEDELIVERY"),custDA.getRecord(rs.getString("CUSTOMERID")),rs.getString("STATUS"),rs.getDouble("TOTAL"),rs.getString("DELIVERYADDRESS"),rs.getDouble("DISTANCE"),rs.getDate("PICKUPTIME"));
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return custorderRecord;
    }
     
     public boolean UpdatePickupRecord(Custorder custOrder){
         boolean success=false;
          try{
        String insertStr="UPDATE "+tableName+" SET PICKUPTIME = ?, STATUS = 'PICKUPED' WHERE CUSTORDERID = ?";
        stmt=conn.prepareStatement(insertStr);
        stmt.setDate(1, new java.sql.Date(custOrder.getPickuptime().getTime()));
        stmt.setString(2, custOrder.getCustorderid());
        stmt.executeUpdate();
        success=true;
        
          }catch(SQLException ex){
              JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
         }
          return success;
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
