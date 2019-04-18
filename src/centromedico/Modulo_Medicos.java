package centromedico;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import clases.Conexion;

/**
 *
 * @author Victor Roberto Bravo Torres
 */
public class Modulo_Medicos extends javax.swing.JInternalFrame {

    String id = "";

    public Modulo_Medicos() {
        initComponents();
        modificar.setEnabled(false);
        eliminar.setEnabled(false);
        registrar.setEnabled(false);
    }

    // Metodo para limpiar campos
    public void limpiarCampos() {
        txtIdMedico.setText("");
        txtNombresMedico.setText("");
        txtApellidosMedico.setText("");
        listaEspecialidad.setSelectedIndex(0);
        experiencia.setText("");
    }

    // Metodo para habilitar boton Registrar
    public void habilitarBoton() {
        if (!txtIdMedico.getText().isEmpty() && !txtNombresMedico.getText().isEmpty()
                && !txtApellidosMedico.getText().isEmpty() && listaEspecialidad.getSelectedIndex() != 0 && !modificar.isEnabled()) {

            registrar.setEnabled(true);
        } else {

            registrar.setEnabled(false);
        }
    }

    // Mostrar opciones de busqueda
    public void mostrarBusqueda() {
        jLabel7.setVisible(true);
        txtBuscar.setVisible(true);
        buscar.setVisible(true);
        resultado.setText("");
    }

    // Ocultar opciones de busqueda
    public void ocultarBusqueda() {
        jLabel7.setVisible(false);
        txtBuscar.setVisible(false);
        buscar.setVisible(false);
        txtBuscar.setText("");
        resultado.setText("");
    }

    public void habilitarCampos() {
        txtIdMedico.setEnabled(true);
        txtNombresMedico.setEnabled(true);
        txtApellidosMedico.setEnabled(true);
        listaEspecialidad.setEnabled(true);
        experiencia.setEnabled(true);
    }

    public void inhabilitarCampos() {
        txtIdMedico.setEnabled(false);
        txtNombresMedico.setEnabled(false);
        txtApellidosMedico.setEnabled(false);
        listaEspecialidad.setEnabled(false);
        experiencia.setEnabled(false);
    }

