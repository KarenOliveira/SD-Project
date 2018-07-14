package aSD;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PartImpl implements Part{
	
	public static int count = 0;
	public Integer id;
	public String nome;
	public String descricao;
	public boolean subpart;
	public int quantidade;
	public Map<Integer, Integer> partList;
	public Scanner sc = new Scanner(System.in);

	public PartImpl(String nm, String desc, boolean subp, int quant, Map<Integer, Integer> partL) {
		count++;
		this.id = count;
		this.nome = nm;
		this.descricao = desc;
		this.subpart = subp;
		this.quantidade = quant;
		this.partList = partL;
	}

	@Override
	public void conectar() throws RemoteException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha a porta a se conectar ");
	    int port = sc.nextInt();
		Registry registry = LocateRegistry.getRegistry(port);
	}

	@Override
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
	
	
	public void setQntd(int qntd) throws RemoteException{
		this.quantidade = qntd;
	}
	

	@Override
	public void getrepName(Registry r) throws RemoteException {
		
		
		
	}

	@Override
	public void getQuant(Registry r) throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public void listPecas(Registry r) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void buscaPeca(Registry r) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addsubpart(Registry r) throws RemoteException {
    	System.out.println("Insira o ID e quantidade da peça: ");
    	int x = sc.nextInt();
    	int y = sc.nextInt();
    	ClientPart.subpAtual.put(x,y);
	}

	@Override
	public void showp(Registry r) throws RemoteException {
		System.out.println("Nome: "+ ClientPart.pecaAtual.nome + "\n" + 
						   "id: "+ ClientPart.pecaAtual.id + "\n" + 
						   "desc: "+ ClientPart.pecaAtual.descricao + "\n" + 
						   "quant: "+ ClientPart.pecaAtual.quantidade + "\n" + 
						   "Nome: "+ ClientPart.pecaAtual.nome);
	}

	@Override
	public void addnewp(Registry r) throws RemoteException {
		System.out.println("Informe o nome da peça: ");
		String name = sc.nextLine();
		System.out.println("Informe a descrição da peça: ");
		String description = sc.nextLine();
		System.out.println("Informe a quantidade de Peças: ");
		int qntd = sc.nextInt();
		System.out.println("A peça possui subparts(s/n) ");
		boolean subp = false;
		if(sc.nextLine().equals("s")) subp = true; 
		PartImpl parteNova = new PartImpl(name,description,subp,qntd,ClientPart.subpAtual);
		try {
			r.bind(parteNova.id.toString(), parteNova);
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
