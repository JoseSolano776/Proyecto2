/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Cliente;

/**
 *
 * @author luisp
 */
public class ClienteController {
    
    private Cliente[] clientes = new Cliente[100];
    private int contador = 0;
    
    
    public boolean registrarCliente(String cedula, String nombre, String direccion){
        
      Cliente cliente = new Cliente (cedula, nombre, direccion);
      
      clientes[contador] = cliente;
      contador++;
              
    return true;
              
    }
    
    
   public Cliente buscarCliente(String cedula){
       
       for(int i = 0; i < contador; i++){
           
           if(clientes[i].getCedula().equals(cedula)){
           return clientes[i];
           }
       }
       return null;
   }
    
    
    
}
