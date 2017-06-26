/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo5;

import BaseDatosDAO.DAO;
import BaseDatosDAO.FabricaDAO;
import Logica.Comando;

/**
 *
 * @author Juan
 */
public class ComandoConsultarPago extends Comando {
    
    private int idPago;
    
    public ComandoConsultarPago(int idPago){
        this.idPago = idPago;
    }
    
    @Override
    public Object ejecutar() {
        
        DAO  dao = FabricaDAO.instanciasDAOPago();
        Object response = dao.consultar(idPago);        
        return response;
    }
    
}
