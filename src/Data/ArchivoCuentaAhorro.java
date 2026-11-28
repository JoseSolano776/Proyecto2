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
        return buscarPorNumeroCuenta(numeroCuenta) != null;
    }

    public CuentaAhorro buscarPorNumeroCuenta(String numeroCuenta) {
        try {
            String[] registros = ArchivoUtil.leer(archivo);
            for (String registro : registros) {
                String[] dato = registro.split("\t", -1);
                if (dato.length == 8 && dato[1].equals(numeroCuenta)) {
                    Cliente cliente = archivoCliente.buscar(dato[4]);
                    if (cliente == null) {
                        return null;
                    }
                    return new CuentaAhorro(Integer.parseInt(dato[0]), dato[1], Float.parseFloat(dato[6]),
                            Float.parseFloat(dato[7]), dato[5].charAt(0), dato[2].charAt(0), new Date(Long.parseLong(dato[3])), cliente);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return null;
        }
        return null;
    }
    
    public CuentaAhorro[] buscar(String cedula) {
        CuentaAhorro[] temporal = new CuentaAhorro[100];
        int contador = 0;

        try {
            for (String registro : ArchivoUtil.leer(archivo)) {
                String[] dato = registro.split("\t", -1);

                if (dato.length == 8 && dato[4].equals(cedula) && contador < temporal.length) {
                    Cliente cliente = archivoCliente.buscar(dato[4]);

                    if (cliente != null) {
                        temporal[contador++] = new CuentaAhorro(
                                Integer.parseInt(dato[0]), dato[1],
                                Float.parseFloat(dato[6]), Float.parseFloat(dato[7]),
                                dato[5].charAt(0), dato[2].charAt(0),
                                new Date(Long.parseLong(dato[3])), cliente);
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

    public CuentaAhorro[] consultarTodos() {
        CuentaAhorro[] temporal = new CuentaAhorro[100];
        int contador = 0;
        try {
            String[] registros = ArchivoUtil.leer(archivo);
            for (String registro : registros) {
                String[] dato = registro.split("\t", -1);
                if (dato.length == 8 && contador < temporal.length) {
                    Cliente cliente = archivoCliente.buscar(dato[4]);
                    if (cliente != null) {
                        temporal[contador++] = new CuentaAhorro(Integer.parseInt(dato[0]), dato[1],
                                Float.parseFloat(dato[6]), Float.parseFloat(dato[7]), dato[5].charAt(0), dato[2].charAt(0), new Date(Long.parseLong(dato[3])), cliente);
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
                String[] dato = registros[i].split("\t", -1);
                if (dato.length == 8 && dato[1].equals(cuenta.getNumCuenta())) {
                    registros[i] = dato[0] + "\t" + dato[1] + "\t" + dato[2] + "\t" + dato[3] + "\t" + dato[4] + "\t"
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
                String[] dato = registro.split("\t", -1);
                if (dato.length == 8 && dato[1].equals(numeroCuenta)) {
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
