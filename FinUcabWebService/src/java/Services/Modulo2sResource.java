/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BaseDatosDAO.Conexion;
import java.io.StringReader;
import java.net.URLDecoder;
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

    /**
     * Creates a new instance of Modulo2sResource
     */
    public Modulo2sResource() {
    }

 

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/actualizarDatosUsuario")
    public String actualizarDatosUsuario(@QueryParam("datosUsuario") String datosCuenta) {

        String decodifico = URLDecoder.decode(datosCuenta);
        
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            JsonObject usuarioJSON = this.stringToJSON(decodifico);
            String query = "NSERT INTO usuario ( u_usuario , u_nombre , u_apellido , u_correo , u_pregunta , u_respuesta , u_password ) "
                    + "VALUES ( '" + usuarioJSON.getString("u_usuario") + "' , '" + usuarioJSON.getString("u_nombre") + "' , "
                    + "'" + usuarioJSON.getString("u_apellido") + "' , '" + usuarioJSON.getString("u_correo") + "' , "
                    + "'" + usuarioJSON.getString("u_pregunta") + "' , '" + usuarioJSON.getString("u_respuesta") + "' , "
                    + "'" + usuarioJSON.getString("u_password") + "' );";
            
            query = "UPDATE usuario set ";
            if (st.executeUpdate(query) > 0) {
                st.close();
                return "Registro exitoso";
            } else {
                st.close();
                return "No se pudo registrar";
            }

        } catch (Exception e) {

            return e.getMessage();

        }
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
