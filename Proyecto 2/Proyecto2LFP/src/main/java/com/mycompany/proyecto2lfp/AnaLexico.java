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
            /*
            else if(this.estado == 2){
                q1(cadena.charAt(this.iArchivo));
            }
            else if(this.estado == 1){
                q3(cadena.charAt(this.iArchivo));
            }
*/
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
    /*else if (Character.isLetter(caracter)) {
        this.buffer += caracter;
        this.posY++;
        this.estado = 2;
    } else if (caracter == '"') {
        this.buffer += caracter;
        this.posY++;
        this.estado = 1;
        
    } 
*/
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
         if(Character.isDigit(caracter)){
        this.buffer += caracter;
        this.posY++;
    } else {
        // Al encontrar algo que no es dígito, generamos el token con lo que teníamos acumulado
        this.nuevoToken(buffer, "Numero", this.posX, this.posY);
        // Volvemos al estado inicial para procesar el nuevo carácter
        this.estado = 0;
        // Procesamos el carácter actual como si estuviéramos en el estado 0
        this.iArchivo--; // Retrocedemos para que el bucle principal procese este carácter nuevamente
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


