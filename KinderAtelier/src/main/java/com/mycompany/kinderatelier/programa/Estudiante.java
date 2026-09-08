/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.programa;

/**
 *
 * @author Ángela
 */
public class Estudiante extends Persona {
    
    private static final int ANIO_ACTUAL = 2026;
    /*DATOS DEL ESTUDIANTE*/
    private String fechaNacimiento;  /*dd/mm/aaaa*/
    private String tipoSangre;
    private String alergias;
    private String habilidades;
    private String direccion;
    /*DATOS DEL ACUDIENTE*/
    private String nombreAcudiente;
    private String documentoAcudiente;
    private String parentesco;
    private String telefonoAcudiente;
    
    public Estudiante(String dDocumento, String dNombres, String dApellidos, String dTelefono, String dEps, String dFechaNacimiento, String dTipoSangre, String dAlergias,
                      String dHabilidades, String dDireccion, String dNombreAcudiente, String dDocumentoAcudiente, String dParentesco, String dTelefonoAcudiente){
        super(dDocumento, dNombres, dApellidos, dTelefono, dEps);
        fechaNacimiento = dFechaNacimiento;
        tipoSangre = dTipoSangre;
        alergias = dAlergias;
        habilidades = dHabilidades;
        direccion = dDireccion;
        
        nombreAcudiente = dNombreAcudiente;
        documentoAcudiente = dDocumentoAcudiente;
        parentesco = dParentesco;
        telefonoAcudiente = dTelefonoAcudiente;
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
    public String getNombreAcudiente() {
        return nombreAcudiente;
    }
    public String getParentesco() {
        return parentesco;
    }
    public String getTelefonoAcudiente() {
        return telefonoAcudiente;
    }
    public String getDocumentoAcudiente() {
        return documentoAcudiente;
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
                ======= ACUDIENTE RESPONSABLE ======
                Estudiante: """ + getNombreCompleto() + " (doc. " + documento + ")\n"
                + "Acudiente: " + nombreAcudiente + "\n"
                + "Documento: " + documentoAcudiente + "\n"
                + "Parentesco: " + parentesco + "\n"
                + "Telefono: " + telefonoAcudiente + "\n";
        return datos;
    }

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
                + "Acudiente: " + nombreAcudiente + " (" + parentesco + ")\n"
                + "Tel. acudiente: " + telefonoAcudiente + "\n";
        return datos;
    }
}
