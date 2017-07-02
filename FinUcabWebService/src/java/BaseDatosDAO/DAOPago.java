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
import Exceptions.FabricaExcepcion;
import IndentityMap.SingletonIdentityMap;
import Logica.Modulo5.AgregarPagoException;
import Logica.Modulo5.ConsultarPagoException;
import Logica.Modulo5.ListarPagosException;
import Logica.Modulo5.ModificarPagoException;
import Registro.RegistroBaseDatos;
import Registro.RegistroIdentityMap;
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

/**
 *
 * @author Ramon
 */
public class DAOPago extends DAO implements IDAOPago{

    @Override
    public Entidad agregar(Entidad e) throws AgregarPagoException {


        Pago pago = (Pago) e;
        CallableStatement pag;
        int idPago = 0;
         
        try {                
            
            Connection conn = Conexion.conectarADb();
            pag = conn.prepareCall(RegistroBaseDatos.AGREGAR_PAGO);
            pag.setFloat(1, pago.getTotal());
            pag.setString(2, pago.getDescripcion());
            pag.setString(3, pago.getTipo());
            pag.setInt(4, pago.getCategoria());
            pag.executeQuery();
            ResultSet rs = pag.getResultSet();
            
            if (rs.next() ){
                idPago = rs.getInt(1); 
            }else{
                throw FabricaExcepcion.instanciarAgregarPagoException(100);
            }
           
            pago.setId(idPago);
            
            SingletonIdentityMap.getInstance().addEntidadEnLista(RegistroIdentityMap.pago_listado, pago);

        } catch (SQLException ex) {
            throw FabricaExcepcion.instanciarAgregarPagoException(998);                
        }
        

        return FabricaEntidad.obtenerSimpleResponse(idPago);
            
    }
    
    
    private boolean containString(String[] cases, String text){
        
        for (String aCase : cases) {
            if (aCase.equals(text))
                return true;
        }
        
        return false;
    }
    

    @Override
    public Entidad modificar(Entidad e) throws ModificarPagoException {
        
        Pago pago = (Pago) e;
        CallableStatement cstmt;
        
        String[] tipos = new String[2];
        tipos[0] = "ingreso";
        tipos[1] = "egreso";
        
        if (!containString(tipos, pago.getTipo()))
            throw FabricaExcepcion.instanciarModificarPagoException(102);
        
        try {
           
            Connection conn = Conexion.conectarADb();
            
            //System.out.println(pago.getId()+"-"+pago.getTotal()+"-"+pago.getDescripcion()+"-"+pago.getTipo()+"-"+pago.getCategoria());
            cstmt = conn.prepareCall(RegistroBaseDatos.MODIFICAR_PAGO);
            cstmt.setInt(1,pago.getId());
            cstmt.setFloat(2,pago.getTotal());
            cstmt.setString(3,pago.getDescripcion());
            cstmt.setString(4,pago.getTipo());
            cstmt.setInt(5,pago.getCategoria());
            cstmt.execute();
            
            SingletonIdentityMap.getInstance().updateEntidadEnLista(RegistroIdentityMap.pago_listado, pago);
            
        } catch (SQLException ex) {
            throw FabricaExcepcion.instanciarModificarPagoException(998);
            
        }
        
        return pago;
    }
         
   

    @Override
    public Entidad consultar(int idPago ) throws ConsultarPagoException{

        
        Entidad pago = SingletonIdentityMap.getInstance().getEntidadEnLista(RegistroIdentityMap.pago_listado, idPago);
        if (pago == null ){
                
            try {

                Connection conn = Conexion.conectarADb();
                Statement st = conn.createStatement();

                CallableStatement a = conn.prepareCall(RegistroBaseDatos.OBTENER_PAGO);
                a.setInt(1, idPago);
                a.executeQuery();

                ResultSet rs = a.getResultSet();
                if (rs.next()){
                    pago =  FabricaEntidad.obtenerPago( rs.getInt(1), rs.getInt(5), rs.getString(3), rs.getFloat(2), rs.getString(4), rs.getString(6) );
                }else{
                    
                    throw FabricaExcepcion.instanciarConsultarPagoException(101);
                }
                
                SingletonIdentityMap.getInstance().addEntidadEnLista(RegistroIdentityMap.pago_listado, pago);

            } catch (SQLException ex) {
                throw FabricaExcepcion.instanciarConsultarPagoException(998);
            }
        }
        
        return pago;
        
    }
   
    

