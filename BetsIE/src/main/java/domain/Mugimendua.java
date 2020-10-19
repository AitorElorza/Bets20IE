package domain;

import java.io.*;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.crypto.Data;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Apustua.class,MoneyTrans.class})
@Entity
public class Mugimendua implements Serializable{

	
	@Id @XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer movId;
	private Date data;
	private float amount;
	
	@XmlIDREF
	private Registered user;
	
	public Mugimendua(int id,Date d, float a, Registered u) {
		this.movId = id;
		this.data = d;
		this.amount =a;
		this.user =u;
	}
	
	public Mugimendua() {}

	public int getId() {
		return this.movId;
	}
	public Date getData() {
		return this.data;
	}
	public float getAmount() {
		return this.amount;
	}
	
	public String toString() {
		String i= this.movId+" "+data.toString()+" "+amount+" ";
		return i;
	}
}
