/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicsistemas.VirtualSocket.gui.controls;

import java.net.Socket;

/**
 *
 * @author marcelo
 */
public interface IConnControl {
    public void OnClickConnect(); 
    public void OnSendMessage(String msg);

    public void error(String messege);
    public void disconnect();
    public void append(String string, String kind);
    public void error(String message, String where);

    public void setClientSocket(Socket socket);
}
