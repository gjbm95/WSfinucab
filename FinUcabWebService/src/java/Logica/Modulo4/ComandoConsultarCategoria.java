/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo4;

import BaseDatosDAO.DAO;
import BaseDatosDAO.FabricaDAO;
import Dominio.Categoria;
import Logica.Comando;

/**
 *
 * @author Jeffrey
 */

public class ComandoConsultarCategoria extends Comando{
    private int idcategoria;
    
    public ComandoConsultarCategoria(int categoria){
        this.idcategoria = categoria;
    }

    @Override
    public Object ejecutar() {
     
        DAO dao = FabricaDAO.instanciasDaoCategoria();
        Object response = dao.consultar(idcategoria);
        return response;
        
    }
    
    
}
