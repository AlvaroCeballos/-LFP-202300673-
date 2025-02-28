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
            System.out.println("7. Salir");
            System.out.println("6. Pruebas xd");
            option = scanner.nextInt();
                      switch (option){
                case 1:
                    lfpaTarea2_202300673.lecturaArchivo();
                    break;
                case 2:
                     lfpaTarea2_202300673.startBattle();
                    break;
                case 3:
                    lfpaTarea2_202300673.lecturaArchivo();
                    break;
                case 4:
                    lfpaTarea2_202300673.lecturaArchivo();
                    break;
                case 5:
                    System.out.println("Nombre: Alvaro Gabriel Ceballos Gil");
                    System.out.println("Carnet: 202300673");
                    System.out.println("Lenguajes Formales de Programacion, seccion A-");
                    break;
                case 6:
                    System.out.println("Saliendo del sistema....");
                    break;     
                case 7:

                    break;
                
                default:
                    System.out.println("Opción no encontrada");
                    break;
            }
            
        }while(option!=7);
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
            
           
            
          // List<PersonajeEnJuego> personajeReportes = new ArrayList<>();
        //for (PersonajeEnJuego iterarPersonajeReporte : personaje) {
            //personajeReportes.add(iterarPersonajeReporte.clone());
        //}
            
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
    
 public void startBattle(){
     System.out.println("INICIA EL COMBATE");
     List<PersonajeEnJuego> listaConcursante = new ArrayList<>(personajes);

     while (listaConcursante.size() > 1){
             List<PersonajeEnJuego> listaSobrevivientes = new ArrayList<>();
         System.out.println("NUEVA RONDA");
         for(int i=0; i< listaConcursante.size(); i+=2){
             if( i+1 < listaConcursante.size()){
                 
                PersonajeEnJuego player1  = listaConcursante.get(i);
                PersonajeEnJuego player2  = listaConcursante.get(i+1);
                
                 System.out.println("Enfrentamiento de "+player1.nombre+" y " +player2.nombre);
                 
                 while (player1.vidaInicial > 0 && player2.vidaInicial > 0) {
                 int danioj1 = Math.max(0, player1.ataque - player2.defensa);
                 int danioj2 = Math.max(0, player2.ataque - player1.defensa);
                 
                 System.out.println(player2.vidaInicial+" de "+ player2.nombre);
                 player2.vidaInicial -= danioj1;
                 System.out.println(player1.vidaInicial+" de "+ player1.nombre);
                 player1.vidaInicial -= danioj2;
                 
                 System.out.println("El concursante "+player1.nombre + " hizo " + danioj1 + " de daño a " + player2.nombre);
                    System.out.println("El concursante "+player2.nombre + " hizo " + danioj2 + " de daño a " + player1.nombre);
                 
                 }
                 
                 
                 if (player1.vidaInicial > 0) {
                        listaSobrevivientes.add(player1);
                        System.out.println("Ganador de la ronda: " + player1.nombre);
                    } else {
                        listaSobrevivientes.add(player2);
                        System.out.println("Ganador de la ronda: " + player2.nombre);
                    }
           
                 } else {
                    listaSobrevivientes.add(listaConcursante.get(i));
                }
            }
            listaConcursante = listaSobrevivientes;
        }
        System.out.println("\nEL GANADOR DEL TORNEO ES: " + listaConcursante.get(0).nombre);
    }
}
    
