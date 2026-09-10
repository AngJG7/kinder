/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.programa;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
/**
 *
 * @author Ángela
 */
public class Matricula {
    public static final int MAX_RAMAS = 3;
    private int numero;
    private String fecha;
    private int anioLectivo;
    private Double valor;
    private String estado; /*puede ser activa o retirada*/
    private Estudiante estudiante;
    private Empleado registradaPor;
    private String motivoRetiro;
    private String fechaRetiro;
    private String[] ramas;
    private int cantidadRamas;

    public Matricula(int dNumero, String dFecha, int dAnioLectivo, Double dValor, Estudiante dEstudiante, Empleado dRegistradaPor) {
        numero = dNumero;
        fecha = dFecha;
        anioLectivo = dAnioLectivo;
        valor = dValor;
        estado = "activa";
        estudiante = dEstudiante;
        registradaPor = dRegistradaPor;
        motivoRetiro = "";
        fechaRetiro = "";
        ramas = new String[MAX_RAMAS];
        cantidadRamas = 0;
    }
    public int getNumero() {
        return numero;
    }
    public String getFecha() {
        return fecha;
    }
    public int getAnioLectivo() {
        return anioLectivo;
    }
    public String getEstado() {
        return estado;
    }
    public double getValor() {
        return valor;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    public int getCantidadRamas() {
        return cantidadRamas;
    }
    public boolean estaActiva() {
        return estado.equals("activa");
    }
    public boolean inscribirRama(String rama) {
        if (cantidadRamas >= MAX_RAMAS) {
            return false;
        }
        if (estaEnRama(rama)) {
            return false;
        }
        ramas[cantidadRamas] = rama;
        cantidadRamas = cantidadRamas + 1;
        return true;
    }
    public boolean estaEnRama(String rama) {
        for (int i = 0; i < cantidadRamas; i++) {
            if (ramas[i].equals(rama)) {
                return true;
            }
        }return false;
    }
    public String listarRamas() {
        String texto;

        if (cantidadRamas == 0) {
            return "sin ramas asignadas";
        }
        texto = ramas[0];
        for (int i = 1; i < cantidadRamas; i++) {
            texto = texto + ", " + ramas[i];
        } return texto;
    }
    public boolean retirar(String pMotivo, String pFechaRetiro) {
        if (!estaActiva()) {
            return false;
        }
        estado = "retirada";
        motivoRetiro = pMotivo;
        fechaRetiro = pFechaRetiro;
        return true;
    }
    public String mostrarDatos() {
        String datos;

        datos = """
                ===== MATRICULA =====
                Numero: """ + numero + "\n"
                + "Fecha: " + fecha + "\n"
                + "Anio lectivo: " + anioLectivo + "\n"
                + "Valor: $" + valor + "\n"
                + "Estado: " + estado + "\n"
                + "Ramas artisticas: " + listarRamas() + "\n"
                + "Estudiante: " + estudiante.getNombreCompleto()
                        + " (doc. " + estudiante.getDocumento() + ")\n"
                + "Acudiente responsable: " + estudiante.getNombreAcudiente()
                        + " - " + estudiante.getParentesco() + "\n"
                + "Registrada por: " + registradaPor.getNombreCompleto()
                        + " - " + registradaPor.getCargo() + "\n";

        if (estado.equals("retirada")) {
            datos = datos
                    + "Fecha de retiro: " + fechaRetiro + "\n"
                    + "Motivo: " + motivoRetiro + "\n";
        }

        return datos;
    }
    
    /*Formateo de bloque de texto pq no salía bien*/
    public String generarConstancia(String nombreKinder, String nitKinder) {
        return """
               ================================================
                       CONSTANCIA DE MATRICULA No. %d
                 %s - NIT %s
               ================================================

               Senor(a) %s,
               identificado(a) con documento %s,
               en calidad de %s del estudiante:

                  %s (doc. %s)

               Le informamos que la matricula fue registrada
               el %s en las siguientes condiciones:

                  Anio lectivo: %d
                  Talleres:     %s
                  Valor:        $%.0f
                  Estado:       %s

               Como acudiente responsable, usted es el contacto
               autorizado para autorizaciones y retiros.
               Telefono registrado: %s
               """.formatted(numero, nombreKinder, nitKinder,
                             estudiante.getNombreAcudiente(),
                             estudiante.getDocumentoAcudiente(),
                             estudiante.getParentesco(),
                             estudiante.getNombreCompleto(),
                             estudiante.getDocumento(), fecha,
                             anioLectivo, listarRamas(), valor, estado,
                             estudiante.getTelefonoAcudiente());
    }
    
    public String generarMatriculaPdf(String nombreKinder, String nitKinder){
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("matricula.pdf"));
            doc.open();
            
            
            Font font = FontFactory.getFont(BaseFont.TIMES_BOLD, 21, BaseColor.BLACK);
            
            
            Paragraph titulo = new Paragraph("Constancia Matricula "+estudiante.getDocumento(),font);
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);
            
            doc.add(new Paragraph("\n\n"));
            
            font = FontFactory.getFont(BaseFont.TIMES_ITALIC,12,BaseColor.BLACK);
            
            Paragraph info = new Paragraph("Nombre: "+estudiante.getNombreCompleto()+"\nFecha de Nacimiento: "+estudiante.getFechaNacimiento()+"\nTipo de sangre: "+estudiante.getTipoSangre()+"\nAlergias: "+estudiante.getAlergias()+"\nHabilidades: "+estudiante.getHabilidades()+"\nDirección: "+estudiante.getDireccion(),font);
            doc.add(info);
            
            
            doc.close();
            
        } catch (DocumentException | java.io.FileNotFoundException e){
            e.printStackTrace();
        }
        return "Cargando el documento...";
    }
    
}