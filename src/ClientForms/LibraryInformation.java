
package ClientForms;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.geom.Path2D;
import java.util.Map;
import javax.swing.JFrame;

/**
 *
 * @author Franchesca
 */
public class LibraryInformation extends javax.swing.JPanel {

    static int userid;
    static int productid;
    
    public LibraryInformation(String title, String publisher, String price, int prodid) {
        initComponents();
        titlelbl.setText(title);
        publisherlbl.setText(publisher);
        pricelbl.setText(price);
        productid = prodid;
        pricelbl.setVisible(false);
        
        
        
        //=========================Mouse Enter==================================
        
        titlelbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
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
            
            @Override
            public void mouseClicked(MouseEvent e) {
//                
            }

        };
        titlelbl.addMouseListener(l);
    }
    
    public LibraryInformation(int id){
        userid = id;
    }

   


    @SuppressWarnings("unchecked")
     @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255, 0));
        Path2D.Float f = new Path2D.Float();
        f.moveTo(0, 30);
        f.curveTo(50, 0, 120, 70, 300, 200);
        f.lineTo(150, getHeight());
        f.lineTo(0, getHeight());
        g2.fill(f);
        super.paintComponent(grphcs);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlelbl = new javax.swing.JLabel();
        publisherlbl = new javax.swing.JLabel();
        pricelbl = new javax.swing.JLabel();

        setBackground(new java.awt.Color(28, 37, 47));

        titlelbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titlelbl.setText("title");
        titlelbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titlelblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                titlelblMouseEntered(evt);
            }
        });

        publisherlbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        publisherlbl.setText("publisher");

        pricelbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pricelbl.setText("price");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titlelbl)
                    .addComponent(publisherlbl)
                    .addComponent(pricelbl))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titlelbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(publisherlbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pricelbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void titlelblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlelblMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_titlelblMouseClicked

    private void titlelblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlelblMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_titlelblMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel pricelbl;
    public static javax.swing.JLabel publisherlbl;
    public static javax.swing.JLabel titlelbl;
    // End of variables declaration//GEN-END:variables
}
