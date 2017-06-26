/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import Dominio.Cuenta_Bancaria;
import Dominio.Entidad;
import Dominio.Tarjeta_Credito;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.logging.Level;
import java.util.logging.Logger;

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
            String datos [] = obj.getFechaven().split("-");
            System.out.println("fehca: " + obj.getFechaven() );
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,Integer.parseInt(datos[2]));
            calendar.set(Calendar.MONTH,Integer.parseInt(datos[1]));
            calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(datos[0]));
            Date date = new Date(calendar.getTime().getTime());
            cstmt.setDate(2,date);
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
            cstmt.setString(2, obj.getFechaven());
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

    public ArrayList<Entidad> consultarTodos() {
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

}
