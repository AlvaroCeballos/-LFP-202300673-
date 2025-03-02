/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

/**
 *
 * @author aceba
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.*;
import main.Personaje;


public class Mavenproject1 {

    List<PersonajeEnJuego> personajes = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        Mavenproject1 lfpaTarea2_202300673 = new Mavenproject1();
        do{
            System.out.println("Menu principal");
            System.out.println("1. Cargar archivo");
            System.out.println("2. Jugar");
            System.out.println("3. Generar reporte de mayor ataque");
            System.out.println("4. Generar reporte con mayor defensa");
            System.out.println("5. Mostrar informacion del desarrollador");
            System.out.println("6. Salir");
            option = scanner.nextInt();
                      switch (option){
                case 1:
                    lfpaTarea2_202300673.lecturaArchivo();
                    break;
                case 2:
                     lfpaTarea2_202300673.startBattle();
                    break;
                case 3:
                    lfpaTarea2_202300673.reporteAtaque();
                    break;
                case 4:
                    lfpaTarea2_202300673.reporteDefensa();
                    break;
                case 5:
                    System.out.println("Nombre: Alvaro Gabriel Ceballos Gil");
                    System.out.println("Carnet: 202300673");
                    System.out.println("Lenguajes Formales de Programacion, seccion A-");
                    break;    
                default:
                    System.out.println("Has cerrado el programa");
                    break;
            }
            
        }while(option!=6);
    }
        public void lecturaArchivo(){
        JFileChooser fileChooser = new JFileChooser();
        
        int selection = fileChooser.showOpenDialog(null);

        if(selection == JFileChooser.APPROVE_OPTION){
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String ruta = archivoSeleccionado.getAbsolutePath();
            
            personajes = leerPersonajes(ruta);
            System.out.println("Lista de personajes particiapntes: ");
            for(PersonajeEnJuego personajeIteracion : personajes){
                System.out.println(personajeIteracion);
                
            }
            
           
          
            
        }else{
            System.out.println("No se seleccionó ningún archivo");
        }
    }
        
        
    public List<PersonajeEnJuego> leerPersonajes(String ruta){
        List<PersonajeEnJuego> personajeLista2 = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))){
        
            String linea;
            br.readLine();//Leyendo los encabezados
            
            while((linea = br.readLine())!=null){
                String[] datos = linea.split("\\|");
                        
                        //Encontrando los datos
                        String nombre = datos[0].trim();
                        int salud = Integer.parseInt(datos[1].trim());
                        int ataque = Integer.parseInt(datos[2].trim());
                        int defensa = Integer.parseInt(datos[3].trim());

                        
   
                        
                        PersonajeEnJuego personajeJuego = new PersonajeEnJuego(nombre, salud, ataque, defensa);
                        personajeLista2.add(personajeJuego);
            }
           
        }catch(IOException e){
            System.out.println("Error al leer el archivo: "+e.getMessage());
        }
        
        return personajeLista2;
    }
    
    
 
    
 public void reporteAtaque() {
        
        List<PersonajeEnJuego> copiaPersonajes = new ArrayList<>(personajes);
        int n = copiaPersonajes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (copiaPersonajes.get(j).ataque < copiaPersonajes.get(j + 1).ataque) {
                    Collections.swap(copiaPersonajes, j, j + 1);
                }
            }
        }
        
        String contenido = "<html>";
        contenido+="<table border=\"solid\">";
        contenido+="<tr>";
        contenido+=("<h2>Top 5 Personajes con Mayor Ataque</h2>");
        contenido+="<th>Numero</th><th>Nombre</th><th>Ataque</th>";
        contenido+="</tr>";
        
       int i=1;
       for(PersonajeEnJuego personajeIteracion : copiaPersonajes){
           if (i>5) break;
            contenido+="<tr>";
            contenido+="<td>"+i+"</td>";
            contenido+="<td>"+personajeIteracion.nombre+"</td>";
            contenido+="<td>"+personajeIteracion.ataque+"</td>";
            contenido+="</tr>";
         i++;
         
     }
        
        contenido+="</table>";
        
        
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Practica\\mavenproject1\\reporteAtaque.html"))) {
            escritor.write(contenido);
            System.out.println("El archivo de ataque se ha escrito correctamente");
        } catch (IOException e) {
            System.out.println("Ha ocirrdo del siguiente error: " + e.getMessage());
        }
        contenido.concat("<html>");
    }
    
   
 public void reporteDefensa() {
        
        List<PersonajeEnJuego> copiaPersonajes = new ArrayList<>(personajes);
        int n = copiaPersonajes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (copiaPersonajes.get(j).defensa < copiaPersonajes.get(j + 1).defensa) {
                    Collections.swap(copiaPersonajes, j, j + 1);
                }
            }
        }
        
        String contenido = "<html>";
        contenido+="<table border=\"solid\">";
        contenido+="<tr>";
        contenido+=("<h2>Top 5 Personajes con Mayor Defensa</h2>");
        contenido+="<th>Numero</th><th>Nombre</th><th>Defensa</th>";
        contenido+="</tr>";
        
        int i=1;
        for(PersonajeEnJuego personajeIteracion : copiaPersonajes){
            if (i>5) break;
            contenido+="<tr>";
            contenido+="<td>"+i+"</td>";
            contenido+="<td>"+personajeIteracion.nombre+"</td>";
            contenido+="<td>"+personajeIteracion.defensa+"</td>";
            contenido+="</tr>";
            i++;
        }
        contenido+="</table>";
        
        
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Practica\\mavenproject1\\reporteDefensa.html"))) {
            escritor.write(contenido);
            System.out.println("El archivo de defensa se ha escrito correctamente");
        } catch (IOException e) {
            System.out.println("Ha ocirrdo del siguiente error: " + e.getMessage());
        }
        contenido.concat("<html>");
    }
 
 
 public void startBattle(){
     System.out.println("EL COMBATE HA COMENZADO");
     List<PersonajeEnJuego> listaConcursante = new ArrayList<>(personajes);

     while (listaConcursante.size() > 1){
             List<PersonajeEnJuego> listaSobrevivientes = new ArrayList<>();
         System.out.println("NUEVA RONDA HA INICIADO");
         for(int i=0; i< listaConcursante.size(); i+=2){
             if( i+1 < listaConcursante.size()){
                 
                PersonajeEnJuego player1  = listaConcursante.get(i);
                PersonajeEnJuego player2  = listaConcursante.get(i+1);
                
                 System.out.println("Ha comenzado la batalla de "+player1.nombre+" y " +player2.nombre);
                 
                 while (player1.vidaInicial > 0 && player2.vidaInicial > 0) {
                 int danioj1 = Math.max(0, player1.ataque - player2.defensa);
                 int danioj2 = Math.max(0, player2.ataque - player1.defensa);
                 
                 System.out.println("Vida actual: "+player2.vidaInicial+", de "+ player2.nombre);
                 player2.vidaInicial -= danioj1;
                 System.out.println("Vida actual: "+player1.vidaInicial+", de "+ player1.nombre);
                 player1.vidaInicial -= danioj2;
                 
                 System.out.println("El concursante "+player1.nombre + " hizo " + danioj1 + " de daño a " + player2.nombre);
                    System.out.println("El concursante "+player2.nombre + " hizo " + danioj2 + " de daño a " + player1.nombre);
                 
                 }
                 
                 
                 if (player1.vidaInicial > 0) {
                        listaSobrevivientes.add(player1);
                        System.out.println(player1.nombre + " HA GANADO LA RONODA -----------------------------");
                    } else {
                        listaSobrevivientes.add(player2);
                        System.out.println(player2.nombre+ " HA GANADO LA RONODA -----------------------------");
                    }
           
                 } else {
                    listaSobrevivientes.add(listaConcursante.get(i));
                }
            }
            listaConcursante = listaSobrevivientes;
        }
        System.out.println("\nEL CAMPEON DEL TONREO ES: " + listaConcursante.get(0).nombre+" APLAUSOS--------------------------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
    
