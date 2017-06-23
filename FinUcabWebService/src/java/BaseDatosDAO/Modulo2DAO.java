/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

/**
 *
 * @author Junior
 */
public abstract class Modulo2DAO <T> {
    
    
    public abstract String agregar(T obj);
    
    public abstract String modificar(T obj);
    
    public abstract String consultarTodos(T obj);
    
    public abstract String eliminar(T obj);
    
}
