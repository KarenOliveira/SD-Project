package aSD;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClientPart {
	
    public static PartImpl pecaAtual;
    static Map<Integer,Integer> subpAtual = new HashMap<Integer,Integer>();
    
	public static void main(String[] args) {
		
	    try {

	       // Localiza o registry. É possível usar endereço/IP porta
	    	Scanner sc = new Scanner(System.in);
	    	String porta  = null;
	    	int port = 0;
	    	
	    	 System.out.println("Escolha a porta a se conectar ");
			 porta = sc.nextLine();
			 if(!porta.equals("Quit")) {port = Integer.parseInt(porta);} 
			 Registry registry = LocateRegistry.getRegistry(port);
	       
	        // Consulta o registry e obtém o stub para o objeto remoto
	        Gerenciador gere = (Gerenciador) registry.lookup("Gerenciador");
	        System.out.println("Conectado à porta " + port);
	        
	        // A partir deste momento, cahamadas ao Gerenciador podem ser
	        // feitas como qualquer chamada a métodos
	        
	        String comando = null;
	        comando = sc.nextLine();
	        
	        switch(comando) {
	        case "bind":
	        	gere.conectar();
	        	break;
	        case "listp":
	        	registry.list();
	        	break;
	        case "getp":
	        	gere.buscaPeca(registry);
	        	break;
	        case "showp":
	        	gere.showp(registry);
	        	break;
	        case "clearlist":
	        	break;
	        case "addsubpart":
	        	gere.addsubpart(registry);
	        	break;
	        case "addp":
	        	gere.addPeca(registry);
	        	break;
	        case "addnewp":
	        	gere.addPeca(registry);
	        	break;
	        case "quit":
	        	break;
	        default:
	        	System.out.println("Esse não é um comando válido!!!");
	        }
	        
	      

	    } catch (Exception e) {
	       System.err.println("Ocorreu um erro no cliente: " + e.toString());
	    }
	  }
	
}
