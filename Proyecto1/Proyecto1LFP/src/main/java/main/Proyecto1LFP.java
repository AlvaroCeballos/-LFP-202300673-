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
        analizadorLexico.imprimirTokens();
        
        System.out.println("");
        analizadorLexico.imprimirErrores();
        analizadorLexico.generarReporteHTML("C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto1\\Proyecto1LFP\\reporte_tokens.html");
        
        
        List<Tokens> tokensAnalizados = analizadorLexico.getTokens();
        List<String> nombresAutomatas = new ArrayList<>();
        List<String> descripcionesLista = new ArrayList<>();
        List<String> estadosGlobales = new ArrayList<>();
        List<String> alfabeto = new ArrayList<>();
        List<String> estadosIniciales = new ArrayList<>();
        List<String> estadosFinales = new ArrayList<>();
        List<String> TransicionesLista = new ArrayList<>();
        /*
        // Agrega esto para depuración:
System.out.println("\n=== TOKENS DETALLADOS ===");
for (int j = 0; j < tokensAnalizados.size(); j++) {
    System.out.println(j + ": " + tokensAnalizados.get(j).getTipoToken() + 
                       " - '" + tokensAnalizados.get(j).getLexema() + "'");
}
        
        */
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
                        estadosGlobales.clear();
                        estadosFinales.clear();
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
            
if(tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") 
        && tokensAnalizados.get(i).getLexema().equals("estados")
        && i+2 < tokensAnalizados.size()
        && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
    i += 2; // Avanzamos más allá de "estados" y ":"
    
    if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteAbrir")) {
        i++; // Avanzamos al primer estado
        
        // Creamos un Set temporal para verificar duplicados
        java.util.Set<String> estadosUnicos = new java.util.HashSet<>();
        
        while (i < tokensAnalizados.size() && !tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
            if (tokensAnalizados.get(i).getTipoToken().equals("Identificador")) {
                String estadoActual = tokensAnalizados.get(i).getLexema();
                
                // Verificamos si el estado ya existe
                if (!estadosUnicos.contains(estadoActual)) {
                    estadosUnicos.add(estadoActual);
                    estadosGlobales.add(estadoActual);
                    System.out.println("Estado encontrado: " + estadoActual);
                } else {
                    System.out.println("Estado duplicado ignorado: " + estadoActual);
                }
            }
            
            if (i+1 < tokensAnalizados.size() && tokensAnalizados.get(i+1).getTipoToken().equals("Coma")) {
                i++; // Saltamos la coma
            }
            
            i++; // Siguiente token
        }

        System.out.println("\n=== Lista de Estados Únicos ===");
        for (String estado : estadosGlobales) {
            System.out.println("- " + estado);
        }
        System.out.println("Posición final después de estados: " + i);
        i += 2; // Ajuste de posición después del corchete de cierre
    }
}
            
            
            
                       
           if(tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") 
        && tokensAnalizados.get(i).getLexema().equals("alfabeto")
        && i+2 < tokensAnalizados.size()
        && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
    
    i += 2; // Avanzamos más allá de "alfabeto" y ":"
    
    if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteAbrir")) {
        i++; // Avanzamos al primer elemento del alfabeto
        
        alfabeto.clear(); // Limpiamos la lista para este autómata
        
        while (i < tokensAnalizados.size() && !tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
            if (tokensAnalizados.get(i).getTipoToken().equals("Cadena de texto")) {
                // Eliminamos las comillas de los símbolos
                String simbolo = tokensAnalizados.get(i).getLexema();
                alfabeto.add(simbolo);
                System.out.println("Símbolo del alfabeto encontrado: " + simbolo);
            }
            
            if (i+1 < tokensAnalizados.size() && tokensAnalizados.get(i+1).getTipoToken().equals("Coma")) {
                i++; // Saltamos la coma
            }
            
            i++; // Siguiente token
        }
        
        // Avanzamos más allá del CorcheteCerrar
        if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
            i++;
        }
        
        System.out.println("\n=== Alfabeto del Autómata ===");
        for (String simbolo : alfabeto) {
            System.out.println("- " + simbolo);
        }
    }
}
           
                            
                        i++;
                           
           if(i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") 
    && tokensAnalizados.get(i).getLexema().equals("inicial") 
    && i+2 < tokensAnalizados.size() 
    && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
    
    i += 2; // Avanzamos más allá de "inicial" y ":"
    
    if(i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Identificador")) {
        String estadoInicial = tokensAnalizados.get(i).getLexema();
        estadosIniciales.add(estadoInicial);
        afd.agregarEstadoInicial(estadoInicial);
        
        System.out.println("Estado inicial encontrado: " + estadoInicial + " para autómata " + nombre);
        
        // Avanzamos al siguiente token después del estado inicial
        i++;
        
        // Verificamos si hay una coma después (y la saltamos)
        if(i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Coma")) {
            i++;
        }
    }
} if(i < tokensAnalizados.size() && 
   tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") && 
   tokensAnalizados.get(i).getLexema().equals("finales") &&
   i+2 < tokensAnalizados.size() && 
   tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
    
    i += 2; // Avanzamos más allá de "finales" y ":"
    
    if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteAbrir")) {
        i++; // Avanzamos al primer estado final
        
        while (i < tokensAnalizados.size() && !tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
            if (tokensAnalizados.get(i).getTipoToken().equals("Identificador")) {
                String estadoFinal = tokensAnalizados.get(i).getLexema();
                
                // Verificamos que el estado exista en estadosGlobales
                if (estadosGlobales.contains(estadoFinal)) {
                    estadosFinales.add(estadoFinal);
                    afd.agregarEstadoFinal(estadoFinal);
                    System.out.println("Estado final encontrado: " + estadoFinal);
                } else {
                    System.out.println("ADVERTENCIA: El estado final " + estadoFinal + 
                                     " no existe en los estados globales");
                }
            }
            
            // Manejo de comas
            if (i+1 < tokensAnalizados.size() && tokensAnalizados.get(i+1).getTipoToken().equals("Coma")) {
                i++; // Saltamos la coma
            }
            
            i++; // Siguiente token
        }
        
        // Avanzamos más allá del CorcheteCerrar
        if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
            i++;
        }
        
        // Verificamos si hay una coma después
        if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Coma")) {
            i++;
        }
        
        System.out.println("\n=== Estados Finales del Autómata ===");
        for (String estado : estadosFinales) {
            System.out.println("- " + estado);
        }
    }
}
             
            
            
            
            
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
                        
                         
                       
                       
                      
              
                /*
                
                }
                   i++;
        }
        automata.get("AFD1").graficar();
                


System.out.println("\nEstados iniciales encontrados (" + estadosIniciales.size() + "):");
for(String estado : estadosIniciales) {
    System.out.println("- " + estado);
}



                */

                         
                         
                        }
    else{
       i++;
       System.out.println(i+ "FINAL"); 
   }
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
        }
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