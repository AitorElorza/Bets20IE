package domain;

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
public class Registered extends User{
	
	private Integer creditCard;
	private String email;
	private String name;
	private String secondName;
	private float credit;
	private float winnings;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Mugimendua> mugimenduak=new Vector<Mugimendua>();
	
	public Registered() {
		super();
	}
	
	public Registered(String un, String pas,Integer c, String em, String n, String sn) {
		super(un, pas);
		this.creditCard=c;
		this.email=em;
		this.name=n;
		this.secondName=sn;
		this.credit=0;
		this.winnings=0;
		}

	public int getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(int creditCard) {
		this.creditCard = creditCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public Vector<Mugimendua> getMugimenduak() {
		return mugimenduak;
	}

	public void setMugimenduak(Vector<Mugimendua> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}
	
	public void addMugimendua(Mugimendua m) {
		this.mugimenduak.add(m);
	}

	public float getWinnings() {
		return winnings;
	}

	public void setWinnings(float winnings) {
		this.winnings = winnings;
	}
	
	
}
