/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Pago;
import Dominio.SimpleResponse;
import Logica.Comando;
import Logica.FabricaComando;
import Logica.Modulo5.EmptyEntityException;
import Logica.Modulo5.EmptyStringException;
import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    

    /**
     * Metodo encargado de la construccion de los JSON para agregar un pago
     * @param datosPagos
     * @return Entidad
     */

    private Entidad CrearJSONagregarPago (@QueryParam("datosPago") String datosPagos)  throws EmptyEntityException  {

        Entidad ex = null;
        
        
        if( (datosPagos != null) || (datosPagos.equals("")) ){
            
            try{
        
            String decodifico = URLDecoder.decode(datosPagos);
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject pagoJSON = reader.readObject();           
            reader.close();
            ex = FabricaEntidad.obtenerPago( pagoJSON.getInt("pg_categoria"), pagoJSON.getString("pg_descripcion"), pagoJSON.getInt("pg_monto"), pagoJSON.getString("pg_tipoTransaccion")) ;

           throw new EmptyEntityException();

        }
            catch(EmptyEntityException e){
                System.out.println(e.EmptyEntity());
            }
        }
        
        else {
          System.out.println("Parametro de entrada nulo o vacio");    
        }
        return ex;
    }
    
    
    
    /**
     * Metodo encargado de la construccion de los JSON para ver un pago
     * @param Objeto
     * @return String
     */

    private String CrearJSONverPago(Entidad Objeto) throws EmptyStringException{

       
         String respuesta ="";
         
          if (Objeto != null ){
              
                    try{
                
                JsonObjectBuilder pagoBuilder = Json.createObjectBuilder();
                
                Pago pago = (Pago) Objeto;                  
                 pagoBuilder.add("pg_id",pago.getId());
                 pagoBuilder.add("pg_monto",pago.getTotal());
                 pagoBuilder.add("pg_tipoTransaccion",pago.getTipo());
                 pagoBuilder.add("pg_categoria",pago.getCategoria());
                 pagoBuilder.add("pg_descripcion",pago.getDescripcion());
                 JsonObject pagoJsonObject = pagoBuilder.build(); 
                respuesta = pagoJsonObject.toString();

                 throw new EmptyStringException();
        }
            catch(EmptyStringException e){
                System.out.println(e.EmptyString());
                    }

    }
          
         
          else{
               System.out.println("Parametro de entrada nulo o vacio");
          }
            return respuesta;
    } 
    

    
    
    /**
     * Metodo encargado de la construccion de los JSON para listar los pagos
     * @param objeto
     * @return String
     */

    private String CrearJSONlistaPago (Entidad objeto) throws EmptyStringException{

       
    String respuesta = "";
        
        if (objeto != null ){
               
            try{
      
                ArrayList<Entidad> lista =  ((ListaEntidad) objeto).getLista();
                JsonObjectBuilder pagoBuilder = Json.createObjectBuilder();
                JsonArrayBuilder list = Json.createArrayBuilder();
              
                for (Entidad enti : lista) {
                    Pago pago = (Pago) enti;
                    pagoBuilder.add("pg_id",pago.getId());
                    pagoBuilder.add("pg_monto",pago.getTotal());
                    pagoBuilder.add("pg_tipoTransaccion",pago.getTipo());
                    pagoBuilder.add("pg_categoria",pago.getCategoria());
                    pagoBuilder.add("pg_descripcion",pago.getDescripcion());
                    JsonObject pagoJsonObject = pagoBuilder.build();  
                                                  
                    list.add( pagoJsonObject.toString());
                    
                }
                
                JsonArray listJsonObject = list.build();
                respuesta = listJsonObject.toString();

                System.out.println(respuesta);
            throw new EmptyStringException();
        }
            catch(EmptyStringException e){
                System.out.println(e.EmptyString());

            }
    }
        
        else {
            System.out.println("Error");   
        }
        
        return respuesta;
    }
    
    
    
    /**
     * Metodo encargado de la construccion de los JSON para modificar un pago
     * @param datosPagos
     * @return Entidad
     */

    private Entidad CrearJSONmodificarPago(@QueryParam("datosPago") String datosPagos)throws EmptyEntityException{

        
        Entidad ex = null;
        
        if( (datosPagos != null) || (datosPagos.equals("")) ){
            
            try {
            String decodifico = URLDecoder.decode(datosPagos);
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject pagoJSON = reader.readObject();
            reader.close();
             ex = FabricaEntidad.obtenerPago(pagoJSON.getInt("pg_id"),pagoJSON.getInt("pg_categoria"), pagoJSON.getString("pg_descripcion"), pagoJSON.getInt("pg_monto"), pagoJSON.getString("pg_tipoTransaccion")) ; 
             throw new EmptyEntityException();
        }
            catch(EmptyEntityException e){
                System.out.println(e.EmptyEntity());

          

            }
        }
        else {
            
           System.out.println("Parametro de entrada nulo o vacio");  
        }
        return ex;
        
    }
    
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/pruebaDB")
    public String getPruebaDataBase() {
        return null;
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
        
         String respuesta = "";
         
       if( (datosPagos != null) || (datosPagos.equals("")) ){
                        try {
            
                        Entidad e = CrearJSONagregarPago(datosPagos);
                        Comando c = FabricaComando.instanciarComandoAgregarPago(e);
                        c.ejecutar();
                        Entidad objectResponse = c.getResponse();
          
                                if (objectResponse != null ){
                                respuesta = String.valueOf(((SimpleResponse) objectResponse).getStatus());
                                                            }
                                
                                else{
                                respuesta = "Error";
                                       }
            

            
                               } catch (Exception e) {
                                respuesta = "0"+e.getMessage();
                                                     }

        
       }
       
       else {
         System.out.println("Parametro de entrada nulo o vacio");  
                         
       }
        return respuesta;
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
        String respuesta ="";
        
        if (idPago !=0){
          
                           try { 
                            Comando c = FabricaComando.instanciarComandoConsultarPago(idPago);
                            c.ejecutar();
                            Entidad objectResponse = c.getResponse();
                            respuesta = CrearJSONverPago(objectResponse);

                                 } catch (Exception e) {
                                    respuesta = "Error :"+e.getMessage();
                                                       }
        
        }
        
        else {
            System.out.println("Parametro de entrada nulo o vacio");  
        }
         return respuesta;
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
    public String visualizarPago(@QueryParam("datosPago") int idPago) {
        
        String respuesta ="";
        
        if (idPago != 0)  {
        
                        try{
            
                        Comando c = FabricaComando.instanciarComandoListarPagos(idPago);
                        c.ejecutar();
                        Entidad objectResponse = c.getResponse();
                        respuesta = CrearJSONlistaPago(objectResponse);
                            }
                        
                         catch(Exception e) {
                         respuesta = "Error Objeto Vacio :"+e.getMessage();
                                             }
                        
        }
        else {
          System.out.println("Parametro de entrada nulo o vacio");  
            
        }
        
        return respuesta;
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
        
        String respuesta = "";
        
     if( (datosPagos != null) || (datosPagos.equals("")) ){


        try {
            
            String decodifico = URLDecoder.decode(datosPagos,"UTF-8");
           
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject pagoJSON = reader.readObject();
            reader.close();             
            Entidad ex = CrearJSONmodificarPago(datosPagos);
            Comando c = FabricaComando.instanciarComandoModificarPago(ex);
            c.ejecutar();
            Entidad objectResponse = c.getResponse();


                                    if (objectResponse != null ){
                
                                    respuesta = String.valueOf(((SimpleResponse) objectResponse).getStatus());
                           
                                    }
                                    else { 
                                    respuesta = "Error Objeto Vacio";
                                            }
                        
                    } 
                
                      catch (Exception e) {
                      System.out.println(e.getMessage());
                      respuesta = "0";
                                          }
                
                
                                }
        
        else {
          System.out.println("Parametro de entrada nulo o vacio");  
            
        }
        return respuesta;
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