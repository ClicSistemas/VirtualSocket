package com.clicsistemas.VirtualSocket;

import com.clicsistemas.VirtualSocket.gui.About;
import com.clicsistemas.VirtualSocket.gui.Client;
import com.clicsistemas.VirtualSocket.gui.MainScreen;
import com.clicsistemas.VirtualSocket.gui.Server;
import java.awt.*;
import javax.swing.*;
import com.clicsistemas.VirtualSocket.gui.SplashScreen;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clic Sistemas Ltda
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("net.sourceforge.mlf.metouia.MetouiaLookAndFeel");
        } catch(Exception e) {
            //e.printStackTrace();
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch(Exception ee) {
                System.out.println("Error setting native LAF: " + ee);
            }
        }
		
        boolean toSplash = true;
        if(args.length>0) {
                if("nosplash".equals(args[0])) toSplash = false;
        }

        SplashScreen splash = null;
        if(toSplash) splash = new SplashScreen();
                              
        try {
            GlobalOptions.Load();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MainScreen st = new MainScreen();              
       
        Util.centerWindow(st);        
        if(toSplash) splash.kill();        
        st.setVisible(true);
    }
    
}
