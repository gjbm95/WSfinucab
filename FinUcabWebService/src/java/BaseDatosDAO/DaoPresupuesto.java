package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOPresupuesto;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Presupuesto;
import IndentityMap.SingletonIdentityMap;
import Registro.RegistroBaseDatos;
import Registro.RegistroIdentityMap;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import jdk.nashorn.internal.codegen.types.Type;

public class DaoPresupuesto extends DAO implements IDAOPresupuesto {

    @Override
    public Entidad agregar(Entidad e) {

        Presupuesto presupuesto = (Presupuesto) e;
        int respuesta = 0;
        try {

            Connection conn = Conexion.conectarADb();
            PreparedStatement pag = conn.prepareStatement(RegistroBaseDatos.AGREGAR_PRESUPUESTO);
            pag.setString(1, presupuesto.getNombre());
            pag.setDouble(2, presupuesto.getMonto());
            pag.setString(3, presupuesto.getClasificacion());
            pag.setInt(4, presupuesto.getDuracion());
            pag.setInt(5, presupuesto.getUsuario());
            pag.setInt(6, Integer.parseInt(presupuesto.getCategoria()));
            pag.executeQuery();
            ResultSet rs = pag.getResultSet();
            rs.next();
            respuesta = rs.getInt("agregarpresupuesto");
            pag.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = 2;

        }

        return FabricaEntidad.obtenerSimpleResponse(respuesta);
    }

    @Override
    public Entidad modificar(Entidad e) {

        Presupuesto presupuesto = (Presupuesto) e;
        int respuesta = 0;

        try {
            Connection conn = Conectar();
            PreparedStatement pag = conn.prepareStatement(RegistroBaseDatos.MODIFICAR_PRESUPUESTO);
            pag.setString(1, presupuesto.getNombre());
            pag.setDouble(2, presupuesto.getMonto());
            pag.setString(3, presupuesto.getClasificacion());
            pag.setInt(4, presupuesto.getDuracion());
            pag.setInt(5, presupuesto.getUsuario());
            pag.setInt(6, Integer.parseInt(presupuesto.getCategoria()));
            pag.setInt(7, presupuesto.getId());
            System.out.println(pag.toString());
            pag.executeQuery();
            ResultSet rs = pag.getResultSet();
            rs.next();
            respuesta = rs.getInt("modificarpresupuesto");
            pag.close();
            Desconectar(conn);

        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = 2;
        }
        return presupuesto;
    }

    @Override
    public Entidad consultar(int id) {
        Entidad presupuesto = null;

        if (presupuesto == null) {
            try {
                Connection conn = Conectar();
                PreparedStatement ps = conn.prepareStatement(RegistroBaseDatos.OBTENER_PRESUPUESTO);
                ps.setInt(1, id);
                System.out.println(ps.toString());
                ps.executeQuery();
                
                ResultSet rs = ps.getResultSet(); 
                rs.next();
                presupuesto = new Presupuesto(rs.getInt(1), rs.getString(2), rs.getDouble(4),
                        rs.getString(6), rs.getInt(5), String.valueOf(rs.getInt(3)), 
                        String.valueOf(rs.getBoolean(7)));
                       
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return presupuesto;
    }

    @Override
    public ListaEntidad consultarTodos(int idUsuario) {

        ListaEntidad listaEntidad = SingletonIdentityMap.getInstance().getListaEntidad(RegistroIdentityMap.LISTA_PRESUPUESTO);

        if (listaEntidad == null) {

            try {
                ArrayList<Entidad> listaPresupuestos = new ArrayList<>();
                Connection conn = Conectar();
                PreparedStatement ps = conn.prepareStatement(RegistroBaseDatos.LISTAR_PRESUPUESTOS);
                ps.setInt(1, idUsuario);
                System.out.println(ps.toString());
                ps.executeQuery();

                ResultSet rs = ps.getResultSet();

                while (rs.next()) {
                    Presupuesto p = new Presupuesto(rs.getInt(1), rs.getString(2),
                            rs.getDouble(4), rs.getString(6),
                            rs.getInt(5), rs.getString(3),
                            String.valueOf(rs.getBoolean(7)));
                    listaPresupuestos.add(p);
                }

                listaEntidad = FabricaEntidad.obtenerListaEntidad(listaPresupuestos);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaEntidad;
    }

    @Override
    public int verificarNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int eliminarPresupuesto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

   
    /**
     * Metodo que se encarga de Obtener los presupuestos realizados por el 
     * usuario. 
     * @param id Id del usuario que realizo los presupuestos
     * @return Un arreglo de Json con los dados de los ultimos presupuestos realizados 
     */
    public JsonArray getUltimosPresupuestos(int id) {
        CallableStatement cstm;
        JsonArray array = null;
        try {
            Statement st = Conexion.conectarADb().createStatement();
            cstm = Conexion.conectarADb().prepareCall("{ call obtenerUltimosPresupuestos(?)}");
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            int n = 0;
            while (rs.next()) {
                n++;
                cuentaBuilder.add("est_id", "5." + Integer.toString(n));
                cuentaBuilder.add("est_fecha", rs.getString("pr_fecha"));
                cuentaBuilder.add("est_transaccion", rs.getString("pr_nombre"));
                JsonObject cuentaJsonObject = cuentaBuilder.build();
                arrayBuilder.add(cuentaJsonObject);
            }
            array = arrayBuilder.build();
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Presupuestos obtenidos con exito");
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Presupuestos obtenidos con exito");
        } catch (SQLException ex) {
            Logger.getLogger(DaoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

}