package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreVolunteer;



public interface ITdpCadreVolunteerDAO extends GenericDao<TdpCadreVolunteer, Long>{
	
	public List<Long> checkVolunteerByMobileOrEmail(String mobileNo, String emailId);
	
	public Long getDeviceTotalCount(String deviceType,Long constituencyId,String searchType);
}
