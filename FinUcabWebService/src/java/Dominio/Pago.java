package Dominio;

import java.io.Serializable;

/**
 * Created by Jeffrey on 10/05/2017.
 */

public class Pago  extends Entidad implements Serializable{
    private int idPago;
    private int categoria;
    private String descripcion;
    private float total;
    private String tipo;
    private int idusuario;

    
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
    
    public int getIdUsario() {
        return idusuario;
    }

    public void setUsuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pago(int categoria, String descripcion, float total, String tipo, int usuario) {
       
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.total = total;
        this.tipo = tipo;
        this.idusuario = usuario;
    }
    
    public Pago( int _id, int categoria, String descripcion, float total, String tipo, int usuario) {
        super(_id);
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.total = total;
        this.tipo = tipo;
        this.idusuario= usuario;
    }

    public Pago(){

    }
}
