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
public class ComandoObtenerPresupuesto extends Comando {

    private int idPresupuesto;

    public ComandoObtenerPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }
        
    @Override
    public void ejecutar() {
        IDAOPresupuesto dao = FabricaDAO.instanciarDAOPresupuesto();
        this.response = dao.consultar(idPresupuesto);
    }
    
}
