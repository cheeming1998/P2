/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop;

import da.ConsumerDA;
import da.CustOrderDA;
import da.PaymentDA;
import entity.Consumer;
import entity.Custorder;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author waikang
 */
public class PickupList extends javax.swing.JFrame {

    /**
     * Creates new form PickupList
     */
    public PickupList() {
        initComponents();
        displayDeliveryOrder();
    }
public void displayDeliveryOrder(){
    Custorder custOrder = new Custorder();
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    Object rowData[] = new Object[4];
    CustOrderDA custOrderDA = new CustOrderDA();
   ConsumerDA consumerDA = new ConsumerDA();
   boolean found = false;

    List<Custorder> custOrderList = custOrderDA.getAllRecord();
     for (Custorder custOrderList1 : custOrderList) {
         
         if(checkDate(custOrderList1.getDatedelivery())&&custOrderList1.getStatus().equals("PICKUP")){
            Consumer consumer = consumerDA.getRecord(custOrderList1.getCustomerid().getCustomerid());
            
              rowData[0] = custOrderList1.getCustomerid().getCustomerid();
              rowData[1] = consumer.getFullname();
              rowData[2] = custOrderList1.getCustorderid();
              rowData[3] = custOrderList1.getDatedelivery();
              model.addRow(rowData);
              found = true;
         }
     }
      if(found==false){
         JOptionPane.showMessageDialog(null, "No Pickup Order Today.");
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PICKUP");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID   ", "Customer Name", "Order ID", "Date Pick-up"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Pick Up");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            int selectedRowIndex = jTable1.getSelectedRow();    
            jTextField1.setText(model.getValueAt(selectedRowIndex, 2).toString());
            
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           String ID= jTextField1.getText();     
           CustOrderDA custOrderDA = new CustOrderDA();
           Custorder custOrder = new Custorder();
           custOrder = custOrderDA.getRecord(ID);
           Date date;
        date = new Date();
           custOrder.setPickuptime(date);
           if(jTextField1.getText()==null){
               JOptionPane.showMessageDialog(null, "Order ID cannot be empty.");
           }else{
           if(custOrderDA.UpdatePickupRecord(custOrder)){
                 JOptionPane.showMessageDialog(null, "Record has been updated.");
                 DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                 model.getDataVector().removeAllElements();
                 model.fireTableDataChanged();
                 displayDeliveryOrder();
           }
           }
           // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(PickupList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PickupList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PickupList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PickupList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PickupList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
