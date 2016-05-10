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
	  //public List<Object[]> getRequestRaisedTimeDetails(Long complaintId);
	  //public List<Object[]> getAllStatusDetails(Long complaintId);
	  public List<Object[]> getAllStatusDetailsByComplaint(Long complaintId,String type);
	  public List<String> getCompletedStatusBycomplaintId(Long complaintId);
	  public List<String> getStatusBycomplaintIdForInsurance(Long complaintId);
	  public List<Object[]> getComplaintsDetailsForGrievanceByLocationAndStatus(Long locationId,String locationType,String status,String issueType);
	  public List<Object[]> getGrievanceRequestDetailsForBenifits(Long id,String searchType,String status);
}
