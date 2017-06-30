/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica.Modulo1;


import Logica.Comando;
import BaseDatosDAO.*;
import BaseDatosDAO.Interfaces.IDAOUsuario;
import Dominio.Entidad;
import Services.Modulo1sResource;

/**
 *
 * @author Oswaldo
 */
public class ComandoIniciarSesion extends Comando{
    
    Entidad usuario;

    public ComandoIniciarSesion(Entidad usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void ejecutar(){
        IDAOUsuario dao = FabricaDAO.instanciasDaoUsuario();
        this.response = dao.obtenerInicioSesion(usuario);
        //return null;
    }
}
