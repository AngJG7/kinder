/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.programa;

import com.mycompany.kinderatelier.lectura.Lector;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

/*Para mi programa, la función de matricula requería de la condición que no podía ser un profesor quien matriculase
entonces construí bastante de las otras funciones para que la matricula me quedase completa */

/**
 *
 * @author Ángela
 */
public class Principal {
 
    public static void main(String[] args) {
        KinderAtelier kinder;
        int opcion;
        boolean salir;
        kinder = new KinderAtelier("Kinder Atelier", "900123456-7", "05/11/2025");
        salir = false;
        Lector.mostrar("Bienvenida al sistema de " + kinder.getNombre());
        while (!salir) {
            opcion = Lector.leerEntero(menu());
            if (opcion == 1) {
                cargarDatosDeEjemplo(kinder);
            } else if (opcion == 2) {
                registrarEstudiante(kinder);
            } else if (opcion == 3) {
                registrarEmpleado(kinder);
            } else if (opcion == 4) {
                matricular(kinder);
            } else if (opcion == 5) {
                desmatricular(kinder);
            } else if (opcion == 6) {
                Lector.mostrar(kinder.listarTodasLasRamas());
            } else if (opcion == 7) {
                Lector.mostrar(kinder.mostrarOcupacion());
            } else if (opcion == 8) {
                consultarTaller(kinder);
            } else if (opcion == 9) {
                consultarEstudiante(kinder);
            } else if (opcion == 10) {
                consultarConstancia(kinder);
            } else if (opcion == 11){
                consultar10Mejores(kinder);
            } else if (opcion == 12){
                agregarNotasEstudiante(kinder);
            } else if (opcion == 13){
                aplicarDescuento(kinder);
            } else if (opcion == 0) {
                salir = true;
                Lector.mostrar("Hasta pronto!");
            } else {
                Lector.mostrar("Opcion invalida, elija un numero del menu");
            }
        }
    }
    static String menu() {
        return """
               ===== KINDER ATELIER =====
 
                1. Cargar datos de ejemplo
                2. Registrar estudiante
                3. Registrar empleado
                4. Matricular estudiante
                5. Desmatricular estudiante
                6. Ver talleres y actividades
                7. Ver ocupacion de los talleres
                8. Ver estudiantes de un taller
                9. Consultar un estudiante
               10. Generar constancia
               11. Consultar los 10 Mejores
               12. Agregar Notas por estudiante
               13. Aplicar Descuentos de Honor
                0. Salir
 
               Digite una opcion:""";
    }
    static void registrarEstudiante(KinderAtelier kinder) {
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
        String nombreAcudiente;
        String documentoAcudiente;
        String parentesco;
        String telefonoAcudiente;
 
        documento = Lector.leerTexto("Documento del estudiante:");
        if (documento.isEmpty()) {
            Lector.mostrar("Registro cancelado.");
            return;
        }
        // reviso de una si ya existe, para no hacer llenar todo al pedo
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
        habilidades = Lector.leerTexto("Habilidades artisticas que muestra:");
        direccion = Lector.leerTexto("Direccion:");
 
        nombreAcudiente = Lector.leerTexto("Nombre del acudiente:");
        documentoAcudiente = Lector.leerTexto("Documento del acudiente:");
        parentesco = Lector.leerTexto("Parentesco (Madre, Padre, etc.):");
        telefonoAcudiente = Lector.leerTexto("Telefono del acudiente:");
 
        nuevo = new Estudiante(documento, nombres, apellidos, telefono, eps,
                fechaNacimiento, tipoSangre, alergias, habilidades, direccion,
                nombreAcudiente, documentoAcudiente, parentesco,
                telefonoAcudiente);
 
        if (kinder.agregarEstudiante(nuevo)) {
            Lector.mostrar("Estudiante registrado:\n\n" + nuevo.mostrarDatos());
        } else {
            Lector.mostrar(kinder.getUltimoMensaje());
        }
    }
    static void registrarEmpleado(KinderAtelier kinder) {
        Empleado nuevo;
        String documento;
        String nombres;
        String apellidos;
        String telefono;
        String eps;
        String idEmpleado;
        String fechaIngreso;
        double salario;
        int tipo;
        tipo = Lector.leerEntero("""
                                 Tipo de empleado:
                                 1. Administrativo
                                 2. Profesor""");
 
        if (tipo != 1 && tipo != 2) {
            Lector.mostrar("Tipo invalido.");
            return;
        }
        documento = Lector.leerTexto("Documento:");
        if (documento.isEmpty()) {
            Lector.mostrar("Registro cancelado.");
            return;
        }
        // mismo caso que con el estudiante
        if (kinder.buscarEmpleado(documento) != null) {
            Lector.mostrar("Ya existe un empleado con ese documento.");
            return;
        }
        nombres = Lector.leerTexto("Nombres:");
        apellidos = Lector.leerTexto("Apellidos:");
        telefono = Lector.leerTexto("Telefono:");
        eps = Lector.leerTexto("EPS:");
        idEmpleado = Lector.leerTexto("ID de empleado:");
        salario = Lector.leerEntero("Salario:");
        fechaIngreso = Lector.leerTexto("Fecha de ingreso (dd/mm/aaaa):");
        if (tipo == 1) {
            nuevo = new Empleado(documento, nombres, apellidos, telefono, eps,
                    idEmpleado, Lector.leerTexto("Cargo:"), salario,
                    fechaIngreso);
        } else {
            nuevo = new Profesor(documento, nombres, apellidos, telefono, eps,
                    idEmpleado, salario, fechaIngreso,
                    Lector.leerTexto("Titulo:"),
                    elegirTaller(kinder, "Taller que dicta:"));
        }
        if (kinder.agregarEmpleado(nuevo)) {
            Lector.mostrar("Empleado registrado:\n\n" + nuevo.mostrarDatos());
        } else {
            Lector.mostrar(kinder.getUltimoMensaje());
        }
    }
    static void matricular(KinderAtelier kinder) {
        Empleado quien;
        Estudiante estudiante;
        Matricula nueva;
        String[] talleres;
        int cantidad;
        quien = pedirEmpleado(kinder);
        if (quien == null) {
            return;
        }
        estudiante = pedirEstudiante(kinder);
        if (estudiante == null) {
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
            talleres[i] = elegirTaller(kinder, "Taller " + (i + 1) + " de "
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
    static void desmatricular(KinderAtelier kinder) {
        Empleado quien;
        String documento;
        String motivo;
        String fecha;
        quien = pedirEmpleado(kinder);
        if (quien == null) {
            return;
        }
        documento = Lector.leerTexto("Documento del estudiante a retirar:");
        motivo = Lector.leerTexto("Motivo del retiro:");
        fecha = Lector.leerTexto("Fecha del retiro (dd/mm/aaaa):");
 
        kinder.desmatricular(documento, motivo, fecha, quien);
        Lector.mostrar(kinder.getUltimoMensaje());
    }
    
     static void consultarTaller(KinderAtelier kinder) {
        String taller;
 
        taller = elegirTaller(kinder, "De cual taller quiere ver la lista?");
        Lector.mostrar(kinder.listarPorRama(taller));
    }
 
    static void consultarEstudiante(KinderAtelier kinder) {
        String documento;
        int opcion;
        documento = Lector.leerTexto("Documento del estudiante:");
        opcion = Lector.leerEntero("""
                                   Que desea consultar?
                                   1. Datos del acudiente
                                   2. Historial de matriculas""");
        if (opcion == 1) {
            Lector.mostrar(kinder.consultarAcudiente(documento));
        } else if (opcion == 2) {
            Lector.mostrar(kinder.historialEstudiante(documento));
        } else {
            Lector.mostrar("Opcion invalida.");
        }
    }
    static void consultarConstancia(KinderAtelier kinder) {
        int numero;
        numero = Lector.leerEntero("Numero de la matricula:");
        Lector.mostrar(kinder.generarConstancia(numero));
        consultarMatriculaPdf(kinder,numero);
    }
    static Empleado pedirEmpleado(KinderAtelier kinder) {
        Empleado empleado;
        String documento;
 
        documento = Lector.leerTexto("Documento del empleado que registra:");
        empleado = kinder.buscarEmpleado(documento);
 
        if (empleado == null) {
            Lector.mostrar("No existe un empleado con ese documento.");
        }
        return empleado;
    }
    static Estudiante pedirEstudiante(KinderAtelier kinder) {
        Estudiante estudiante;
        String documento;
 
        documento = Lector.leerTexto("Documento del estudiante:");
        estudiante = kinder.buscarEstudiante(documento);
 
        if (estudiante == null) {
            Lector.mostrar("No existe un estudiante con ese documento.");
        }
        return estudiante;
    }
    static String elegirTaller(KinderAtelier kinder, String mensaje) {
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
    public static void consultarMatriculaPdf(KinderAtelier kinder,int numero){
        Lector.mostrar(kinder.generarMatriculaPdf(numero));
        /*
        try {
            Runtime.getRuntime().exec("cmd /c start matricula_"+numero+".pdf");
        } catch (Exception e){
            e.printStackTrace();
        }
        */
        try {
            String archivo = "matricula_" + numero + ".pdf";
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                Runtime.getRuntime().exec("cmd /c start " + archivo);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("xdg-open " + archivo);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open " + archivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    public static void agregarNotasEstudiante(KinderAtelier kinder) {
    String docEstudiante = Lector.leerTexto("Ingrese el documento del estudiante:");
    if (docEstudiante.isEmpty()) return;

    Estudiante estudiante = kinder.buscarEstudiante(docEstudiante);
    if (estudiante == null) {
        Lector.mostrar("Estudiante no encontrado.");
        return;
    }

    String docProfesor = Lector.leerTexto("Ingrese el documento del profesor:");
    if (docProfesor.isEmpty()) return;

    Profesor profesor = kinder.buscarProfesor(docProfesor);
    if (profesor == null) {
        Lector.mostrar("Profesor no encontrado (o el documento no pertenece a un docente).");
        return;
    }

    float nota = Lector.leerFloat("Ingrese la nota (0.0 a 5.0):");
    if (profesor.agregarNotaAEstudiante(estudiante, nota)) {
        Lector.mostrar("Nota agregada con exito.");
    } else {
        Lector.mostrar("No se pudo agregar la nota (fuera de rango 0-5 o limite alcanzado de 5 notas).");
    }
}
    
    public static void consultar10Mejores(KinderAtelier kinder) {
        Lector.mostrar(kinder.generarMejores10Texto());
        kinder.generarMejores10Pdf();
        /*
        try {
            Runtime.getRuntime().exec("cmd /c start mejores10.pdf");
        } catch (Exception e){
            e.printStackTrace();
        }
        */
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
            e.printStackTrace();
        }
    }
    
    public static void aplicarDescuento(KinderAtelier kinder){
        kinder.aplicarDescuentoMejores10();
    }
    static void cargarDatosDeEjemplo(KinderAtelier kinder) {
        // 1. Personal Administrativo y Docentes
        Empleado secretaria = new Empleado("435", "Luz Marina", "Ospina",
                "3105558899", "Sura", "EMP-01", "Secretaria academica",
                2200000.0, "01/02/2020");

        Profesor profesorMusica = new Profesor("712", "Carlos", "Restrepo",
                "3009991122", "Nueva EPS", "EMP-03", 2600000.0, "15/01/2022",
                "Licenciado en Musica", "Musica");

        Profesor profesorDanza = new Profesor("713", "Valeria", "Torres",
                "3158884433", "Sura", "EMP-04", 2500000.0, "10/02/2023",
                "Maestra en Artes Escenicas", "Danza");

        kinder.agregarEmpleado(secretaria);
        kinder.agregarEmpleado(profesorMusica);
        kinder.agregarEmpleado(profesorDanza);

        // 2. Arreglo de 12 Estudiantes (Suficientes para llenar el Top 10 y dejar 2 por fuera)
        Estudiante[] estudiantes = new Estudiante[] {
            new Estudiante("101", "Camila", "Perez Gomez", "3001112233", "Sura", "10/01/2021", "O+", "", "Le gusta pintar", "Calle 10 # 5-12", "Laura Perez", "401", "Madre", "3001112233"),
            new Estudiante("102", "Samuel", "Castro Rios", "3002223344", "Savia Salud", "15/04/2021", "A+", "Polen", "Toca el xilofono", "Carrera 43A # 12-09", "Pedro Castro", "402", "Padre", "3002223344"),
            new Estudiante("103", "Valentina", "Ortiz Marin", "3003334455", "Sura", "20/06/2021", "B+", "", "Muy expresiva", "Calle 50 # 20-30", "Marta Marin", "403", "Madre", "3003334455"),
            new Estudiante("104", "Sofia", "Gomez Ruiz", "3201234567", "Sura", "12/03/2022", "O+", "", "Canta y baila", "Calle 45 # 30-12", "Ana Ruiz", "431", "Madre", "3201234567"),
            new Estudiante("105", "Lucas", "Rios Restrepo", "3004445566", "Sanitas", "05/09/2021", "AB+", "", "Arma bloques rapidamente", "Carrera 70 # 45-10", "Carlos Rios", "404", "Padre", "3004445566"),
            new Estudiante("106", "Martin", "Suarez Londono", "3005556677", "Sura", "11/11/2021", "O-", "Lactosa", "Gran agilidad motriz", "Calle 33 # 14-02", "Diana Londono", "405", "Madre", "3005556677"),
            new Estudiante("107", "Isabella", "Morales Gil", "3006667788", "Savia Salud", "02/02/2022", "A-", "", "Facilidad para idiomas", "Carrera 25 # 60-15", "Andres Morales", "406", "Padre", "3006667788"),
            new Estudiante("108", "Mateo", "Alvarez Diaz", "3117654321", "Savia Salud", "08/07/2021", "A+", "Mani", "Dibuja muy bien", "Carrera 50 # 12-04", "Jorge Alvarez", "714", "Padre", "3117654321"),
            new Estudiante("109", "Mariana", "Ramirez Toro", "3007778899", "Coomeva", "18/08/2021", "O+", "", "Le gusta moldear plastilina", "Calle 12 # 40-50", "Elena Toro", "407", "Madre", "3007778899"),
            new Estudiante("110", "Luciana", "Vargas Ospina", "3008889900", "Sura", "30/10/2021", "B-", "Polvo", "Memoriza canciones facil", "Carrera 80 # 32-11", "Gonzalo Vargas", "408", "Padre", "3008889900"),
            new Estudiante("111", "Joaquin", "Londono Mesa", "3009990011", "Sanitas", "14/05/2021", "O+", "", "Muy colaborativo", "Calle 65 # 10-08", "Patricia Mesa", "409", "Madre", "3009990011"),
            new Estudiante("112", "Tomas", "Herrera Cano", "3010001122", "Sura", "22/12/2021", "A+", "", "Le gusta la percusión", "Carrera 39 # 54-21", "Esteban Herrera", "410", "Padre", "3010001122")
        };

        // Matricular todos los estudiantes con la secretaria autorizada
        String[][] talleresSugeridos = new String[][] {
            {"Musica", "Plastica"}, {"Danza", "Teatro"}, {"Musica", "Danza"},
            {"Teatro", "Plastica"}, {"Musica", "Teatro"}, {"Danza", "Plastica"},
            {"Musica", "Danza"}, {"Plastica", "Teatro"}, {"Musica", "Plastica"},
            {"Danza", "Teatro"}, {"Musica", "Danza"}, {"Plastica", "Teatro"}
        };

        for (int i = 0; i < estudiantes.length; i++) {
            kinder.agregarEstudiante(estudiantes[i]);
            kinder.matricular(estudiantes[i], talleresSugeridos[i], secretaria);
        }

        // 3. Matriz de 5 notas por estudiante para habilitar cálculos de promedios
        // Nota: El ultimo estudiante ("112") solo tiene 2 notas para probar la validacion de incompleto (-1.0)
        float[][] matrizNotas = new float[][] {
            {5.0f, 5.0f, 4.8f, 5.0f, 5.0f}, // Camila: 4.96 (Top 1)
            {4.8f, 4.9f, 5.0f, 4.7f, 4.9f}, // Samuel: 4.86 (Top 2)
            {4.7f, 4.8f, 4.9f, 4.8f, 4.8f}, // Valentina: 4.80 (Top 3)
            {4.5f, 4.7f, 4.8f, 4.6f, 4.9f}, // Sofia: 4.70 (Top 4)
            {4.4f, 4.5f, 4.6f, 4.5f, 4.5f}, // Lucas: 4.50 (Top 5)
            {4.2f, 4.3f, 4.4f, 4.2f, 4.4f}, // Martin: 4.30 (Top 6)
            {4.0f, 4.2f, 4.1f, 4.3f, 4.2f}, // Isabella: 4.16 (Top 7)
            {4.0f, 4.0f, 3.8f, 4.1f, 3.9f}, // Mateo: 3.96 (Top 8)
            {3.8f, 3.7f, 3.9f, 3.8f, 3.8f}, // Mariana: 3.80 (Top 9)
            {3.5f, 3.6f, 3.7f, 3.5f, 3.6f}, // Luciana: 3.58 (Top 10)
            {3.2f, 3.1f, 3.3f, 3.0f, 3.2f}, // Joaquin: 3.16 (Queda por fuera del Top 10)
            {4.5f, 4.0f}                    // Tomas: Notas incompletas (< 5)
        };

        for (int i = 0; i < estudiantes.length; i++) {
            for (float nota : matrizNotas[i]) {
                profesorMusica.agregarNotaAEstudiante(estudiantes[i], nota);
            }
        }

        Lector.mostrar("""
                ================================================
                         DATOS DE PRUEBA CARGADOS
                ================================================
                • 3 Empleados registrados (1 Secretaria, 2 Docentes)
                • 12 Estudiantes registrados y matriculados.
                • Historial de notas cargado para pruebas de:
                   - Ranking de los 10 mejores estudiantes.
                   - Aplicación/retiro automatizado de descuentos.
                   - Validación de estudiantes con notas pendientes.

                Doc. Secretaria autorizada: 435
                Doc. Docente para pruebas de rechazo: 712
                ================================================""");
    }
}
 