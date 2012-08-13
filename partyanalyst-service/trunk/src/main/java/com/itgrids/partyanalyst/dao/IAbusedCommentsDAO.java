package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AbusedComments;

public interface IAbusedCommentsDAO extends GenericDao<AbusedComments,Long>{
	 public List<AbusedComments> getAllAbuseComment(Date firstDate, Date secondDate,String selectstatus);
	 
	 public Integer controlAbuseComments(Long id, String status,String isDelete);
	 
	 public Long checkForAlreadyAbused(Long cmntId);

}
