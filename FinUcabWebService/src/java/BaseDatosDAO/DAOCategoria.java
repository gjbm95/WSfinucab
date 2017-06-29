/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOCategoria;
import Dominio.Categoria;
import Dominio.Entidad;
import java.io.StringReader;
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
import javax.json.JsonReader;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MariPerez
 */
public class DAOCategoria extends DAO implements IDAOCategoria {   
    
    private Connection conn = Conexion.conectarADb();
    
    
    public int agregar(Entidad e) {
        int respuesta = 0;
        try {
            Categoria ca = (Categoria) e;
            Statement st = conn.createStatement();
            CallableStatement cat = conn.prepareCall("{ call AgregarCategoria(?,?,?,?,?) }");
            cat.setInt(1, ca.getIdUsario());
            cat.setString(2, ca.getNombre());
            cat.setString(3, ca.getDescripcion());
            cat.setBoolean(4, ca.isIngreso());
            cat.setBoolean(5, ca.isEstaHabilitado());
             cat.executeQuery();
            ResultSet rs = cat.getResultSet();
            rs.next();
            
            } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }              
                   
         
        


    @Override
    public Entidad modificar(Entidad e) {
        Categoria ca = (Categoria) e;        
        try {
            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ call ModificarCategoria(?,?,?,?,?) }");
            cstmt.setString(1,ca.getNombre());
            cstmt.setString(2,ca.getDescripcion());
            cstmt.setBoolean(3,ca.isIngreso());
            cstmt.setBoolean(4,ca.isEstaHabilitado());
            cstmt.setInt(5, ca.getIdcategoria());
            cstmt.execute();
           } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ca;
    }
        
        
        
       
        
    @Override
    public Entidad consultar(int idcategoria) {
         Categoria ca = null;
         
         try {

            Statement st = conn.createStatement();
                                   
            CallableStatement a = conn.prepareCall("{ call ConsultarCategoria(?) }");
            a.setInt(1, idcategoria);
            a.executeQuery();
                        
            ResultSet rs = a.getResultSet();
            while (rs.next()){
                ca = new Categoria( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5), rs.getInt(6));
               
               
            }
            
            return ca;

            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ca;
        
    }
   


    @Override
    public ArrayList<Entidad> consultarTodos(int idUsuario) {
        
        ArrayList<Entidad> listaCategorias = new ArrayList<>();
        
        try {
            Statement st = conn.createStatement();
            
            CallableStatement a = conn.prepareCall("{ call ConsultarTodos(?) }");
            a.setInt(1, idUsuario);
            a.executeQuery();
  
           ResultSet rs = a.getResultSet();
                     
            while (rs.next())
            {
               Categoria ca = new Categoria( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5), rs.getInt(6));
                listaCategorias.add(ca);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaCategorias;
        
    }

    @Override 
    public int eliminarCategoria(int idCategoria){
        int respuesta=0;
        try {
            Statement st = conn.createStatement();
            EliminarCategoria2(idCategoria, "presupuesto");
            EliminarCategoria2(idCategoria,"pago");
            Categoria ca = null;
            CallableStatement cat = conn.prepareCall("{ call EliminarCategoria(?) }");
            cat.setInt(1,idCategoria);
            cat.executeQuery();
            ResultSet rs = cat.getResultSet();
            rs.next();
            
            } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }        

    
    public boolean EliminarCategoria2 (int idcat, String tabla){
        boolean respuesta = false;
        try {
            CallableStatement cstmt;
            cstmt = conn.prepareCall("{ call EliminarCategoria2(?,?) }");
            cstmt.setInt(1, idcat);
            cstmt.setString(2, tabla);
            cstmt.execute();
            
           } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
