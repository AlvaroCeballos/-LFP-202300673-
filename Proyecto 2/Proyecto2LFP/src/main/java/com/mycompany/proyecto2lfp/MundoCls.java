/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2lfp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aceba
 */
public class MundoCls {
    HashMap<String, LugarCls> HMlugares;
    HashMap<String, FormasFigurasL> HMlugaresDecorados;
    
    
    public MundoCls(){
        HMlugares = new HashMap<>();
        HMlugaresDecorados = new HashMap<>();
        
        this.HMlugaresDecorados.put("playa", new FormasFigurasL("ellipse", "lightblue"));
        this.HMlugaresDecorados.put("cueva", new FormasFigurasL("box", "gray"));
        this.HMlugaresDecorados.put("templo", new FormasFigurasL("octagon", "gold"));
        this.HMlugaresDecorados.put("jungla", new FormasFigurasL("parallelogram", "forestgreen"));
        this.HMlugaresDecorados.put("montana", new FormasFigurasL("triangle", "sienna"));
        this.HMlugaresDecorados.put("pueblo", new FormasFigurasL("house", "burlywood"));
        this.HMlugaresDecorados.put("isla", new FormasFigurasL("invtriangle", "lightgoldenrod"));
        this.HMlugaresDecorados.put("rio", new FormasFigurasL("hexagon", "deepskyblue"));
        this.HMlugaresDecorados.put("volcan", new FormasFigurasL("doublecircle", "orangered"));
        this.HMlugaresDecorados.put("pantano", new FormasFigurasL("trapezium", "darkseagreen"));
}
    
    public void agregarPlace(String identificador, LugarCls lugar){
        this.HMlugares.put(identificador, lugar);
    }
    
    public void graficar(){
        String archivodot = ("C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto 2\\archivo.dot");
        String archivoImagen = ("C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto 2\\grafica.png");
        
        
        
        String dotContent = "digraph G {\n";
        dotContent += "node [style=filled fontname=\"Noto Color Emoji\"];\n";
        dotContent += "graph [layout=neato, splines=true, overlap=false];\n";
        
        for (Map.Entry<String, LugarCls> entry: HMlugares.entrySet()){
            String identificador = entry.getKey();
            LugarCls lugar = entry.getValue();
            
            FormasFigurasL lugarVisual = this.HMlugaresDecorados.get(lugar.tipo);
            
            dotContent+=identificador+"[label=\""+identificador+"\", shape="+lugarVisual.getFigura()
                    +", fillcolor= "+lugarVisual.getColor()+", pos=\""+lugar.LposX+","+lugar.LposY+"!\"];";
        }
        
        dotContent+="}";
        
        
        
        
         try{ 
             FileWriter writer = new FileWriter(archivodot);
             writer.write(dotContent);
             writer.close(); // Muy importante
            }catch (IOException e) {
            System.out.println("Error al escribir el archivo.");
            e.printStackTrace();
        }
            
            String[] comando = {"dot", "-Tpng", archivodot, "-o", archivoImagen};
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


