/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Cuenta;
import Model.CuentaAhorro;

/**
 *
 * @author luisp
 */
public class CuentaAhorroController {
    
public boolean depositar(Cuenta cuenta, float monto){
    CuentaAhorro ahorro = (CuentaAhorro) cuenta;
    
    if (ahorro.getEstado() != 'A' || monto <= 0){
        return false;
    }
    return ahorro.deposito(monto);
    
}


public boolean retirar(Cuenta cuenta, float monto){
    
 CuentaAhorro ahorro = (CuentaAhorro) cuenta;
 
 if(ahorro.getEstado() != 'A' || monto <= 0 || monto > ahorro.getSaldo()){
     return false;
 }
 
 return ahorro.retiro(monto);
}

public boolean actualizarTaza(Cuenta cuenta, float tasa){
    
    
    CuentaAhorro ahorro = (CuentaAhorro) cuenta;
    
    ahorro.setTasaInteres(tasa);
    return true;
}


public boolean asignarInteres(Cuenta cuenta){
    
    
    CuentaAhorro ahorro = (CuentaAhorro) cuenta;
    
    ahorro.calculoIntereses();
    
    return true;
}


public boolean cambiarEstado(Cuenta cuenta, char estado){
    
    if( estado != 'A' && estado != 'I'){
        return false;
    }
    
    CuentaAhorro ahorro = (CuentaAhorro) cuenta;
    
    ahorro.setEstado(estado);
    
    return true;
}





    
}
