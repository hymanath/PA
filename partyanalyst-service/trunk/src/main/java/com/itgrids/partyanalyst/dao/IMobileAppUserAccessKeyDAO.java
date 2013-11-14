package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppUserAccessKey;
import com.itgrids.partyanalyst.model.User;

public interface IMobileAppUserAccessKeyDAO extends GenericDao<MobileAppUserAccessKey,Long>{
	
	public List<Object[]> checkUniqueCodeByAccesskey(String uniquecode,String accessKey);

}
