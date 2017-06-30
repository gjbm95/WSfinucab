/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import Dominio.Cuenta_Bancaria;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author AlejandroNegrin
 */
public class DaoCuenta_Bancaria extends DAO {

    private Connection conn = Conexion.conectarADb();

    @Override
    public Entidad agregar(Entidad e) {
        Cuenta_Bancaria obj = (Cuenta_Bancaria) e;
        CallableStatement cstmt;
        int idCuenta = 0;
        try {
            cstmt = conn.prepareCall("{ call agregarCuentaBancaria(?,?,?,?,?)}");
            cstmt.setString(1, obj.getNombreBanco());
            cstmt.setString(2, obj.getTipoCuenta());
            cstmt.setString(3, obj.getNumcuenta());
            cstmt.setFloat(4, obj.getSaldoActual());
            cstmt.setInt(5, obj.getIdusuario());
            cstmt.executeQuery();
            ResultSet rs = cstmt.getResultSet();
            rs.next();
            idCuenta = rs.getInt(1);
            System.out.printf("id de: " + rs.getString(1));
            cstmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FabricaEntidad.obtenerSimpleResponse(idCuenta);
    }

    public Entidad modificar(Entidad e) {
        Cuenta_Bancaria obj = (Cuenta_Bancaria) e;
        CallableStatement cstmt;
        try {
            System.out.println("Paso por aqui");
            cstmt = conn.prepareCall("{ call modificarCuentaBancaria(?,?,?,?,?)}");
            cstmt.setString(3, obj.getTipoCuenta());
            cstmt.setString(2, obj.getNumcuenta());
            cstmt.setString(1, obj.getNombreBanco());
            cstmt.setFloat(4, obj.getSaldoActual());
            cstmt.setInt(5, obj.getId());
            cstmt.execute();
            cstmt.close();
            
          
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    public Entidad consultar(int id) {
        return null;
    }

    public ArrayList<Entidad> consultarTodos() {
        CallableStatement cstmt;
        int idCuenta = 0;

        return null;
    }

    public int eliminar(int id) {
        CallableStatement cstmt;
        int idCuenta = 0;
        try {
            cstmt = conn.prepareCall("{ call eliminarCuentaBancaria(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();
            ResultSet rs = cstmt.getResultSet();
            rs.next();
            idCuenta = rs.getInt(1);
            System.out.printf("id de: " + rs.getString(1));
            cstmt.close();
          
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idCuenta;
    }

    public String getCuentasXUsuario(int id) {
        CallableStatement cstm;
        String respuesta;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall("{ call obtenerCuentasBancarias(?,?)}");
            cstm.setInt(2, id);
            cstm.setString(1, "OBTENERCUENTASUSUARIO");
            System.out.println("Entre con id" + id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (rs.next()) {
                System.out.println("Entre2");
                cuentaBuilder.add("ct_id", rs.getString("ct_id"));
                cuentaBuilder.add("ct_tipo", rs.getString("ct_tipocuenta"));
                cuentaBuilder.add("ct_numerocuenta", rs.getString("ct_numcuenta"));
                cuentaBuilder.add("ct_nombrebanco", rs.getString("ct_nombrebanco"));
                cuentaBuilder.add("ct_saldoactual", rs.getString("ct_saldo"));
                JsonObject cuentaJsonObject = cuentaBuilder.build();
                arrayBuilder.add(cuentaJsonObject);
            }
            JsonArray array = arrayBuilder.build();
            respuesta = array.toString();
            cstm.close();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "0";
        }
        return respuesta;
    }

    @Override
    public ArrayList<Entidad> consultarTodos(int idUsuario) {
        return null;
    }

    public String getSaldoTotal(int id) {
        CallableStatement cstm;
        String respuesta;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall("{ call getSaldoCuentas(?)}");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            if (rs.next()) {
                respuesta = rs.getString(1);
            }
            else {
                respuesta = "";
            }
            cstm.close();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "e";
        }
        return respuesta;
    }

}
