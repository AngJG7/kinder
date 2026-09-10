/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.lectura;
import javax.swing.JOptionPane;

/**
 *
 * @author Ángela
 */
public class Lector {
    public static String leerTexto(String mensaje) {
        String ingreso;
        ingreso = (String) JOptionPane.showInputDialog(null, mensaje, "",
                JOptionPane.PLAIN_MESSAGE);
        if (ingreso == null) {
            return "";
        }return ingreso;
    }
    public static int leerEntero(String mensaje) {
        String ingreso;
        int numero;
        boolean valido;
        numero = 0;
        valido = false;
        while (!valido) {
            ingreso = leerTexto(mensaje);
            if (esNumeroEntero(ingreso)) {
                numero = Integer.parseInt(ingreso);
                valido = true;
            } else {
                mostrar("Dato invalido. Escriba un numero entero.");
            }
        }return numero;
    }
    public static float leerFloat(String mensaje) {
        String ingreso;
        float numero;
        boolean valido;
        numero = 0;
        valido = false;
        while (!valido) {
            ingreso = leerTexto(mensaje);
            if (esNumeroFloat(ingreso)) {
                numero = Float.parseFloat(ingreso);
                valido = true;
            } else {
                mostrar("Dato invalido. Escriba un numero entero.");
            }
        }return numero;
    }
    public static void mostrar(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "",
                JOptionPane.PLAIN_MESSAGE);
    }
    private static boolean esNumeroEntero(String texto) {
        char caracter;

        if (texto.isEmpty()) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            caracter = texto.charAt(i);
            if (caracter < '0' || caracter > '9') {
                return false;
            }
        }return true;
    }
    private static boolean esNumeroFloat(String texto) {
        char caracter;

        if (texto.isEmpty()) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            caracter = texto.charAt(i);
            if ((caracter < '0' || caracter > '9') && caracter != '.') {
                return false;
            }
        }return true;
    }
}
