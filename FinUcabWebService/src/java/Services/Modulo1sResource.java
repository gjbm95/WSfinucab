package Services;


import Dominio.Usuario;
import BaseDatosDAO.Conexion;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.SimpleResponse;
import Logica.Comando;
import Logica.FabricaComando;
import Logica.Modulo1.ComandoActualizarClave;
import Logica.Modulo1.ComandoIniciarSesion;
import Logica.Modulo1.ComandoRecuperarClave;
import Logica.Modulo1.ComandoRegistrarUsuario;
import Logica.Modulo1.ComandoVerificarUsuario;
import Logica.Modulo5.EmptyEntityException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
*Modulo 1 - Modulo de Inicio de sesion y registro de usuario.
*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Metodos del servicio web destinados para las funcionalidades de iniciar session,
* registro de usuario y recuperacion de cuenta.
*@Params
*
**/
@Path("/Modulo1")
public class Modulo1sResource {

    @Context
    private UriInfo context;
    private boolean suiche;
    public static String resultado;

    /**
     * Creates a new instance of Modulo1sResource
     */
    public Modulo1sResource(){
    }

    /**
     * Retrieves representation of an instance of Services.Modulo1sResource
     *
     * @return an instance of javax.json.Json
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/prueba")
    public String getPruebaJson() {
        //TODO return proper representation object
        JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
        usuarioBuilder.add("Nombre", "Jose");
        usuarioBuilder.add("Apellido", "Rodriguez");
        usuarioBuilder.add("Usuario", "jose123");
        JsonObject usuarioJsonObject = usuarioBuilder.build();
        return usuarioJsonObject.toString();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("pruebaData")
    public String getData(@QueryParam("objeto") String objeto) {
        return "";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/pruebaDB")
    public String getPruebaDataBase() {
        //TODO return proper representation object 
        String respuesta = "";
        try {

            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Usuario;");
            while (rs.next()) {
                //Creo el objeto Json!             
                JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                usuarioBuilder.add("Nombre", rs.getString(3));
                usuarioBuilder.add("Apellido", rs.getString(4));
                usuarioBuilder.add("Usuario", rs.getString(2));
                JsonObject usuarioJsonObject = usuarioBuilder.build();
                respuesta = usuarioJsonObject.toString();
            }
            rs.close();
            st.close();

            return respuesta;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
    * Función que registra un usuario en la base de datos si esta disponible su
    * nombre de usuario.
    *
    * @param datosCuenta JSON.toString() con los atributos: u_usuario, u_nombre
    * , u_apellido , u_correo , u_pregunta , u_respuesta , u_pregunta ,
    * u_password
    *
    * @return Si se inserta el usuario devuelve un String con el mensaje
    * "1", De lo contrario devuelve el mensaje "0"
    */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarUsuario")
    public String registrarUsuario(@QueryParam("datosUsuario") String datosCuenta) {
        Entidad usuario = entidadAgregarUsuario(datosCuenta);
        Comando cru = FabricaComando.instanciarComandoRegistrarUsuario(usuario);
        cru.ejecutar();
        Entidad respuesta = cru.getResponse();
        String resultado = obtenerRespuestaRegistrarUsuario(respuesta);
        return resultado;
    }
    
