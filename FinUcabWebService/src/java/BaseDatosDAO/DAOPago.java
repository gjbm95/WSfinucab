/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOPago;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Pago;
import IndentityMap.SingletonIdentityMap;
import Registro.RegistroIdentityMap;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ramon
 */
public class DAOPago extends DAO implements IDAOPago{

    @Override
    public Entidad agregar(Entidad e) {

            Pago pago = (Pago) e;
            CallableStatement pag;
            
        int idPago = 0;
        try {
            pag = conn.prepareCall("{ call AgregarPago(?,?,?,?) }");
            pag.setFloat(1, pago.getTotal());
            pag.setString(2, pago.getDescripcion());
            pag.setString(3, pago.getTipo());
            pag.setInt(4, pago.getCategoria());
            pag.executeQuery();
            ResultSet rs = pag.getResultSet();
            rs.next();            
            idPago = rs.getInt(1);
            
            pago.setId(idPago);
            SingletonIdentityMap.getInstance().addEntidadEnLista(RegistroIdentityMap.pago_listado, pago);
                        
            } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return FabricaEntidad.obtenerSimpleResponse(idPago);

    }
    
    

    @Override
    public Entidad modificar(Entidad e) {
        
        Pago pago = (Pago) e;
        CallableStatement cstmt;
        
        try {
            cstmt = conn.prepareCall("{ call ModificarPago(?,?,?,?,?) }");
            cstmt.setInt(1,pago.getId());
            cstmt.setFloat(2,pago.getTotal());
            cstmt.setString(3,pago.getDescripcion());
            cstmt.setString(4,pago.getDescripcion());
            cstmt.setInt(5,pago.getCategoria());
            cstmt.execute();
            
            SingletonIdentityMap.getInstance().updateEntidadEnLista(RegistroIdentityMap.pago_listado, pago);
            
           } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pago;
    }
         
   

    @Override
    public Entidad consultar(int idPago ) {

        Entidad pago = SingletonIdentityMap.getInstance().getEntidadEnLista(RegistroIdentityMap.pago_listado, idPago);
        
        if (pago == null ){
                
            try {

                Connection conn = Conexion.conectarADb();
                Statement st = conn.createStatement();

                CallableStatement a = conn.prepareCall("{ call ConsultarPago(?) }");
                a.setInt(1, idPago);
                a.executeQuery();

                ResultSet rs = a.getResultSet();
                while (rs.next()){
                    pago = new Pago( rs.getInt(1), rs.getInt(5), rs.getString(3), rs.getFloat(2), rs.getString(4) );
                }
                
                SingletonIdentityMap.getInstance().addEntidadEnLista(RegistroIdentityMap.pago_listado, pago);

            } catch (SQLException ex) {
                Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return pago;
        
    }
   
    

    @Override
    public ListaEntidad consultarTodos(int idUsuario) {
        
        ListaEntidad listaEntidad = SingletonIdentityMap.getInstance().getListaEntidad(RegistroIdentityMap.pago_listado);
        
        if (listaEntidad == null ){
            try {
                
                ArrayList<Entidad> listaPagos = new ArrayList<>();
                Connection conn = Conexion.conectarADb();
                Statement st = conn.createStatement();

                CallableStatement a = conn.prepareCall("{ call ListaPagos(?) }");
                a.setInt(1, idUsuario);
                a.executeQuery();

                ResultSet rs = a.getResultSet();

                while (rs.next())
                {
                    Pago pago = new Pago(rs.getInt(1), rs.getInt(5), rs.getString(3), rs.getFloat(2), rs.getString(4) );
                    listaPagos.add(pago);
                }

                listaEntidad = FabricaEntidad.obtenerListaEntidad(listaPagos);
                
                SingletonIdentityMap.getInstance().setListaEntidad(RegistroIdentityMap.pago_listado, listaEntidad);
                
            } catch (SQLException ex) {
                Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return listaEntidad;
        
    }

   
    
}
