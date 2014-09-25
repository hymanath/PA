package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.dao.INewsBullitionDAO;
import com.itgrids.partyanalyst.dao.INewsBullitionNewsTypeDAO;
import com.itgrids.partyanalyst.dao.INewsBullitionTypeDAO;
import com.itgrids.partyanalyst.dao.INewsBullitionValuesDAO;
import com.itgrids.partyanalyst.service.INewsBullitionService;

public class NewsBullitionService implements INewsBullitionService{
	
	private INewsBullitionDAO			  newsBullitionDAO ;
	private INewsBullitionTypeDAO		  newsBullitionTypeDAO ;
	private INewsBullitionNewsTypeDAO     newsBullitionNewsTypeDAO;
	private INewsBullitionValuesDAO       newsBullitionValuesDAO ;
	
	
	public void setNewsBullitionDAO(INewsBullitionDAO newsBullitionDAO) {
		this.newsBullitionDAO = newsBullitionDAO;
	}
	public void setNewsBullitionTypeDAO(INewsBullitionTypeDAO newsBullitionTypeDAO) {
		this.newsBullitionTypeDAO = newsBullitionTypeDAO;
	}
	public void setNewsBullitionNewsTypeDAO(
			INewsBullitionNewsTypeDAO newsBullitionNewsTypeDAO) {
		this.newsBullitionNewsTypeDAO = newsBullitionNewsTypeDAO;
	}
	public void setNewsBullitionValuesDAO(
			INewsBullitionValuesDAO newsBullitionValuesDAO) {
		this.newsBullitionValuesDAO = newsBullitionValuesDAO;
	}
	
	
	
	
	
}
