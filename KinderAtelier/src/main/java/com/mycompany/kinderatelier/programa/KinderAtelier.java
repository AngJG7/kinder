package com.mycompany.kinderatelier.programa;
import com.mycompany.kinderatelier.lectura.Lector;
import java.util.Arrays;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

public class KinderAtelier {
    public static final int VALOR_DESCUENTO_MATRICULA = 250000;
    public static final int ANIO_LECTIVO = 2026;
    public static final int EDAD_MINIMA = 4;
    public static final int EDAD_MAXIMA = 5;
    public static final int CUPO_POR_RAMA = 15;
    public static final double VALOR_MATRICULA = 450000;
    public static final String[] RAMAS = {"Plastica", "Musica", "Teatro",
                                          "Danza", "Literatura"};
    public static final String[] TALENTOS = {"Deportivo", "Artistico",
                                             "Academico", "Social"};
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
    public KinderAtelier(String dNombre, String dNit, String dFechaActual) {
        nombre = dNombre;
        nit = dNit;
        fechaActual = dFechaActual;

        estudiantes = new Estudiante[MAX_REGISTROS];
        empleados = new Empleado[MAX_REGISTROS];
        matriculas = new Matricula[MAX_REGISTROS];

        cantidadEstudiantes = 0;
        cantidadEmpleados = 0;
        cantidadMatriculas = 0;
        consecutivo = 1;
        ultimoMensaje = "";
    }