    @Override
    public ListaEntidad consultarTodos(int idUsuario) throws ListarPagosException {
        
        ListaEntidad listaEntidad = SingletonIdentityMap.getInstance().getListaEntidad(RegistroIdentityMap.pago_listado);
        if (listaEntidad.getLista().isEmpty()){
            try {
                
                ArrayList<Entidad> listaPagos = new ArrayList<>();
                Connection conn = Conexion.conectarADb();
                Statement st = conn.createStatement();

                CallableStatement a = conn.prepareCall(RegistroBaseDatos.LISTAR_PAGOS);
                a.setInt(1, idUsuario);
                a.executeQuery();

                ResultSet rs = a.getResultSet();

                while (rs.next())
                {
                    Pago pago = FabricaEntidad.obtenerPago(rs.getInt(1), rs.getInt(5), rs.getString(3), rs.getFloat(2), rs.getString(4), rs.getString(6) );
                    listaPagos.add(pago);
                }

                listaEntidad = FabricaEntidad.obtenerListaEntidad(listaPagos);
                
                SingletonIdentityMap.getInstance().setListaEntidad(RegistroIdentityMap.pago_listado, listaEntidad);
                
            } catch (SQLException ex) {
                throw FabricaExcepcion.instanciarListarPagosException(998);
            }
        }
        
        return listaEntidad;
        
    }


    public JsonArray getUltimosPagosXUsuario(int id) {
        CallableStatement cstm;
        String respuesta;
        JsonArray array = null;
        try {
            Statement st = Conexion.conectarADb().createStatement();
            cstm = Conexion.conectarADb().prepareCall("{ call obtenerUltimosPagos(?)}");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            int n = 0;
            while (rs.next()) {
                n++;
                cuentaBuilder.add("est_id", "3." + Integer.toString(n));
                cuentaBuilder.add("est_fecha", rs.getString("pg_fecha"));
                cuentaBuilder.add("est_transaccion", rs.getString("pg_descripcion"));
                JsonObject cuentaJsonObject = cuentaBuilder.build();
                arrayBuilder.add(cuentaJsonObject);
            }
            array = arrayBuilder.build();
            cstm.close();
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);

        }
        return array;
    }

    public JsonObject getBalance(int id) {
        CallableStatement cstm;
        String respuesta;
        JsonObject cuentaJsonObject = null;
        try {
            Statement st = Conexion.conectarADb().createStatement();
            cstm = Conexion.conectarADb().prepareCall("{ call obtenerBalance(?)}");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            int n = 0;
            if (rs.next()) {
                float ingresos = rs.getFloat("ingreso");
                float egresos = rs.getFloat("egreso");
                float total = ingresos + egresos;
                ingresos = ingresos * 100 / total;
                egresos = egresos * 100 / total;
                cuentaBuilder.add("est_id", "2");
                cuentaBuilder.add("est_ingreso", Float.toString(ingresos));
                cuentaBuilder.add("est_egreso", Float.toString(egresos));
                cuentaJsonObject = cuentaBuilder.build();
            }
            cstm.close();
            st.close();
            rs.close();
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Balance obtenido del usuario de id: "+id);
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Balance obtenido del usuario de id: "+id);
        } catch (SQLException ex) {
            Logger.getLogger(DAOPago.class.getName()).log(Level.SEVERE, null, ex);

        }
        return cuentaJsonObject;
    }
}