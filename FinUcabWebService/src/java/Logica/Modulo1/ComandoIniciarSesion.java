/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica.Modulo1;


import Logica.Comando;
import BaseDatosDAO.*;
import Services.Modulo1sResource;

/**
 *
 * @author Oswaldo
 */
public class ComandoIniciarSesion extends Comando{
    
    String usuario, clave;

    public ComandoIniciarSesion(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    
    @Override
    public void ejecutar(){
        DaoUsuario dao = FabricaDAO.instanciasDaoUsuario();
        Modulo1sResource.resultado = dao.obtenerInicioSesion(usuario , clave);
        
    }
}
