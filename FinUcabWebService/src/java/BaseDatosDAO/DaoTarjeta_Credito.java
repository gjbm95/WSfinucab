/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import Dominio.Cuenta_Bancaria;
import Dominio.Entidad;
import Dominio.Tarjeta_Credito;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author AlejandroNegrin
 */
public class DaoTarjeta_Credito extends DAO {

    private Connection conn = Conexion.conectarADb();

    @Override
    public int agregar(Entidad e) {
        Tarjeta_Credito obj = (Tarjeta_Credito) e;
        CallableStatement cstmt;
        int idtarjeta = 0;
        try {
            cstmt = conn.prepareCall("{ call agregarTarjetaCredito(?,?,?,?,?)}");
            cstmt.setString(1, obj.getTipotdc());
            String datos[] = obj.getFechaven().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(datos[2]));
            calendar.set(Calendar.MONTH, Integer.parseInt(datos[1]));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(datos[0]));
            Date date = new Date(calendar.getTime().getTime());
            cstmt.setDate(2, date);
            cstmt.setString(3, obj.getNumero());
            cstmt.setFloat(4, obj.getSaldo());
            cstmt.setInt(5, obj.getIdusuario());
            cstmt.executeQuery();
            ResultSet rs = cstmt.getResultSet();
            rs.next();
            idtarjeta = rs.getInt(1);
            System.out.printf("id de: " + rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idtarjeta;
    }

    @Override
    public Entidad modificar(Entidad e) {
        Tarjeta_Credito obj = (Tarjeta_Credito) e;
        CallableStatement cstmt;
        try {
            cstmt = conn.prepareCall("{ call modificarTarjetaCredito(?,?,?,?,?)}");
            cstmt.setString(1, obj.getTipotdc());
            String datos[] = obj.getFechaven().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(datos[2]));
            calendar.set(Calendar.MONTH, Integer.parseInt(datos[1]));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(datos[0]));
            Date date = new Date(calendar.getTime().getTime());
            cstmt.setDate(2, date);
            cstmt.setString(3, obj.getNumero());
            cstmt.setFloat(4, obj.getSaldo());
            cstmt.setInt(5, obj.getId());
            cstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    public Entidad consultar(int id) {
        return null;
    }

    public ArrayList<Entidad> consultarTodos(int idUsuario) {
        return null;
    }

    public int eliminar(int id) {
        CallableStatement cstmt;
        int idtarjeta = 0;
        try {
            cstmt = conn.prepareCall("{ call eliminarTarjetasCredito(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();
            ResultSet rs = cstmt.getResultSet();
            rs.next();
            idtarjeta = rs.getInt(1);
            System.out.printf("id de: " + rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idtarjeta;
    }

    public String getTarjetasXUsuario(int id) {
        CallableStatement cstm;
        String respuesta;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall("{ call obtenerTarjetasCredito(?,?)}");
            cstm.setInt(2, id);
            cstm.setString(1, "OBTENERTARJETASSUSUARIO");
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder tdcBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (rs.next()) {

                tdcBuilder.add("tc_id", Integer.toString(rs.getInt("tc_id")));
                tdcBuilder.add("tc_tipo", rs.getString("tc_tipotarjeta"));
                tdcBuilder.add("tc_fechavencimiento", rs.getString("tc_fechavencimiento"));
                tdcBuilder.add("tc_numero", rs.getString("tc_numero"));
                tdcBuilder.add("tc_saldo", Float.toString(rs.getFloat("tc_saldo")));
                JsonObject tdcJsonObject = tdcBuilder.build();
                arrayBuilder.add(tdcJsonObject);
            }
            JsonArray array = arrayBuilder.build();
            respuesta = array.toString();
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "0";
        }
        return respuesta;
    }

    public String getSaldoTotal(int id) {
        CallableStatement cstm;
        String respuesta;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall("{ call getSaldoTarjetas(?)}");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                respuesta = rs.getString(1);
            } else {
                respuesta = "";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "e";
        }
        return respuesta;
    }

}