    // Generar reporte en Excel .xls
    public void reporteExcel() {
        HSSFWorkbook doc = new HSSFWorkbook();
        HSSFSheet sheet = doc.createSheet("Medicos");

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select * from medicos");

            ResultSet rs = pst.executeQuery();

            // Nombre de la Empresa
            CellStyle empresaEstilo = doc.createCellStyle();
            empresaEstilo.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            empresaEstilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            empresaEstilo.setAlignment(HorizontalAlignment.CENTER);
            empresaEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            HSSFFont font1 = doc.createFont();
            font1.setFontName("Arial");
            font1.setBold(true);
            font1.setItalic(true);
            font1.setUnderline(Byte.MAX_VALUE);
            font1.setColor(IndexedColors.BLACK.getIndex());
            font1.setFontHeightInPoints((short) 20);
            empresaEstilo.setFont(font1);

            HSSFRow empresa = sheet.createRow((short) 0);
            HSSFCell cellEmpresa = empresa.createCell(0);
            cellEmpresa.setCellValue("Centro Medico Roberto Bravo");
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            cellEmpresa.setCellStyle(empresaEstilo);

            // Nombre del Reporte
            CellStyle tituloEstilo = doc.createCellStyle();
            tituloEstilo.setFillForegroundColor(IndexedColors.BLACK.getIndex());
            tituloEstilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            HSSFFont font2 = doc.createFont();
            font2.setFontName("Arial");
            font2.setBold(true);
            font2.setItalic(true);
            font2.setColor(IndexedColors.WHITE.getIndex());
            font2.setFontHeightInPoints((short) 16);
            tituloEstilo.setFont(font2);

            HSSFRow titulo = sheet.createRow((short) 1);
            HSSFCell celdaTitulo = titulo.createCell(0);
            celdaTitulo.setCellValue("Reporte de Medicos");
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
            celdaTitulo.setCellStyle(tituloEstilo);

            // Encabezados
            CellStyle headStyle = doc.createCellStyle();
            headStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headStyle.setAlignment(HorizontalAlignment.CENTER);
            headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headStyle.setBorderTop(BorderStyle.THIN);
            headStyle.setBorderLeft(BorderStyle.THIN);
            headStyle.setBorderRight(BorderStyle.THIN);
            headStyle.setBorderBottom(BorderStyle.THIN);
            HSSFFont font3 = doc.createFont();
            font3.setFontName("Arial");
            font3.setBold(true);
            font3.setItalic(true);
            font3.setColor(IndexedColors.BLACK.getIndex());
            font3.setFontHeightInPoints((short) 12);
            headStyle.setFont(font3);

            HSSFRow rowhead = sheet.createRow((short) 2);
            HSSFCell head1 = rowhead.createCell(0);
            HSSFCell head2 = rowhead.createCell(1);
            HSSFCell head3 = rowhead.createCell(2);
            HSSFCell head4 = rowhead.createCell(3);
            HSSFCell head5 = rowhead.createCell(4);
            head1.setCellValue("Identificacion");
            head1.setCellStyle(headStyle);
            head2.setCellValue("Nombres");
            head2.setCellStyle(headStyle);
            head3.setCellValue("Apellidos");
            head3.setCellStyle(headStyle);
            head4.setCellValue("Especialidad");
            head4.setCellStyle(headStyle);
            head5.setCellValue("Experiencia");
            head5.setCellStyle(headStyle);

            // Cargar Datos de la Base
            int i = 3;

            while (rs.next()) {
                String idm = rs.getString("idMedico");
                String nm = rs.getString("nombreMedico");
                String am = rs.getString("apellidoMedico");
                String esp = rs.getString("especialidad");
                String exp = rs.getString("experiencia");

                CellStyle datosStyle = doc.createCellStyle();
                datosStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                datosStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                datosStyle.setAlignment(HorizontalAlignment.CENTER);
                datosStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                datosStyle.setBorderTop(BorderStyle.THIN);
                datosStyle.setBorderLeft(BorderStyle.THIN);
                datosStyle.setBorderRight(BorderStyle.THIN);
                datosStyle.setBorderBottom(BorderStyle.THIN);
                HSSFFont font4 = doc.createFont();
                font4.setFontName("Arial");
                font4.setItalic(true);
                font4.setColor(IndexedColors.BLACK.getIndex());
                font4.setFontHeightInPoints((short) 12);
                datosStyle.setFont(font4);

                HSSFRow r = sheet.createRow((short) i);
                HSSFCell id = r.createCell(0);
                HSSFCell nombre = r.createCell(1);
                HSSFCell apellido = r.createCell(2);
                HSSFCell especialidad = r.createCell(3);
                HSSFCell experiencia = r.createCell(4);
                id.setCellValue(idm);
                id.setCellStyle(datosStyle);
                nombre.setCellValue(nm);
                nombre.setCellStyle(datosStyle);
                apellido.setCellValue(am);
                apellido.setCellStyle(datosStyle);
                especialidad.setCellValue(esp);
                especialidad.setCellStyle(datosStyle);
                experiencia.setCellValue(exp);
                experiencia.setCellStyle(datosStyle);
                i++;

                sheet.setColumnWidth(0, 5000);
                sheet.setColumnWidth(1, 7000);
                sheet.setColumnWidth(2, 7000);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);

                try (FileOutputStream out = new FileOutputStream("Reporte Medicos.xls")) {
                    doc.write(out);
                }
            }
            JOptionPane.showMessageDialog(null, "Reporte generado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        System.gc();
    }

