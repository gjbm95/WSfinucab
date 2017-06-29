/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo6;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAO;
import BaseDatosDAO.Interfaces.IDAOPresupuesto;
import Dominio.Entidad;
import Logica.Comando;

/**
 *
 * @author William
 */
public class ComandoAgregarPresupuesto extends Comando{

    private Entidad presupuesto;

    public ComandoAgregarPresupuesto(Entidad presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    @Override
    public void ejecutar() {
        
        IDAOPresupuesto dao = FabricaDAO.instanciarDAOPresupuesto();
        this.response = dao.agregar(presupuesto);
    }
    
}
