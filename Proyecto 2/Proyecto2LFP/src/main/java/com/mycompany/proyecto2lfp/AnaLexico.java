/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2lfp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aceba
 */
public class AnaLexico {
    private List<Token> ListaTokens;
    private List<ErrorSL> ListaErrores;
    private int posX;
    private int posY;
    private String buffer;
    private int estado;
    private int iArchivo;
    
    public AnaLexico(){
        this.ListaTokens = new ArrayList<>();
        this.ListaErrores = new ArrayList<>();
    }
    
    public void nuevoToken(String caracter, String token, int posX, int posY){
        this.ListaTokens.add(new Token(caracter, token, posX, posY));
        this.buffer = "";
    }
    
    
    public void nuevoError(String caracter, int posX, int posY, String tipoError){
        this.ListaErrores.add(new ErrorSL(caracter, "Caracter "+caracter+" no reconocido en el lenguaje", posX, posY, tipoError));
        this.buffer = "";
    }
    
     public void analizarArchivo(StringBuilder cadena){
        this.ListaTokens.clear();
        this.ListaErrores.clear();
        this.estado = 0;
        this.buffer = "";
        this.iArchivo = 0;
        this.posX = 0;
        this.posY = 0;
        while(this.iArchivo < cadena.length()){
            if(this.estado == 0){
                q0q4(cadena.charAt(this.iArchivo));
            } 
            else if(this.estado == 3){
                q3(cadena.charAt(this.iArchivo));
            }
           
            else if(this.estado == 2){
                q2(cadena.charAt(this.iArchivo));
            }
            
            else if(this.estado == 1){
                q1(cadena.charAt(this.iArchivo));
            }

            iArchivo++;
            
        }
        if(!buffer.isEmpty()) {
        if(estado == 3) {
            nuevoToken(buffer, "Numero", posX, posY);
        }
    }
        
     }
     public void q0q4(char caracter){
         if (caracter == '{') {
        this.nuevoToken(String.valueOf(caracter), "LlaveAbrir", this.posX, this.posY);
        this.posY++;
    } else if (caracter == '}') {
        this.nuevoToken(String.valueOf(caracter), "LlaveCerrar", this.posX, this.posY);
        this.posY++;
    } else if (caracter == ':') {
        this.nuevoToken(String.valueOf(caracter), "DosPuntos", this.posX, this.posY);
        this.posY++;
    } else if (caracter == ',') {
        this.nuevoToken(String.valueOf(caracter), "Coma", this.posX, this.posY);
        this.posY++;
    } else if (caracter == '(') {
        this.nuevoToken(String.valueOf(caracter), "ParentesisAbrir", this.posX, this.posY);
        this.posY++;
    } else if (caracter == ')') {
        this.nuevoToken(String.valueOf(caracter), "ParentesisCerrar", this.posX, this.posY);
        this.posY++;
     } else if (caracter == '\n') {
        this.posX++;
        this.posY = 0;
    } else if (caracter == ' ') {
        this.posY++;
    } else if (caracter == '\t') {
        this.posY += 4;
    } 
    else if (Character.isDigit(caracter)) {
            this.buffer += caracter;
            this.posY++;
            this.estado = 3;
        }
    else if (Character.isLetter(caracter)) {
        this.buffer += caracter;
        this.posY++;
        this.estado = 2;
    } 
    else if (caracter == '"') {
        this.buffer += caracter;
        this.posY++;
        this.estado = 1;
        
    } 

    else {
        this.buffer += caracter;
        String mensajeError = "Carácter no válido: '" + caracter + "'";
        this.nuevoError(String.valueOf(caracter), this.posX, this.posY, "Error de tipo lexico");
        this.posY++;
        this.buffer = "";
        System.out.println(mensajeError);
    }
         
         
}
     
