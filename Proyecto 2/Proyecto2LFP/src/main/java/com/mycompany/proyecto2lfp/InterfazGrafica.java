/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto2lfp;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author aceba
 */



public class InterfazGrafica extends javax.swing.JFrame {
    
    private JTextArea txtAreaContenido;
    private JPanel panelImagen;
    private JComboBox<String> comboMundos;
    private JButton btnCargarArchivo, btnLimpiarArea, btnAnalizarArchivo, btnGenerarReportes;
    
    // Analizadores
    private AnaLexico analizadorLexico;
    private AnaSintactico analizadorSintactico;
    
    // Contenido del archivo
    private StringBuilder contenidoArchivo;

    /**
     * Creates new form InterfazGrafica
     */
    public InterfazGrafica() {
        // Inicializar analizadores
        analizadorLexico = new AnaLexico();
        
        // Configurar ventana principal
        setTitle("Acerca de");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Inicializar componentes
        initComponents();
        initComponents2();
    }
    
    
 private void initComponents2() {
        // Crear panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel central con GridLayout para texto e imagen
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 0));
        
        // Área de texto
        txtAreaContenido = new JTextArea();
        txtAreaContenido.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollTexto = new JScrollPane(txtAreaContenido);
        scrollTexto.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Área de texto",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        panelCentral.add(scrollTexto);
        
        // Panel para imagen
        panelImagen = new JPanel();
        panelImagen.setLayout(new BorderLayout());
        panelImagen.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Área de imagen",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        panelImagen.setBackground(Color.LIGHT_GRAY);
        panelCentral.add(panelImagen);
        
        // Añadir panel central a panel principal
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        
        // Panel superior para combo box y botón de reportes
        JPanel panelSuperior = new JPanel(new BorderLayout());
        
        // ComboBox para mundos
        String[] mundos = {"Isla del místico", "Valle de las brujas", "Montaña de dragones"};
        comboMundos = new JComboBox<>(mundos);
        comboMundos.setPreferredSize(new Dimension(200, 30));
        panelSuperior.add(comboMundos, BorderLayout.EAST);
        
        // Botón para generar reportes
        btnGenerarReportes = new JButton("Generar reportes");
        panelSuperior.add(btnGenerarReportes, BorderLayout.WEST);
        
        // Añadir panel superior a panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel inferior para botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Botones
        btnCargarArchivo = new JButton("Cargar archivo");
        btnLimpiarArea = new JButton("Limpiar área");
        btnAnalizarArchivo = new JButton("Analizar archivo");
        
        // Añadir botones al panel
        panelBotones.add(btnCargarArchivo);
        panelBotones.add(btnLimpiarArea);
        panelBotones.add(btnAnalizarArchivo);
        
        // Añadir panel de botones al panel principal
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        // Añadir panel principal al JFrame
        setContentPane(panelPrincipal);
        
        // Configurar eventos de botones
        configurarEventos();
    }
 
 
  private void configurarEventos() {
        // Evento del botón Cargar Archivo
        btnCargarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarArchivo();
            }
        });
        
        // Evento del botón Limpiar Área
        btnLimpiarArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarArea();
            }
        });
        
        // Evento del botón Analizar Archivo
        btnAnalizarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analizarArchivo();
            }
        });
        
        // Evento del botón Generar Reportes
        btnGenerarReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReportes();
            }
        });
    }
    
    /**
     * Carga un archivo seleccionado por el usuario
     */
    private void cargarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(this);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                // Leer el contenido del archivo
                contenidoArchivo = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenidoArchivo.append(linea).append("\n");
                }
                br.close();
                
                // Mostrar contenido en el área de texto
                txtAreaContenido.setText(contenidoArchivo.toString());
                
                // Mensaje de éxito
                JOptionPane.showMessageDialog(this, 
                        "Archivo cargado exitosamente: " + archivo.getName(),
                        "Archivo Cargado", 
                        JOptionPane.INFORMATION_MESSAGE);
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                        "Error al leer el archivo: " + ex.getMessage(),
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Limpia el área de texto y la imagen
     */
    private void limpiarArea() {
        txtAreaContenido.setText("");
        panelImagen.removeAll();
        panelImagen.repaint();
        contenidoArchivo = null;
    }
    
    /**
     * Analiza el archivo cargado
     */
    
    private void analizarArchivo() {
    // Obtenemos el texto actual del área de texto
    String textoActual = txtAreaContenido.getText();
    
    if (textoActual != null && !textoActual.isEmpty()) {
        try {
            // Actualizamos el contenidoArchivo con el texto modificado
            contenidoArchivo = new StringBuilder(textoActual);
            
            // Análisis léxico con el texto actual
            analizadorLexico.analizarArchivo(contenidoArchivo);
            analizadorLexico.imprimirTokens();
            System.out.println("");
            analizadorLexico.imprimirErrores();
            
            // Análisis sintáctico
            analizadorSintactico = new AnaSintactico(analizadorLexico.getTokens());
            analizadorSintactico.analizar();
            
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, 
                    "Análisis completado exitosamente.",
                    "Análisis Completado", 
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                    "Error durante el análisis: " + ex.getMessage(),
                    "Error de Análisis", 
                    JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, 
                "No hay contenido para analizar. Por favor escriba o cargue texto primero.",
                "Sin Contenido", 
                JOptionPane.WARNING_MESSAGE);
    }
}
    
    /**
     * Genera reportes HTML
     */
    private void generarReportes() {
        // Esta funcionalidad se implementará más adelante
        JOptionPane.showMessageDialog(this, 
                "La generación de reportes se implementará próximamente.",
                "Función en Desarrollo", 
                JOptionPane.INFORMATION_MESSAGE);
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
