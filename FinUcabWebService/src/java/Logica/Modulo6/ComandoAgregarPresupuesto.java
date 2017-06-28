/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo6;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAO;
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
    public Object ejecutar() {
        int respuesta =0;
        IDAO dao = FabricaDAO.instanciarDAOPresupuesto();
        respuesta = dao.agregar(presupuesto);
        return respuesta;
    }
    
}
