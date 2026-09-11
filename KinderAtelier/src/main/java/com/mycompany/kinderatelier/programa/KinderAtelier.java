/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kinderatelier.programa;
import com.mycompany.kinderatelier.lectura.Lector;
import java.util.Arrays;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.util.ArrayList;
/**
 *
 * @author Ángela
 */
public class KinderAtelier {
    public static final int VALOR_DESCUENTO_MATRICULA = 250000;
    public static final int ANIO_LECTIVO = 2026;
    public static final int EDAD_MINIMA = 4;
    public static final int EDAD_MAXIMA = 5;
    public static final int CUPO_POR_RAMA = 15;
    public static final double VALOR_MATRICULA = 450000;
    public static final String[] RAMAS = {"Plastica", "Musica", "Teatro",
                                          "Danza", "Literatura"};
    private static final String[][] ACTIVIDADES = {
        {"Pintura con dedos", "Modelado en arcilla", "Collage",
         "Dibujo libre"},
        {"Iniciacion a la percusion", "Juegos de ritmo", "Coro infantil",
         "Flauta dulce"},
        {"Juego dramatico", "Titeres", "Expresion corporal"},
        {"Danza creativa", "Rondas tradicionales", "Ritmos latinos"},
        {"Cuenteria", "Creacion de rimas", "Teatro leido"}
    };
    private static final int MAX_REGISTROS = 100;
    private String nombre;
    private String nit;
    private String fechaActual;
    private Estudiante[] estudiantes;
    private Empleado[] empleados;
    private Matricula[] matriculas;
    private int cantidadEstudiantes;
    private int cantidadEmpleados;
    private int cantidadMatriculas;
    private int consecutivo;
    private String ultimoMensaje;
    private Estudiante[] mejores10;
    public KinderAtelier(String dNombre, String dNit, String dFechaActual) {
        nombre = dNombre;
        nit = dNit;
        fechaActual = dFechaActual;

        estudiantes = new Estudiante[MAX_REGISTROS];
        empleados = new Empleado[MAX_REGISTROS];
        matriculas = new Matricula[MAX_REGISTROS];
        mejores10 = new Estudiante[10];
        
        cantidadEstudiantes = 0;
        cantidadEmpleados = 0;
        cantidadMatriculas = 0;
        consecutivo = 1;
        ultimoMensaje = "";
        
    }

    public String getNombre() {
        return nombre;
    }
    // El kinder no imprime: guarda el mensaje y quien lo llama lo muestra
    public String getUltimoMensaje() {
        return ultimoMensaje;
    }
    public boolean agregarEstudiante(Estudiante nuevo) {
        if (cantidadEstudiantes >= MAX_REGISTROS) {
            ultimoMensaje = "No hay espacio para mas estudiantes";
            return false;
        }
        if (buscarEstudiante(nuevo.getDocumento()) != null) {
            ultimoMensaje = "Ya existe un estudiante con ese documento";
            return false;
        }
        estudiantes[cantidadEstudiantes] = nuevo;
        cantidadEstudiantes++;
        return true;
    }
    public boolean agregarEmpleado(Empleado nuevo) {
        if (cantidadEmpleados >= MAX_REGISTROS) {
            ultimoMensaje = "No hay espacio para mas empleados";
            return false;
        }
        if (buscarEmpleado(nuevo.getDocumento()) != null) {
            ultimoMensaje = "Ya existe un empleado con ese documento";
            return false;
        }
        empleados[cantidadEmpleados] = nuevo;
        cantidadEmpleados++;
        return true;
    }

