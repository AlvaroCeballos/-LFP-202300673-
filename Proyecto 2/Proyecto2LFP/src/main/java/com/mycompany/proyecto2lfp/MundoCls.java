/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2lfp;

import java.util.HashMap;

/**
 *
 * @author aceba
 */
public class MundoCls {
    HashMap<String, LugarCls> HMlugares;
    
    
    public MundoCls(){
        HMlugares = new HashMap<>();
}
    
    public void agregarPlace(String identificador, LugarCls lugar){
        this.HMlugares.put(identificador, lugar);
    }
}


