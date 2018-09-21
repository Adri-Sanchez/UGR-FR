package Cliente;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class YodafyClienteTCP {

	public static void main(String[] args) {
		
                                    String cadena;
		int bytesLeidos=0;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		// Socket para la conexión TCP
		Socket socketServicio=null;
		
		try {
			// Creamos un socket que se conecte a "hst" y "port":
			//////////////////////////////////////////////////////
			socketServicio = new Socket(host, port);
			//////////////////////////////////////////////////////			
			
			PrintWriter outPrinter  = new PrintWriter (socketServicio.getOutputStream(), true);
			BufferedReader inReader = new BufferedReader ( new InputStreamReader (socketServicio.getInputStream()));
			
			// Enviamos el array por PrintWriter;
			//////////////////////////////////////////////////////
			outPrinter.println("Al monte del volcÃ¡n debes ir sin demora");
			//////////////////////////////////////////////////////
			
			// Cerramos outPrinter
			//////////////////////////////////////////////////////
			//outPrinter.close();
			//////////////////////////////////////////////////////
			
			// Leemos la respuesta del servidor. 
			//////////////////////////////////////////////////////
			cadena = inReader.readLine();
			//////////////////////////////////////////////////////
                        
                                                      // Cerramos inReader
			//////////////////////////////////////////////////////
			//inReader.close();
			//////////////////////////////////////////////////////
			
			// Mostremos la cadena de caracteres recibidos:
			System.out.println("Recibido: " + cadena);
			
			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			socketServicio.close();
			//////////////////////////////////////////////////////
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
