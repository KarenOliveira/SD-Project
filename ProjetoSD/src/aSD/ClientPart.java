package aSD;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClientPart {
	
    static Part pecaAtual;
    static Map<Integer,Integer> listAtual = new HashMap<Integer,Integer>();
    
	public static void main(String[] args) {
		
	    try {

	       // Localiza o registry. É possível usar endereço/IP porta
	    	Scanner sc = new Scanner(System.in);
	    	String porta  = null;
	    	int port = 0;
	    	
	       while(!porta.equals("Quit")) {
	    	   porta = sc.nextLine();
	    	   System.out.println("Escolha a porta a se conectar ");
			   porta = sc.nextLine();
			   if(!porta.equals("Quit")) {port = Integer.parseInt(porta);} 
			   Registry registry = LocateRegistry.getRegistry(port);
	       
	        // Consulta o registry e obtém o stub para o objeto remoto
	        Gerenciador gere = (Gerenciador) registry.lookup("Gerenciador");
	        // A partir deste momento, cahamadas ao Gerenciador podem ser
	        // feitas como qualquer chamada a métodos
	        
	        String comando = null;
	        
	        switch(comando) {
	        case "listp":
	        	registry.list();
	        	break;
	        case "getp":
	        	gere.buscaPeca();
	        	break;
	        case "showp":
	        	break;
	        case "clearlist":
	        	break;
	        case "addsubpart":
	        	gere.addsubpart();
	        	break;
	        case "addp":
	        	break;
	        case "quit":
	        	break;
	        default:
	        	System.out.println("Esse não é um comando válido!!!");
	        }
	        	
	        
	        
	        }

	    } catch (Exception e) {
	       System.err.println("Ocorreu um erro no cliente: " + e.toString());
	    }
	  }
	
}
