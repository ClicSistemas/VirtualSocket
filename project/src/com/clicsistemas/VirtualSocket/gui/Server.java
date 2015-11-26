/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicsistemas.VirtualSocket.gui;

import com.clicsistemas.VirtualSocket.GlobalOptions;
import com.clicsistemas.VirtualSocket.SocketServer;
import com.clicsistemas.VirtualSocket.Util;
import com.clicsistemas.VirtualSocket.gui.controls.IConnControl;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author marcelo
 */
public class Server extends javax.swing.JPanel implements IConnControl {
    private String ip;
    private int port;
    private ServerSocket server;
    private SocketServer socketServer;
    private Socket client;

    /**
     * Creates new form Server
     */
    public Server() {
        initComponents();
        
        this.connDetailsControl1.setConnectButtonText("Listen");
        this.connDetailsControl1.setListener(this);
        this.sendControl1.setListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connDetailsControl1 = new com.clicsistemas.VirtualSocket.gui.controls.ConnDetailsControl();
        connStateControl1 = new com.clicsistemas.VirtualSocket.gui.controls.ConnStateControl();
        sendControl1 = new com.clicsistemas.VirtualSocket.gui.controls.SendControl();
        logo1 = new com.clicsistemas.VirtualSocket.gui.controls.Logo();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connStateControl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sendControl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(connDetailsControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(connDetailsControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connStateControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.clicsistemas.VirtualSocket.gui.controls.ConnDetailsControl connDetailsControl1;
    private com.clicsistemas.VirtualSocket.gui.controls.ConnStateControl connStateControl1;
    private com.clicsistemas.VirtualSocket.gui.controls.Logo logo1;
    private com.clicsistemas.VirtualSocket.gui.controls.SendControl sendControl1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void OnClickConnect() {
        if(server!=null) {
            disconnect();            
        } else {
            connect();
        }
    }

    @Override
    public void OnSendMessage(String msg) {
        Util.SendToSocket(client, msg, this);
    }

    @Override
    public void disconnect() {
           try {
            disconnectClients(); //close any client
            
            socketServer.setStop(true);
        } catch (Exception e) {
            error(e.getLocalizedMessage(), "Core");
        }
    }

  @Override
    public void error(String messege) {
        this.connStateControl1.error(messege, "Core");
    }

    @Override
    public void append(String string) {
        this.connStateControl1.append(string);
    }

    @Override
    public void error(String message, String where) {
        this.connStateControl1.error(message, where);
    }
   
    private void connect() {
        if(GlobalOptions.ResetOnConnect) {
            this.connStateControl1.clear();
        }
        
        this.connDetailsControl1.setEnabled(false);
        this.sendControl1.setEnabled(false);
        
        ip = this.connDetailsControl1.getHostName();
        port = this.connDetailsControl1.getPort();

        if (ip != null && ip.isEmpty() == false) {
            if (port > 0) {
                // Check the Host
                if (Util.checkHost(ip)) {                    
                    try {
                        this.connStateControl1.append("Listen to: " + ip + ":" + port);
                        
                        if(GlobalOptions.UseUDP) {
                            openUdp();
                        } else {
                            openTcp();
                        }
                     
                        this.connDetailsControl1.setConnectButtonText("Stop");
                    } catch (Exception e) {
                        this.connStateControl1.error(e.getLocalizedMessage(), "Listen");
                    }                    
                } else {
                    this.connStateControl1.error("Invalid Hostname or IP Address", "Listen");
                }
            } else {
                this.connStateControl1.error("Invalid Port Number", "Listen");
            }
        } else {
            this.connStateControl1.error("Invalid Hostname or IP Address", "Listen");
        }
        
        this.connDetailsControl1.setEnabled(true);
        this.sendControl1.setEnabled(true);
    }

    private void openTcp() throws IOException {
        InetAddress bindAddr=null;
        
        if(!ip.equals("0.0.0.0"))
            bindAddr = InetAddress.getByName(ip);
        else
            bindAddr = null;
         
        server = new ServerSocket(port,1,bindAddr);
        socketServer=SocketServer.handle(this,server);
        
        append("Tcp Server Started");
    }

    private void openUdp() {
        error("UDP Not Implemented Yet", "Core");
    }

    @Override
    public void setClientSocket(Socket socket) {
        this.client = socket;
        this.connStateControl1.setConnectedTo(socket.getInetAddress().getHostName());
    }

    private void disconnectClients() {
       try {
            socketServer.setDisconnected(true);
        } catch (Exception e) {}
    }
   
}
