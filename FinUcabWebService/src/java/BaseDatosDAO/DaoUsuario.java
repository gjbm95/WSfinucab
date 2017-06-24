/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import Dominio.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlejandroNegrin
 */
public class DaoUsuario extends Modulo2DAO<Usuario> {

    private Connection conn = Conexion.conectarADb();

    DaoUsuario() {
    }

    @Override
    public String agregar(Usuario obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String modificar(Usuario obj) {
        CallableStatement cstmt;
        try {
            cstmt = conn.prepareCall("{ call update_usuario(?,?,?,?,?,?,?,?)}");
            cstmt.setInt(1, obj.getIdusuario());
            cstmt.setString(2, obj.getUsuario());
            cstmt.setString(3, obj.getNombre());
            cstmt.setString(4, obj.getApellido());
            cstmt.setString(5, obj.getCorreo());
            cstmt.setString(6, obj.getPregunta());
            cstmt.setString(7, obj.getRespuesta());
            cstmt.setString(8, obj.getContrasena());
            cstmt.execute();
            System.out.printf("ENTREEEEEEEEEEE");
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "hola";
    }

    @Override
    public String consultarTodos(Usuario obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String eliminar(Usuario obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