    public Estudiante buscarEstudiante(String documento) {
        Estudiante encontrado;

        encontrado = null;
        for (int i = 0; i < cantidadEstudiantes; i++) {
            if (estudiantes[i].getDocumento().equals(documento)) {
                encontrado = estudiantes[i];
            }
        }return encontrado;
    }
    public Empleado buscarEmpleado(String documento) {
        Empleado encontrado;

        encontrado = null;
        for (int i = 0; i < cantidadEmpleados; i++) {
            if (empleados[i].getDocumento().equals(documento)) {
                encontrado = empleados[i];
            }
        }return encontrado;
    }
    public Profesor buscarProfesor(String documento) {
        Profesor encontrado;

        encontrado = null;
        for (int i = 0; i < cantidadEmpleados; i++) {
            if (empleados[i] instanceof Profesor && empleados[i].getDocumento().equals(documento)) {
                encontrado = (Profesor) empleados[i];
            }
        }return encontrado;
    }
    public Matricula buscarMatriculaActiva(String documento) {
        Matricula encontrada;

        encontrada = null;
        for (int i = 0; i < cantidadMatriculas; i++) {
            if (matriculas[i].getEstudiante().getDocumento().equals(documento)
                    && matriculas[i].estaActiva()
                    && matriculas[i].getAnioLectivo() == ANIO_LECTIVO) {
                encontrada = matriculas[i];
            }
        }return encontrada;
    }
    public Matricula buscarMatricula(int numero) {
        Matricula encontrada;

        encontrada = null;
        for (int i = 0; i < cantidadMatriculas; i++) {
            if (matriculas[i].getNumero() == numero) {
                encontrada = matriculas[i];
            }
        }return encontrada;
    }
    public int indiceRama(String rama) {  //cda rama asociada a un indice
        for (int i = 0; i < RAMAS.length; i++) {
            if (RAMAS[i].equals(rama)) {
                return i;
            }
        }return -1;
    }
    public boolean esRamaValida(String rama) {
        return indiceRama(rama) != -1;
    }
    public String listarActividades(String rama) {  // Actividades por rama 
        String reporte;
        int indice;
        indice = indiceRama(rama);
        if (indice == -1) {
            return "La rama " + rama + " no existe en este kinder\n";
        }
        reporte = "Actividades de " + rama + ":\n";
        for (int i = 0; i < ACTIVIDADES[indice].length; i++) {
            reporte = reporte + "- " + ACTIVIDADES[indice][i] + "\n";
        }return reporte;
    }
    public String listarTodasLasRamas() {
        String reporte;
        reporte = "TALLERES ARTISTICOS DISPONIBLES\n";
        for (int i = 0; i < RAMAS.length; i++) {
            reporte = reporte + "\n" + listarActividades(RAMAS[i]);
        }return reporte;
    }
    public int contarPorRama(String rama) {
        int contador;
        contador = 0;
        for (int i = 0; i < cantidadMatriculas; i++) {
            if (matriculas[i].estaActiva()
                    && matriculas[i].estaEnRama(rama)) {
                contador = contador + 1;
            }
        }return contador;
    }

