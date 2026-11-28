package Data;

import Model.CuentaAhorro;
import Model.Transaccion;
import java.io.IOException;
import java.util.Date;

public class ArchivoTransaccion {

    private final String archivo = "Transaccion.txt";
    private final ArchivoCuentaAhorro archivoCuenta = new ArchivoCuentaAhorro();

    public boolean registrar(Transaccion transaccion) {
        if (transaccion == null || transaccion.getCuentaAhorro() == null || transaccion.getIdTransaccion() == null || existe(transaccion.getIdTransaccion()) || archivoCuenta.buscarPorNumeroCuenta(transaccion.getCuentaAhorro().getNumCuenta()) == null || transaccion.getMonto() <= 0 || (transaccion.getTipo() != 'D' && transaccion.getTipo() != 'R')) {
            return false;
        }
        try {
            ArchivoUtil.agregar(archivo, transaccion.getConsecutivo() + "\t" + ArchivoUtil.codificar(transaccion.getIdTransaccion()) + "\t" + transaccion.getMonto() + "\t" + transaccion.getTipo() + "\t" + transaccion.getFecha().getTime() + "\t" + ArchivoUtil.codificar(transaccion.getCuentaAhorro().getNumCuenta()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean existe(String idTransaccion) {
        return buscar(idTransaccion) != null;
    }

    public Transaccion buscar(String idTransaccion) {
        try {
            for (String registro : ArchivoUtil.leer(archivo)) {
                String[] d = registro.split("\t", -1);
                if (d.length == 6 && d[1].equals(idTransaccion)) {
                    CuentaAhorro cuenta = archivoCuenta.buscarPorNumeroCuenta(d[5]);
                    if (cuenta == null) {
                        return null;
                    }
                    return new Transaccion(Integer.parseInt(d[0]), d[1], Float.parseFloat(d[2]), d[3].charAt(0), new Date(Long.parseLong(d[4])), cuenta);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return null;
        }
        return null;
    }

    public Transaccion[] consultarPorCuenta(String numeroCuenta) {
        Transaccion[] temporal = new Transaccion[200];
        int contador = 0;
        CuentaAhorro cuenta = archivoCuenta.buscarPorNumeroCuenta(numeroCuenta);
        if (cuenta == null) {
            return new Transaccion[0];
        }
        try {
            for (String registro : ArchivoUtil.leer(archivo)) {
                String[] d = registro.split("\t", -1);
                if (d.length == 6 && d[5].equals(numeroCuenta) && contador < temporal.length) {
                    temporal[contador++] = new Transaccion(Integer.parseInt(d[0]), d[1], Float.parseFloat(d[2]), d[3].charAt(0), new Date(Long.parseLong(d[4])), cuenta);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return new Transaccion[0];
        }
        Transaccion[] resultado = new Transaccion[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = temporal[i];
        }
        return resultado;
    }

    public Transaccion[] consultarPorFecha(String numeroCuenta, Date fechaInicio, Date fechaFin) {
        if (fechaInicio == null || fechaFin == null || fechaInicio.after(fechaFin)) {
            return new Transaccion[0];
        }
        Transaccion[] transacciones = consultarPorCuenta(numeroCuenta), temporal = new Transaccion[transacciones.length];
        int contador = 0;
        for (Transaccion t : transacciones) {
            Date fecha = t.getFecha();
            if (!fecha.before(fechaInicio) && !fecha.after(fechaFin)) {
                temporal[contador++] = t;
            }
        }
        Transaccion[] resultado = new Transaccion[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = temporal[i];
        }
        return resultado;
    }

    public boolean eliminar(String idTransaccion) {
        try {
            String[] registros = ArchivoUtil.leer(archivo), temporal = new String[registros.length];
            int contador = 0;
            boolean eliminado = false;
            for (String registro : registros) {
                String[] d = registro.split("\t", -1);
                if (d.length == 6 && d[1].equals(idTransaccion)) {
                    eliminado = true;
                } else {
                    temporal[contador++] = registro;
                }
            }
            if (eliminado) {
                String[] exacto = new String[contador];
                for (int i = 0; i < contador; i++) {
                    exacto[i] = temporal[i];
                }
                ArchivoUtil.reescribir(archivo, exacto);
            }
            return eliminado;
        } catch (IOException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean eliminarPorCuenta(String numeroCuenta) {
        try {
            String[] registros = ArchivoUtil.leer(archivo), temporal = new String[registros.length];
            int contador = 0;
            boolean eliminado = false;
            for (String registro : registros) {
                String[] d = registro.split("\t", -1);
                if (d.length == 6 && d[5].equals(numeroCuenta)) {
                    eliminado = true;
                } else {
                    temporal[contador++] = registro;
                }
            }
            if (eliminado) {
                String[] exacto = new String[contador];
                for (int i = 0; i < contador; i++) {
                    exacto[i] = temporal[i];
                }
                ArchivoUtil.reescribir(archivo, exacto);
            }
            return eliminado;
        } catch (IOException | IllegalArgumentException e) {
            return false;
        }
    }
}
