/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clicsistemas.VirtualSocket.gui;

import com.clicsistemas.VirtualSocket.Util;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelo
 */
public class About extends javax.swing.JPanel {

    /**
     * Creates new form About
     */
    public About() {
        initComponents();
        
        loadAbout();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAbout = new javax.swing.JTextPane();
        btnPjHomePage = new javax.swing.JButton();
        btnClicHomePage = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("About"));
        setPreferredSize(new java.awt.Dimension(640, 480));

        lbTitle.setFont(new java.awt.Font("Yu Gothic UI", 1, 48)); // NOI18N
        lbTitle.setIcon(Util.Logo);
        lbTitle.setText("VirtualSocket");

        txtAbout.setEditable(false);
        jScrollPane1.setViewportView(txtAbout);

        btnPjHomePage.setText("Project Home Page");
        btnPjHomePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPjHomePageActionPerformed(evt);
            }
        });

        btnClicHomePage.setText("Clic Sistemas Home Page");
        btnClicHomePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClicHomePageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 228, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClicHomePage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPjHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPjHomePage)
                    .addComponent(btnClicHomePage))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClicHomePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClicHomePageActionPerformed
        Util.openWebpage(URI.create("http://clicsistemas.com.br"));
    }//GEN-LAST:event_btnClicHomePageActionPerformed

    private void btnPjHomePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPjHomePageActionPerformed
        Util.openWebpage(URI.create("https://github.com/ClicSistemas/VirtualSocket"));
    }//GEN-LAST:event_btnPjHomePageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClicHomePage;
    private javax.swing.JButton btnPjHomePage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextPane txtAbout;
    // End of variables declaration//GEN-END:variables

    private void loadAbout() {
        try {
            String cont = Util.readFile("readme.txt");
            this.txtAbout.setText(cont);            
        } catch (IOException ex) {
            Logger.getLogger(About.class.getName()).log(Level.SEVERE, null, ex);
            this.txtAbout.setText("Cant load the about page");
        }
    }
}
