package aSD;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class GerenciadorImpl implements Gerenciador{
	
	public static PartImpl pecaAtual;
	public static HashMap<Integer,Integer> subpAtual = new HashMap<Integer,Integer>();
	static transient Scanner sc = new Scanner(System.in);

	public void addnewp(Registry r) throws RemoteException {
		PartImpl parteNova = null;
		System.out.println("----------------------------------------\n");
		System.out.println("Informe o nome da pe�a: ");
		String name = sc.nextLine();
		System.out.println("Informe a descri��o da pe�a: ");
		String description = sc.nextLine();
		System.out.println("Informe a quantidade de Pe�as: ");
		int qntd = Integer.parseInt(sc.nextLine());
		System.out.println("A pe�a possui subparts(s/n): ");
		String conf = sc.nextLine();
		if(conf.equals("s"))  parteNova = new PartImpl(name,description,true,qntd,subpAtual);
		else parteNova = new PartImpl(name,description,false,qntd,null);
		
		try {
			r.bind(parteNova.id.toString(), parteNova);
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		System.out.println("Pe�a adicionada");
		System.out.println("----------------------------------------\n");
		
	}
	
	public void addPeca(Registry r) throws RemoteException {
		
		System.out.println("Informe o ID da pe�a a ser adicionada: ");
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
		
		System.out.println("Informe o ID da Subpe�a: ");
		String id = sc.nextLine();
		boolean jaCadastrado = false;
		String[] nomes = r.list();
		
		for (int i = 0;i < nomes.length;i++) {
			if (id.equals(nomes[i])) jaCadastrado = true;
		}
		
		if (jaCadastrado == true) {
		    System.out.println("Informe a quantidade de Subpe�as nessa Pe�as ");
			Integer qntdP = Integer.parseInt(sc.nextLine());
			
			subpAtual.put(Integer.parseInt(id), qntdP);
		} else { System.out.println("Subpe�a n�o cadastrada"); }
		
	}
		
	public void buscaPeca(Registry r) throws RemoteException {
		
		System.out.println("Informe o ID da Subpe�a: ");
		String id = sc.nextLine();
		String[] nomes = r.list();
		
		try {
			for (int i = 0;i < nomes.length;i++) {
				if (id.equals(nomes[i])) { 
					pecaAtual = (PartImpl) r.lookup(id);
					System.out.println("Pe�a selecionada, use o comando showp para visualizar");
				} else { System.out.println("Pe�a n�o encontrada"); }
			}
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void conectar() throws RemoteException {
		
		
	}

	public void getrepName(Registry r) throws RemoteException {
			System.out.println("Server atual: " + r.list()[0]);
	}

	public void listPecas(Registry r) throws RemoteException {
		
		String[] list = r.list();
		
		System.out.println("----------------------------------------\n");
		
		try {
			for(int i = 1;i<list.length;i++) {
				System.out.println("Nome: " + ((PartImpl) r.lookup(list[i])).nome);
				System.out.println("ID: " + ((PartImpl) r.lookup(list[i])).id);
			}
		}
		catch (NotBoundException e) { e.printStackTrace(); }
		
		System.out.println("----------------------------------------\n");
		
	}

	public void showp(Registry r) throws RemoteException {
		System.out.println("Nome: "+ pecaAtual.nome + "\n" + 
						   "id: "+ pecaAtual.id + "\n" + 
						   "desc: "+ pecaAtual.descricao + "\n" + 
						   "quant: "+ pecaAtual.quantidade + "\n");
		
		if(pecaAtual.partList != null) {
			Iterator<Integer> iterator = pecaAtual.partList.keySet().iterator();
	
			while (iterator.hasNext()) {
			   Integer key = iterator.next();
			   Integer value = pecaAtual.partList.get(key);
	
			   System.out.println(key + " " + value);
			}
		}
		
	}
	
	public void showsubp(Registry r) {
		
		if(subpAtual != null) {
			Iterator<Integer> iterator = subpAtual.keySet().iterator();
	
			while (iterator.hasNext()) {
			   Integer key = iterator.next();
			   Integer value = subpAtual.get(key);
	
			   System.out.println(key + " " + value);
			}
		}
	}

}
