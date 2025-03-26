/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author aceba
 */
public class ErrorLexico {
    private String descripcion;
    private String caracter;
    private int posX;
    private int posY;
    
    public ErrorLexico(String caracter, String descripcion, int posX, int posY){
        this.caracter = caracter;
        this.descripcion= descripcion;
        this.posX = posX;
        this.posY = posY;
    }
    
    @Override
    public String toString(){
        return( this.descripcion+" Linea: "+posX+" Columna: "+posY);
    }
    
    
    public String getCaracter() {
        return caracter;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }
}
