/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.Entidad;
import Logica.Modulo1.*;
import Logica.Modulo2.ComandoActualizarDatosUsuario;
import Logica.Modulo4.ComandoAgregarCategoria;
import Logica.Modulo4.ComandoVisualizarCategoria;
import Dominio.Cuenta_Bancaria;
import Dominio.Tarjeta_Credito;
import Dominio.Usuario;
import Logica.Modulo2.*;
import Logica.Modulo5.ComandoAgregarPago;
import Logica.Modulo5.ComandoConsultarPago;
import Logica.Modulo5.ComandoListarPagos;
import Logica.Modulo5.ComandoModificarPago;


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
     
     public static ComandoAgregarCategoria instanciarComandoAgregarCategoria(Entidad categoria){
        return new ComandoAgregarCategoria(categoria);
    }
     
     public static ComandoVisualizarCategoria instanciarComandoVisualizarCategoria(int usuario){
         return new ComandoVisualizarCategoria(usuario);
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
      * @param idUsuario Identificador de usuario en base de datos
      * @return 
      */
     public static ComandoListarPagos instanciarComandoListarPagos(int idUsuario){
        return new ComandoListarPagos(idUsuario);
    }
    
     /**
      * Fabrica inicializadora del ComandoAgregarPago
      * @param pago 
      * @return 
      */
     public static ComandoAgregarPago instanciarComandoAgregarPago(Entidad pago){
        return new ComandoAgregarPago(pago);
     }
     
     /**
      * Fabrica inicializadora del ComandoAgregarPago
      * @param pago 
      * @return 
      */
     public static ComandoModificarPago instanciarComandoModificarPago(Entidad pago){
        return new ComandoModificarPago(pago);
     }
     
     /**
      * Fabrica inicializadora del ComandoConsultarPago
      * @param idPago Identificador de pago en base de datos
      * @return 
      */
      public static ComandoConsultarPago instanciarComandoConsultarPago(int idPago){
        return new ComandoConsultarPago(idPago);
    }
     
     /*---------------------------   Gestion de Cuentas y Tarjetas      ------------------------------------*/

     public static ComandoAgregarTDC instanciarComandoAgregarTDC(Tarjeta_Credito tdc){
        return new ComandoAgregarTDC(tdc);
    }
    
     public static ComandoActualizarTDC instanciarComandoActualizarTDC(Tarjeta_Credito tdc){
        return new ComandoActualizarTDC(tdc);
    }
    
     public static ComandoEliminarTDC instanciarComandoEliminarTDC(int id){
        return new ComandoEliminarTDC(id);
    }
    
     public static ComandoConsultarTDC instanciarComandoConsultarTDC(int id){
        return new ComandoConsultarTDC(id);
    }
    
     public static ComandoConsultarCuentas instanciarComandoConsultarCuentas(int id){
        return new ComandoConsultarCuentas(id);
    }

}
