/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import Dominio.Entidad;
import Dominio.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlejandroNegrin
 */
public class DaoUsuario extends DAO {

    private Connection conn = Conexion.conectarADb();

    DaoUsuario() {
    }

 
    @Override
    public int agregar(Entidad e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Entidad modificar(Entidad e) {
         Usuario obj = (Usuario)e;
        CallableStatement cstmt;
    try {
        cstmt = conn.prepareCall("{ call update_usuario(?,?,?,?,?,?,?,?)}");
        cstmt.setInt(1, obj.getId());
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
        return obj;
    }

    @Override
    public Entidad consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Dictionary<Integer, Entidad> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }




    
}
