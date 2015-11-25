package net.sf.sockettest;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import net.sf.sockettest.swing.About;
import net.sf.sockettest.swing.SocketTestClient;
import net.sf.sockettest.swing.SocketTestServer;
import net.sf.sockettest.swing.SocketTestUdp;
import net.sf.sockettest.swing.SplashScreen;

/**
 *
 * @author Akshathkumar Shetty
 */
public class SocketTest extends JFrame {
    private JTabbedPane tabbedPane;
    
    /** Creates a new instance of SocketTest */
    public SocketTest() {
        Container cp = getContentPane();
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        SocketTestClient client = new SocketTestClient(this);
        SocketTestServer server = new SocketTestServer(this);
        SocketTestUdp udp = new SocketTestUdp(this);
        About about = new About();
        
        tabbedPane.addTab("Client", Util.Arrow, (Component)client, "Test any server");
        tabbedPane.addTab("Server", Util.Arrow, server, "Test any client");
        tabbedPane.addTab("Udp", Util.Arrow, udp, "Test any UDP Client or Server");
        tabbedPane.addTab("About", Util.Arrow, about, "About SocketTest");        
        tabbedPane.setBorder(BorderFactory.createSoftBevelBorder(2));
        
        cp.add(tabbedPane);
    }
    
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
        
        SocketTest st = new SocketTest();
        
        st.setTitle("VirtualSocket");
        st.setSize(600,500);
        st.setBackground(Color.getHSBColor(191, 28, 56));
        
        Util.centerWindow(st);
        
        st.setDefaultCloseOperation(EXIT_ON_CLOSE);
        st.setIconImage(Util.Logo.getImage());
        
        if(toSplash) splash.kill();
        
        st.setVisible(true);
    }
    
}
