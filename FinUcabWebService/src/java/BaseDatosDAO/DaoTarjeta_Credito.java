
package BaseDatosDAO;
import BaseDatosDAO.Interfaces.IDAOTarjetaCredito;
import Dominio.Cuenta_Bancaria;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Tarjeta_Credito;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
*Modulo 2 - Modulo de Home
*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Metodos del servicio web destinados para las funcionalidades de Home y 
* Tarjetas de Credito y Cuentas Bancarias. 
*
**/
public class DaoTarjeta_Credito extends DAO implements IDAOTarjetaCredito {

    private Connection conn = Conexion.conectarADb();

    
    /**
     * Metodo encargado de registrar Tarjetas de Credito en la base de datos 
     * 
     * @param e Entidad Tarjeta_Credito a ser almacenada
     * @return Objeto de tipo Tarjeta_Credito (Entidad sin castear)
     */
    @Override
    public Entidad agregar(Entidad e) {
        Tarjeta_Credito obj = (Tarjeta_Credito) e;
        CallableStatement cstmt;
        int idtarjeta = 0;
        try {
            cstmt = conn.prepareCall("{ call agregarTarjetaCredito(?,?,?,?,?)}");
            cstmt.setString(1, obj.getTipotdc());
            String datos[] = obj.getFechaven().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(datos[2]));
            calendar.set(Calendar.MONTH, Integer.parseInt(datos[1]));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(datos[0]));
            Date date = new Date(calendar.getTime().getTime());
            cstmt.setDate(2, date);
            cstmt.setString(3, obj.getNumero());
            cstmt.setFloat(4, obj.getSaldo());
            cstmt.setInt(5, obj.getIdusuario());
            cstmt.executeQuery();
            ResultSet rs = cstmt.getResultSet();
            rs.next();
            idtarjeta = rs.getInt(1);
            obj.setId(idtarjeta);
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Agregado Tarjeta de credito con exito");
            cstmt.close();
            rs.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Agregado Tarjeta de credito con exito");
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    
    /**
     * Metodo encargado de modificar Tarjetas de Credito en la base de datos 
     * 
     * @param e Entidad Tarjeta_Credito a ser modificada
     * @return Objeto de tipo Tarjeta_Credito (Entidad sin castear)
     */
    @Override
    public Entidad modificar(Entidad e) {
        Tarjeta_Credito obj = (Tarjeta_Credito) e;
        CallableStatement cstmt;
        try {
            cstmt = conn.prepareCall("{ call modificarTarjetaCredito(?,?,?,?,?)}");
            cstmt.setString(1, obj.getTipotdc());
            String datos[] = obj.getFechaven().split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(datos[2]));
            calendar.set(Calendar.MONTH, Integer.parseInt(datos[1]));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(datos[0]));
            Date date = new Date(calendar.getTime().getTime());
            cstmt.setDate(2, date);
            cstmt.setString(3, obj.getNumero());
            cstmt.setFloat(4, obj.getSaldo());
            cstmt.setInt(5, obj.getId());
            cstmt.execute();
            Logger.getLogger(getClass().getName()).log(
            Level.FINER,"Modificado Tarjeta de credito con exito");
            cstmt.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Modificado Tarjeta de credito con exito");
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    public Entidad consultar(int id) {
        return null;
    }

    public ListaEntidad consultarTodos(int idUsuario) {
        return null;
    }

    
    /**
     * Metodo encargado de eliminar Tarjetas de Credito en la base de datos 
     * 
     * @param e Id de las tarjetas de credito a ser almacenadas. 
     * @return Objeto de tipo Tarjeta_Credito (Entidad sin castear)
     */
    public int eliminar(int id) {
        CallableStatement cstmt;
        int idtarjeta = 0;
        try {
            cstmt = conn.prepareCall("{ call eliminarTarjetasCredito(?)}");
            cstmt.setInt(1, id);
            cstmt.executeQuery();
            ResultSet rs = cstmt.getResultSet();
            rs.next();
            idtarjeta = rs.getInt(1);
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Eliminado Tarjeta de credito con exito");
            cstmt.close();
            rs.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Eliminado Tarjeta de credito con exito");
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idtarjeta;
    }

    
    /**
     * Metodo encargado de mostrar Tarjetas de Credito de un usuario determinado
     * 
     * @param e Entidad Id del usuario titular de las tarjetas de credito. 
     * @return Objeto de tipo Tarjeta_Credito (Entidad sin castear)
     */
    public String getTarjetasXUsuario(int id) {
        CallableStatement cstm;
        String respuesta ="";
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall("{ call obtenerTarjetasCredito(?,?)}");
            cstm.setInt(2, id);
            cstm.setString(1, "OBTENERTARJETASSUSUARIO");
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder tdcBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (rs.next()) {
                tdcBuilder.add("tc_id", Integer.toString(rs.getInt("tc_id")));
                tdcBuilder.add("tc_tipo", rs.getString("tc_tipotarjeta"));
                tdcBuilder.add("tc_fechavencimiento", rs.getString("tc_fechavencimiento"));
                tdcBuilder.add("tc_numero", rs.getString("tc_numero"));
                tdcBuilder.add("tc_saldo", Float.toString(rs.getFloat("tc_saldo")));
                JsonObject tdcJsonObject = tdcBuilder.build();
                arrayBuilder.add(tdcJsonObject);
            }
            JsonArray array = arrayBuilder.build();
            respuesta = array.toString();
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Obtenido lista de Tarjetas de credito con exito");
            cstm.close();   
            st.close();
            rs.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Obtenido lista de Tarjetas de credito con exito");
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "0";
        }catch (Exception ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "0";
        }
        return respuesta;
    }

    
    /**
     * Metodo encargado de mostrar el saldo de las Tarjetas de Credito
     *  registradas en la base de datos 
     * 
     * @param e Entidad Tarjeta_Credito a ser almacenada
     * @return Objeto de tipo Tarjeta_Credito (Entidad sin castear)
     */
    public String getSaldoTotal(int id) {
        CallableStatement cstm;
        String respuesta;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall("{ call getSaldoTarjetas(?)}");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            if (rs.next()) {
                respuesta = rs.getString(1);
            } else {
                respuesta = "";
            }
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Obtenido saldo de deuda de Tarjeta de credito con exito");
            cstm.close();
            st.close();
            rs.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Obtenido saldo de deuda de Tarjeta de credito con exito");
        } catch (SQLException ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "e";
        }catch (Exception ex) {
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "e";
        }
        return respuesta;
    }

}
