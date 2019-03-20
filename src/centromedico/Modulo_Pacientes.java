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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import clases.Conexion;

public class Modulo_Pacientes extends javax.swing.JInternalFrame {

    String id = ""; // Variable para busquedas en base de datos para modificar o eliminar

    public Modulo_Pacientes() {
        initComponents();
        modificar.setEnabled(false);
        eliminar.setEnabled(false);
        registrar.setEnabled(false);
        
    }

    // Metodo para limpiar campos
    public void limpiarCampos() {
        txtId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        listaGenero.setSelectedIndex(0);
    }

    // Metodo para habilitar boton Registrar
    public void habilitarBoton() {
        if (!txtId.getText().isEmpty() && !txtNombres.getText().isEmpty()
                && !txtApellidos.getText().isEmpty() && !txtEdad.getText().isEmpty()
                && listaGenero.getSelectedIndex() != 0 && !modificar.isEnabled()) {

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
        txtId.setEnabled(true);
        txtNombres.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtEdad.setEnabled(true);
        listaGenero.setEnabled(true);
    }

    public void inhabilitarCampos() {
        txtId.setEnabled(false);
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtEdad.setEnabled(false);
        listaGenero.setEnabled(false);
    }

    // Reporte en Excel
    public static void reporteExcel() {
        HSSFWorkbook doc = new HSSFWorkbook();
        HSSFSheet sheet = doc.createSheet("Pacientes");

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select * from pacientes");

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
            celdaTitulo.setCellValue("Reporte de Pacientes");
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
            head4.setCellValue("Edad");
            head4.setCellStyle(headStyle);
            head5.setCellValue("Genero");
            head5.setCellStyle(headStyle);

            // Cargar Datos de la Base
            int i = 3;

            while (rs.next()) {
                String idp = rs.getString("idPaciente");
                String np = rs.getString("nombrePaciente");
                String ap = rs.getString("apellidoPaciente");
                int ep = rs.getInt("edadPaciente");
                String gen = rs.getString("genero");

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
                HSSFCell edad = r.createCell(3);
                HSSFCell genero = r.createCell(4);
                id.setCellValue(idp);
                id.setCellStyle(datosStyle);
                nombre.setCellValue(np);
                nombre.setCellStyle(datosStyle);
                apellido.setCellValue(ap);
                apellido.setCellStyle(datosStyle);
                edad.setCellValue(ep);
                edad.setCellStyle(datosStyle);
                genero.setCellValue(gen);
                genero.setCellStyle(datosStyle);
                i++;

                sheet.setColumnWidth(0, 5000);
                sheet.setColumnWidth(1, 5000);
                sheet.setColumnWidth(2, 5000);
                sheet.setColumnWidth(3, 5000);
                sheet.setColumnWidth(4, 5000);

                try (FileOutputStream out = new FileOutputStream("Reporte Pacientes.xls")) {
                    doc.write(out);
                }
            }
            JOptionPane.showMessageDialog(null, "Reporte generado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        System.gc();
    }

    // Reporte en PDF
    public static void reportePdf() {
        Document documento = new Document();

        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Pacientes.pdf"));

            Image header = Image.getInstance("src/img/header.png");
            header.scaleToFit(650, 500);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Calibri", 20, Font.BOLDITALIC, BaseColor.DARK_GRAY));
            parrafo.add("Centro Medico Roberto Bravo\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Listado de Pacientes Registrados\n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(5);
            tabla.setSpacingAfter(WIDTH);
            tabla.setWidthPercentage(100);
            tabla.addCell("Identificacion");
            tabla.addCell("Nombres del Paciente");
            tabla.addCell("Apellidos del Paciente");
            tabla.addCell("Edad");
            tabla.addCell("Genero");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("select * from pacientes");

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

        } catch (Exception e) {
        }
        System.gc();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        registrar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        nuevo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        reporte = new javax.swing.JButton();
        consultar = new javax.swing.JButton();
        resultado = new javax.swing.JLabel();
        listaGenero = new javax.swing.JComboBox<>();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setBorder(null);
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Gestionar Pacientes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Datos del Paciente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 8, -1, -1));

        jLabel2.setText("Identificacion:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 46, -1, -1));

        jLabel3.setText("Nombres:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 72, -1, -1));

        jLabel4.setText("Apellidos:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 72, -1, -1));

        jLabel5.setText("Edad:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 100, -1, -1));

        jLabel6.setText("Genero:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 100, -1, -1));

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 44, 180, -1));

        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresKeyReleased(evt);
            }
        });
        getContentPane().add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 70, 180, -1));

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidosKeyReleased(evt);
            }
        });
        getContentPane().add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 70, 218, -1));

        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEdadKeyReleased(evt);
            }
        });
        getContentPane().add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 98, 180, -1));

        buscar.setText("Buscar");
        buscar.setEnabled(false);
        buscar.setPreferredSize(new java.awt.Dimension(75, 28));
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.setVisible(false);
        getContentPane().add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 347, 113, -1));

        jLabel7.setText("Consultar Paciente:");
        jLabel7.setVisible(false);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 353, -1, -1));

        txtBuscar.setVisible(false);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 347, 302, 28));

        registrar.setText("Registrar");
        registrar.setEnabled(false);
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        getContentPane().add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 203, 92, 38));

        modificar.setText("Modificar");
        modificar.setEnabled(false);
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        getContentPane().add(modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 203, 92, 38));

        eliminar.setText("Eliminar");
        eliminar.setEnabled(false);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 203, 92, 38));

        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        getContentPane().add(nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(506, 203, 92, 38));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 139, 646, 10));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Opciones");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 161, -1, -1));

        reporte.setText("Reporte de Pacientes");
        reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteActionPerformed(evt);
            }
        });
        getContentPane().add(reporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 271, -1, 38));

        consultar.setText("Consultar Paciente");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        getContentPane().add(consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 271, 156, 38));

        resultado.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        getContentPane().add(resultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 406, 624, 20));

        listaGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una Opcion", "Masculino", "Femenino" }));
        listaGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaGeneroActionPerformed(evt);
            }
        });
        getContentPane().add(listaGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 96, 218, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        limpiarCampos();
        mostrarBusqueda();

    }//GEN-LAST:event_consultarActionPerformed
    // Buscar en base de datos para consultar, modificar o eliminar pacientes
    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select * from pacientes where idPaciente = ?");
            pst.setString(1, txtBuscar.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txtId.setText(rs.getString("idPaciente"));
                txtNombres.setText(rs.getString("nombrePaciente"));
                txtApellidos.setText(rs.getString("apellidoPaciente"));
                txtEdad.setText(rs.getString("edadPaciente"));
                listaGenero.setSelectedItem(rs.getString("genero"));
                id = txtBuscar.getText().trim();
                habilitarCampos();
                ocultarBusqueda();
                modificar.setEnabled(true);
                eliminar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Paciente no Registrado");
            }
        } catch (HeadlessException | SQLException e) {
        }
        System.gc();
    }//GEN-LAST:event_buscarActionPerformed

    // Modificar informacion de pacientes en base de datos
    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("update pacientes set idPaciente = ?, nombrePaciente = ?, apellidoPaciente = ?, edadPaciente = ?, genero = ? where idPaciente = " + id);

            pst.setString(1, txtId.getText().trim());
            pst.setString(2, txtNombres.getText().trim());
            pst.setString(3, txtApellidos.getText().trim());
            pst.setInt(4, Integer.parseInt(txtEdad.getText().trim()));
            pst.setString(5, listaGenero.getSelectedItem().toString());
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
    // Eliminar paciente de la base de datos
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("delete from pacientes where idPaciente = ?");

            pst.setString(1, id);
            pst.executeUpdate();
            limpiarCampos();
            inhabilitarCampos();
            resultado.setText("Los datos han sido eliminados correctamente");
            modificar.setEnabled(false);
            eliminar.setEnabled(false);
        } catch (SQLException e) {
        }
        System.gc();
    }//GEN-LAST:event_eliminarActionPerformed

    // Nuevo registro
    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        limpiarCampos();
        habilitarCampos();
        ocultarBusqueda();
        resultado.setText("");
        modificar.setEnabled(false);
        eliminar.setEnabled(false);
        registrar.setEnabled(false);

    }//GEN-LAST:event_nuevoActionPerformed

    /* Activar/desctivar boton validando si hay dato ingresado
       en el campo de texto*/
    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (!txtBuscar.getText().isEmpty()) {
            buscar.setEnabled(true);
        } else {
            buscar.setEnabled(false);
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    // Guardar datos de pacientes nuevos
    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into pacientes values(?,?,?,?,?)");

            pst.setString(1, txtId.getText().trim());
            pst.setString(2, txtNombres.getText().trim());
            pst.setString(3, txtApellidos.getText().trim());
            pst.setInt(4, Integer.parseInt(txtEdad.getText().trim()));
            pst.setString(5, listaGenero.getSelectedItem().toString());
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

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        habilitarBoton();
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyReleased
        habilitarBoton();
    }//GEN-LAST:event_txtNombresKeyReleased

    private void txtEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyReleased
        habilitarBoton();
    }//GEN-LAST:event_txtEdadKeyReleased

    private void txtApellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyReleased
        habilitarBoton();
    }//GEN-LAST:event_txtApellidosKeyReleased

    private void reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteActionPerformed

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
    }//GEN-LAST:event_reporteActionPerformed

    private void listaGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaGeneroActionPerformed
        habilitarBoton();
    }//GEN-LAST:event_listaGeneroActionPerformed

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JButton consultar;
    private javax.swing.JButton eliminar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> listaGenero;
    private javax.swing.JButton modificar;
    private javax.swing.JButton nuevo;
    private javax.swing.JButton registrar;
    private javax.swing.JButton reporte;
    private javax.swing.JLabel resultado;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
