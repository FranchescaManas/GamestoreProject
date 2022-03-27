/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminForms;

/**
 *
 * @author Franchesca
 */

import static AdminForms.SqlConnection.url;
import ClientForms.CardInformation;
import ClientForms.ClientForm_JFrame;
import ClientForms.DisplayOwned;
import java.sql.*;
import java.util.logging.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.*;


public class Login_JFrame extends javax.swing.JFrame {

    
    //Declarations
    Connection connection ;
    Statement statement = null;
    ResultSet resultset = null;
    static String role;
    static String name;
    public static int id;
    

    
    public Login_JFrame() {
        initComponents();
        
        incorrectlbl.setVisible(false);
        //jbtnLogin.putClientProperty( "JButton.buttonType", "roundRect" );
        loginbtn.putClientProperty( "JButton.buttonType", "roundRect" );
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jtxtUsername = new javax.swing.JTextField();
        jtxtPassword = new javax.swing.JPasswordField();
        incorrectlbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        loginbtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanel_main.setBackground(new java.awt.Color(28, 37, 47));

        jPanel1.setBackground(new java.awt.Color(40, 49, 59));

        jtxtUsername.setBackground(new java.awt.Color(64, 77, 91));
        jtxtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtUsernameFocusGained(evt);
            }
        });
        jtxtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtUsernameActionPerformed(evt);
            }
        });

        jtxtPassword.setBackground(new java.awt.Color(64, 77, 91));
        jtxtPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtxtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtPasswordFocusGained(evt);
            }
        });
        jtxtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPasswordActionPerformed(evt);
            }
        });

        incorrectlbl.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        incorrectlbl.setForeground(new java.awt.Color(255, 51, 0));
        incorrectlbl.setText("Wrong username or password");

        jLabel1.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Password");

        loginbtn.setBackground(new java.awt.Color(102, 204, 255));
        loginbtn.setForeground(new java.awt.Color(255, 255, 255));
        loginbtn.setText("Log In");
        loginbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/256669696_1791306991069667_50788230320871552623_n.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(incorrectlbl)
                    .addComponent(jtxtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jtxtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(loginbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(incorrectlbl)
                .addGap(27, 27, 27)
                .addComponent(loginbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back_icon.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPanel_mainLayout = new javax.swing.GroupLayout(JPanel_main);
        JPanel_main.setLayout(JPanel_mainLayout);
        JPanel_mainLayout.setHorizontalGroup(
            JPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_mainLayout.createSequentialGroup()
                .addGroup(JPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanel_mainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPanel_mainLayout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(330, Short.MAX_VALUE))
        );
        JPanel_mainLayout.setVerticalGroup(
            JPanel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_mainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(57, 57, 57)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        getContentPane().add(JPanel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ClientForm_JFrame frame = new ClientForm_JFrame(null, 0);
        frame.show();
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtxtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtUsernameActionPerformed

    private void jtxtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPasswordActionPerformed

    private void jtxtUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtUsernameFocusGained
        // TODO add your handling code here:
        incorrectlbl.setVisible(false);
        
    }//GEN-LAST:event_jtxtUsernameFocusGained

    private void jtxtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtPasswordFocusGained
        // TODO add your handling code here:
        incorrectlbl.setVisible(false);
        
    }//GEN-LAST:event_jtxtPasswordFocusGained

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed
        // TODO add your handling code here:
        try 
        {
            
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
          
           url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
           
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
           connection = DriverManager.getConnection(url);
            
           
           String query = "select * from [user] where username = '"+ jtxtUsername.getText() +"' and password = '"+ jtxtPassword.getText()+"' ";
           
           
           statement = connection.createStatement();
           
           
           resultset = statement.executeQuery(query);
           
           //reiterates through the whole table???
           if (resultset.next()){
               System.out.println("login success");
               
               //getting role from database and assigning it to role variable
               role = resultset.getString("role");
               name = resultset.getString("username");
               id = resultset.getInt("userid");
               
               // verifies if user is a client or admin
               
               
               if (role.equals("Admin"))
               {
                   Admin_JFrame admin = new Admin_JFrame(name, id);
                   admin.show();
                   admin.namelbl.setText(name);
                   incorrectlbl.setVisible(false);
                   this.dispose();
               }
               else if (role.equals("Client"))
               {
                   ClientForm_JFrame client = new ClientForm_JFrame(name, id);
                   CardInformation info = new CardInformation(id);
                   CardInformation info2 = new CardInformation(name);
                   
                   
                   client.show();
                   client.namelbl.setText(name);
                   DisplayOwned.userid2 = id;
                   
                   incorrectlbl.setVisible(false);
                   
                   client.dispose();
                   
                   
                   client.show();
                   client.namelbl.setText(name);
                   DisplayOwned.userid2 = id;
                   
                   incorrectlbl.setVisible(false);
                   this.dispose();
               }
               else
               {
                   System.out.println("role has not been set");
               }
               
           }
           else
           {
               System.out.println("login failed");
               jtxtUsername.setText("");
               jtxtPassword.setText("");
               incorrectlbl.setVisible(true);
           }
        }
        
        
        
        catch(SQLException sqle) 
        {
            System.out.println("Sql Exception :"+sqle.getMessage());
            System.out.println(sqle);
        } 
        catch (ClassNotFoundException ex) {
            
            Logger.getLogger(Login_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Login_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loginbtnActionPerformed

    
    //=========================================== methods ========================================
    
    

    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
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
            java.util.logging.Logger.getLogger(Login_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel JPanel_main;
    private javax.swing.JLabel incorrectlbl;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jtxtPassword;
    private javax.swing.JTextField jtxtUsername;
    private javax.swing.JButton loginbtn;
    // End of variables declaration//GEN-END:variables
}
