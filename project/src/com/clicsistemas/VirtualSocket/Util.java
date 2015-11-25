package com.clicsistemas.VirtualSocket;

import com.clicsistemas.VirtualSocket.gui.controls.IConnControl;
import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Akshathkumar Shetty
 */
public class Util {

    private static final ClassLoader cl = Util.class.getClassLoader();
    public static ImageIcon Logo = new ImageIcon(
            cl.getResource("icons/logo.png"));
    public static ImageIcon Arrow = new ImageIcon(
            cl.getResource("icons/arrow.png"));

    public static final String NEW_LINE = "\r\n";

    public static void centerWindow(Window win) {
        Dimension dim = win.getToolkit().getScreenSize();
        win.setLocation(dim.width / 2 - win.getWidth() / 2,
                dim.height / 2 - win.getHeight() / 2);
    }

    public static boolean checkHost(String host) {
        try {
            InetAddress.getByName(host);
            return (true);
        } catch (UnknownHostException uhe) {
            return (false);
        }
    }
    
    public static void openWebpage(URI uri) {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    public static void writeFile(String fileName, String text)
            throws IOException {
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(fileName)));
        out.print(text);
        out.close();
    }

    public static String readFile(String fileName)
            throws IOException {
        StringBuffer sb = new StringBuffer();
        InputStream is = cl.getResourceAsStream(fileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String s;
        while ((s = in.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        in.close();
        return sb.toString();
    }
    
    public static void SendToSocket(Socket socket, String msg, IConnControl control) {
         try {                       
           PrintWriter clientOut = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);          
           
           control.append("S: " + msg);
           
           clientOut.print(msg);
           clientOut.flush();
        } catch (Exception e) {
            control.error(e.getLocalizedMessage(), "Send");
        }
    }
}
