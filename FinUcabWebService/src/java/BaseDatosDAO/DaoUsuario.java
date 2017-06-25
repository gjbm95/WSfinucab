/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import Dominio.Entidad;
import Dominio.Usuario;
import Services.Modulo1sResource;
import java.net.URLDecoder;
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
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

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
        Usuario usuario = (Usuario) e;
        int respuesta =0;
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            CallableStatement a = conn.prepareCall("{ call Registrar(?,?,?,?,?,?,?) }");
            a.setString(1, usuario.getUsuario());
            a.setString(2, usuario.getNombre());
            a.setString(3, usuario.getApellido());
            a.setString(4, usuario.getCorreo());
            a.setString(5, usuario.getPregunta());
            a.setString(6, usuario.getRespuesta());
            a.setString(7, usuario.getContrasena());
            if (a.execute()) {
                st.close();
                respuesta = 1;
            } else {
                st.close();
                respuesta = 0;
            }

        } catch (Exception ex) {

            respuesta = 2;

        }
        return respuesta;
    }
    
    public int ActualizarClave(String usuario, String clave){
    String decodifico = URLDecoder.decode(usuario);
    int respuesta = 0;
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
           
            CallableStatement a = conn.prepareCall("{ call ActualizarClave(?,?) }");
            a.setString(1,usuario);
            a.setString(2,clave);
            a.execute();
            ResultSet rs = a.getResultSet();
           
            while(rs.next()){
                if (rs.getString(1).equals("1")){
                    st.close();
                    respuesta =  5; 
                }else{ 
                   st.close();
                   respuesta =  6; 
                }
            }
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respuesta = 2;

        }
        return respuesta;
    }
    
    public int verificarUsuario(String usuario){
        int respuesta = 0;
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            CallableStatement a = conn.prepareCall("{ call verificarUsuario(?) }");
            
            a.setString(1, usuario);
            a.execute();
            
            ResultSet rs = a.getResultSet();
            while(rs.next()){
                if (rs.getString(1)!=null){
                    st.close();
                    respuesta =  4; //Usuario no disponible
                }else{     
                   st.close();
                   respuesta =  3; //Usuario Disponible
                }
            }
        } catch (SQLException ex) {
             Logger.getLogger(Modulo1sResource.class.getName()).log(Level.SEVERE, null, ex);
             
        } catch (Exception e) {
            respuesta =  2;//cambiar
        }
        return respuesta;
    }
    
    public String obtenerInicioSesion(String usuario, String clave){
        String decodifico = URLDecoder.decode(usuario);
        String respuesta="";
        int bandera=0;
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            
            CallableStatement a = conn.prepareCall("{ call iniciarSesion(?,?) }");
            a.setString(1,  usuario);
            a.setString(2,  clave);
            a.execute();
  
           ResultSet rs = a.getResultSet();
            while (rs.next()) {
                JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                usuarioBuilder.add("u_id", rs.getString("id"));
                usuarioBuilder.add("u_usuario", rs.getString("usuario"));
                usuarioBuilder.add("u_nombre", rs.getString("nombre"));
                usuarioBuilder.add("u_apellido", rs.getString("apellido"));
                usuarioBuilder.add("u_correo", rs.getString("correo"));
                usuarioBuilder.add("u_pregunta", rs.getString("pregunta"));
                usuarioBuilder.add("u_respuesta", rs.getString("respuesta"));
                usuarioBuilder.add("u_password", rs.getString("password"));
                JsonObject usuarioJsonObject = usuarioBuilder.build();
                respuesta= usuarioJsonObject.toString()+":-:iniciosesion";
                bandera=1;
            }
            if(bandera==0){
              st.close();
              respuesta= "7";
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(Modulo1sResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            respuesta= "ERROR";
        }
        
    return respuesta;
    }
    
    public String obtenerXRecuperarClave(String usuario){
        String respuesta="";
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //String query = "SELECT * from recuperarclave('" + usuario+"');";
            CallableStatement a = conn.prepareCall("{ call recuperarclave(?) }");
            a.setString(1, usuario);
            a.execute(); 
            ResultSet rs = a.getResultSet();
            Integer bandera = 0;
            while (rs.next()) {
                JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                usuarioBuilder.add("u_id", rs.getString("id"));
                usuarioBuilder.add("u_usuario", rs.getString("usuario"));
                usuarioBuilder.add("u_nombre", rs.getString("nombre"));
                usuarioBuilder.add("u_apellido", rs.getString("apellido"));
                usuarioBuilder.add("u_correo", rs.getString("correo"));
                usuarioBuilder.add("u_pregunta", rs.getString("pregunta"));
                usuarioBuilder.add("u_respuesta", rs.getString("respuesta"));
                usuarioBuilder.add("u_password", rs.getString("password"));
                JsonObject usuarioJsonObject = usuarioBuilder.build();
                respuesta = usuarioJsonObject.toString()+":-:recuperarclave";
                bandera = 1;
            }
            if(bandera == 0){
                st.close();
                respuesta = "ERROR";
            }
            
        } catch (Exception e) {
            respuesta = e.getMessage();
        }
        return respuesta;
    }
    

    @Override
    public Entidad modificar(Entidad e) {
        Usuario obj = (Usuario) e;
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
    public ArrayList<Entidad> consultarTodos(int idUsuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
