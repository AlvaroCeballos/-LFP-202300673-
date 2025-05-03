/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto2lfp;
import javax.swing.ImageIcon;
import java.awt.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Set;
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
    private AnaLexico analizadorLexico;
    private AnaSintactico analizadorSintactico;
    private StringBuilder contenidoArchivo;

    /**
     * Creates new form InterfazGrafica
     */
    public InterfazGrafica() {
        analizadorLexico = new AnaLexico();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initComponents();
        initComponents2();
    }
    
    
 private void initComponents2() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(70, 130, 180));
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 0));
        txtAreaContenido = new JTextArea();
        txtAreaContenido.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollTexto = new JScrollPane(txtAreaContenido);
        scrollTexto.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Área de texto",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        panelCentral.add(scrollTexto);
        panelImagen = new JPanel();
        panelImagen.setLayout(new BorderLayout());
        panelImagen.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Área de imagen",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        panelImagen.setBackground(Color.LIGHT_GRAY);
        panelCentral.add(panelImagen);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        JPanel panelSuperior = new JPanel(new BorderLayout());
        String[] mundos = {};
        comboMundos = new JComboBox<>(mundos);
        comboMundos.setPreferredSize(new Dimension(200, 30));
        panelSuperior.add(comboMundos, BorderLayout.EAST);
        btnGenerarReportes = new JButton("Graficar");
        btnGenerarReportes.setBackground(new Color (34, 139, 34));
        panelSuperior.add(btnGenerarReportes, BorderLayout.WEST);
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnCargarArchivo = new JButton("Cargar archivo");
        btnLimpiarArea = new JButton("Limpiar área");
        btnAnalizarArchivo = new JButton("Analizar archivo");
        panelBotones.add(btnCargarArchivo);
        panelBotones.add(btnLimpiarArea);
        panelBotones.add(btnAnalizarArchivo);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        setContentPane(panelPrincipal);
        configurarEventos();
    }
 
 
private void configurarEventos() {
    btnCargarArchivo.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cargarArchivo();
        }
    });
    
    btnLimpiarArea.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            limpiarArea();
        }
    });
    
    btnAnalizarArchivo.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            analizarArchivo();
        }
    });
    
    btnGenerarReportes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            generarReportes();
        }
    });
}
    
  
  private void graficarMundoSeleccionado() {
    // Verificar si hay un analizador sintáctico y mundos disponibles
    if (analizadorSintactico == null || analizadorSintactico.getNombresMundos().isEmpty()) {
        return;
    }
    
    // Obtener el nombre del mundo seleccionado
    String mundoSeleccionado = (String) comboMundos.getSelectedItem();
    if (mundoSeleccionado == null) {
        return;
    }
    
    // Ajustar el nombre para buscar en el HashMap (con comillas)
    String nombreMundoKey = "\"" + mundoSeleccionado + "\"";
    
    try {
        // Generar la gráfica para el mundo seleccionado
        analizadorSintactico.graficarMundo(nombreMundoKey);
        
        // Mostrar la imagen generada en el panel
        mostrarImagenGenerada();
        
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, 
                "Error al graficar el mundo: " + ex.getMessage(),
                "Error de Graficación", 
                JOptionPane.ERROR_MESSAGE);
    }
}

/**
 * Muestra la imagen generada en el panel de imagen
 */
private void mostrarImagenGenerada() {
    try {
        // Ruta de la imagen generada
        String rutaImagen = "C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto 2\\grafica.png";
        File archivo = new File(rutaImagen);
        
        if (archivo.exists()) {
            // Cargar la imagen
            ImageIcon icono = new ImageIcon(rutaImagen);
            
            // Redimensionar la imagen para que se ajuste al panel
            Image imagen = icono.getImage();
            int anchoPanel = panelImagen.getWidth();
            int altoPanel = panelImagen.getHeight();
            
            // Solo redimensionar si el panel tiene dimensiones válidas
            if (anchoPanel > 0 && altoPanel > 0) {
                Image imagenRedimensionada = imagen.getScaledInstance(
                        anchoPanel - 20, altoPanel - 20, Image.SCALE_SMOOTH);
                icono = new ImageIcon(imagenRedimensionada);
            }
            
            // Limpiar el panel y mostrar la imagen
            panelImagen.removeAll();
            JLabel etiquetaImagen = new JLabel(icono);
            panelImagen.add(etiquetaImagen, BorderLayout.CENTER);
            
            // Actualizar el panel
            panelImagen.revalidate();
            panelImagen.repaint();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se encontró el archivo de imagen generado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this,
                "Error al mostrar la imagen: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}



    private void cargarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(this);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                contenidoArchivo = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenidoArchivo.append(linea).append("\n");
                }
                br.close();
                txtAreaContenido.setText(contenidoArchivo.toString());
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
    
    private void limpiarArea() {
        txtAreaContenido.setText("");
        panelImagen.removeAll();
        panelImagen.repaint();
        contenidoArchivo = null;
    }
    
    private void actualizarComboBoxMundos() {
    if (analizadorSintactico != null) {
        Set<String> nombresMundos = analizadorSintactico.getNombresMundos();
        comboMundos.removeAllItems();
       
        for (String nombre : nombresMundos) {
            if (nombre.startsWith("\"") && nombre.endsWith("\"")) {
                nombre = nombre.substring(1, nombre.length() - 1);
            }
            comboMundos.addItem(nombre);
        }
       
        if (!nombresMundos.isEmpty()) {
            comboMundos.setSelectedIndex(0);
        }
    }
}
    
   private void analizarArchivo() {
    String textoActual = txtAreaContenido.getText();
    
    if (textoActual != null && !textoActual.isEmpty()) {
        try {
            contenidoArchivo = new StringBuilder(textoActual);
            analizadorLexico.analizarArchivo(contenidoArchivo);
            analizadorLexico.imprimirTokens();
            System.out.println("");
            analizadorLexico.imprimirErrores();
             analizadorLexico.generarReporteHTML("C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto 2\\reporteTokensBoton.html");
            analizadorLexico.generarReporteErroresHTML("C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto 2\\reporteErroresBoton.html");
            analizadorSintactico = new AnaSintactico(analizadorLexico.getTokens());
            analizadorSintactico.analizar();
            analizadorSintactico.generarReporteErroresHTML("C:\\Users\\aceba\\OneDrive\\Desktop\\Practica1\\-LFP-202300673-\\Proyecto 2\\reporteErroresBotonSintactico.html");
            
            // Actualizar combobox con los mundos encontrados
            actualizarComboBoxMundos();
            
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

// Método generarReportes - implementando la funcionalidad de graficación
private void generarReportes() {
    // Verificar si hay mundos disponibles para graficar
    if (analizadorSintactico == null) {
        JOptionPane.showMessageDialog(this, 
                "No hay análisis realizado. Por favor analice un archivo primero.",
                "Sin datos de análisis", 
                JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (comboMundos.getItemCount() == 0) {
        JOptionPane.showMessageDialog(this, 
                "No se encontraron mundos para graficar.",
                "Sin mundos", 
                JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
        // Graficar el mundo seleccionado
        graficarMundoSeleccionado();
        
        JOptionPane.showMessageDialog(this, 
                "Gráfico generado exitosamente.",
                "Gráfico generado", 
                JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, 
                "Error al generar el gráfico: " + ex.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
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
