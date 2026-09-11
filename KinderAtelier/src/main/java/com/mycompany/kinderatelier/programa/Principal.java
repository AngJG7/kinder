/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.programa;

import com.mycompany.kinderatelier.lectura.Lector;

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
            } else if (opcion == 0) {
                salir = true;
                Lector.mostrar("Hasta pronto.");
            } else {
                Lector.mostrar("Opcion invalida. Elija un numero del menu.");
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
        
        /*Nuevas variables para ambos padres*/
        String nombrePadre;
        String documentoPadre;
        String telefonoPadre;
        String nombreMadre;
        String documentoMadre;
        String telefonoMadre;
 
        documento = Lector.leerTexto("Documento del estudiante:");
        if (documento.isEmpty()) {
            Lector.mostrar("Registro cancelado.");
            return;
        }
        // reviso de una si ya existe, para no hacer llenar todo al pedo
        if (kinder.buscarEstudiante(documento) != null) {
            Lector.mostrar("Ya existe un estudiante con ese documento.");
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
 
        // Datos del Padre
        nombrePadre = Lector.leerTexto("Nombre del padre:");
        documentoPadre = Lector.leerTexto("Documento del padre:");
        telefonoPadre = Lector.leerTexto("Telefono del padre:");
        
        // Datos de la Madre
        nombreMadre = Lector.leerTexto("Nombre de la madre:");
        documentoMadre = Lector.leerTexto("Documento de la madre:");
        telefonoMadre = Lector.leerTexto("Telefono de la madre:");
 
        // Constructor actualizado
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
                                   1. Datos de los acudientes
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

    static void cargarDatosDeEjemplo(KinderAtelier kinder) {
        Empleado secretaria;
        Profesor profesorMusica;
        Estudiante sofia;
        Estudiante mateo;
 
        secretaria = new Empleado("43567890", "Luz Marina", "Ospina",
                "3105558899", "Sura", "EMP-01", "Secretaria academica",
                2200000.0, "01/02/2020");
 
        profesorMusica = new Profesor("71234567", "Carlos", "Restrepo",
                "3009991122", "Nueva EPS", "EMP-03", 2600000.0, "15/01/2022",
                "Licenciado en Musica", "Musica");
 
        kinder.agregarEmpleado(secretaria);
        kinder.agregarEmpleado(profesorMusica);
 
        /* Datos de ejemplo actualizados con los dos padres */
        sofia = new Estudiante("1098765432", "Sofia", "Gomez Ruiz",
                "3201234567", "Sura", "12/03/2022", "O+", "",
                "Canta y baila todo el dia", "Calle 45 # 30-12",
                "Carlos Gomez", "11223344", "3209876543", // Datos Padre
                "Ana Ruiz Molina", "43112233", "3201234567"); // Datos Madre
 
        mateo = new Estudiante("1087654321", "Mateo", "Alvarez Diaz",
                "3117654321", "Savia Salud", "08/07/2021", "A+", "Mani",
                "Dibuja muy bien", "Carrera 50 # 12-04",
                "Jorge Alvarez Pena", "71445566", "3117654321", // Datos Padre
                "Maria Diaz", "43215678", "3101234567"); // Datos Madre
 
        kinder.agregarEstudiante(sofia);
        kinder.agregarEstudiante(mateo);
        // los matriculo de una para que ya haya algo que ver en los reportes
        kinder.matricular(sofia, new String[]{"Musica", "Danza"}, secretaria);
        kinder.matricular(mateo, new String[]{"Plastica", "Teatro"}, secretaria);
 
        Lector.mostrar("""
                       Datos de ejemplo cargados:
 
                       EMPLEADOS
                       - Luz Marina Ospina, doc. 43567890 (administrativa)
                       - Carlos Restrepo, doc. 71234567 (profesor de musica)
 
                       ESTUDIANTES (ya matriculados)
                       - Sofia Gomez Ruiz, doc. 1098765432 - Musica y Danza
                       - Mateo Alvarez Diaz, doc. 1087654321 - Plastica y Teatro
 
                       Use el documento 43567890 para matricular.
                       Pruebe con 71234567 para ver el rechazo.""");
    }
}
