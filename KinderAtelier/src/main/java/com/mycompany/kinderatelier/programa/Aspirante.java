/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.programa;

/**
 *
 * @author Ángela
 */
public class Aspirante {
    private String nombreResponsableFinanciero;
    private String identificacion;
    private double salario;
    private double puntaje;
    public Aspirante(String dNombreResponsableFinanciero, String dIdentificacion, double dSalario) {
        nombreResponsableFinanciero = dNombreResponsableFinanciero;
        identificacion = dIdentificacion;
        salario = dSalario;
        puntaje = calcularPuntaje();
    }
    public String getNombreAcudiente() {
        return nombreResponsableFinanciero;
    }
    public String getIdentificacion() {
        return identificacion;
    }
    public double getSalario() {
        return salario;
    }
    public double getPuntaje() {
        return puntaje;
    }
    private double calcularPuntaje() {
        return salario / KinderAtelier.VALOR_MATRICULA; // da el número de veces q puede pagarse la matricula con el salario, entre más alto más capacidad de pago
    }
    public String mostrarDatos() {
        return """
               ======= ASPIRANTE =======
               """
               + "Acudiente: " + nombreResponsableFinanciero + "\n"
               + "Identificacion: " + identificacion + "\n"
               + "Salario: $" + salario + "\n"
               + "Puntaje: " + String.format("%", puntaje) + "\n";
    }
}