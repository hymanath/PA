package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreHealthStatus;

public interface ICadreHealthStatusDAO extends GenericDao<CadreHealthStatus, Long>{
	  public List<Object[]> getAllCadreHealthStatus();
	  public List<Object[]> getDeathsAndHospitalizationStatusWiseDetails(Long locationValue,String searchType);
	  public List<Object[]> getAllGrievanceInsuranceStatus();
	  public List<Object[]> getComplaintsDetailsByLocationAndStatus(Long locationId,String locationType,Long insuranceStatId,String issueType);
	  //public List<Object[]> testKamal();
}