     public void q3(char caracter){
    if(Character.isDigit(caracter)) {
        this.buffer += caracter;
        this.posY++;
    } else {
        int tokenPosY = this.posY - buffer.length();
        this.nuevoToken(buffer, "Numero", this.posX, tokenPosY);
        this.estado = 0;
        this.iArchivo--;
    }
}
     
public void q2(char caracter) {
    if (Character.isDigit(caracter) || Character.isLetter(caracter)) {
        this.buffer += caracter;
        this.posY += 1;
    } else {
        if (this.buffer.equals("world")) {
            this.nuevoToken(this.buffer, "PRWorld", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
        } else if (this.buffer.equals("place")) {
            this.nuevoToken(this.buffer, "PRPlace", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
        } else if (this.buffer.equals("at")) {
            this.nuevoToken(this.buffer, "PRAt", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
        } else if (this.buffer.equals("playa") || this.buffer.equals("cueva") || 
                  this.buffer.equals("templo") || this.buffer.equals("jungla") || 
                  this.buffer.equals("montana") || this.buffer.equals("pueblo") || 
                  this.buffer.equals("isla") || this.buffer.equals("rio") || 
                  this.buffer.equals("volcan") || this.buffer.equals("pantano")) {
            
            this.nuevoToken(this.buffer, "PRLugar", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
            
        } else if (this.buffer.equals("tesoro") || this.buffer.equals("llave") ||
                  this.buffer.equals("arma") || this.buffer.equals("objetomagico") ||
                  this.buffer.equals("pocion") || this.buffer.equals("trampa") ||
                  this.buffer.equals("libro") || this.buffer.equals("herramienta") ||
                  this.buffer.equals("bandera") || this.buffer.equals("gema")) {
            
            this.nuevoToken(this.buffer, "PRObjeto", posX, posY);
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
        }else if(this.buffer.equals("connect")){
            this.nuevoToken(this.buffer, "PRConnect", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
            
        }else if(this.buffer.equals("to")){
            this.nuevoToken(this.buffer, "PRTo", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
            
        }else if(this.buffer.equals("with")){
            this.nuevoToken(this.buffer, "PRWith", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
            
        }else if(this.buffer.equals("object")){
            this.nuevoToken(this.buffer, "PalabraObjeto", posX, posY); 
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
            
        }else {
            this.nuevoToken(this.buffer, "Identificador", posX, posY);
            this.estado = 0;
            this.posY += 1;
            this.iArchivo -= 1;
        }
    }
}
     
     public void q1(char caracter){
            if(caracter != '"'){
                this.buffer+=caracter;
                this.posY+=1;
                
                
            }else{
                this.buffer+=caracter;
                this.nuevoToken(this.buffer, "Cadena de texto", posX, posY);
                this.posY+=1;
                this.estado=0;
            }
        }
     
     public void imprimirTokens(){
        for(Token token: this.ListaTokens){
            System.out.println(token);
        }
    }
    
    public void imprimirErrores(){
        for(ErrorSL error: this.ListaErrores){
            System.out.println(error);
        }
    }
    
    public List<Token> getTokens(){
        return this.ListaTokens;
    }
    
    public void generarReporteHTML(String rutaArchivo) {
    try {
        FileWriter escritorHTMLToken = new FileWriter(rutaArchivo);
        
        escritorHTMLToken.write("<!DOCTYPE html>\n");
        escritorHTMLToken.write("<html>\n");
        escritorHTMLToken.write("<head>\n");
        escritorHTMLToken.write("<title>Reporte de Tokens</title>\n");
        escritorHTMLToken.write("<style>\n");
        escritorHTMLToken.write("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #1e1e2e; color: #ffffff; margin: 20px; text-align: center; }\n");
        escritorHTMLToken.write("h1 { color: #00ff7f; text-align: center; text-shadow: 2px 2px 5px rgba(0, 255, 127, 0.5); }\n");
        escritorHTMLToken.write("table { width: 80%; margin: 20px auto; border-collapse: collapse; background-color: #2a2a3a; border-radius: 10px; overflow: hidden; box-shadow: 0 0 10px rgba(0, 255, 127, 0.5); }\n");
        escritorHTMLToken.write("th, td { padding: 12px; text-align: center; border: 2px solid #00ff7f; transition: all 0.3s ease-in-out; }\n");
        escritorHTMLToken.write("th { background-color: #00ff7f; color: #1e1e2e; font-weight: bold; text-transform: uppercase; }\n");
        escritorHTMLToken.write("td { background-color: #2a2a3a; color: #ffffff; }\n");
        escritorHTMLToken.write("tr:hover { background-color: #3a3a4a; transform: scale(1.02); }\n");
        escritorHTMLToken.write("tr:nth-child(even) { background-color: #2f2f3f; }\n");
        escritorHTMLToken.write("</style>\n");
        escritorHTMLToken.write("</head>\n");
        escritorHTMLToken.write("<body>\n");
        escritorHTMLToken.write("<h1>Reporte de Tokens</h1>\n");
        escritorHTMLToken.write("<table>\n");
        escritorHTMLToken.write("<tr><th>Token</th><th>Lexema</th><th>Línea</th><th>Columna</th></tr>\n");
        for (Token token : this.ListaTokens) {
            escritorHTMLToken.write("<tr>");
            escritorHTMLToken.write("<td>" + descripHTML(token.getTipoToken()) + "</td>");
            escritorHTMLToken.write("<td>" + descripHTML(token.getLexema()) + "</td>");
            escritorHTMLToken.write("<td>" + (token.getPosX() + 1) + "</td>"); 
            escritorHTMLToken.write("<td>" + (token.getPosY() + 1) + "</td>"); 
            escritorHTMLToken.write("</tr>\n");
        }
        escritorHTMLToken.write("</table>\n");
        escritorHTMLToken.write("</body>\n");
        escritorHTMLToken.write("</html>");
        escritorHTMLToken.close();
        System.out.println("Reporte HTML generado exitosamente en: " + rutaArchivo);
    } catch (IOException e) {
        System.err.println("Error al generar el reporte HTML: " + e.getMessage());
    }
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
        for (ErrorSL error : this.ListaErrores) {
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


