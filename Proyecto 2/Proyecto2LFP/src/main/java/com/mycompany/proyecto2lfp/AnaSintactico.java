/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2lfp;

import java.io.FileWriter;
import java.io.IOException;
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
    private List<ErrorSintactico> ListaErrores;
    HashMap<String, MundoCls> HMmundo;
    private int posX;
    private int posY;
    
    String nombreMundoA;
    
 public AnaSintactico(List<Token> ListaTokens){
        this.ListaTokens = ListaTokens;
        this.ListaErrores = new ArrayList<>();
        this.HMmundo = new HashMap<>();
        this.nombreMundoA = "";
            this.posX = 0;
    this.posY = 0;
    }
 
 public void nuevoError(String caracter, int posX, int posY, String tipoError){
        this.ListaErrores.add(new ErrorSintactico(caracter, "Caracter "+caracter+" no reconocido en el lenguaje", posX, posY, tipoError));

    }
 
 public void agregarError(Token token){
    this.nuevoError("No se esperaba el token "+token.getLexema(), token.getPosY(), token.getPosX(), "Error de tipo sintactico");   
    
   }
 
 public void agregarErrorEspecifico(Token token, String mensaje) {
    this.ListaErrores.add(new ErrorSintactico(
        token.getLexema(),
        mensaje,
        token.getPosX(),
        token.getPosY(),
        "Error Sintáctico"
    ));
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
            Token tokenTemporal = this.ListaTokens.get(0);
            if(tokenTemporal.getTipoToken().equals("Coma")){
                this.ListaTokens.removeFirst();
                this.MUNDOU();
                this.MUNDOSP();


            }else{
              
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
  
                        agregarErrorEspecifico(tokenTemporal, "Se esperaba llave para cerrar '}', no " + tokenTemporal.getLexema());
                    }
                }else{

                    agregarErrorEspecifico(tokenTemporal, "Se esperaba llave de apertura '{', no " + tokenTemporal.getLexema());
                }
            }else{
                agregarErrorEspecifico(tokenTemporal, "Se esperaba cadena de texto, no " + tokenTemporal.getLexema());
            }
        }else{
        agregarErrorEspecifico(tokenTemporal, "Se esperaba palabra world, no " + tokenTemporal.getLexema());
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
                                                  
                                                   agregarErrorEspecifico(tokenTemporal, "Se esperaba un parentesis de cierre, no " + tokenTemporal.getLexema());
                                            }
                                        } else {
                 
                                            agregarErrorEspecifico(tokenTemporal, "Se esperaba un numero, no " + tokenTemporal.getLexema());
                                        }

                                    } else {
                                  
                                       agregarErrorEspecifico(tokenTemporal, "\"Se esperaba una coma, no " + tokenTemporal.getLexema());
                                    }
                                } else {
                                    
                                    agregarErrorEspecifico(tokenTemporal, "Se esperaba un numero, no " + tokenTemporal.getLexema());
                                }
                            } else {
                               
                                agregarErrorEspecifico(tokenTemporal, "Se esperaba un parentesis de apertura, no " + tokenTemporal.getLexema());
                            }

                        } else {
                           
                            agregarErrorEspecifico(tokenTemporal, "Se esperaba la palabra at, no " + tokenTemporal.getLexema());
                        }
                    } else {
                      
                        agregarErrorEspecifico(tokenTemporal, "Se esperaba uno de los posibles lugares, no " + tokenTemporal.getLexema());
                    }

                } else {
                
                    agregarErrorEspecifico(tokenTemporal, "Se esperaban dos puntos, no " + tokenTemporal.getLexema());
                }
            } else {
             
                agregarErrorEspecifico(tokenTemporal, "Se esperaba un identificador, no " + tokenTemporal.getLexema());
            }
        } else {
          
            agregarErrorEspecifico(tokenTemporal, "Se esperaba palabra place, no " + tokenTemporal.getLexema());
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
                              
                                agregarErrorEspecifico(tokenTemporal, "Se esperaba una cadena de texto, no " + tokenTemporal.getLexema());
                            }

                        } else {
                            
                            agregarErrorEspecifico(tokenTemporal, "Se esperaba palabra with, no " + tokenTemporal.getLexema());
                        }

                    } else {
                      
                        agregarErrorEspecifico(tokenTemporal, "Se esperaba un identificador, no " + tokenTemporal.getLexema());
                    }

                } else {
                 
                    agregarErrorEspecifico(tokenTemporal, "Se esperaba palabra to, no " + tokenTemporal.getLexema());
                }

            } else {
       
                agregarErrorEspecifico(tokenTemporal, "Se esperaba un identificador, no " + tokenTemporal.getLexema());
            }

        } else {
       
            agregarErrorEspecifico(tokenTemporal, "Se esperaba palabra connect, no " + tokenTemporal.getLexema());
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
        Token tokenTemporali = this.ListaTokens.get(0);
        
         if (tokenTemporali.getTipoToken().equals("Identificador")) {

        Token tokenTemporal = this.ListaTokens.removeFirst();

             System.out.println("IDENTIFICADOR");
        return;
    } else if (tokenTemporali.getTipoToken().equals("ParentesisAbrir")) {

        Token tokenTemporal = this.ListaTokens.removeFirst(); 
        
        tokenTemporal = this.ListaTokens.removeFirst(); 
        if (tokenTemporal.getTipoToken().equals("Numero")) {
            tokenTemporal = this.ListaTokens.removeFirst(); 
            
            if (tokenTemporal.getTipoToken().equals("Coma")) {
                tokenTemporal = this.ListaTokens.removeFirst(); 
                
                if (tokenTemporal.getTipoToken().equals("Numero")) {
                    tokenTemporal = this.ListaTokens.removeFirst(); 
                    
                    if (tokenTemporal.getTipoToken().equals("ParentesisCerrar")) {
                        System.out.println("POSNUM");
                        return;
                    } else {
                 
                        agregarErrorEspecifico(tokenTemporal, "Se esperaba un parentesis de cierre, no " + tokenTemporal.getLexema());
                    }
                } else {
                  
                    agregarErrorEspecifico(tokenTemporal, "Se esperaba un numero, no " + tokenTemporal.getLexema());
                }
            } else {
                agregarErrorEspecifico(tokenTemporal, "Se esperaba una coma, no " + tokenTemporal.getLexema());
            }
        } else {
            agregarErrorEspecifico(tokenTemporal, "Se esperaba un numero, no " + tokenTemporal.getLexema());
        }
    } else {
        agregarErrorEspecifico(tokenTemporali, "Se esperaba parentesis de apertura, no  " + tokenTemporali.getLexema());
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
                      
                        agregarErrorEspecifico(tokenTemporal, "Se esperaba palabra at, no " + tokenTemporal.getLexema());
                    }
                } else {
          
                    agregarErrorEspecifico(tokenTemporal, "Se esperaba uno de los posibles objetos, no " + tokenTemporal.getLexema());
                }
            } else {
               
                agregarErrorEspecifico(tokenTemporal, "Se esperaban dos puntos, no " + tokenTemporal.getLexema());
            }
        } else {
           
            agregarErrorEspecifico(tokenTemporal, "Se esperaba una cadena de texto, no " + tokenTemporal.getLexema());
        }
    } else {
       agregarErrorEspecifico(tokenTemporal, "Se esperaba palabra object, no " + tokenTemporal.getLexema());
    }
    }
    
    
    public Set<String> getNombresMundos() {
    return this.HMmundo.keySet();
}
    
    public List<ErrorSintactico> getErrores() {
    return this.ListaErrores;
}
    
    private Token getTokenActual() {
    if (!this.ListaTokens.isEmpty()) {
        return this.ListaTokens.getFirst();
    }
    // Token ficticio para manejar el final del archivo
    return new Token("EOF", "FinDeArchivo", this.posX, this.posY);
}

