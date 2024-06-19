/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unidad02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author sheco
 */
public class Archivo {
    
    private String nombre;
    
    public Archivo(String nombre){
        this.nombre = nombre;
    }
    
    public LinkedList<String> obtenerTextoDelArchivo(){
        LinkedList<String> lineasDeTexto = null;
        try{
            File archivo = obtenerArchivo();
            if(archivo.exists()){
                lineasDeTexto=new LinkedList();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null){
                    System.out.println(linea);
                    lineasDeTexto.add(linea);
                }
                br.close();
            }else{
                JOptionPane.showMessageDialog(null, "El archivo de texto no existe");
            }
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Se produjo un Error");
        }
        return lineasDeTexto;
    }
    
    private File obtenerArchivo(){
        try { URL ur1 = getClass().getClassLoader().getResource("archivos/"+nombre);
        return new File(ur1.toURI());
        }catch(URISyntaxException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean registrar(String linea){
        File archivo = obtenerArchivo();
        try{
            if(archivo.exists()){
                FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                pw.println(linea);
                pw.flush();
                pw.close();
                return true;
            }
        }catch(Exception error){
            error.printStackTrace();
        }
        return false;
    }
    
    public boolean borrarContenido(){
        try{
            File archivo = obtenerArchivo();
            String directorio = archivo.getParent();
            archivo.delete();
            new FileWriter(directorio + "/productos.txt", true);
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
}
