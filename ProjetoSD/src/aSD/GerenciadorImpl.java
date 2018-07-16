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
				retorno = "Pe�a e suas subpe�as adicionadas com sucesso";
				return retorno;
			} else {
				retorno = "Houve erro em adicionar a pe�a devido a Subpe�as atual estar vazio";
				return retorno;
			}	
		}
	
			parteNova = new PartImpl(name,description,false,qntd,null);		
			retorno = "Pe�a adicionada com sucesso";
		
		try {
			r.bind(parteNova.id.toString(), parteNova);
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
public void addPeca(Registry r) throws RemoteException {
		
		System.out.println("Informe o ID da pe�a a ser adicionada: ");
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
						System.out.println("Quantidade inv�lida");
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
		String ajuda = "Comandos:\naddp: Adiciona uma nova pe�a que j� esteja identificada.\naddnewp: Adiciona pe�as que ainda n�o foram cadastradas.\naddsubpart: Adiciona subpe�as em uma pe�a j� cadastrada.\nclearlist: Limpar pe�a e lista de subpe�as atuais.\ngetp: Busca uma pe�a, caso a busca seja bem sucedida, salva o resultado como Pe�a atual\nlistp: Lista as pe�as do reposit�rio atual.\nquit: Desconecta do Servidor atual, ir� solicitar uma nova porta.\nshowp: Mostra a pe�a atual.\nshowsubp: Mostra a lista de subpe�as atual.\n";
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
						   "Descri��o: "+ pecaAtual.descricao + "\n" + 
						   "Quantidade: "+ pecaAtual.quantidade + "\nLista das Subpe�as: \n";
		
		if(pecaAtual.partList != null) {
			for(int i=0;i<pecaAtual.partList.length;i++) {
				retorno = retorno + "ID da Subpe�a: " + pecaAtual.partList[i][0] + "\n";
				retorno = retorno + "Quantidade da Subpe�a: " + pecaAtual.partList[i][1] + "\n";
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

}
