/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo2;

import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Modulo1DAO;
import Dominio.Usuario;
import Logica.Comando;
import Services.Modulo1sResource;
import Services.Modulo2sResource;
import javax.json.JsonObject;

/**
 *
 * @author AlejandroNegrin
 */
public class ComandoAgregarTDC extends Comando {

    private Usuario user ;
 
    
    
    public ComandoAgregarTDC(Usuario user) {
        this.user  = user;
    }

    
    
    @Override
    public Object ejecutar() {

        DaoUsuario daoUsuario = FabricaDAO.instanciasDaoUsuario();
        return daoUsuario.modificar(user);
    }
    
    
}
