/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo4;

import BaseDatosDAO.DAO;
import BaseDatosDAO.DAOCategoria;
import BaseDatosDAO.FabricaDAO;
import Logica.Comando;

/**
 *
 * @author Jeffrey
 */
public class ComandoEliminarCategoria extends Comando {
    private int idCategoria;
    
    public ComandoEliminarCategoria(int idcategoria){
        this.idCategoria = idcategoria;
    }        

    @Override
    public Object ejecutar() {
        
        DAOCategoria dao = FabricaDAO.instanciasDaoCategoria();
        Object response = dao.eliminarCategoria(idCategoria);
        return response;
    
    }
    
}
