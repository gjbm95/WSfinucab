/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOPresupuesto;
import Dominio.Entidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Alejandro Negrin
 */
public class DaoPresupuesto extends DAO implements IDAOPresupuesto {

    private Connection conn = Conexion.conectarADb();
    
    DaoPresupuesto() {
    }

    @Override
    public int agregar(Entidad e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entidad modificar(Entidad e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entidad consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Entidad> consultarTodos(int idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int verificarNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminarPresupuesto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JsonArray getUltimosPresupuestos(int id) {
        CallableStatement cstm;
        JsonArray array = null;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall("{ call obtenerUltimosPresupuestos(?)}");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            int n = 0;
            while (rs.next()) {
                n++;
                cuentaBuilder.add("est_id", "5." + Integer.toString(n));
                cuentaBuilder.add("est_fecha", rs.getString("pr_fecha"));
                cuentaBuilder.add("est_transaccion", rs.getString("pr_nombre"));
                JsonObject cuentaJsonObject = cuentaBuilder.build();
                arrayBuilder.add(cuentaJsonObject);
            }
            array = arrayBuilder.build();

        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);

        }
        return array;
    }

}
