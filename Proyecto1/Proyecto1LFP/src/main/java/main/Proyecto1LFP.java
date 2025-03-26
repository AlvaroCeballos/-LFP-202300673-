/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aceba
 */
public class Proyecto1LFP {
    private static Map<String, AutomataIndividual> automata;

    public static void main(String[] args) {
        AnalizadorLexico analizadorLexico = new AnalizadorLexico(); 
        automata = new HashMap<>();
        
        //
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
        //analizadorLexico.imprimirTokens();
        
        System.out.println("");
        analizadorLexico.imprimirErrores();
        
        
        List<Tokens> tokensAnalizados = analizadorLexico.getTokens();
        List<String> nombresAutomatas = new ArrayList<>();
        List<String> descripcionesLista = new ArrayList<>();
        List<String> estadosGlobales = new ArrayList<>();
        List<String> estadosIniciales = new ArrayList<>();
        List<String> estadosFinales = new ArrayList<>();
        List<String> TransicionesLista = new ArrayList<>();
        
        // Agrega esto para depuración:
System.out.println("\n=== TOKENS DETALLADOS ===");
for (int j = 0; j < tokensAnalizados.size(); j++) {
    System.out.println(j + ": " + tokensAnalizados.get(j).getTipoToken() + 
                       " - '" + tokensAnalizados.get(j).getLexema() + "'");
}
        // I VA EN 0
         int i = 0;
        while(i < tokensAnalizados.size()){

                    
   if(tokensAnalizados.get(i).getTipoToken().equals("Identificador") 
       && i+2 < tokensAnalizados.size() 
       && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")
       && tokensAnalizados.get(i+2).getTipoToken().equals("LlaveAbrir")) {
        
        String nombre = tokensAnalizados.get(i).getLexema();
        nombresAutomatas.add(nombre);
                        AutomataIndividual afd = new AutomataIndividual(nombre);
                        
                        System.out.println("Automata encontrado: " + nombre);
                        //I VA EN 1
 
                        i+=3;
                        //I VA EN 4
                        
                        
                        
                        while(i < tokensAnalizados.size() && !tokensAnalizados.get(i).getTipoToken().equals("LlaveCerrar")) {
            // Buscar descripción
            if(tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") 
               && tokensAnalizados.get(i).getLexema().equals("descripcion")
               && i+2 < tokensAnalizados.size()
               && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")
               ) {
                String descripcionActual = tokensAnalizados.get(i+2).getLexema();
                descripcionesLista.add(descripcionActual);
                System.out.println("Descripcion del automata "+ descripcionActual);
                i +=4;
                //I VA EN 8
            }
            
            else if(tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") 
            && tokensAnalizados.get(i).getLexema().equals("estados")
            && i+2 < tokensAnalizados.size()
            && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
        i += 2; // Avanzamos más allá de "estados" y ":"
        // I VA EN 10
        if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteAbrir")) {
            i++; // Avanzamos al primer estado
            // I VA EN 11
         while (i < tokensAnalizados.size() && !tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
                if (tokensAnalizados.get(i).getTipoToken().equals("Identificador")) {
                    estadosGlobales.add(tokensAnalizados.get(i).getLexema());
                    System.out.println("Estado encontrado: " + tokensAnalizados.get(i).getLexema());
                }
                
                if (i+1 < tokensAnalizados.size() && tokensAnalizados.get(i+1).getTipoToken().equals("Coma")) {
                    i++; // Saltamos la coma
                }
                // I VA EN 12
                i++; // Siguiente token
                // I VA EN 13
            }
            
            System.out.println("\n=== Lista de Estados ===");
            for (String estado : estadosGlobales) {
                System.out.println("- " + estado);
            }
        }
            }
                        }
                        
                         /*
                       
                        if(i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") && tokensAnalizados.get(i).getLexema().equals("inicial") && i+2 < tokensAnalizados.size() && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
                            i+=2;
                            
                            if(i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Identificador")) {
                                String estadoInicial = tokensAnalizados.get(i).getLexema();
                                estadosIniciales.add(estadoInicial);
                                afd.agregarEstadoInicial(estadoInicial);
                                
                                System.out.println("Estado inicial encontrado: " + estadoInicial + " para autómata " + nombre);
                                
                                i+=2; 
                            }
                        }
                        
               if(tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") && tokensAnalizados.get(i).getLexema().equals("finales")){
                    i+=3;
                    while(!tokensAnalizados.get(i).getTipoToken().equals("ParentesisCerrar")){
                        afd.agregarEstadoFinal(tokensAnalizados.get(i).getLexema());
                        estadosFinales.add(tokensAnalizados.get(i).getLexema());
                        
                        
                        if(tokensAnalizados.get(i+1).getTipoToken().equals("Coma")){
                            i+=2;
                        }else{
                            i++;
                        }
                    }
                    
                }
                i+=2;
                if(tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") && tokensAnalizados.get(i).getLexema().equals("transiciones")){
                    i+=1;
                    
                    while(!tokensAnalizados.get(i+1).getTipoToken().equals("LlaveCerrar")){
                        i+=2;
                        
                        String estadoActual = tokensAnalizados.get(i).getLexema();
                        
                        i+=3;
                        
                        while(!tokensAnalizados.get(i).getTipoToken().equals("ParentesisCerrar")){
                            String entrada = tokensAnalizados.get(i).getLexema();
                            
                            i+=2;
                            String estadoDestino = tokensAnalizados.get(i).getLexema();
                            
                            System.out.println("Estado actual :"+estadoActual + "Entrada: " +entrada + "EstadoDestino: "+estadoDestino);
                            afd.agregarTransicion(estadoActual, entrada, estadoDestino);
                            
                            
                            if(tokensAnalizados.get(i+1).getTipoToken().equals("Coma")){
                                i+=2;
                            }else{
                                i++;
                            }
                        }
                        }
                        
                    automata.put(nombre, afd);
                    
                    
                     
                    }
                }
                   i++;
        }
        automata.get("AFD1").graficar();
                


System.out.println("\nEstados iniciales encontrados (" + estadosIniciales.size() + "):");
for(String estado : estadosIniciales) {
    System.out.println("- " + estado);
}

System.out.println("\nEstados finales encontrados (" + estadosFinales.size() + "):");
for(String estado : estadosFinales) {
    System.out.println("- " + estado);
}

System.out.println("\nTransiciones encontradas:");
for (Map.Entry<String, AutomataIndividual> entry : automata.entrySet()) {
    String nombreAutomata = entry.getKey();
    AutomataIndividual afd = entry.getValue();
    
    System.out.println("\nAutómata: " + nombreAutomata);
    System.out.println("Transiciones:");

    for (Map.Entry<String, Map<String, String>> transicion : afd.getTransiciones().entrySet()) {
        String estadoActual = transicion.getKey();
        Map<String, String> destinos = transicion.getValue();
        
        for (Map.Entry<String, String> destino : destinos.entrySet()) {
            String entrada = destino.getKey();
            String estadoDestino = destino.getValue();
            System.out.printf("- %s --[%s]--> %s%n", estadoActual, entrada, estadoDestino);
        }                */

                         
                         
                        }
    else{
       i++;
   }
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

