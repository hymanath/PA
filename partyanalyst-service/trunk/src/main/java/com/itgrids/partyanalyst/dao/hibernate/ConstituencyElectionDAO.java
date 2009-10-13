/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Aug 4, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;
/**
*@author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
*@author <a href="mailto:sriharigopalnalam@gmail.com">Srihari</a>
*/
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.appfuse.dao.jpa.GenericDaoJpa;
import javax.persistence.Query;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.model.ConstituencyElection;


public class ConstituencyElectionDAO extends GenericDaoHibernate<ConstituencyElection, Long> implements
		IConstituencyElectionDAO {

	public ConstituencyElectionDAO() {
		super(ConstituencyElection.class);
	}	 
	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByProperty(String propertyName, Object value) {
		return getHibernateTemplate().find("from ConstituencyElection where " + propertyName + "=?", value);		
		
	}

	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElection(Long electionID){
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionId =?", electionID);	
	}
	

	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndDistrict(Long electionID, Long districtID){
		Long[] params = {electionID,districtID};
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionId =? and model.constituency.district.districtId=?", params);	
	}

	@SuppressWarnings("unchecked")
	public List<ConstituencyElection> findByElectionAndConstituency(Long electionID, Long constituencyID){
		Long[] params = {electionID,constituencyID};
		return getHibernateTemplate().find("from ConstituencyElection model where model.election.electionId =? and model.constituency.constituencyId=?", params);	
	}
	
}
