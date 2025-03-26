/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aceba
 */
public class AutomataIndividual {
    
    private String nombreAutomata;
        private List<String> estadosGlobales;
    private List<String> alfabeto;
    private String estadoInicial;
    private List<String> estadosFinales;
    
    private Map<String, Map<String, String>> transiciones;
    
    
    public AutomataIndividual(String nombre){
        this.nombreAutomata = nombreAutomata;
        this.estadosFinales = new ArrayList<>();
        this.transiciones = new HashMap<>();
    }
    
    public void agregarAlfabeto(List<String> alfabeto) {
    this.alfabeto = new ArrayList<>(alfabeto);
}
    
    public void agregarEstadoInicial(String eInicial)
    {
        this.estadoInicial = eInicial;
    }
    
    public void agregarEstadoFinal(String estado){
        this.estadosFinales.add(estado);
    }
    
    public void agregarTransicion(String estadoActual, String entrada, String estadoObjetivo){
        transiciones.putIfAbsent(estadoActual, new HashMap<>());
        transiciones.get(estadoActual).put(entrada, estadoObjetivo);
        
    }
    
        public Map<String, Map<String, String>> getTransiciones() {
        return this.transiciones;
    }
    
      public void graficar(){
        String dotFile = "C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto1\\AFD.dot";
        
        String pngFile = "C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto1\\AFD.png";
        
        try{
            FileWriter writer = new FileWriter(dotFile);
            
            writer.write("digraph G {\n");
             writer.write("rankdir=LR;\n"); 
             writer.write("}"); //Fin
             writer.close();
             
             String transicionesDot = "";
             String infoDot = "";
             
             for(Map.Entry<String, Map<String, String>> entry : this.transiciones.entrySet()){
                 String TranEinicial = entry.getKey();
             }
        
}catch (IOException e) {
            System.out.println("Error al escribir el archivo.");
            e.printStackTrace();
        }

}
}


