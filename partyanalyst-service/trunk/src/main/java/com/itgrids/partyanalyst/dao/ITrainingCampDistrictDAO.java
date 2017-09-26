
package com.itgrids.partyanalyst.dao;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampDistrict;

public interface ITrainingCampDistrictDAO extends GenericDao<TrainingCampDistrict, Long>{
	public List<Object[]> getCampDetailsByDistrictIds(List<Long> districtIdsList);
	public List<Object[]> getTrainingCampDetailsByCampIds(List<Long> trainingCampIdsList);
}
