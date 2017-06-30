/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Ramon
 */
public abstract class FinUCABException extends Exception{
    
    private int _code;
    private String _message;
    private Class _className;
    
    public FinUCABException(int code, String message, Class className){
        super();        
        this._code = code;
        this._message = message;
        this._className = className;
        
    }
    
    
}
