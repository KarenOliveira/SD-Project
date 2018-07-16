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

	public String addnewp(Registry r,String name,String description,int qntd, String conf) throws RemoteException {
		String retorno = "";
		PartImpl parteNova = null;

		if(conf.equals("s"))  {
			Integer[][] subp = new Integer[subpAtual.size()][2];
			
			if(!subpAtual.isEmpty()) {
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
				retorno = "Pe�a e suas subpe�as adicionadas com sucesso";
			} else {
				retorno = "Houve erro em adicionar a pe�a devido a Subpe�as atual estar vazio";
				return retorno;
			}	
			
			
		} else if (conf.equals("n")){
			parteNova = new PartImpl(name,description,false,qntd,null);		
			retorno = "Pe�a adicionada com sucesso";
			} else {return "Escolha 's' para sim ou 'n' para n�o";}
		
		try {
			if(parteNova != null) r.bind(parteNova.id.toString(), parteNova);
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public void addP(Registry r) throws RemoteException {
	/*
	 * Tentamos implementar um m�todo para alterar a quantidade de pe�as no reposit�rio,
	 * mas infelizmente o valor da vari�vel n�o � alterado conforme o esperado*/
	/*	
		System.out.println("Informe o ID da pe�a a ser adicionada: ");
		String id = sc.nextLine();
		String[] nomes = r.list();
		
		for(int i=1;i<nomes.length;i++) {
			if(nomes[i].equals(id)) {
				int q = 0;
				try {
					System.out.println("Informe a quantidade a ser adicionada: ");
					try {
					q = Integer.parseInt(sc.nextLine());
					}
					catch(NumberFormatException e){
						System.out.println("Quantidade inv�lida");
					}
					((PartImpl) r.lookup(id)).quantidade  += q;
					getp(r, id);
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			System.out.println("Nome" + ((PartImpl) r.lookup(pecaAtual.id.toString())).nome);
			System.out.println("Quantidade " + ((PartImpl) r.lookup(pecaAtual.id.toString())).quantidade);
			((PartImpl) r.lookup(pecaAtual.id.toString())).quantidade += 3;
			System.out.println("Quantidade " + ((PartImpl) r.lookup(pecaAtual.id.toString())).quantidade);
			System.out.println("Adicionado 3");
			getp(r, pecaAtual.id.toString());
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	public String addsubp(Registry r, String id) throws RemoteException {
		

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
			return "Pe�a adicionada";
		} else { return "Subpe�a n�o cadastrada"; }
		
	}
	
	public String getp(Registry r, String id) throws RemoteException {
		
		String[] nomes = r.list();
		String retorno = null;
		
		try {
			for (int i = 0;i < nomes.length;i++) {
				if (id.equals(nomes[i])) {
					pecaAtual = (PartImpl) r.lookup(id);
					retorno = "Pe�a selecionada, use o comando showp para visualizar";
					return retorno;
				}
			}		
		} catch (NotBoundException e) {
			retorno = "Pe�a n�o encontrada";
			return retorno;
		}
		return retorno;	
	}
	
	public String help() throws RemoteException {
		return "Comandos:\n"
				+ "addp: Adiciona uma nova pe�a que j� esteja identificada.\n"
				+ "addnewp: Adiciona pe�as que ainda n�o foram cadastradas.\n"
				+ "addsubpart: Adiciona subpe�as em uma pe�a j� cadastrada.\n"
				+ "clearlist: Limpar pe�a e lista de subpe�as atuais.\n"
				+ "getp: Busca uma pe�a, caso a busca seja bem sucedida, salva o resultado como Pe�a atual\n"
				+ "listp: Lista as pe�as do reposit�rio atual.\n"
				+ "quit: Desconecta do Servidor atual, ir� solicitar uma nova porta.\n"
				+ "showp: Mostra a pe�a atual.\nshowsubp: Mostra a lista de subpe�as atual.\n";
	}
	
	public void clear() {
		subpAtual.clear();
		pecaAtual = null;
	}

	public String getrepName(Registry r) throws RemoteException {
			return "Server atual: " + r.list()[0];
	}

	public String listp(Registry r) throws RemoteException {
		
		String[] list = r.list();
		String retorno = "";
		try {
			for(int i = 1;i<list.length;i++) {
				retorno = retorno + "Nome: " + ((PartImpl) r.lookup(list[i])).nome + "\n";
				retorno = retorno + "ID: " + ((PartImpl) r.lookup(list[i])).id + "\n";
			}
			return retorno;
		}
		catch (NotBoundException e) { return retorno; }
	}

	public String showp(Registry r) throws RemoteException {
		
		if(pecaAtual!=null) {
		
			String retorno = "Nome: "+ pecaAtual.nome + "\n" + 
							   "ID: "+ pecaAtual.id + "\n" + 
							   "Descri��o: "+ pecaAtual.descricao + "\n" +
							   "Quantidade: "+ pecaAtual.quantidade;
			
			if(pecaAtual.partList != null) {
				 retorno += "\nLista das Subpe�as: \n";
				for(int i=0;i<pecaAtual.partList.length;i++) {
					
					retorno += "ID da Subpe�a: " + pecaAtual.partList[i][0] + "\n" + 
					"Quantidade da Subpe�a: " + pecaAtual.partList[i][1] + "\n" + 
					"--------\n";
				}
			}
		return retorno;
		} else { return "N�o h� pe�a selecionada"; }
	} 
	
	public String showsubp(Registry r) {
		
		String retorno = "";
		
		if(subpAtual != null) {
			Iterator<Integer> iterator = subpAtual.keySet().iterator();
	
			while (iterator.hasNext()) {
			   Integer key = iterator.next();
			   Integer value = subpAtual.get(key);
	
			   retorno += "ID: " + key + ", Quantidade: " + value + "\n";
			   return retorno;
			}
		} else {retorno = "N�o h� subpe�a selecionada";}
		return retorno;
	}


}
