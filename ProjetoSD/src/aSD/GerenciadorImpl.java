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
	public static HashMap<Integer,Integer> subpAtual = new HashMap	<Integer,Integer>();
	static transient Scanner sc = new Scanner(System.in);

	public void addnewp(Registry r) throws RemoteException {
		PartImpl parteNova = null;
		System.out.println("----------------------------------------\n");
		System.out.println("Informe o nome da pe�a: ");
		String name = sc.nextLine();
		System.out.println("Informe a descri��o da pe�a: ");
		String description = sc.nextLine();
		try {
		System.out.println("Informe a quantidade de Pe�as: ");
		int qntd = Integer.parseInt(sc.nextLine());
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		System.out.println("A pe�a possui subparts(s/n): ");
		String conf = sc.nextLine();
		if(conf.equals("s"))  {
			Integer[][] subp = new Integer[subpAtual.size()][2];
			
			if(subpAtual != null) {
				Iterator<Integer> iterator = subpAtual.keySet().iterator();
				int i = 0;
				while (iterator.hasNext()) {
				   
				   Integer key = iterator.next();
				   Integer value = subpAtual.get(key);
		
				   subp[i][0] = key;
				   subp[i][1] = value;
				   i++;
				}
				parteNova = new PartImpl(name,description,true,qntd,subp);
				
			} else {System.out.println("N�o h� subpart atual para ser adicionada");}
			
		}
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
		    Integer qntdP = 0;
		    try {
			qntdP = Integer.parseInt(sc.nextLine());
		    }catch (NumberFormatException e) {
		    	e.printStackTrace();
		    }
			subpAtual.put(Integer.parseInt(id), qntdP);
		} else { System.out.println("Subpe�a n�o cadastrada"); }
		
	}
		
	public void getp(Registry r) throws RemoteException {
		
		System.out.println("Informe o ID da Pe�a: ");
		String id = sc.nextLine();
		String[] nomes = r.list();
		boolean conf = false;
		
		try {
			for (int i = 0;i < nomes.length;i++) {
				if (id.equals(nomes[i])) {
					pecaAtual = (PartImpl) r.lookup(id);
					System.out.println("Pe�a selecionada, use o comando showp para visualizar");
					conf = true;
				}
			}
			
			if(conf == false) System.out.println("Pe�a n�o encontrada");
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void help() throws RemoteException {
		System.out.println("Comandos:\naddp: Adiciona uma nova pe�a que j� esteja identificada.\n"
				+ "addnewp: Adiciona pe�as que ainda n�o foram cadastradas.\n"
				+ "addsubpart: Adiciona subpe�as em uma pe�a j� cadastrada.\n"
				+ "clearlist: Limpar pe�a e lista de subpe�as atuais.\n"
				+ "getp: Busca uma pe�a, caso a busca seja bem sucedida, salva o resultado como Pe�a atual\n"
				+ "listp: Lista as pe�as do reposit�rio atual.\n"
				+ "quit: Desconecta do Servidor atual, ir� solicitar uma nova porta.\n"
				+ "showp: Mostra a pe�a atual.\n"
				+ "showsubp: Mostra a lista de subpe�as atual.\n");
	}
	
	public void clear() {
		subpAtual = null;
		pecaAtual = null;
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
						   "ID: "+ pecaAtual.id + "\n" + 
						   "Descri��o: "+ pecaAtual.descricao + "\n" + 
						   "Quantidade: "+ pecaAtual.quantidade + "\n");
		
		if(pecaAtual.partList != null) {
			for(int i=0;i<pecaAtual.partList.length;i++) {
				System.out.println("ID da Subpe�a: " + pecaAtual.partList[i][0]);
				System.out.println("Quantidade da Subpe�a: " + pecaAtual.partList[i][1]);
			}
		}
	}
	
	public void showsubp(Registry r) {
		
		if(subpAtual != null) {
			Iterator<Integer> iterator = subpAtual.keySet().iterator();
	
			while (iterator.hasNext()) {
			   Integer key = iterator.next();
			   Integer value = subpAtual.get(key);
	
			   System.out.println("ID: " + key + "\nQuantidade: " + value);
			}
		}
	}

}
