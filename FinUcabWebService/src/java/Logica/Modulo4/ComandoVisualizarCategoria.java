/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo4;

import BaseDatosDAO.DAOCategoria;
import BaseDatosDAO.FabricaDAO;
import Dominio.Entidad;
import Logica.Comando;

/**
 *
 * @author MariPerez
 */
public class ComandoVisualizarCategoria extends Comando {
         private String usuario;
     
     public ComandoVisualizarCategoria(String usuario){
         this.usuario=usuario;
     }

    @Override
    public Object ejecutar() {
        DAOCategoria dao = FabricaDAO.instanciasDaoCategoria();
        int idUsuario = 1;
        dao.consultarTodos(idUsuario);
        
        return null;
    }
    
}
