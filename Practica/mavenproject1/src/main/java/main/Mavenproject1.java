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
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.*;
public class Mavenproject1 {

   ArrayList<Personaje> personajeLista = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        Mavenproject1 lfpaTarea2_202300673 = new Mavenproject1();
        do{
            System.out.println("Menu principal");
            System.out.println("1. Lectura de archivo");
            System.out.println("2. Escritura de archivo");
            System.out.println("3. Salir");
            option = scanner.nextInt();
                      switch (option){
                case 1:
                    lfpaTarea2_202300673.lecturaArchivo();
                    break;
                case 2:
                    lfpaTarea2_202300673.escrituraArchivo();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema....");
                    break;
                default:
                    System.out.println("Opción no encontrada");
                    break;
            }
            
        }while(option!=3);
    }
        public void lecturaArchivo(){
        JFileChooser fileChooser = new JFileChooser();
        
        int selection = fileChooser.showOpenDialog(null);

        if(selection == JFileChooser.APPROVE_OPTION){
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String ruta = archivoSeleccionado.getAbsolutePath();
            
            try{
                File archivo = new File(ruta);
                
                if(archivo.exists()){
                    BufferedReader br = new BufferedReader(new FileReader(archivo));
                    String linea;
                    br.readLine();

                    while((linea = br.readLine())!=null){
                        System.out.println(linea);
                         String[] datos = linea.split("\\|");
                        
                        //Encontrando los datos
                        String nombre = datos[0].trim();
                        int salud = Integer.parseInt(datos[1].trim());
                        int ataque = Integer.parseInt(datos[2].trim());
                        int defensa = Integer.parseInt(datos[3].trim());
                        
                        Personaje personaje = new Personaje(nombre, salud, ataque, defensa);
                        personajeLista.add(personaje);
                        
                    }
                }else{
                    System.out.println("Error: el archivo no existe :(");
                }
            }catch (IOException e){
                System.out.println("Error al leer el archivo "+e.getMessage());
            }
            
        }else{
            System.out.println("No se seleccionó ningún archivo");
        }
    }
    
    public void escrituraArchivo() {
        String contenido = "<html>";
        contenido+="<table border=\"solid\">";
        contenido+="<tr>";
        contenido+="<th>Nombre</th><th>Salud</th><th>Ataque</th><th>Defensa</th>";
        contenido+="</tr>";
        
        
        for(Personaje personajeIteracion : personajeLista){
            contenido+="<tr>";
            contenido+="<td>"+personajeIteracion.nombre+"</td>";
            contenido+="<td>"+personajeIteracion.salud+"</td>";
            contenido+="<td>"+personajeIteracion.ataque+"</td>";
            contenido+="<td>"+personajeIteracion.defensa+"</td>";
            contenido+="</tr>";
        }
        contenido+="</table>";
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("C:\\Users\\aceba\\OneDrive\\Desktop\\Tareas\\Tareas 5\\Lfp\\Lab\\reporte.html"))) {
            escritor.write(contenido);
            System.out.println("El archivo se ha escrito correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

        contenido.concat("<html>");
    }
}
    
