/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aceba
 */
public class Proyecto1LFP {

    public static void main(String[] args) {
        AnalizadorLexico analizadorLexico = new AnalizadorLexico();       
        try {
    File archivo = new File("C:\\Users\\aceba\\OneDrive\\Desktop\\Tareas\\Proyectos\\LFP\\entradaPruebaGraficando.txt");
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
        
        
        List<Tokens> tokensAnalizados = analizadorLexico.getTokens();
        List<String> nombresAutomatas = new ArrayList<>();
                List<String> estadosIniciales = new ArrayList<>();
        
         int i = 0;
        while(i < tokensAnalizados.size()){

                    
                   if(tokensAnalizados.get(i).getTipoToken().equals("Identificador") && i+1 < tokensAnalizados.size() && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
                        
                        String nombre = tokensAnalizados.get(i).getLexema();
                        nombresAutomatas.add(nombre);
                        AutomataIndividual afd = new AutomataIndividual(nombre);
                        
                        System.out.println("Automata encontrado: " + nombre);
 
                        i+=2; 
                       
                        if(i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") && tokensAnalizados.get(i).getLexema().equals("inicial") && i+2 < tokensAnalizados.size() && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
                            i+=2;
                            
                            if(i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Identificador")) {
                                String estadoInicial = tokensAnalizados.get(i).getLexema();
                                estadosIniciales.add(estadoInicial);
                                afd.agregarEstadoInicial(estadoInicial);
                                
                                System.out.println("Estado inicial encontrado: " + estadoInicial + " para autómata " + nombre);
                                
                                i+=2; // Avanzamos al siguiente token después del estado inicial
                            }
                        }
                        
                        
                    } else {
                        i++; // Solo avanzamos si no encontramos un patrón válido
                    }
                }
                

                System.out.println("Autómatas encontrados (" + nombresAutomatas.size() + "):");
                for(String nombre : nombresAutomatas) {
                    System.out.println("- " + nombre);
                }
                
                System.out.println("\nEstados iniciales encontrados (" + estadosIniciales.size() + "):");
                for(String estado : estadosIniciales) {
                    System.out.println("- " + estado);
                }

        
    } else {
        System.out.println("El archivo no existe.");
    }
}
    catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
    

