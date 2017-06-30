/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo3;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOPresupuesto;
import Logica.Comando;

/**
 *
 * @author William
 */
public class ComandoListarPresupuestos extends Comando {

    private int idUsuario; 

    public ComandoListarPresupuestos(int idUsuario) {
        this.idUsuario = idUsuario;
    }
            
    @Override
    public void ejecutar() {
        IDAOPresupuesto dao = FabricaDAO.instanciarDAOPresupuesto();
        this.response = dao.consultarTodos(idUsuario);
    }
    
}
