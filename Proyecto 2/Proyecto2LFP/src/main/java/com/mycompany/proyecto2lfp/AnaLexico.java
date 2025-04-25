/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2lfp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aceba
 */
public class AnaLexico {
    private List<Token> ListaTokens;
    private List<ErrorSL> ListaErrores;
    private int posX;
    private int posY;
    private String buffer;
    private int estado;
    private int iArchivo;
    
    public AnaLexico(){
        this.ListaTokens = new ArrayList<>();
        this.ListaErrores = new ArrayList<>();
    }
    
    public void nuevoToken(String caracter, String token, int posX, int posY){
        this.ListaTokens.add(new Token(caracter, token, posX, posY));
        this.buffer = "";
    }
    
    
    public void nuevoError(String caracter, int posX, int posY, String tipoError){
        this.ListaErrores.add(new ErrorSL(caracter, "Caracter "+caracter+" no reconocido en el lenguaje", posX, posY, tipoError));
        this.buffer = "";
    }
    
     public void analizarArchivo(StringBuilder cadena){
        this.ListaTokens.clear();
        this.ListaErrores.clear();
        this.estado = 0;
        this.buffer = "";
        this.iArchivo = 0;
        
     }
     public void q0q4(char caracter){
         if (caracter == '{') {
        this.nuevoToken(String.valueOf(caracter), "LlaveAbrir", this.posX, this.posY);
        this.posY++;
    } else if (caracter == '}') {
        this.nuevoToken(String.valueOf(caracter), "LlaveCerrar", this.posX, this.posY);
        this.posY++;
    } else if (caracter == ':') {
        this.nuevoToken(String.valueOf(caracter), "DosPuntos", this.posX, this.posY);
        this.posY++;
    } else if (caracter == ',') {
        this.nuevoToken(String.valueOf(caracter), "Coma", this.posX, this.posY);
        this.posY++;
    } else if (caracter == '(') {
        this.nuevoToken(String.valueOf(caracter), "ParentesisAbrir", this.posX, this.posY);
        this.posY++;
    } else if (caracter == ')') {
        this.nuevoToken(String.valueOf(caracter), "ParentesisCerrar", this.posX, this.posY);
        this.posY++;
     }
}
}


