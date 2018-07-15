package aSD;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class GerenciadorImpl implements Gerenciador{
	
	static transient Scanner sc = new Scanner(System.in);

	public void addnewp(Registry r) throws RemoteException {
		PartImpl parteNova = null;
		
		System.out.println("Informe o nome da peça: ");
		String name = sc.nextLine();
		System.out.println("Informe a descrição da peça: ");
		String description = sc.nextLine();
		System.out.println("Informe a quantidade de Peças: ");
		int qntd = sc.nextInt();
		System.out.println("A peça possui subparts(s/n) ");
		boolean subp = false;
		
		if(sc.nextLine().equals("s")) {
		subp = true;
		parteNova = new PartImpl(name,description,subp,qntd,ClientPart.subpAtual);
		} else { parteNova = new PartImpl(name,description,subp,qntd,null); }
		
		try {
			r.bind(parteNova.id.toString(), parteNova);
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addPeca(Registry r) throws RemoteException {
		
		System.out.println("Informe o ID da peça a ser adicionada: ");
		String id = sc.nextLine();
		String[] nomes = r.list();
		
		for(int i=0;i<nomes.length;i++) {
			if(nomes[i].equals(id)) {
				try {
					System.out.println("Informe a quantidade a ser adicionada: ");
					int q = sc.nextInt();
					((PartImpl) r.lookup(id)).quantidade += q;
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		}
		
	}
	
	public void addsubpart(Registry r) throws RemoteException {
		
		System.out.println("Informe o ID da Subpeça: ");
		String id = sc.nextLine();
		boolean jaCadastrado = false;
		String[] nomes = r.list();
		
		for (int i = 0;i < nomes.length;i++) {
			if (id.equals(nomes[i])) jaCadastrado = true;
		}
		
		if (jaCadastrado == true) {
		    try {
				System.out.println("Informe a quantidade de Subpeças nessa Peças ");
				Integer qntdP = sc.nextInt();
			
				ClientPart.subpAtual.put(((PartImpl) r.lookup(id)), qntdP);
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { System.out.println("Subpeça não cadastrada"); }
		
	}
	
	public void buscaPeca(Registry r) throws RemoteException {
		
		System.out.println("Informe o ID da Subpeça: ");
		String id = sc.nextLine();
		String[] nomes = r.list();
		
		try {
			for (int i = 0;i < nomes.length;i++) {
				if (id.equals(nomes[i])) { 
					ClientPart.pecaAtual = (PartImpl) r.lookup(id);
					System.out.println("Peça adicionada ao repositório atual");
				} else { System.out.println("Peça não encontrada"); }
			}
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void conectar() throws RemoteException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha a porta a se conectar ");
	    int port = sc.nextInt();
		Registry registry = LocateRegistry.getRegistry(port);
	}

	public void getrepName(Registry r) throws RemoteException {
		
		
		
	}

	public void getQuant(Registry r) throws RemoteException {
		// TODO Auto-generated method stub
	}

	public void listPecas(Registry r) throws RemoteException {
		
		String[] list = r.list();
		try {
			for(int i = 1;i<list.length;i++) {
				
					System.out.println("Nome: " + ((PartImpl) r.lookup(list[i])).nome);
					System.out.println("ID: " + ((PartImpl) r.lookup(list[i])).id);
			}
		}
		
		catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showp(Registry r) throws RemoteException {
		System.out.println("Nome: "+ ClientPart.pecaAtual.nome + "\n" + 
						   "id: "+ ClientPart.pecaAtual.id + "\n" + 
						   "desc: "+ ClientPart.pecaAtual.descricao + "\n" + 
						   "quant: "+ ClientPart.pecaAtual.quantidade + "\n" + 
						   "Nome: "+ ClientPart.pecaAtual.nome);
	}

}
