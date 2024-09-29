/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import modeloConexion.Conexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author gabriel rivas
 */
public class DAOCategoria {
    
     Conexion conectar = Conexion.getInstance();

    public List ObtenerDatos() throws SQLException {
     
        String proced = "listarCategoria()";
        List<Map> registros = new Database().Listar(proced);
        List<Categorias> cat = new ArrayList();

        for (Map registro : registros) { // aqui recupera los datos de la base de datos CUIDADO
            Categorias ca = new Categorias((int) registro.get("id_categoria"),
            (String) registro.get("nombrecategoria")
                    
            );
            cat.add(ca);
        }
        return cat;
    }
}
