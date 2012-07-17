package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.Visibility;

public interface IVisibilityDAO extends GenericDao<Visibility,Long>{
	
	public List<Visibility> getVisibilityByVisibilityType(String visibilityType);

}
