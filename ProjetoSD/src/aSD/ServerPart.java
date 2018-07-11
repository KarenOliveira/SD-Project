package aSD;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerPart {
	  public static void main(String args[]) {
		    try {
		    	//System.setProperty("RMI1","192.168.1.48");
		      //Crio o objeto servidor
		      GerenciadorImpl gere = new GerenciadorImpl();
		      //Criamos o stub do objeto que será registrado
		      Gerenciador stub = (Gerenciador)UnicastRemoteObject.exportObject((Remote) gere, 0);
		      //Registra (binds) o stub no registry
		      Registry registry = LocateRegistry.createRegistry(9815);
		      registry.bind("Gerenciador", (Remote) stub);
		      System.out.println("Servidor iniciado.");
		    } catch (Exception e) {
		          System.err.println("Ocorreu um erro no servidor: " + e.toString());
		    }
		  }

}
