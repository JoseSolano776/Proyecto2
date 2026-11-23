/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.CuentaAhorro;
import Model.Transaccion;
import java.util.Date;

/**
 *
 * @author luisp
 */
public class TransaccionController {
    
    private Transaccion[] transacciones = new Transaccion[200];
    private int contador = 0;
    
    public boolean registrarTransaccion(float monto, char tipo, CuentaAhorro cuenta){
      
        if(contador >= transacciones.length || cuenta == null){
            return false;
        }
        
        if (monto <= 0 || tipo != 'D' && tipo != 'R'){
        return false;
        }
        
        if (cuenta.getEstado() != 'A'){
            return false;
        }
        
        if(tipo == 'R' && monto > cuenta.getSaldo()){
            return false;
           
        }
        
        Transaccion transaccion = new Transaccion(monto,tipo,new Date());
            
          transacciones [contador] = transaccion;
          contador++;
          
          return true;
        }
        
        
      public Transaccion buscarTransaccion(int posicion){
        
          if(posicion < 0 || posicion >= contador){
              return null;
          }
          return transacciones[posicion];
      }
    
    
        
        
    }
    
    

