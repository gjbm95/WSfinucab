/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.Cuenta_Bancaria;
import Dominio.Usuario;
import Logica.Modulo1.*;
import Logica.Modulo2.*;

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
    
     public static ComandoAgregarCuenta instanciarComandoAgregarCuenta(Cuenta_Bancaria cuenta){
        return new ComandoAgregarCuenta(cuenta);
    }
    
     public static ComandoActualizarCuenta instanciarComandoActualizarCuenta(Cuenta_Bancaria cuenta){
        return new ComandoActualizarCuenta(cuenta);
    }
    
     public static ComandoEliminarCuenta instanciarComandoEliminarCuenta(int id){
        return new ComandoEliminarCuenta(id);
    }
    
     /*---------------------------     PAGOS      ------------------------------------*/
     /**
      * Fabrica inicializadora del ComandoListarPagos
      * @param idUsuario
      * @return 
      */
     public static ComandoListarPagos instanciarComandoListarPagos(int idUsuario){
        return new ComandoListarPagos(id);
    }
     
     
     /*---------------------------     /PAGOS      ------------------------------------*/
}
