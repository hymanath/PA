package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MeekosamPetitioner;

public interface IMeekosamPetitionerDAO extends GenericDao<MeekosamPetitioner, Long> {
	public List<Object[]> getPetitionerDetailsByCardNoMobileNoAadharNo(String cardNo,String type);
}
