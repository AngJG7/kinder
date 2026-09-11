/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.programa;

/**
 *
 * @author Ángela
 */
public class Empleado extends Persona {
    protected String idEmpleado;
    protected String cargo;
    protected Double salario;
    protected String fechaIngreso;
    
    public Empleado(String dDocumento, String dNombres, String dApellidos, String dTelefono, String dEps, String dIdEmpleado, String dCargo,
            Double dSalario, String dFechaIngreso) {
        super(dDocumento, dNombres, dApellidos, dTelefono, dEps);
        idEmpleado = dIdEmpleado;
        cargo = dCargo;
        salario = dSalario;
        fechaIngreso = dFechaIngreso;
    }
    public String getIdEmpleado() {
        return idEmpleado;
    }
    public String getCargo() {
        return cargo;
    }
    public Double getSalario() {
        return salario;
    }
    public Boolean esAdministrativo() {
        return true;
    }
    public String mostrarDatos() {
        String datos;
        datos = """
                ======= EMPLEADO =======
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
