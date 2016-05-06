package cifradocesar;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Cesar {
	
	public static void main (String[] args){
		Path ficheroEntrada = Paths.get("mensaje.txt");
		Path ficheroSalida = Paths.get("mensajeMay.txt");
		
		System.out.println(ficheroEntrada.getFileName().toAbsolutePath());
		
		BufferedReader input = null;
		BufferedWriter output = null;
		
		//Charset charset = Charset.forName("UTF-8");
		
		try{
			input = Files.newBufferedReader(ficheroEntrada);
			output = Files.newBufferedWriter(ficheroSalida);
			
			String bufferIn = "";
			while ( (bufferIn = input.readLine()) != null){
				bufferIn = bufferIn.toUpperCase();
				output.write(bufferIn);
			}
			
			input.close();
			output.close();
			
			codificar(ficheroSalida);
			
			visualizar(ficheroEntrada);
			visualizar(ficheroSalida);
			visualizar(Paths.get("codificado.txt"));
			
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public static void visualizar(Path fichero){
		BufferedReader input = null;
		
		try{
			input = Files.newBufferedReader(fichero);		
			String bufferIn = null;
			while ( (bufferIn = input.readLine()) != null){
				System.out.println(bufferIn);
			}
			
			input.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void codificar(Path fichero){
		Path salida = Paths.get("codificado.txt");
		
		InputStream istream=null;
		OutputStream ostream=null;
		int c;
		
		
		try {
			//InputStream y OutputStream de java.io nos permiten trabajar byte a byte
			//Los generamos de forma eficiente utilizando java.nio
			istream = Files.newInputStream(fichero);
			ostream = Files.newOutputStream(salida);
			while ((c = istream.read()) != -1) {
				if(c >= 65 && c <= 87){
					c+=3;
				}else if(c == 88){
					c=65;
				}else if(c == 89){
					c=66;
				}else if(c == 90){
					c=67;
				}
				ostream.write(c);
			}
			istream.close();
			ostream.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
