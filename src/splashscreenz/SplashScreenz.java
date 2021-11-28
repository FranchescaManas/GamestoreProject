/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splashscreenz;

import ClientForms.ClientForm_JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static splashscreenz.Splashz.jProgressBar1;

/**
 *
 * @author cloudswyft
 */
public class SplashScreenz {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, UnsupportedLookAndFeelException, IllegalAccessException {
        UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        Splashz splash = new Splashz();
        
        splash.setVisible(true);
        try{
            
            for(int i = 2;i <= 100;i++){
                Thread.sleep(100);
                Splashz.jLabel1.setText(i + "%");
                
                if(i==10){
                    splash.jLabel4.setText("Turning On Modules...");                   
                }
                if(i==20){
                    splash.jLabel4.setText("Loading Modules...");
                }
                if(i==50){
                    splash.jLabel4.setText("Connecting to Database...");
                }
                if(i==70){
                    splash.jLabel4.setText("Connection Successful!");
                }
                if(i==80){
                    splash.jLabel4.setText("Launching Application...");
                }
                if(i==100){
                    ClientForm_JFrame client = new ClientForm_JFrame(null, 0);
                    splash.setVisible(false);
                    splash.dispose();
                    client.setVisible(true);
                }
                splash.jProgressBar1.setValue(i);
                
            }
            
            
            
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
