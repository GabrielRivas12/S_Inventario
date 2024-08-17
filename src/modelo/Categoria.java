/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author gabriel rivas
 */
public class Categoria {
    
    //A POCO SI 
    
    // prueba de git a ver si funciona esta huevada 
    
    int id_categoria;
    String nombreCategoria;

    public Categoria(int id_categoria, String nombreCategoria) {
        this.id_categoria = id_categoria;
        this.nombreCategoria = nombreCategoria;
    }

    public Categoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
        
    }
     
    @Override
    public String toString(){
    return id_categoria + " - " + nombreCategoria;
    }
    
     
    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
  
    
}
