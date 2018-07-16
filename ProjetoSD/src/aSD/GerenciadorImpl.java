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
				retorno = "Peça e suas subpeças adicionadas com sucesso";
				return retorno;
			} else {
				retorno = "Houve erro em adicionar a peça devido a Subpeças atual estar vazio";
				return retorno;
			}	
		}
	
			parteNova = new PartImpl(name,description,false,qntd,null);		
			retorno = "Peça adicionada com sucesso";
		
		try {
			r.bind(parteNova.id.toString(), parteNova);
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
public void addPeca(Registry r) throws RemoteException {
		
		System.out.println("Informe o ID da peça a ser adicionada: ");
		String id = sc.nextLine();
		String[] nomes = r.list();
		
		for(int i=0;i<nomes.length;i++) {
			if(nomes[i].equals(id)) {
				int q = 0;
				try {
					System.out.println("Informe a quantidade a ser adicionada: ");
					try {
					q = Integer.parseInt(sc.nextLine());
					}
					catch(NumberFormatException e){
						System.out.println("Quantidade inválida");
					}
					((PartImpl) r.lookup(id)).quantidade  = ((PartImpl) r.lookup(id)).quantidade + q;
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		}
		
	}
	public String getp(Registry r, String id) throws RemoteException {
		
		String[] nomes = r.list();
		String retorno = null;
		
		try {
			for (int i = 0;i < nomes.length;i++) {
				if (id.equals(nomes[i])) {
					pecaAtual = (PartImpl) r.lookup(id);
					retorno = "Peça selecionada, use o comando showp para visualizar";
					return retorno;
				}
			}		
		} catch (NotBoundException e) {
			retorno = "Peça não encontrada";
			return retorno;
		}
		return retorno;	
	}
	

	public String help() throws RemoteException {
		String ajuda = "Comandos:\naddp: Adiciona uma nova peça que já esteja identificada.\naddnewp: Adiciona peças que ainda não foram cadastradas.\naddsubpart: Adiciona subpeças em uma peça já cadastrada.\nclearlist: Limpar peça e lista de subpeças atuais.\ngetp: Busca uma peça, caso a busca seja bem sucedida, salva o resultado como Peça atual\nlistp: Lista as peças do repositório atual.\nquit: Desconecta do Servidor atual, irá solicitar uma nova porta.\nshowp: Mostra a peça atual.\nshowsubp: Mostra a lista de subpeças atual.\n";
		return ajuda;
	}
	
	public void clear() {
		subpAtual = null;
		pecaAtual = null;
	}

	public void getrepName(Registry r) throws RemoteException {
			System.out.println("Server atual: " + r.list()[0]);
	}

	public String listPecas(Registry r) throws RemoteException {
		
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
		String retorno = "Nome: "+ pecaAtual.nome + "\n" + 
						   "ID: "+ pecaAtual.id + "\n" + 
						   "Descrição: "+ pecaAtual.descricao + "\n" + 
						   "Quantidade: "+ pecaAtual.quantidade + "\nLista das Subpeças: \n";
		
		if(pecaAtual.partList != null) {
			for(int i=0;i<pecaAtual.partList.length;i++) {
				retorno = retorno + "ID da Subpeça: " + pecaAtual.partList[i][0] + "\n";
				retorno = retorno + "Quantidade da Subpeça: " + pecaAtual.partList[i][1] + "\n";
				retorno = retorno + "--------\n";
			}
		}
	return retorno;
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

	@Override
public void addsubpart(Registry r) throws RemoteException {
		
		System.out.println("Informe o ID da Subpeça: ");
		String id = sc.nextLine();
		boolean jaCadastrado = false;
		String[] nomes = r.list();
		
		for (int i = 0;i < nomes.length;i++) {
			if (id.equals(nomes[i])) jaCadastrado = true;
		}
		
		if (jaCadastrado == true) {
		    System.out.println("Informe a quantidade de Subpeças nessa Peças ");
		    Integer qntdP = 0;
		    try {
			qntdP = Integer.parseInt(sc.nextLine());
		    }catch (NumberFormatException e) {
		    	e.printStackTrace();
		    }
			subpAtual.put(Integer.parseInt(id), qntdP);
		} else { System.out.println("Subpeça não cadastrada"); }
		
	}

}
