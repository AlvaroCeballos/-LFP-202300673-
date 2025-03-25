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

    /**
     * @return the lexema
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * @param lexema the lexema to set
     */
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    /**
     * @return the tipoToken
     */
    public String getTipoToken() {
        return tipoToken;
    }

    /**
     * @param tipoToken the tipoToken to set
     */
    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
    
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
        return("Token: '"+this.getTipoToken()+"'" +" Lexema: '"+this.getLexema()+"'" +" Linea: '"+getPosX()+"'" +" Columna: '" +getPosY()+"'");
    }
    
}
