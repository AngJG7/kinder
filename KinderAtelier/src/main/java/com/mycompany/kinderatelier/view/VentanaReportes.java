package com.mycompany.kinderatelier.view;
import com.mycompany.kinderatelier.programa.KinderAtelier;
import com.mycompany.kinderatelier.programa.Estudiante;
import com.mycompany.kinderatelier.programa.Profesor;
import com.mycompany.kinderatelier.programa.Matricula;
import com.mycompany.kinderatelier.lectura.Lector;

public class VentanaReportes extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaReportes.class.getName());

    private KinderAtelier kinder;
    public VentanaReportes(KinderAtelier dKinder) {
        initComponents();
        kinder = dKinder;
    }
    private String elegirDeLista(String[] opciones, String mensaje) {
        String lista;
        int eleccion;

        lista = mensaje + "\n\n";
        for (int i = 0; i < opciones.length; i++) {
            lista = lista + (i + 1) + ". " + opciones[i] + "\n";
        }
        eleccion = Lector.leerEntero(lista);
        while (eleccion < 1 || eleccion > opciones.length) {
            Lector.mostrar("Opcion invalida.");
            eleccion = Lector.leerEntero(lista);
        }
        return opciones[eleccion - 1];
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ocupacionTalleres = new javax.swing.JButton();
        ocupacionPorTaller = new javax.swing.JButton();
        catalogoActividades = new javax.swing.JButton();
        talentosPorCategoria = new javax.swing.JButton();
        distribucionTalentos = new javax.swing.JButton();
        top10 = new javax.swing.JButton();
        generarPdfTop10Btn = new javax.swing.JButton();
        aplicarDescuentoBtn = new javax.swing.JButton();
        volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 0, 36)); // NOI18N
        jLabel1.setText("REPORTES");

        ocupacionTalleres.setText("Ocupacion de talleres");
        ocupacionTalleres.addActionListener(this::ocupacionTalleresActionPerformed);

        ocupacionPorTaller.setText("Estudiantes por taller");
        ocupacionPorTaller.addActionListener(this::ocupacionPorTallerActionPerformed);

        catalogoActividades.setText("Catálogo de actividades");
        catalogoActividades.addActionListener(this::catalogoActividadesActionPerformed);

        talentosPorCategoria.setText("Talentos por categoria");
        talentosPorCategoria.addActionListener(this::talentosPorCategoriaActionPerformed);

        distribucionTalentos.setText("Distribución de talentos");
        distribucionTalentos.addActionListener(this::distribucionTalentosActionPerformed);

        top10.setText("Top 10 estudiantes");
        top10.addActionListener(this::top10ActionPerformed);

        generarPdfTop10Btn.setText("Generar PDF");
        generarPdfTop10Btn.addActionListener(this::generarPdfTop10BtnActionPerformed);

        aplicarDescuentoBtn.setText("Aplicar descuentos Top 10");
        aplicarDescuentoBtn.addActionListener(this::aplicarDescuentoBtnActionPerformed);

        volver.setText("Volver");
        volver.setToolTipText("");
        volver.addActionListener(this::volverActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(volver)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ocupacionTalleres)
                    .addComponent(catalogoActividades)
                    .addComponent(distribucionTalentos)
                    .addComponent(aplicarDescuentoBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(talentosPorCategoria)
                    .addComponent(ocupacionPorTaller)
                    .addComponent(top10)
                    .addComponent(generarPdfTop10Btn))
                .addGap(118, 118, 118))
            .addGroup(layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ocupacionTalleres)
                    .addComponent(ocupacionPorTaller))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(catalogoActividades)
                    .addComponent(talentosPorCategoria))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(distribucionTalentos)
                    .addComponent(top10))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aplicarDescuentoBtn)
                    .addComponent(generarPdfTop10Btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(volver)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ocupacionTalleresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ocupacionTalleresActionPerformed
        Lector.mostrar(kinder.mostrarOcupacion());
    }//GEN-LAST:event_ocupacionTalleresActionPerformed

    private void ocupacionPorTallerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ocupacionPorTallerActionPerformed
        String taller = elegirDeLista(KinderAtelier.RAMAS, "de cual taller quiere ver la lista?");
        Lector.mostrar(kinder.listarPorRama(taller));
    }//GEN-LAST:event_ocupacionPorTallerActionPerformed

    private void catalogoActividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catalogoActividadesActionPerformed
        Lector.mostrar(kinder.listarTodasLasRamas());
    }//GEN-LAST:event_catalogoActividadesActionPerformed

    private void talentosPorCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_talentosPorCategoriaActionPerformed
        String talento = elegirDeLista(KinderAtelier.TALENTOS, "Cual categoria de talento?");
        Lector.mostrar(kinder.listarPorTalento(talento));
    }//GEN-LAST:event_talentosPorCategoriaActionPerformed

    private void distribucionTalentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distribucionTalentosActionPerformed
       Lector.mostrar(kinder.mostrarDistribucionTalentos());
    }//GEN-LAST:event_distribucionTalentosActionPerformed

    private void top10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_top10ActionPerformed
        Lector.mostrar(kinder.generarMejores10Texto());
    }//GEN-LAST:event_top10ActionPerformed

    private void generarPdfTop10BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarPdfTop10BtnActionPerformed
        Lector.mostrar(kinder.generarMejores10Pdf());
        try {
            String archivo = "mejores10.pdf";
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                Runtime.getRuntime().exec("cmd /c start " + archivo);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("xdg-open " + archivo);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open " + archivo);
            }
        } catch (Exception e) {
            logger.log(java.util.logging.Level.WARNING, "No se pudo abrir el PDF del Top 10", e);
        }
    }//GEN-LAST:event_generarPdfTop10BtnActionPerformed

    private void aplicarDescuentoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplicarDescuentoBtnActionPerformed
        kinder.aplicarDescuentoMejores10();
        Estudiante[] mejores = kinder.mejores10();
        StringBuilder resumen = new StringBuilder("Valor de matricula tras aplicar el descuento:\n\n");
        boolean hayDatos = false;
        for (Estudiante e : mejores) {
            if (e == null) continue;
            Matricula matricula = kinder.buscarMatriculaActiva(e.getDocumento());
            if (matricula != null) {
                hayDatos = true;
                resumen.append(e.getNombreCompleto())
                        .append(" (Doc. ").append(e.getDocumento()).append("): $")
                        .append(String.format("%.0f", matricula.getValor()))
                        .append("\n");
            }
        }
        if (!hayDatos) {
            resumen.append("No hay estudiantes del Top 10 con matricula activa.");
        }
        Lector.mostrar(resumen.toString());
    }//GEN-LAST:event_aplicarDescuentoBtnActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aplicarDescuentoBtn;
    private javax.swing.JButton catalogoActividades;
    private javax.swing.JButton distribucionTalentos;
    private javax.swing.JButton generarPdfTop10Btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton ocupacionPorTaller;
    private javax.swing.JButton ocupacionTalleres;
    private javax.swing.JButton talentosPorCategoria;
    private javax.swing.JButton top10;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}

