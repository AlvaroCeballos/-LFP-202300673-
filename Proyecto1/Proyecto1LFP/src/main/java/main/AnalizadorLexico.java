/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author aceba
 */
public class AnalizadorLexico {
    private List<Tokens> ListaTokens;
    private List<ErrorLexico> ListaErrores;
    private int posX;
    private int posY;
    private String buffer;
    private int bandera;
    private int iArchivo;
    
    
    public AnalizadorLexico(){
        this.ListaTokens = new ArrayList<>();
        this.ListaErrores = new ArrayList<>();
    }
    
    public void agregarToken(String caracter, String token, int linea, int columna){
        this.ListaTokens.add(new Tokens(caracter, token, linea, columna));
        this.buffer = "";
    }
    
    
    public void agregarError(String caracter, int linea, int columna){
        this.ListaErrores.add(new ErrorLexico(caracter, "Caracter "+caracter+" no reconocido en el lenguaje", linea, columna));
        this.buffer = "";
    }
    
    public void analizarArchivo(StringBuilder cadena){
        this.ListaTokens.clear();
        this.ListaErrores.clear();
        this.bandera = 0;
        this.iArchivo = 0;
        this.buffer = "";
        
        
    }
    
}
