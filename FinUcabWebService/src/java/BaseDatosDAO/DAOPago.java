/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOPago;
import Dominio.Categoria;
import Dominio.Entidad;
import Dominio.Pago;
import Dominio.Usuario;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

/**
 *
 * @author Ramon
 */
public class DAOPago extends DAO implements IDAOPago{

    @Override
    public int agregar(Entidad e) {
        Pago pago = (Pago) e;
        int respuesta =0;
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            CallableStatement pag = conn.prepareCall("{ call Agregar(?,?,?,?,?) }");
            pag.setFloat(1, pago.getTotal());
            pag.setString(2, pago.getDescripcion());
            pag.setString(3, pago.getTipo());
            pag.setInt(4, pago.getCategoria());
            pag.setInt(5, pago.getIdUsario());
            
            if (pag.execute()) {
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
    
    

    @Override
    public Entidad modificar(Entidad e) {
        
       try {
            Pago pago = (Pago) e;
            Connection conn = Conexion.conectarADb();
           
            Statement st = conn.createStatement();
           
            String query = "UPDATE pago SET "
                    + "pg_monto = '" +pago.getTotal()
                    + "', pg_tipoTransaccion = '" + pago.getTipo()
                    + "', categoriaca_id= " + pago.getCategoria()
                    + ",pg_descripcion = '" + pago.getDescripcion() +
                    "' WHERE "
                    + "pg_id = " + pago.getIdUsario();
            
                   
            if (st.executeUpdate(query) > 0) {
                st.close();
                System.out.println("modificacion exitosa");
                return null;
            } else {
                st.close();
                System.out.println("no se pudo modificar");
                return null;
                
            }

        } catch (Exception ex) {

            return null;

        }
            
    }

    @Override
    public Entidad consultar(int idPago) {
             String respuesta ="";
             
             try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            
             //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT pg_id, pg_monto, pg_tipoTransaccion, categoriaca_id, pg_descripcion "
                    + "FROM Pago, Categoria WHERE categoriaca_id = ca_id AND usuariou_id = "+ idPago);
            
            
                Pago pago = new Pago( rs.getInt(1), rs.getInt(4), rs.getString(5), rs.getFloat(2), rs.getString(3) );
                //listaPagos.add(pago);
                
           
            
            return pago;
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
   
    

    @Override
    public ArrayList<Entidad> consultarTodos(int idUsuario) {
        
        String respuesta ="";
        ArrayList<Entidad> listaPagos = new ArrayList<>();
        
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT pg_id, pg_monto, pg_tipoTransaccion, categoriaca_id, pg_descripcion "
                    + "FROM Pago, Categoria WHERE categoriaca_id = ca_id AND usuariou_id = "+ idUsuario);
            
            while (rs.next())
            {
                Pago pago = new Pago( rs.getInt(1), rs.getInt(4), rs.getString(5), rs.getFloat(2), rs.getString(3) );
                listaPagos.add(pago);
                
            }
            
            return listaPagos;
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
}
