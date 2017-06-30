/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo1;

import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOUsuario;
import Dominio.Entidad;
import Dominio.Usuario;
import Logica.Comando;
import Services.Modulo1sResource;

/**
 *
 * @author Oswaldo
 */
public class ComandoRegistrarUsuario extends Comando{
    
    Entidad usuario;

    public ComandoRegistrarUsuario(Entidad usuario) {
        this.usuario = usuario;
    }
    
    
    public void ejecutar(){
        IDAOUsuario dao = FabricaDAO.instanciasDaoUsuario();
        this.response = dao.agregar(usuario);
        
        /*if(respuesta == 1){
            Modulo1sResource.resultado = "1";
        }else {
            Modulo1sResource.resultado = "0";
        }
*/
        //return null;
    }
}
