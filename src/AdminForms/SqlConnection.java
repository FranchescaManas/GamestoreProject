/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminForms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Franchesca
 */
public class SqlConnection {

    /**
     * @param args the command line arguments
     */
    public static String url;
    public static Connection connection;
    
    
    public static void main(String[] args) {
        
         try{
     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
     
    url = "jdbc:sqlserver://localhost;databaseName=Games;integratedSecurity=true";
    
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    
    connection = DriverManager.getConnection(url);
    
     System.out.println("Connected to database !");
 
   }
   catch(SQLException sqle) {
      System.out.println("Sql Exception :"+sqle.getMessage());
   }
   catch(ClassNotFoundException e) {
    System.out.println("Class Not Found Exception :" + e.getMessage());
   }
    }
    
}
