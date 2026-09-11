/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.programa;

/**
 *
 * @author Ángela
 */
public abstract class Persona {
    
    protected String documento;
    protected String nombres;
    protected String apellidos;
    protected String telefono;
    protected String eps;
    
    public Persona(String dDocumento, String dNombres, String dApellidos, String dTelefono, String dEps){
        documento = dDocumento;
        nombres = dNombres;
        apellidos = dApellidos;
        telefono = dTelefono;
        eps = dEps;
    }
    public String getDocumento() {
        return documento;
    }
    public String getNombres() {
        return nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getEps() {
        return eps;
    }
    public String getNombreCompleto() {
        return (nombres + " " + apellidos);
    }
    public abstract String mostrarDatos();
}
