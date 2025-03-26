/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JTextArea;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author aceba
 */
public class TextAreaOutputStream extends OutputStream{
    private final JTextArea textArea;
public TextAreaOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
    @Override
    public void write(int b) throws IOException {
        // Convierte el byte a char y lo añade al JTextArea
        textArea.append(String.valueOf((char) b));
        // Autoscroll
        textArea.setCaretPosition(textArea.getDocument().getLength());
    
}
}
