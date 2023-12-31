/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package foodordeingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author qiqi0
 */
public class ManagementMenu extends javax.swing.JFrame {

    /**
     * Creates new form CustomerMenu
     */
    public ManagementMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ManagementL = new javax.swing.JLabel();
        ShowAllUserB = new javax.swing.JButton();
        AddFoodB = new javax.swing.JButton();
        LogOutB = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Management Menu");
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        ManagementL.setText("Management Menu");
        getContentPane().add(ManagementL);
        ManagementL.setBounds(170, 60, 120, 20);

        ShowAllUserB.setText("Show All User");
        ShowAllUserB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllUserBActionPerformed(evt);
            }
        });
        getContentPane().add(ShowAllUserB);
        ShowAllUserB.setBounds(150, 110, 150, 30);

        AddFoodB.setText("Add Food");
        AddFoodB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddFoodBActionPerformed(evt);
            }
        });
        getContentPane().add(AddFoodB);
        AddFoodB.setBounds(150, 150, 150, 30);

        LogOutB.setText("Logout");
        LogOutB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutBActionPerformed(evt);
            }
        });
        getContentPane().add(LogOutB);
        LogOutB.setBounds(170, 210, 111, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ShowAllUserBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllUserBActionPerformed
      dispose();


       DB.getAllUser(); 

      
    }//GEN-LAST:event_ShowAllUserBActionPerformed

    private void AddFoodBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddFoodBActionPerformed
       dispose();
        AddFood addFoodB = new AddFood();
       addFoodB.setVisible(true);
       
       
    }//GEN-LAST:event_AddFoodBActionPerformed

    private void LogOutBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutBActionPerformed
        dispose();
        Login LoginPage = new Login();
        LoginPage.setVisible(true);
    }//GEN-LAST:event_LogOutBActionPerformed

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
            java.util.logging.Logger.getLogger(ManagementMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagementMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagementMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagementMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagementMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddFoodB;
    private javax.swing.JToggleButton LogOutB;
    private javax.swing.JLabel ManagementL;
    private javax.swing.JButton ShowAllUserB;
    // End of variables declaration//GEN-END:variables
}
