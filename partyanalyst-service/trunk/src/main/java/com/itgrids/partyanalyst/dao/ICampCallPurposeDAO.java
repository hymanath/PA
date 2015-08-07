package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CampCallPurpose;

public interface ICampCallPurposeDAO extends GenericDao<CampCallPurpose, Long>{

	public List<Object[]>  getAllCampCallPurpose();
}
