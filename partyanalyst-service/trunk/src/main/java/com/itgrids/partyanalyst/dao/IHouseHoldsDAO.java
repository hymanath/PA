
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHSurveyQuestion;
import com.itgrids.partyanalyst.model.HouseHolds;

public interface IHouseHoldsDAO extends GenericDao<HouseHolds, Long>{
	
	public List<HouseHolds> getHouseHoldInfoByPanchayatOrLocalId(Long panchayatId,Long localBodyId,String houseNo);
}