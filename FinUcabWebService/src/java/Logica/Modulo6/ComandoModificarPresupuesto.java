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
public class ComandoModificarPresupuesto extends Comando{
    
    private Entidad presupuesto;

    public ComandoModificarPresupuesto(Entidad presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Override
    public Object ejecutar() {
        
        Entidad respuesta;
        IDAO dao = FabricaDAO.instanciarDAOPresupuesto();
        respuesta = dao.modificar(presupuesto);
        return respuesta;
    }
    
    
}
