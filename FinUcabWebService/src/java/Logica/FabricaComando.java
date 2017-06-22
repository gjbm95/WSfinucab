/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.Usuario;
import Logica.Modulo1.*;
import Logica.Modulo2.ComandoActualizarDatosUsuario;

/**
 *
 * @author Oswaldo
 */
public class FabricaComando {
    
    public static ComandoIniciarSesion instanciarComandoIniciarSesion(String usuario,String clave){
        return new ComandoIniciarSesion(usuario, clave);
    }
    
     public static ComandoVerificarUsuario instanciarComandoVerificarUsuario(String usuario){
        return new ComandoVerificarUsuario(usuario);
    }
     
     public static ComandoRecuperarClave instanciarComandoRecuperarClave(String usuario){
        return new ComandoRecuperarClave(usuario);
    }
     
     public static ComandoActualizarClave instanciarComandoActualizarClave(String usuario,String clave){
        return new ComandoActualizarClave(usuario, clave);
    }
     
     public static ComandoRegistrarUsuario instanciarComandoRegistrarUsuario(Usuario usuario){
        return new ComandoRegistrarUsuario(usuario);
    }
    
     public static ComandoActualizarDatosUsuario instanciarComandoActualizarDatosUsuario(Usuario usuario){
        return new ComandoActualizarDatosUsuario(usuario);
    }
    
}
