/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo5;

import BaseDatosDAO.DAOPago;
import BaseDatosDAO.FabricaDAO;
import Dominio.Entidad;
import Logica.Comando;

/**
 *
 * @author Juan
 */
public class ComandoModificarPago extends Comando{
    
   private Entidad pago;
     
     public ComandoModificarPago(Entidad pago){
         this.pago=pago;
     }
    
    

 @Override
    public void ejecutar() {
        DAOPago dao = FabricaDAO.instanciasDAOPago();
        Entidad respuesta = dao.modificar(pago);
        /*
        if (respuesta != null)
        return respuesta;
        
        else
            return "Modificacion no exitosa";
*/
        
    }
    
}
