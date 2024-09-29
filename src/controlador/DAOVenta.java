/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modeloConexion.Conexion; // Importar la clase para la conexión
import java.sql.CallableStatement; // Usar java.sql en lugar de com.mysql.cj.jdbc
import java.sql.Connection; // Importar Connection
import java.sql.ResultSet; // Importar ResultSet
import java.sql.SQLException; // Importar SQLException
import java.util.ArrayList; // Importar ArrayList
import java.util.HashMap; // Importar HashMap
import java.util.List; // Importar java.util.List
import java.util.Map; // Importar Map\
import java.sql.Date;
import java.util.Locale;
import javax.lang.model.util.Types;
import javax.swing.JOptionPane;
import modelo.Producto;
import modelo.Venta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author gabriel rivas
 */
public class DAOVenta {
    Conexion conectar = Conexion.getInstance();
    public int insertarVenta(Venta vent) throws SQLException{
        
        try{ // PARA MANEJAR ERRORES AL REALIZAR LA CONEX Y TRASACCION EN BD
            
        //--------------LLAMA A PROCED ALMACENADO ----------//
         CallableStatement st = conectar.Conectar().
                prepareCall("{CALL insertarventa(?,?)}"); // esto falta
                       // cliente se quita
                st.setInt(1, vent.getNum_pago());
                // Convertir java.util.Date a java.sql.Date directamente en la línea
                st.setDate(2, new java.sql.Date(vent.getFecha().getTime()));
                st.executeUpdate();
                
        }catch(SQLException e){
            System.out.println(e+"Error");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int insertarDetalleVenta(Venta vent) throws SQLException{
        try{
            //---------LLAMA LA PROCED ALMACEDNADO-------------//
             CallableStatement st = conectar.Conectar().
              prepareCall("{CALL insertarDetalleVenta(?,?,?,?)}");
             st.setInt(1, vent.getNum_factura());
             st.setInt(2, vent.getId_producto());
             st.setInt(3, vent.getCantidad());
             st.setDouble(4, vent.getPrecio());
             st.executeUpdate();
             
        } catch (SQLException e){
            System.out.println(e+"Error");
            conectar.cerrarConexion();
            return -1;
        }
        conectar.cerrarConexion();
        return 0;
    }
    
    public int obtenerUltimoNumFactura() throws SQLException{
        int numfac = 0;
        ResultSet rs = null;
        
        try{
            CallableStatement st = conectar.Conectar().
                prepareCall("{CALL obtenerUltimoIDFactura()}");
            
             // ekecucion del proced / obtencion de datos 
              rs = st.executeQuery();
              if (rs.next()){
                  numfac = rs.getInt(1);
              }
              return numfac;
        }catch (SQLException e){
            System.out.println("No se realizo la consulta"+ e.getMessage());
            return 0;
        }
    }
    
    public int actualizarExistenciaProductos(Producto pro) throws SQLException {
        try{
            
            CallableStatement st = conectar.Conectar().
                    prepareCall("{CALL actualizarExistenciaPro(?,?)}");
                        
                    st.setInt(1,pro.getId_producto());
                    st.setInt(2, pro.getExistencia());
                    st.executeUpdate();
                    
                    return 0;
                    
        }catch (SQLException e){
            System.out.println("No se realizo la consulta" + e.getMessage());
            return -1;
        }
    }
        
    public void productosExistenciabaja()throws JRException{
        conectar.Conectar();
        
        //ruta 
        String path =  "src/Reportes/factura_Letter.jrxml";
        
        JasperReport jr;
        
        try{
            jr = JasperCompileManager.compileReport(path);
            JasperPrint mostrarReporte = JasperFillManager.fillReport
                    (jr,null,conectar.Conectar());
            
            JasperViewer.viewReport(mostrarReporte, false);
        }catch(JRException e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Error" + e);
        }
    }
}
