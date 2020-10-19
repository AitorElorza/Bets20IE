package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Kuota {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private Integer kuotaNum;
	private String desk;
	private Integer r; //Irabazia euroko
	private Boolean result;

	@XmlIDREF
	private Question question;
	
	public Kuota() {super();}
	
	public Kuota(Integer n, String desk, Integer r, Question q) {
		
		this.kuotaNum=n;
		this.desk=desk;
		this.r=r;
		this.question = q;
		this.result = null;
	
	}
	

	public Boolean getResult() {
		return result;
	}


	public void setResult(Boolean result) {
		this.result = result;
	}
	
	public Integer getR() {
		return r;
	}
	public void setR(Integer r) {
		this.r = r;
	}


	public Integer getKuotaNum() {
		return kuotaNum;
	}


	public void setKuotaNum(Integer kuotaNum) {
		this.kuotaNum = kuotaNum;
	}


	public String getDesk() {
		return desk;
	}


	public void setDesk(String desk) {
		this.desk = desk;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}

	
	
}
