/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author aceba
 */  
public class Tokens {
    
    private String lexema;
    private String tipoToken;
    private int posX;
    private int posY;
    
    public Tokens(String lexema, String tipoToken, int posX, int posY){
        this.lexema = lexema;
        this.tipoToken = tipoToken;
        this.posX = posX;
        this.posY = posY;
    }
    
   @Override
    public String toString(){
        return("Token: '"+this.tipoToken+"'" +" Lexema: '"+this.lexema+"'" +" Linea: '"+posX+"'" +" Columna: '" +posY+"'");
    }
    
}
