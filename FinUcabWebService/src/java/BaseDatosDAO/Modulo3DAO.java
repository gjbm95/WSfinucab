/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOPresupuesto;
import Dominio.Entidad;
import java.util.Dictionary;

/**
 *
 * @author Junior
 */
public class Modulo3DAO extends DAO implements IDAOPresupuesto {

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
    public Dictionary<Integer, Entidad> consultarTodos() {
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
