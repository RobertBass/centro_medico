package centromedico;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Centro_Medico extends javax.swing.JFrame {

    public Centro_Medico() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        // Wallpaper
        ImageIcon wallpaper = new ImageIcon("src/img/RB002.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(labelFondo.getWidth(), labelFondo.getHeight() , Image.SCALE_DEFAULT));
        labelFondo.setIcon(icono);
        this.repaint();
    }
    
    // Establecer icono de ventana
    @Override
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/RB001b.jpg"));
        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        labelFondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuOpciones = new javax.swing.JMenu();
        itemPaciente = new javax.swing.JMenuItem();
        itemMedicos = new javax.swing.JMenuItem();
        itemHC = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();
        menuCitas = new javax.swing.JMenu();
        agendarCitas = new javax.swing.JMenuItem();
        atenderCitas = new javax.swing.JMenuItem();
        menuInfo = new javax.swing.JMenu();
        info = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Centro Medico Roberto Bravo");
        setIconImage(getIconImage());

        escritorio.setBackground(new java.awt.Color(204, 204, 204));
        escritorio.setPreferredSize(new java.awt.Dimension(650, 500));

        escritorio.setLayer(labelFondo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1500, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
        );

        menuOpciones.setText("Administrar");
        menuOpciones.setAutoscrolls(true);
        menuOpciones.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuOpciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuOpciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuOpciones.setIconTextGap(25);
        menuOpciones.setPreferredSize(new java.awt.Dimension(130, 21));

        itemPaciente.setText("Gestionar Pacientes");
        itemPaciente.setPreferredSize(new java.awt.Dimension(130, 21));
        itemPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPacienteActionPerformed(evt);
            }
        });
        menuOpciones.add(itemPaciente);

        itemMedicos.setText("Gestionar Medicos");
        itemMedicos.setPreferredSize(new java.awt.Dimension(130, 21));
        itemMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMedicosActionPerformed(evt);
            }
        });
        menuOpciones.add(itemMedicos);

        itemHC.setText("Historias Clinicas");
        itemHC.setPreferredSize(new java.awt.Dimension(130, 21));
        itemHC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHCActionPerformed(evt);
            }
        });
        menuOpciones.add(itemHC);

        salir.setText("Salir");
        salir.setPreferredSize(new java.awt.Dimension(130, 21));
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        menuOpciones.add(salir);

        jMenuBar1.add(menuOpciones);

        menuCitas.setText("Citas Medicas");
        menuCitas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        agendarCitas.setText("Agendar Citas");
        agendarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agendarCitasActionPerformed(evt);
            }
        });
        menuCitas.add(agendarCitas);

        atenderCitas.setText("Atender Citas");
        menuCitas.add(atenderCitas);

        jMenuBar1.add(menuCitas);

        menuInfo.setText("Informacion");
        menuInfo.setAutoscrolls(true);
        menuInfo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuInfo.setIconTextGap(25);
        menuInfo.setPreferredSize(new java.awt.Dimension(130, 21));

        info.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        info.setText("Acerca de");
        info.setPreferredSize(new java.awt.Dimension(130, 21));
        info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoActionPerformed(evt);
            }
        });
        menuInfo.add(info);

        jMenuBar1.add(menuInfo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPacienteActionPerformed
        Modulo_Pacientes pacientes = new Modulo_Pacientes();
        this.escritorio.add(pacientes);
        pacientes.show();
    }//GEN-LAST:event_itemPacienteActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void itemMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMedicosActionPerformed
        Modulo_Medicos medicos = new Modulo_Medicos();
        this.escritorio.add(medicos);
        medicos.show();
    }//GEN-LAST:event_itemMedicosActionPerformed

    private void itemHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHCActionPerformed
        Historia_Clinica historia = new Historia_Clinica();
        this.escritorio.add(historia);
        historia.show();
    }//GEN-LAST:event_itemHCActionPerformed

    private void infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoActionPerformed
        new Creador().setVisible(true);
        
    }//GEN-LAST:event_infoActionPerformed

    private void agendarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agendarCitasActionPerformed
        Agenda agenda = new Agenda();
        this.escritorio.add(agenda);
        agenda.show();
    
    }//GEN-LAST:event_agendarCitasActionPerformed

    public static void main(String args[]) {
        /* Set the Metal look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Metal (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Centro_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Centro_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Centro_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Centro_Medico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Centro_Medico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem agendarCitas;
    private javax.swing.JMenuItem atenderCitas;
    public javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuItem info;
    private javax.swing.JMenuItem itemHC;
    private javax.swing.JMenuItem itemMedicos;
    private javax.swing.JMenuItem itemPaciente;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel labelFondo;
    private javax.swing.JMenu menuCitas;
    private javax.swing.JMenu menuInfo;
    private javax.swing.JMenu menuOpciones;
    private javax.swing.JMenuItem salir;
    // End of variables declaration//GEN-END:variables
}
