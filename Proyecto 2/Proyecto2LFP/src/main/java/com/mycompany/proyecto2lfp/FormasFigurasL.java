/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2lfp;

/**
 *
 * @author aceba
 */
public class FormasFigurasL {

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the figura
     */
    public String getFigura() {
        return figura;
    }

    /**
     * @param figura the figura to set
     */
    public void setFigura(String figura) {
        this.figura = figura;
    }
    private String figura;
    private String color;
    
    public FormasFigurasL(String figura, String color){
        this.figura = figura;
        this.color = color;
    }
}
