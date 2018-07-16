package aSD;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ServerPart {
	  public static void main(String args[]) {
		    try {
		    	Scanner sc = new Scanner(System.in);
		    	//System.setProperty("RMI1","192.168.1.48");
		    	System.out.println("Escolha a porta a instanciar o servidor: ");
		    	int port = sc.nextInt();
		      //Crio o objeto servidor
		      GerenciadorImpl gere = new GerenciadorImpl();
		      //Criamos o stub do objeto que será registrado
		      Gerenciador stub = (Gerenciador)UnicastRemoteObject.exportObject((Remote) gere, 0);
		      //Registra (binds) o stub no registry
		      Registry registry = LocateRegistry.createRegistry(port);
		      registry.bind("Server " + port, (Remote) stub);
		      System.out.println("Servidor iniciado.");
		    } catch (Exception e) {
		          System.err.println("Ocorreu um erro no servidor: " + e.toString());
		    }
		  }

}