    // Generar Reporte en PDF
    public void reportePdf() {
        Document documento = new Document();

        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Medicos.pdf"));
            documento.open();

            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Identificacion");
            tabla.addCell("Nombres del Medico");
            tabla.addCell("Apellidos del Medico");
            tabla.addCell("Especialidad");
            tabla.addCell("Experiencia");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select * from medicos");

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                    } while (rs.next());
                    documento.add(tabla);
                }
            } catch (DocumentException | SQLException e) {

            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte Generado correctamente");

        } catch (DocumentException | HeadlessException | FileNotFoundException e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdMedico = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombresMedico = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellidosMedico = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        registrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        experiencia = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        modificar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        nuevo = new javax.swing.JButton();
        consultarMedico = new javax.swing.JButton();
        reporteMedicos = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        resultado = new javax.swing.JLabel();
        listaEspecialidad = new javax.swing.JComboBox<>();

        setBorder(null);
        setClosable(true);
        setTitle("Gestionar Medicos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Datos del Medico");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 12, -1, -1));

        jLabel2.setText("Identificacion:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 71, -1, -1));

        txtIdMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdMedicoKeyReleased(evt);
            }
        });
        getContentPane().add(txtIdMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 69, 207, -1));

        jLabel3.setText("Nombres:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 97, -1, -1));

        txtNombresMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresMedicoKeyReleased(evt);
            }
        });
        getContentPane().add(txtNombresMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 95, 207, -1));

        jLabel4.setText("Apellidos:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 123, -1, -1));

        txtApellidosMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidosMedicoKeyReleased(evt);
            }
        });
        getContentPane().add(txtApellidosMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 121, 207, -1));

        jLabel5.setText("Especialidad");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 152, -1, -1));

        registrar.setText("Registrar");
        registrar.setEnabled(false);
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        getContentPane().add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 249, 92, 38));

        jLabel6.setText("Experiencia:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 48, -1, -1));

        experiencia.setColumns(20);
        experiencia.setRows(5);
        jScrollPane1.setViewportView(experiencia);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 72, 247, 101));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 191, 626, 10));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Opciones");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 207, -1, -1));

        modificar.setText("Modificar");
        modificar.setEnabled(false);
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        getContentPane().add(modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 249, 92, 39));

        eliminar.setText("Eliminar");
        eliminar.setEnabled(false);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 249, 92, 39));

        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        getContentPane().add(nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 249, 92, 39));

        consultarMedico.setText("Consultar Medico");
        consultarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarMedicoActionPerformed(evt);
            }
        });
        getContentPane().add(consultarMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 306, 156, 39));

        reporteMedicos.setText("Reporte de Medicos");
        reporteMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteMedicosActionPerformed(evt);
            }
        });
        getContentPane().add(reporteMedicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 306, 156, 39));

        jLabel7.setText("Consultar Medico:  ");
        jLabel7.setVisible(false);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 369, -1, -1));

        txtBuscar.setVisible(false);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 363, 305, 28));

        buscar.setText("Buscar");
        buscar.setEnabled(false);
        buscar.setPreferredSize(new java.awt.Dimension(75, 28));
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.setVisible(false);
        getContentPane().add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 363, 113, -1));

        resultado.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        getContentPane().add(resultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 409, 544, 20));

        listaEspecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una Opcion", "Cardiologia", "Dermatologia", "Ginecologia", "Medicina General", "Neurologia", "Oftalmologia", "Otorrinolaringologia", "Pediatria", "Traumatologia" }));
        listaEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaEspecialidadActionPerformed(evt);
            }
        });
        getContentPane().add(listaEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 148, 208, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarMedicoActionPerformed
        limpiarCampos();
        mostrarBusqueda();

    }//GEN-LAST:event_consultarMedicoActionPerformed

    // Buscar registro de medicos en la base de datos
    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select * from medicos where idMedico = ?");
            pst.setString(1, txtBuscar.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txtIdMedico.setText(rs.getString("idMedico"));
                txtNombresMedico.setText(rs.getString("nombreMedico"));
                txtApellidosMedico.setText(rs.getString("apellidoMedico"));
                listaEspecialidad.setSelectedItem(rs.getString("especialidad"));
                experiencia.setText(rs.getString("experiencia"));
                id = txtBuscar.getText().trim();
                habilitarCampos();
                ocultarBusqueda();
                modificar.setEnabled(true);
                eliminar.setEnabled(true);
                buscar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Medico no Registrado");
            }
        } catch (HeadlessException | SQLException e) {
        }
        System.gc();
    }//GEN-LAST:event_buscarActionPerformed

    // Modificar datos de medico de la base de datos
    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("update medicos set idMedico = ?, nombreMedico = ?, apellidoMedico = ?, especialidad = ?, experiencia = ? where idMedico = " + id);

            pst.setString(1, txtIdMedico.getText().trim());
            pst.setString(2, txtNombresMedico.getText().trim());
            pst.setString(3, txtApellidosMedico.getText().trim());
            pst.setString(4, listaEspecialidad.getSelectedItem().toString());
            pst.setString(5, experiencia.getText().trim());
            pst.executeUpdate();

            limpiarCampos();
            inhabilitarCampos();
            modificar.setEnabled(false);
            eliminar.setEnabled(false);
            resultado.setText("Los datos han sido modificados correctamente");
        } catch (SQLException e) {
        }
        System.gc();
    }//GEN-LAST:event_modificarActionPerformed

    // Eliminar registro de medico en base de datos
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("delete from medicos where idMedico = ?");

            pst.setString(1, id);
            pst.executeUpdate();
            limpiarCampos();
            inhabilitarCampos();
            resultado.setText("Los datos han sido eliminados correctamente");
            modificar.setEnabled(false);
            eliminar.setEnabled(false);
            System.gc();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        limpiarCampos();
        ocultarBusqueda();
        resultado.setText("");
        modificar.setEnabled(false);
        eliminar.setEnabled(false);
        registrar.setEnabled(false);
        habilitarCampos();
        System.gc();
    }//GEN-LAST:event_nuevoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (!txtBuscar.getText().isEmpty()) {
            buscar.setEnabled(true);
        } else {
            buscar.setEnabled(false);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into medicos values(?,?,?,?,?)");

            pst.setString(1, txtIdMedico.getText().trim());
            pst.setString(2, txtNombresMedico.getText().trim());
            pst.setString(3, txtApellidosMedico.getText().trim());
            pst.setString(4, listaEspecialidad.getSelectedItem().toString());
            pst.setString(5, experiencia.getText().trim());
            pst.executeUpdate();

            limpiarCampos();
            inhabilitarCampos();
            resultado.setText("Los datos han sido guardados correctamente");
            registrar.setEnabled(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ya existe un registro con esa identificaciòn");
        }
        System.gc();

    }//GEN-LAST:event_registrarActionPerformed

    private void txtIdMedicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdMedicoKeyReleased
        habilitarBoton();
    }//GEN-LAST:event_txtIdMedicoKeyReleased

    private void txtNombresMedicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresMedicoKeyReleased
        habilitarBoton();
    }//GEN-LAST:event_txtNombresMedicoKeyReleased

    private void txtApellidosMedicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosMedicoKeyReleased
        habilitarBoton();
    }//GEN-LAST:event_txtApellidosMedicoKeyReleased

    private void reporteMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteMedicosActionPerformed

        limpiarCampos();
        resultado.setText("");
        String opcion = "";
        int validar = 0;
        try {
            do {
                try {
                    do {
                        opcion = JOptionPane.showInputDialog(null, " Escoge el tipo de reporte que deseas:\n\n 1.- Reporte PDF\n 2.- Reporte Excel\n\n");
                        validar = Integer.parseInt(opcion);
                    } while (validar != 1 && validar != 2);

                    switch (validar) {
                        case 1:
                            reportePdf();
                            limpiarCampos();
                            break;

                        case 2:
                            reporteExcel();
                            limpiarCampos();
                            break;

                        default:

                            break;
                    }
                } catch (NullPointerException | NumberFormatException e) {

                }
            } while (opcion.equals(""));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cancelaste la generacion del reporte");
        }
        System.gc();
    }//GEN-LAST:event_reporteMedicosActionPerformed

    private void listaEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaEspecialidadActionPerformed
        habilitarBoton();
    }//GEN-LAST:event_listaEspecialidadActionPerformed

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JButton consultarMedico;
    private javax.swing.JButton eliminar;
    private javax.swing.JTextArea experiencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> listaEspecialidad;
    private javax.swing.JButton modificar;
    private javax.swing.JButton nuevo;
    private javax.swing.JButton registrar;
    private javax.swing.JButton reporteMedicos;
    private javax.swing.JLabel resultado;
    private javax.swing.JTextField txtApellidosMedico;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtIdMedico;
    private javax.swing.JTextField txtNombresMedico;
    // End of variables declaration//GEN-END:variables
}
