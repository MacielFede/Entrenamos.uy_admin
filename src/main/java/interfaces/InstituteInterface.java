package interfaces;

import java.util.List;
import java.util.Map;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;
import dataTypes.DtUser;


public interface InstituteInterface {
	public boolean registerUserToClass(DtClass rclass, DtUser user);
	public boolean checkActivityAvialability(String name);
	public DtClass showClassInfo(String className);
	public DtClass chooseClassByName(String className);
	public List<DtClass> listClassesDictationRanking();
	public boolean createSportClass(DtClass newClass, Integer idSportActivity);
	public Map<String, DtInstitute> listSportInstitutes();
	public Map<String, DtActivity> selectInstitution(String institutionName);
	public Map<String, DtClass> chooseActivity(String activity);
	public List<DtActivity> listSportsActivitiesRanking();
	public void modiFySportInstitute(DtInstitute institute);
	public void addNewSportActivity(DtActivity sportActivity, String nameInstitute);
	public void registerInstitution(String name, String description, String url);
	public DtActivity getActivity(String activityName);
	public DtInstitute chooseSportInstitute(String name);
	public boolean checkInstitutionAvialability(String name);
}
