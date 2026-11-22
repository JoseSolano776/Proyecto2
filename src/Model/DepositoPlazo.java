package Model;

import java.util.Date;

public class DepositoPlazo extends Cuenta{
    
    private int plazoVencimiento;
    private float montoDeposito;
    private float tasaInteresDeposito;

    public DepositoPlazo(int plazoVencimiento, float montoDeposito, float tasaInteresDeposito, int consecutivo, String numCuenta, char tipoMoneda, Date fechaApertura) {
        super(consecutivo, numCuenta, tipoMoneda, fechaApertura);
        this.plazoVencimiento = plazoVencimiento;
        this.montoDeposito = montoDeposito;
        this.tasaInteresDeposito = tasaInteresDeposito;
    }

    public int getPlazoVencimiento() {
        return plazoVencimiento;
    }

    public float getMontoDeposito() {
        return montoDeposito;
    }

    public float getTasaInteresDeposito() {
        return tasaInteresDeposito;
    }
    
    public float calculoMontoFinal(){
        float calculo = montoDeposito;
        calculo += calculo*(tasaInteresDeposito/100);
        return calculo;
    }
    
}
