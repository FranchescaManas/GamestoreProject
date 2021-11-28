package AdminForms;

import static AdminForms.AddViewUpdate_JForm.imglbl;
import ClientForms.CardInformation;
import ClientForms.ClientForm_JFrame;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.table.*;


/**
 *
 * @author Franchesca
 */
public class Admin_JFrame extends javax.swing.JFrame {

   
    
    
    String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
    
    //===========================================================================
    
    
    static Connection connection;
    
    static Statement statement = null;
    static ResultSet resultset = null;
    String prodID, productname, publisher;
    double price = 0;
    int stocks = 0;
    public static int mProductID;
    static boolean nophoto = false;

    int col[] = {30, 150, 150, 70, 50};

    int hcol[] = {10, 20, 70, 70, 70, 50, 80};
    static int userid;
    static String username;
    
    static int des;
    
    public Admin_JFrame(String name, int id) throws ClassNotFoundException {
        initComponents();
        userid = id;
        username = name;
        loading();
        namelbl.setText(username);
        centerdata(jTable1);
        setColumnWidth(col, jTable1);
        jTable1.setRowHeight(120);
        doubleclick(jTable1);
        orderdetails();
        setColumnWidth(hcol, historyJtbl);
        historyJtbl.setRowHeight(25);
        searchFilter();
        
        
        
       
    }


    

