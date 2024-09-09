/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.seagile.bigmart.gui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.util.Random;

/**
 *
 * @author DTC
 */
public class Splash extends javax.swing.JFrame {

    /**
     * Creates new form Splash
     */
    public Splash() {
        initComponents();
        loading();
    }

    private void loading() {
        new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i <= 100; i++) {
                jProgressBar1.setValue(i);

                switch (i) {
                    case 10:
//                        jLabel3.setText("Loading...");
                        break;
                    case 37:
//                        jLabel3.setText("Database connetion create...");
                        break;
                    case 50:
//                        jLabel3.setText("Reports libraries loaded...");
                        break;
                    case 85:
//                        jLabel3.setText("Application loading success");
                        break;
                    case 95:
//                        jLabel3.setText("Done");
                        break;
                }

                try {
                    Thread.sleep(30 + random.nextInt(50));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            new Login().setVisible(true);
            this.dispose();
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(400, 400));
        getContentPane().setLayout(null);

        jProgressBar1.setForeground(new java.awt.Color(0, 153, 0));
        jProgressBar1.setValue(25);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(10, 360, 380, 4);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/seagile/bigmart/img/bigmart ..png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 400, 400);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        FlatIntelliJLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Splash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
