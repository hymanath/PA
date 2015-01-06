package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreIVREnquiry;

public interface ICadreIVREnquiryDAO  extends GenericDao<CadreIVREnquiry, Long>{
	public List<Object[]> getLocationWiseEnquiryInfo(String locationLvl,Long locationValue,Long userId,String resultType);
}
