/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;
import static Registro.RegistroBaseDatos.url;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
* Modulo 2 - Modulo de Pefil y Gestion de Cuentas. 
* Desarrolladores:
* Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
* Descripcion de la clase: 
* Se encarga de gestionar los datos de la conexion a la base de datos. 
*@Params
*
**/
public class Seguridad {
  
private static Seguridad instancia;


private Seguridad () {}

/**
 * Fabrica de la clase Seguridad.
 * @return Devuelve la instanciacion de la clase. 
 */
public static Seguridad obtenerInstancia(){
  if (instancia==null)
      instancia = new Seguridad(); 
  return instancia; 
}
    
/**
 * Metodo encarga del encriptado de datos.
 * @param sinCifrar Cadena sin encriptar
 * @return Cadena encriptada
 * @throws Exception 
 */    
private byte[] cifra(String sinCifrar) throws Exception {
	final byte[] bytes = sinCifrar.getBytes("UTF-8");
	final Cipher aes = obtieneCipher(true);
	final byte[] cifrado = aes.doFinal(bytes);
	return cifrado;
}    


/**
 * Metodo encarga del desencriptado de datos.
 * @param sinCifrar Cadena encriptada
 * @return Cadena sin encriptar.
 * @throws Exception 
 */    
private String descifra(byte[] cifrado) throws Exception {
	final Cipher aes = obtieneCipher(false);
	final byte[] bytes = aes.doFinal(cifrado);
	final String sinCifrar = new String(bytes, "UTF-8");
	return sinCifrar;
}


/**
 * Metodo que define como se va manejar el cifrado de las cadenas.  
 * @param paraCifrar Indica si esta en modo de desencriptacion o en encriptacion
 * @return De vuelve la manera en como se presenta AES.
 * @throws Exception 
 */
private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
    final String frase = "";
    final MessageDigest digest = MessageDigest.getInstance("SHA");
    digest.update(frase.getBytes("UTF-8"));
    final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

    final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
    if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
    } else {
            aes.init(Cipher.DECRYPT_MODE, key);
    }
    return aes;
}  


/**
 * Metodo que devuelve los datos desencriptados de la conexion a la base de 
 * datos.
 * @return
 */
private String obtenerDatos(){
  String respuesta = "";
        try {       
         
            FileInputStream fileInputStream = null;
            
            File file = obtenerArchivo();
            byte[] fileArray = new byte[(int) file.length()];
            // Con este código se obtienen los bytes del archivo.           
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileArray);
            fileInputStream.close();                    
            respuesta = this.descifra(fileArray);
 
        } catch (Exception ex) {
            Logger.getLogger(Seguridad.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        
        return respuesta; 
}

/**
 * Metodo que devuelve el nombre de la base de datos. 
 */
public String obtenerUsuarioDB(){
  String [] data = this.obtenerDatos().split("%");
  return data[0];  
}

/**
 * Metodo que devuelve la contrasena de la base de datos.
 * @return 
 */
public String obtenerContrasenaDB(){
  String [] data = this.obtenerDatos().split("%");
  return data[1];  
}

/**
 * Metodo que devuelve la direccion de servidor de la base de datos. 
 * @return 
 */
public String obtenerServerDB(){
  String [] data = this.obtenerDatos().split("%");
  return data[2];  
}

/**
 * Genera un archivo temporal con los datos encriptados de la base de datos. 
 * @return Devuelve un objeto tipo File
 */
private File obtenerArchivo(){
    File tempFile;
    try {
         
        InputStream in = Seguridad.class.getClassLoader()
                .getResourceAsStream("/Recursos/datosconexion.txt");
        if (in == null) {
            return null;
        }
        
     tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
     tempFile.deleteOnExit();

        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            //copy stream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
        
    }catch(Exception e){
      
    }
       return null;
    }



}
