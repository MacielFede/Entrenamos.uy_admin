package entities;

import dataTypes.DtActivity;
import dataTypes.DtClass;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import java.util.Map;
import java.util.TreeMap;

@Entity
public class Activity {
	@Id
	private String name;
	private String description;
	private String imgName;

	private Integer duration;
	private Float price;
	@Temporal(TemporalType.TIMESTAMP)
	private Date registryDate;
	@OneToMany(cascade = CascadeType.ALL)
	private Map<String, Class> classes;

	public Activity() {
	}

	public Activity(String name, String description, String imgName, Integer duration, Date registeryDate, Float price, Map<String, Class> classes) {
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.price = price;
		this.classes = classes;
		this.registryDate = registeryDate;
		this.imgName = imgName;
	}

	// GETTERS
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Integer getDuration() {
		return duration;
	}

	public Float getPrice() {
		return price;
	}

	public Date getregistryDate() {
		return registryDate;
	}

	public Map<String, Class> getClasses() {
		return classes;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public Date getRegistryDate() {
		return registryDate;
	}

	public void setRegistryDate(Date registryDate) {
		this.registryDate = registryDate;
	}

	public void setClasses(Map<String, Class> classes) {
		this.classes = classes;
	}
	
	// SETTERS

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setregistryDate(Date registryDate) {
		this.registryDate = registryDate;
	}

	// METHODS
	public boolean existsClass(String name) {
		// programar logica
		return false;
	}

	public Map<String, Class> getAssociatedClasses() {
		return classes;
	}

	public Class getClass(String className) {
		// programar logica
		return new Class();
	}

	public DtActivity getData() {
		Map<String, DtClass> classesList = new TreeMap<String, DtClass>();
		for(Class c : this.classes.values()) {
			classesList.put(c.getName(), c.getData());
		}
		return new DtActivity(this.name, this.description, this.imgName, this.duration, this.price, this.registryDate, this.classes.size(), classesList);
	}
}
