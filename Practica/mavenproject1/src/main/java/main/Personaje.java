/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


/**
 *
 * @author aceba
 */
public class Personaje {
    String nombre;
    int salud;
    int ataque;
    int defensa;
    
    public Personaje (String nombre, int salud, int ataque, int defensa){
        this.nombre=nombre;
        this.salud=salud;
        this.ataque=ataque;
        this.defensa=defensa;
    }
    
    @Override
    public String toString(){
        return nombre + "(Salud: "+salud+" Ataque: "+ataque+" Defensa: "+defensa+")";
    }
        
     /*Recomendación:
    Crear un método que devuelva una línea html con los datos del personaje para los reportes
     */

}
