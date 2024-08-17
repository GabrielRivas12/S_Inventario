import modeloConexion.Conexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.Categoria;
import modelo.Database;

public class DAOCategoria {

    Conexion conectar = Conexion.getInstance();

    public List<Categoria> ObtenerCategoria() throws SQLException {
        String proced = "listarCategoria()";
        List<Map<String, Object>> registros = new Database().Listar(proced);
        List<Categoria> cat = new ArrayList<>();

        for (Map<String, Object> registro : registros) {
            Categoria ca = new Categoria(
                (int) registro.get("id_categoria"),
                (String) registro.get("nombreCategoria")
            );
            cat.add(ca);
        }
        return cat;
    }
}