    public boolean hayCupo(String rama) {
        return contarPorRama(rama) < CUPO_POR_RAMA;
    }
    public boolean edadValida(Estudiante estudiante) {
        int edad;
        edad = estudiante.calcularEdad();
        return edad >= EDAD_MINIMA && edad <= EDAD_MAXIMA;
    }
    // proceso de matrícula como tal
    public Matricula matricular(Estudiante estudiante, String[] ramasElegidas,
                                Empleado quien) {
        Matricula nueva;
        // Solo el personal administrativo matricula
        if (!quien.esAdministrativo()) {
            ultimoMensaje = "Solo el personal administrativo puede matricular";
            return null;
        }
        // El estudiante debe estar registrado
        if (buscarEstudiante(estudiante.getDocumento()) == null) {
            ultimoMensaje = "El estudiante no esta registrado en el sistema";
            return null;
        }
        // No puede tener otra matricula activa
        if (buscarMatriculaActiva(estudiante.getDocumento()) != null) {
            ultimoMensaje = "El estudiante ya tiene una matricula activa";
            return null;
        }
        // La edad debe estar en el rango del kinder
        if (!edadValida(estudiante)) {
            ultimoMensaje = "La edad (" + estudiante.calcularEdad()
                    + ") esta fuera del rango: " + EDAD_MINIMA
                    + " a " + EDAD_MAXIMA + " anios";
            return null;
        }
        // Debe elegir entre 1 y MAX_RAMAS talleres
        if (ramasElegidas.length == 0) {
            ultimoMensaje = "Debe elegir al menos un taller";
            return null;
        }
        if (ramasElegidas.length > Matricula.MAX_RAMAS) {
            ultimoMensaje = "Maximo " + Matricula.MAX_RAMAS
                    + " talleres por estudiante";
            return null;
        }
        // Cada taller debe existir y tener cupo
        for (int i = 0; i < ramasElegidas.length; i++) {
            if (!esRamaValida(ramasElegidas[i])) {
                ultimoMensaje = "El taller " + ramasElegidas[i]
                        + " no existe en este kinder";
                return null;
            }
            if (!hayCupo(ramasElegidas[i])) {
                ultimoMensaje = "No hay cupo disponible en "
                        + ramasElegidas[i];
                return null;
            }
        }
        nueva = new Matricula(consecutivo, fechaActual, ANIO_LECTIVO,
                              VALOR_MATRICULA, estudiante, quien);
        for (int i = 0; i < ramasElegidas.length; i++) {
            nueva.inscribirRama(ramasElegidas[i]);
        }
        matriculas[cantidadMatriculas] = nueva;
        cantidadMatriculas++;
        consecutivo++;
        ultimoMensaje = "Matricula " + nueva.getNumero()
                + " registrada en: " + nueva.listarRamas();
        return nueva;
    }
// busca por documento
    public boolean desmatricular(String documento, String motivo, String fechaRetiro, Empleado quien) {
        Matricula matricula;
        if (!quien.esAdministrativo()) {
            ultimoMensaje = "Solo el personal administrativo puede desmatricular";
            return false;
        }
        matricula = buscarMatriculaActiva(documento);
        if (matricula == null) {
            ultimoMensaje = "El estudiante no tiene una matricula activa";
            return false;
        }
        matricula.retirar(motivo, fechaRetiro);
        ultimoMensaje = "Estudiante retirado de: "
                + matricula.listarRamas() + ". Cupos liberados.";
        return true;
    }
// datos (informes)
    public String listarPorRama(String rama) {
        String reporte;
        if (!esRamaValida(rama)) {
            return "El taller " + rama + " no existe en este kinder\n";
        }
        reporte = "Estudiantes en " + rama + " ("
                + contarPorRama(rama) + "/" + CUPO_POR_RAMA + "):\n";
        for (int i = 0; i < cantidadMatriculas; i++) {
            if (matriculas[i].estaActiva()
                    && matriculas[i].estaEnRama(rama)) {
                reporte = reporte + "- "
                        + matriculas[i].getEstudiante().getNombreCompleto()
                        + "\n";
            }
        }return reporte;
    }
    public String mostrarOcupacion() {
        String reporte;
        int inscritos;
        double porcentaje;

        reporte = "OCUPACION DE LOS TALLERES - " + nombre + "\n";
        for (int i = 0; i < RAMAS.length; i++) {
            inscritos = contarPorRama(RAMAS[i]);
            porcentaje = (inscritos * 100.0) / CUPO_POR_RAMA;
            reporte = reporte + RAMAS[i] + ": " + inscritos
                    + "/" + CUPO_POR_RAMA
                    + " (" + Math.round(porcentaje) + "% ocupado, "
                    + (CUPO_POR_RAMA - inscritos) + " cupos libres)\n";
        }return reporte;
    }
    public String historialEstudiante(String documento) {
        String reporte;
        Estudiante estudiante;
        estudiante = buscarEstudiante(documento);
        if (estudiante == null) {
            return "No existe un estudiante con documento " + documento + "\n";
        }
        reporte = "HISTORIAL DE " + estudiante.getNombreCompleto() + "\n";
        for (int i = 0; i < cantidadMatriculas; i++) {
            if (matriculas[i].getEstudiante().getDocumento().equals(documento)) {
                reporte = reporte + "- Matricula " + matriculas[i].getNumero()
                        + " | " + matriculas[i].getAnioLectivo()
                        + " | " + matriculas[i].listarRamas()
                        + " | " + matriculas[i].getEstado() + "\n";
            }
        }return reporte;
    }
    public String consultarAcudiente(String documento) {
        Estudiante estudiante;

        estudiante = buscarEstudiante(documento);
        if (estudiante == null) {
            return "No existe un estudiante con documento " + documento + "\n";
        }return estudiante.mostrarAcudiente();
    }
    public String generarConstancia(int numero) {
        Matricula matricula;

        matricula = buscarMatricula(numero);
        if (matricula == null) {
            return "No existe la matricula numero " + numero + "\n";
        }return matricula.generarConstancia(nombre, nit);
    }
    public String generarMatriculaPdf(int numero) {
        Matricula matricula;
        matricula = buscarMatricula(numero);
        if (matricula == null) {
            return "No existe la matricula numero " + numero + "\n";
        } else {
            return matricula.generarMatriculaPdf(nombre,nit);
        }
    }

   

