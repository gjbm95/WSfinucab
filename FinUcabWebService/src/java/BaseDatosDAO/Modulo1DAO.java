/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;


import Dominio.Usuario;
import Services.Modulo1sResource;
import java.io.StringReader;
import java.net.URLDecoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

/**
 *
 * @author Junior
 */
public class Modulo1DAO extends ModuloDAO{
    
    
    @Override
    public int agregarDatos(Object object){
        Usuario usuario = (Usuario) object;
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
                return 1;
            } else {
                st.close();
                return 0;
            }

        } catch (Exception e) {

            return 2;

        }
    }
    
    public int ActualizarClave(String usuario, String clave){
    String decodifico = URLDecoder.decode(usuario);
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
           
            CallableStatement a = conn.prepareCall("{ call ActualizarClave(?,?) }");
            a.setString(1,usuario);
            a.setString(2,clave);
        
         
            if (a.execute() ) {
                st.close();
                return 1;
            } else {
                st.close();
                return 0;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 2;

        }
    }
    
    
    public int verificarUsuario(String usuario){
        
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            CallableStatement a = conn.prepareCall("{ call verificarUsuario(?) }");
            
            a.setString(1, usuario);
            a.execute();
            
            ResultSet rs = a.getResultSet();
            while(rs.next()){
                if (rs.getString(1)!=null){
                    return 1; //Usuario no disponible
                }else{     
                   return 0; //Usuario Disponible
                }
            }
        } catch (SQLException ex) {
             Logger.getLogger(Modulo1sResource.class.getName()).log(Level.SEVERE, null, ex);
             
        } catch (Exception e) {
            return 2;
        }
        return 0;
    }
    
    public String obtenerXRecuperarClave(String usuario){
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //String query = "SELECT * from recuperarclave('" + usuario+"');";
            CallableStatement a = conn.prepareCall("{ call recuperarclave(?) }");
            a.setString(1, usuario);
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
                return usuarioJsonObject.toString()+":-:recuperarclave";
            }
            return "ERROR";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public String obtenerInicioSesion(String usuario, String clave){
        String decodifico = URLDecoder.decode(usuario);
 
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
                return usuarioJsonObject.toString()+":-:iniciosesion";
            }

            return "DATOSMAL";
        } catch (SQLException ex) {
            Logger.getLogger(Modulo1sResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            return "ERROR";
        }
        
    return "";
    }
    
    
    
}
