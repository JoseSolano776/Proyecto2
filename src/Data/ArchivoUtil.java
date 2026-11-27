package Data;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class ArchivoUtil {

    private static final String CARPETA = "Data";
    
    public static String codificar(String texto) {
        return texto == null ? "" : texto;
    }

    public static String decodificar(String texto) {
        return texto == null ? "" : texto;
    }

    public static String[] leer(String nombreArchivo) throws IOException {
        File archivo = obtenerArchivo(nombreArchivo);
        int cantidad = 0;
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            while (raf.readLine() != null) {
                cantidad++;
            }
        }
        String[] registros = new String[cantidad];
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "r")) {
            for (int i = 0; i < cantidad; i++) {
                String linea = raf.readLine();
                byte[] bytes = linea.getBytes(StandardCharsets.ISO_8859_1);
                registros[i] = new String(bytes, StandardCharsets.UTF_8);
            }
        }
        return registros;
    }

    public static void agregar(String nombreArchivo, String registro) throws IOException {
        File archivo = obtenerArchivo(nombreArchivo);
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            raf.seek(raf.length());
            raf.write((registro + "\n").getBytes(StandardCharsets.UTF_8));
        }
    }

    public static void reescribir(String nombreArchivo, String[] registros) throws IOException {
        File archivo = obtenerArchivo(nombreArchivo);
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            raf.setLength(0);
            if (registros != null) {
                for (String registro : registros) {
                    if (registro != null) {
                        raf.write((registro + "\n").getBytes(StandardCharsets.UTF_8));
                    }
                }
            }
        }
    }

    private static File obtenerArchivo(String nombreArchivo) throws IOException {
        File carpeta = new File(CARPETA);
        if (!carpeta.exists() && !carpeta.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta Data");
        }
        File archivo = new File(carpeta, nombreArchivo);
        if (!archivo.exists() && !archivo.createNewFile()) {
            throw new IOException("No se pudo crear el archivo");
        }
        return archivo;
    }
}