    /**
     * Devuelve el listado de los mejores estudiantes (hasta 10) ordenados
     * de mayor a menor promedio. Solo considera estudiantes que ya tienen
     * las MAX_NOTAS notas registradas (promedio distinto de -1).
     * Esta función SOLO consulta y ordena: no aplica descuentos ni bonos.
     */
    public Estudiante[] mejores10() {
        // Copia solo las posiciones realmente usadas del arreglo
        Estudiante[] candidatos = Arrays.copyOf(estudiantes, cantidadEstudiantes);

        // Bubble sort descendente por promedio
        for (int i = 0; i < candidatos.length - 1; i++) {
            for (int j = 0; j < candidatos.length - 1 - i; j++) {
                if (candidatos[j].calcularPromedio() < candidatos[j + 1].calcularPromedio()) {
                    Estudiante aux = candidatos[j];
                    candidatos[j] = candidatos[j + 1];
                    candidatos[j + 1] = aux;
                }
            }
        }

        int cantidadFinal = Math.min(10, candidatos.length);
        return Arrays.copyOfRange(candidatos, 0, cantidadFinal);
    }

    /**
     * Aplica el beneficio (descuento en matricula si se cobra matricula,
     * o reconocimiento en bono comercial si no se cobra) a los estudiantes
     * que estan en el listado de mejores10(). Es una accion explicita,
     * separada de la consulta, para no reaplicar el beneficio cada vez
     * que alguien solo quiere VER el listado.
     */
    public void aplicarDescuentoMejores10() {
        Estudiante[] mejores = mejores10();

        for (int k = 0; k < cantidadMatriculas; k++) {
            Matricula matricula = matriculas[k];

            if (matricula != null && matricula.estaActiva()) {
                boolean esTop10 = false;

                // Verificamos si el estudiante de esta matricula pertenece al Top 10
                for (Estudiante e : mejores) {
                    if (e != null && e.getDocumento().equals(matricula.getEstudiante().getDocumento())) {
                        esTop10 = true;
                        break;
                    }
                }

                if (esTop10) {
                    matricula.setValor(VALOR_MATRICULA - VALOR_DESCUENTO_MATRICULA);
                    Lector.mostrar("Descuento aplicado/mantenido para: " + matricula.getEstudiante().getNombreCompleto());
                } else {
                    // Si salio del Top 10 o no pertenece, se restablece la tarifa plena
                    matricula.setValor(VALOR_MATRICULA);
                }
            }
        }
    }

    /**
     * Genera el reporte PDF con el listado de los mejores estudiantes,
     * siguiendo el mismo patron usado en Matricula.generarMatriculaPdf.
     */
    public String generarMejores10Pdf() {
        Estudiante[] mejores = mejores10();
        String nombreArchivo = "mejores10.pdf";
        try {
            Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo));
            doc.open();

