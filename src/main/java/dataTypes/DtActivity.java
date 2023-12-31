package dataTypes;


import java.util.Date;
import java.util.Map;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtActivity {

	private final String name;
	private final String description;
	private final String imgName;
	private final  Integer duration;
	private final  Float price;
	private final  Date registryDate;
	private final int classesQuantity;
	private final Map<String, DtClass> classes;
	
	public DtActivity() {
		this.name = "";
		this.description = "";
		this.imgName = "";
		this.duration = null;
		this.price = null;
		this.registryDate = new Date();
		this.classesQuantity = 0;
		this.classes = null;
	}


	public DtActivity(String name, String description, String imgName, Integer duration, Float price, Date registryDate, int classesQuantity, Map<String, DtClass> classes)
	{
		this.name           	= name;
		this.description    	= description;
		this.duration       	= duration;
		this.price          	= price;
		this.registryDate   	= registryDate;
		this.classesQuantity 	= classesQuantity;
		this.classes			= classes;
		this.imgName			= imgName;
	}

	public String getImgName() {
		return imgName;
	}

	public Map<String, DtClass> getClasses() {
		return classes;
	}

	public int getClassesQuantity() {
		return classesQuantity;
	}

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

	public Date getRegistryDate() {
		return registryDate;
	}

}