    public void loading() throws ClassNotFoundException {
        try {
            
            // connection for sql
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);
          
            String[] title = {"ProductID", "Product Name", "Publisher", "Price", "Cover Photo"};

            String sql = "select * from product";

            DefaultTableModel model = new DefaultTableModel(null, title) {
                @Override
                public Class<?> getColumnClass(int column) {
                    
                    if (column == 4) {
                        return ImageIcon.class;
                    }
                    return Object.class;
                }
                
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            // to run the query in sql
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            Object[] fila = new Object[5];
            while (rs.next()) {
                fila[0] = rs.getString("productid");
                fila[1] = rs.getString("productname");
                fila[2] = rs.getString("publisher");
                double peso = rs.getDouble("price");
                fila[3] = String.format("₱%.2f", peso);
                byte[] img = rs.getBytes("poster");

                //resizing the image to fit the table
                ImageIcon image = new ImageIcon(img);
                Image im = image.getImage();
                Image myimg = im.getScaledInstance(70, 100, Image.SCALE_SMOOTH);

                fila[4] = new ImageIcon(myimg);

                model.addRow(fila);
                
            }
          
            JTableHeader header = jTable1.getTableHeader();
            header.setBackground(new Color(40,49,59));
            jTable1.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void updatetable(){
        String sql = "select * from product";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            statement = connection.createStatement();
            
            

            connection = DriverManager.getConnection(url);
            
            ResultSet rs = statement.executeQuery(sql);
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.removeRow(jTable1.getSelectedRow());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setColumnWidth(int[] width, JTable table) {

        for (int i = 0; i <= table.getColumnCount() - 1; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(width[i]);
        }
    }

    public static void centerdata(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // center any data in jtable
        for (int i = 0; i < model.getColumnCount() - 1; i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

    public void orderdetails() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");


            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            connection = DriverManager.getConnection(url);

            String[] htitle = {"UserID", "ProductID", "Product Name", "Publisher", "Price", "Purchase Date"};
            String sql = "SELECT   orderdetails.ProductID, orderdetails.UserID, Product.ProductName, Product.Publisher, Product.Price, orderdetails.PurchaseDate "
                    + "FROM Product INNER JOIN orderdetails ON Product.ProductID = orderdetails.ProductID";

            DefaultTableModel jmodel = new DefaultTableModel(null, htitle) {

                public boolean isCellEditable(int row, int column) {
                    return false;
                }

            };

            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Object[] history = new Object[7];
            while (rs.next()) {
                history[0] = rs.getString("UserID");
                history[1] = rs.getString("productid");
                history[2] = rs.getString("productname");
                history[3] = rs.getString("Publisher");
               
                double monetary = rs.getDouble("price");
                history[4] =  String.format("₱%.2f", monetary);
                history[5] = rs.getString("purchasedate");
                jmodel.addRow(history);
            }
            JTableHeader header = historyJtbl.getTableHeader();
            header.setBackground(new Color(40,49,59));
            historyJtbl.setModel(jmodel);
            centerdata(historyJtbl);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchFilter(){
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTable1.getModel());
        
        jTable1.setRowSorter(rowSorter);
        
        jtxtSearchFilter.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtxtSearchFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtxtSearchFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        
    }
    
    
    public void doubleclick(JTable table) {

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println(" double click");
                    try {
                        
                        AddViewUpdate_JForm update = new AddViewUpdate_JForm(username, userid);
                        update.savebtn.hide();

                        update.cancelbtn.setText("Back");
                        update.show();

                        int rowIndex = table.getSelectedRow();
                        
                        System.out.println(rowIndex);
                        TableModel model = table.getModel();
                        int test = Integer.parseInt(model.getValueAt(rowIndex, 0).toString());

                        String tt = model.getValueAt(rowIndex, 1).toString();

                        mProductID = test;
                        setdesccriptionHUHU(test);
                        update.setprodid(mProductID);
                        update.jtxtproductID.setText(String.valueOf(test));

                        update.setTitle(tt);
                        update.jtxtProductName.setText(tt);
                        String pb = model.getValueAt(rowIndex, 2).toString();
                        String pr = model.getValueAt(rowIndex, 3).toString();
                        

                        update.jtxtPublisher.setText(pb);
                        String nosign = pr.replace("₱", "");
                        update.jtxtPrice.setText(nosign);
                        getdescription();
                 
                        update.connection();
                        
                        dispose();

                    } catch (SQLException ex) {
                        Logger.getLogger(Admin_JFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public void setdesccriptionHUHU(int index) {
        try {
            des = index;
            System.out.println("i want to get the description of " + des);

        } catch (Exception ex) {

        }
    }
    
    

    public void getdescription(){
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");


            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            connection = DriverManager.getConnection(url);

            String sql = "select description from product where productid = '"+ des +"' ";
            
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                
                AddViewUpdate_JForm ad = new AddViewUpdate_JForm();
                ad.jtxtdescription.append(rs.getString("description"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        productPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jtxtSearchFilter = new javax.swing.JTextField();
        transactionPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyJtbl = new javax.swing.JTable();
        namelbl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jbtnMin = new javax.swing.JButton();
        jbtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(28, 37, 47));
        jPanel1.setLayout(null);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/polarisWhiteS.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jPanel1.add(jButton5);
        jButton5.setBounds(0, 10, 180, 50);

        jTabbedPane1.setBackground(new java.awt.Color(40, 49, 59));

        productPanel.setBackground(new java.awt.Color(28, 37, 47));
        productPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productPanelMouseClicked(evt);
            }
        });

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(40, 49, 59));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jButton3.setBackground(new java.awt.Color(64, 77, 91));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-edit-24.png"))); // NOI18N
        jButton3.setToolTipText("Edit ");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(64, 77, 91));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-24.png"))); // NOI18N
        jButton4.setToolTipText("Delete");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(64, 77, 91));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-plus-math-24.png"))); // NOI18N
        jButton2.setToolTipText("Add New");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jtxtSearchFilter.setBackground(new java.awt.Color(64, 77, 91));
        jtxtSearchFilter.setForeground(new java.awt.Color(204, 204, 204));
        jtxtSearchFilter.setText("Search by title or publisher");
        jtxtSearchFilter.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtSearchFilterFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtSearchFilterFocusLost(evt);
            }
        });
        jtxtSearchFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtSearchFilterKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtSearchFilterKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout productPanelLayout = new javax.swing.GroupLayout(productPanel);
        productPanel.setLayout(productPanelLayout);
        productPanelLayout.setHorizontalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(productPanelLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(547, 547, 547)
                        .addComponent(jtxtSearchFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        productPanelLayout.setVerticalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSearchFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Products", productPanel);

        transactionPanel.setBackground(new java.awt.Color(28, 37, 47));
        transactionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionPanelMouseClicked(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });
        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyPressed(evt);
            }
        });

        historyJtbl.setBackground(new java.awt.Color(40, 49, 59));
        historyJtbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        historyJtbl.setSelectionBackground(new java.awt.Color(0, 51, 102));
        historyJtbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyJtblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(historyJtbl);

        javax.swing.GroupLayout transactionPanelLayout = new javax.swing.GroupLayout(transactionPanel);
        transactionPanel.setLayout(transactionPanelLayout);
        transactionPanelLayout.setHorizontalGroup(
            transactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactionPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1052, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        transactionPanelLayout.setVerticalGroup(
            transactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactionPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transaction History", transactionPanel);

        jPanel1.add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 70, 1100, 549);

        namelbl.setBackground(new java.awt.Color(255, 255, 255));
        namelbl.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        namelbl.setForeground(new java.awt.Color(204, 204, 204));
        namelbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        namelbl.setText("Name");
        namelbl.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanel1.add(namelbl);
        namelbl.setBounds(710, 40, 290, 23);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-logout-24 (1).png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(1000, 40, 30, 20);

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
        jPanel1.add(jbtnMin);
        jbtnMin.setBounds(1050, 0, 20, 20);

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
        jPanel1.add(jbtnExit);
        jbtnExit.setBounds(1070, 0, 20, 20);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 630));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int noRowSelected = jTable1.getSelectedRow();
        System.out.println(noRowSelected);

        if (noRowSelected == -1) {
            JOptionPane.showMessageDialog(this.frame, "No row selected", "Select an item", JOptionPane.PLAIN_MESSAGE);
        } else {
            try {

                int rowIndex = jTable1.getSelectedRow();

                AddViewUpdate_JForm update = new AddViewUpdate_JForm(username, userid);
                update.savebtn.setText("Save");
                
                TableModel model = jTable1.getModel();
                int test = Integer.parseInt(model.getValueAt(rowIndex, 0).toString());

                String tt = model.getValueAt(rowIndex, 1).toString();

                mProductID = test;
                setdesccriptionHUHU(mProductID);
                update.setprodid(mProductID);
                update.jtxtproductID.setText(String.valueOf(test));

                update.setTitle(tt);
                update.jtxtProductName.setText(tt);
                String pb = model.getValueAt(rowIndex, 2).toString();
                String pr = model.getValueAt(rowIndex, 3).toString();

                update.jtxtPublisher.setText(pb);
                String nosign = pr.replace("₱", "");
                        update.jtxtPrice.setText(nosign);
                getdescription();
                
               
                update.connection();

                dispose();
                update.show();

                System.out.println(rowIndex);

            } catch (SQLException ex) {
                Logger.getLogger(Admin_JFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_jButton3ActionPerformed
    private JFrame frame;
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 

        int noRowSelected = jTable1.getSelectedRow();

        if (noRowSelected == -1) {

            JOptionPane.showMessageDialog(this.frame, "No row selected", "Select an item", JOptionPane.PLAIN_MESSAGE);

        } else {

            try {
                
                if (JOptionPane.showConfirmDialog(this.frame, "Are you sure you want to delete this item?", "Confirm Delete",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {

                    try {
                        
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        //String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";

                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                        connection = DriverManager.getConnection(url);
                        int rowIndex = jTable1.getSelectedRow();
                        // make into method to reuse when editing row

                        TableModel model = jTable1.getModel();
                        String test = model.getValueAt(rowIndex, 0).toString();
                        System.out.println("test " + test);
//
                        String sql = "delete from product where productid = " + test;

                        PreparedStatement pst = connection.prepareStatement(sql);
                        pst.execute();

                        updatetable();
//                    centerdata(jTable1);
//                    setColumnWidth(col, jTable1);

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AddViewUpdate_JForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Admin_JFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // variable to be passed in addviewupdate 
            // sets default img when addviewupdate form is opened. if user does not change img
            // default img will be uploaded in the database
            nophoto = true;

            AddViewUpdate_JForm addform = new AddViewUpdate_JForm(username, userid);

            Image photo = new ImageIcon(this.getClass().getResource("/Images/defaultimg.png")).getImage();
            //fits image size to imglbl
            Image myimg = photo.getScaledInstance(imglbl.getWidth(), imglbl.getHeight(), Image.SCALE_SMOOTH);
            imglbl.setIcon(new ImageIcon(myimg));

            addform.savebtn.setText("Add");
            addform.jtxtproductID.setEnabled(false);

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //String url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sql = "select productid from product order by ProductID";

            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            int generate = 0;
            while (rs.next()) {
                generate = rs.getInt("productid");
                addform.jtxtproductID.setText(String.valueOf(generate + 1));

                addform.show();
                dispose();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Admin_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            CardInformation c = new CardInformation(null);
            CardInformation cc = new CardInformation(0);
            ClientForm_JFrame frame = new ClientForm_JFrame(null, 0);
            this.dispose();
            dispose();
            frame.show();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked
    
    private void productPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productPanelMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_productPanelMouseClicked

    private void transactionPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionPanelMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_transactionPanelMouseClicked

    private void jScrollPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1KeyPressed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void historyJtblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyJtblMouseClicked

    }//GEN-LAST:event_historyJtblMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked

    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here: hidemenu();

    }//GEN-LAST:event_jButton2MouseClicked

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jScrollPane3MouseClicked

    private void jtxtSearchFilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSearchFilterKeyTyped
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jtxtSearchFilterKeyTyped

    private void jtxtSearchFilterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSearchFilterKeyPressed
//        jtxtSearchFilter.addKeyListener(new KeyAdapter() {
//            public void keyTyped(KeyEvent e) {
//                char c = e.getKeyChar();
//                if (((c >= '0') && (c <= '9')
//                        || (c == KeyEvent.VK_BACK_SPACE)
//                        || (c == KeyEvent.VK_DELETE) )) {
//                    getToolkit().beep();
//                    e.consume();
//                }
//            }
//        });
    }//GEN-LAST:event_jtxtSearchFilterKeyPressed

    private void jtxtSearchFilterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtSearchFilterFocusGained
        // TODO add your handling code here:
        jtxtSearchFilter.setForeground(new Color(204,204,204)); 
        if (jtxtSearchFilter.getText().equals("Search by title or publisher"))
        {
           jtxtSearchFilter.setText("");
           
        }
    }//GEN-LAST:event_jtxtSearchFilterFocusGained

    private void jtxtSearchFilterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtSearchFilterFocusLost
        // TODO add your handling code here:
//        jtxtSearchFilter.setForeground(new Color(204,204,204)); 
//        if (jtxtSearchFilter.getText().equals(""))
//        {
//           jtxtSearchFilter.setText("Search by title or publisher");
//           
//        }
    }//GEN-LAST:event_jtxtSearchFilterFocusLost

    private void jbtnMinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnMinMouseEntered
        Image photo = new ImageIcon(this.getClass().getResource("/Images/minHover.png")).getImage();
        jbtnMin.setIcon(new ImageIcon(photo));
    }//GEN-LAST:event_jbtnMinMouseEntered

    private void jbtnMinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnMinMouseExited
        Image photo = new ImageIcon(this.getClass().getResource("/Images/min.png")).getImage();
        jbtnMin.setIcon(new ImageIcon(photo));
    }//GEN-LAST:event_jbtnMinMouseExited

    private void jbtnMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMinActionPerformed
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jbtnMinActionPerformed

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
            java.util.logging.Logger.getLogger(Admin_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Admin_JFrame(username, userid).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Admin_JFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable historyJtbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnExit;
    private javax.swing.JButton jbtnMin;
    private javax.swing.JTextField jtxtSearchFilter;
    public static javax.swing.JLabel namelbl;
    public static javax.swing.JPanel productPanel;
    public static javax.swing.JPanel transactionPanel;
    // End of variables declaration//GEN-END:variables
}
