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
        
        while(this.iArchivo < cadena.length()){
            if(this.estado == 0){
                q0q4(cadena.charAt(this.iArchivo));
            } 
            else if(this.estado == 3){
                q3(cadena.charAt(this.iArchivo));
            }
           
            else if(this.estado == 2){
                q2(cadena.charAt(this.iArchivo));
            }
            
            else if(this.estado == 1){
                q1(cadena.charAt(this.iArchivo));
            }

            iArchivo++;
            
        }
        if(!buffer.isEmpty()) {
        if(estado == 3) {
            nuevoToken(buffer, "Numero", posX, posY);
        }
    }
        
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
     } else if (caracter == '\n') {
        this.posX++;
        this.posY = 0;
    } else if (caracter == ' ') {
        this.posY++;
    } else if (caracter == '\t') {
        this.posY += 4;
    } 
    else if (Character.isDigit(caracter)) {
            this.buffer += caracter;
            this.posY++;
            this.estado = 3;
        }
    else if (Character.isLetter(caracter)) {
        this.buffer += caracter;
        this.posY++;
        this.estado = 2;
    } 
    else if (caracter == '"') {
        this.buffer += caracter;
        this.posY++;
        this.estado = 1;
        
    } 

    else {
        this.buffer += caracter;
        String mensajeError = "Carácter no válido: '" + caracter + "'";
        this.nuevoError(String.valueOf(caracter), this.posX, this.posY, "Error de tipo lexico");
        this.posY++;
        this.buffer = "";
        System.out.println(mensajeError);
    }
         
         
}
     
     public void q3(char caracter){
    if(Character.isDigit(caracter)) {
        this.buffer += caracter;
        this.posY++;
    } else {
        int tokenPosY = this.posY - buffer.length();
        this.nuevoToken(buffer, "Numero", this.posX, tokenPosY);
        this.estado = 0;
        this.iArchivo--;
    }
}
     
public void q2(char caracter) {
    if (Character.isDigit(caracter) || Character.isLetter(caracter)) {
        this.buffer += caracter;
        this.posY += 1;
    } else {
        if (this.buffer.equals("world")) {
            this.nuevoToken(this.buffer, "PRWorld", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
        } else if (this.buffer.equals("place")) {
            this.nuevoToken(this.buffer, "PRPlace", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
        } else if (this.buffer.equals("at")) {
            this.nuevoToken(this.buffer, "PRAt", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
        } else if (this.buffer.equals("playa") || this.buffer.equals("cueva") || 
                  this.buffer.equals("templo") || this.buffer.equals("jungla") || 
                  this.buffer.equals("montana") || this.buffer.equals("pueblo") || 
                  this.buffer.equals("isla") || this.buffer.equals("rio") || 
                  this.buffer.equals("volcan") || this.buffer.equals("pantalla")) {
            
            this.nuevoToken(this.buffer, "PRLugar", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
            
        } else if (this.buffer.equals("tesoro") || this.buffer.equals("llave") ||
                  this.buffer.equals("arma") || this.buffer.equals("objetomagico") ||
                  this.buffer.equals("pocion") || this.buffer.equals("trampa") ||
                  this.buffer.equals("libro") || this.buffer.equals("herramienta") ||
                  this.buffer.equals("bandera") || this.buffer.equals("gema")) {
            
            this.nuevoToken(this.buffer, "PRObjeto", posX, posY);
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
        }else if(this.buffer.equals("connect")){
            this.nuevoToken(this.buffer, "PRConnect", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
            
        }else if(this.buffer.equals("to")){
            this.nuevoToken(this.buffer, "PRTo", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
            
        }else if(this.buffer.equals("with")){
            this.nuevoToken(this.buffer, "PRWith", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
            
        }else if(this.buffer.equals("object")){
            this.nuevoToken(this.buffer, "PalabraObjeto", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
            
        }else {
            this.nuevoToken(this.buffer, "Identificador", posX, posY);
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
        }
    }
}
     
     public void q1(char caracter){
            if(caracter != '"'){
                this.buffer+=caracter;
                this.posY+=1;
                
                
            }else{
                this.buffer+=caracter;
                this.nuevoToken(this.buffer, "Cadena de texto", posX, posY);
                this.posY+=1;
                this.estado=0;
            }
        }
     
     public void imprimirTokens(){
        for(Token token: this.ListaTokens){
            System.out.println(token);
        }
    }
    
    public void imprimirErrores(){
        for(ErrorSL error: this.ListaErrores){
            System.out.println(error);
        }
    }
    
    public List<Token> getTokens(){
        return this.ListaTokens;
    }
}


