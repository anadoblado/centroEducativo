package visual;


import java.io.BufferedReader;

import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class Utils {

	
	public static int obtenerNumeroAzar () {
		 return (int) Math.round(Math.random() * 100);
	}
	
	public static int obtenerNumeroAzar (int min, int max) {
		 return (int) Math.round(Math.random() * (max - min)) + min;
	}
	 
	public static void main (String args[]) {
		System.out.print(obtenerNumeroAzar (-2, 5));
	}
	
	
	
	
	public static int obtenerEntero () {
		int numero = 0;
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader (isr);
			numero = Integer.parseInt (br.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numero;
	}


	
	/**
	 * 
	 * @return
	 */
	public static int obtenerNumUsuario () {
		int num = obtenerNumUsuario("Introduzca número");
		return num;
	}
	/**
	 * 
	 * @param mensaje
	 * @return
	 */
	public static int obtenerNumUsuario (String mensaje) {
		int num = obtenerNumUsuario(mensaje, -2147483648, 2147483647);
		return num;
	}
	
	
	/**
	 * 
	 * @param mensaje
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	public static int obtenerNumUsuario (String mensaje, int minimo, int maximo) {
		int num;
		String mensajeError = "";
		do {
			num = Integer.parseInt(JOptionPane.showInputDialog(mensajeError + mensaje));
			if (num < minimo || num > maximo) {
				mensajeError = "El número debe estar entre " + minimo + " y " + maximo + " - ";
			}
		} while (num < minimo || num > maximo);
		return num;
	}




}
