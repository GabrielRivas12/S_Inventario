/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.List;
import modeloConexion.Conexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


/**
 *
 * @author gabriel rivas
 */
public class DAODetalle {
    
    Conexion conectar = Conexion.getInstance();
    
    public List ObtenerSalida()throws SQLException{
        String Proced ="listaVenta";
        
         List<Map> registros = new Database().Listar(Proced);
           List<Detalle> detall = new ArrayList();
           
           for(Map registro: registros ){
               Detalle deta = new Detalle ((int) registro.get("num_factura"),
                       (int) registro.get("id_producto"),
                       (String) registro.get("nombreProducto"),
                       (int) registro.get("cantidad"),
                       (Double) registro.get("precioventa"),
                       (Date) registro.get("fecha"));
                   detall.add(deta);
           }return detall;
    
    }
}
