/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author aceba
 */
public class AnalizadorLexico {
    private List<Tokens> ListaTokens;
    private List<ErrorLexico> ListaErrores;
    private int posX;
    private int posY;
    private String buffer;
    private int estado;
    private int iArchivo;
    
    
    public AnalizadorLexico(){
        this.ListaTokens = new ArrayList<>();
        this.ListaErrores = new ArrayList<>();
    }
    
    public void nuevoToken(String caracter, String token, int posX, int posY){
        this.ListaTokens.add(new Tokens(caracter, token, posX, posY));
        this.buffer = "";
    }
    
    
    public void nuevoError(String caracter, int posX, int posY){
        this.ListaErrores.add(new ErrorLexico(caracter, "Caracter "+caracter+" no reconocido en el lenguaje", posX, posY));
        this.buffer = "";
    }
    
    public void analizarArchivo(StringBuilder cadena){
        this.ListaTokens.clear();
        this.ListaErrores.clear();
        this.estado = 0;
        this.iArchivo = 0;
        this.buffer = "";
        
        
         while(this.iArchivo < cadena.length()){
            if(this.estado == 0){
                q0(cadena.charAt(this.iArchivo));
            } 
            else if(this.estado == 4){
                q4(cadena.charAt(this.iArchivo));
            }
            else if(this.estado == 1){
                q1(cadena.charAt(this.iArchivo));
            }
            else if(this.estado == 3){
                q3(cadena.charAt(this.iArchivo));
            }
            iArchivo++;
        }
        
    }
    //Este estado es el iniciar, que a su vez es capaz de reconocer simbolos
    public void q0(char caracter){
        if (caracter == '{') {
        this.nuevoToken(String.valueOf(caracter), "LlaveAbrir", this.posX, this.posY);
        this.posY++;
    } else if (caracter == '}') {
        this.nuevoToken(String.valueOf(caracter), "LlaveCerrar", this.posX, this.posY);
        this.posY++;
    } else if (caracter == '[') {
        this.nuevoToken(String.valueOf(caracter), "CorcheteAbrir", this.posX, this.posY);
        this.posY++;
    } else if (caracter == ']') {
        this.nuevoToken(String.valueOf(caracter), "CorcheteCerrar", this.posX, this.posY);
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
    } else if (caracter == '=') {
        this.nuevoToken(String.valueOf(caracter), "SignoIgual", this.posX, this.posY);
        this.posY++;
    } else if (caracter == '\n') {
        this.posX++;
        this.posY = 0;
    } else if (caracter == ' ') {
        this.posY++;
    } else if (caracter == '\t') {
        this.posY += 4;
    } else if (caracter == '-') {
        this.buffer += caracter;
        this.posY++;
        this.estado = 4;
    } else if (Character.isLetter(caracter)) {
        this.buffer += caracter;
        this.posY++;
        this.estado = 1;
    } else if (caracter == '"') {
        this.buffer += caracter;
        this.posY++;
        this.estado = 3;
        
    } else {
        this.buffer += caracter;
        String mensajeError = "Carácter no válido: '" + caracter + "'";
        this.nuevoError(String.valueOf(caracter), this.posX, this.posY);
        this.posY++;
        this.buffer = "";
        System.out.println(mensajeError);
    }
}
    
    //Este estado reconoce flechas, el "-" se define desde el estado inicial
    public void q4(char caracter){
        switch (caracter) {
            case '>' -> {
                this.buffer+=caracter;
                this.nuevoToken(buffer, "Flecha", this.posX, this.posY);
                this.posY++;
                this.estado = 0;
            }
            
            default -> {
                this.buffer+= caracter;
                String mensajeError = "Carácter no válido: '" + caracter + "'";
                this.nuevoError(String.valueOf(caracter), this.posX, this.posY);
                this.posY++;
                this.buffer = "";
                this.estado = 0;
                System.out.println(mensajeError);
            }
        }
    }
    //Este estado reconoce palabras reservadas e identificadores
    public void q1(char caracter){
        if( Character.isDigit(caracter) ||Character.isLetter(caracter) ){
            this.buffer+= caracter;
            this.posY+=1;
        }else{
            
            if(this.buffer.equals("descripcion") || this.buffer.equals("estados") || this.buffer.equals("alfabeto") || this.buffer.equals("inicial") ||this.buffer.equals("finales") || this.buffer.equals("transiciones")){
               this.nuevoToken(this.buffer, "Palabra Reservada", posX, posY); 
               this.estado = 0;
            this.posY+=1;
            this.iArchivo -= 1;
            }else{
                this.nuevoToken(this.buffer, "Identificador", posX, posY);
            this.estado = 0;
            this.posY+=1;
            this.iArchivo -= 1;
            }
            
            
    }
    }
    //Este estado reconoce todas las cadenas de texto del lenguaje
        public void q3(char caracter){
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
        for(Tokens token: this.ListaTokens){
            System.out.println(token);
        }
    }
    
    public void imprimirErrores(){
        for(ErrorLexico error: this.ListaErrores){
            System.out.println(error);
        }
    }
    
    public List<Tokens> getTokens(){
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
        for (Tokens token : this.ListaTokens) {
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
        escritorError.write("<tr><th>Carácter</th><th>Línea</th><th>Columna</th><th>Descripción</th></tr>\n");
        for (ErrorLexico error : this.ListaErrores) {
            escritorError.write("<tr>");
            escritorError.write("<td class='error-char'>" + descripHTML(error.getCaracter()) + "</td>");
            escritorError.write("<td>" + (error.getPosX() + 1) + "</td>"); 
            escritorError.write("<td>" + (error.getPosY() + 1) + "</td>"); 
            escritorError.write("<td>" + descripHTML(error.getDescripcion()) + "</td>");
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
