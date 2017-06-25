/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import Dominio.Cuenta_Bancaria;
import Dominio.Entidad;
import Dominio.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlejandroNegrin
 */
public class DaoCuenta_Bancaria extends DAO {

    private Connection conn = Conexion.conectarADb();

    @Override
    public int agregar(Entidad e) {
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
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idCuenta;
    }

    public Entidad modificar(Entidad e) {
        Cuenta_Bancaria obj = (Cuenta_Bancaria) e;
        CallableStatement cstmt;
        try {
            cstmt = conn.prepareCall("{ call modificarCuentaBancaria(?,?,?,?,?)}");
            cstmt.setString(1, obj.getTipoCuenta());
            cstmt.setString(2, obj.getNumcuenta());
            cstmt.setString(3, obj.getNombreBanco());
            cstmt.setFloat(4, obj.getSaldoActual());
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
        int idCuenta = 0;
        try {
            cstmt = conn.prepareCall("{ call eliminarCuentaBancaria(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();
            ResultSet rs = cstmt.getResultSet();
            rs.next();
            idCuenta = rs.getInt(1);
            System.out.printf("id de: " + rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idCuenta;
    }

}
