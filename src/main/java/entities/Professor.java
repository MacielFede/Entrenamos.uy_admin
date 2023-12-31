package entities;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import dataTypes.DtClass;
import dataTypes.DtProfessor;
import dataTypes.DtUser;

@Entity
public class Professor extends User {

	@OneToMany(cascade = CascadeType.ALL)
	private Map<String, Class> classes;

	@ManyToOne(cascade = CascadeType.ALL)
	private Institute sportInstitution;
	private String description;
	private String biography;
	private String webPage;

	public Professor() {
		super();
	}

	public Professor(String description, String biography, String webPage, String nickname, String name,
					 String lastName, String email, Date bornDate, String password, Institute sportInstitution) {
		super(nickname, name, lastName, email, bornDate, password);
		this.description = description;
		this.biography = biography;
		this.webPage = webPage;
		this.sportInstitution = sportInstitution;
		this.classes = new TreeMap<String,Class>();
	}

	public Professor(String description, String biography, String webPage, String nickname, String name,
					 String lastName, String email, Date bornDate, String password) {
		super(nickname, name, lastName, email, bornDate, password);
		this.description = description;
		this.biography = biography;
		this.webPage = webPage;
		this.classes = new TreeMap<String,Class>();
	}

//	public Professor(String description, String biography, String webPage, String nickname, String name,
//					 String lastName, String email, Date bornDate, Institute sportInstitution) {
//		super(nickname, name, lastName, email, bornDate);
//		this.description = description;
//		this.biography = biography;
//		this.webPage = webPage;
//		this.sportInstitution = sportInstitution;
//		this.classes = new TreeMap<String,Class>();
//	}
//
//	public Professor(String description, String biography, String webPage, String nickname, String name,
//					 String lastName, String email, Date bornDate) {
//		super(nickname, name, lastName, email, bornDate);
//		this.description = description;
//		this.biography = biography;
//		this.webPage = webPage;
//		this.classes = new TreeMap<String,Class>();
//	}

	public Institute getSportInstitution() {
		return sportInstitution;
	}

	public void setSportInstitution(Institute sportInstitution) {
		this.sportInstitution = sportInstitution;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public Map<String, Class> getClasses() {
		return classes;
	}

	public void setClasses(Map<String, Class> classes) {
		this.classes = classes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public DtUser getData() {
		Map<String,DtClass> profClasses = new TreeMap<String, DtClass>();
		if (this.classes != null) {
			for (Map.Entry<String, Class> entry : this.classes.entrySet()) {
				Class currentClass = entry.getValue();
				profClasses.put(currentClass.getName(), currentClass.getData());
			}
		}

		return new DtProfessor(profClasses, this.description, this.biography, this.webPage,
				this.nickname, this.name, this.lastName, this.email, this.bornDate, this.password);
	}


	@Override
	public Map<String, DtClass> getRelatedClasses() {
		Map<String,DtClass> profClasses = new TreeMap<String, DtClass>();
		if (this.classes != null) {
			for (Map.Entry<String, Class> entry : this.classes.entrySet()) {
				Class currentClass = entry.getValue();
				profClasses.put(currentClass.getName(), currentClass.getData());
			}
		}
		return profClasses;
	}

}