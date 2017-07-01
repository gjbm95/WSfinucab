
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
public class ModificarFallidoException extends Exception {
    
    public ModificarFallidoException(String mensaje){
      super(mensaje);
    }
    
}
