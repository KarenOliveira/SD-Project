package aSD;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientPart {

	public static void main(String[] args) {
		
		
		
	    try {

	       // Localiza o registry. É possível usar endereço/IP porta
	       Scanner sc = new Scanner(System.in);
	       String input = null;
	       
	       System.out.print("Escolha o servidor a se conectar");
	       
	       while(!input.equals("Quit")) {
	    	   input = sc.nextLine();
	       }
	       
	      
	       
	       
	       Registry registry = LocateRegistry.getRegistry(9815);
	       // Consulta o registry e obtém o stub para o objeto remoto
	       Gerenciador gere = (Gerenciador) registry.lookup("Gerenciador");
	       // A partir deste momento, cahamadas à Caluladora podem ser
	       // feitas como qualquer chamada a métodos
	       
	       

	    } catch (Exception e) {
	       System.err.println("Ocorreu um erro no cliente: " + e.toString());
	    }
	  }
	
}
