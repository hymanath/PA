package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.BloodGroup;

public interface IBloodGroupDAO extends GenericDao<BloodGroup,Long>{
	
	public List<BloodGroup> getBloodGroupList();

}