    /**
     * Metodo encargado de la construccion de los JSON para agregar un usuario
     * @param datosUsuario JSON.toString() con los atributos: u_usuario, u_nombre
     * , u_apellido , u_correo , u_pregunta , u_respuesta , u_pregunta ,
     * u_password
     * @return Entidad con los datos del usuario
     */
    private Entidad entidadAgregarUsuario (@QueryParam("datosUsuario") String datosCuenta)  /*throws EmptyEntityException  */{

        Entidad usuario = null;
                 
            /*try{       
                
                boolean validador  = validadorString(datosPagos);
                if( validador ){
         */
                    String decodifico;
        try {
            decodifico = URLDecoder.decode(datosCuenta,"UTF-8");
            JsonReader reader = Json.createReader(new StringReader(decodifico));
                    JsonObject usuarioJSON = reader.readObject();           
                    reader.close();
                    usuario = FabricaEntidad.obtenerUsuario(0,
                    usuarioJSON.getString("u_nombre"),
                    usuarioJSON.getString("u_apellido"),
                    usuarioJSON.getString("u_correo"),
                    usuarioJSON.getString("u_usuario"),
                    usuarioJSON.getString("u_password"),
                    usuarioJSON.getString("u_pregunta"),
                    usuarioJSON.getString("u_respuesta"),null,null);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Modulo1sResource.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                    /*
                }else {

                    throw new EmptyEntityException();   
                }
            
            }catch(EmptyEntityException e){
                System.out.println(e.EmptyEntity());
            } catch (UnsupportedEncodingException ex1) {
                
            }*/
        return usuario;
    }
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti que contiene la respuesta
     * @return Si se inserta el usuario devuelve un String con el mensaje
     * "1", De lo contrario devuelve el mensaje "0"
     */
    private String obtenerRespuestaRegistrarUsuario(Entidad enti){
          String respuesta;
        //if(validadorEntidad(enti)) 
        if(((SimpleResponse) enti).getStatus() == 1){
            respuesta = "1";
        }else{
            respuesta = "0";
        }
        return respuesta;
        //else {
        //    return "Error Entidad nula o Vacia";
        //}
    }

    /**
     * Funcion que verifica disponibilidad de nombre de usuario.
     *
     * @param usuario String del nombre de usuario a consultar.
     * @return Si el nombre ya existe devuelve "4", Si no existe
     * devuelve "3"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/verificarUsuario")
    public String verificarUsuario(@QueryParam("nombreUsuario") String usuario) {
        Comando cvu = FabricaComando.instanciarComandoVerificarUsuario(usuario);
        cvu.ejecutar();
        Entidad respuesta = cvu.getResponse();
        String resultado = obtenerRespuestaVerificarUsuario(respuesta);        
        return resultado;
    }

    
  /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti que contiene la respuesta
     * @return Si el nombre ya existe devuelve "4", Si no existe
     * devuelve "3"
     */
    private String obtenerRespuestaVerificarUsuario(Entidad enti){
          String respuesta = "";
        //if(validadorEntidad(enti)) 
               if(((SimpleResponse) enti).getStatus() == 4){
            respuesta = "4";//EN USO
        }else if(((SimpleResponse) enti).getStatus() == 3){
            respuesta = "3";//DISPONIBLE
        }
        return respuesta;
        
        //else {
        //    return "Error Entidad nula o Vacia";
        //}
    }
    
    /**
     * Función que verifica existencia de un usuario en el sistema y de existir
     * verifica si el password recibido es igual al que está almacenado en la BD
     *
     * @param usuario JSON.toString() con los "atributos" u_usuario y u_password
     * @return De existir el usuario y la contraseña coincide retorna un JSON en
     * String con todos los datos del usuario. De lo contrario retorna el String
     * "7"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/iniciarSesion")
    public String iniciarSesion(@QueryParam("datosUsuario") String usuario) {
         Entidad usuarioi = entidadiniciarSesion(usuario);
        Comando cru = FabricaComando.instanciarComandoIniciarSesion(usuarioi);
        cru.ejecutar();
        Entidad respuesta = cru.getResponse();
        String resultado = obtenerRespuestaIniciarsesion(respuesta);
        return resultado;
        
      
    }
    
    /**
     * Metodo encargado de la construccion de los JSON para iniciar sesion
     * @param usuario JSON.toString() con los "atributos" u_usuario y u_password
     * @return Entidad con los datos del usuario
     */
    private Entidad entidadiniciarSesion (@QueryParam("datosUsuario") String usuario)  /*throws EmptyEntityException  */{

        Entidad usuarioi = null;
                 
            /*try{       
                
                boolean validador  = validadorString(datosPagos);
                if( validador ){
         */
                    String decodifico;
        try {
            decodifico = URLDecoder.decode(usuario,"UTF-8");
            JsonReader reader = Json.createReader(new StringReader(decodifico));
                    JsonObject usuarioJSON = reader.readObject();           
                    reader.close();
                    usuarioi = FabricaEntidad.obtenerUsuario(0,
                    null,null,null,
                    usuarioJSON.getString("u_usuario"),
                    usuarioJSON.getString("u_password"),null,null,null,null);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Modulo1sResource.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                    /*
                }else {

                    throw new EmptyEntityException();   
                }
            
            }catch(EmptyEntityException e){
                System.out.println(e.EmptyEntity());
            } catch (UnsupportedEncodingException ex1) {
                
            }*/
        return usuarioi;
    }
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti que contiene la respuesta
     * @return De existir el usuario y la contraseña coincide retorna un JSON en
     * String con todos los datos del usuario. De lo contrario retorna el String
     * "7"
     */
     private String obtenerRespuestaIniciarsesion(Entidad enti){
          String respuesta;
        //if(validadorEntidad(enti)) 
        
        return ((SimpleResponse) enti).getDescripcion();
        //else {
        //    return "Error Entidad nula o Vacia";
        //}
    }
    

    /**
     * Función que verifica existencia de un usuario en el sistema
     *
     * @param usuario JSON.toString() con el "atributos" u_usuario
     * @return De existir el usuario retorna un JSON en String con todos los 
     * datos del usuario. De lo contrario retorna el String
     * "ERROR"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/recuperarClave")
    public String recuperarClave(@QueryParam("datosUsuario") String usuario) {
        Comando crc = FabricaComando.instanciarComandoRecuperarClave(usuario);
        crc.ejecutar();
        Entidad respuesta = crc.getResponse();
        String resultado = obtenerRespuestaRecuperarClave(respuesta);        
        return resultado;
        
        
    }
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti que contiene la respuesta
     * @return De existir el usuario coincide retorna un JSON en
     * String con todos los datos del usuario. De lo contrario retorna el String
     * "ERROR"
     */
    private String obtenerRespuestaRecuperarClave(Entidad enti){
          String respuesta = "";
        //if(validadorEntidad(enti)) 
        return ((SimpleResponse) enti).getDescripcion();
        
        //else {
        //    return "Error Entidad nula o Vacia";
        //}
    }
    
    
    /**
     * Función que actualiza la clave de un usuario en la BD
     *
     * @param datosUsuario JSON.toString() que tenga los "atributos" 
     * u_usuario , u_password
     * @return Si se actualiza correctamente retorna un String con "5" de no 
     * actualiza correctamente retorna "6"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/actualizarClave")
    public String actualizarClave(@QueryParam("datosUsuario") String datosUsuario) {
        Entidad usuario = entidadActualizarClave(datosUsuario);
        Comando cis = FabricaComando.instanciarComandoActualizarClave(usuario);
        cis.ejecutar();
        Entidad respuesta = cis.getResponse();
        String resultado = obtenerRespuestaActualizarClave(respuesta);
        return resultado;
    }
    
    /**
     * Metodo encargado de la construccion de los JSON para iniciar sesion
     * @param usuario JSON.toString() con los "atributos" u_usuario y u_password
     * @return Entidad con los datos del usuario
     */
    
    private Entidad entidadActualizarClave (@QueryParam("datosUsuario") String datosUsuario)  /*throws EmptyEntityException  */{

        Entidad usuarioi = null;
                 
            /*try{       
                
                boolean validador  = validadorString(datosPagos);
                if( validador ){
         */
                    String decodifico;
        try {
            decodifico = URLDecoder.decode(datosUsuario,"UTF-8");
            JsonReader reader = Json.createReader(new StringReader(decodifico));
                    JsonObject usuarioJSON = reader.readObject();           
                    reader.close();
                    usuarioi = FabricaEntidad.obtenerUsuario(0,
                    null,null,null,usuarioJSON.getString("u_usuario"),
                    usuarioJSON.getString("u_password"),null,null,null,null);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Modulo1sResource.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                    /*
                }else {

                    throw new EmptyEntityException();   
                }
            
            }catch(EmptyEntityException e){
                System.out.println(e.EmptyEntity());
            } catch (UnsupportedEncodingException ex1) {
                
            }*/
        return usuarioi;
    }
    
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti que contiene la respuesta
     * @return Si se actualiza correctamente retorna un String con "5" de no 
     * actualiza correctamente retorna "6"
     */
    private String obtenerRespuestaActualizarClave(Entidad enti){
          String respuesta;
        //if(validadorEntidad(enti)) 
        if(((SimpleResponse) enti).getStatus() == 5){
            respuesta = "5"; //Correctamente
        }else{
            respuesta = "6"; //Incorrecto
        }
        return respuesta;
        //else {
        //    return "Error Entidad nula o Vacia";
        //}
    }

    /**
     * POST method for creating an instance of Modulo1Resource
     *
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(Json content
    ) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public Modulo1Resource getModulo1Resource(@PathParam("id") String id
    ) {
        return Modulo1Resource.getInstance(id);
    }
}