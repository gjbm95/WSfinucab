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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
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
    
    @Override
    public String eliminarCategria(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public int agregar(Entidad e) {
        
        try {
            Categoria ca = (Categoria) e;
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            String query = "INSERT INTO categoria (usuariou_id, ca_nombre , c_descripcion , ca_esingreso , ca_eshabilitado  ) "
                    + "VALUES ( " + ca.getIdUsario()+ " , '" + ca.getNombre() + "' , '" + ca.getDescripcion()
                    + "' , " + "'" + ca.isIngreso() + "' , '" + ca.isEstaHabilitado()  + "');";
                       
            System.out.println(query);
           
            if (st.executeUpdate(query) > 0) {
                st.close();
                return 1;
            } else {
                st.close();
                return 0;
            }

        } catch (Exception ex) {

            return 2;

        }
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
    public ArrayList<Entidad> consultarTodos() {
        
              
        try{
                    
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Categoria WHERE ca_id <> -1  AND usuariou_id = '" + 1 + "';");
            ArrayList<Entidad> list= null;
            while (rs.next())
            {
                //Creo el objeto Json!             
                 Categoria ca= new Categoria();
                 ca.setIdcategoria(rs.getInt(1));
                 ca.setNombre(rs.getString(2));
                 ca.setDescripcion(rs.getString(3));
                 ca.setEsIngreso(rs.getBoolean(5));
                 ca.setEstaHabilitado(rs.getBoolean(4));
                 list.add(ca);
                 System.out.println(ca);
            }
             
            return list;
        }
        catch(Exception e) {
            return null;
        }
    }
   
}

