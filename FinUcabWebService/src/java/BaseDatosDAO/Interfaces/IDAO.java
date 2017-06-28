/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO.Interfaces;

import Dominio.Entidad;
import Dominio.ListaEntidad;
import java.util.ArrayList;
import java.util.Dictionary;

/**
 *
 * @author William
 */
public interface IDAO {
    
    Entidad agregar(Entidad e);
    
    Entidad modificar(Entidad e);
    
    Entidad consultar(int id);
    
    ListaEntidad consultarTodos(int idUsuario);
}
