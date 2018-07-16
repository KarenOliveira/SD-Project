package aSD;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientPart{
	
    static transient Scanner sc = new Scanner(System.in);
    
	public static void main(String[] args) {
		
	    try {

	       // Localiza o registry. � poss�vel usar endere�o/IP porta
	    	
	    	//String porta  = null;
	    	int port = 0;
	    	String comando = "Init";
	    	while(!comando.equals("Quit")) {
		    	try {
		    	System.out.println("Escolha a porta a se conectar: ");
				comando = sc.nextLine();
				port = Integer.parseInt(comando);
		    	}catch(NumberFormatException e) {
		    		System.out.println("As portas s�o numericas");
		    	}
				try {
				
					Registry registry = LocateRegistry.getRegistry(port);
			        // Consulta o registry e obt�m o stub para o objeto remoto
			        Gerenciador gere = (Gerenciador) registry.lookup("Server " + port);
			        System.out.println("Conectado � porta " + port);
					
			        // A partir deste momento, cahamadas ao Gerenciador podem ser
			        // feitas como qualquer chamada a m�todos
			        
			        Conex�o(registry, gere);
				} catch(IOException e) {
					System.out.println("Porta inv�lida");
				}
	    	}

	    } catch (Exception e) {
	       System.err.println("Ocorreu um erro no cliente: " + e.toString());
	    }
	 }
	
	public static void Conex�o(Registry registry, Gerenciador gere) {
        String comando = "Init";
        Scanner sn = new Scanner(System.in);
        
        try {
        
	        while(!comando.equals("quit")){
	        		
	        	 System.out.println("Aguardando comando: ");
	        	 
	        	 comando = sn.nextLine();
	        
		        switch(comando) {
		        case "addnewp":
		    		int qntd = 0;
		    		System.out.println("Informe o nome da pe�a: ");
		    		String name = sc.nextLine();
		    		System.out.println("Informe a descri��o da pe�a: ");
		    		String description = sc.nextLine();
		    		try {
		    		System.out.println("Informe a quantidade de Pe�as: ");
		    		qntd = Integer.parseInt(sc.nextLine());
		    		}catch (NumberFormatException e) {
		    			e.printStackTrace();
		    		}
		    		System.out.println("A pe�a possui subparts(s/n): ");
		    		String conf = sc.nextLine();
		        	System.out.println(gere.addnewp(registry,name,description,qntd,conf));
		        	break;
		        case "addsubp":
		    		System.out.println("Informe o ID da Subpe�a: ");
		    		String i = sc.nextLine();
		        	System.out.println(gere.addsubp(registry,i));
		        	break;
		        case "clear":
		        	gere.clear();
		        	break;
		        case "getp":
		        	System.out.println("Informe o ID da pe�a: ");
		        	String id = sc.nextLine();
		        	String getp = gere.getp(registry, id);
		        	System.out.println(getp);
		        	break;
		        case "getrepname":
		        	System.out.println(gere.getrepName(registry));
		        	break;
		        case "listp":
		        	String listp = gere.listp(registry);
		        	System.out.println(listp);
		        	break;
		        case "showp":
		        	String showp = gere.showp(registry);
		        	System.out.println(showp);
		        	break;
		        case "showsubp":
		        	System.out.println(gere.showsubp(registry));
		        	break;
		        case "quit":
		        	break;
		        case "help":
		        	String help = gere.help();
		        	System.out.println(help);
		        	break;
		        default:
		        	System.out.println("Esse n�o � um comando v�lido!!!");
	          }
		        System.out.println("----------------------------------------\n");
	      }
	
        } catch(Exception e) {
        	System.err.println("Ocorreu um erro na conex�o: " + e.toString());
        }
        
    System.out.println("Conex�o encerrada");
        
	}
	
	
}
