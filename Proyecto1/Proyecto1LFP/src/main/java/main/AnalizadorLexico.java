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
    private int linea;
    private int columna;
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
    
}
