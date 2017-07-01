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
public class ComandoVerificarNombre extends Comando {

    private String nombrePresupuesto;

    public ComandoVerificarNombre(String nombrePresupuesto) {
        this.nombrePresupuesto = nombrePresupuesto;
    }
        
    @Override
    public void ejecutar() {
        IDAOPresupuesto dao = FabricaDAO.instanciarDAOPresupuesto();
        this.response = dao.verificarNombre(nombrePresupuesto);
    }
    
}
