/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO.Interfaces;

import Dominio.Entidad;

/**
 *
 * @author Junior
 */
public interface IDAOUsuario extends IDAO {
    
    public Entidad ActualizarClave(Entidad entidad);
    
    public Entidad verificarUsuario(String usuario);
    
    public Entidad obtenerInicioSesion(Entidad usuario);
    
    public Entidad obtenerXRecuperarClave(String usuario);
 
}
