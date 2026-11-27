
package Model;

import java.util.Date;

public abstract class Cuenta {

    private static int ultimoConsecutivo = 0;

    private int consecutivo;
    private String numCuenta;
    private char tipoMoneda;
    private Date fechaApertura;
    private Cliente cliente;

    public Cuenta() {
    }

    public Cuenta(char tipoMoneda, Date fechaApertura, Cliente cliente) {

        ultimoConsecutivo++;
        this.consecutivo = ultimoConsecutivo;

        // Generación automática del número de cuenta
        this.numCuenta = "C-" + consecutivo;

        this.tipoMoneda = tipoMoneda;
        this.fechaApertura = fechaApertura;
        this.cliente = cliente;
    }

    // Constructor para reconstruir una cuenta desde el archivo
    public Cuenta(int consecutivo, String numCuenta, char tipoMoneda,
            Date fechaApertura, Cliente cliente) {

        this.consecutivo = consecutivo;
        this.numCuenta = numCuenta;
        this.tipoMoneda = tipoMoneda;
        this.fechaApertura = fechaApertura;
        this.cliente = cliente;

        if (consecutivo > ultimoConsecutivo) {
            ultimoConsecutivo = consecutivo;
        }
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

}
