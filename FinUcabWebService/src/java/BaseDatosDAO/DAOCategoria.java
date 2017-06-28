/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOCategoria;
import Dominio.Categoria;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MariPerez
 */
public class DAOCategoria extends DAO implements IDAOCategoria {
    
    @Override
    public String eliminarCategria(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Entidad agregar(Entidad e) {
        
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
                return FabricaEntidad.obtenerSimpleResponseStatus(1);
            } else {
                st.close();
                return FabricaEntidad.obtenerSimpleResponseStatus(0);
            }

        } catch (Exception ex) {

            return FabricaEntidad.obtenerSimpleResponseStatus(2);

        }
    }

    @Override
    public Entidad modificar(Entidad e) {
        try {
           
            Categoria ca = (Categoria) e;
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            String query = "UPDATE categoria SET "
                    + "ca_nombre = '" + ca.getNombre()
                    + "', c_descripcion = '" + ca.getDescripcion()
                    + "', ca_esingreso = " + ca.isIngreso()
                    + ",ca_eshabilitado = " + ca.isEstaHabilitado() +
                    " WHERE "
                    + "ca_id = " + ca.getIdcategoria() + ";";
           
            if (st.executeUpdate(query) > 0) {
                st.close();
                return null;
            } else {
                st.close();
                return null;
                
            }

        } catch (Exception ex) {

            return null;

        }
    }

    @Override
    public Entidad consultar(int id) {
        
        String respuesta ="";
             
             try {
                 
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            
             //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Categoria WHERE ca_id = '" + id + "';");
            
                Categoria categoria = new Categoria(  rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(5), rs.getBoolean(4) );
           
            return categoria;
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public ListaEntidad consultarTodos(int idUsuario) {
              
        String respuesta ="";
        ArrayList<Entidad> listaCategoria = new ArrayList<>();
        
        try{
                    
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Categoria WHERE ca_id <> -1  AND usuariou_id = '" + idUsuario + "';");

            while (rs.next())
            {
                Categoria categoria = new  Categoria( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(5), rs.getBoolean(4) );
                listaCategoria.add(categoria);
                
            }
            
            ListaEntidad listaEntidad = FabricaEntidad.obtenerListaEntidad(listaCategoria);
        
            return listaEntidad;
        }
        catch(Exception e) {
            return null;
        }
    }
   
}

