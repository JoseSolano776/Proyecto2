package Data;

import Model.Cliente;
import Model.CuentaAhorro;
import java.io.IOException;
import java.util.Date;

public class ArchivoCuentaAhorro {

    private final String archivo = "CuentaAhorro.txt";
    private final ArchivoCliente archivoCliente = new ArchivoCliente();

    public boolean registrar(CuentaAhorro cuenta) {
        if (cuenta == null || cuenta.getCliente() == null || existe(cuenta.getNumCuenta()) || archivoCliente.buscar(cuenta.getCliente().getCedula()) == null) {
            return false;
        }
        try {
            String registro = cuenta.getConsecutivo() + "\t" + ArchivoUtil.codificar(cuenta.getNumCuenta()) + "\t"
                    + cuenta.getTipoMoneda() + "\t" + cuenta.getFechaApertura().getTime() + "\t"
                    + ArchivoUtil.codificar(cuenta.getCliente().getCedula()) + "\t" + cuenta.getEstado() + "\t"
                    + cuenta.getSaldo() + "\t" + cuenta.getTasaInteres();
            ArchivoUtil.agregar(archivo, registro);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean existe(String numeroCuenta) {
        return buscar(numeroCuenta) != null;
    }

    public CuentaAhorro buscar(String numeroCuenta) {
        try {
            String[] registros = ArchivoUtil.leer(archivo);
            for (String registro : registros) {
                String[] d = registro.split("\t", -1);
                if (d.length == 8 && d[1].equals(numeroCuenta)) {
                    Cliente cliente = archivoCliente.buscar(d[4]);
                    if (cliente == null) {
                        return null;
                    }
                    return new CuentaAhorro(Integer.parseInt(d[0]), d[1], Float.parseFloat(d[6]),
                            Float.parseFloat(d[7]), d[5].charAt(0), d[2].charAt(0), new Date(Long.parseLong(d[3])), cliente);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return null;
        }
        return null;
    }

    public CuentaAhorro[] consultarTodos() {
        CuentaAhorro[] temporal = new CuentaAhorro[100];
        int contador = 0;
        try {
            String[] registros = ArchivoUtil.leer(archivo);
            for (String registro : registros) {
                String[] d = registro.split("\t", -1);
                if (d.length == 8 && contador < temporal.length) {
                    Cliente cliente = archivoCliente.buscar(d[4]);
                    if (cliente != null) {
                        temporal[contador++] = new CuentaAhorro(Integer.parseInt(d[0]), d[1],
                                Float.parseFloat(d[6]), Float.parseFloat(d[7]), d[5].charAt(0), d[2].charAt(0), new Date(Long.parseLong(d[3])), cliente);
                    }
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return new CuentaAhorro[0];
        }
        CuentaAhorro[] resultado = new CuentaAhorro[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = temporal[i];
        }
        return resultado;
    }

    public boolean actualizar(CuentaAhorro cuenta) {
        if (cuenta == null) {
            return false;
        }
        try {
            String[] registros = ArchivoUtil.leer(archivo);
            boolean encontrado = false;
            for (int i = 0; i < registros.length; i++) {
                String[] d = registros[i].split("\t", -1);
                if (d.length == 8 && d[1].equals(cuenta.getNumCuenta())) {
                    registros[i] = d[0] + "\t" + d[1] + "\t" + d[2] + "\t" + d[3] + "\t" + d[4] + "\t"
                            + cuenta.getEstado() + "\t" + cuenta.getSaldo() + "\t" + cuenta.getTasaInteres();
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                ArchivoUtil.reescribir(archivo, registros);
            }
            return encontrado;
        } catch (IOException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean eliminar(String numeroCuenta) {
        try {
            String[] registros = ArchivoUtil.leer(archivo), temporal = new String[registros.length];
            int contador = 0;
            boolean eliminado = false;
            for (String registro : registros) {
                String[] d = registro.split("\t", -1);
                if (d.length == 8 && d[1].equals(numeroCuenta)) {
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
