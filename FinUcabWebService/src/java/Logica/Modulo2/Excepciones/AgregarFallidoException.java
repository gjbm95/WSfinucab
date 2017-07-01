
package Logica.Modulo2.Excepciones;
/**
*Modulo 2 - Modulo de Home
*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Metodos del servicio web destinados para las funcionalidades de Home y 
* Tarjetas de Credito y Cuentas Bancarias. 
*
**/
public class AgregarFallidoException extends Exception {
    
    
    public AgregarFallidoException(String mensaje){
       super(mensaje);
    }
    
}
