package com.clicsistemas.VirtualSocket.gui;

import com.clicsistemas.VirtualSocket.Util;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 *
 * @author Akshathkumar Shetty
 */
public class SplashScreen extends JWindow {
    protected JLabel productName;
    
    public SplashScreen() {
        productName = new JLabel("<html><font face=\"Verdana\" size=10>"+
                "VirtualSocket",Util.Logo,JLabel.CENTER);
        //productName.setBackground(Color.white);
        productName.setOpaque(true);
        
        productName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10,10,10,10),
                BorderFactory.createLineBorder(Color.black) ));
        getContentPane().add(productName);
        Dimension dim=productName.getPreferredSize();
        dim.setSize(dim.getWidth()+10,dim.getHeight()+10);
        setSize(dim);
        Util.centerWindow(this);
        setVisible(true);
    }
    
    public void kill() {
        dispose();
    }
    
}
