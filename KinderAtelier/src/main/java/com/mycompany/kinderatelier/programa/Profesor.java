/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.programa;

import java.util.ArrayList;

/**
 *
 * @author Ángela
 */
public class Profesor extends Empleado {

    private String titulo;
    private String ramaAsignada;

    public Profesor(String dDocumento, String dNombres, String dApellidos,
                    String dTelefono, String dEps, String dIdEmpleado,
                    Double dSalario, String dFechaIngreso,
                    String dTitulo, String dRamaAsignada) {

        super(dDocumento, dNombres, dApellidos, dTelefono, dEps,
              dIdEmpleado, "Docente", dSalario, dFechaIngreso);
        titulo = dTitulo;
        ramaAsignada = dRamaAsignada;
    }

    public String getRamaAsignada() {
        return ramaAsignada;
    }

    public Boolean esAdministrativo() {
        return false;
    }

    public boolean agregarNotaAEstudiante(Estudiante estudiante, float nota) {
        return estudiante.agregarNota(nota);
    }

    public boolean modificarNotaEstudiante(Estudiante estudiante, int posicion, float nuevaNota) {
        return estudiante.modificarNota(posicion - 1, nuevaNota);
    }

    public String verNotasEstudiante(Estudiante estudiante) {
        ArrayList<Float> listaNotas = estudiante.getNotas();
        if (listaNotas.isEmpty()) {
            return "El estudiante " + estudiante.getNombreCompleto() + " no tiene notas registradas.";
        }
        String reporte = "Notas de " + estudiante.getNombreCompleto() + " (" + listaNotas.size() + "/5):\n";
        for (int i = 0; i < listaNotas.size(); i++) {
            reporte += "Nota " + (i + 1) + ": " + listaNotas.get(i) + "\n";
        }
        return reporte;
    }

    public String obtenerPromedioEstudiante(Estudiante estudiante) {
        double promedio = estudiante.calcularPromedio();
        if (promedio == -1.0) {
            return "No se puede calcular el promedio. El estudiante tiene " 
                    + estudiante.getNotas().size() + " de 5 notas registradas.";
        }
        return "El promedio de " + estudiante.getNombreCompleto() + " es: " + promedio;
    }

    public String mostrarDatos() {
        String datos;
        datos = """
                ======= PROFESOR =======
                ID Empleado: """ + idEmpleado + "\n"
                + "Documento: " + documento + "\n"
                + "Nombre: " + getNombreCompleto() + "\n"
                + "Cargo: " + getCargo() + "\n"
                + "EPS: " + eps + "\n"
                + "Salario: $" + getSalario() + "\n"
                + "Fecha de Ingreso: " + fechaIngreso + "\n"
                + "Telefono: " + telefono + "\n";
        return datos;
    }
}