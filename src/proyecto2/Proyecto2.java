package proyecto2;

import Data.ArchivoCliente;
import Data.ArchivoCuentaAhorro;
import Data.ArchivoDepositoPlazo;
import Data.ArchivoTransaccion;
import Model.Cliente;
import Model.Cuenta;
import Model.CuentaAhorro;
import Model.DepositoPlazo;
import Model.Transaccion;
import java.util.Date;


public class Proyecto2 {


    public static void main(String[] args) {
       
        Cliente cliente1 = new Cliente("305480458", "Luis Pablo", "Cartago");
        Cliente cliente2 = new Cliente("302580415", "Jose Solano", "Turrialba");
        Cliente cliente3 = new Cliente("302140785", "Adrian Fuentes", "Guapiles");
        Cliente cliente4 = new Cliente("306980478", "Keren Ballestero", "Talamanca");
        Cliente cliente5 = new Cliente("105470496", "Juanca Zamora", "San Jose");
        
        Cuenta cuentaAhorro1 = new CuentaAhorro((float) 5000000.0, 15, 'A', 'C', cliente1);
        Cuenta cuentaAhorro2 = new CuentaAhorro((float) 127245, 12, 'I', 'D', cliente4);
        Cuenta cuentaAhorro3 = new CuentaAhorro((float) 500000.0, 13, 'A', 'C', cliente5);
        
        Cuenta cuentaPlazos1 = new DepositoPlazo(3, (float)100000.0, (float)2.0, 'C', cliente2);
        Cuenta cuentaPlazos2 = new DepositoPlazo(12, (float)30000.0, (float)3.0, 'D', cliente3);
        Cuenta cuentaPlazos3 = new DepositoPlazo(12, (float)100000.0, (float)5.0, 'C', cliente5);
        
        Date date = new Date(0);
        
        Transaccion transaccion1 = new Transaccion((float)100000, 'D', date, (CuentaAhorro) cuentaAhorro1);
        Transaccion transaccion2 = new Transaccion((float)35000, 'R', date, (CuentaAhorro) cuentaAhorro1);
        Transaccion transaccion3 = new Transaccion((float)50, 'D', date, (CuentaAhorro) cuentaAhorro2);
        Transaccion transaccion4 = new Transaccion((float)10, 'R', date, (CuentaAhorro) cuentaAhorro2);
        Transaccion transaccion5 = new Transaccion((float)100000, 'D', date, (CuentaAhorro) cuentaAhorro3);
        
        ArchivoCliente archivoCliente = new ArchivoCliente();
        ArchivoCuentaAhorro archivoAhorro = new ArchivoCuentaAhorro();
        ArchivoDepositoPlazo depositoPlazo = new ArchivoDepositoPlazo();
        ArchivoTransaccion transaccion = new ArchivoTransaccion();
        
        archivoCliente.registrar(cliente1);
        archivoCliente.registrar(cliente2);
        archivoCliente.registrar(cliente3);
        archivoCliente.registrar(cliente4);
        archivoCliente.registrar(cliente5);
        
        archivoAhorro.registrar((CuentaAhorro) cuentaAhorro1);
        archivoAhorro.registrar((CuentaAhorro) cuentaAhorro2);
        archivoAhorro.registrar((CuentaAhorro) cuentaAhorro3);
        
        depositoPlazo.registrar((DepositoPlazo) cuentaPlazos1);
        depositoPlazo.registrar((DepositoPlazo) cuentaPlazos2);
        depositoPlazo.registrar((DepositoPlazo) cuentaPlazos3);
        
        transaccion.registrar(transaccion1);
        transaccion.registrar(transaccion2);
        transaccion.registrar(transaccion3);
        transaccion.registrar(transaccion4);
        transaccion.registrar(transaccion5);
        
        
    }
    
}
