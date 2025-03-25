/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author aceba
 */
public class Proyecto1LFP {

    public static void main(String[] args) {
        AnalizadorLexico analizadorLexico = new AnalizadorLexico();       
        try {
    File archivo = new File("C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto1\\Proyecto1LFP\\archivoEntradaPruebaProyecto1.txt");
    StringBuilder content = new StringBuilder();
    if (archivo.exists()) {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
            while ((linea = br.readLine()) != null) {
                content.append(linea).append("\n");
            }

        analizadorLexico.analizarArchivo(content);        
        analizadorLexico.imprimirTokens();
        System.out.println("");
        analizadorLexico.imprimirErrores();
    } else {
        System.out.println("El archivo no existe.");
    }
}
    catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
    

