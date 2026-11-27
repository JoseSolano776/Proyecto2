package Model;

import java.util.Date;
import javax.swing.JOptionPane;

public class CuentaAhorro extends Cuenta {

    private char estado;
    private float saldo;
    private float tasaInteres;

    public CuentaAhorro(float saldo, float tasaInteres, char estado, char tipoMoneda, Cliente cliente) {
        super(tipoMoneda, new Date(), cliente); 
        this.estado = estado;
        this.saldo = saldo;
        this.tasaInteres = tasaInteres;
    }

    // Constructor para reconstruir una cuenta desde el archivo
    public CuentaAhorro(int consecutivo, String numCuenta, float saldo, float tasaInteres, char estado, char tipoMoneda, Date fechaApertura, Cliente cliente) {
        super(consecutivo, numCuenta, tipoMoneda, fechaApertura, cliente);
        this.estado = estado;
        this.saldo = saldo;
        this.tasaInteres = tasaInteres;
    }

    public char getEstado() {
        return estado;
    }

    public float getSaldo() {
        return saldo;
    }

    public float getTasaInteres() {
        return tasaInteres;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public void setTasaInteres(float tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public boolean deposito(float monto) {
        boolean realizado = true;
        try {
            if (monto <= 0) {
                JOptionPane.showMessageDialog(null, "Monto no válido");
            } else {
                saldo += monto;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Fallo en el ingreso de datos. /n"
                    + "No se realizó la transacción");
            realizado = false;
        }

        return realizado;
    }

    public boolean retiro(float monto) {
        boolean realizado = true;
        try {
            if (monto <= 0) {
                JOptionPane.showMessageDialog(null, "Monto no válido");
            } else if (monto > saldo) {
                JOptionPane.showMessageDialog(null, "Fondos insuficientes");
            } else {
                saldo -= monto;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Fallo en el ingreso de datos. /n"
                    + "No se realizó la transacción");
            realizado = false;
        }

        return realizado;
    }

    public void calculoIntereses() {
        saldo += saldo*(tasaInteres/100);
    }

}
