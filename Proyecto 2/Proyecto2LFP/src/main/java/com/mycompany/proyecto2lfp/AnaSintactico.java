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
public class AnaSintactico {
    private List<Token> ListaTokens;
    private List<ErrorSL> ListaErrores;
    
 public AnaSintactico(List<Token> ListaTokens){
        this.ListaTokens = ListaTokens;
        this.ListaErrores = new ArrayList<>();
    }
 
 public void agregarError(Token token){
     
    // this.ListaErrores.add(new Error(token.getLexema(), "No se esperaba el token "+token.getLexema(), token.getPosY(), token.getPosX(), "Sintáctico", token.getTipoError()));  
   }
 
    public void analizar(){
        this.INICIO();
    }
    
    public void INICIO(){
        System.out.println("INICIO");
        this.MUNDOS();
    }
    
    public void MUNDOS(){
        System.out.println("MUNDOS");
        this.MUNDOU();
        this.MUNDOSP();
    }
    
    public void MUNDOSP(){
       System.out.println("MUNDOS PRIMA");
    }
    
    public void MUNDOU(){
       System.out.println("MUNDO UNICO"); 
    }
    
}
