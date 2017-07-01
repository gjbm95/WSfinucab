/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import Logica.Modulo5.AgregarPagoException;
import Logica.Modulo5.ConsultarPagoException;
import Logica.Modulo5.ListarPagosException;
import Logica.Modulo5.ModificarPagoException;

/**
 *
 * @author Ramon
 */
public class FabricaExcepcion {
    
    public static DataReaderException instanciarDataReaderException(int code) {
        return new DataReaderException(code);
    }
    
    public static AgregarPagoException instanciarAgregarPagoException(int code) {
        return new AgregarPagoException(code);
    }
    
    public static ModificarPagoException instanciarModificarPagoException(int code) {
        return new ModificarPagoException(code);
    }
    
    public static ConsultarPagoException instanciarConsultarPagoException(int code) {
        return new ConsultarPagoException(code);
    }
    
    public static ListarPagosException instanciarListarPagosException(int code) {
        return new ListarPagosException(code);
    }
    
    public static DataReaderException instanciarDataReaderException(int code, String message) {
        return new DataReaderException(code);
    }
    
    public static AgregarPagoException instanciarAgregarPagoException(int code, String message) {
        return new AgregarPagoException(code, message);
    }
    
    public static ModificarPagoException instanciarModificarPagoException(int code, String message) {
        return new ModificarPagoException(code, message);
    }
    
    public static ConsultarPagoException instanciarConsultarPagoException(int code, String message) {
        return new ConsultarPagoException(code, message);
    }
    
    public static ListarPagosException instanciarListarPagosException(int code, String message) {
        return new ListarPagosException(code, message);
    }
}
