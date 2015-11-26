/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicsistemas.VirtualSocket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class GlobalOptions {
    public static boolean TLSSocket;    
    public static boolean UseUDP;
    public static boolean ShowSymbols;
    public static boolean IncludeCrLF;
    public static boolean ResetOnConnect;

    public static void Load() throws IOException {
        Properties props = new Properties();
        FileInputStream stream = new FileInputStream("./VirtualSocket.properties");
        props.load(stream);
        
        TLSSocket = Boolean.valueOf(props.getProperty("TLSSocket", "false"));
        UseUDP = Boolean.valueOf(props.getProperty("UseUDP", "false"));
        IncludeCrLF = Boolean.valueOf(props.getProperty("IncludeCrLF", "true"));
        ShowSymbols = Boolean.valueOf(props.getProperty("ShowSymbols", "true"));
        ResetOnConnect = Boolean.valueOf(props.getProperty("ResetOnConnect", "false"));
    }
    
    public static void Save() throws FileNotFoundException, IOException {
        Properties props = new Properties();
        FileOutputStream stream = new FileOutputStream("./VirtualSocket.properties");
        
        props.setProperty("TLSSocket", String.valueOf(TLSSocket));
        props.setProperty("UseUDP", String.valueOf(UseUDP));
        props.setProperty("IncludeCrLF", String.valueOf(IncludeCrLF));
        props.setProperty("ShowSymbols", String.valueOf(ShowSymbols));
        props.setProperty("ResetOnConnect", String.valueOf(ResetOnConnect));
        
        props.store(stream, "Virtual Socket Configuration");
        stream.flush();
        stream.close();
    }
}
