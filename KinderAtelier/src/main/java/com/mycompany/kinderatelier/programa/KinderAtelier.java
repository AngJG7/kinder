/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kinderatelier.programa;
/**
 *
 * @author Ángela
 */
public class KinderAtelier {
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
}
