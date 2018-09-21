package Servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativoUDP
// (CC) Adrián Sánchez Cerrillo & Robin Costas del Moral
//
public class YodafyServidorIterativoUDP {

	public static void main(String[] args) {
	
		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		byte []buffer=new byte[256];
		// Número de bytes leídos
		int bytesLeidos=0;
		
		DatagramSocket socketServidor;

		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
			socketServidor = new DatagramSocket(port);
			//////////////////////////////////////////////////
			
			// Mientras ... siempre!
			do {
				
				// Creamos un objeto de la clase ProcesadorYodafyUDP, pasándole como 
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				ProcesadorYodafyUDP procesador=new ProcesadorYodafyUDP(socketServidor);
				procesador.procesa();
				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
