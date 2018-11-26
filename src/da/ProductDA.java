/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import entity.Product;
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
public class ProductDA {
      private String host = "jdbc:derby://localhost:1527/FioreFlower";
    private String user = "abcde";
    private String password = "abcde";
    private String tableName = "PRODUCT";
    private Connection conn;
    private PreparedStatement stmt;
    public ProductDA(){
        createConnection();
        
    }
    public List<Product> getAllRecord(){
        String queryStr = "SELECT * FROM " + tableName;
        List<Product> productList = new ArrayList<Product>();
        ProductTypeDA productTypeDA = new ProductTypeDA();
        Product product=null;
         try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
              product = new Product(rs.getString("PRODUCTID"),rs.getString("DESCRIPTION"),productTypeDA.getRecord(rs.getString("PRODUCTTYPEID")),rs.getInt("QUANTITY"),rs.getDouble("PRICE"));
              productList.add(product);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return productList;
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
