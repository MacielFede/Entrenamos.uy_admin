package publishers;

import java.io.InputStream;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;
import exceptions.EmptyRequiredFieldException;
import exceptions.NullPriceException;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class InstitutePublisher {
	private ControllerFactory factory;
	private InstituteInterface icon;
	private Endpoint endpoint;
	private String baseUrl;
	
	public InstitutePublisher() {
		factory = ControllerFactory.getInstance();
		icon = factory.getInstituteInterface();
		try {
			InputStream configStream = InstitutePublisher.class.getClassLoader().getResourceAsStream("config.properties");
			if (configStream != null) {
				Properties properties = new Properties();
				properties.load(configStream);
				baseUrl = properties.getProperty("baseUrl");
			} 
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

	@WebMethod(exclude = true)
	public void publish() {
		endpoint = Endpoint.publish(baseUrl + "/instituteController", this);
		System.out.println(baseUrl + "/instituteController");
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
        return endpoint;
	}
	
	@WebMethod
	public boolean checkClassNameAvailability(String className) {
		return icon.checkClassNameAvailability(className);
	}
	
	@WebMethod
	public boolean checkActivityAvialability(String name) {
		return icon.checkActivityAvialability(name);
	}
	
	@WebMethod
	public DtClass chooseClassByName(String className) {
		return icon.chooseClassByName(className);
	}
	
	@WebMethod
	public DtClass [] listClassesDictationRanking() {
		return (DtClass[]) icon.listClassesDictationRanking().toArray();
	}
	
	@WebMethod
	public void createSportClass(DtClass newClass, String idSportActivity, String idProfessor) {
		icon.createSportClass(newClass, idSportActivity, idProfessor);
	}
	
	@WebMethod
	public DtInstitute [] listSportInstitutes() {
		return (DtInstitute[]) icon.listSportInstitutes().entrySet().toArray();
	}
	
	@WebMethod
	public DtActivity [] selectInstitution(String institutionName) {
		return (DtActivity[]) icon.selectInstitution(institutionName).entrySet().toArray();
	}
	
	@WebMethod
	public DtClass[] chooseActivity(String activity) {
		return (DtClass[]) icon.chooseActivity(activity).entrySet().toArray();
	}
	
	@WebMethod
	public DtActivity [] listSportsActivitiesRanking() {
		return (DtActivity[]) icon.listSportsActivitiesRanking().toArray();
	}
	
	@WebMethod
	public void modiFySportInstitute(DtInstitute institute) {
		icon.modiFySportInstitute(institute);
	}
	
	@WebMethod
	public void addNewSportActivity(DtActivity sportActivity, String nameInstitute) {
		icon.addNewSportActivity(sportActivity, nameInstitute);
	}
	
	@WebMethod
	public void registerInstitution(String name, String description, String url) {
		icon.registerInstitution(name, description, url);
	}
	
	@WebMethod
	public DtActivity getActivity(String activityName) {
		return icon.getActivity(activityName);
	}
	
	@WebMethod
	void updateActivityInfo(DtActivity dtA) throws EmptyRequiredFieldException, NullPriceException {
		icon.updateActivityInfo(dtA);
	}
	
	@WebMethod
	public DtInstitute chooseSportInstitute(String name) {
		return icon.chooseSportInstitute(name);
	}
	
	@WebMethod
	public boolean checkInstitutionAvialability(String name) {
		return icon.checkInstitutionAvialability(name);
	}
	
	@WebMethod
    String[] listSportActivitiesByName() {
		return icon.listSportActivitiesByName();
	}
	
	@WebMethod
    public DtActivity[] getAllActivities() {
		return (DtActivity[]) icon.getAllActivities().entrySet().toArray();
	}
	
	@WebMethod
	DtActivity chooseActivityByName(String activity) {
		return icon.chooseActivityByName(activity);
	}
	
}
