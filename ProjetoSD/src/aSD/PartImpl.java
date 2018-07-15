package aSD;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PartImpl implements Part, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int count = 0;
	public Integer id;
	public String nome;
	public String descricao;
	public boolean subpart;
	public int quantidade;
	public HashMap<Integer, Integer> partList;

	public PartImpl(String nm, String desc, boolean subp, int quant, HashMap<Integer, Integer> subpAtual) {
		count++;
		this.id = count;
		this.nome = nm;
		this.descricao = desc;
		this.subpart = subp;
		this.quantidade = quant;
		this.partList = subpAtual;
	}
	
	
}
