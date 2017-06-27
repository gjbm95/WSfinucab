/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo5;

import BaseDatosDAO.DAOCategoria;
import BaseDatosDAO.DAOPago;
import BaseDatosDAO.FabricaDAO;
import Dominio.Entidad;
import Logica.Comando;

/**
 *
 * @author Juan
 */
public class ComandoAgregarPago extends Comando {

    private Entidad pago;
     
     public ComandoAgregarPago(Entidad categoria){
         this.pago=pago;
     }

    @Override
    public Object ejecutar() {
        DAOPago dao = FabricaDAO.instanciasDAOPago();
        int respuesta = dao.agregar(pago);
        if(respuesta==1){
            System.out.println("Registro Exitoso");
                }
        else{System.out.println("Fallido");}
        
        return null;
        
    }
    
}
