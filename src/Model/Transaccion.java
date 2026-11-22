package Model;

import java.util.Date;

public class Transaccion {

    private int consecutivo;
    private String idTransaccion;
    private float monto;
    private char tipo;
    private Date fecha;
    private CuentaAhorro cuentaAhorro;

    public Transaccion(float monto, char tipo, Date fecha) {
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = fecha;

        if (tipo == 'D') {
            cuentaAhorro.deposito(monto);
        } else if (tipo == 'R') {
            cuentaAhorro.retiro(monto);
        }

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

}
