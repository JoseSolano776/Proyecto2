package Data;

import Model.Cliente;
import Model.DepositoPlazo;
import java.io.IOException;
import java.util.Date;

public class ArchivoDepositoPlazo {

    private final String archivo = "DepositoPlazo.txt";
    private final ArchivoCliente archivoCliente = new ArchivoCliente();

    public boolean registrar(DepositoPlazo deposito) {
        if (deposito == null || deposito.getCliente() == null || existe(deposito.getNumCuenta())
                || archivoCliente.buscar(deposito.getCliente().getCedula()) == null) {
            return false;
        }
        try {
            ArchivoUtil.agregar(archivo, deposito.getConsecutivo() + "\t" + ArchivoUtil.codificar(deposito.getNumCuenta()) + "\t"
                    + deposito.getTipoMoneda() + "\t" + deposito.getFechaApertura().getTime() + "\t"
                    + ArchivoUtil.codificar(deposito.getCliente().getCedula()) + "\t" + deposito.getPlazoVencimiento() + "\t"
                    + deposito.getMontoDeposito() + "\t" + deposito.getTasaInteresDeposito() + "\t " + "false");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean existe(String numeroCuenta) {
        return buscarPorNumeroCuenta(numeroCuenta) != null;
    }

    public DepositoPlazo buscarPorNumeroCuenta(String numeroCuenta) {
        try {
            for (String registro : ArchivoUtil.leer(archivo)) {
                String[] dato = registro.split("\t", -1);
                if (dato.length == 9 && dato[1].equals(numeroCuenta)) {
                    Cliente cliente = archivoCliente.buscar(dato[4]);
                    if (cliente == null) {
                        return null;
                    }
                    return new DepositoPlazo(Integer.parseInt(dato[0]), dato[1], Integer.parseInt(dato[5]),
                            Float.parseFloat(dato[6]), Float.parseFloat(dato[7]), dato[2].charAt(0), new Date(Long.parseLong(dato[3])), cliente);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return null;
        }
        return null;
    }
    
    public DepositoPlazo[] buscar(String cedula) {
        DepositoPlazo[] temporal = new DepositoPlazo[100];
        int contador = 0;

        try {
            for (String registro : ArchivoUtil.leer(archivo)) {
                String[] dato = registro.split("\t", -1);

                if (dato.length == 9 && dato[4].equals(cedula) && contador < temporal.length) {
                    Cliente cliente = archivoCliente.buscar(dato[4]);

                    if (cliente != null) {
                        temporal[contador++] = new DepositoPlazo(
                                Integer.parseInt(dato[0]), dato[1],
                                Integer.parseInt(dato[5]),
                                Float.parseFloat(dato[6]),
                                Float.parseFloat(dato[7]),
                                dato[2].charAt(0),
                                new Date(Long.parseLong(dato[3])), cliente);
                    }
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return new DepositoPlazo[0];
        }

        DepositoPlazo[] resultado = new DepositoPlazo[contador];

        for (int i = 0; i < contador; i++) {
            resultado[i] = temporal[i];
        }

        return resultado;
    }
    
    
    public DepositoPlazo[] consultarTodos() {
        DepositoPlazo[] temporal = new DepositoPlazo[100];
        int contador = 0;
        try {
            for (String registro : ArchivoUtil.leer(archivo)) {
                String[] dato = registro.split("\t", -1);
                if (dato.length == 9 && contador < temporal.length) {
                    Cliente cliente = archivoCliente.buscar(dato[4]);
                    if (cliente != null) {
                        temporal[contador++] = new DepositoPlazo(Integer.parseInt(dato[0]), dato[1],
                                Integer.parseInt(dato[5]), Float.parseFloat(dato[6]), Float.parseFloat(dato[7]), dato[2].charAt(0), new Date(Long.parseLong(dato[3])), cliente);
                    }
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return new DepositoPlazo[0];
        }
        DepositoPlazo[] resultado = new DepositoPlazo[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = temporal[i];
        }
        return resultado;
    }

    public boolean finalizar(String numeroCuenta) {
        try {
            String[] registros = ArchivoUtil.leer(archivo);
            boolean encontrado = false;
            for (int i = 0; i < registros.length; i++) {
                String[] dato = registros[i].split("\t", -1);
                if (dato.length == 9 && dato[1].equals(numeroCuenta)) {
                    if (Boolean.parseBoolean(dato[8])) {
                        return false;
                    }
                    dato[8] = "true";
                    registros[i] = String.join("\t", dato);
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

    public boolean estaFinalizado(String numeroCuenta) {
        try {
            for (String registro : ArchivoUtil.leer(archivo)) {
                String[] dato = registro.split("\t", -1);
                if (dato.length == 9 && dato[1].equals(numeroCuenta)) {
                    return Boolean.parseBoolean(dato[8]);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return false;
        }
        return false;
    }

    public boolean eliminar(String numeroCuenta) {
        try {
            String[] registros = ArchivoUtil.leer(archivo), temporal = new String[registros.length];
            int contador = 0;
            boolean eliminado = false;
            for (String registro : registros) {
                String[] dato = registro.split("\t", -1);
                if (dato.length == 9 && dato[1].equals(numeroCuenta)) {
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
