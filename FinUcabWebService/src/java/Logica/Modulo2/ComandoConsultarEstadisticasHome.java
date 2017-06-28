/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo2;

import BaseDatosDAO.DAOPago;
import BaseDatosDAO.DaoCuenta_Bancaria;
import BaseDatosDAO.DaoPlanificacion;
import BaseDatosDAO.DaoPresupuesto;
import BaseDatosDAO.DaoTarjeta_Credito;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOUsuario;
import Dominio.Cuenta_Bancaria;
import Dominio.Pago;
import Dominio.Planificacion;
import Dominio.Presupuesto;
import Dominio.Usuario;
import Logica.Comando;
import Services.Modulo1sResource;
import Services.Modulo2sResource;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author AlejandroNegrin
 */
public class ComandoConsultarEstadisticasHome extends Comando {

    private Pago pago;
    private Planificacion plan;
    private String saldoCuenta;
    private String saldoTarjetas;
    private Presupuesto presupuesto;
    private int idusuario;
    private String result;

    public ComandoConsultarEstadisticasHome(int idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public Object ejecutar() {

        DaoCuenta_Bancaria daoCueta = FabricaDAO.instanciasDaoCuenta_Bancaria();
        DaoTarjeta_Credito daotcd = FabricaDAO.instanciasDaoTarjeta_Credito();
        DaoPresupuesto daopre = FabricaDAO.instanciasDAOPresupuesto();
        DaoPlanificacion daopla = FabricaDAO.instanciasDAOPlanificacion();
        DAOPago daoPago = FabricaDAO.instanciasDAOPago();
        JsonObjectBuilder saldoBuilder = Json.createObjectBuilder();

        // construyo primera parte del json
        saldoCuenta = daoCueta.getSaldoTotal(this.idusuario);
        saldoTarjetas = daotcd.getSaldoTotal(this.idusuario);
        saldoBuilder.add("est_id", "1");
        saldoBuilder.add("est_saldocuenta", saldoCuenta);
        saldoBuilder.add("est_saldotarjeta", saldoTarjetas);
        JsonObject jsonObject1 = saldoBuilder.build();
        
        //busco la segunda parte del json (balance)
        JsonObject jsonObject2 = daoPago.getBalance(idusuario);
        //busco la tercera  parte del json (ultimos pagos)
        JsonArray jsonObject3 = daoPago.getUltimosPagosXUsuario(idusuario);
        //busco la cuarta parte del json (ultimas planificaciones)
        JsonArray jsonObject4 = daopla.getUltimasPlanificaciones(idusuario);
        //busco la quinta parte del json (ultimos presupuestos)
        JsonArray jsonObject5 = daopre.getUltimosPresupuestos(idusuario);
        
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        if (jsonObject1!=null) 
        arrayBuilder.add(jsonObject1);
        if (jsonObject2!=null)
        arrayBuilder.add(jsonObject2);
        if (jsonObject3!=null)
        arrayBuilder.add(jsonObject3);
        if (jsonObject4!=null)
        arrayBuilder.add(jsonObject4);
        if (jsonObject5!=null)
        arrayBuilder.add(jsonObject5);
        
        JsonArray array = arrayBuilder.build();
        return array.toString();
    }

}
