/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo4;

import BaseDatosDAO.DAOCategoria;
import BaseDatosDAO.FabricaDAO;
import Dominio.Categoria;
import Dominio.Entidad;
import Logica.Comando;

/**
 *
 * @author MariPerez
 */
public class ComandoAgregarCategoria extends Comando {
     private Entidad categoria;
     
     public ComandoAgregarCategoria(Entidad categoria){
         this.categoria=categoria;
     }

    @Override
    public void ejecutar() {
        DAOCategoria dao = FabricaDAO.instanciasDaoCategoria();
        Entidad respuesta = dao.agregar(categoria);
        
        /*if(respuesta==1){
            System.out.println("Registro Exitoso");
                }
        else{System.out.println("Fallido");}
        */
        //return respuesta;
        
    }
    
}
