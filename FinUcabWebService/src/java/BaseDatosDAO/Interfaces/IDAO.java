/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO.Interfaces;

import Dominio.Entidad;
import java.util.Dictionary;

/**
 *
 * @author William
 */
public interface IDAO {
    
    int Agregar(Entidad e);
    
    Entidad Modificar(Entidad e);
    
    Entidad Consultar(int id);
    
    Dictionary<Integer, Entidad> ConsultarTodos();
}
