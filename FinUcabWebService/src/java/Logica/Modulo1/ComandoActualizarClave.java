/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo1;

import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOUsuario;
import Dominio.Entidad;
import Logica.Comando;
import Services.Modulo1sResource;

/**
 *
 * @author Oswaldo
 */
public class ComandoActualizarClave extends Comando{
    Entidad usuario;

    public ComandoActualizarClave(Entidad usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void ejecutar(){
        IDAOUsuario dao = FabricaDAO.instanciasDaoUsuario();
        this.response = dao.ActualizarClave(usuario);
    }
}
