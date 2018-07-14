package aSD;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public interface Part extends Remote{
	
	public void conectar()
			throws RemoteException;
		public void addPeca(Registry r)
			throws RemoteException;
		public void addnewp(Registry r)
			throws RemoteException;
		public void getrepName(Registry r)
			throws RemoteException;
		public void getQuant(Registry r)
			throws RemoteException;
		public void listPecas(Registry r)
			throws RemoteException;
		public void buscaPeca(Registry r)
			throws RemoteException;
		public void addsubpart(Registry r)
			throws RemoteException;
		public void showp(Registry r)
			throws RemoteException;
		public void setQntd(int qntd)
			throws RemoteException;
	
}

