package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.VoterTag;

public interface IVoterTagDAO extends GenericDao<VoterTag,Long>{
	
	public VoterTag getVoterTagByVoterIdAndUniqueCode(Long voterId,String uniqueCode);
	 public Long getTotalTaggedVoters();
	 public Long getVotersByType(String type);
	  public Long getTotalInsertedTaggedVoters(String status);
	  public Long getInsertedVotersByType(String type,String status);
	  
	  public List<Object[]> getTotalTaggedVoterDetails(String isType,String typeOfData);
	  public Long getVoterTagId(Long voterId);

}
