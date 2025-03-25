/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

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
    private String estadoInicial;
    private List<String> estadosFinales;
    private Map<String, Map<String, String>> transiciones;
    
    
    public AutomataIndividual(String nombre){
        this.nombreAutomata = nombreAutomata;
        this.estadosFinales = new ArrayList<>();
        this.transiciones = new HashMap<>();
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
    
    public String obtenerTransicionesFormateadas() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Map<String, String>> entryEstado : transiciones.entrySet()) {
            String estadoActual = entryEstado.getKey();
            Map<String, String> transicionesEstado = entryEstado.getValue();
            
            for (Map.Entry<String, String> entryTransicion : transicionesEstado.entrySet()) {
                String entrada = entryTransicion.getKey();
                String estadoDestino = entryTransicion.getValue();
                
                sb.append(String.format("%s --[%s]--> %s%n", estadoActual, entrada, estadoDestino));
            }
        }
        return sb.toString();
    }
}
    

