package com.mycompany.kinderatelier.view;
import com.mycompany.kinderatelier.programa.KinderAtelier;
import com.mycompany.kinderatelier.programa.Estudiante;
import com.mycompany.kinderatelier.programa.Profesor;
import com.mycompany.kinderatelier.programa.Matricula;
import com.mycompany.kinderatelier.lectura.Lector;

public class VentanaEstudiantes extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaEstudiantes.class.getName());
    private KinderAtelier kinder;
    public VentanaEstudiantes(KinderAtelier dKinder) {
        initComponents();
        kinder = dKinder;
    }
    private Estudiante estudianteBuscado() {
        String documento;
        Estudiante estudiante;

        documento = documentoEstudiantes.getText().trim();
        if (documento.isEmpty()) {
            Lector.mostrar("Escriba primero el documento del estudiante");
            return null;
        }
        estudiante = kinder.buscarEstudiante(documento);
        if (estudiante == null) {
            Lector.mostrar("No existe un estudiante con el documento " + documento);
        }
        return estudiante;
    }
    private void mostrarNotas() {
        Estudiante estudiante;
        Profesor profesor;

        estudiante = estudianteBuscado();
        if (estudiante == null) {
            return;
        }
        profesor = kinder.buscarProfesor(
                Lector.leerTexto("Documento del profesor que consulta:"));
        if (profesor == null) {
            Lector.mostrar("No existe un profesor con ese documento.");
            return;
        }
        Lector.mostrar(profesor.verNotasEstudiante(estudiante) + "\n"
                + profesor.obtenerPromedioEstudiante(estudiante));
    }
    private void asignarTalento() {
        Estudiante estudiante;
        String lista;
        int eleccion;

        estudiante = estudianteBuscado();
        if (estudiante == null) {
            return;
        }
        lista = "Talento de " + estudiante.getNombreCompleto() + ":\n\n";
        for (int i = 0; i < KinderAtelier.TALENTOS.length; i++) {
            lista = lista + (i + 1) + ". " + KinderAtelier.TALENTOS[i] + "\n";
        }
        eleccion = Lector.leerEntero(lista);
        while (eleccion < 1 || eleccion > KinderAtelier.TALENTOS.length) {
            Lector.mostrar("Opcion invalida.");
            eleccion = Lector.leerEntero(lista);
        }
        kinder.asignarTalento(estudiante.getDocumento(),
                KinderAtelier.TALENTOS[eleccion - 1]);
        Lector.mostrar(kinder.getUltimoMensaje());
    }
    private void agregarNota() {
        Estudiante estudiante;
        Profesor profesor;
        float nota;

        estudiante = estudianteBuscado();
        if (estudiante == null) {
            return;
        }
        profesor = kinder.buscarProfesor(
                Lector.leerTexto("Documento del profesor que califica:"));
        if (profesor == null) {
            Lector.mostrar("No existe un profesor con ese documento.");
            return;
        }
        nota = Lector.leerFloat("Ingrese la nota (0.0 a 5.0):");
        if (profesor.agregarNotaAEstudiante(estudiante, nota)) {
            Lector.mostrar("Nota agregada con exito.");
        } else {
            Lector.mostrar("No se pudo agregar la nota (fuera de rango 0-5 o limite de 5 notas alcanzado).");
        }
    }
    private void registrarPatologia() {
        Estudiante estudiante;
        String texto;

        estudiante = estudianteBuscado();
        if (estudiante == null) {
            return;
        }
        texto = Lector.leerTexto("Informe de patologias fisicas o mentales del estudiante:");
        estudiante.setPatologias(texto);
        Lector.mostrar("Patologias registradas para " + estudiante.getNombreCompleto());
    }
    private void generarBoletin() {
        Estudiante estudiante;

        estudiante = estudianteBuscado();
        if (estudiante == null) {
            return;
        }
        Lector.mostrar(kinder.generarBoletinPdf(estudiante.getDocumento()));
    }
    private void hojaDeVida() {
        Estudiante estudiante;
        Matricula matricula;

        estudiante = estudianteBuscado();
        if (estudiante == null) {
            return;
        }
        matricula = kinder.buscarMatriculaActiva(estudiante.getDocumento());
        if (matricula == null) {
            Lector.mostrar("El estudiante no tiene una matricula activa.");
            return;
        }
        Lector.mostrar(kinder.generarMatriculaPdf(matricula.getNumero()));
    }
    public static void nuevoEstudiante(KinderAtelier kinder) {
        Estudiante nuevo;
        String documento;
        String nombres;
        String apellidos;
        String telefono;
        String eps;
        String fechaNacimiento;
        String tipoSangre;
        String alergias;
        String habilidades;
        String direccion;
        String nombrePadre;
        String documentoPadre;
        String telefonoPadre;
        String nombreMadre;
        String documentoMadre;
        String telefonoMadre;

        documento = Lector.leerTexto("Documento del estudiante:");
        if (documento.isEmpty()) {
            Lector.mostrar("Registro cancelado");
            return;
        }
        if (kinder.buscarEstudiante(documento) != null) {
            Lector.mostrar("Ya existe un estudiante con ese documento!");
            return;
        }
        nombres = Lector.leerTexto("Nombres:");
        apellidos = Lector.leerTexto("Apellidos:");
        telefono = Lector.leerTexto("Telefono:");
        eps = Lector.leerTexto("EPS:");
        fechaNacimiento = Lector.leerTexto("Fecha de nacimiento (dd/mm/aaaa):");
        tipoSangre = Lector.leerTexto("Tipo de sangre:");
        alergias = Lector.leerTexto("Alergias (deje vacio si no tiene):");
        habilidades = Lector.leerTexto("Habilidades que muestra el nino:");
        direccion = Lector.leerTexto("Direccion:");
        nombrePadre = Lector.leerTexto("Nombre del padre:");
        documentoPadre = Lector.leerTexto("Documento del padre:");
        telefonoPadre = Lector.leerTexto("Telefono del padre:");
        nombreMadre = Lector.leerTexto("Nombre de la madre:");
        documentoMadre = Lector.leerTexto("Documento de la madre:");
        telefonoMadre = Lector.leerTexto("Telefono de la madre:");

        nuevo = new Estudiante(documento, nombres, apellidos, telefono, eps,
                fechaNacimiento, tipoSangre, alergias, habilidades, direccion,
                nombrePadre, documentoPadre, telefonoPadre,
                nombreMadre, documentoMadre, telefonoMadre);

        if (kinder.agregarEstudiante(nuevo)) {
            Lector.mostrar("Estudiante registrado:\n\n" + nuevo.mostrarDatos());
        } else {
            Lector.mostrar(kinder.getUltimoMensaje());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        documentoEstudiantes = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        verListaEstudiantes = new javax.swing.JButton();
        verAcudiente = new javax.swing.JButton();
        verHistorial = new javax.swing.JButton();
        verNotas = new javax.swing.JButton();
        asignarTalentoBtn = new javax.swing.JButton();
        agregarNotaBtn = new javax.swing.JButton();
        registrarPatologiaBtn = new javax.swing.JButton();
        generarBoletinBtn = new javax.swing.JButton();
        generarHojaDeVida = new javax.swing.JButton();
        verDatos = new javax.swing.JButton();
        volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 0, 36));
        jLabel1.setText("ESTUDIANTES");

        documentoEstudiantes.addActionListener(this::documentoEstudiantesActionPerformed);

        jLabel2.setText("Ingrese el documento del estudiante a buscar:");

        verListaEstudiantes.setText("Ver lista de todos los estudiantes");
        verListaEstudiantes.addActionListener(this::verListaEstudiantesActionPerformed);

        verAcudiente.setText("Ver acudiente");
        verAcudiente.addActionListener(this::verAcudienteActionPerformed);

        verHistorial.setText("Ver historial");
        verHistorial.addActionListener(this::verHistorialActionPerformed);

        verNotas.setMnemonic('V');
        verNotas.setText("Ver notas");
        verNotas.addActionListener(this::verNotasActionPerformed);

        asignarTalentoBtn.setText("Asignar talento");
        asignarTalentoBtn.addActionListener(this::asignarTalentoBtnActionPerformed);

        agregarNotaBtn.setText("Agregar nota");
        agregarNotaBtn.addActionListener(this::agregarNotaBtnActionPerformed);

        registrarPatologiaBtn.setText("Registrar patologia");
        registrarPatologiaBtn.addActionListener(this::registrarPatologiaBtnActionPerformed);

        generarBoletinBtn.setText("Generar Boletin");
        generarBoletinBtn.addActionListener(this::generarBoletinBtnActionPerformed);

        generarHojaDeVida.setText("Generar Hoja de Vida");
        generarHojaDeVida.addActionListener(this::generarHojaDeVidaActionPerformed);

        verDatos.setText("Ver Datos");
        verDatos.addActionListener(this::verDatosActionPerformed);

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
                        .addGap(207, 207, 207)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(documentoEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(verListaEstudiantes)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(generarHojaDeVida)
                        .addGap(18, 18, 18)
                        .addComponent(agregarNotaBtn)
                        .addGap(18, 18, 18)
                        .addComponent(registrarPatologiaBtn)
                        .addGap(18, 18, 18)
                        .addComponent(generarBoletinBtn)
                        .addGap(40, 40, 40)
                        .addComponent(volver)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(verDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(verAcudiente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(verHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(verNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(asignarTalentoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(documentoEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(verListaEstudiantes)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verAcudiente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(asignarTalentoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generarHojaDeVida)
                    .addComponent(agregarNotaBtn)
                    .addComponent(registrarPatologiaBtn)
                    .addComponent(generarBoletinBtn))
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(volver)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void documentoEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documentoEstudiantesActionPerformed
        Estudiante estudiante = estudianteBuscado();
        if (estudiante != null) {
            Lector.mostrar(estudiante.mostrarDatos());
        }
    }//GEN-LAST:event_documentoEstudiantesActionPerformed

    private void verListaEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verListaEstudiantesActionPerformed
        Lector.mostrar(kinder.listarEstudiantes());
    }//GEN-LAST:event_verListaEstudiantesActionPerformed

    private void verAcudienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verAcudienteActionPerformed
        Estudiante estudiante = estudianteBuscado();
        if (estudiante != null) {
            Lector.mostrar(estudiante.mostrarAcudiente());
        }
    }//GEN-LAST:event_verAcudienteActionPerformed

    private void verHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verHistorialActionPerformed
        Estudiante estudiante = estudianteBuscado();
        if (estudiante != null){
            Lector.mostrar(kinder.historialEstudiante(estudiante.getDocumento()));
        }
    }//GEN-LAST:event_verHistorialActionPerformed

    private void verNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verNotasActionPerformed
        Estudiante estudiante = estudianteBuscado();
        if (estudiante != null) {
            mostrarNotas();
        }
    }//GEN-LAST:event_verNotasActionPerformed

    private void verDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verDatosActionPerformed
        Estudiante estudiante = estudianteBuscado();
        if (estudiante != null) {
            Lector.mostrar(estudiante.mostrarDatos());
        }
    }//GEN-LAST:event_verDatosActionPerformed

    private void asignarTalentoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asignarTalentoBtnActionPerformed
        asignarTalento();
    }//GEN-LAST:event_asignarTalentoBtnActionPerformed

    private void agregarNotaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarNotaBtnActionPerformed
        agregarNota();
    }//GEN-LAST:event_agregarNotaBtnActionPerformed

    private void registrarPatologiaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarPatologiaBtnActionPerformed
        registrarPatologia();
    }//GEN-LAST:event_registrarPatologiaBtnActionPerformed

    private void generarBoletinBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarBoletinBtnActionPerformed
        generarBoletin();
    }//GEN-LAST:event_generarBoletinBtnActionPerformed

    private void generarHojaDeVidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarHojaDeVidaActionPerformed
        hojaDeVida();
    }//GEN-LAST:event_generarHojaDeVidaActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        this.dispose();
    }//GEN-LAST:event_volverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarNotaBtn;
    private javax.swing.JButton asignarTalentoBtn;
    private javax.swing.JTextField documentoEstudiantes;
    private javax.swing.JButton generarBoletinBtn;
    private javax.swing.JButton generarHojaDeVida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton registrarPatologiaBtn;
    private javax.swing.JButton verAcudiente;
    private javax.swing.JButton verDatos;
    private javax.swing.JButton verHistorial;
    private javax.swing.JButton verListaEstudiantes;
    private javax.swing.JButton verNotas;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}

