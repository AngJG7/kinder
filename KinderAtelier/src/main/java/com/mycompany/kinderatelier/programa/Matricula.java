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
    public void setValor(Double valor){
        this.valor = valor;
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
    /**
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
            
            Paragraph info = new Paragraph("Nombre: "+estudiante.getNombreCompleto()+"\nFecha de Nacimiento: "+estudiante.getFechaNacimiento()+"\nTipo de sangre: "+estudiante.getTipoSangre()+"\nAlergias: "+estudiante.getAlergias()+"\nHabilidades: "+estudiante.getHabilidades()+"\nDirección: "+estudiante.getDireccion()+"\nValor: "+valor,font);
            doc.add(info);
            
            
            doc.close();
            
        } catch (DocumentException | java.io.FileNotFoundException e){
            e.printStackTrace();
        }
        return "Cargando el documento...";
    }
    */
    public String generarMatriculaPdf(String nombreKinder, String nitKinder) {
    String nombreArchivo = "matricula_" + numero + ".pdf";
    try {
        Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo));
        doc.open();

        // Colores y fuentes corporativas
        BaseColor azulOscuro = new BaseColor(30, 81, 123);
        BaseColor grisTexto = new BaseColor(60, 60, 60);

        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, azulOscuro);
        Font fontSubHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.BLACK);
        Font fontSeccion = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, azulOscuro);
        Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, grisTexto);
        Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10, grisTexto);
        Font fontPie = FontFactory.getFont(FontFactory.TIMES_ITALIC, 9, BaseColor.GRAY);

        // 1. Encabezado de la Institución
        Paragraph pKinder = new Paragraph(nombreKinder.toUpperCase(), fontHeader);
        pKinder.setAlignment(Element.ALIGN_CENTER);
        doc.add(pKinder);

        Paragraph pNit = new Paragraph("NIT: " + nitKinder, fontSubHeader);
        pNit.setAlignment(Element.ALIGN_CENTER);
        pNit.setSpacingAfter(10);
        doc.add(pNit);

        // Línea divisoria
        Paragraph linea = new Paragraph("________________________________________________________________________", fontSubHeader);
        linea.setAlignment(Element.ALIGN_CENTER);
        linea.setSpacingAfter(15);
        doc.add(linea);

        // 2. Título principal
        Paragraph pTitulo = new Paragraph("CONSTANCIA DE MATRÍCULA No. " + numero, fontTitulo);
        pTitulo.setAlignment(Element.ALIGN_CENTER);
        pTitulo.setSpacingAfter(20);
        doc.add(pTitulo);

        // 3. Información del Acudiente
        Paragraph pSecAcudiente = new Paragraph("DATOS DEL ACUDIENTE RESPONSABLE", fontSeccion);
        pSecAcudiente.setSpacingAfter(6);
        doc.add(pSecAcudiente);

        Paragraph pAcudiente = new Paragraph();
        pAcudiente.setLeading(16f); // Espaciado entre líneas
        pAcudiente.add(new Chunk("Nombre: ", fontBold));
        pAcudiente.add(new Chunk(estudiante.getNombreAcudiente() + "\n", fontNormal));
        pAcudiente.add(new Chunk("Documento: ", fontBold));
        pAcudiente.add(new Chunk(estudiante.getDocumentoAcudiente() + "\n", fontNormal));
        pAcudiente.add(new Chunk("Calidad/Parentesco: ", fontBold));
        pAcudiente.add(new Chunk(estudiante.getParentesco() + "\n", fontNormal));
        pAcudiente.add(new Chunk("Teléfono registrado: ", fontBold));
        pAcudiente.add(new Chunk(estudiante.getTelefonoAcudiente() + "\n", fontNormal));
        pAcudiente.setSpacingAfter(15);
        doc.add(pAcudiente);

        // 4. Información del Estudiante
        Paragraph pSecEstudiante = new Paragraph("DATOS DEL ESTUDIANTE", fontSeccion);
        pSecEstudiante.setSpacingAfter(6);
        doc.add(pSecEstudiante);

        Paragraph pEstudiante = new Paragraph();
        pEstudiante.setLeading(16f);
        pEstudiante.add(new Chunk("Nombre completo: ", fontBold));
        pEstudiante.add(new Chunk(estudiante.getNombreCompleto() + "\n", fontNormal));
        pEstudiante.add(new Chunk("Documento: ", fontBold));
        pEstudiante.add(new Chunk(estudiante.getDocumento() + "\n", fontNormal));
        pEstudiante.setSpacingAfter(15);
        doc.add(pEstudiante);

        // 5. Detalles de la Matrícula
        Paragraph pSecMatricula = new Paragraph("CONDICIONES DE LA MATRÍCULA", fontSeccion);
        pSecMatricula.setSpacingAfter(6);
        doc.add(pSecMatricula);

        Paragraph pMatricula = new Paragraph();
        pMatricula.setLeading(16f);
        pMatricula.add(new Chunk("Fecha de registro: ", fontBold));
        pMatricula.add(new Chunk(fecha + "\n", fontNormal));
        pMatricula.add(new Chunk("Año lectivo: ", fontBold));
        pMatricula.add(new Chunk(anioLectivo + "\n", fontNormal));
        pMatricula.add(new Chunk("Talleres inscritos: ", fontBold));
        pMatricula.add(new Chunk(listarRamas() + "\n", fontNormal));
        pMatricula.add(new Chunk("Valor matrícula: ", fontBold));
        pMatricula.add(new Chunk(String.format("$%.0f", valor) + "\n", fontNormal));
        pMatricula.add(new Chunk("Estado: ", fontBold));
        pMatricula.add(new Chunk(estado.toUpperCase() + "\n", fontNormal));
        pMatricula.setSpacingAfter(25);
        doc.add(pMatricula);

        // 6. Pie de página
        Paragraph pie = new Paragraph(
            "Como acudiente responsable, usted es el contacto autorizado para autorizaciones y retiros.",
            fontPie
        );
        pie.setAlignment(Element.ALIGN_CENTER);
        doc.add(pie);

        doc.close();
        return "Cargando el documento...";

    } catch (DocumentException | java.io.FileNotFoundException e) {
        e.printStackTrace();
        return "Error al generar la constancia en PDF.";
    }
}
    
}