package aSD;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Gerenciador extends Remote{
	
	public void conectar()
		throws RemoteException;
	public void addPeca()
		throws RemoteException;
	public void getrepName()
		throws RemoteException;
	public int getQuant()
		throws RemoteException;
	public int listPecas()
		throws RemoteException;
	public void buscaPeca(int id)
		throws RemoteException;
}
