package aSD;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.registry.Registry;

public interface Gerenciador extends Remote{
	
	public void conectar()
		throws RemoteException;
	public void addPeca(Registry r)
		throws RemoteException;
	public void addnewp(Registry r)
		throws RemoteException;
	public void getrepName(Registry r)
		throws RemoteException;
	public void listPecas(Registry r)
		throws RemoteException;
	public void buscaPeca(Registry r)
		throws RemoteException;
	public void addsubpart(Registry r)
		throws RemoteException;
	public void showp(Registry r)
		throws RemoteException;
	public void showsubp(Registry r)
		throws RemoteException;
}
