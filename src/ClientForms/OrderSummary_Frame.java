
package ClientForms;


import static AdminForms.Login_JFrame.id;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Franchesca
 */
public class OrderSummary_Frame extends javax.swing.JFrame {

    
    
    static Connection connection ;
    static Statement statement = null;
    static ResultSet resultset = null;
    static boolean isImage = false;
    Double pr;
    
    static int prodid;
    static int userid;
    static String username;
    int generate =0;
    boolean kulang = false;
    
    public OrderSummary_Frame(int id, int productid, String uname) {
        initComponents();
        prodid = productid;
        userid = id;
        username = uname;
        Font font = checkoutlbl.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        checkoutlbl.setFont(font.deriveFont(attributes));
        userdeets();
        gamedeets();
        orderbtn.setEnabled(false);
        amount();
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
        backbtn.addMouseListener(l);
        
        cardnumber.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (cardnumber.getText().length() >= 16) 
                {
                    e.consume();
                    kulang = true;
                }else{
                    kulang = false;
                }
            }
        });
    }
    public void amount(){
        double total;
        double tax;
        double mctax = 0.90;
        tax = pr - ( pr * mctax);
        total = pr + tax;
        
        taxlbl.setText(String.format("₱%.2f", tax));
        totallbl.setText(String.format("₱%.2f", total));
    }
    
    public void userdeets(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            connection = DriverManager.getConnection(url);
            
            
            String sql1 = "select username from [user] where userID = '"+ userid +"'";
            
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql1);
            
            while(rs.next()){
                namelbl.setText(rs.getString("username"));
            }
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            datelbl.setText(date.toString());
            
           
            
            
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
            
             String sql2 = "select productname, price, poster from product where productid = '"+prodid+"'";
            
            statement = connection.createStatement();
            ResultSet rs2 = statement.executeQuery(sql2);
            
            while(rs2.next()){
               
                pr = rs2.getDouble("price");
                
                String sign = String.format("₱%.2f", pr);
                listpricelbl.setText(sign);
                
                titlelbl.setText(rs2.getString("productname"));
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

    
    public void oderplacement(){
        try{
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            connection = DriverManager.getConnection(url);
            String sql1 = "select count(orderid) as counter from orderdetails";
            
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql1);
            while(rs.next()){
                generate = rs.getInt("counter");
            }
            
            
            
            String sql2 = "insert into orderdetails (orderid, productid, userid, Purchasedate) values (?,?,?,?)";
             PreparedStatement pst = connection.prepareStatement(sql2);
             pst.setInt(1, generate + 1);
             pst.setInt(2, prodid);
             pst.setInt(3, userid);
             long millis = System.currentTimeMillis();
            java.sql.Date dates = new java.sql.Date(millis);
            datelbl.setText(dates.toString());
            pst.setDate(4, dates);
            
            pst.executeUpdate();
            System.out.println("order complete!");
            
            
            
           
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderSummary_Frame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderSummary_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        summaryPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        agreementcbx = new javax.swing.JCheckBox();
        orderbtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cardname = new javax.swing.JTextField();
        cardnumber = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        checkoutlbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        listpricelbl = new javax.swing.JLabel();
        taxlbl = new javax.swing.JLabel();
        totallbl = new javax.swing.JLabel();
        imglbl = new javax.swing.JLabel();
        datelbl = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        namelbl = new javax.swing.JLabel();
        titlelbl = new javax.swing.JLabel();
        backbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        summaryPanel.setBackground(new java.awt.Color(245, 245, 245));
        summaryPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Payment Information");
        summaryPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 22, -1, -1));

        agreementcbx.setText("I agree with the terms and conditions");
        agreementcbx.setContentAreaFilled(false);
        agreementcbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agreementcbxActionPerformed(evt);
            }
        });
        summaryPanel.add(agreementcbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        orderbtn.setBackground(new java.awt.Color(102, 204, 255));
        orderbtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 18)); // NOI18N
        orderbtn.setForeground(new java.awt.Color(245, 245, 245));
        orderbtn.setText("Place Order");
        orderbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderbtnActionPerformed(evt);
            }
        });
        summaryPanel.add(orderbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 480, 270, 45));

        jLabel8.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Name of Cardholder");
        summaryPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 246, -1, 20));

        cardname.setBackground(new java.awt.Color(250, 250, 250));
        cardname.setForeground(new java.awt.Color(51, 51, 51));
        cardname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cardnameKeyTyped(evt);
            }
        });
        summaryPanel.add(cardname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 218, 30));

        cardnumber.setBackground(new java.awt.Color(250, 250, 250));
        cardnumber.setForeground(new java.awt.Color(51, 51, 51));
        cardnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cardnumberKeyTyped(evt);
            }
        });
        summaryPanel.add(cardnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 219, 30));

        jLabel9.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Card Number");
        summaryPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        mainPanel.add(summaryPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 0, 340, 560));

        checkoutlbl.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18)); // NOI18N
        checkoutlbl.setForeground(new java.awt.Color(51, 51, 51));
        checkoutlbl.setText("Checkout");
        mainPanel.add(checkoutlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 180, 30));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("List Price");
        mainPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, -1, -1));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Tax");
        mainPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 360, -1, -1));

        jLabel4.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Total");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        mainPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, 90, -1));

        listpricelbl.setBackground(new java.awt.Color(102, 102, 102));
        listpricelbl.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        listpricelbl.setForeground(new java.awt.Color(51, 51, 51));
        listpricelbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        listpricelbl.setText("0");
        listpricelbl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        mainPanel.add(listpricelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, 110, -1));

        taxlbl.setBackground(new java.awt.Color(102, 102, 102));
        taxlbl.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        taxlbl.setForeground(new java.awt.Color(51, 51, 51));
        taxlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        taxlbl.setText("0");
        taxlbl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        mainPanel.add(taxlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 120, -1));

        totallbl.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        totallbl.setForeground(new java.awt.Color(51, 51, 51));
        totallbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totallbl.setText("0");
        totallbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        totallbl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        mainPanel.add(totallbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 490, 100, -1));

        imglbl.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        mainPanel.add(imglbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 260, 390));

        datelbl.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        datelbl.setForeground(new java.awt.Color(51, 51, 51));
        datelbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        datelbl.setText("current date");
        mainPanel.add(datelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 150, -1));

        jLabel12.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Date of Purchase");
        mainPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 140, -1));

        jLabel13.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Sold to");
        mainPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 80, -1));

        namelbl.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        namelbl.setForeground(new java.awt.Color(51, 51, 51));
        namelbl.setText("name");
        mainPanel.add(namelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, 230, -1));

        titlelbl.setBackground(new java.awt.Color(255, 255, 255));
        titlelbl.setFont(new java.awt.Font("Corbel", 0, 28)); // NOI18N
        titlelbl.setForeground(new java.awt.Color(51, 51, 51));
        titlelbl.setText("Sample title");
        mainPanel.add(titlelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 340, 40));

        backbtn.setBackground(new java.awt.Color(255, 255, 255));
        backbtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        backbtn.setForeground(new java.awt.Color(51, 51, 51));
        backbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-back-16.png"))); // NOI18N
        backbtn.setText("Back");
        backbtn.setContentAreaFilled(false);
        backbtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backbtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        backbtn.setIconTextGap(-2);
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });
        mainPanel.add(backbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void orderbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderbtnActionPerformed
             
             
         if (cardname.getText().equals("") || cardnumber.getText().equals("") || kulang == false) {
             
            JOptionPane.showMessageDialog(this, "Missing field information", "Incomplete data", JOptionPane.PLAIN_MESSAGE);
        } else {
            oderplacement();
            ClientForm_JFrame client = new ClientForm_JFrame(username, id);
            SwingUtilities.updateComponentTreeUI(client);
            client.show();

            dispose();
        }
        
    }//GEN-LAST:event_orderbtnActionPerformed

    private void agreementcbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agreementcbxActionPerformed
        // TODO add your handling code here:
        if(agreementcbx.isSelected()){
            orderbtn.setEnabled(true);
        }else{
            orderbtn.setEnabled(false);
        }
    }//GEN-LAST:event_agreementcbxActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        
        PurchaseJFrame pform = new PurchaseJFrame(userid, prodid, username);
        pform.show();
        dispose();
        
    }//GEN-LAST:event_backbtnActionPerformed

    private void cardnumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardnumberKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE)){
            int punto=0;
            if(c==KeyEvent.VK_PERIOD){ 
                        String s=cardnumber.getText();
                        int dot=s.indexOf('.');
                        punto=dot;
                        if(dot!=-1){
                            getToolkit().beep();
                            evt.consume();
                            
                        }
                    }
            
        }
        else{    
            getToolkit().beep();
            evt.consume();
            
        }
        
    }//GEN-LAST:event_cardnumberKeyTyped

    private void cardnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardnameKeyTyped
        
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE)){
            int punto=0;
            if(c==KeyEvent.VK_PERIOD){ 
                        String s=cardname.getText();
                        int dot=s.indexOf('.');
                        punto=dot;
                        if(dot!=-1){
                            getToolkit().beep();
                            evt.consume();
                            
                        }
                    }
            
        }
        else{    
            getToolkit().beep();
            evt.consume();
            
        }
    }//GEN-LAST:event_cardnameKeyTyped

    
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
            java.util.logging.Logger.getLogger(OrderSummary_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderSummary_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderSummary_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderSummary_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderSummary_Frame(userid, prodid, username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox agreementcbx;
    private javax.swing.JButton backbtn;
    private javax.swing.JTextField cardname;
    private javax.swing.JTextField cardnumber;
    private javax.swing.JLabel checkoutlbl;
    private javax.swing.JLabel datelbl;
    private javax.swing.JLabel imglbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel listpricelbl;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel namelbl;
    private javax.swing.JButton orderbtn;
    private javax.swing.JPanel summaryPanel;
    private javax.swing.JLabel taxlbl;
    private javax.swing.JLabel titlelbl;
    private javax.swing.JLabel totallbl;
    // End of variables declaration//GEN-END:variables
}
