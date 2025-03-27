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
    //private List<String> estadosGlobales;
    // private List<String> alfabeto;
    private String estadoInicial;
    private List<String> estadosFinales;
    
    private Map<String, Map<String, String>> transiciones;
    
    
    public AutomataIndividual(String nombreAutomata){
        this.nombreAutomata = nombreAutomata;
        this.estadosFinales = new ArrayList<>();
        this.transiciones = new HashMap<>();
    }
    
    /*public void agregarAlfabeto(List<String> alfabeto) {
    this.alfabeto = new ArrayList<>(alfabeto);
}
    */
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
    
      public void GraficandoAutomata(){
        String ArchivoDOTGenerado = "C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto1\\AFD.dot";
        String ArchivoPNGGenerado = "C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto1\\AFD.png";
        
        try{
            FileWriter escritorArchivoDOT = new FileWriter(ArchivoDOTGenerado);
             escritorArchivoDOT.write("digraph G {\n");
             escritorArchivoDOT.write("rankdir=LR;\n"); 
             escritorArchivoDOT.write("node [shape=circle];\n");
             String transitionString ="";
             String infoAuto = "";
             
             for(Map.Entry<String, Map<String, String>> entry : this.transiciones.entrySet()){
                 String sInicial = entry.getKey();
                 if (sInicial.equals(estadoInicial)) {
                infoAuto += "invisible_start [shape=point, width=0.1, height=0.1]\n";
                infoAuto += "invisible_start -> " + sInicial + " [style=bold]\n";
            }
            if (this.estadosFinales.contains(sInicial)) {
                infoAuto += sInicial + " [shape=doublecircle]\n";
            }
                 Map<String, String> transicion = entry.getValue();
                 
                 for(Map.Entry<String, String> entry2: transicion.entrySet()){
                     String etiqueta = entry2.getKey();
                     
                     String estadoFinal = entry2.getValue();
                     
                     transitionString += sInicial + "->" + estadoFinal + "[label="+etiqueta+"]\n";
                     
                     if(this.estadosFinales.contains(estadoFinal)){
                        infoAuto+=estadoFinal+"[shape=doublecircle]\n";
                    }else{
                        infoAuto+=estadoFinal+"[shape=circle]\n";
                    }
                 }

             }
             escritorArchivoDOT.write(transitionString);
             escritorArchivoDOT.write(infoAuto);
             escritorArchivoDOT.write("}");
             escritorArchivoDOT.close();
        
        }catch (IOException e) {
            System.out.println("Error al escribir el archivo.");
            e.printStackTrace();
        }
        String[] comando = {"dot", "-Tpng", ArchivoDOTGenerado, "-o", ArchivoPNGGenerado};
        try{
            ProcessBuilder builder = new ProcessBuilder(comando);
            builder.inheritIO();
            Process proceso = builder.start();
            int exitCode = proceso.waitFor();
            
            if(exitCode == 0){
                System.out.println("Conversion completada");
            }else{
                System.err.println("Error en la conversion de codigo");
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
}
}


