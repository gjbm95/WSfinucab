/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOPago;
import Dominio.Entidad;
import Dominio.Pago;
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
 * @author Ramon
 */
public class DAOPago extends DAO implements IDAOPago {

    private Connection conn = Conexion.conectarADb();

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

        String respuesta = "";
        ArrayList<Entidad> listaPagos = new ArrayList<>();

        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();

            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT pg_id, pg_monto, pg_tipoTransaccion, categoriaca_id, pg_descripcion "
                    + "FROM Pago, Categoria WHERE categoriaca_id = ca_id AND usuariou_id = " + idUsuario);

            while (rs.next()) {
                Pago pago = new Pago(rs.getInt(1), rs.getInt(4), rs.getString(5), rs.getFloat(2), rs.getString(3));
                listaPagos.add(pago);

            }

            return listaPagos;

        } catch (SQLException ex) {
            Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public JsonArray getUltimosPagosXUsuario(int id) {
        CallableStatement cstm;
        String respuesta;
        JsonArray array = null;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall("{ call obtenerUltimosPagos(?)}");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            int n = 0;
            while (rs.next()) {
                n++;
                cuentaBuilder.add("est_id", "3." + Integer.toString(n));
                cuentaBuilder.add("est_fecha", rs.getString("pg_fecha"));
                cuentaBuilder.add("est_transaccion", rs.getString("pg_descripcion"));
                JsonObject cuentaJsonObject = cuentaBuilder.build();
                arrayBuilder.add(cuentaJsonObject);
            }
            array = arrayBuilder.build();

        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);

        }
        return array;
    }

    public JsonObject getBalance(int id) {
        CallableStatement cstm;
        String respuesta;
        JsonObject cuentaJsonObject = null;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall("{ call obtenerBalance(?)}");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            int n = 0;
            if (rs.next()) {
                float ingresos = rs.getFloat("ingreso");
                float egresos = rs.getFloat("egreso");
                float total = ingresos + egresos;
                ingresos = ingresos * 100 / total;
                egresos = egresos * 100 / total;
                cuentaBuilder.add("est_id", "2");
                cuentaBuilder.add("est_ingreso", Float.toString(ingresos));
                cuentaBuilder.add("est_egreso", Float.toString(egresos));
                cuentaJsonObject = cuentaBuilder.build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);

        }
        return cuentaJsonObject;
    }

}
