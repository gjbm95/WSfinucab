/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO.Model;

/**
 *
 * @author William
 */
public class ExceptionDB extends Exception {
    
    private String codigo;
    private String mensaje;
    private Exception excepcion;
    
    public ExceptionDB (){
        super();
    }
    
    public ExceptionDB(String mensaje){
        super(mensaje);
    }

    public ExceptionDB(String codigo, String mensaje, Exception excepcion) {
        super(mensaje, excepcion);
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.excepcion = excepcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Exception getExcepcion() {
        return excepcion;
    }

    public void setExcepcion(Exception excepcion) {
        this.excepcion = excepcion;
    }
    
    
    
}
