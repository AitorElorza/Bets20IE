package domain;


import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlSeeAlso({Registered.class,Admin.class})
@Entity
public class User {
	
	@Id @XmlID
	String username;
	String password;
	
	
	public User (String un, String pas) {
		username = un;
		password = pas;
	}
	
	public User() {
	}

	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void updatePassword(String old1, String old2, String new1, String new2) {
		if(old1.equals(old2)) {
			if(new1.equals(new2)) {
				password=new1;
			}
		}
	}

}
