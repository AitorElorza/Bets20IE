package domain;

import java.io.*;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.crypto.Data;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class MoneyTrans extends Mugimendua{
	
	private boolean withdraw;
	
	public MoneyTrans() {
		super();
	}
	
	public MoneyTrans(int id, Date d, float a, Registered u,boolean w) {
		super(id, d, a, u);
		
		this.withdraw=w;
	}
	
	public String toString() {
		String i=super.toString()+withdraw+" ";
		return i;
	}

}