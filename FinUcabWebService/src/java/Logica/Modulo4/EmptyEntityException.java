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
public class EmptyEntityException extends Exception{
    
    public EmptyEntityException(){
        super();
    };
    

    

    public String EmptyEntity(){
        return "Error se tiene una Entidad Vacia";
    }
    
    
    
}