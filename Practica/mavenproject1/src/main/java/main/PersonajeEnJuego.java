/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author aceba
 */
public class PersonajeEnJuego extends Personaje{

    int vidaInicial;

    
    public PersonajeEnJuego (String nombre, int salud, int ataque, int defensa){
        super(nombre, salud, ataque, defensa);
        this.vidaInicial = salud * 10;

    }
    
    @Override
    public String toString(){
        return nombre + "(Salud: "+salud+" Ataque: "+ataque+" Defensa: "+defensa+" Vida Inicial: "+vidaInicial+")";
    }
      
}
