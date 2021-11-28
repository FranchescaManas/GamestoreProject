/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientForms;

import static AdminForms.Admin_JFrame.namelbl;
import AdminForms.Login_JFrame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Franchesca
 */
public class ClientForm_JFrame extends javax.swing.JFrame {

    CardLayout cardlayout;
        
    String text = "Store";
    
    static int userid = 0;
    static String username = null;
    
    public ClientForm_JFrame(String name, int id) {
        username = name;
        userid= id;
        
        
        this.invalidate();
        this.validate();
        this.repaint();
       
        DisplayOwned op = new DisplayOwned(userid);
        initComponents();
        CheckSelected();
        
        cardlayout = (CardLayout)(panelCards.getLayout());
        guest();
        System.out.println("username " + username);
        System.out.println("userid " + userid);
        
    }
    
    public ClientForm_JFrame(){
        
    }
    
    public void guest(){
        if (username == null || userid == 0){
            System.out.println("this is guest form");
            jbtnMyGames.setVisible(false);
            
        }else{
            System.out.println("this is client form");
            namelbl.setText(username);
            jbtnMyGames.setVisible(true);
        }
    }
    
    public void close(){
        this.dispose();
        dispose();
    }
    
    
  


    

    
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelCards = new javax.swing.JPanel();
        panelStore = new javax.swing.JPanel();
        displayPanel1 = new ClientForms.DisplayPanel();
        panelLibrary = new javax.swing.JPanel();
        displayOwned1 = new ClientForms.DisplayOwned();
        panelMenu = new javax.swing.JPanel();
        jbtnMyGames = new javax.swing.JButton();
        jbtnStore = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        namelbl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jbtnMin = new javax.swing.JButton();
        jbtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(28, 37, 47));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 40));
        jLayeredPane1.getAccessibleContext().setAccessibleParent(this);

        jSplitPane1.setDividerLocation(190);
        jSplitPane1.setDividerSize(0);

        panelCards.setLayout(new java.awt.CardLayout());

        panelStore.setBackground(new java.awt.Color(28, 37, 47));
        panelStore.setPreferredSize(new java.awt.Dimension(1000, 684));

        displayPanel1.setPreferredSize(new java.awt.Dimension(875, 684));

        javax.swing.GroupLayout panelStoreLayout = new javax.swing.GroupLayout(panelStore);
        panelStore.setLayout(panelStoreLayout);
        panelStoreLayout.setHorizontalGroup(
            panelStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(displayPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
        );
        panelStoreLayout.setVerticalGroup(
            panelStoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(displayPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelCards.add(panelStore, "panelStore");

        panelLibrary.setBackground(new java.awt.Color(28, 37, 47));
        panelLibrary.setEnabled(false);
        panelLibrary.setPreferredSize(new java.awt.Dimension(1000, 684));

        displayOwned1.setEnabled(false);
        displayOwned1.setMinimumSize(new java.awt.Dimension(875, 684));

        javax.swing.GroupLayout panelLibraryLayout = new javax.swing.GroupLayout(panelLibrary);
        panelLibrary.setLayout(panelLibraryLayout);
        panelLibraryLayout.setHorizontalGroup(
            panelLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLibraryLayout.createSequentialGroup()
                .addComponent(displayOwned1, javax.swing.GroupLayout.PREFERRED_SIZE, 987, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 157, Short.MAX_VALUE))
        );
        panelLibraryLayout.setVerticalGroup(
            panelLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLibraryLayout.createSequentialGroup()
                .addComponent(displayOwned1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 163, Short.MAX_VALUE))
        );

        panelCards.add(panelLibrary, "panelLibrary");

        jSplitPane1.setRightComponent(panelCards);

        panelMenu.setBackground(new java.awt.Color(28, 37, 47));
        panelMenu.setPreferredSize(new java.awt.Dimension(333, 287));

        jbtnMyGames.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jbtnMyGames.setForeground(new java.awt.Color(125, 125, 125));
        jbtnMyGames.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/list-2-24dark.png"))); // NOI18N
        jbtnMyGames.setText("My Games");
        jbtnMyGames.setAlignmentY(0.0F);
        jbtnMyGames.setBorderPainted(false);
        buttonGroup1.add(jbtnMyGames);
        jbtnMyGames.setContentAreaFilled(false);
        jbtnMyGames.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jbtnMyGames.setIconTextGap(20);
        jbtnMyGames.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jbtnMyGames.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnMyGames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnMyGamesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnMyGamesMouseExited(evt);
            }
        });
        jbtnMyGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMyGamesActionPerformed(evt);
            }
        });

        jbtnStore.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jbtnStore.setForeground(new java.awt.Color(125, 125, 125));
        jbtnStore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tag-24dark.png"))); // NOI18N
        jbtnStore.setText("Store");
        buttonGroup1.add(jbtnStore);
        jbtnStore.setContentAreaFilled(false);
        jbtnStore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnStore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jbtnStore.setIconTextGap(20);
        jbtnStore.setMargin(new java.awt.Insets(2, 13, 2, 14));
        jbtnStore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnStoreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnStoreMouseExited(evt);
            }
        });
        jbtnStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnStoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnMyGames, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnStore, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jbtnStore)
                .addGap(18, 18, 18)
                .addComponent(jbtnMyGames)
                .addContainerGap(424, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(panelMenu);

        jPanel1.add(jSplitPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1180, 530));

        jPanel2.setBackground(new java.awt.Color(28, 37, 47));

        namelbl.setBackground(new java.awt.Color(255, 255, 255));
        namelbl.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        namelbl.setForeground(new java.awt.Color(204, 204, 204));
        namelbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        namelbl.setText("Guest");
        namelbl.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        namelbl.setPreferredSize(new java.awt.Dimension(46, 23));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-logout-24 (1).png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addComponent(namelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namelbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, 50));
        jPanel2.getAccessibleContext().setAccessibleParent(this);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/polarisWhiteS.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 179, 60));

        jbtnMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/min.png"))); // NOI18N
        jbtnMin.setContentAreaFilled(false);
        jbtnMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnMinMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnMinMouseExited(evt);
            }
        });
        jbtnMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMinActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 20, 20));

        jbtnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/x.png"))); // NOI18N
        jbtnExit.setContentAreaFilled(false);
        jbtnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnExitMouseExited(evt);
            }
        });
        jbtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExitActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, 20, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 630));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (username == null || userid == 0) {
            Login_JFrame login = new Login_JFrame();
            login.show();
            close();
            
        } else {
            CardInformation c = new CardInformation(null);
            CardInformation cc = new CardInformation(0);
            ClientForm_JFrame frame = new ClientForm_JFrame(null, 0);
            this.dispose();
            dispose();
            frame.show();
        }
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbtnStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnStoreActionPerformed
        cardlayout.show(panelCards, "panelStore");
        text = "Store";

       

        CheckSelected();
    }//GEN-LAST:event_jbtnStoreActionPerformed

    private void jbtnStoreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnStoreMouseExited

        jbtnStore.setForeground(new Color(125, 125, 125));

        Image photo = new ImageIcon(this.getClass().getResource("/Images/tag-24dark.png")).getImage();
        jbtnStore.setIcon(new ImageIcon(photo));

        CheckSelected();
    }//GEN-LAST:event_jbtnStoreMouseExited

    private void jbtnStoreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnStoreMouseEntered

        Image photo = new ImageIcon(this.getClass().getResource("/Images/tag-24.png")).getImage();
        jbtnStore.setIcon(new ImageIcon(photo));

        jbtnStore.setForeground(Color.white);
    }//GEN-LAST:event_jbtnStoreMouseEntered

    private void jbtnMyGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMyGamesActionPerformed

              cardlayout.show(panelCards, "panelLibrary");
       
        text = "My Games";

        

        CheckSelected();
    }//GEN-LAST:event_jbtnMyGamesActionPerformed

    private void jbtnMyGamesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnMyGamesMouseExited
        Image photo = new ImageIcon(this.getClass().getResource("/Images/list-2-24dark.png")).getImage();
        jbtnMyGames.setIcon(new ImageIcon(photo));

        jbtnMyGames.setForeground(new Color(125, 125, 125));

        CheckSelected();
    }//GEN-LAST:event_jbtnMyGamesMouseExited

    private void jbtnMyGamesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnMyGamesMouseEntered
        Image photo = new ImageIcon(this.getClass().getResource("/Images/list-2-24.png")).getImage();
        jbtnMyGames.setIcon(new ImageIcon(photo));

        jbtnMyGames.setForeground(Color.white);
        
    }//GEN-LAST:event_jbtnMyGamesMouseEntered

    private void jbtnMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMinActionPerformed
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jbtnMinActionPerformed

    private void jbtnMinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnMinMouseEntered
        Image photo = new ImageIcon(this.getClass().getResource("/Images/minHover.png")).getImage();
        jbtnMin.setIcon(new ImageIcon(photo));

    }//GEN-LAST:event_jbtnMinMouseEntered

    private void jbtnMinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnMinMouseExited
        Image photo = new ImageIcon(this.getClass().getResource("/Images/min.png")).getImage();
        jbtnMin.setIcon(new ImageIcon(photo));
    }//GEN-LAST:event_jbtnMinMouseExited

    private void jbtnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnExitMouseEntered
        Image photo = new ImageIcon(this.getClass().getResource("/Images/xHover.png")).getImage();
        jbtnExit.setIcon(new ImageIcon(photo));
    }//GEN-LAST:event_jbtnExitMouseEntered

    private void jbtnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnExitMouseExited
       Image photo = new ImageIcon(this.getClass().getResource("/Images/x.png")).getImage();
        jbtnExit.setIcon(new ImageIcon(photo));
    }//GEN-LAST:event_jbtnExitMouseExited

    private void jbtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtnExitActionPerformed

    
    void CheckSelected()
      {
          
          if (text.equals("Store"))
          {
              Image photo = new ImageIcon(this.getClass().getResource("/Images/tag-24.png")).getImage();
            jbtnStore.setIcon(new ImageIcon(photo));
            jbtnStore.setForeground(Color.white);
            
            Image photo1 = new ImageIcon(this.getClass().getResource("/Images/list-2-24dark.png")).getImage();
            jbtnMyGames.setIcon(new ImageIcon(photo1));
            jbtnMyGames.setForeground(new Color(125, 125, 125));
          }
          else if (text.equals("My Games"))
          {
               Image photo = new ImageIcon(this.getClass().getResource("/Images/list-2-24.png")).getImage();
                jbtnMyGames.setIcon(new ImageIcon(photo));
                jbtnMyGames.setForeground(Color.white);
                
                jbtnStore.setForeground(new Color(125, 125, 125));
                Image photo1 = new ImageIcon(this.getClass().getResource("/Images/tag-24dark.png")).getImage();
                jbtnStore.setIcon(new ImageIcon(photo1));
          }
      }
    
       
        
    
        
       
    

    
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
            java.util.logging.Logger.getLogger(ClientForm_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientForm_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientForm_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientForm_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
         UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientForm_JFrame(username, userid).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public static ClientForms.DisplayOwned displayOwned1;
    public static ClientForms.DisplayPanel displayPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtnExit;
    private javax.swing.JButton jbtnMin;
    private javax.swing.JButton jbtnMyGames;
    private javax.swing.JButton jbtnStore;
    public static javax.swing.JLabel namelbl;
    public static javax.swing.JPanel panelCards;
    public static javax.swing.JPanel panelLibrary;
    private javax.swing.JPanel panelMenu;
    public static javax.swing.JPanel panelStore;
    // End of variables declaration//GEN-END:variables
}
