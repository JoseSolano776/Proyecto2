package Model;

import java.util.Date;

public class Cuenta {
    
    private int consecutivo;
    private String numCuenta;
    private char tipoMoneda;
    private Date fechaApertura;
    private Cliente cliente;

    public Cuenta() {
    }

    public Cuenta(int consecutivo, String numCuenta, char tipoMoneda, Date fechaApertura) {
        this.consecutivo = consecutivo;
        this.numCuenta = numCuenta;
        this.tipoMoneda = tipoMoneda;
        this.fechaApertura = fechaApertura;
        cliente = new Cliente();
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
    
    
}
