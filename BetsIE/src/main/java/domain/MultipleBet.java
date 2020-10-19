package domain;

import java.io.*;
import java.util.Date;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.crypto.Data;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class MultipleBet extends Mugimendua{
	
	@XmlIDREF
	private Vector<Kuota> mugimenduak=new Vector<Kuota>();
	private boolean win;
	private boolean lose;
	private boolean tbd;
	private boolean paid;
	
	public MultipleBet() {super();}
	
	public MultipleBet(int id, Date d, float a, Registered u,Vector<Kuota> k) {
		super(id, d, a,u);
		this.mugimenduak =k;
		this.win=false;
		this.lose=false;
		this.tbd=true;
		this.setPaid(false);
	}

	public Vector<Kuota> getMugimenduak() {
		return mugimenduak;
	}

	public void setMugimenduak(Vector<Kuota> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}

	public boolean isWin() {
		return win;
	}



	public void setWin(boolean win) {
		this.win = win;
	}



	public boolean isLose() {
		return lose;
	}



	public void setLose(boolean lose) {
		this.lose = lose;
	}



	public boolean isTbd() {
		return tbd;
	}



	public void setTbd(boolean tbd) {
		this.tbd = tbd;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	

}
