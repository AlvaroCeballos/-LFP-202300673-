/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto2lfp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author aceba
 */
public class Proyecto2LFP {

    public static void main(String[] args) {

        AnaLexico analizador = new AnaLexico();
        AnaSintactico analizadorSintactico = new AnaSintactico(analizador.getTokens());
        
       
       
       try {
    File archivo = new File("C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto 2\\xd.txt");
    StringBuilder content = new StringBuilder();
    if (archivo.exists()) {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
            while ((linea = br.readLine()) != null) {
                content.append(linea).append("\n");
            }

        System.out.println(content);
        analizador.analizarArchivo(content);
        
        analizador.imprimirTokens();
        System.out.println("");
        analizador.imprimirErrores();
        analizadorSintactico.analizar();
    } else {
        System.out.println("El archivo no existe.");
    }
}
    catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    }

