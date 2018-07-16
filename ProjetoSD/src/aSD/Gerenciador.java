package aSD;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.registry.Registry;

public interface Gerenciador extends Remote{
	
	public void addPeca(Registry r)
		throws RemoteException;
	public void addnewp(Registry r)
		throws RemoteException;
	public void getrepName(Registry r)
		throws RemoteException;
	public void listPecas(Registry r)
		throws RemoteException;
	public void getp(Registry r)
		throws RemoteException;
	public void addsubpart(Registry r)
		throws RemoteException;
	public void showp(Registry r)
		throws RemoteException;
	public void showsubp(Registry r)
		throws RemoteException;
	public void clear()
		throws RemoteException;
	public void ajuda()
		throws RemoteException;
}
