package centromedico;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Creador extends javax.swing.JFrame {

    public Creador() {
        initComponents();
        setResizable(false);
        setTitle("Acerca del Creador");
        setLocationRelativeTo(null);
        
        // Wallpaper
        ImageIcon wallpaper = new ImageIcon("src/img/wallpaper.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(labelWallpaper.getWidth(), labelWallpaper.getHeight() , Image.SCALE_DEFAULT));
        labelWallpaper.setIcon(icono);
        this.repaint();
        
        // Wallpaper
        ImageIcon logo = new ImageIcon("src/img/RB002.jpg");
        Icon icono2 = new ImageIcon(logo.getImage().getScaledInstance(label_Logo.getWidth(), label_Logo.getHeight() , Image.SCALE_DEFAULT));
        label_Logo.setIcon(icono2);
        this.repaint();
        
    }
    
    @Override
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/RB003b.png"));
        return retValue;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        botonOk = new javax.swing.JButton();
        label_Logo = new javax.swing.JLabel();
        labelWallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acerca de");
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(550, 550));
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setFont(new java.awt.Font("Arial Narrow", 3, 24)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 0, 0));
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("Creado por Robert - Bass ®");
        getContentPane().add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 265, -1));

        label2.setFont(new java.awt.Font("Arial Narrow", 3, 24)); // NOI18N
        label2.setForeground(new java.awt.Color(0, 0, 0));
        label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2.setText("Centro Medico");
        getContentPane().add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 265, -1));

        label3.setFont(new java.awt.Font("Arial Narrow", 3, 24)); // NOI18N
        label3.setForeground(new java.awt.Color(0, 0, 0));
        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setText("Sistema Modelo");
        getContentPane().add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 265, -1));

        label4.setFont(new java.awt.Font("Arial Narrow", 3, 24)); // NOI18N
        label4.setForeground(new java.awt.Color(0, 0, 0));
        label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label4.setText("Version 1.0");
        getContentPane().add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 265, -1));

        botonOk.setFont(new java.awt.Font("Arial Narrow", 3, 18)); // NOI18N
        botonOk.setText("Aceptar");
        botonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOkActionPerformed(evt);
            }
        });
        getContentPane().add(botonOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, -1, -1));
        getContentPane().add(label_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 220));
        getContentPane().add(labelWallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOkActionPerformed
        dispose();
    }//GEN-LAST:event_botonOkActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Creador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Creador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Creador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Creador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Creador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonOk;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel labelWallpaper;
    private javax.swing.JLabel label_Logo;
    // End of variables declaration//GEN-END:variables
}
