/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2lfp;

/**
 *
 * @author aceba
 */
public class ErrorSintactico {
     private String descripcion;
    private String caracter;
    private int posX;
    private int posY;
    private String tipoError;
    
    public ErrorSintactico(String caracter, String descripcion, int posX, int posY, String tipoError){
        this.caracter = caracter;
        this.descripcion= descripcion;
        this.posX = posX;
        this.posY = posY;
        this.tipoError = tipoError;
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
    
    public String getTipoError() {
        return tipoError;
    }
}
