/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BaseDatosDAO.Conexion;
import Dominio.Usuario;
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
     *
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/actualizarDatosUsuario")
    public String actualizarDatosUsuario() {
//@QueryParam("datosUsuario") String datosCuenta
//        String decodifico = URLDecoder.decode(datosCuenta);
        String decodifico = "{ \"u_id\" : \"4\" , \"u_usuario\" : \"ARROZ\" , \"u_nombre\" : \"Alejandro\""
                + ", \"u_apellido\" : \"Negrin\", \"u_correo\" : \"aledavid21@hotmail.com\", "
                + "\"u_pregunta\" : \"Nombre de mi mama\" , \"u_respuesta\" : \"/alejandra\", "
                + "\"u_password\" : \"123456\" }";

        try {
            JsonObject usuarioJSON = this.stringToJSON(decodifico);
            System.out.println(usuarioJSON.toString());
            Usuario usuario = new Usuario();
            usuario.jsonToUsuario(usuarioJSON);
            System.out.println("USUARIO: "+usuario.getApellido());
            Comando command = FabricaComando.instanciarComandoActualizarDatosUsuario(usuario);
            command.ejecutar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "falle";
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
