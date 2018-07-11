package aSD;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PartImpl implements Part{

	int id;
	String nome;
	String descricao;
	boolean subpart;
	int quantidade;
	ArrayList<Part> partList = new ArrayList<Part>();
	
	public PartImpl(int i, String nm, String desc, boolean subp, int quant, ArrayList<Part> partL) {
		this.id = i;
		this.nome = nm;
		this.descricao = desc;
		this.subpart = subp;
		this.quantidade = quant;
		this.partList = partL;
	}
	
		
}
