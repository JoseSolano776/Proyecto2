/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Cuenta;
import java.util.Date;

/**
 *
 * @author luisp
 */
public class CuentaController {
    
    private Cuenta[] cuentas = new Cuenta[100];
    private int contador = 0;
    private int consecutivo = 1;
    
    
    public boolean registrarCuenta(Cuenta cuenta){
        
        if(contador >= cuentas.length || cuenta == null){
            return false;
        }
        cuentas[contador] = cuenta;
        contador++;
        
        return true;
    }
    
    public Cuenta buscarCuenta(String numCuenta){
        for (int i = 0; i <contador; i++){
            
            if (cuentas[i].getNumCuenta().equals(numCuenta)){
                return cuentas[i];
            }
        }
        return null;
    }
    
    
    public int generarConsecutivo(){
        return consecutivo++;
    }
    
    public String generarNumeroCuenta(){
        return "Cuenta " + consecutivo;
    }
    
    public Date FechaActual(){
        return new Date();
    }
    
    
    
    
    
}
