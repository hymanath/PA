/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 11, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.appfuse.dao.jpa.GenericDaoJpa;
import javax.persistence.Query;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.columns.enums.NominationColumnNames;
import com.itgrids.partyanalyst.model.Nomination;

/**
*@author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
*@author <a href="mailto:sriharigopalnalam@gmail.com">Srihari</a>
*/

public class NominationDAO extends GenericDaoHibernate<Nomination, Long> implements
		INominationDAO {
    
	
	public NominationDAO() {
		super(Nomination.class);
	}	 
	public List<Nomination> findByAssets(Object assets) {
		
		return findByProperty(NominationColumnNames.ASSETS, assets);
	}

	public List<Nomination> findByCriminalCharges(Object criminalCharges) {
		
		return findByProperty(NominationColumnNames.CRIMINAL_CHARGES, criminalCharges);
	}

	public List<Nomination> findByLiabilities(Object liabilities) {
		
		return findByProperty(NominationColumnNames.LIABILITIES, liabilities);
	}

	public List<Nomination> findByNominationStatus(Object nominationStatus) {
		
		return findByProperty(NominationColumnNames.NOMINATION_STATUS, nominationStatus);
	}

	@SuppressWarnings("unchecked")
	public List<Nomination> findByProperty(NominationColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from Nomination where " + propertyName.getValue() + "=?", value);		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Nomination> findByConstituencyElection(Long constituencyElectionID){
		return getHibernateTemplate().find( "from Nomination model where model.constituencyElection.constiElecId = ?",constituencyElectionID);
	}
	
	@SuppressWarnings("unchecked")
	public Nomination findByConstituencyElectionAndCandidate(String candidateName, Long constituencyElectionID){
		Nomination nomination = null;
		Object[] params = {candidateName, constituencyElectionID};
		List<Nomination> list = getHibernateTemplate().find( "from Nomination model where model.candidate.lastname = ? and model.constituencyElection.constiElecId = ?",params);
		if(list != null && list.size() > 0)
			nomination = list.get(0);
		return nomination;
	}
}
