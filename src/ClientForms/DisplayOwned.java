
package ClientForms;


import AdminForms.Login_JFrame;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import ClientForms.ClientForm_JFrame;

import static ClientForms.ClientForm_JFrame.userid;
import javax.swing.Icon;
import javax.swing.SwingUtilities;

public class DisplayOwned extends javax.swing.JPanel {

    static Connection connection ;
    static Statement statement = null;
    static ResultSet resultset = null;
    public static int userid2 = Login_JFrame.id;
    String title, pub, signs;
   Icon icon;
    int prodid;
   static int userid;
    public DisplayOwned() {
 
        initComponents();
        init();
        panel.setEnabled(false);
        
        System.out.println(userid2);
        userid2 = 0;
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        
        
     
    }
    
    public DisplayOwned(int id){
        userid = id;
    }

   
  
    
    public void init() {
        panel.setLayout(new WrapLayout(WrapLayout.LEADING, 10, 15));
        
        try {
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
          
          String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
           
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
           connection = DriverManager.getConnection(url);
           
        
        String sql="SELECT        OrderDetails.ProductID, Product.ProductName, Product.Publisher, Product.Price, Product.Poster\n" +
"FROM            OrderDetails INNER JOIN\n" +
"                         [User] ON OrderDetails.UserID = [User].UserID INNER JOIN\n" +
"                         Product ON OrderDetails.ProductID = Product.ProductID\n" +
"						 where OrderDetails.UserID='"+ userid + "'";

       
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        Object[]fila = new Object[5];
        while(rs.next()){
 
            byte[] img = rs.getBytes("poster");
            
                ImageIcon image = new ImageIcon(img);
                Image im = image.getImage();
                Image myimg = im.getScaledInstance(70, 100, Image.SCALE_SMOOTH);
                double pr = rs.getDouble("price");
                
                String sign = String.format("₱%.2f", pr);
                
           panel.add(new LibraryContainer(new ModelContainer(new ImageIcon(im), rs.getString("productname"), rs.getString("publisher"),sign, rs.getInt("productid")  )));
           
        }
        
        }
    catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(28, 37, 47));
        setPreferredSize(new java.awt.Dimension(1089, 521));

        panel.setBackground(new java.awt.Color(28, 37, 47));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
