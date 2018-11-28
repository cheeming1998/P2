/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;
import da.ConsumerDA;
import da.CustOrderDA;
import entity.*;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author waikang
 */
public class DeliveryList extends javax.swing.JFrame {
 @PersistenceContext
   EntityManager em;
 
    /**
     * Creates new form DeliveryList
     */
    public DeliveryList() {
        initComponents();
        displayDeliveryOrder();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FioreFlowerShopPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("FioreFlowerShopPU").createEntityManager();
        custorderQuery = java.beans.Beans.isDesignTime() ? null : FioreFlowerShopPUEntityManager.createQuery("SELECT c FROM Custorder c");
        custorderList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : custorderQuery.getResultList();
        custorderQuery1 = java.beans.Beans.isDesignTime() ? null : FioreFlowerShopPUEntityManager.createQuery("SELECT c FROM Custorder c");
        custorderList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : custorderQuery1.getResultList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("Daily Delivery List");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Customer Name", "Order ID", "Delivery Address", "Distance"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(68, 68, 68))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DeliveryList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeliveryList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeliveryList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeliveryList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeliveryList().setVisible(true);
            }
        });
    }
    public void displayDeliveryOrder(){
    Custorder custOrder = new Custorder();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    Object rowData[] = new Object[5];
    CustOrderDA custOrderDA = new CustOrderDA();
    boolean found=false;
    ConsumerDA consumerDA = new ConsumerDA();
    List<Custorder> custOrderList = custOrderDA.getAllRecord();
 
     for (Custorder custOrderList1 : custOrderList) {
         
         if(checkDate(custOrderList1.getDatedelivery())&&(custOrderList1.getStatus()).toUpperCase().equals("DELIVERY")){
              rowData[0] = custOrderList1.getCustomerid().getCustomerid();
              rowData[1] = consumerDA.getRecord(custOrderList1.getCustomerid().getCustomerid());
              rowData[2] = custOrderList1.getCustorderid();
              rowData[3] = custOrderList1.getDeliveryaddress();
              rowData[4] = custOrderList1.getDistance();
              model.addRow(rowData);
              found=true;
         }
     
         
     }
     if(found==false){
         JOptionPane.showMessageDialog(null, "No Delivery Order Today.");
    }
     
}
    public boolean checkDate(Date deliveryDate){
        Date todayDate = new Date();
        boolean date;
        if(deliveryDate.getYear()== todayDate.getYear()){
            if(deliveryDate.getMonth() == todayDate.getMonth() ){
                if(deliveryDate.getDate() == todayDate.getDate() ){
                    date = true;
                }else{
                    date = false;
                }
            }else{
                date=false;
            }
        }else{
            date=false;
        }
        return date;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager FioreFlowerShopPUEntityManager;
    private java.util.List<entity.Custorder> custorderList;
    private java.util.List<entity.Custorder> custorderList1;
    private javax.persistence.Query custorderQuery;
    private javax.persistence.Query custorderQuery1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
