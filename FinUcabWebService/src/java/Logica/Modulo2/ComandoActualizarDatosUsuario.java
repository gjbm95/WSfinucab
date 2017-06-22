/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo2;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Modulo1DAO;
import BaseDatosDAO.Modulo2DAO;
import Dominio.Usuario;
import Logica.Comando;
import Services.Modulo1sResource;
import Services.Modulo2sResource;
import javax.json.JsonObject;

/**
 *
 * @author AlejandroNegrin
 */
public class ComandoActualizarDatosUsuario extends Comando {

    Usuario user ;
    
    
    public ComandoActualizarDatosUsuario(Usuario user) {
        this.user  = user;
    }

    @Override
    public void ejecutar() {
        Modulo2DAO daoUsuario = FabricaDAO.instanciasDaoUsuario();
        daoUsuario.modificar(user);
    }
    
    
}
