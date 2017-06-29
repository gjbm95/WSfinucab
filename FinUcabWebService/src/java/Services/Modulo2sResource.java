/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BaseDatosDAO.Conexion;
import Dominio.*;
import Logica.Comando;
import Logica.FabricaComando;
import java.io.StringReader;
import java.net.URLDecoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author AlejandroNegrin
 */
@Path("/Modulo2")
public class Modulo2sResource {

    @Context
    private UriInfo context;
    public static String resultado;

    /**
     * Creates a new instance of Modulo2sResource
     */
    public Modulo2sResource() {
    }

    /**
     * Función que atualiza los datos de un usuario.
     *
     * @return int 1 si se pudo actualizar, int 0 si no logro actualizar
     * @param String Json String con los atributos: u_id , u_uduario , u_nombre ,u_apellido, u_correo , u_pregunta ,
     * u_respuesta , u_password
     *
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/actualizarDatosUsuario")
    public String actualizarDatosUsuario(@QueryParam("datosUsuario") String datosCuenta) {

        String decodifico = URLDecoder.decode(datosCuenta);
        String resultado = "1";
//        String decodifico = "{ \"u_id\" : \"4\" , \"u_usuario\" : \"Eoeooeoe\" , \"u_nombre\" : \"Alejandro\""
//                + ", \"u_apellido\" : \"Negrin\", \"u_correo\" : \"aledavid21@hotmail.com\", "
//                + "\"u_pregunta\" : \"Nombre de mi mama\" , \"u_respuesta\" : \"/alejandra\", "
//                + "\"u_password\" : \"123456\" }";

        try {
            JsonObject usuarioJSON = this.stringToJSON(decodifico);
            Usuario usuario = new Usuario();
            usuario.jsonToUsuario(usuarioJSON);
            Comando command = FabricaComando.instanciarComandoActualizarDatosUsuario(usuario);
            command.ejecutar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado;
    }

    /**
     * Función que agrega una nueva Cuenta Bancaria para un Usuario
     *
     * @return int id de la nueva cuenta, 0 si no logro actualizar
     * @param String JSON String con los atributos: ct_tipocuenta , ct_numcuenta , ct_nombrebanco, ct_saldoactual ,
     * usuariou_id
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/agregarCuentaBancaria")
    public String agregarCuentaBancaria(@QueryParam("datosCuenta") String datosCuenta) {

        String decodifico = URLDecoder.decode(datosCuenta);
        Object resultado;
//        String decodifico = "{ \"ct_tipocuenta\" : \"4\" , \"ct_numcuenta\" : \"9900120\" , \"ct_nombrebanco\" : \"AND BANK\""
//                + ", \"ct_saldoactual\" : \"522\", \"usuariou_id\" : \"1\" }";

        try {
            JsonObject cuentaJSON = this.stringToJSON(decodifico);

            Cuenta_Bancaria cuenta = new Cuenta_Bancaria(cuentaJSON.getString("ct_tipocuenta"),
                    cuentaJSON.getString("ct_numcuenta"), cuentaJSON.getString("ct_nombrebanco"),
                    Float.parseFloat(cuentaJSON.getString("ct_saldoactual")), 0,
                    Integer.parseInt(cuentaJSON.getString("usuariou_id")));

            Comando command = FabricaComando.instanciarComandoAgregarCuenta(cuenta);
            resultado = command.ejecutar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado.toString();
    }

    /**
     * Función que actualiza o modifica los datos de una cuenta bancaria
     *
     * @return int 1 si se pudo actualizar, int 0 si no logro actualizar
     * @param String Json String con los atributos: ct_id , ct_tipocuenta , ct_numcuenta ,ct_nombrebanco, ct_saldoactual
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/actualizarCuentaBancaria")
    public String actualizarCuentaBancaria(@QueryParam("datosCuenta") String datosCuenta) {

        String decodifico = URLDecoder.decode(datosCuenta);
        String resultado = "1";
//        String decodifico = "{ \"ct_id\" : \"8\" , \"ct_tipocuenta\" : \"4\" , \"ct_numcuenta\" : \"15946\" ,"
//                + " \"ct_nombrebanco\" : \"OKOKN BANK\", \"ct_saldoactual\" : \"522\" , \"usuariou_id\" : \"1\"}";
       
        try {
            JsonObject cuentaJSON = this.stringToJSON(decodifico);
            Cuenta_Bancaria cuenta = new Cuenta_Bancaria(cuentaJSON.getString("ct_tipocuenta"),
                    cuentaJSON.getString("ct_numcuenta"), cuentaJSON.getString("ct_nombrebanco"),
                    Float.parseFloat(cuentaJSON.getString("ct_saldoactual")),
                    Integer.parseInt(cuentaJSON.getString("ct_id")),
                    Integer.parseInt(cuentaJSON.getString("usuariou_id")));
            Comando command = FabricaComando.instanciarComandoActualizarCuenta(cuenta);
            command.ejecutar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado;
    }

    /**
     * Función que agrega una nueva Cuenta Bancaria para un Usuario
     *
     * @return int id de la nueva cuenta, 0 si no logro actualizar
     * @param String JSON String con los atributos: ct_tipocuenta , ct_numcuenta , ct_nombrebanco, ct_saldoactual ,
     * usuariou_id
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eliminarCuentaBancaria")
    public String eliminarCuentaBancaria(@QueryParam("idCuenta") String idCuenta) {

        String decodifico = URLDecoder.decode(idCuenta);
        Object resultado = "1";
//        String decodifico = "3";

        try {
            int id = Integer.parseInt(decodifico);

            Comando command = FabricaComando.instanciarComandoEliminarCuenta(id);
            resultado = command.ejecutar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado.toString();
    }

    /**
     * Función que agrega una nueva Tarjet de Crédito para un Usuario
     *
     * @return int id de la nueva cuenta, 0 si no logro actualizar
     * @param String JSON String con los atributos: tc_tipo , tc_fechavencimiento (en formato DD/MM/YYYY) , tc_numero,
     * tc_saldo ,usuariou_id
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/agregarTDC")
    public String agregarTDC(@QueryParam("datosTDC") String datosTDC) {

       String decodifico = URLDecoder.decode(datosTDC);
        Object resultado;
//        String decodifico = "{ \"tc_tipo\" : \"4\" , \"tc_fechavencimiento\" : \"21/11/1995\" ,"
//                + " \"tc_numero\" : \"12234\""
//                + ", \"tc_saldo\" : \"522\", \"usuariou_id\" : \"1\" }";

        try {
            JsonObject tdcJSON = this.stringToJSON(decodifico);

            Tarjeta_Credito tdc = new Tarjeta_Credito(tdcJSON.getString("tc_tipo"),
                    tdcJSON.getString("tc_fechavencimiento"), tdcJSON.getString("tc_numero"),
                    Float.parseFloat(tdcJSON.getString("tc_saldo")), 0,
                    Integer.parseInt(tdcJSON.getString("usuariou_id")));

            Comando command = FabricaComando.instanciarComandoAgregarTDC(tdc);
            resultado = command.ejecutar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado.toString();
    }

    /**
     * Función que actualiza o modifica los datos de una Tarjeta de crédito
     *
     * @return int 1 si se pudo actualizar, int 0 si no logro actualizar
     * @param String JSON String con los atributos: tc_id , tc_tipo , tc_fechavencimiento (en formato DD/MM/YYYY) ,
     * tc_numero, tc_saldo ,usuariou_id
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/actualizarTDC")
    public String actualizarTDC(@QueryParam("datosTDC") String datosTDC) {

        String decodifico = datosTDC;
        String resultado = "1";
//        String decodifico = "{ \"tc_id\" : \"1\" , \"tc_tipo\" : \"4\" , \"tc_fechavencimiento\" : \"21/11/1995\" ,"
//                + " \"tc_numero\" : \"12234\""
//                + ", \"tc_saldo\" : \"522\", \"usuariou_id\" : \"1\" }";
        try {
            JsonObject tdcJSON = this.stringToJSON(decodifico);

            Tarjeta_Credito tdc = new Tarjeta_Credito(tdcJSON.getString("tc_tipo"),
                    tdcJSON.getString("tc_fechavencimiento"), tdcJSON.getString("tc_numero"),
                    Float.parseFloat(tdcJSON.getString("tc_saldo")),
                    Integer.parseInt(tdcJSON.getString("tc_id")),
                    Integer.parseInt(tdcJSON.getString("usuariou_id")));

            Comando command = FabricaComando.instanciarComandoActualizarTDC(tdc);
            command.ejecutar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado;
    }

    /**
     * Función que agrega una nueva Cuenta Bancaria para un Usuario
     *
     * @return int id de la nueva cuenta, 0 si no logro actualizar
     * @param String JSON String con los atributos: ct_tipocuenta , ct_numcuenta , ct_nombrebanco, ct_saldoactual ,
     * usuariou_id
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eliminarTDC")
    public String eliminarTDC(@QueryParam("idtdc") String idtdc) {

//        String decodifico = URLDecoder.decode(idtdc);
        Object resultado = "1";
        String decodifico = idtdc;

        try {
            int id = Integer.parseInt(decodifico);

            Comando command = FabricaComando.instanciarComandoEliminarTDC(id);
            resultado = command.ejecutar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado.toString();
    }

    /**
     * Función que busca todas las tarjetas de credito de un usuario
     *
     * @return JsonToString compuesto de JsonArrays de cada tarjeta
     * @param String id del usuario
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/consultarTDC")
    public String consultarTDC(@QueryParam("idUsuario") String idUsuario) {

        String decodifico = idUsuario;
        Object resultado = "1";
//        String decodifico = "1";

        try {
            int id = Integer.parseInt(decodifico);

            Comando command = FabricaComando.instanciarComandoConsultarTDC(id);
            resultado = command.ejecutar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        System.out.println(resultado.toString());
        return resultado.toString();
    }

    /**
     * Función que busca todas las tarjetas de credito de un usuario
     *
     * @return JsonToString compuesto de JsonArrays de cada tarjeta
     * @param String id del usuario
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/consultarCuentas")
    public String consultarCuentas(@QueryParam("idUsuario") String idUsuario) {

        String decodifico = URLDecoder.decode(idUsuario);
        Object resultado = "1";
//        String decodifico = "1";

        try {
            int id = Integer.parseInt(decodifico);
          
            Comando command = FabricaComando.instanciarComandoConsultarCuentas(id);
            resultado = command.ejecutar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado.toString();
    }

    /**
     * Función que busca todas las tarjetas de credito de un usuario
     *
     * @return JsonToString compuesto de JsonArrays de cada tarjeta
     * @param String id del usuario
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/consultarEstadisticas")
    public String consultarEstadisticas(@QueryParam("idUsuario") String idUsuario) {

        String decodifico = URLDecoder.decode(idUsuario);
        Object resultado = "1";
        try {
            int id = Integer.parseInt(decodifico);
          
            Comando command = FabricaComando.instanciarComandoConsultarEstadisticas(id);
            resultado = command.ejecutar();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado.toString();
    }

    /**
     * Funcion que convierte un string con estructura JSON en JsonObject
     *
     * @param decodifico String con estructura json
     * @return JsonObject del string
     */
    private JsonObject stringToJSON(String decodifico) {
        JsonReader reader = Json.createReader(new StringReader(decodifico));
        JsonObject jsonObj = reader.readObject();
        reader.close();
        return jsonObj;
    }

}