            // 1. Colores y fuentes corporativas
            BaseColor azulOscuro = new BaseColor(30, 81, 123);
            BaseColor grisTexto = new BaseColor(60, 60, 60);

            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, azulOscuro);
            Font fontSubHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.BLACK);
            Font fontSeccion = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, azulOscuro);
            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, grisTexto);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10, grisTexto);
            Font fontPie = FontFactory.getFont(FontFactory.TIMES_ITALIC, 9, BaseColor.GRAY);

            // 2. Encabezado de la Institución
            Paragraph pKinder = new Paragraph(nombre.toUpperCase(), fontHeader);
            pKinder.setAlignment(Element.ALIGN_CENTER);
            doc.add(pKinder);

            Paragraph pNit = new Paragraph("NIT: " + nit, fontSubHeader);
            pNit.setAlignment(Element.ALIGN_CENTER);
            pNit.setSpacingAfter(10);
            doc.add(pNit);

            // Línea divisoria
            Paragraph linea = new Paragraph("________________________________________________________________________", fontSubHeader);
            linea.setAlignment(Element.ALIGN_CENTER);
            linea.setSpacingAfter(15);
            doc.add(linea);

            // 3. Título Principal
            Paragraph pTitulo = new Paragraph("CUADRO DE HONOR - 10 MEJORES ESTUDIANTES", fontTitulo);
            pTitulo.setAlignment(Element.ALIGN_CENTER);
            pTitulo.setSpacingAfter(20);
            doc.add(pTitulo);

            // 4. Listado de Estudiantes
            if (mejores.length == 0) {
                Paragraph pVacio = new Paragraph("No hay estudiantes registrados o con notas disponibles.", fontNormal);
                pVacio.setAlignment(Element.ALIGN_CENTER);
                doc.add(pVacio);
            } else {
                for (int i = 0; i < mejores.length; i++) {
                    Estudiante e = mejores[i];
                    if (e == null) continue;

                    double promedio = e.calcularPromedio();
                    String strPromedio = (promedio == -1.0) ? "Pendiente (menos de 5 notas)" : String.format("%.2f", promedio);

                    Paragraph pItem = new Paragraph();
                    pItem.setLeading(16f);

                    // Número de posición y Nombre Completo
                    pItem.add(new Chunk((i + 1) + ". ", fontSeccion));
                    pItem.add(new Chunk(e.getNombreCompleto(), fontBold));
                    pItem.add(new Chunk(" (Doc. " + e.getDocumento() + ")\n", fontNormal));

                    // Promedio obtenido
                    pItem.add(new Chunk("    Promedio Académico: ", fontBold));
                    pItem.add(new Chunk(strPromedio + "\n", fontNormal));

                    pItem.setSpacingAfter(10);
                    doc.add(pItem);
                }
            }

            // 5. Pie de página
            Paragraph pie = new Paragraph(
                "Reporte de excelencia académica generado automáticamente por " + nombre + ".",
                fontPie
            );
            pie.setSpacingBefore(15);
            pie.setAlignment(Element.ALIGN_CENTER);
            doc.add(pie);

            doc.close();
            return "Reporte de mejores estudiantes generado exitosamente.\n";

        } catch (DocumentException | java.io.FileNotFoundException e) {
            e.printStackTrace();
            return "Error generando el reporte de mejores estudiantes.\n";
        }
    }
    public String generarMejores10Texto() {
        Estudiante[] mejores = mejores10();
        if (mejores == null || mejores.length == 0) {
            return "No hay estudiantes registrados en el sistema.\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("================================================\n");
        sb.append("   MEJORES 10 ESTUDIANTES - ").append(nombre).append("\n");
        sb.append("================================================\n\n");

        boolean hayDatos = false;
        for (int i = 0; i < mejores.length; i++) {
            Estudiante e = mejores[i];
            if (e != null) {
                hayDatos = true;
                double promedio = e.calcularPromedio();
                String promTexto = (promedio == -1.0) 
                        ? "Pendiente (menos de 5 notas)" 
                        : String.format("%.2f", promedio);

                sb.append(i + 1).append(". ")
                  .append(e.getNombreCompleto())
                  .append(" (Doc: ").append(e.getDocumento()).append(")")
                  .append(" | Edad: ").append(e.calcularEdad()).append(" años")
                  .append(" | Promedio: ").append(promTexto)
                  .append("\n");
            }
        }

        if (!hayDatos) {
            return "No hay datos de estudiantes disponibles para mostrar.\n";
        }
        return sb.toString();
    }
    
    public void consultarNotas(String doc){
        Estudiante estudiante = buscarEstudiante(doc);
        ArrayList <Float>notas = estudiante.getNotas();
        String patologias = "";
        String idTalentosEstudiante = Lector.leerTexto("Descripción de los talentos deportivos, artísticos, académicos y sociales de los estudiantes");
        String informe = "Notas de "+estudiante.getNombreCompleto()+" - "+estudiante.getDocumento()+"\n";
        for (int i = 0;i<notas.size();i++){
            informe += "Nota #"+(i+1)+": "+notas.get(i)+"\n";
        }
        informe +="\n Talentos del Estudiante: "+idTalentosEstudiante;
        if(estudiante.calcularEdad()<3){
            patologias = Lector.leerTexto("Informe de las Patologias fisicas y mentales del Estudiante: ");
            informe += "\n Informe de Patologías: \n"+patologias;
        }
        Lector.mostrar(informe);
        Lector.mostrar(consultarNotasPDF(doc,idTalentosEstudiante,patologias));
    }
    public String consultarNotasPDF(String doc,String idTalentos,String patologias){
        Estudiante estudiante = buscarEstudiante(doc);
        ArrayList <Float>notas = estudiante.getNotas();
        String archivo = "notas_"+estudiante.getDocumento()+".pdf";
        try {
            Document doc2 = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(doc2, new FileOutputStream(archivo));
            doc2.open();

            // 1. Colores y fuentes corporativas
            BaseColor azulOscuro = new BaseColor(30, 81, 123);
            BaseColor grisTexto = new BaseColor(60, 60, 60);

            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, azulOscuro);
            Font fontSubHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.BLACK);
            Font fontSeccion = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, azulOscuro);
            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, grisTexto);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10, grisTexto);
            Font fontPie = FontFactory.getFont(FontFactory.TIMES_ITALIC, 9, BaseColor.GRAY);

            // 2. Encabezado de la Institución
            Paragraph pKinder = new Paragraph(nombre.toUpperCase(), fontHeader);
            pKinder.setAlignment(Element.ALIGN_CENTER);
            doc2.add(pKinder);

            Paragraph pNit = new Paragraph("NIT: " + nit, fontSubHeader);
            pNit.setAlignment(Element.ALIGN_CENTER);
            pNit.setSpacingAfter(10);
            doc2.add(pNit);

            // Línea divisoria
            Paragraph linea = new Paragraph("________________________________________________________________________", fontSubHeader);
            linea.setAlignment(Element.ALIGN_CENTER);
            linea.setSpacingAfter(15);
            doc2.add(linea);

            // 3. Título Principal
            Paragraph pTitulo = new Paragraph("INFORME DE NOTAS - "+estudiante.getNombreCompleto()+" - "+estudiante.getDocumento(), fontTitulo);
            pTitulo.setAlignment(Element.ALIGN_CENTER);
            pTitulo.setSpacingAfter(20);
            doc2.add(pTitulo);

            // 4. Notas del Estudiante
            if (notas.size() == 0) {
                Paragraph pVacio = new Paragraph("No hay estudiantes registrados o con notas disponibles.", fontNormal);
                pVacio.setAlignment(Element.ALIGN_CENTER);
                doc2.add(pVacio);
            } else {
                for (int i = 0; i < notas.size(); i++) {
                    Float e = notas.get(i);
                    if (e == null) {
                        continue;
                    }

                    Float nota = notas.get(i);
                    

                    Paragraph pItem = new Paragraph();
                    pItem.setLeading(16f);

                    // Número de nota y nota
                    pItem.add(new Chunk((i + 1) + ". ", fontSeccion));
                    pItem.add(new Chunk("NOTA : "+nota, fontBold));


                    pItem.setSpacingAfter(10);
                    doc2.add(pItem);
                }
            }
            Paragraph prom = new Paragraph("Promedio Obtenido: "+estudiante.calcularPromedio(),fontBold);
            Paragraph tituloTalentos = new Paragraph("Talentos del Estudiante",fontTitulo);
            Paragraph talentos = new Paragraph(idTalentos,fontBold);
            
            prom.setSpacingAfter(10);
            doc2.add(prom);
            
            tituloTalentos.setSpacingAfter(8);
            doc2.add(tituloTalentos);
            doc2.add(talentos);
            
            if(!(patologias.isBlank())){
                Paragraph tituloEnfermedades = new Paragraph("Patologías del Estudiante",fontTitulo);
                Paragraph enfermedades = new Paragraph(patologias,fontBold);
                
                tituloEnfermedades.setSpacingAfter(8);
                
                doc2.add(tituloEnfermedades);
                doc2.add(enfermedades);
            }
            
            // 5. Pie de página
            Paragraph pie = new Paragraph(
                "Informe de notas generado automáticamente por " + nombre + ".",
                fontPie
            );
            pie.setSpacingBefore(15);
            pie.setAlignment(Element.ALIGN_CENTER);
            doc2.add(pie);

            doc2.close();
            return "Informe de notas generado exitosamente.\n";

        } catch (DocumentException | java.io.FileNotFoundException e) {
            e.printStackTrace();
            return "Error generando el reporte de mejores estudiantes.\n";
        }
    }
        
}
