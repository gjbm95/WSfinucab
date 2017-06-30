/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo2;

import BaseDatosDAO.DaoTarjeta_Credito;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import Dominio.Tarjeta_Credito;
import Logica.Comando;
import Services.Modulo1sResource;
import Services.Modulo2sResource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;

/**
 *
 * @author AlejandroNegrin
 */
public class ComandoConsultarTDC extends Comando {

    private int user ;
 
    
    
    public ComandoConsultarTDC(int user) {
        this.user  = user;
    }

    
    
    @Override
    public void ejecutar() {

        DaoTarjeta_Credito dao = FabricaDAO.instanciasDaoTarjeta_Credito();
        //return dao.getTarjetasXUsuario(user);
      
    }
    
    
}
