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
         Entidad entidad = null;
             
             try {
                 
                Connection conn = Conexion.conectarADb();
                Statement st = conn.createStatement();

                 //Se coloca el query
                ResultSet rs = st.executeQuery("SELECT * FROM Categoria WHERE ca_id = '" + id + "';");
                while (rs.next()){
                entidad = new Categoria( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(5), rs.getBoolean(4),rs.getInt(6) );
                }
                
                return entidad;
            
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
                Categoria categoria = new  Categoria( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(5), rs.getBoolean(4), rs.getInt(6) );
                listaCategoria.add(categoria);
                
            }
            
            ListaEntidad listaEntidad = FabricaEntidad.obtenerListaEntidad(listaCategoria);
        
            return listaEntidad;
        }
        catch(Exception e) {
            return null;
        }
    }
    
    @Override 
    public int eliminarCategoria(int idCategoria){
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            EliminarCategoria2(idCategoria, "presupuesto");
            EliminarCategoria2(idCategoria,"pago");
           
            String query = "DELETE FROM categoria WHERE ca_id =" + idCategoria  + ";";
            
            if (st.executeUpdate(query) > 0) {
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
    
    public boolean EliminarCategoria2 (int id, String tabla){
        try{
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            String query = "UPDATE "+tabla+" SET "
                    + "categoriaca_id = " + -1 + 
                    " WHERE "
                    + "categoriaca_id = " + id + ";";
            if (st.executeUpdate(query) > 0) {
                st.close();
                return true;
            } else {
                st.close();
                return false;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false ;

        }
    }
   
}

