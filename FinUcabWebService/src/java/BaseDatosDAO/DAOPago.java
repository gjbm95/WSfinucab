/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOPago;
import Dominio.Entidad;
import Dominio.Pago;
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

/**
 *
 * @author Ramon
 */
public class DAOPago extends DAO implements IDAOPago{

    @Override
    public int agregar(Entidad e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
