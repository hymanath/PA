package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreTxnUser;


public interface ICadreTxnUserDAO  extends GenericDao<CadreTxnUser, Long>{
	
	public List checkUserExists(Long userId , String mobileNo);
	public List<String> checkForExistsMobileNo(String mobileNo);
}
