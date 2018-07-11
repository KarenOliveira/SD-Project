import java.rmi.registry.Registry;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
public class ServidorCalculadora {
  public static void main(String args[]) {
    try {
    	//System.setProperty("RMI1","192.168.1.48");
      //Crio o objeto servidor
      CalculadoraImpl calc = new CalculadoraImpl();
      //Criamos o stub do objeto que será registrado
      Calculadora stub = (Calculadora)UnicastRemoteObject.exportObject((Remote) calc, 0);
      //Registra (binds) o stub no registry
      Registry registry = LocateRegistry.createRegistry(9815);
      registry.bind("calculadora", (Remote) stub);
      System.out.println("Servidor iniciado.");
    } catch (Exception e) {
          System.err.println("Ocorreu um erro no servidor: " + e.toString());
    }
  }
}