package Model;

import java.util.Date;

public class DepositoPlazo extends Cuenta{
    
    private int plazoVencimiento;
    private float montoDeposito;
    private float tasaInteresDeposito;

    public DepositoPlazo(int plazoVencimiento, float montoDeposito, float tasaInteresDeposito, char tipoMoneda, Cliente cliente) {
        super('A', new Date(), cliente);
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
