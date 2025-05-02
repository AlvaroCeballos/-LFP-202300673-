/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2lfp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author aceba
 */
public class AnaSintactico {
    private List<Token> ListaTokens;
    private List<ErrorSL> ListaErrores;
    HashMap<String, MundoCls> HMmundo;
    
    String nombreMundoA;
    
 public AnaSintactico(List<Token> ListaTokens){
        this.ListaTokens = ListaTokens;
        this.ListaErrores = new ArrayList<>();
        this.HMmundo = new HashMap<>();
        this.nombreMundoA = "";
    }
 
 public void agregarError(Token token){
     
    // this.ListaErrores.add(new Error(token.getLexema(), "No se esperaba el token "+token.getLexema(), token.getPosY(), token.getPosX(), "Sintáctico", token.getTipoError()));  
   }
 
    public void analizar(){
        this.INICIO();
    }
    
    public void INICIO(){
        System.out.println("INICIO");
        this.MUNDOS();
    }
    
    public void MUNDOS(){
        System.out.println("MUNDOS()");
        this.MUNDOU();
        this.MUNDOSP();
    }
    
    public void MUNDOSP(){
       System.out.println("MUNDOS PRIMA");
       try{
            
            if(this.ListaTokens.get(0).getTipoToken().equals("Coma")){
                this.ListaTokens.removeFirst();
                this.MUNDOU();
                this.MUNDOSP();


            }else{
                //Agregar error
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        
        }
    }
    
    public void MUNDOU(){
       System.out.println("MUNDO UNICO"); 
       Token tokenTemporal = this.ListaTokens.removeFirst();
       if(tokenTemporal.getTipoToken().equals("PRWorld")){
        //Continuamos
            tokenTemporal = this.ListaTokens.removeFirst();
            
            if(tokenTemporal.getTipoToken().equals("Cadena de texto")){
                this.nombreMundoA = tokenTemporal.getLexema();
                this.HMmundo.put(nombreMundoA, new MundoCls());
                tokenTemporal = this.ListaTokens.removeFirst();

                
                if(tokenTemporal.getTipoToken().equals("LlaveAbrir")){
                    this.LPLACES();
                    this.LCONNECTS();
                    this.LOBJECTS();
                    
                    tokenTemporal = this.ListaTokens.removeFirst();
                    
                    if(tokenTemporal.getTipoToken().equals("LlaveCerrar")){
                        //Continuo
                        return;
                    }else{
                        //Agrego error
                    }
                }else{
                    //Agrego error
                }
            }else{
                //Agregar error
            }
        }else{
        //AGregar error
        }
    }
    public void LPLACES(){
        System.out.println("LPLACES()");
        this.LPLACE();
        this.LPLACESP();
    }
    
    public void LPLACESP(){
        System.out.println("LPLACESP");
        if(this.ListaTokens.get(0).getTipoToken().equals("PRPlace")){
            this.LPLACE();
            this.LPLACESP();
        }else{
            return;
        }
    }
    
    public void LPLACE() {
        System.out.println("LPLACE");
        String nombreLugar;
        String tipo;
        String LposX;
        String LposY;

        Token tokenTemporal = this.ListaTokens.removeFirst();

        if (tokenTemporal.getTipoToken().equals("PRPlace")) {

            tokenTemporal = this.ListaTokens.removeFirst();

            if (tokenTemporal.getTipoToken().equals("Identificador")) {
               nombreLugar = tokenTemporal.getLexema();
                tokenTemporal = this.ListaTokens.removeFirst();

                if (tokenTemporal.getTipoToken().equals("DosPuntos")) {

                    tokenTemporal = this.ListaTokens.removeFirst();

                    if (tokenTemporal.getTipoToken().equals("PRLugar")) {
                        tipo = tokenTemporal.getLexema();
                        tokenTemporal = this.ListaTokens.removeFirst();

                        if (tokenTemporal.getTipoToken().equals("PRAt")) {

                            tokenTemporal = this.ListaTokens.removeFirst();

                            if (tokenTemporal.getTipoToken().equals("ParentesisAbrir")) {

                                tokenTemporal = this.ListaTokens.removeFirst();

                                if (tokenTemporal.getTipoToken().equals("Numero")) {
                                     LposX = tokenTemporal.getLexema();
                                    tokenTemporal = this.ListaTokens.removeFirst();

                                    if (tokenTemporal.getTipoToken().equals("Coma")) {

                                        tokenTemporal = this.ListaTokens.removeFirst();

                                        if (tokenTemporal.getTipoToken().equals("Numero")) {
                                            LposY = tokenTemporal.getLexema();
                                            
                                            tokenTemporal = this.ListaTokens.removeFirst();

                                            if (tokenTemporal.getTipoToken().equals("ParentesisCerrar")) {
                                               LugarCls lugar = new LugarCls(nombreLugar, tipo, LposX, LposY);
                                               this.HMmundo.get(nombreMundoA).agregarPlace(nombreLugar, lugar);
                                                return;

                                            } else {

                                            }
                                        } else {

                                        }

                                    } else {

                                    }
                                } else {

                                }
                            } else {

                            }

                        } else {

                        }
                    } else {

                    }

                } else {
                    //Error
                }
            } else {
                //Error
            }
        } else {
            //Error
        }

    }
    
    public void LCONNECTS(){
        System.out.println("LCONNECTS()");
        this.LCONNECT();
        this.LCONNECTSP();
    }
    
    public void LCONNECTSP(){
        System.out.println("LCONNECTSP");
        if(this.ListaTokens.get(0).getTipoToken().equals("PRConnect")){
            this.LCONNECT();
            this.LCONNECTSP();
        }else{
            return;
        }
    }
    
    public void LCONNECT(){
        System.out.println("LCONNECT");
        
        Token tokenTemporal = this.ListaTokens.removeFirst();

        if (tokenTemporal.getTipoToken().equals("PRConnect")) {

            tokenTemporal = this.ListaTokens.removeFirst();

            if (tokenTemporal.getTipoToken().equals("Identificador")) {

                tokenTemporal = this.ListaTokens.removeFirst();

                if (tokenTemporal.getTipoToken().equals("PRTo")) {

                    tokenTemporal = this.ListaTokens.removeFirst();

                    if (tokenTemporal.getTipoToken().equals("Identificador")) {

                        tokenTemporal = this.ListaTokens.removeFirst();

                        if (tokenTemporal.getTipoToken().equals("PRWith")) {

                            tokenTemporal = this.ListaTokens.removeFirst();
                            if (tokenTemporal.getTipoToken().equals("Cadena de texto")) {

                                return;

                            } else {

                            }

                        } else {

                        }

                    } else {

                    }

                } else {

                }

            } else {

            }

        } else {

        }
    }
    
    public void LOBJECTS(){
        System.out.println("LOBJECTS()");
        this.LOBJECT();
        this.LOBJECTSP();
    }
    
    
    public void LOBJECTSP(){
        System.out.println("LOBJECTSP");
         if(this.ListaTokens.get(0).getTipoToken().equals("PalabraObjeto")){
            this.LOBJECT();
            this.LOBJECTSP();
        }else{
            return;
        }
    }
    
    public void OBJECTCASE(){
        
         if (this.ListaTokens.get(0).getTipoToken().equals("Identificador")) {
        // Camino 1: Es un identificador
        Token tokenTemporal = this.ListaTokens.removeFirst();
        // Aquí puedes almacenar el valor del identificador si lo necesitas
             System.out.println("IDENTIFICADOR");
        return;
    } else if (this.ListaTokens.get(0).getTipoToken().equals("ParentesisAbrir")) {
        // Camino 2: Es una coordenada
        Token tokenTemporal = this.ListaTokens.removeFirst(); // Extraer '('
        
        tokenTemporal = this.ListaTokens.removeFirst(); // Extraer número
        if (tokenTemporal.getTipoToken().equals("Numero")) {
            tokenTemporal = this.ListaTokens.removeFirst(); // Extraer ','
            
            if (tokenTemporal.getTipoToken().equals("Coma")) {
                tokenTemporal = this.ListaTokens.removeFirst(); // Extraer segundo número
                
                if (tokenTemporal.getTipoToken().equals("Numero")) {
                    tokenTemporal = this.ListaTokens.removeFirst(); // Extraer ')'
                    
                    if (tokenTemporal.getTipoToken().equals("ParentesisCerrar")) {
                        System.out.println("POSNUM");
                        return;
                    } else {
                        // Error: se esperaba paréntesis de cierre
                    }
                } else {
                    // Error: se esperaba un número
                }
            } else {
                // Error: se esperaba una coma
            }
        } else {
            // Error: se esperaba un número
        }
    } else {
        // Error: token inesperado
    }
    }
    
    
    public void LOBJECT(){
        System.out.println("LOBJECT");
        Token tokenTemporal = this.ListaTokens.removeFirst();
    
    if (tokenTemporal.getTipoToken().equals("PalabraObjeto")) {
        tokenTemporal = this.ListaTokens.removeFirst();
        
        if (tokenTemporal.getTipoToken().equals("Cadena de texto")) {
            tokenTemporal = this.ListaTokens.removeFirst();
            
            if (tokenTemporal.getTipoToken().equals("DosPuntos")) {
                tokenTemporal = this.ListaTokens.removeFirst();
                
                if (tokenTemporal.getTipoToken().equals("PRObjeto")) {
                    tokenTemporal = this.ListaTokens.removeFirst();
                    
                    if (tokenTemporal.getTipoToken().equals("PRAt")) {
                        // Ahora llama a POSCASE para manejar la alternativa
                        this.OBJECTCASE();
                        return;
                    } else {
                        // Error: se esperaba 'at'
                    }
                } else {
                    // Error: se esperaba un tipo de objeto válido
                }
            } else {
                // Error: se esperaban dos puntos
            }
        } else {
            // Error: se esperaba una cadena de texto
        }
    } else {
        // Error: se esperaba la palabra 'object'
    }
    }
    
    
    public Set<String> getNombresMundos() {
    return this.HMmundo.keySet();
}
}
