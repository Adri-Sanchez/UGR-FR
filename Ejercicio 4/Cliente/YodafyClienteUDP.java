package Cliente;

//
// YodafyServidorIterativo
// (CC) Adrián Sánchez Cerrillo & Robin Costas del Moral
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class YodafyClienteUDP {

	public static void main(String[] args) {
		
		byte []buferEnvio;
		byte []buferRecepcion=new byte[256];
		int bytesLeidos=0;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		// Socket para la conexión UDP
                                    DatagramSocket socket;
                                    // Datagram Packet para conexión UDP
                                    DatagramPacket paquete, paqueteRecibido;
                                    // Dirección para conexión UDP
                                    InetAddress direccion;
		
		try {
			// Creamos un socket que se conecte a "hst" y "port":
			//////////////////////////////////////////////////////
			socket = new DatagramSocket();
			//////////////////////////////////////////////////////
	
                                                       direccion = InetAddress.getByName(host);
                       
			buferEnvio="Al monte del volcán debes ir sin demora".getBytes();
			
                                                      // Generamos el paquete a enviar por UDP
                                                      //////////////////////////////////////////////////////
                                                      paquete = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
                                                      //////////////////////////////////////////////////////
                                                      
                                                      // Enviamos el DatagramPacket
                                                      //////////////////////////////////////////////////////
                                                      socket.send(paquete);
                                                      //////////////////////////////////////////////////////
                                                      
                                                      
			// Leemos la respuesta del servidor.
			////////////////////////////////////////////////////// 
			paqueteRecibido = new DatagramPacket(buferRecepcion, buferRecepcion.length);
                                                      socket.receive(paqueteRecibido);
			//////////////////////////////////////////////////////
                   
                                                      // Una vez terminado el servicio, cerramos el socket.
			//////////////////////////////////////////////////////
			socket.close();
			//////////////////////////////////////////////////////
                                            
			// Mostremos la cadena de caracteres recibidos:
			System.out.println("Recibido: ");
			System.out.println(new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength()));
		
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
