/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.Date;
import java.util.List;
import modeloConexion.Conexion;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author gabriel rivas
 */
public class DAOProducto {
    
    Conexion conectar = Conexion.getInstance();
    
    public List busquedaProducto(String parametroBusqueda) throws SQLException{
        ResultSet rs = null;
        List<Map> registros = null;
        List<Producto> productos = new ArrayList();
        
        List resultado = new ArrayList();
        
        try{
            CallableStatement st = conectar.Conectar(). 
                    prepareCall("{CALL buscarProducto(?)}");
                    
            st.setString(1, parametroBusqueda);
            rs=st.executeQuery();
            resultado = OrganizarDatos(rs);
            registros = resultado;
            
            for (Map registro : registros) {
                Producto pro = new Producto((int) registro.get("id_producto"),
                            (int) registro.get("id_categoria"),
                            (String) registro.get("nombreProducto"),
                            (Double) registro.get("precio"), 
                            (int) registro.get("Existencia")
                );
                    productos.add(pro);
                
            }
        }catch (SQLException e){
            System.out.println("No se realizo la consulta:" + e.getMessage());
        }
        return productos;
    }

    private List OrganizarDatos(ResultSet rs) {
        List filas = new ArrayList(); 
        
        try{
            
            int numColumnas = rs.getMetaData().getColumnCount();
            while(rs.next()){
                Map<String, Object> renglon = new HashMap();
                
                for (int i = 1; i <= numColumnas; i++) {
                    
                    String nombreCampo = rs.getMetaData().getColumnName(i);
                    Object valor = rs.getObject(nombreCampo);
                    renglon.put(nombreCampo,valor);
                }
                filas.add(renglon);
            }
        }catch(SQLException e){
            System.out.println("Error"+ e);
        }
        return filas;
    }
    
    public List ObtenerProducto() throws SQLException{
    
        
        String proced =  "listarProductos()"; //lista de productos
        List <Map>  registros  = new Database().Listar(proced);
        List<Producto> products = new ArrayList();
        
        for (Map registro : registros) {
              Producto proo= new Producto ((int) registro.get("id_producto"),
                      (int) registro.get("id_categoria"),
                      (String)registro.get("nombreProducto"),
                      (Double)registro.get("precio"),
                      (int) registro.get("Existencia"));
                      
                      
              products.add(proo);
    }return products;
    
    
}
    public int Insertar (Producto prod) throws SQLException{
    try{
        
        CallableStatement st=conectar.Conectar().
                prepareCall("{CALL insertar }");
                st.setInt(1, prod.getId_producto());
                st.setInt(2, prod.getId_categoria());
                st.setString(3,prod.getNombreProducto());
                st.setDouble(4, prod.getPrecio());
                st.setInt(5, prod.getExistencia());
                
                st.executeUpdate();
        
    }catch(SQLException e){
        System.out.println(e +"Error");
        conectar.cerrarConexion();
        return -1;
    }
    conectar.cerrarConexion();
    return 0;
    }
    
    
    
}
