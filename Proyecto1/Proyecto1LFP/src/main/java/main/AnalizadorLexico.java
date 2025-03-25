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
    private int estado;
    private int iArchivo;
    
    
    public AnalizadorLexico(){
        this.ListaTokens = new ArrayList<>();
        this.ListaErrores = new ArrayList<>();
    }
    
    public void nuevoToken(String caracter, String token, int posX, int posY){
        this.ListaTokens.add(new Tokens(caracter, token, posX, posY));
        this.buffer = "";
    }
    
    
    public void nuevoError(String caracter, int posX, int posY){
        this.ListaErrores.add(new ErrorLexico(caracter, "Caracter "+caracter+" no reconocido en el lenguaje", posX, posY));
        this.buffer = "";
    }
    
    public void analizarArchivo(StringBuilder cadena){
        this.ListaTokens.clear();
        this.ListaErrores.clear();
        this.estado = 0;
        this.iArchivo = 0;
        this.buffer = "";
        
        
         while(this.iArchivo < cadena.length()){
            if(this.estado == 0){
                q0(cadena.charAt(this.iArchivo));
            } 
            else if(this.estado == 4){
                q4(cadena.charAt(this.iArchivo));
            }
            else if(this.estado == 1){
                q1(cadena.charAt(this.iArchivo));
            }
            else if(this.estado == 3){
                q3(cadena.charAt(this.iArchivo));
            }
            iArchivo++;
        }
        
    }
    //Este estado es el iniciar, que a su vez es capaz de reconocer simbolos
    public void q0(char caracter){
        if (caracter == '{') {
        this.nuevoToken(String.valueOf(caracter), "LlaveAbrir", this.posX, this.posY);
        this.posY++;
    } else if (caracter == '}') {
        this.nuevoToken(String.valueOf(caracter), "LlaveCerrar", this.posX, this.posY);
        this.posY++;
    } else if (caracter == '[') {
        this.nuevoToken(String.valueOf(caracter), "CorcheteAbrir", this.posX, this.posY);
        this.posY++;
    } else if (caracter == ']') {
        this.nuevoToken(String.valueOf(caracter), "CorcheteCerrar", this.posX, this.posY);
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
    } else if (caracter == '=') {
        this.nuevoToken(String.valueOf(caracter), "SignoIgual", this.posX, this.posY);
        this.posY++;
    } else if (caracter == '\n') {
        this.posX++;
        this.posY = 0;
    } else if (caracter == ' ') {
        this.posY++;
    } else if (caracter == '\t') {
        this.posY += 4;
    } else if (caracter == '-') {
        this.buffer += caracter;
        this.posY++;
        this.estado = 4;
    } else if (Character.isLetter(caracter)) {
        this.buffer += caracter;
        this.posY++;
        this.estado = 1;
    } else if (caracter == '"') {
        this.buffer += caracter;
        this.posY++;
        this.estado = 3;
        
    } else {
        this.buffer += caracter;
        String mensajeError = "Carácter no válido: '" + caracter + "'";
        this.nuevoError(String.valueOf(caracter), this.posX, this.posY);
        this.posY++;
        this.buffer = "";
        System.out.println(mensajeError);
    }
}
    
    //Este estado reconoce flechas, el "-" se define desde el estado inicial
    public void q4(char caracter){
        switch (caracter) {
            case '>' -> {
                this.buffer+=caracter;
                this.nuevoToken(buffer, "Flecha", this.posX, this.posY);
                this.posY++;
                this.estado = 0;
            }
            
            default -> {
                this.buffer+= caracter;
                String mensajeError = "Carácter no válido: '" + caracter + "'";
                this.nuevoError(String.valueOf(caracter), this.posX, this.posY);
                this.posY++;
                this.buffer = "";
                this.estado = 0;
                System.out.println(mensajeError);
            }
        }
    }
    //Este estado reconoce palabras reservadas e identificadores
    public void q1(char caracter){
        if( Character.isDigit(caracter) ||Character.isLetter(caracter) ){
            this.buffer+= caracter;
            this.posY+=1;
        }else{
            
            if(this.buffer.equals("descripcion") || this.buffer.equals("estados") || this.buffer.equals("alfabeto") || this.buffer.equals("inicial") ||this.buffer.equals("finales") || this.buffer.equals("transiciones")){
               this.nuevoToken(this.buffer, "Palabra Reservada", posX, posY); 
               this.estado = 0;
            this.posY+=1;
            this.iArchivo -= 1;
            }else{
                this.nuevoToken(this.buffer, "Identificador", posX, posY);
            this.estado = 0;
            this.posY+=1;
            this.iArchivo -= 1;
            }
            
            
    }
    }
    //Este estado reconoce todas las cadenas de texto del lenguaje
        public void q3(char caracter){
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
        for(Tokens token: this.ListaTokens){
            System.out.println(token);
        }
    }
    
    public void imprimirErrores(){
        for(ErrorLexico error: this.ListaErrores){
            System.out.println(error);
        }
    }
    
    public List<Tokens> getTokens(){
        return this.ListaTokens;
    }
    
}