    public String getNombre() {
        return nombre;
    }

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
    public int indiceRama(String rama) {
        for (int i = 0; i < RAMAS.length; i++) {
            if (RAMAS[i].equals(rama)) {
                return i;
            }
        }return -1;
    }
    public boolean esRamaValida(String rama) {
        return indiceRama(rama) != -1;
    }
    public String listarActividades(String rama) {
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

    public Matricula matricular(Estudiante estudiante, String[] ramasElegidas,
                                Empleado quien) {
        Matricula nueva;

        if (!quien.esAdministrativo()) {
            ultimoMensaje = "Solo el personal administrativo puede matricular";
            return null;
        }

        if (buscarEstudiante(estudiante.getDocumento()) == null) {
            ultimoMensaje = "El estudiante no esta registrado en el sistema";
            return null;
        }

        if (buscarMatriculaActiva(estudiante.getDocumento()) != null) {
            ultimoMensaje = "El estudiante ya tiene una matricula activa";
            return null;
        }

        if (!edadValida(estudiante)) {
            ultimoMensaje = "La edad (" + estudiante.calcularEdad()
                    + ") esta fuera del rango: " + EDAD_MINIMA
                    + " a " + EDAD_MAXIMA + " anios";
            return null;
        }

        if (ramasElegidas.length == 0) {
            ultimoMensaje = "Debe elegir al menos un taller";
            return null;
        }
        if (ramasElegidas.length > Matricula.MAX_RAMAS) {
            ultimoMensaje = "Maximo " + Matricula.MAX_RAMAS
                    + " talleres por estudiante";
            return null;
        }

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

    public boolean esTalentoValido(String talento) {
        for (int i = 0; i < TALENTOS.length; i++) {
            if (TALENTOS[i].equals(talento)) {
                return true;
            }
        }return false;
    }

    public boolean asignarTalento(String documento, String talento) {
        Estudiante estudiante;
        estudiante = buscarEstudiante(documento);
        if (estudiante == null) {
            ultimoMensaje = "No existe un estudiante con documento " + documento;
            return false;
        }
        if (!esTalentoValido(talento)) {
            ultimoMensaje = "El talento " + talento + " no es una categoria valida";
            return false;
        }
        estudiante.setTalento(talento);
        ultimoMensaje = "Talento " + talento + " asignado a "
                + estudiante.getNombreCompleto();
        return true;
    }

    public int contarPorTalento(String talento) {
        int contador;
        contador = 0;
        for (int i = 0; i < cantidadEstudiantes; i++) {
            if (estudiantes[i].getTalento().equals(talento)) {
                contador = contador + 1;
            }
        }return contador;
    }

    public String listarPorTalento(String talento) {
        String reporte;
        if (!esTalentoValido(talento)) {
            return "El talento " + talento + " no es una categoria valida\n";
        }
        reporte = "TALENTO " + talento.toUpperCase() + " ("
                + contarPorTalento(talento) + " estudiantes)\n\n";
        for (int i = 0; i < cantidadEstudiantes; i++) {
            if (estudiantes[i].getTalento().equals(talento)) {
                reporte = reporte + "- " + estudiantes[i].getNombreCompleto()
                        + " (" + estudiantes[i].calcularEdad() + " anios)\n";
                if (estudiantes[i].getHabilidades().isEmpty()) {
                    reporte = reporte + "    sin detalle registrado\n";
                } else {
                    reporte = reporte + "    " + estudiantes[i].getHabilidades() + "\n";
                }
            }
        }return reporte;
    }

    public String mostrarDistribucionTalentos() {
        String reporte;
        int conTalento;
        int enCategoria;
        double porcentaje;

        conTalento = 0;
        for (int i = 0; i < cantidadEstudiantes; i++) {
            if (estudiantes[i].tieneTalento()) {
                conTalento = conTalento + 1;
            }
        }

        reporte = "DESARROLLO INTEGRAL - " + nombre + "\n\n";
        reporte = reporte + "Estudiantes registrados: " + cantidadEstudiantes + "\n";
        reporte = reporte + "Con talento identificado: " + conTalento + "\n";
        reporte = reporte + "Sin identificar: " + (cantidadEstudiantes - conTalento)
                + "\n\n";

        if (conTalento == 0) {
            return reporte + "Todavia no hay talentos identificados\n";
        }

        for (int i = 0; i < TALENTOS.length; i++) {
            enCategoria = contarPorTalento(TALENTOS[i]);
            porcentaje = (enCategoria * 100.0) / conTalento;
            reporte = reporte + TALENTOS[i] + ": " + enCategoria
                    + " ninos (" + Math.round(porcentaje) + "%)\n";
        }return reporte;
    }

    public String listarEstudiantes() {
        String reporte;
        Matricula matricula;

        reporte = "ESTUDIANTES REGISTRADOS (" + cantidadEstudiantes + ")\n\n";
        for (int i = 0; i < cantidadEstudiantes; i++) {
            matricula = buscarMatriculaActiva(estudiantes[i].getDocumento());
            reporte = reporte + "- " + estudiantes[i].getNombreCompleto()
                    + " (doc. " + estudiantes[i].getDocumento() + ", "
                    + estudiantes[i].calcularEdad() + " anios)\n";
            if (matricula == null) {
                reporte = reporte + "    Talleres: sin matricular\n";
            } else {
                reporte = reporte + "    Talleres: " + matricula.listarRamas() + "\n";
            }
            if (estudiantes[i].tieneTalento()) {
                reporte = reporte + "    Talento: "
                        + estudiantes[i].getTalento() + "\n";
            }
        }return reporte;
    }

    public String listarPersonal() {
        String reporte;

        reporte = "PERSONAL DEL KINDER (" + cantidadEmpleados + ")\n\n";
        for (int i = 0; i < cantidadEmpleados; i++) {
            reporte = reporte + "- " + empleados[i].getNombreCompleto()
                    + " (doc. " + empleados[i].getDocumento() + ")\n";
            reporte = reporte + "    Cargo: " + empleados[i].getCargo() + "\n";
            if (!empleados[i].esAdministrativo()) {
                Profesor profesor = (Profesor) empleados[i];
                reporte = reporte + "    Dicta: " + profesor.getRamaAsignada() + "\n";
            }
        }return reporte;
    }

    public Estudiante[] mejores10() {

        Estudiante[] candidatos = Arrays.copyOf(estudiantes, cantidadEstudiantes);

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

    public void aplicarDescuentoMejores10() {
        Estudiante[] mejores = mejores10();

        for (int k = 0; k < cantidadMatriculas; k++) {
            Matricula matricula = matriculas[k];

            if (matricula != null && matricula.estaActiva()) {
                boolean esTop10 = false;

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

                    matricula.setValor(VALOR_MATRICULA);
                }
            }
        }
    }

    public String generarMejores10Pdf() {
        Estudiante[] mejores = mejores10();
        String nombreArchivo = "mejores10.pdf";
        try {
            Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo));
            doc.open();


            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Font fontSubHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.BLACK);
            Font fontSeccion = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

            Paragraph pKinder = new Paragraph(nombre.toUpperCase(), fontHeader);
            pKinder.setAlignment(Element.ALIGN_CENTER);
            doc.add(pKinder);

            Paragraph pNit = new Paragraph("NIT: " + nit, fontSubHeader);
            pNit.setAlignment(Element.ALIGN_CENTER);
            pNit.setSpacingAfter(10);
            doc.add(pNit);
            Paragraph linea = new Paragraph("==================================", fontSubHeader);
            linea.setAlignment(Element.ALIGN_CENTER);
            linea.setSpacingAfter(15);
            doc.add(linea);
            Paragraph pTitulo = new Paragraph("CUADRO DE HONOR - 10 MEJORES ESTUDIANTES", fontTitulo);
            pTitulo.setAlignment(Element.ALIGN_CENTER);
            pTitulo.setSpacingAfter(20);
            doc.add(pTitulo);
            if (mejores.length == 0) {
                Paragraph pVacio = new Paragraph("No hay estudiantes registrados o con notas disponibles.", fontNormal);
                pVacio.setAlignment(Element.ALIGN_CENTER);
                doc.add(pVacio);
            } else {
                for (int i = 0; i < mejores.length; i++) {
                    Estudiante e = mejores[i];
                    if (e == null) continue;

                    double promedio = e.calcularPromedio();
                    String strPromedio = (promedio == -1.0) ? "Pendiente (tiene menos de 5 notas)" : String.format("%.2f", promedio);

                    Paragraph pItem = new Paragraph();
                    pItem.setLeading(16f);

                    pItem.add(new Chunk((i + 1) + ". ", fontSeccion));
                    pItem.add(new Chunk(e.getNombreCompleto(), fontBold));
                    pItem.add(new Chunk(" (Doc. " + e.getDocumento() + ")\n", fontNormal));

                    pItem.add(new Chunk("    Promedio Académico: ", fontBold));
                    pItem.add(new Chunk(strPromedio + "\n", fontNormal));

                    pItem.setSpacingAfter(10);
                    doc.add(pItem);
                }
            }
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

    public String generarBoletinPdf(String documentoEstudiante) {
        Estudiante estudiante = buscarEstudiante(documentoEstudiante);
        if (estudiante == null) {
            return "No existe el estudiante con documento " + documentoEstudiante + "\n";
        }

        String nombreArchivo = "boletin_" + estudiante.getDocumento() + ".pdf";
        try {
            Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo));
            doc.open();
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Font fontSubHeader = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.BLACK);
            Font fontSeccion = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.BLACK);
            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

            Paragraph pKinder = new Paragraph(nombre.toUpperCase(), fontHeader);
            pKinder.setAlignment(Element.ALIGN_CENTER);
            doc.add(pKinder);

            Paragraph pNit = new Paragraph("NIT: " + nit, fontSubHeader);
            pNit.setAlignment(Element.ALIGN_CENTER);
            pNit.setSpacingAfter(10);
            doc.add(pNit);

            Paragraph linea = new Paragraph("==================================", fontSubHeader);
            linea.setAlignment(Element.ALIGN_CENTER);
            linea.setSpacingAfter(15);
            doc.add(linea);

            Paragraph pTitulo = new Paragraph("BOLETIN DE NOTAS", fontTitulo);
            pTitulo.setAlignment(Element.ALIGN_CENTER);
            pTitulo.setSpacingAfter(20);
            doc.add(pTitulo);

            Paragraph pSecEstudiante = new Paragraph("DATOS DEL ESTUDIANTE", fontSeccion);
            pSecEstudiante.setSpacingAfter(6);
            doc.add(pSecEstudiante);

            Paragraph pEstudiante = new Paragraph();
            pEstudiante.setLeading(16f);
            pEstudiante.add(new Chunk("Nombre completo: ", fontBold));
            pEstudiante.add(new Chunk(estudiante.getNombreCompleto() + "\n", fontNormal));
            pEstudiante.add(new Chunk("Documento: ", fontBold));
            pEstudiante.add(new Chunk(estudiante.getDocumento() + "\n", fontNormal));
            pEstudiante.add(new Chunk("Edad: ", fontBold));
            pEstudiante.add(new Chunk(estudiante.calcularEdad() + " anios\n", fontNormal));
            pEstudiante.setSpacingAfter(15);
            doc.add(pEstudiante);

            Paragraph pSecFamilia = new Paragraph("GRUPO FAMILIAR - ACUDIENTE RESPONSABLE", fontSeccion);
            pSecFamilia.setSpacingAfter(6);
            doc.add(pSecFamilia);

            Paragraph pFamilia = new Paragraph();
            pFamilia.setLeading(16f);
            pFamilia.add(new Chunk("Nombre: ", fontBold));
            pFamilia.add(new Chunk(estudiante.getNombreAcudiente() + "\n", fontNormal));
            pFamilia.add(new Chunk("Parentesco: ", fontBold));
            pFamilia.add(new Chunk(estudiante.getParentesco() + "\n", fontNormal));
            pFamilia.add(new Chunk("Documento: ", fontBold));
            pFamilia.add(new Chunk(estudiante.getDocumentoAcudiente() + "\n", fontNormal));
            pFamilia.add(new Chunk("Telefono: ", fontBold));
            pFamilia.add(new Chunk(estudiante.getTelefonoAcudiente() + "\n", fontNormal));
            pFamilia.setSpacingAfter(15);
            doc.add(pFamilia);

            Paragraph pSecNotas = new Paragraph("NOTAS", fontSeccion);
            pSecNotas.setSpacingAfter(6);
            doc.add(pSecNotas);

            java.util.ArrayList<Float> notas = estudiante.getNotas();
            if (notas.isEmpty()) {
                Paragraph pVacio = new Paragraph("Este estudiante no tiene notas registradas", fontNormal);
                doc.add(pVacio);
            } else {
                for (int i = 0; i < notas.size(); i++) {
                    Paragraph pNota = new Paragraph("Nota " + (i + 1) + ": " + notas.get(i), fontNormal);
                    doc.add(pNota);
                }
            }

            double promedio = estudiante.calcularPromedio();
            String strPromedio = (promedio == -1.0)
                    ? "Pendiente (menos de 5 notas)"
                    : String.format("%.2f", promedio);
            Paragraph pProm = new Paragraph("Promedio: " + strPromedio, fontBold);
            pProm.setSpacingBefore(10);
            pProm.setSpacingAfter(15);
            doc.add(pProm);

            if (estudiante.requierePatologias()) {
                Paragraph pSecPatologias = new Paragraph("PATOLOGIAS (MENOR DE 3 ANIOS)", fontSeccion);
                pSecPatologias.setSpacingAfter(6);
                doc.add(pSecPatologias);

                String textoPatologias = estudiante.getPatologias().isEmpty()
                        ? "Sin registrar" : estudiante.getPatologias();
                Paragraph pPatologias = new Paragraph(textoPatologias, fontNormal);
                pPatologias.setSpacingAfter(15);
                doc.add(pPatologias);
            }
            doc.close();
            return "Boletin generado exitosamente!!\n";

        } catch (DocumentException | java.io.FileNotFoundException e) {
            e.printStackTrace();
            return "Error al generar el boletin en PDF.\n";
        }
    }
}

