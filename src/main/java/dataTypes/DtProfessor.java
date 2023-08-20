package dataTypes;

import java.util.Date;

public class DtProfessor extends DtUser{
	//private Map<String, DtClass> relatedClasses;
	private final String description;
	private final String biography;
	private final String webPage;
	
	public DtProfessor(
			/* Map<String, DtClass> relatedClasses ,*/ 
			String description, 
			String biography, 
			String webPage, 
			String nickname, 
			String name, 
			String lastName, 
			String email, 
			Date bornDate) {
		super(nickname, name, lastName, email, bornDate);
		/*this.relatedClasses = relatedClasses;*/
		this.description = description;
		this.biography = biography;
		this.webPage = webPage;
	}
	
	public String getDescription() {
		return description;
	}

	public String getBiography() {
		return biography;
	}

	public String getWebPage() {
		return webPage;
	}
	/*
	public Map<String, DtClass> getRelatedClasses() {
		return relatedClasses;
	}*/
}