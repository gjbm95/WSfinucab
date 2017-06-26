/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo2;

import BaseDatosDAO.DaoCuenta_Bancaria;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import Dominio.Cuenta_Bancaria;
import Dominio.Usuario;
import Logica.Comando;
import Services.Modulo1sResource;
import Services.Modulo2sResource;
import javax.json.JsonObject;

/**
 *
 * @author AlejandroNegrin
 */
public class ComandoConsultarCuentas extends Comando {

    private int idusuario ;
 
    
    
    public ComandoConsultarCuentas(int idusuario) {
        this.idusuario  = idusuario;
    }

    
    
    @Override
    public Object ejecutar() {

        DaoCuenta_Bancaria dao = FabricaDAO.instanciasDaoCuenta_Bancaria();
        return dao.getCuentasXUsuario(idusuario);
    }
    
    
}
