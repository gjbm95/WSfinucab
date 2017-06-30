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
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaAgregar(Entidad enti){
          
        if(validadorEntidad(enti)) 
        
        return String.valueOf(((SimpleResponse) enti).getId());
        else {
            return "Error Entidad nula o Vacia";
        }
    }
    
    
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaConsultar(Entidad enti) throws EmptyStringException{
         
        if(validadorEntidad(enti)) 
        
        return stringVerPago(enti);
        else {
            return "Error Entidad nula o Vacia";
        }
    }
     
     
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaLista(Entidad enti) throws EmptyStringException{
         
        if(validadorEntidad(enti)) 
        
        return stringListaPago(enti);
        else {
            return "Error Entidad nula o Vacia";
        }
    }
    
    
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaModificar(Entidad enti){
          
        if(validadorEntidad(enti)) 
        
        return String.valueOf(((SimpleResponse) enti).getId());
        else {
            return "Error Entidad nula o Vacia";
        }
    }
    
    
    
    /**
     * Metodo para validar un string
     * @param valor
     * @return boolean
     */
    private boolean validadorString(String valor) throws EmptyEntityException, NullPointerException{
        
        if (valor == null) {
            throw new NullPointerException();
        }else if(valor.equals("")) {
            throw new EmptyEntityException();
        }else{
            return true;
        }

    }
    
    
    
    /**
     * Metodo para validar que un integer no sea cero , ni nulo
     * @param valor
     * @return boolean
     */
    private boolean validadorInteger(int valor){
        
        return (valor!=0);
    }
    
   
    
    /**
     * Metodo para validar que una entidad no sea nula ni vacia
     * @param valor
     * @return boolean
     */
    private boolean validadorEntidad(Entidad valor){
        
        return (valor!= null) && (!valor.equals(""));
    }
    
    
    

    /**
     * Metodo encargado de la construccion de los JSON para agregar un pago
     * @param datosPagos
     * @return Entidad
     */
    private Entidad entidadAgregarPago (@QueryParam("datosPago") String datosPagos)  throws EmptyEntityException  {

        Entidad ex = null;
                 
            try{       
                
                boolean validador  =validadorString(datosPagos);
                if( validador ){
         
                    String decodifico = URLDecoder.decode(datosPagos,"UTF-8");
                    JsonReader reader = Json.createReader(new StringReader(decodifico));
                    JsonObject pagoJSON = reader.readObject();           
                    reader.close();
                    ex = FabricaEntidad.obtenerPago( pagoJSON.getInt("pg_categoria"), pagoJSON.getString("pg_descripcion"), pagoJSON.getInt("pg_monto"), pagoJSON.getString("pg_tipoTransaccion")) ;

                }else {

                    throw new EmptyEntityException();   
                }
            
            }catch(EmptyEntityException e){
                System.out.println(e.EmptyEntity());
            } catch (UnsupportedEncodingException ex1) {
                
            }
        return ex;
    }
    
    
    
    
    /**
     * Metodo encargado de la construccion de los JSON para ver un pago
     * @param Objeto
     * @return String
     */
    private String stringVerPago(Entidad Objeto) throws EmptyStringException{

       
         String respuesta ="";
         
         
         
        boolean validador  =validadorEntidad(Objeto);

           try{     
                if( validador ){
              
                    JsonObjectBuilder pagoBuilder = Json.createObjectBuilder();

                    Pago pago = (Pago) Objeto;                  
                     pagoBuilder.add("pg_id",pago.getId());
                     pagoBuilder.add("pg_monto",pago.getTotal());
                     pagoBuilder.add("pg_tipoTransaccion",pago.getTipo());
                     pagoBuilder.add("pg_categoria",pago.getCategoria());
                     pagoBuilder.add("pg_descripcion",pago.getDescripcion());
                     JsonObject pagoJsonObject = pagoBuilder.build(); 
                    respuesta = pagoJsonObject.toString();

                }else{
                    
                    throw new EmptyEntityException();  
                }
           } catch (EmptyEntityException ex) {
            Logger.getLogger(Modulo5sResource.class.getName()).log(Level.SEVERE, null, ex);
        }
          
            return respuesta;
    } 
    

    
    
    /**
     * Metodo encargado de la construccion de los JSON para listar los pagos
     * @param objeto
     * @return String
     */
    private String stringListaPago (Entidad objeto) throws EmptyStringException{

       
    String respuesta = "";
        
        boolean validador  =validadorEntidad(objeto);
                
        if( validador ){
               
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
    private Entidad entidadModificarPago(@QueryParam("datosPago") String datosPagos)throws EmptyEntityException{

        
        Entidad ex = null;
        
       boolean validador  =validadorString(datosPagos);
                
        if( validador ){
            
            try {
                String decodifico = URLDecoder.decode(datosPagos,"UTF-8");
                JsonReader reader = Json.createReader(new StringReader(decodifico));
                JsonObject pagoJSON = reader.readObject();
                reader.close();  
                ex = FabricaEntidad.obtenerPago(pagoJSON.getInt("pg_id"),pagoJSON.getInt("pg_categoria"), pagoJSON.getString("pg_descripcion"), pagoJSON.getInt("pg_monto"), pagoJSON.getString("pg_tipoTransaccion")) ; 
                throw new EmptyEntityException();
        }
            catch(EmptyEntityException e){
                System.out.println(e.EmptyEntity());

          

            } catch (UnsupportedEncodingException ex1) {
                Logger.getLogger(Modulo5sResource.class.getName()).log(Level.SEVERE, null, ex1);
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
         
        try {
            Entidad e = entidadAgregarPago(datosPagos);
            Comando c = FabricaComando.instanciarComandoAgregarPago(e);
            c.ejecutar();
            Entidad objectResponse = c.getResponse();
            respuesta = obtenerRespuestaAgregar(objectResponse);
        } 
        catch (EmptyEntityException ex) { 
                Logger.getLogger(Modulo5sResource.class.getName()).log(Level.SEVERE, null, ex);
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
                        System.out.println("Parametro");  
        String respuesta ="";
                try { 
                        System.out.println("Parametro");  
                    if( validadorInteger(idPago)){
                        System.out.println("Parametro");  
                        Comando c = FabricaComando.instanciarComandoConsultarPago(idPago);
                        System.out.println("Parametro");  
                        c.ejecutar();
                        System.out.println("Parametro");  
                        Entidad objectResponse = c.getResponse();
                        System.out.println("Parametro");  
                        respuesta =obtenerRespuestaConsultar(objectResponse);
                        System.out.println("Parametro");  
                    }
                    else {
                        System.out.println("Parametro de entrada nulo o vacio");  
                    }
                    
                    }catch (Exception e) {
                        respuesta = "Error :"+e.getMessage();
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
            
                try{
                    if( validadorInteger(idPago) ){
                        Comando c = FabricaComando.instanciarComandoListarPagos(idPago);
                        c.ejecutar();
                        Entidad objectResponse = c.getResponse();
                        respuesta =obtenerRespuestaLista(objectResponse);
                    }
                    else {
                        System.out.println("Parametro de entrada nulo o vacio");  
                    }
                }
                    catch(Exception e) {
                    respuesta = "Error Objeto Vacio :"+e.getMessage();
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

            try {
                Entidad ex = entidadModificarPago(datosPagos);
                Comando c = FabricaComando.instanciarComandoModificarPago(ex);
                c.ejecutar();
                Entidad objectResponse = c.getResponse();
                respuesta = obtenerRespuestaModificar(objectResponse);
                
            } 
            catch (Exception e) {
                System.out.println(e.getMessage());
                respuesta = "0";
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