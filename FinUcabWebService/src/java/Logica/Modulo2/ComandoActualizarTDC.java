/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo2;

import BaseDatosDAO.DaoTarjeta_Credito;
import BaseDatosDAO.FabricaDAO;
import Dominio.Tarjeta_Credito;
import Logica.Comando;
import Services.Modulo1sResource;
import Services.Modulo2sResource;
import javax.json.JsonObject;

/**
 *
 * @author AlejandroNegrin
 */
public class ComandoActualizarTDC extends Comando {

    private Tarjeta_Credito tdc ;
 
    
    
    public ComandoActualizarTDC(Tarjeta_Credito tdc) {
        this.tdc  = tdc;
    }

    
    
    @Override
    public void ejecutar() {

        DaoTarjeta_Credito daoTarjeta_Credito = FabricaDAO.instanciasDaoTarjeta_Credito();
        //return daoTarjeta_Credito.modificar(tdc);
    }
    
    
}
