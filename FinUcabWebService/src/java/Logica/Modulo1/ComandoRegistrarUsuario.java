/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo1;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Modulo1DAO;
import Dominio.Usuario;
import Logica.Comando;
import Services.Modulo1sResource;

/**
 *
 * @author Oswaldo
 */
public class ComandoRegistrarUsuario extends Comando{
    
    Usuario usuario;

    public ComandoRegistrarUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public void ejecutar(){
        Modulo1DAO dao = FabricaDAO.instanciarModulo1Dao();
        int respuesta = dao.agregarDatos(usuario);
        if(respuesta == 1){
            Modulo1sResource.resultado = "1";
        }else if(respuesta == 0){
            Modulo1sResource.resultado = "0";
        }
        
    }
}
