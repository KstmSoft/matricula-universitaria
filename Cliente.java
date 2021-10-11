import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private Socket conexion;

    public synchronized void conectar(String direccionServer){
        try {
            conexion = new Socket(direccionServer, 12345); //El socket del cliente, el 12345 es el puerto
            //el 2 el nombre del pc o servidor de la ip
            //obtenemos flujos de entrada y salida
            entrada = new ObjectInputStream(conexion.getInputStream()); //construimos el inputstream
            salida = new ObjectOutputStream(conexion.getOutputStream()); //outputstream
            salida.flush(); //Vaciamos el outputstream

        } catch (UnknownHostException ex){
            System.out.println("UnknownHostException" + ex.getMessage());
        } catch (IOException ex){ //Posibles errores. getMessage explica el error más a fondo. es todo una plantilla
            System.out.println("IOException" + ex.getMessage());
        }
    }




}
