package aSD;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public interface Gerenciador extends Remote{
	
	public void addP(Registry r)
		throws RemoteException;
	public String addnewp(Registry r,String name,String description,int qntd, String conf)
		throws RemoteException;
	public String getrepName(Registry r)
		throws RemoteException;
	public String listp(Registry r)
		throws RemoteException;
	public String getp(Registry r,String id)
		throws RemoteException;
	public String addsubp(Registry r, String id)
		throws RemoteException;
	public String showp(Registry r)
		throws RemoteException;
	public String showsubp(Registry r)
		throws RemoteException;
	public void clear()
		throws RemoteException;
	public String help()
		throws RemoteException;
}
