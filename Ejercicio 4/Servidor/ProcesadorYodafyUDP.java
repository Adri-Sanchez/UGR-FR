package Servidor;

//
// YodafyServidorIterativo
// (CC) Adrián Sánchez Cerrillo & Robin Costas del Moral
//
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

//
// Nota: si esta clase extendiera la clase Thread, y el procesamiento lo hiciera el método "run()",
// ¡Podríamos realizar un procesado concurrente! 
//
public class ProcesadorYodafyUDP {
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private DatagramSocket socketServicio;
                  
                  private DatagramPacket paquete;
	
	private InetAddress direccion;
	
                  int puerto;
	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;
	
	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorYodafyUDP(DatagramSocket socketServicio) {
		this.socketServicio=socketServicio;
		random=new Random();
	}
	
	
	// Aquí es donde se realiza el procesamiento realmente:
	void procesa(){
		
		// Como máximo leeremos un bloque de 1024 bytes. Esto se puede modificar.
		byte [] datosRecibidos=new byte[1024];
		int bytesRecibidos=0;
		
		// Array de bytes para enviar la respuesta. Podemos reservar memoria cuando vayamos a enviarka:
		byte [] datosEnviar;
		
		
		try {
			
			
			paquete = new DatagramPacket(datosRecibidos, datosRecibidos.length);
			socketServicio.receive(paquete);
			direccion = paquete.getAddress();
			puerto = paquete.getPort();
			
			
                        
			// Yoda hace su magia:
			// Creamos un String a partir de un array de bytes de tamaÃ±o "bytesRecibidos":
			String mensaje = new String(datosRecibidos,0,paquete.getLength());
			// Yoda reinterpreta el mensaje:
			String respuesta=yodaDo(mensaje);
			// Convertimos el String de respuesta en una array de bytes:
			datosEnviar=respuesta.getBytes();
			
			
			
			paquete = new DatagramPacket(datosEnviar, datosEnviar.length, direccion, puerto);
			socketServicio.send(paquete); 
			///////////////////////////////////////////////////////
			
			
		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";
		
		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];
			
			s[j]=s[k];
			s[k]=tmp;
		}
		
		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}
		
		return resultado;
	}
}
