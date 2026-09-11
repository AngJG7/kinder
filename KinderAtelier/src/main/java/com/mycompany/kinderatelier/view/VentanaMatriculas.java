package com.mycompany.kinderatelier.view;
import com.mycompany.kinderatelier.programa.KinderAtelier;
import com.mycompany.kinderatelier.programa.Empleado;
import com.mycompany.kinderatelier.programa.Estudiante;
import com.mycompany.kinderatelier.programa.Matricula;
import com.mycompany.kinderatelier.lectura.Lector;

public class VentanaMatriculas extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaMatriculas.class.getName());

    private KinderAtelier kinder;

    public VentanaMatriculas(KinderAtelier dKinder) {
        initComponents();
        kinder = dKinder;
        }
    private Empleado pedirEmpleado() {
        Empleado empleado;

        empleado = kinder.buscarEmpleado(
                Lector.leerTexto("Documento del empleado que registra:"));
        if (empleado == null) {
            Lector.mostrar("No existe un empleado con ese documento.");
        }
        return empleado;
    }
    private String elegirTaller(String mensaje) {
        String lista;
        int eleccion;

        lista = mensaje + "\n\n";
        for (int i = 0; i < KinderAtelier.RAMAS.length; i++) {
            lista = lista + (i + 1) + ". " + KinderAtelier.RAMAS[i] + "\n";
        }
        eleccion = Lector.leerEntero(lista);
        while (eleccion < 1 || eleccion > KinderAtelier.RAMAS.length) {
            Lector.mostrar("Opcion invalida.");
            eleccion = Lector.leerEntero(lista);
        }
        return KinderAtelier.RAMAS[eleccion - 1];
    }
    private void hacerMatricula() {
        Empleado quien;
        Estudiante estudiante;
        Matricula nueva;
        String[] talleres;
        int cantidad;

        quien = pedirEmpleado();
        if (quien == null) {
            return;
        }
        estudiante = kinder.buscarEstudiante(
                Lector.leerTexto("Documento del estudiante:"));
        if (estudiante == null) {
            Lector.mostrar("No existe un estudiante con ese documento.");
            return;
        }
        cantidad = Lector.leerEntero("Cuantos talleres va a tomar? (1 a "
                + Matricula.MAX_RAMAS + ")");
        if (cantidad < 1 || cantidad > Matricula.MAX_RAMAS) {
            Lector.mostrar("Debe elegir entre 1 y " + Matricula.MAX_RAMAS
                    + " talleres.");
            return;
        }
        talleres = new String[cantidad];
        for (int i = 0; i < cantidad; i++) {
            talleres[i] = elegirTaller("Taller " + (i + 1) + " de "
                    + cantidad + ":");
        }
        nueva = kinder.matricular(estudiante, talleres, quien);
        if (nueva == null) {
            Lector.mostrar("No se pudo matricular:\n\n"
                    + kinder.getUltimoMensaje());
        } else {
            Lector.mostrar(kinder.getUltimoMensaje() + "\n\n"
                    + nueva.mostrarDatos());
        }
    }
    private void hacerRetiro() {
        Empleado quien;
        String documento;
        String motivo;
        String fecha;
        quien = pedirEmpleado();
        if (quien == null) {
            return;
        }
        documento = Lector.leerTexto("Documento del estudiante a retirar:");
        motivo = Lector.leerTexto("Motivo del retiro:");
        fecha = Lector.leerTexto("Fecha del retiro (dd/mm/aaaa):");
        kinder.desmatricular(documento, motivo, fecha, quien);
        Lector.mostrar(kinder.getUltimoMensaje());
    }
    private void mostrarConstancia() {
        int numero;
        int opcion;
        numero = Lector.leerEntero("Numero de la matricula:");
        opcion = Lector.leerEntero("""
                                   Como quiere la constancia?

                                   1. Ver en pantalla
                                   2. Generar PDF""");
        if (opcion == 1) {
            Lector.mostrar(kinder.generarConstancia(numero));
        } else if (opcion == 2) {
            Lector.mostrar(kinder.generarMatriculaPdf(numero));
        } else {
            Lector.mostrar("Opcion invalida.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        registrarEstudiante = new javax.swing.JButton();
        matricular = new javax.swing.JButton();
        desmatricular = new javax.swing.JButton();
        verConstancia = new javax.swing.JButton();
        volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 0, 36));
        jLabel1.setText("MATRICULAS");

        registrarEstudiante.setText("Registrar nuevo estudiante");
        registrarEstudiante.addActionListener(this::registrarEstudianteActionPerformed);

        matricular.setText("Matricular");
        matricular.addActionListener(this::matricularActionPerformed);

        desmatricular.setText("Desmatricular");
        desmatricular.setActionCommand("Desmatricular");
        desmatricular.addActionListener(this::desmatricularActionPerformed);

        verConstancia.setText("Ver constancia");
        verConstancia.addActionListener(this::verConstanciaActionPerformed);

        volver.setText("Volver");
        volver.setToolTipText("");
        volver.addActionListener(this::volverActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(matricular)
                        .addGap(40, 40, 40)
                        .addComponent(desmatricular)
                        .addGap(26, 26, 26)
                        .addComponent(verConstancia))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel1)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(registrarEstudiante)
                        .addGap(136, 136, 136))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(volver)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registrarEstudiante)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matricular)
                    .addComponent(desmatricular)
                    .addComponent(verConstancia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(volver)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void matricularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matricularActionPerformed
        hacerMatricula();
    }//GEN-LAST:event_matricularActionPerformed

    private void desmatricularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desmatricularActionPerformed
        hacerRetiro();
    }//GEN-LAST:event_desmatricularActionPerformed

    private void verConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verConstanciaActionPerformed
        mostrarConstancia();
    }//GEN-LAST:event_verConstanciaActionPerformed

    private void registrarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarEstudianteActionPerformed
        VentanaEstudiantes.nuevoEstudiante(kinder);
    }//GEN-LAST:event_registrarEstudianteActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton desmatricular;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton matricular;
    private javax.swing.JButton registrarEstudiante;
    private javax.swing.JButton verConstancia;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}

