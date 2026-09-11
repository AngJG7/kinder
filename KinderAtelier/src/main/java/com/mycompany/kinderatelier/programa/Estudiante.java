package com.mycompany.kinderatelier.programa;

import java.util.ArrayList;

public class Estudiante extends Persona {

    private static final int ANIO_ACTUAL = 2026;
    private static final int MAX_NOTAS = 5;

    private String fechaNacimiento;
    private String tipoSangre;
    private String alergias;
    private String habilidades;
    private String talento;
    private String direccion;
    private String patologias;

    private String nombreAcudiente;
    private String documentoAcudiente;
    private String parentesco;
    private String telefonoAcudiente;

    private ArrayList<Float> notas;

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

        talento = "";
        patologias = "";
        notas = new ArrayList<>();
    }
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
    public String getTalento() {
        return talento;
    }
    public void setTalento(String dTalento) {
        talento = dTalento;
    }
    public boolean tieneTalento() {
        return !talento.isEmpty();
    }
    public String getPatologias() {
        return patologias;
    }
    public void setPatologias(String dPatologias) {
        patologias = dPatologias;
    }

    public boolean requierePatologias() {
        return calcularEdad() < 3;
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
    public String getAlergias(){
        return alergias;
    }
    public String getADireccion(){
        return direccion;
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
        String textoTalento;
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
        if (talento.isEmpty()) {
            textoTalento = "sin identificar";
        } else {
            textoTalento = talento;
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
                + "Talento: " + textoTalento + "\n"
                + "Direccion: " + direccion + "\n"
                + "Telefono: " + telefono + "\n"
                + "Acudiente: " + nombreAcudiente + " (" + parentesco + ")\n"
                + "Tel. acudiente: " + telefonoAcudiente + "\n";
        if (requierePatologias()) {
            String textoPatologias = patologias.isEmpty()
                    ? "sin registrar" : patologias;
            datos = datos + "Patologias (menor de 3 anios): " + textoPatologias + "\n";
        }
        return datos;
    }
}

