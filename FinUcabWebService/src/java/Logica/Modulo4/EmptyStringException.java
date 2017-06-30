/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo4;



/**
 *
 * @author Jeffrey
 */
public class EmptyStringException extends Exception{
    public EmptyStringException(){
        super();
    };
    
    
    public String EmptyString(){
        return "Error se tiene una Cadena Vacia";
    }
    
}