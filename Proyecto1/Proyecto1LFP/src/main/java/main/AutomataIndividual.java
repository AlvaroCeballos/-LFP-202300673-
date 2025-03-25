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
    
}
