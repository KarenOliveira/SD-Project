package aSD;
import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClientPart{
	
    public static PartImpl pecaAtual;
    static Map<PartImpl,Integer> subpAtual = new HashMap<PartImpl,Integer>();
    static transient Scanner sc = new Scanner(System.in);
    
	public static void main(String[] args) {
		
	    try {

	       // Localiza o registry. É possível usar endereço/IP porta
	    	
	    	String porta  = null;
	    	int port = 0;
	    	
	    	 System.out.println("Escolha a porta a se conectar: ");
			 port = sc.nextInt();
			 Registry registry = LocateRegistry.getRegistry(9815);
			 	       
	        // Consulta o registry e obtém o stub para o objeto remoto
	        Gerenciador gere = (Gerenciador) registry.lookup("Gerenciador");
	        System.out.println("Conectado à porta " + port);
	        
	        //registry.bind("nome", gere);
	        
	        // A partir deste momento, cahamadas ao Gerenciador podem ser
	        // feitas como qualquer chamada a métodos
	        
	       
	        Scanner sn = new Scanner(System.in);
	        String comando = null;
	              
	        while(comando != "Quit"){
	        	
	        	 System.out.println("Aguardando comando: ");
	        	 comando = sn.nextLine();
	        
		        switch(comando) {
		        case "bind":
		        	gere.conectar();
		        	break;
		        case "listp":
		        	gere.listPecas(registry);
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
		        	gere.addnewp(registry);
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
