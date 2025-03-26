/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author aceba
 */
public class Menu extends javax.swing.JFrame {
    
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JTextArea txtResultados;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JScrollPane scrollPane;
     private AnalizadorLexico analizadorLexico;
    private Map<String, AutomataIndividual> automata;

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        initComponents2();
        analizadorLexico = new AnalizadorLexico();
        automata = new HashMap<>();
    }
     private void initComponents2() {
        // Configuración básica del JFrame
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Analizador Léxico de Autómatas");
        
        // Creación de componentes
        btnAnalizar = new javax.swing.JButton();
        fileChooser = new javax.swing.JFileChooser();
        txtResultados = new javax.swing.JTextArea();
        scrollPane = new javax.swing.JScrollPane(txtResultados);
        tabbedPane = new javax.swing.JTabbedPane();
        
        // Configuración de componentes
        btnAnalizar.setText("Analizar Archivo");
        btnAnalizar.addActionListener(this::btnAnalizarActionPerformed);
        
        txtResultados.setEditable(false);
        txtResultados.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        // Organización del layout
        tabbedPane.addTab("Resultados", scrollPane);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAnalizar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAnalizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane)
                .addContainerGap())
        );
        
        pack();
        setSize(800, 600); // Tamaño inicial adecuado
    }
    
    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            analizarArchivo(archivo);
        }
    }
    private void procesarAutomata(List<Tokens> tokensAnalizados) {
    // Limpiar autómatas anteriores
    automata.clear();
    txtResultados.append("\n=== PROCESAMIENTO DE AUTÓMATAS ===\n");
    
    // Variables temporales (puedes moverlas como atributos de clase si lo prefieres)

        List<String> nombresAutomatas = new ArrayList<>();
        List<String> descripcionesLista = new ArrayList<>();
        List<String> estadosGlobales = new ArrayList<>();
        List<String> alfabeto = new ArrayList<>();
        List<String> estadosIniciales = new ArrayList<>();
        List<String> estadosFinales = new ArrayList<>();
        List<String> TransicionesLista = new ArrayList<>();
        /*
        // Agrega esto para depuración:
System.out.println("\n=== TOKENS DETALLADOS ===");
for (int j = 0; j < tokensAnalizados.size(); j++) {
    System.out.println(j + ": " + tokensAnalizados.get(j).getTipoToken() + 
                       " - '" + tokensAnalizados.get(j).getLexema() + "'");
}
        
        */
        // I VA EN 0
         int i = 0;
        while(i < tokensAnalizados.size()){

                    
   if(tokensAnalizados.get(i).getTipoToken().equals("Identificador") 
       && i+2 < tokensAnalizados.size() 
       && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")
       && tokensAnalizados.get(i+2).getTipoToken().equals("LlaveAbrir")) {
        
        String nombre = tokensAnalizados.get(i).getLexema();
        nombresAutomatas.add(nombre);
                        AutomataIndividual afd = new AutomataIndividual(nombre);
                        estadosGlobales.clear();
                        estadosFinales.clear();
                        System.out.println("Automata encontrado: " + nombre);
                        //I VA EN 1
 
                        i+=3;
                        //I VA EN 4
                        
                        
                        
                        while(i < tokensAnalizados.size() && !tokensAnalizados.get(i).getTipoToken().equals("LlaveCerrar")) {
            // Buscar descripción
            if(tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") 
               && tokensAnalizados.get(i).getLexema().equals("descripcion")
               && i+2 < tokensAnalizados.size()
               && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")
               ) {
                String descripcionActual = tokensAnalizados.get(i+2).getLexema();
                descripcionesLista.add(descripcionActual);
                System.out.println("Descripcion del automata "+ descripcionActual);
                i +=4;
                //I VA EN 8
            }
            
if(tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") 
        && tokensAnalizados.get(i).getLexema().equals("estados")
        && i+2 < tokensAnalizados.size()
        && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
    i += 2; // Avanzamos más allá de "estados" y ":"
    
    if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteAbrir")) {
        i++; // Avanzamos al primer estado
        
        // Creamos un Set temporal para verificar duplicados
        java.util.Set<String> estadosUnicos = new java.util.HashSet<>();
        
        while (i < tokensAnalizados.size() && !tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
            if (tokensAnalizados.get(i).getTipoToken().equals("Identificador")) {
                String estadoActual = tokensAnalizados.get(i).getLexema();
                
                // Verificamos si el estado ya existe
                if (!estadosUnicos.contains(estadoActual)) {
                    estadosUnicos.add(estadoActual);
                    estadosGlobales.add(estadoActual);
                    System.out.println("Estado encontrado: " + estadoActual);
                } else {
                    System.out.println("Estado duplicado ignorado: " + estadoActual);
                }
            }
            
            if (i+1 < tokensAnalizados.size() && tokensAnalizados.get(i+1).getTipoToken().equals("Coma")) {
                i++; // Saltamos la coma
            }
            
            i++; // Siguiente token
        }

        System.out.println("\n=== Lista de Estados Únicos ===");
        for (String estado : estadosGlobales) {
            System.out.println("- " + estado);
        }
        System.out.println("Posición final después de estados: " + i);
        i += 2; // Ajuste de posición después del corchete de cierre
    }
}
            
            
            
                       
           if(tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") 
        && tokensAnalizados.get(i).getLexema().equals("alfabeto")
        && i+2 < tokensAnalizados.size()
        && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
    
    i += 2; // Avanzamos más allá de "alfabeto" y ":"
    
    if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteAbrir")) {
        i++; // Avanzamos al primer elemento del alfabeto
        
        alfabeto.clear(); // Limpiamos la lista para este autómata
        
        while (i < tokensAnalizados.size() && !tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
            if (tokensAnalizados.get(i).getTipoToken().equals("Cadena de texto")) {
                // Eliminamos las comillas de los símbolos
                String simbolo = tokensAnalizados.get(i).getLexema();
                alfabeto.add(simbolo);
                System.out.println("Símbolo del alfabeto encontrado: " + simbolo);
            }
            
            if (i+1 < tokensAnalizados.size() && tokensAnalizados.get(i+1).getTipoToken().equals("Coma")) {
                i++; // Saltamos la coma
            }
            
            i++; // Siguiente token
        }
        
        // Avanzamos más allá del CorcheteCerrar
        if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
            i++;
        }
        
        System.out.println("\n=== Alfabeto del Autómata ===");
        for (String simbolo : alfabeto) {
            System.out.println("- " + simbolo);
        }
    }
}
           
                            
                        i++;
                           
           if(i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") 
    && tokensAnalizados.get(i).getLexema().equals("inicial") 
    && i+2 < tokensAnalizados.size() 
    && tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
    
    i += 2; // Avanzamos más allá de "inicial" y ":"
    
    if(i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Identificador")) {
        String estadoInicial = tokensAnalizados.get(i).getLexema();
        estadosIniciales.add(estadoInicial);
        afd.agregarEstadoInicial(estadoInicial);
        
        System.out.println("Estado inicial encontrado: " + estadoInicial + " para autómata " + nombre);
        
        // Avanzamos al siguiente token después del estado inicial
        i++;
        
        // Verificamos si hay una coma después (y la saltamos)
        if(i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Coma")) {
            i++;
        }
    }
} if(i < tokensAnalizados.size() && 
   tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") && 
   tokensAnalizados.get(i).getLexema().equals("finales") &&
   i+2 < tokensAnalizados.size() && 
   tokensAnalizados.get(i+1).getTipoToken().equals("DosPuntos")) {
    
    i += 2; // Avanzamos más allá de "finales" y ":"
    
    if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteAbrir")) {
        i++; // Avanzamos al primer estado final
        
        while (i < tokensAnalizados.size() && !tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
            if (tokensAnalizados.get(i).getTipoToken().equals("Identificador")) {
                String estadoFinal = tokensAnalizados.get(i).getLexema();
                
                // Verificamos que el estado exista en estadosGlobales
                if (estadosGlobales.contains(estadoFinal)) {
                    estadosFinales.add(estadoFinal);
                    afd.agregarEstadoFinal(estadoFinal);
                    System.out.println("Estado final encontrado: " + estadoFinal);
                } else {
                    System.out.println("ADVERTENCIA: El estado final " + estadoFinal + 
                                     " no existe en los estados globales");
                }
            }
            
            // Manejo de comas
            if (i+1 < tokensAnalizados.size() && tokensAnalizados.get(i+1).getTipoToken().equals("Coma")) {
                i++; // Saltamos la coma
            }
            
            i++; // Siguiente token
        }
        
        // Avanzamos más allá del CorcheteCerrar
        if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("CorcheteCerrar")) {
            i++;
        }
        
        // Verificamos si hay una coma después
        if (i < tokensAnalizados.size() && tokensAnalizados.get(i).getTipoToken().equals("Coma")) {
            i++;
        }
        
        System.out.println("\n=== Estados Finales del Autómata ===");
        for (String estado : estadosFinales) {
            System.out.println("- " + estado);
        }
    }
}
             
            
            
            
            
            if(tokensAnalizados.get(i).getTipoToken().equals("Palabra Reservada") && tokensAnalizados.get(i).getLexema().equals("transiciones")){
                    i+=1;
                    
                    while(!tokensAnalizados.get(i+1).getTipoToken().equals("LlaveCerrar")){
                        i+=2;
                        
                        String estadoActual = tokensAnalizados.get(i).getLexema();
                        
                        i+=3;
                        
                        while(!tokensAnalizados.get(i).getTipoToken().equals("ParentesisCerrar")){
                            String entrada = tokensAnalizados.get(i).getLexema();
                            
                            i+=2;
                            String estadoDestino = tokensAnalizados.get(i).getLexema();
                            
                            System.out.println("Estado actual :"+estadoActual + "Entrada: " +entrada + "EstadoDestino: "+estadoDestino);
                            afd.agregarTransicion(estadoActual, entrada, estadoDestino);
                            
                            
                            if(tokensAnalizados.get(i+1).getTipoToken().equals("Coma")){
                                i+=2;
                            }else{
                                i++;
                            }
                        }
                        }
                        
                    automata.put(nombre, afd);
                    
                    
                     
                    }
            





            
                        }
                        
                         
                       
                       
                      
              
                /*
                
                }
                   i++;
        }
        automata.get("AFD1").graficar();
                


System.out.println("\nEstados iniciales encontrados (" + estadosIniciales.size() + "):");
for(String estado : estadosIniciales) {
    System.out.println("- " + estado);
}



                */

                         
                         
                        }
   else{
       i++;
       
   }
        }
    }
    
    
    
    private void analizarArchivo(File archivo) {
        try {
            // Limpiar resultados anteriores
            txtResultados.setText("");
            automata.clear();
            
            // Leer archivo
            StringBuilder content = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                content.append(linea).append("\n");
            }
            
            // Procesar archivo
            analizadorLexico.analizarArchivo(content);
            
            // Mostrar tokens en la interfaz
            mostrarTokensEnGUI();
            
            // Generar reportes HTML
            String directorio = archivo.getParent();
            analizadorLexico.generarReporteHTML(directorio + "/reporte_tokens.html");
            analizadorLexico.generarReporteErroresHTML(directorio + "/reporte_errores.html");
            
            // Procesar autómatas
            procesarAutomata(analizadorLexico.getTokens());
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarTokensEnGUI() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== TOKENS ENCONTRADOS ===\n");
        for (Tokens token : analizadorLexico.getTokens()) {
            sb.append(token.toString()).append("\n");
        }
        
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 882, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
                try {
        javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        new Menu().setVisible(true);
    });
}
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
