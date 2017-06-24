/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class FabricaEntidad {
    
    public static Usuario obtenerUsuario(){
     return new Usuario(); 
    }
    
    public static Usuario obtenerUsuario(int idusuario, String nombre, 
       String apellido, String correo, 
       String usuario, String contrasena, String pregunta, String respuesta, 
       ArrayList<Cuenta_Bancaria> cuentas, ArrayList<Planificacion> planes){
    
       return new Usuario (idusuario,nombre,apellido,correo,usuario,contrasena, 
       pregunta,respuesta,cuentas,planes);   
        
    }
    
    public static Cuenta_Bancaria obtenerCuentaBancaria(String _tipoCuenta, 
       String _numcuenta,String _nombreBanco, float _saldoActual, int id){
       return 
      new Cuenta_Bancaria(_tipoCuenta,_numcuenta,_nombreBanco,_saldoActual,id); 
    }
    
    public static Cuenta_Bancaria obtenerCuentaBancaria(){
     return new Cuenta_Bancaria(); 
    }
    
    public static Tarjeta_Credito obtenerTarjetaCredito(){
     return new Tarjeta_Credito(); 
    }
    
    public static Tarjeta_Credito obtenerTarjetaCredito(String _tipotdc, 
            String _fechaven, String _numero,float _saldo, int id){
       return 
      new Tarjeta_Credito(_tipotdc,_fechaven,_numero,_saldo,id); 
    }
    
}
