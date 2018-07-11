package aSD;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class GerenciadorImpl implements Gerenciador{
	
	Scanner sc = new Scanner(System.in);

	@Override
	public void conectar() throws RemoteException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha a porta a se conectar ");
	    int port = sc.nextInt();
		Registry registry = LocateRegistry.getRegistry(port);
	}

	@Override
	public void addPeca() throws RemoteException {
		
	}

	@Override
	public void getrepName() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getQuant() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int listPecas() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void buscaPeca() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addsubpart() throws RemoteException {
    	System.out.println("Insira o ID e quantidade da peça: ");
    	int x = sc.nextInt();
    	int y = sc.nextInt();
    	ClientPart.listAtual.put(x,y);
	}

}
