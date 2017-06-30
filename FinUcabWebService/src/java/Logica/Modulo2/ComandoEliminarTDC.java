/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo2;

import BaseDatosDAO.DaoTarjeta_Credito;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import Dominio.FabricaEntidad;
import Dominio.Usuario;
import Logica.Comando;
import Services.Modulo1sResource;
import Services.Modulo2sResource;
import javax.json.JsonObject;

/**
 *
 * @author AlejandroNegrin
 */
public class ComandoEliminarTDC extends Comando {

    private int tdc ;
 
    
    
    public ComandoEliminarTDC(int tdc) {
        this.tdc  = tdc;
    }

    
    
    @Override
    public void ejecutar() {

        DaoTarjeta_Credito daoTarjeta_credito = FabricaDAO.instanciasDaoTarjeta_Credito();
        super.response = FabricaEntidad.obtenerSimpleResponse(daoTarjeta_credito.eliminar(tdc));
    }
    
    
}
