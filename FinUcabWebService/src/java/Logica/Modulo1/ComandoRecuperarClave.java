/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo1;

import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import Logica.Comando;
import Services.Modulo1sResource;

/**
 *
 * @author Oswaldo
 */
public class ComandoRecuperarClave extends Comando{
    
    String usuario;

    public ComandoRecuperarClave(String usuario) {
        this.usuario = usuario;
    }
    
    
    @Override
    public Object ejecutar(){
        DaoUsuario dao = FabricaDAO.instanciasDaoUsuario();
        Modulo1sResource.resultado = dao.obtenerXRecuperarClave(usuario);
        return null;
    }
}
