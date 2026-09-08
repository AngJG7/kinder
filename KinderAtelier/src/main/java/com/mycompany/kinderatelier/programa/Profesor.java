/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.programa;

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
    public  Boolean esAdministrativo() {
        return false;
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