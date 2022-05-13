package jpa.entitymodels;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private String sEmail;
	private String sName;
	private String sPass;
	@OneToMany(targetEntity=Student.class,fetch=FetchType.EAGER)
	private List<String> sCourses;
	
	
	public Student() {
	}

	public Student(String sEmail, String sName, String sPass, List<String> sCourses) {
		super();
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.sCourses = sCourses;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}
	@ManyToOne
	@JoinColumn(name="cId")
	public List<String> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<String> sCourses) {
		this.sCourses = sCourses;
	}

	
	
	
}
