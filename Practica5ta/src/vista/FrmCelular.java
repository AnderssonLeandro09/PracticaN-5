package vista;

import controlador.dao.CelularDAO;
import controlador.dao.MarcaDAO;
import controlador.ed.listas.ListaEnlazada;
import javax.swing.JOptionPane;
import modelo.Marca;
import modelo.tabla.ModeloTablaMarca;
import vista.utilidades.Utilidades;

/**
 *
 * @author Andersson
 */
public class FrmCelular extends java.awt.Dialog {

    private ModeloTablaMarca modeloT = new ModeloTablaMarca();
    private CelularDAO celularD = new CelularDAO();
    private MarcaDAO marcaD = new MarcaDAO();
    private Integer fila = -1;

    /**
     * Creates new form Jframe
     */
    public FrmCelular(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarCombo();
        cargarTabla();
    }
    public void cargarCombo(){
        try {
            Utilidades.cargarEquipo(cbxMarca, celularD);
        } catch (Exception e) {
        }
    }
    
    public void cargarTabla() {
        modeloT.setDato(marcaD.listar());
        tblTabla.setModel(modeloT);
        tblTabla.updateUI();
    }

    public void limpiar() {
        txtVersion.setText("");
        txtSiglas.setText("");
        cargarTabla();
        cargarCombo();
        marcaD.setMarca(null);
        fila = -1;
        txtBusqueda.setText("");
        cbxCriterio.setSelectedIndex(0);
    }
    public void guardar() {
        if (txtVersion.getText().trim().isEmpty() || txtSiglas.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese datos", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            try {
                marcaD.getMarca().setVersion(txtVersion.getText());
                marcaD.getMarca().setSiglas(txtSiglas.getText());
                marcaD.getMarca().setId_celular(celularD.buscarPorMarca(cbxMarca.getSelectedItem().toString()).getId());
                if (marcaD.getMarca().getId() != null) {
                    marcaD.modificar(fila);
                    limpiar();
                } else {
                    marcaD.guardar();
                    limpiar();
                }
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void seleccionar() {
        fila = tblTabla.getSelectedRow();
        if (fila >= 0) {
            try {
                Integer id = modeloT.getDato().get(fila).getId();
                marcaD.setMarca(marcaD.obtener(id));
                txtVersion.setText(marcaD.getMarca().getVersion());
                txtSiglas.setText(marcaD.getMarca().getSiglas());
                cbxMarca.setSelectedItem(celularD.obtener(marcaD.getMarca().getId_celular()).getVersion().toUpperCase());
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione algo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void busquedaLinealBinaria() {
        String por = cbxCriterio.getSelectedItem().toString();
        String texto = txtBusqueda.getText();
        try {
            if (por.equalsIgnoreCase("Version")) {
                modeloT.setDato(marcaD.busquedaLinealIdMarca(texto));
            }
            if (por.equalsIgnoreCase("Siglas")) {
                modeloT.setDato(marcaD.busquedaLinealSiglas(texto));
            }
            if (por.equalsIgnoreCase("Marca")) {
                modeloT.setDato(marcaD.busquedaLinealMarca(texto));
            }
            tblTabla.setModel(modeloT);
            tblTabla.updateUI();

        } catch (Exception e) {
            System.out.println("xd");
        }
    }

    public void busquedaBinaria() {
        ListaEnlazada<Marca> lista = new ListaEnlazada<>();
        String criterio = cbxCriterio.getSelectedItem().toString();
        String texto = txtBusqueda.getText();

        try {
            if (criterio.equalsIgnoreCase("Version")) {
                Marca da = marcaD.busquedaBinariaIdMarca(texto);
                if (da != null) {
                    lista.insertar(da);
                    modeloT.setDato(lista);
                } else {
                    modeloT.setDato(new ListaEnlazada<>()); // Si no se encontró el anime, se asigna una lista vacía al modelo
                }
            }
            if (criterio.equalsIgnoreCase("Siglas")) {
                Marca da = marcaD.busquedaBinariaSiglas(texto);
                if (da != null) {
                    lista.insertar(da);
                    modeloT.setDato(lista);
                } else {
                    modeloT.setDato(new ListaEnlazada<>()); // Si no se encontró el anime, se asigna una lista vacía al modelo
                }
            }
            if (criterio.equalsIgnoreCase("Marca")) {
                Marca da = marcaD.busquedaBinariaMarca(texto);
                if (da != null) {
                    lista.insertar(da);
                    modeloT.setDato(lista);
                } else {
                    modeloT.setDato(new ListaEnlazada<>()); // Si no se encontró el anime, se asigna una lista vacía al modelo
                }
            }
            tblTabla.setModel(modeloT);
            tblTabla.updateUI();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtVersion = new javax.swing.JTextField();
        txtSiglas = new javax.swing.JTextField();
        cbxMarca = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        cbxCriterio = new javax.swing.JComboBox<>();
        btnBusquedaLineal = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnBusquedaBinaria = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TABLA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        tblTabla.setBackground(new java.awt.Color(153, 153, 153));
        tblTabla.setForeground(new java.awt.Color(255, 255, 255));
        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTabla.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tblTabla.setSelectionForeground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(tblTabla);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DATOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Versión:");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Siglas:");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Marca:");

        txtVersion.setBackground(new java.awt.Color(255, 255, 255));
        txtVersion.setForeground(new java.awt.Color(102, 102, 102));
        txtVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVersionActionPerformed(evt);
            }
        });

        txtSiglas.setBackground(new java.awt.Color(255, 255, 255));
        txtSiglas.setForeground(new java.awt.Color(102, 102, 102));

        cbxMarca.setBackground(new java.awt.Color(255, 255, 255));
        cbxMarca.setForeground(new java.awt.Color(102, 102, 102));
        cbxMarca.setMaximumRowCount(12);
        cbxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMarcaActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(102, 102, 102));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(16, 16, 16))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSiglas, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(btnGuardar)))
                        .addContainerGap(24, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSiglas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BUSQUEDA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(204, 204, 204));

        cbxCriterio.setBackground(new java.awt.Color(255, 255, 255));
        cbxCriterio.setForeground(new java.awt.Color(102, 102, 102));
        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Marca", "Siglas", "Version" }));

        btnBusquedaLineal.setBackground(new java.awt.Color(102, 102, 102));
        btnBusquedaLineal.setForeground(new java.awt.Color(255, 255, 255));
        btnBusquedaLineal.setText("BusquedaLineal");
        btnBusquedaLineal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaLinealActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Criterio");

        btnBusquedaBinaria.setBackground(new java.awt.Color(102, 102, 102));
        btnBusquedaBinaria.setForeground(new java.awt.Color(255, 255, 255));
        btnBusquedaBinaria.setText("BusquedaBinaria");
        btnBusquedaBinaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaBinariaActionPerformed(evt);
            }
        });

        txtBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        txtBusqueda.setForeground(new java.awt.Color(102, 102, 102));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Buscar:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxCriterio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 81, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBusquedaBinaria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBusquedaLineal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(59, 59, 59)))
                .addGap(16, 16, 16))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBusquedaBinaria)
                .addGap(18, 18, 18)
                .addComponent(btnBusquedaLineal)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBusquedaLinealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaLinealActionPerformed
        // TODO add your handling code here:
        busquedaLinealBinaria();
    }//GEN-LAST:event_btnBusquedaLinealActionPerformed

    private void btnBusquedaBinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaBinariaActionPerformed
        // TODO add your handling code here:
        busquedaBinaria();
    }//GEN-LAST:event_btnBusquedaBinariaActionPerformed

    private void cbxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMarcaActionPerformed

    private void txtVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVersionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVersionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmCelular dialog = new FrmCelular(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusquedaBinaria;
    private javax.swing.JButton btnBusquedaLineal;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable tblTabla;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtSiglas;
    private javax.swing.JTextField txtVersion;
    // End of variables declaration//GEN-END:variables
}