// Método simplificado para agregar errores
public void agregarErrorSintactico(String mensajeError) {
    Token tokenActual = getTokenActual();
    this.ListaErrores.add(new ErrorSintactico(
        tokenActual.getLexema(),
        mensajeError,
        tokenActual.getPosX(),
        tokenActual.getPosY(),
        "Error Sintáctico"
    ));
}

        public void generarReporteErroresHTML(String rutaArchivo) {
    try {
        FileWriter escritorError = new FileWriter(rutaArchivo);
        
        escritorError.write("<!DOCTYPE html>\n");
        escritorError.write("<html>\n");
        escritorError.write("<head>\n");
        escritorError.write("<title>Reporte de Tokens</title>\n");
        escritorError.write("<style>\n");
        escritorError.write("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #1e1e2e; color: #ffffff; margin: 20px; text-align: center; }\n");
        escritorError.write("h1 { color: #00ff7f; text-align: center; text-shadow: 2px 2px 5px rgba(0, 255, 127, 0.5); }\n");
        escritorError.write("table { width: 80%; margin: 20px auto; border-collapse: collapse; background-color: #2a2a3a; border-radius: 10px; overflow: hidden; box-shadow: 0 0 10px rgba(0, 255, 127, 0.5); }\n");
        escritorError.write("th, td { padding: 12px; text-align: center; border: 2px solid #00ff7f; transition: all 0.3s ease-in-out; }\n");
        escritorError.write("th { background-color: #00ff7f; color: #1e1e2e; font-weight: bold; text-transform: uppercase; }\n");
        escritorError.write("td { background-color: #2a2a3a; color: #ffffff; }\n");
        escritorError.write("tr:hover { background-color: #3a3a4a; transform: scale(1.02); }\n");
        escritorError.write("tr:nth-child(even) { background-color: #2f2f3f; }\n");
        escritorError.write("</style>\n");
        escritorError.write("</head>\n");
        escritorError.write("<body>\n");
        escritorError.write("<h1>Reporte de Tokens</h1>\n");
        escritorError.write("<table>\n");
        escritorError.write("<h1>Reporte de Errores Léxicos</h1>\n");
        escritorError.write("<p>Total de errores encontrados: " + this.ListaErrores.size() + "</p>\n");
        escritorError.write("<table>\n");
        escritorError.write("<tr><th>Carácter</th><th>Línea</th><th>Columna</th><th>Descripción</th><th>Tipo de error</th></tr>\n");
        for (ErrorSintactico error : this.ListaErrores) {
            escritorError.write("<tr>");
            escritorError.write("<td class='error-char'>" + descripHTML(error.getCaracter()) + "</td>");
            escritorError.write("<td>" + (error.getPosX() + 1) + "</td>"); 
            escritorError.write("<td>" + (error.getPosY() + 1) + "</td>"); 
            escritorError.write("<td>" + descripHTML(error.getDescripcion()) + "</td>");
            escritorError.write("<td>" + descripHTML(error.getTipoError()) + "</td>");
            
            escritorError.write("</tr>\n");
        }
        escritorError.write("</table>\n");
        escritorError.write("</body>\n");
        escritorError.write("</html>");
        escritorError.close();
        System.out.println("Reporte de errores HTML generado exitosamente en: " + rutaArchivo);
    } catch (IOException e) {
        System.err.println("Error al generar el reporte de errores HTML: " + e.getMessage());
    }
}
    
    private String descripHTML(String input) {
    if (input == null) {
        return "";
    }
    return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
}
}
