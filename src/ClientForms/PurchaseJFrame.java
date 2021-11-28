
package ClientForms;

import static AdminForms.Login_JFrame.id;
import static ClientForms.OrderSummary_Frame.connection;
import static ClientForms.OrderSummary_Frame.isImage;
import static ClientForms.OrderSummary_Frame.statement;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class PurchaseJFrame extends javax.swing.JFrame {

    static int productid;
    static int userid;
    static String username;
    boolean lib = false;
    
    public PurchaseJFrame(int id , int prodid, String uid) {
        initComponents();
        
       username = uid;
        productid = prodid;
        userid = id;
        buybtn.setEnabled(true);
        gamedeets();
        
        MouseListener l = new MouseAdapter() {
            Font original;

            @Override
            public void mouseEntered(MouseEvent e) {
                original = e.getComponent().getFont();
                Map attributes = original.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                e.getComponent().setFont(original.deriveFont(attributes));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setFont(original);
            }

        };
        
        btnBack.addMouseListener(l);
        librarychecker();
    }
    
    public PurchaseJFrame(int prodid){
        initComponents();
        productid = prodid;
        userid = 0;
        username = null;
        gamedeets();
        buybtn.setEnabled(false);
        MouseListener l = new MouseAdapter() {
            Font original;

            @Override
            public void mouseEntered(MouseEvent e) {
                original = e.getComponent().getFont();
                Map attributes = original.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                e.getComponent().setFont(original.deriveFont(attributes));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setFont(original);
            }

        };
        
        btnBack.addMouseListener(l);
    }
    public void librarychecker(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            connection = DriverManager.getConnection(url);
           
             String sql2 = "select * from orderdetails where userid = '"+ userid +"' and productid = '"+productid+"'";
            
            statement = connection.createStatement();
            ResultSet rs2 = statement.executeQuery(sql2);
            
            while(rs2.next()){
                int user = rs2.getInt("userid");
                int game = rs2.getInt("productid");
                
                if( user == userid && game == productid){
                    System.out.println("game already exist in the library");
                    //JOptionPane.showMessageDialog(this, "Product already exists in library", "Purchase unavailable", JOptionPane.PLAIN_MESSAGE);
                    buybtn.setEnabled(false);
                    lib = false;
                }else{
                    buybtn.setEnabled(true);
                    lib = true;
                }
                
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderSummary_Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderSummary_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gamedeets(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            connection = DriverManager.getConnection(url);
            
             String sql2 = "select productname,publisher ,price, poster, description from product where productid = '"+productid+"'";
            
            statement = connection.createStatement();
            ResultSet rs2 = statement.executeQuery(sql2);
            
            while(rs2.next()){
                
                double pr = rs2.getDouble("price");
                
                String sign = String.format("₱%.2f", pr);
                pricelbl.setText(sign);
                descriptiontxt.append( rs2.getString("Description"));
                descriptiontxt.setLineWrap(true);
                descriptiontxt.setWrapStyleWord(true);

                titlelbl1.setText(rs2.getString("productname"));
                developerlbl.setText(rs2.getString("publisher"));
                byte[] imgs = rs2.getBytes("poster");
                ImageIcon image = new ImageIcon(imgs);
                Image im = image.getImage();
                Image myimg = im.getScaledInstance(imglbl.getWidth(), imglbl.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myimg);
                imglbl.setIcon(newImage);
                if (imgs != null){
                    isImage = true;
                }
                else{
                    isImage= false;
                }
               
                
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderSummary_Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderSummary_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        imglbl = new javax.swing.JLabel();
        titlelbl1 = new javax.swing.JLabel();
        buybtn = new javax.swing.JButton();
        pricelbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptiontxt = new javax.swing.JTextArea();
        developerlbl = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(28, 37, 47));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(28, 37, 47));
        jPanel1.setForeground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        logo.setForeground(new java.awt.Color(255, 255, 255));
        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setText("POLARIS");
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 460, -1, 17));

        imglbl.setText("jLabel7");
        imglbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(imglbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 270, 360));

        titlelbl1.setBackground(new java.awt.Color(204, 204, 204));
        titlelbl1.setFont(new java.awt.Font("Corbel", 0, 26)); // NOI18N
        titlelbl1.setForeground(new java.awt.Color(245, 245, 245));
        titlelbl1.setText("TITLE");
        jPanel1.add(titlelbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 400, 40));

        buybtn.setBackground(new java.awt.Color(102, 204, 255));
        buybtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        buybtn.setForeground(new java.awt.Color(255, 255, 255));
        buybtn.setText("Buy Now");
        buybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buybtnActionPerformed(evt);
            }
        });
        jPanel1.add(buybtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 420, 50));

        pricelbl.setBackground(new java.awt.Color(204, 204, 204));
        pricelbl.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 16)); // NOI18N
        pricelbl.setForeground(new java.awt.Color(245, 245, 245));
        pricelbl.setText("Price");
        jPanel1.add(pricelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 400, 40));

        descriptiontxt.setEditable(false);
        descriptiontxt.setBackground(new java.awt.Color(28, 37, 47));
        descriptiontxt.setColumns(20);
        descriptiontxt.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        descriptiontxt.setForeground(new java.awt.Color(245, 245, 245));
        descriptiontxt.setLineWrap(true);
        descriptiontxt.setRows(5);
        descriptiontxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 47)));
        jScrollPane1.setViewportView(descriptiontxt);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 420, 180));

        developerlbl.setBackground(new java.awt.Color(204, 204, 204));
        developerlbl.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        developerlbl.setForeground(new java.awt.Color(204, 204, 204));
        developerlbl.setText("publisher");
        jPanel1.add(developerlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 400, 40));

        btnBack.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back_icon.png"))); // NOI18N
        btnBack.setText("Back to Store");
        btnBack.setContentAreaFilled(false);
        btnBack.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBack.setMargin(new java.awt.Insets(2, 0, 2, 14));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buybtnActionPerformed
        // TODO add your handling code here:
        OrderSummary_Frame order = new OrderSummary_Frame(userid, productid, username);
        order.show();
        dispose();
        
    }//GEN-LAST:event_buybtnActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        ClientForm_JFrame client = new ClientForm_JFrame(username, id);
                   CardInformation info = new CardInformation(id);
                   CardInformation info2 = new CardInformation(username);
                   client.show();
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        
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
            java.util.logging.Logger.getLogger(PurchaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
         UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchaseJFrame(userid, productid, username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    public static javax.swing.JButton buybtn;
    private javax.swing.JTextArea descriptiontxt;
    private javax.swing.JLabel developerlbl;
    private javax.swing.JLabel imglbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel pricelbl;
    private javax.swing.JLabel titlelbl1;
    // End of variables declaration//GEN-END:variables
}
