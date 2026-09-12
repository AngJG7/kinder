/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kinderatelier.lectura;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;
import com.mycompany.kinderatelier.programa.Aspirante;
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
        float numero = 0;
        boolean valido = false;
        while (!valido) {
            ingreso = leerTexto(mensaje);
            if (ingreso.isEmpty()) {
                return -1; // Permite cancelar la operacion
            }
            try {
                numero = Float.parseFloat(ingreso);
                valido = true;
            } catch (NumberFormatException e) {
                mostrar("Dato invalido. Escriba un numero decimal (ejemplo: 4.5).");
            }
        }
        return numero;
    }
    public static double leerDouble(String mensaje) {
        String ingreso;
        double numero = 0;
        boolean valido = false;
        while (!valido) {
            ingreso = leerTexto(mensaje);
            if (ingreso.isEmpty()) {
                return -1; // Permite cancelar la operacion
            }
            try {
                // .replace(",", ".") permite aceptar tanto comas como puntos decimales (ej: 1500000,50 o 1500000.50)
                numero = Double.parseDouble(ingreso.replace(",", "."));
                valido = true;
            } catch (NumberFormatException e) {
                mostrar("Dato invalido. Escriba un numero decimal valido (ejemplo: 4.5 o 1500000.50).");
            }
        }
        return numero;
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

    public static void guardarCsv(Aspirante[] aspirantes, int contador) {
        String rutaArchivo = "aspirantes.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("Identificacion:NombreResponsable:Salario:Puntaje\n");

            // Solo recorre hasta las posiciones realmente ocupadas
            for (int i = 0; i < contador; i++) {
                Aspirante asp = aspirantes[i];
                if (asp != null) {
                    bw.write(asp.getIdentificacion() + ":"
                            + asp.getNombreResponsableFinanciero() + ":"
                            + asp.getSalario() + ":"
                            + String.format("%.2f", asp.getPuntaje()) + "\n");
                }
            }

            mostrar("Archivo CSV guardado exitosamente.");
        } catch (IOException e) {
            mostrar("Error al guardar el CSV: " + e.getMessage());
        }
    }

    public static int cargarCsv(Aspirante[] aspirantes, int contador) {
        String rutaArchivo = "aspirantes.csv";
        File archivo = new File(rutaArchivo);

        // Si el archivo no existe o está vacío, no se modifica la RAM ni el contador
        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("El CSV no existe o está vacío. Se conservan los datos actuales en RAM.");
            return contador;
        }

        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine(); // Descartar cabecera

            // Agrega datos a partir del índice donde va el contador actual
            while ((linea = br.readLine()) != null && contador < aspirantes.length) {
                if (linea.isBlank()) continue;

                String[] datos = linea.split(":");
                String identificacion = datos[0];
                String nombreResponsable = datos[1];
                double salario = Double.parseDouble(datos[2].replace(",", "."));

                if (yaExiste(aspirantes, contador, identificacion)) {
                    continue; // Evita duplicar si ya fue cargado previamente
                }

                aspirantes[contador] = new Aspirante(nombreResponsable, identificacion, salario);
                contador++; // Incrementa sobre el contador existente
            }

            System.out.println("Datos cargados del CSV exitosamente. Total actual: " + contador);
        } catch (IOException e) {
            System.err.println("Error al cargar el CSV: " + e.getMessage());
        }

        return contador; // Mantiene el conteo acumulado (RAM previo + CSV)
    }

    private static boolean yaExiste(Aspirante[] aspirantes, int contador, String identificacion) {
        for (int i = 0; i < contador; i++) {
            if (aspirantes[i] != null && aspirantes[i].getIdentificacion().equals(identificacion)) {
                return true;
            }
        }
        return false;
    }
}