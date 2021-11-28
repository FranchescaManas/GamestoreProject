/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminForms;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Franchesca
 */
public class AddViewUpdate_JForm extends javax.swing.JFrame {

    static Connection connection ;
    static Statement statement = null;
    static ResultSet resultset = null;
    static int prodid;
    static String title;
    static String publisher;
    static double price;
    static int stock;
    static ImageIcon image;
    static String uploadImage;
    static boolean isImage = false;
    static String username;
    static int userid;

    
    public AddViewUpdate_JForm(String name, int id) throws SQLException {
        username = name;
        userid = id;
        initComponents();
        //hide productid label and username
        jtxtproductID.setVisible(false);
        savebtn.putClientProperty( "JButton.buttonType", "roundRect" );
        cancelbtn.putClientProperty( "JButton.buttonType", "roundRect" );
        connection();
        
    }
    
    public AddViewUpdate_JForm(){
    }

    public void connection() throws SQLException{
        try {  
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
           
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
           connection = DriverManager.getConnection(url);
           Admin_JFrame boss = new Admin_JFrame(username, userid);
           
           //getting image from sql
            String sql="select poster from product where productid = '"+ getprodID() +"'";



            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()){
                byte[] img = rs.getBytes("poster");
                //converts image
                ImageIcon image = new ImageIcon(img);
                Image im = image.getImage();
                //setting image to the imglbl by getting its h / w
                Image myimg = im.getScaledInstance(imglbl.getWidth(), imglbl.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myimg);
                imglbl.setIcon(newImage);
                if (img != null){
                    isImage = true;
                }
                else{
                    isImage= false;
                }
                
            }
            
           


            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(Level.SEVERE, null, ex);
            }
          
         
    }
    
    
    
    public static int getprodID(){
        return prodid;
    }
    
    public void setprodid(int value){
        this.prodid = value;
  
    }
    public static BufferedImage getBufferedImage(Image img){
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null),
                img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
} 
    static Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        } else {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge
                    = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage image = gc.createCompatibleImage(w, h);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }
    JFrame frame  = new JFrame();
    public void invalidinput(){
        if(jtxtPrice.getText().equals("") || jtxtProductName.getText().equals("") || jtxtPublisher.getText().equals("") || jtxtdescription.getText().equals("")){
            JOptionPane.showMessageDialog(this.frame, "Missing field information", "Incomplete data", JOptionPane.PLAIN_MESSAGE);
        }else{
            System.out.println("god bless");
            try {  
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            connection = DriverManager.getConnection(url);
            byte[] photo = null;
            String sql;
            
            if (savebtn.getText().equals("Add")){
               
                
                sql = "insert into product (productid, productname, publisher, price, poster, description) values (?,?,?,?,?, ?)";
                photo = null;
                
                Admin_JFrame admin = new Admin_JFrame(username, userid);
                boolean noimg;
                
               
                noimg = admin.nophoto;

                if (noimg == true) {
                    System.out.println("no photo inserted");

                    try {
                        BufferedImage bfi = getBufferedImage(iconToImage(imglbl.getIcon()));
                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        ImageIO.write(bfi, "png", bs);
                        photo = bs.toByteArray();

                    } catch (IOException ex) {
                        Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("photo Inserted");
                    try {

                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        File image = new File(uploadImage);
                        FileInputStream fis = new FileInputStream(image);
                        byte[] buf = new byte[1024];
                        for (int readNum; (readNum = fis.read(buf)) != -1;) {
                            bos.write(buf, 0, readNum);

                        }

                        photo = bos.toByteArray();

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                
            }
            else{
                
                sql = "update product set productid = ?, productname=?, publisher=?, price=?, poster=?, description=? where productid = '"+ getprodID() +"'";
                
                try{
                    BufferedImage bfi = getBufferedImage(iconToImage(imglbl.getIcon()));
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    ImageIO.write(bfi, "png", bs);
                    photo = bs.toByteArray();
                } catch (IOException ex) {
                    Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }
            PreparedStatement pst = connection.prepareStatement(sql);
            System.out.println("product of updated row is " + getprodID());
            pst.setInt(1, Integer.parseInt(jtxtproductID.getText()));
            pst.setString(2, jtxtProductName.getText());
            pst.setString(3, jtxtPublisher.getText());
            double money = Double.parseDouble(jtxtPrice.getText());
            pst.setBigDecimal(4, BigDecimal.valueOf(money));
            pst.setBytes(5, photo);
            pst.setString(6, jtxtdescription.getText());
             
            
            
            pst.executeUpdate();
            Admin_JFrame admin = new Admin_JFrame(username, userid);
            admin.show();
            dispose();
            

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
            Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    


 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        popupMenu2 = new java.awt.PopupMenu();
        jPanel1 = new javax.swing.JPanel();
        imglbl = new javax.swing.JLabel();
        jtxtproductID = new javax.swing.JTextField();
        jtxtProductName = new javax.swing.JTextField();
        jtxtPublisher = new javax.swing.JTextField();
        jtxtPrice = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        savebtn = new javax.swing.JButton();
        cancelbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtdescription = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();

        popupMenu1.setLabel("popupMenu1");

        popupMenu2.setLabel("popupMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(40, 49, 59));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imglbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(imglbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 190, 280));
        jPanel1.add(jtxtproductID, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 26, -1, -1));

        jtxtProductName.setBackground(new java.awt.Color(64, 77, 91));
        jtxtProductName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jtxtProductNameMouseMoved(evt);
            }
        });
        jPanel1.add(jtxtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 196, 26));

        jtxtPublisher.setBackground(new java.awt.Color(64, 77, 91));
        jPanel1.add(jtxtPublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 196, 26));

        jtxtPrice.setBackground(new java.awt.Color(64, 77, 91));
        jtxtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtPriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtPriceKeyTyped(evt);
            }
        });
        jPanel1.add(jtxtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 196, 26));

        jButton1.setBackground(new java.awt.Color(64, 77, 91));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-upload-24.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 30, 30));

        jLabel3.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Title");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 69, -1));

        jLabel4.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Publisher");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Description");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 90, -1));

        savebtn.setBackground(new java.awt.Color(78, 129, 203));
        savebtn.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        savebtn.setText("Save");
        savebtn.setToolTipText("");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });
        jPanel1.add(savebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, 90, -1));

        cancelbtn.setBackground(new java.awt.Color(207, 92, 96));
        cancelbtn.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cancelbtn.setText("Cancel");
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });
        jPanel1.add(cancelbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 330, 90, -1));

        jtxtdescription.setBackground(new java.awt.Color(64, 77, 91));
        jtxtdescription.setColumns(20);
        jtxtdescription.setForeground(new java.awt.Color(204, 204, 204));
        jtxtdescription.setLineWrap(true);
        jtxtdescription.setRows(5);
        jScrollPane1.setViewportView(jtxtdescription);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 530, 160));

        jLabel6.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Price");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 69, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileupload = new JFileChooser();
        fileupload.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "gif", "bmp");
        fileupload.addChoosableFileFilter(filter);
        fileupload.showOpenDialog(null);
        File f = fileupload.getSelectedFile();
        uploadImage = f.getAbsolutePath();
        // displays the uploaded image to the label
        ImageIcon imgThisImg = new ImageIcon(new ImageIcon(uploadImage)
                .getImage().getScaledInstance(imglbl.getWidth(), imglbl.getHeight(), Image.SCALE_DEFAULT));
        imglbl.setIcon(imgThisImg);
        System.out.println(uploadImage);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        // TODO add your handling code here:
        Admin_JFrame backframe;
        try {
            backframe = new Admin_JFrame(username, userid);
            backframe.show();
            dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_cancelbtnActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        invalidinput();
    }//GEN-LAST:event_savebtnActionPerformed

    private void jtxtPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtPriceKeyPressed
  
    }//GEN-LAST:event_jtxtPriceKeyPressed

    private void jtxtPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtPriceKeyTyped
        char c=evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_PERIOD)||(c==KeyEvent.VK_BACK_SPACE)){
            int punto=0;
            if(c==KeyEvent.VK_PERIOD){ 
                        String s=jtxtPrice.getText();
                        int dot=s.indexOf('.');
                        punto=dot;
                        if(dot!=-1){
                            getToolkit().beep();
                            evt.consume();
                            //savebtn.setEnabled(false);
                        }
                    }
            //savebtn.setEnabled(true);
        }
        else{    
            getToolkit().beep();
            evt.consume();
            //savebtn.setEnabled(false);
        }
    }//GEN-LAST:event_jtxtPriceKeyTyped

    private void jtxtProductNameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtProductNameMouseMoved
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jtxtProductNameMouseMoved

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
         UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AddViewUpdate_JForm(username, userid).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton cancelbtn;
    public static javax.swing.JLabel imglbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField jtxtPrice;
    public static javax.swing.JTextField jtxtProductName;
    public static javax.swing.JTextField jtxtPublisher;
    public static javax.swing.JTextArea jtxtdescription;
    public static javax.swing.JTextField jtxtproductID;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    public static javax.swing.JButton savebtn;
    // End of variables declaration//GEN-END:variables
}
