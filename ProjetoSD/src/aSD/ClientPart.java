package aSD;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientPart{
	
    static transient Scanner sc = new Scanner(System.in);
    
	public static void main(String[] args) {
		
	    try {

	       // Localiza o registry. É possível usar endereço/IP porta
	    	
	    	//String porta  = null;
	    	int port = 0;
	    	String comando = "Init";
	    	while(!comando.equals("Quit")) {
		    	
		    	System.out.println("Escolha a porta a se conectar: ");
				comando = sc.nextLine();
				port = Integer.parseInt(comando);
				
				try {
				
					Registry registry = LocateRegistry.getRegistry(port);
			        // Consulta o registry e obtém o stub para o objeto remoto
			        Gerenciador gere = (Gerenciador) registry.lookup("Server " + port);
			        System.out.println("Conectado à porta " + port);
					
			        // A partir deste momento, cahamadas ao Gerenciador podem ser
			        // feitas como qualquer chamada a métodos
			        
			        Conexão(registry, gere);
				} catch(IOException e) {
					System.out.println("Porta inválida");
				}
	    	}

	    } catch (Exception e) {
	       System.err.println("Ocorreu um erro no cliente: " + e.toString());
	    }
	 }
	
	public static void Conexão(Registry registry, Gerenciador gere) {
        Scanner sn = new Scanner(System.in);
        String comando = "Init";
        
        try {
        
	        while(!comando.equals("Quit")){
	        	
	        	 System.out.println("Aguardando comando: ");
	        	 comando = sn.nextLine();
	        
		        switch(comando) {
		        case "addnewp":
		        	gere.addnewp(registry);
		        	break;
		        case "addp":
		        	gere.addPeca(registry);
		        	break;
		        case "addsubpart":
		        	gere.addsubpart(registry);
		        	break;
		        case "bind":
		        	gere.conectar();
		        	break;
		        case "clearlist":
		        	gere.clear();
		        	break;
		        case "getp":
		        	gere.getp(registry);
		        	break;
		        case "listp":
		        	gere.listPecas(registry);
		        	break;
		        case "showp":
		        	gere.showp(registry);
		        	break;
		        case "showsubp":
		        	gere.showsubp(registry);
		        	break;
		        case "Quit":
		        	break;
		        default:
		        	System.out.println("Esse não é um comando válido!!!");
	          }
	      }
	
        } catch(Exception e) {
        	System.err.println("Ocorreu um erro na conexão: " + e.toString());
        }
        
    System.out.println("Conexão encerrada");
        
	}
	
	
}
