package aSD;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartImpl implements Part{
	
	static int count = 0;
	Integer id;
	String nome;
	String descricao;
	boolean subpart;
	int quantidade;
	Map<Integer, Integer> partList;

	public PartImpl(String nm, String desc, boolean subp, int quant, Map<Integer, Integer> partL) {
		count++;
		this.id = count;
		this.nome = nm;
		this.descricao = desc;
		this.subpart = subp;
		this.quantidade = quant;
		this.partList = partL;
	}
}
