package Data;

import Model.Cliente;
import java.io.IOException;

public class ArchivoCliente {

    private final String archivo = "Cliente.txt";

    public boolean registrar(Cliente cliente) {
        if (cliente == null || cliente.getCedula() == null || existe(cliente.getCedula())) {
            return false;
        }
        try {
            ArchivoUtil.agregar(archivo, ArchivoUtil.codificar(cliente.getCedula()) + "\t" + ArchivoUtil.codificar(cliente.getNombre()) + "\t" + ArchivoUtil.codificar(cliente.getDireccion()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean existe(String cedula) {
        return buscar(cedula) != null;
    }

    public Cliente buscar(String cedula) {
        if (cedula == null) {
            return null;
        }
        try {
            String[] registros = ArchivoUtil.leer(archivo);
            for (String registro : registros) {
                String[] dato = registro.split("\t", -1);
                if (dato.length == 3 && dato[0].equals(cedula)) {
                    return new Cliente(dato[0], dato[1], dato[2]);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return null;
        }
        return null;
    }

    public Cliente[] consultarTodos() {
        Cliente[] resultado = new Cliente[100];
        int contador = 0;
        try {
            String[] registros = ArchivoUtil.leer(archivo);
            for (String registro : registros) {
                String[] dato = registro.split("\t", -1);
                if (dato.length == 3 && contador < resultado.length) {
                    resultado[contador++] = new Cliente(dato[0], dato[1], dato[2]);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            return new Cliente[0];
        }
        Cliente[] exacto = new Cliente[contador];
        for (int i = 0; i < contador; i++) {
            exacto[i] = resultado[i];
        }
        return exacto;
    }

    public boolean actualizar(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        try {
            String[] registros = ArchivoUtil.leer(archivo);
            boolean encontrado = false;
            for (int i = 0; i < registros.length; i++) {
                String[] dato = registros[i].split("\t", -1);
                if (dato.length == 3 && dato[0].equals(cliente.getCedula())) {
                    registros[i] = ArchivoUtil.codificar(cliente.getCedula()) + "\t" + ArchivoUtil.codificar(cliente.getNombre()) + "\t" + ArchivoUtil.codificar(cliente.getDireccion());
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

    public boolean eliminar(String cedula) {
        try {
            String[] registros = ArchivoUtil.leer(archivo);
            String[] temporal = new String[registros.length];
            int contador = 0;
            boolean eliminado = false;
            for (String registro : registros) {
                String[] dato = registro.split("\t", -1);
                if (dato.length == 3 && dato[0].equals(cedula)) {
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
