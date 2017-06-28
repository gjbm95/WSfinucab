package Services;

import BaseDatosDAO.Conexion;
import Dominio.Categoria;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Logica.Comando;
import Logica.FabricaComando;
import java.io.StringReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@Path("/Modulo4")
public class Modulo4sResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Modulo4sResource
     */
    public Modulo4sResource() {
    }

    /**
     * Retrieves representation of an instance of Services.Modulo4sResource
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
     * Funcion que registra una categoria creada por el usuario en la base de datos
     * 
     *
     * @param datosCategoria JSON.toString() con los atributos: c_uduario, c_nombre, c_descripcion
     * , c_ingreso, c_estado
     *
     * @return Si se inserta la categoria devuelve un String con el mensaje
     * "Registro Exitoso", De lo contrario devuelve el mensaje "No se pudo
     * registrar"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarCategoria")
    public String registrarCategoria(@QueryParam("datosCategoria") String datosCategoria) {
        System.out.println(datosCategoria);
        String decodifico = URLDecoder.decode(datosCategoria);

        try {
           
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject categoriaJSON = reader.readObject();
           
            reader.close();
            Entidad e = FabricaEntidad.obtenerCategoria(categoriaJSON.getInt("c_usuario"), categoriaJSON.getString("c_nombre"), categoriaJSON.getString("c_descripcion"), categoriaJSON.getBoolean("c_ingreso"), categoriaJSON.getBoolean("c_estado")) ;
            Comando c = FabricaComando.instanciarComandoAgregarCategoria(e);
            Object objectResponse = c.ejecutar();
            return objectResponse.toString();
        } catch (Exception e) {

            return e.getMessage();

        }
    }

    
    /**
     * Función que elimina una categoria y modifica las tablas donde se encontraba esa categoria
     * Siempre debe existir una categoria con id -1 para modificar cuando se elimine la categoria
     *
     * @param datosCategoria JSON.toString() con los atributos: c_id
     * @metodo eliminarCategoria2 para modificar todas las tablas donde aparecia la categoria.
     *
     * @return Si el registro fue borrado exitosamente devuelve un String
     *"Borado exitoso" de lo contrario devuelve "No se pudo borrar"
     * 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eliminarCategoria")
    public String eliminarCategoria(@QueryParam("datosCategoria") int datosCategoria) {

        Comando c = FabricaComando.instanciarComandoEliminarCategoria(datosCategoria);
        Object objectResponse = c.ejecutar();
        return objectResponse.toString();
    }
    
    
      /**
     * Función que permite visualizar todas las categoria que posee un usuaria
     * 
     *
     * @param usuario JSON.toString() con los atributos: c_id
     * 
     * 
     *
     * @return devuelve una lista con todas las categoria que posee un usuario
     * en caso de no poseer categorias creadas devuelve null
     * 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/visualizarCategoria")
    public String VisualizarCategoria(@QueryParam("datosCategoria") int usuario) {

        try{

            Comando c = FabricaComando.instanciarComandoVisualizarCategoria(usuario);
            Object objectResponse = c.ejecutar();
            
            if (objectResponse != null ){
                
                ArrayList<Categoria> lista = (ArrayList<Categoria>) objectResponse;
                JsonObjectBuilder categoriaBuilder = Json.createObjectBuilder();
                JsonArrayBuilder list = Json.createArrayBuilder();
                
                for (Categoria categoria : lista) {
                    
                    categoriaBuilder.add("ca_id",categoria.getIdcategoria());
                    categoriaBuilder.add("ca_nombre",categoria.getNombre());
                    categoriaBuilder.add("ca_descripcion",categoria.getDescripcion());
                    categoriaBuilder.add("ca_eshabilitado",categoria.isEstaHabilitado());
                    categoriaBuilder.add("ca_esingreso",categoria.isIngreso());
                    categoriaBuilder.add("usuariou_id",categoria.getIdUsario());
                    JsonObject categoriaJsonObject = categoriaBuilder.build();  

                    list.add( categoriaJsonObject.toString());
                    
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
     * Función que modifica en la base de datos atributos de categoria creados por el usuario
     * 
     *
     * @param datosCategoria JSON.toString() con los atributos: c_id, c_nombre, c_descripcion
     * ,c_ingreso, c_estado
     * 
     * 
     *
     * @return Si se modifica la categoria devuelve un String con el mensaje
     * "Modificacion exitosa", De lo contrario devuelve el mensaje "No se pudo
     * modificar"
     */
   @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/modificarCategoria")
    public String modificarCategoria(@QueryParam("datosCategoria") String datosCategoria) {
        String decodifico = URLDecoder.decode(datosCategoria);

        try {
           
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject categoriaJSON = reader.readObject();       
            reader.close();
            Entidad e = FabricaEntidad.obtenerCategoria(categoriaJSON.getInt("c_id"),categoriaJSON.getInt("c_usuario"), categoriaJSON.getString("c_nombre"), categoriaJSON.getString("c_descripcion"), categoriaJSON.getBoolean("c_ingreso"), categoriaJSON.getBoolean("c_estado")) ;
            Comando c = FabricaComando.instanciarComandoModificarCategoria(e);
            Object objectResponse = c.ejecutar();
            return objectResponse.toString();
        } catch (Exception e) {

            return e.getMessage();

        }
        
    }
    
        /**
     * Función que busca y devuelve una categoria por su id
     *
     * @param datosCategoria JSON.toString() con los atributos: c_id
     * @metodo eliminarCategoria2 para modificar todas las tablas donde aparecia la categoria.
     *
     * @return Si el registro fue borrado exitosamente devuelve un String
     *con la categoria si se encontro de lo contrario devuelve null
     * 
     */
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/buscarCategoria")
    public String buscarCategoria(@QueryParam("datosCategoria") int datosCategoria){
        //String decodifico = URLDecoder.decode(datosCategoria);
        try{
            
            Comando c = FabricaComando.instanciarComandoConsultarCategoria(datosCategoria);
            Object objectResponse = c.ejecutar();
            
            if (objectResponse != null ){           
                System.out.println("entro");
                JsonObjectBuilder categoriaBuilder = Json.createObjectBuilder();

                Categoria categoria = (Categoria) objectResponse;
                categoriaBuilder.add("ca_id",categoria.getIdcategoria());
                categoriaBuilder.add("ca_nombre",categoria.getNombre());
                categoriaBuilder.add("ca_descripcion",categoria.getDescripcion());
                categoriaBuilder.add("ca_eshabilitado",categoria.isEstaHabilitado());
                categoriaBuilder.add("ca_esingreso",categoria.isIngreso());
                categoriaBuilder.add("usuariou_id",categoria.getIdUsario());
                JsonObject categoriaJsonObject = categoriaBuilder.build();  
                String  respuesta = categoriaJsonObject.toString();
                System.out.println(respuesta);
                return respuesta;
            }
        }
        catch(Exception e) {
            return e.getMessage();
        }
    return "Error";
    }

    /**
     * POST method for creating an instance of Modulo4Resource
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
    public Modulo4Resource getModulo4Resource(@PathParam("id") String id) {
        return Modulo4Resource.getInstance(id);
    }

}
