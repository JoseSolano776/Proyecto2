
package Model;

import java.util.Date;

public class Transaccion {

    private static int ultimoConsecutivo = 0;

    private int consecutivo;
    private String idTransaccion;
    private float monto;
    private char tipo;
    private Date fecha;
    private CuentaAhorro cuentaAhorro;

    public Transaccion(float monto, char tipo, Date fecha,
            CuentaAhorro cuentaAhorro) {

        ultimoConsecutivo++;
        this.consecutivo = ultimoConsecutivo;

        this.idTransaccion = "T-" + consecutivo;
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = fecha;
        this.cuentaAhorro = cuentaAhorro;

        if (tipo == 'D') {
            cuentaAhorro.deposito(monto);
        } else if (tipo == 'R') {
            cuentaAhorro.retiro(monto);
        }
    }

    // Constructor para reconstruir una transacción desde el archivo
    public Transaccion(int consecutivo, String idTransaccion,
            float monto, char tipo, Date fecha,
            CuentaAhorro cuentaAhorro) {

        this.consecutivo = consecutivo;
        this.idTransaccion = idTransaccion;
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = fecha;
        this.cuentaAhorro = cuentaAhorro;

        if (consecutivo > ultimoConsecutivo) {
            ultimoConsecutivo = consecutivo;
        }
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public float getMonto() {
        return monto;
    }

    public char getTipo() {
        return tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public CuentaAhorro getCuentaAhorro() {
        return cuentaAhorro;
    }

    public void setCuentaAhorro(CuentaAhorro cuentaAhorro) {
        this.cuentaAhorro = cuentaAhorro;
    }

}
