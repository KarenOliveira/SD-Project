package aSD;

import java.io.Serializable;
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

public class PartImpl implements Part, Serializable{
	
	public static int count = 0;
	public Integer id;
	public String nome;
	public String descricao;
	public boolean subpart;
	public int quantidade;
	public Map<PartImpl, Integer> partList;

	public PartImpl(String nm, String desc, boolean subp, int quant, Map<PartImpl, Integer> subpAtual) {
		count++;
		this.id = count;
		this.nome = nm;
		this.descricao = desc;
		this.subpart = subp;
		this.quantidade = quant;
		this.partList = subpAtual;
	}
	
	
}
