
package ClientForms;

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

/**
 *
 * @author Franchesca
 */
public class DisplayPanel extends javax.swing.JPanel {

    static Connection connection ;
    static Statement statement = null;
    static ResultSet resultset = null;
    
    public DisplayPanel() {
        initComponents();
        init();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    
    private void init() {
        panel.setLayout(new WrapLayout(WrapLayout.LEADING, 10, 15));
        
        try {
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
          
          String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
           
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
           connection = DriverManager.getConnection(url);
           
        
        String sql="select productid, productname, publisher, price, poster from product";

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
                
           panel.add(new Container(new ModelContainer(new ImageIcon(im), rs.getString("productname"), rs.getString("publisher"),sign, rs.getInt("productid") )));
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
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables

}
