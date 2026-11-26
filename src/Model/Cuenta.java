package Model;

import java.util.Date;

public class Cuenta {
    
    private static int consecutivo;
    private String numCuenta;
    private char tipoMoneda;
    private Date fechaApertura;
    private Cliente cliente;

    public Cuenta() {
    }

    public Cuenta(char tipoMoneda, Date fechaApertura, Cliente cliente) {
        this.consecutivo ++;
        this.tipoMoneda = tipoMoneda;
        this.fechaApertura = fechaApertura;
        this.cliente = cliente;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public char getTipoMoneda() {
        return tipoMoneda;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
