package aSD;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartImpl implements Part{

	int id;
	String nome;
	String descricao;
	boolean subpart;
	int quantidade;
	HashMap<Integer, Integer> partList;

	public PartImpl(int i, String nm, String desc, boolean subp, int quant, HashMap<Integer, Integer> partL) {
		this.id = i;
		this.nome = nm;
		this.descricao = desc;
		this.subpart = subp;
		this.quantidade = quant;
		this.partList = partL;
	}
}
