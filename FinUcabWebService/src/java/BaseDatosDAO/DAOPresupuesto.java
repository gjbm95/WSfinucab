/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOPresupuesto;
import Dominio.Entidad;
import Dominio.Presupuesto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;

/**
 *
 * @author Junior
 */
public class DAOPresupuesto extends DAO implements IDAOPresupuesto {

    @Override
    public int agregar(Entidad e) {
       
        Presupuesto presupuesto = (Presupuesto) e;
        int respuesta =0;
        try{
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            CallableStatement pag = conn.prepareCall("{ call agregarPresupuesto (?,?,?,?,?,?)}");
            pag.setString(1, presupuesto.getNombre());
            pag.setDouble(2, presupuesto.getMonto());
            pag.setString(3, presupuesto.getClasificacion());
            pag.setInt(4,presupuesto.getDuracion());
            pag.setInt(5, presupuesto.getUsuario());
            pag.setInt(6, presupuesto.getCategoria());
            
            if (pag.execute()){
                st.close();
                respuesta = 1;
            } else {
                st.close();
                respuesta =0;
            }
        } catch (Exception ex){
            respuesta = 2;
            
        }
        return respuesta;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int verificarNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminarPresupuesto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
