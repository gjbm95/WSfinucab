/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import BaseDatosDAO.Conexion;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.Pago;
import Logica.Comando;
import Logica.FabricaComando;
import java.io.StringReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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
@Path("/Modulo5")
public class Modulo5sResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Modulo5sResource
     */
    public Modulo5sResource() {
    }

    /**
     * Retrieves representation of an instance of Services.Modulo5sResource
     * @return an instance of javax.json.Json
     */
   @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/prueba")
    public String getPruebaJson() {
        //TODO return proper representation object
        JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
        usuarioBuilder.add("Nombre","Jose");
        usuarioBuilder.add("Apellido","Rodriguez");
        usuarioBuilder.add("Usuario","jose123");
        JsonObject usuarioJsonObject = usuarioBuilder.build();
       return usuarioJsonObject.toString();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/pruebaDB")
    public String getPruebaDataBase() {
        //TODO return proper representation object
        String respuesta ="";
        try{

            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Usuario;");
            while (rs.next())
            {
                //Creo el objeto Json!             
                 JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                 usuarioBuilder.add("Nombre",rs.getString(3));
                 usuarioBuilder.add("Apellido",rs.getString(4));
                 usuarioBuilder.add("Usuario",rs.getString(2));
                 JsonObject usuarioJsonObject = usuarioBuilder.build();  
                 respuesta = usuarioJsonObject.toString();
            }
            rs.close();
            st.close();

            return respuesta;
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
    
    /**
     * Función que registra un pago en la base de datos.
     *
     * @param datosPago JSON.toString() con los atributos: pg_monto, pg_tipoTransaccion,
     * pg_categoria , pg_descripcion
     *
     * @return Si se inserta el pago devuelve un String con el mensaje
     * "Registro Exitoso", De lo contrario devuelve el mensaje "No se pudo
     * registrar"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarPago")
    public String registrarPago(@QueryParam("datosPago") String datosPagos) {
          Object resultado;
          String decodifico = URLDecoder.decode(datosPagos);

        try {
           
          JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject pagoJSON = reader.readObject();
           
            reader.close();
            Entidad e = FabricaEntidad.obtenerPago(pagoJSON.getInt("pg_categoria"), pagoJSON.getString("pg_descripcion"), pagoJSON.getInt("pg_monto"), pagoJSON.getString("pg_tipoTransaccion")) ;
            Comando command = FabricaComando.instanciarComandoAgregarPago(e);
            resultado = command.ejecutar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado.toString();
    }
    
    /**
     * Función que consulta un pago seleccionado en la base de datos.
     *
     * @param consultarPago con los atributos: pg_monto, pg_tipoTransaccion,
     * pg_categoria , pg_descripcion
     *
     * @return Si encuentra el pago devuelve los datos del pago.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/consultarPago")
    public String consultarPago(@QueryParam("datosPago") int idPago) {

        System.out.println(idPago);
        String respuesta ="";

        try {
            
            Comando c = FabricaComando.instanciarComandoConsultarPago(idPago);
            Object objectResponse = c.ejecutar();
            
            if (objectResponse != null ){
                
                
                
                //ArrayList<Pago> lista = (ArrayList<Pago>) objectResponse;
                JsonObjectBuilder pagoBuilder = Json.createObjectBuilder();
                //JsonArrayBuilder list = Json.createArrayBuilder();
                
                Pago pago = (Pago) objectResponse;
                //for (Pago pago : lista) {          
                 pagoBuilder.add("pg_id",pago.getIdPago());
                 pagoBuilder.add("pg_monto",pago.getTotal());
                 pagoBuilder.add("pg_tipoTransaccion",pago.getTipo());
                 pagoBuilder.add("pg_categoria",pago.getCategoria());
                 pagoBuilder.add("pg_descripcion",pago.getDescripcion());
                JsonObject pagoJsonObject = pagoBuilder.build(); 
                 respuesta = pagoJsonObject.toString();
            
                
                
                
           

            return respuesta;
            }

        } catch (Exception e) {

            return e.getMessage();

        }
         return "Error";
    }

   
    /**
     * Función que visualiza los pagos.
     *
     * @param visualizarPago con los atributos: pg_id, pg_monto, pg_tipoTransaccion,
     * pg_categoria , pg_descripcion
     *
     * @return Lista de pagos por Usuario
     */
     @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/visualizarPago")
    public String visualizarPago(@QueryParam("datosPago") int idUsuario) {
        
        try{
            
            Comando c = FabricaComando.instanciarComandoListarPagos(idUsuario);
            Object objectResponse = c.ejecutar();
            
            if (objectResponse != null ){
                
                ArrayList<Pago> lista = (ArrayList<Pago>) objectResponse;
                JsonObjectBuilder pagoBuilder = Json.createObjectBuilder();
                JsonArrayBuilder list = Json.createArrayBuilder();
                
                for (Pago pago : lista) {
                    
                    pagoBuilder.add("pg_id",pago.getIdPago());
                    pagoBuilder.add("pg_monto",pago.getTotal());
                    pagoBuilder.add("pg_tipoTransaccion",pago.getTipo());
                    pagoBuilder.add("pg_categoria",pago.getCategoria());
                    pagoBuilder.add("pg_descripcion",pago.getDescripcion());
                    JsonObject pagoJsonObject = pagoBuilder.build();  

                    list.add( pagoJsonObject.toString());
                    
                }
                
                JsonArray listJsonObject = list.build();
                String resp = listJsonObject.toString();
                
                return resp;
            }

        }
        catch(Exception e) {
            return e.getMessage();
        }
        
        return "Error";
    }
      
    /**
     * Función que modificar un pago.
     *
     * @param modificararPago con los atributos: pg_id, pg_monto, pg_tipoTransaccion,
     * pg_categoria , pg_descripcion
     *
     * @return Lista de pagos por Usuario
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/modificarPago")
    public String modificarPago(@QueryParam("datosPago") String datosPagos) {
        
        
          Object resultado;
          String decodifico = URLDecoder.decode(datosPagos);

        try {
           
          JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject pagoJSON = reader.readObject();
           
            reader.close();
            Entidad e = FabricaEntidad.obtenerPago(pagoJSON.getInt("pg_categoria"), pagoJSON.getString("pg_descripcion"), pagoJSON.getInt("pg_monto"), pagoJSON.getString("pg_tipoTransaccion")) ;
            Comando command = FabricaComando.instanciarComandoModificarPago(e);
            resultado = command.ejecutar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "0";
        }
        return resultado.toString();
    }
    
    
    /**
     * POST method for creating an instance of Modulo5Resource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(Json content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public Modulo5Resource getModulo5Resource(@PathParam("id") String id) {
        return Modulo5Resource.getInstance(id);
    }
}