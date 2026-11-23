/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Cuenta;
import Model.DepositoPlazo;

/**
 *
 * @author luisp
 */
public class DepositoPlazoController {
    
    
   public float calcularMontoFinal(Cuenta cuenta){
     
       
     DepositoPlazo deposito  = (DepositoPlazo) cuenta;
     
     return deposito.calculoMontoFinal();
       
   }
    
    
}
