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
public class Estudiante extends Persona {
    
    private static final int ANIO_ACTUAL = 2026;
    private static final int MAX_NOTAS = 5;

    /*DATOS DEL ESTUDIANTE*/
    private String fechaNacimiento;  /*dd/mm/aaaa*/
    private String tipoSangre;
    private String alergias;
    private String habilidades;
    private String direccion;

    /*DATOS DEL ACUDIENTE PADRE*/
    private String nombrePadre;
    private String documentoPadre;
    private String telefonoPadre;
    
    /*DATOS DEL ACUDIENTE MADRE*/
    private String nombreMadre;
    private String documentoMadre;
    private String telefonoMadre;

    /*NOTAS*/
    private ArrayList<Float> notas;

    public Estudiante(String dDocumento, String dNombres, String dApellidos, String dTelefono, String dEps, 
                      String dFechaNacimiento, String dTipoSangre, String dAlergias, String dHabilidades, 
                      String dDireccion, String dNombrePadre, String dDocumentoPadre, String dTelefonoPadre, 
                      String dNombreMadre, String dDocumentoMadre, String dTelefonoMadre) {
        
        super(dDocumento, dNombres, dApellidos, dTelefono, dEps);
        fechaNacimiento = dFechaNacimiento;
        tipoSangre = dTipoSangre;
        alergias = dAlergias;
        habilidades = dHabilidades;
        direccion = dDireccion;
        
        nombrePadre = dNombrePadre;
        documentoPadre = dDocumentoPadre;
        telefonoPadre = dTelefonoPadre;
        
        nombreMadre = dNombreMadre;
        documentoMadre = dDocumentoMadre;
        telefonoMadre = dTelefonoMadre;

        notas = new ArrayList<>();
    }
    
    /*MANEJO DE NOTAS*/
    public ArrayList<Float> getNotas() {
        return notas;
    }

    public boolean agregarNota(float nota) {
        if (nota < 0.0f || nota > 5.0f) {
            return false;
        }
        if (notas.size() >= MAX_NOTAS) {
            return false;
        }
        return notas.add(nota);
    }

    public boolean modificarNota(int indice, float nuevaNota) {
        if (nuevaNota < 0.0f || nuevaNota > 5.0f) {
            return false;
        }
        if (indice < 0 || indice >= notas.size()) {
            return false;
        }
        notas.set(indice, nuevaNota);
        return true;
    }

    public double calcularPromedio() {
        if (notas.size() < MAX_NOTAS) {
            return -1.0;
        }
        double suma = 0;
        for (float nota : notas) {
            suma += nota;
        }
        return suma / MAX_NOTAS;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public String getHabilidades() {
        return habilidades;
    }
    
    public String getNombrePadre() {
        return nombrePadre;
    }

    public String getDocumentoPadre() {
        return documentoPadre;
    }

    public String getTelefonoPadre() {
        return telefonoPadre;
    }

    public String getNombreMadre() {
        return nombreMadre;
    }

    public String getDocumentoMadre() {
        return documentoMadre;
    }

    public String getTelefonoMadre() {
        return telefonoMadre;
    }

    public int calcularEdad() {
        int anioNacimiento;
        int edad;
        anioNacimiento = Integer.parseInt(fechaNacimiento.substring(6));
        edad = (ANIO_ACTUAL - anioNacimiento);
        return edad;
    }

    public String mostrarAcudiente() {
        String datos;
        datos = """
                ======= ACUDIENTES RESPONSABLES ======
                Estudiante: """ + getNombreCompleto() + " (doc. " + documento + ")\n"
                + "--- Datos del Padre ---\n"
                + "Nombre: " + nombrePadre + "\n"
                + "Documento: " + documentoPadre + "\n"
                + "Telefono: " + telefonoPadre + "\n"
                + "--- Datos de la Madre ---\n"
                + "Nombre: " + nombreMadre + "\n"
                + "Documento: " + documentoMadre + "\n"
                + "Telefono: " + telefonoMadre + "\n";
        return datos;
    }

    @Override
    public String mostrarDatos(){
        String datos;
        String textoAlergias;
        String textoHabilidades;
        
        if (alergias.isEmpty()) {
            textoAlergias = "ninguna reportada";
        } else {
            textoAlergias = alergias;
        }
        if (habilidades.isEmpty()) {
            textoHabilidades = "sin registrar";
        } else {
            textoHabilidades = habilidades;
        }
        
        datos = """
                ======= ESTUDIANTE =======
                Documento: """ + documento + "\n"
                + "Nombre: " + getNombreCompleto() + "\n"
                + "Fecha nacimiento: " + fechaNacimiento + "\n"
                + "Edad: " + calcularEdad() + " anios\n"
                + "EPS: " + eps + "\n"
                + "Tipo de sangre: " + tipoSangre + "\n"
                + "Alergias: " + textoAlergias + "\n"
                + "Habilidades: " + textoHabilidades + "\n"
                + "Direccion: " + direccion + "\n"
                + "Telefono: " + telefono + "\n"
                + "Padre: " + nombrePadre + " (Tel. " + telefonoPadre + ")\n"
                + "Madre: " + nombreMadre + " (Tel. " + telefonoMadre + ")\n";
        return datos;
    }
}