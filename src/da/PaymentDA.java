/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import entity.Payment;
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
public class PaymentDA {
     private String host = "jdbc:derby://localhost:1527/FioreFlower";
    private String user = "abcde";
    private String password = "abcde";
    private String tableName = "PAYMENT";
    private Connection conn;
    private PreparedStatement stmt;
    CustOrderDA custOrderDA = new CustOrderDA();
    
    public PaymentDA(){
        createConnection();
    }
    
    public Payment getRecordByCustorderID(String ID){
        String queryStr = "SELECT * FROM " + tableName + " WHERE CUSTORDERID = ?";
        Payment paymentRecord = null;
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                
                paymentRecord = new Payment(rs.getString("PAYMENTID"),rs.getDate("PAYMENTDATE"),custOrderDA.getRecord(rs.getString("CUSTORDERID")),rs.getDouble("AMOUNT"));
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return paymentRecord;
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
